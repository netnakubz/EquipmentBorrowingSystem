package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.LikeModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostRentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.LikeRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.PostRentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRentRepository postRentRepository;
    private final LikeRepository likeRepository;

    public PostService(PostRentRepository postRentRepository, LikeRepository likeRepository) {
        this.postRentRepository = postRentRepository;
        this.likeRepository = likeRepository;
    }
    
    public ResponseEntity<String> post(PostRentModel postRentModel) {
        postRentRepository.save(postRentModel);
        return new ResponseEntity<>("Post success", HttpStatus.OK);
    }

    public ResponseEntity<String> deletePost(int postId) {
        Optional<PostRentModel> postRentModel = postRentRepository.findById(postId);
        if (postRentModel.isPresent()) {
            return new ResponseEntity<>("Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updatePost(PostRentModel postRentModel, int postId) {
        Optional<PostRentModel> prm = postRentRepository.findById(postId);
        if (prm.isPresent()) {
            postRentRepository.save(postRentModel);
            return new ResponseEntity<>("Update successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }
    public List<PostRentModel> likedPost(int userId){
        return postRentRepository.getAllLikedPostByUserId(userId);
    }
    /**
     * get all post from database
     * and send to frontend or mobile application
     * set like of post
     * @return
     */
    public ResponseEntity<Iterable<PostRentModel>> getPost(){
        Iterable<PostRentModel> postRentModels = postRentRepository.findAll();
        postRentModels.forEach(post -> {
            int like = likeRepository.countByPostId(post.getPostId());
            post.setLike(like);
        });
        return new ResponseEntity<>(postRentModels,HttpStatus.OK);
    }

    public List<PostRentModel> getPostWithFilter(String type) {
        return postRentRepository.getAllByItemTypeName(type);
    }
    /**
     * 
     * @param likeModel like post when user click like or delete like when user click like again
     */
    public void likePost(LikeModel likeModel) {
        Optional<LikeModel> isLiked = likeRepository.findByPostIdAndUserId(likeModel.getPostId(),
                likeModel.getUserId());
        isLiked.ifPresentOrElse(
                (result) -> {
                    likeRepository.deleteById(result.getLikeId());
                },
                () -> {
                    likeRepository.save(likeModel);
                });
    }

}
