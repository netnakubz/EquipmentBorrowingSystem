package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostRentModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface PostRentRepository extends PagingAndSortingRepository<PostRentModel, Integer>, JpaSpecificationExecutor<PostRentModel> {
    /*
     * Get all post
     */
    List<PostRentModel> findAllByPostIdNotNull();

    @Query(value = "SELECT * FROM post_rent_model p" +
            " INNER JOIN like_post lp on lp.user_id = 1 and p.post_ID = lp.post_ID;",
            nativeQuery = true)
    List<PostRentModel> getAllLikedPostByUserId(@Param("userId") int userId);

    @Query(value = "select * from like_post lp, post_rent_model prm where prm.post_owner_id = lp.user_ID and prm.id = lp.post_ID;",
            nativeQuery = true)
    List<PostRentModel> test();


    @Query(value = "SELECT * FROM post_rent_model p INNER JOIN equipment on equipment.name like %:query% OR p.details like %:query%",
            nativeQuery = true)
    List<PostRentModel> search(@Param("query") String query);

    /*
     * Filter post by item type name
     */
    @Query(value = "SELECT * FROM post_rent_model pr " +
            "INNER JOIN equipment e ON (e.item_ID = pr.item_ID)" +
            "INNER JOIN type t ON (e.type_ID =  t.type_ID) AND (t.name = :type)",
            nativeQuery = true)
    List<PostRentModel> getAllByItemTypeName(@Param("type") String type);

    @Query(value = "SELECT * FROM post_rent_model",
            nativeQuery = true)
    List<PostRentModel> getAll(@Param("limit") int limit);
}
