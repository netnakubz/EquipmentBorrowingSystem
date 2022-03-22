package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.UserRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.SecurityService;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.UserService;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.internal.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final SecurityService securityService;
    public UserController(UserService userService, UserRepository userRepository,SecurityService securityService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

    @GetMapping("/profile")
    public Optional<UserModel> profile(@RequestParam int id) {
        return userService.getProfile(id);
    }

    @PostMapping("/updateUserInformation")
    public ResponseEntity<UserModel> updateUserInformation(@RequestBody UserModel userModel, Principal principal) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        UserModel result = userRepository.findByLocalId(principal.getName());
        if (result != null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        userModel.setLocalId(principal.getName());
        userModel.setCreate_date(timestamp);
        userModel.setLast_login(timestamp);
        return userService.addUserOrUpdate(userModel);
    }
}
