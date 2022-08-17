package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.LikeRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.PostService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class LikePost {
    private PostService postService;
    private LikeRepository likeRepository;
    public LikePost(PostService postService,LikeRepository likeRepository) {
        this.postService = postService;
        this.likeRepository=likeRepository;
    }

     @GetMapping("/getLikePost")
     public List<Map<String,Object[]>> getLikePost(){
         // return postService.getLikePost(userId);
         return likeRepository.getLikePost();
     }
}
