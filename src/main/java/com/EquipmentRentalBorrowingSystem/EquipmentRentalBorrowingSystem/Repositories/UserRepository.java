package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.Timestamp;

public interface UserRepository extends CrudRepository<UserModel, Integer> {
    UserModel findUserByEmail(String email) throws UsernameNotFoundException;

    UserModel findByLocalId(String id) throws UsernameNotFoundException;

    @Query(value = "UPDATE user SET last_login = :timestamp WHERE local_id = :principal", nativeQuery = true)
    Boolean updateLastLogin(@Param("principal") String principal, @Param("timestamp") Timestamp timestamp) throws UsernameNotFoundException;
}
