package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostRentModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRentRepository extends CrudRepository<PostRentModel,Integer> {
    /*
    * Get all post
    * */
    List<PostRentModel> findAllByPostIdNotNull();
    /*
    * Filter post by item type name
    * */
    @Query(
            value = "SELECT * FROM post_rent_model pr " +
                    "INNER JOIN equipment e ON (e.item_ID = pr.item_ID)" +
                    "INNER JOIN type t ON (e.type_ID =  t.type_ID) AND (t.name = :type)",
            nativeQuery = true)
    List<PostRentModel> getAllByItemTypeName(@Param("type")String type);


}
