package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.EquipmentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostBorrowModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostRentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.PostBorrowRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.PostRentRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.EquipmentService;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.PostService;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;
    private final PostRentRepository postRentRepository;
    private final PostBorrowRepository postBorrowRepository;
    private final EquipmentService equipmentService;


    public PostController(PostService postService, PostRentRepository postRentRepository, PostBorrowRepository postBorrowRepository, EquipmentService equipmentService) {
        this.postService = postService;
        this.postRentRepository = postRentRepository;
        this.postBorrowRepository = postBorrowRepository;
        this.equipmentService = equipmentService;
    }

    @GetMapping("/post/rent/search")
    public Iterable<PostRentModel> searchPostRentModel(@RequestParam String query) {
        return postService.search(query);
    }

    @PostMapping("/post/rent")
    public PostRentModel postRent(@RequestBody PostRentModel postRentModel) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        postRentModel.setCreateDate(timestamp);
        Optional<EquipmentModel> equipmentModel = equipmentService.getEquipmentById(postRentModel.getEquipment().getItemId());
        if (equipmentModel.isPresent()) {
            postRentModel.setEquipment(equipmentModel.get());
        } else {
            System.out.println("Equipment doesn't exist");
        }
        return postService.post(postRentModel);
    }

    @GetMapping("/post/borrow/search")
    public Iterable<PostBorrowModel> searchPostBorrow(@RequestParam String query) {
        return postService.searchPostBorrow(query);
    }

    @PostMapping("/post/borrow")
    public ResponseEntity<String> post(@RequestBody @NotNull PostBorrowModel postBorrowModel) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        postBorrowModel.setCreate_date(timestamp);
        postService.savePostBorrow(postBorrowModel);
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
    public Iterable<PostRentModel> getPost(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, Principal principal) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        return postRentRepository.findAll(paging);
    }

    @GetMapping("/get/lendPost")
    public Iterable<PostBorrowModel> getLendPost(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        return postBorrowRepository.findAll(paging);
    }

    @GetMapping("/get/post/by")
    public Optional<PostRentModel> getPost(@RequestParam Integer postId) {
        return postService.getPostByPostId(postId);
    }

    @GetMapping("/filter/post")
    public List<PostRentModel> getPostWithFilter(@RequestParam String filter) {
        return postService.getPostWithFilter(filter);
    }


}
