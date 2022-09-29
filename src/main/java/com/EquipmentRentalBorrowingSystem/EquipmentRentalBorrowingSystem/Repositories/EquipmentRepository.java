package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.EquipmentModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EquipmentRepository extends CrudRepository<EquipmentModel, Integer> {

    @Query(value = "SELECT * FROM equipment WHERE user_ID = :userId",
            nativeQuery = true)
    Iterable<EquipmentModel> findAllByUserId(@Param("userId") Integer userId);

    Optional<EquipmentModel> findByItemId(Integer itemId);

    @Query(value = "SELECT * FROM equipment WHERE user_ID = :userId", nativeQuery = true)
    Iterable<EquipmentModel> getEquipmentByUserId(@Param("userId") Integer userId);
}
