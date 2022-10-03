package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.LikeModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostRentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Config.Repositories.LikeRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.LikeService;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.PostService;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class LikePostController {
    private final PostService postService;
    private final LikeRepository likeRepository;
    private final LikeService likeService;

    private final UserService userService;

    public LikePostController(PostService postService, LikeRepository likeRepository, LikeService likeService, UserService userService) {
        this.postService = postService;
        this.likeRepository = likeRepository;
        this.likeService = likeService;
        this.userService = userService;
    }

    @GetMapping("/get/LikePost")
    public Iterable<LikeModel> getLikePost(Principal principal) {
        Optional<UserModel> userModel = userService.findByLocalId(principal.getName());
        if (userModel.isEmpty()) return null;
        return postService.getLikePost(userModel.get());
    }

    @GetMapping(value = "/isLiked")
    public boolean isLiked(@RequestParam int postId, Principal principal) {
        Optional<PostRentModel> postRentModel = postService.getPostByPostId(postId);
        Optional<UserModel> userModel = userService.findByLocalId(principal.getName());
        if (postRentModel.isEmpty()) return false;
        return likeService.isLiked(postRentModel.get(), userModel.get());
    }

    @PostMapping("/like/post")
    public void likePost(@RequestParam Integer postId, Principal principal) {
        Optional<PostRentModel> postRentModel = postService.getPostByPostId(postId);
        if (postRentModel.isPresent()) {
            Optional<UserModel> userModel = userService.findByLocalId(principal.getName());
            LikeModel likeModel = new LikeModel();
            likeModel.setPostRentModel(postRentModel.get());
            likeModel.setUserId(userModel.get().getUserId());
            likeService.likePost(likeModel);
        } else {
            System.out.println("Empty");
        }

    }

    @GetMapping("/like/post")
    public List<PostRentModel> getLikedPost(@RequestParam int userId) {
        return postService.likedPost(userId);
    }


}
