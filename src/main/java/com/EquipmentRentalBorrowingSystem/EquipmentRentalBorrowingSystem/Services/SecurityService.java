 package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

 import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
 import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.UserRepository;
 import com.google.firebase.auth.FirebaseAuth;
 import com.google.firebase.auth.FirebaseAuthException;
 import com.google.firebase.auth.FirebaseToken;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.stereotype.Service;
 import org.springframework.util.StringUtils;

 import javax.servlet.http.HttpServletRequest;
 import java.util.Objects;

 @Service
 public class SecurityService {

     private final HttpServletRequest httpServletRequest;
     private final UserRepository userRepository;

     public SecurityService(HttpServletRequest httpServletRequest, UserRepository userRepository) {
         this.httpServletRequest = httpServletRequest;
         this.userRepository = userRepository;
     }

     public String getUserName() throws FirebaseAuthException {
         ResponseEntity<UserModel> userModel = this.getCurrentUser();
         return Objects.requireNonNull(userModel.getBody()).getName();
     }


     public ResponseEntity<UserModel> getCurrentUser() throws FirebaseAuthException {
         String accessToken = this.getBearerToken(httpServletRequest);
         FirebaseToken decodeToken = FirebaseAuth.getInstance().verifyIdToken(accessToken);
         UserModel userModel = userRepository.findByLocalId(decodeToken.getUid());
         if (userModel == null)
             return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
         return new ResponseEntity<>(userModel, HttpStatus.OK);
     }

     public String getBearerToken(HttpServletRequest request) {
         String bearerToken = null;
         String authorization = request.getHeader("Authorization");
         if (StringUtils.hasText(authorization) &&
                 authorization.startsWith("Bearer ")) {
             bearerToken = authorization.substring(7);
         }
         return bearerToken;
     }
 }
