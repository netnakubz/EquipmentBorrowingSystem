package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Config.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.RoomModel;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RoomRepository extends CrudRepository<RoomModel, Integer> {
    List<RoomModel> findAllByUserOneAndUserTwoOrUserOneAndUserTwo(int one, int two, int three, int four);

    RoomModel findRoomModelByRoomId(int id);

    RoomModel findRoomModelByUserOneAndUserTwoOrUserOneAndUserTwo(int one, int two, int three, int four);

    @Query(value = "select r.*,u1.name as user_one_name,u2.name as user_two_name " +
            "from room r,user u1,user u2 " +
            "where (r.user_one = :userId or r.user_two = :userId) and " +
            "(u1.user_ID = r.user_one) and " +
            "(u2.user_ID = r.user_two)", nativeQuery = true)
    List<Map<String, Object[]>> getRoomList(@Param("userId") int userId);

    Iterable<RoomModel> getAllByUserOneOrUserTwoOrderByUpdateAtDesc(UserModel userOne, UserModel userTwo);

    //    @Query(value = "SELECT room.room_ID FROM room " +
//            "WHERE (user_one = :userOne and user_two = :userTwo) OR " +
//            "(user_one = :userTwo and user_two = :userOne)",
//            nativeQuery = true)
//    Optional<RoomModel> findRoomModelByUserOneAndUserTwoOrUserOneAndUserTwo(UserModel a, UserModel b, UserModel c, UserModel d);

    Optional<RoomModel> findRoomModelByUserOneAndUserTwoOrUserOneAndUserTwo(UserModel userOne, UserModel userTwo, UserModel userTwo1, UserModel userOne1);
}
