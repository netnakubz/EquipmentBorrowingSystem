package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import java.security.Principal;
import java.util.Optional;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Config.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<UserModel> addUserOrUpdate(UserModel user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    public Optional<UserModel> findUser(int id) {
        return userRepository.findById(id);
    }

    public Optional<UserModel> findByLocalId(String principal) {
        return userRepository.findByLocalId(principal);
    }

    /**
     * Get user information
     *
     * @param id
     * @return
     */
//    public Optional<UserModel> getProfile(int id) {
//        Optional<UserModel> userInfo = userRepository.findById(id);
//        userInfo.ifPresent(userModel -> userModel.setLocalId("null"));
//        return userInfo;
//    }

    /**
     * Login function
     * check that user is existed yet
     * if not save user information into database
     * then return false
     * to user so user will add more information
     * <p>
     * if user is existed
     *
     * @param principal
     * @return json web token
     */
    public Boolean authenticated(Principal principal) {
        Optional<UserModel> userModel = userRepository.findByLocalId(principal.getName());
        //  Date date = new Date();
        //  Timestamp timestamp = new Timestamp(date.getTime());
        //  userRepository.updateLastLogin(principal.getName(), timestamp);
        return userModel.isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
