package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Jwt.JwtHelper;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.HomeAddressModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.RefreshTokenRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.UserRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.HomeAdressService;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtHelper jwtHelper;
    private final HomeAdressService homeAdressService;
    @Autowired
    HttpServletRequest request;

    public UserController(UserService userService, UserRepository userRepository, AuthenticationManager authenticationManager, RefreshTokenRepository refreshTokenRepository, JwtHelper jwtHelper, HomeAdressService homeAdressService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtHelper = jwtHelper;
        this.homeAdressService = homeAdressService;
    }

    @GetMapping("/profile")
    public UserModel profile(Principal principal) {
        Optional<UserModel> userModel = userService.findByLocalId(principal.getName());
        if (userModel.isEmpty()) return null;
        return userModel.get();
    }

    @GetMapping("/isAuthed")
    public Boolean isAuthed(Principal principal) {
        Optional<UserModel> userModel = userService.findByLocalId(principal.getName());
        return userModel.isPresent();
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RegisteredOAuth2AuthorizedClient("google") OAuth2AuthorizedClient authorizedClient) {
        return ResponseEntity.ok(authorizedClient.getPrincipalName());
//        String refreshToken = token.getRefreshToken();
//        RefreshToken newToken = new RefreshToken();
//        newToken.setLocalId("118044518041531359821");
//        refreshTokenRepository.save(newToken);
//        String accessToken = jwtHelper.generateAccessToken(null);
//        String newRefreshTokenString = jwtHelper.generateRefreshToken(null,newToken);
//
//        return  ResponseEntity.ok(new RefreshToken("118044518041531359821",accessToken,newRefreshTokenString));
    }

    @PostMapping("/updateUserInformation")
    public ResponseEntity<UserModel> updateUserInformation(@RequestBody UserModel userModel, Principal principal) {
        Optional<UserModel> user = userService.findByLocalId(principal.getName());
        if(user.isPresent()){
            userModel.setUserId(user.get().getUserId());
        }
        HomeAddressModel homeAddressModel = homeAdressService.save(userModel.getHomeAddressModel());
        userModel.setHomeAddressModel(homeAddressModel);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        userModel.setLocalId(principal.getName());
        userModel.setCreate_date(timestamp);
        userModel.setLast_login(timestamp);
        System.out.println(userModel);
        return userService.addUserOrUpdate(userModel);
    }
}
