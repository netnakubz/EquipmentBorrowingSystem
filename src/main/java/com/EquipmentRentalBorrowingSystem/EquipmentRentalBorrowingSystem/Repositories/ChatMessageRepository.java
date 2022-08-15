package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ChatMessageModel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends CrudRepository<ChatMessageModel, Integer> {
    
    @Query(value = "SELECT * FROM message WHERE room_ID=:roomId ORDER BY create_date ASC ",nativeQuery = true)
    Iterable<ChatMessageModel> findChatMessageModelsByRoomId(@Param("roomId") int roomId);

}
