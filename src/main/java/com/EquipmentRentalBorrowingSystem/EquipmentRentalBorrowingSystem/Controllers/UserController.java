package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.UserRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.UserService;

import org.h2.engine.User;
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

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    public UserModel profile(Principal principal) {
        Optional<UserModel> userModel = userService.userInformation(principal);
        if(userModel.isEmpty())
            return null;
        return userModel.get();
    }

    @GetMapping("/isAuthed")
    public boolean isAuthed(Principal principal) {
        return userService.authenticated(principal);
    }


    @PostMapping("/updateUserInformation")
    public ResponseEntity<UserModel> updateUserInformation(@RequestBody UserModel userModel, Principal principal) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        userModel.setLocalId(principal.getName());
        userModel.setCreate_date(timestamp);
        userModel.setLast_login(timestamp);
        System.out.println(userModel);
        return userService.addUserOrUpdate(userModel);
    }
}
