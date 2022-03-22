package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;


import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ItemImageModel;
import org.springframework.data.repository.CrudRepository;

public interface ItemImageRepository extends CrudRepository<ItemImageModel,Integer> {

    Iterable<ItemImageModel> findAllByItemId(int itemId);
}
