package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import java.util.Optional;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public Optional<UserModel> findUser(int id) {
        return userRepository.findById(id);
    }

    public boolean addUser(UserModel personModel) {
        if (!Optional.of(personModel).isPresent())
            return false;
        userRepository.save(personModel);
        return true;
    }
}
