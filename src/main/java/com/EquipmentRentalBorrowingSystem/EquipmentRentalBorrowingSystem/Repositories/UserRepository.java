package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserRepository extends CrudRepository<UserModel, Integer> {
    UserModel findUserByEmail(String email) throws UsernameNotFoundException;

    UserModel findByLocalId(String id) throws UsernameNotFoundException;

}
