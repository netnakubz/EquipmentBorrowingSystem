package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Config.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ReceiptModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface ReceiptRepository extends CrudRepository<ReceiptModel,Integer> {

    Iterable<ReceiptModel> findAllByUserModel(UserModel userModel);
}
