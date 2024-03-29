package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.LikeModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostBorrowModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostRentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.LikeRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.PostBorrowRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.PostRentRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.SuggestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRentRepository postRentRepository;
    private final PostBorrowRepository postBorrowRepository;
    private final LikeRepository likeRepository;
    private final SuggestionRepository suggestionRepository;

    public PostService(PostRentRepository postRentRepository, LikeRepository likeRepository, PostBorrowRepository postBorrowRepository, SuggestionRepository suggestionRepository) {
        this.postRentRepository = postRentRepository;
        this.likeRepository = likeRepository;
        this.postBorrowRepository = postBorrowRepository;
        this.suggestionRepository = suggestionRepository;
    }

    public PostRentModel post(PostRentModel postRentModel) {
        PostRentModel post = postRentRepository.save(postRentModel);
        return post;
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

    public List<PostRentModel> likedPost(int userId) {
        return postRentRepository.getAllLikedPostByUserId(userId);
    }

    /**
     * get all post from database
     * and send to frontend or mobile application
     * set like of post
     *
     * @return
     */
//    public ResponseEntity<Iterable<PostRentModel>> getPost() {
//        Iterable<PostRentModel> postRentModels = postRentRepository.findAll();
//        postRentModels.forEach(post -> {
//            int like = likeRepository.countByPostId(post.getPostId());
//            post.setLike(like);
//        });
//        return new ResponseEntity<>(postRentModels, HttpStatus.OK);
//    }
    public Iterable<PostRentModel> getPost(int limit) {
//        return postRentRepository.getPost(limit);
        return postRentRepository.findAll();
    }

    public List<PostRentModel> getPostWithFilter(String type) {
        return postRentRepository.getAllByItemTypeName(type);
    }

    public Iterable<PostRentModel> search(String query) {
        if (query.isBlank())
            return postRentRepository.findAll();
        return postRentRepository.searchAllByDetailsIsContainingOrDetailsLike(query, query);
    }

    public Iterable<PostBorrowModel> searchPostBorrow(String query) {
        if (query.isBlank())
            return postBorrowRepository.findAll();
        return postBorrowRepository.searchAllByDetailsIsContainingOrDetailsLike(query, query);
    }

    public Iterable<LikeModel> getLikePost(UserModel userModel) {
        return likeRepository.findAllByUserId(userModel.getUserId());
    }

    public List<PostRentModel> test() {
        return postRentRepository.test();
    }

    public Optional<PostRentModel> getPostByPostId(Integer postId) {
        return postRentRepository.findById(postId);
    }

    public void savePostBorrow(PostBorrowModel postBorrowModel) {
        postBorrowRepository.save(postBorrowModel);
    }

}
