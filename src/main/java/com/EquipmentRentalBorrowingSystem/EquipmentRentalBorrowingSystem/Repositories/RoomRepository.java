package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.RoomModel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface RoomRepository extends CrudRepository<RoomModel, Integer> {
    List<RoomModel> findAllByUserOneAndUserTwoOrUserOneAndUserTwo(int one, int two, int three, int four);

    RoomModel findRoomModelById(int id);

    RoomModel findRoomModelByUserOneAndUserTwoOrUserOneAndUserTwo(int one, int two, int three, int four);

    @Query(value = "select r.*,u1.name as user_one_name,u2.name as user_two_name " +
            "from room r,user u1,user u2 " +
            "where (r.user_one = :userId or r.user_two = :userId) and " +
            "(u1.user_ID = r.user_one) and " +
            "(u2.user_ID = r.user_two)", nativeQuery = true)
    List<Map<String,Object[]>> getRoomList(@Param("userId") int userId);

    @Query(value = "SELECT room.room_ID FROM room " +
            "WHERE (user_one = :userOne and user_two = :userTwo) OR " +
            "(user_one = :userTwo and user_two = :userOne)",
            nativeQuery = true)
    Map<String,Object> findRoomByTwoUserId(@Param("userOne") int userOne, @Param("userTwo") int userTwo);
}
