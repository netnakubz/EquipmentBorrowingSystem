 package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Config.Repositories;

 import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;

import org.springframework.data.jpa.repository.Query;
 import org.springframework.data.repository.CrudRepository;
 import org.springframework.data.repository.query.Param;
 import org.springframework.security.core.userdetails.UsernameNotFoundException;

 import java.sql.Timestamp;
import java.util.Optional;

 public interface UserRepository extends CrudRepository<UserModel, Integer> {
     UserModel findUserByEmail(String email) throws UsernameNotFoundException;

     Optional<UserModel> findByLocalId(String id) throws UsernameNotFoundException;

     @Query(value = "UPDATE user SET last_login = :timestamp WHERE local_id = :principal", nativeQuery = true)
     Boolean updateLastLogin(@Param("principal") String principal, @Param("timestamp") Timestamp timestamp) throws UsernameNotFoundException;
 }
