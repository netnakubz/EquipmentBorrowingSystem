package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.RoomModel;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<RoomModel, Integer> {
    List<RoomModel> findAllByUserOneAndUserTwoOrUserOneAndUserTwo(int one, int two, int three, int four);
    RoomModel findRoomModelById(int id);
    RoomModel findRoomModelByUserOneAndUserTwoOrUserOneAndUserTwo(int one, int two, int three, int four);
}
