package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.LikeModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LikeRepository extends CrudRepository<LikeModel, Integer> {

    Optional<LikeModel> findByPostIdAndUserId(int postId, int userId);

    Iterable<LikeModel> getLikePostByUserId(int userId);

    @Query(value = "SELECT prm.*,ii.location FROM like_post lp,post_rent_model prm,equipment eq,item_img ii " +
            "where lp.post_id = prm.id " +
            "and lp.user_ID =10001 " +
            "and eq.id = ii.item_id "+
            "and prm.item_id = ii.item_id"
            ,nativeQuery = true)
    List<Map<String,Object[]>> getLikePost();
    int countByPostId(int postId);
}
