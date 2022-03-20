package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.EquipmentModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

public interface EquipmentRepository extends CrudRepository<EquipmentModel,Integer> {
    @Query(value = "SELECT * FROM equipment WHERE user_ID = (SELECT user_ID FROM user WHERE local_ID = :localId)",nativeQuery = true)
    ResponseEntity<Iterable<EquipmentModel>> findAllByUserId(@Param("localId")String principal);
}
