package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;


import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ItemImgModel;
import org.springframework.data.repository.CrudRepository;

public interface ItemImgRepository extends CrudRepository<ItemImgModel,Integer> {

    Iterable<ItemImgModel> findAllByItemId(int itemId);
}
