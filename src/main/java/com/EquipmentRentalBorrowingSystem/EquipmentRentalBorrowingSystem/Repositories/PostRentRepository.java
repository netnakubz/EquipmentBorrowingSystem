package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostRentModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRentRepository extends CrudRepository<PostRentModel, Integer> {
    /*
     * Get all post
     */
    List<PostRentModel> findAllByPostIdNotNull();


    @Query(value = "SELECT * FROM post_rent_model p" +
            " INNER JOIN like_post lp on lp.user_id = 1 and p.post_ID = lp.post_ID;",
            nativeQuery = true)
    List<PostRentModel> getAllLikedPostByUserId(@Param("userId") int userId);


    /*
     * Filter post by item type name
     */
    @Query(value = "SELECT * FROM post_rent_model pr " +
            "INNER JOIN equipment e ON (e.item_ID = pr.item_ID)" +
            "INNER JOIN type t ON (e.type_ID =  t.type_ID) AND (t.name = :type)",
            nativeQuery = true)
    List<PostRentModel> getAllByItemTypeName(@Param("type") String type);

}
