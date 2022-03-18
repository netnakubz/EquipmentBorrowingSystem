package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public Optional<UserModel> userInfo(@RequestParam int id) {
        return userService.findUser(id);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserModel> authenticate(Principal principal) {
        return userService.authenticated(principal);
    }

    @PostMapping("/updateUserInformation")
    public ResponseEntity<UserModel> updateUserInformation(@RequestBody UserModel userModel, Principal principal) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        userModel.setLocalId(principal.getName());
        userModel.setCreate_date(timestamp);
        userModel.setLast_login(timestamp);
        return userService.addUserOrUpdate(userModel);
    }
}
