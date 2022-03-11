package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.EquipmentModel;
import org.springframework.data.repository.CrudRepository;

public interface EquipmentRepository extends CrudRepository<EquipmentModel,Integer> {
}
