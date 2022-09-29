package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.LikeModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostRentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public boolean isLiked(PostRentModel postRentModel) {
        Optional<LikeModel> likeModel = likeRepository.findByPostRentModelAndUserId(postRentModel, 10001);
        return likeModel.isPresent();
    }

    /**
     * @param likeModel like post when user click like or delete like when user
     *                  click like again
     */
    public void likePost(LikeModel likeModel) {
        Optional<LikeModel> res = likeRepository.findByPostRentModelAndUserId(likeModel.getPostRentModel(), likeModel.getUserId());
        if (res.isPresent()) {
            likeRepository.manualDelete(res.get().getLikeId());
        } else {
            likeRepository.save(likeModel);
        }
    }
}
