package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.LikeModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostBorrowModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostRentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.PostService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post/rent")
    public ResponseEntity<String> postRent(@RequestBody PostRentModel postRentModel) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        postRentModel.setCreate_date(timestamp);
        System.out.println(postRentModel.getDetails());
        return postService.post(postRentModel);
    }

    @PostMapping("/post/borrow")
    public ResponseEntity<String> post(@RequestBody @NotNull PostBorrowModel postBorrowModel) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        postBorrowModel.setCreate_date(timestamp);
        return new ResponseEntity<>("Post borrow success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/post")
    public ResponseEntity<String> delete(@RequestParam int postId) {
        return postService.deletePost(postId);
    }

    @PutMapping("/update/post")
    public ResponseEntity<String> update(@RequestBody PostRentModel postRentModel) {
        return postService.updatePost(postRentModel, postRentModel.getPostId());
    }

    @GetMapping("/get/post")
    public List<Map<String, Object[]>> getPost(@RequestParam int limit) {
        return postService.getPost(limit);
    }

    @GetMapping("/filter/post")
    public List<PostRentModel> getPostWithFilter(@RequestParam String filter) {
        return postService.getPostWithFilter(filter);
    }

    @PostMapping("/like/post")
    public void likePost(@RequestBody LikeModel likeModel) {
        postService.likePost(likeModel);
    }

    @GetMapping("/like/post")
    public List<PostRentModel> getLikedPost(@RequestParam int userId) {
        return postService.likedPost(userId);
    }

    @GetMapping("/test")
    public List<PostRentModel> test(){
        return postService.test();
    }
}
