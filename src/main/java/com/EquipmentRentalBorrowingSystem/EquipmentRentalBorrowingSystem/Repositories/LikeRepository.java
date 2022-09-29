package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.LikeModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostRentModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends CrudRepository<LikeModel, Integer> {

    Optional<LikeModel> findByPostRentModelAndUserId(PostRentModel postRentModel, int userId);

    @Query(value = "DELETE FROM like_post WHERE like_ID = :likeId",
    nativeQuery = true)
    void manualDelete(@Param("likeId") int likeId);
    Iterable<LikeModel> getLikePostByUserId(int userId);



}
