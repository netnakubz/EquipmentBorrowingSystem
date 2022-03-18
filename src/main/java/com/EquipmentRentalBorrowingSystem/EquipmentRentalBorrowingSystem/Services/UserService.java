package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import java.security.Principal;
import java.util.Optional;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public ResponseEntity<UserModel> addUserOrUpdate(UserModel user){
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    public Optional<UserModel> findUser(int id) {
        return userRepository.findById(id);
    }

    /**
     * Login function
     * check that user is existed yet
     * if not save user information into database
     * then return false
     * to user so user will add more information
     * <p>
     * if user is existed
     * generate jwt with user id and user email
     *
     * @param  principal
     * @return json web token
     */
    public boolean authenticated(Principal principal) {
        UserModel userModel = userRepository.findByLocalId(principal.getName());
        return userModel != null;
    }



}
