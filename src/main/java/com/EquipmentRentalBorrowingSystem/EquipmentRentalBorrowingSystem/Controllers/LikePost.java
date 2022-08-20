package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.LikeModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.LikeRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.PostService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LikePost {
    private PostService postService;
    private LikeRepository likeRepository;
    public LikePost(PostService postService,LikeRepository likeRepository) {
        this.postService = postService;
        this.likeRepository=likeRepository;
    }

     @GetMapping("/getLikePost")
     public Iterable<LikeModel> getLikePost(){
         // return postService.getLikePost(userId);
         return likeRepository.findAll();
     }
}
