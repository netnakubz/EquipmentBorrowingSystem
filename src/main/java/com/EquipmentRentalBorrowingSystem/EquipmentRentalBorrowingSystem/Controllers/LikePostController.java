package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.LikeModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostRentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.LikeRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.LikeService;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.PostService;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LikePostController {
    private final PostService postService;
    private final LikeRepository likeRepository;
    private final LikeService likeService;

    public LikePostController(PostService postService, LikeRepository likeRepository, LikeService likeService) {
        this.postService = postService;
        this.likeRepository = likeRepository;
        this.likeService = likeService;
    }

    @GetMapping("/get/LikePost")
    public Iterable<LikeModel> getLikePost(Principal principal) {
        // return postService.getLikePost(userId);
        return likeRepository.findAll();
    }

    @GetMapping(value = "/isLiked")
    public boolean isLiked(@RequestParam int postId) {
        return likeService.isLiked(postId);
    }

    @PostMapping("/like/post")
    public void likePost(@RequestParam int postId) {
        LikeModel likeModel = new LikeModel(postId, 10001);
        likeService.likePost(likeModel);
    }

    @GetMapping("/like/post")
    public List<PostRentModel> getLikedPost(@RequestParam int userId) {
        return postService.likedPost(userId);
    }


}
