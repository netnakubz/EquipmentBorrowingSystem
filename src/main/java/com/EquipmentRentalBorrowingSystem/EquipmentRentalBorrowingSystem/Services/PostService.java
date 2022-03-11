package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.PostRentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.PostRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRentRepository postRentRepository;

    @Autowired
    public PostService(PostRentRepository postRentRepository) {
        this.postRentRepository = postRentRepository;
    }

    public ResponseEntity<String> post(PostRentModel postRentModel) {
        PostRentModel r = postRentRepository.save(postRentModel);
        return new ResponseEntity<>("Post success", HttpStatus.OK);
    }

    public ResponseEntity<String> deletePost(int postId) {
        Optional<PostRentModel> postRentModel = postRentRepository.findById(postId);
        if (postRentModel.isPresent()) {
            postRentRepository.deleteById(postId);
            return new ResponseEntity<>("Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updatePost(PostRentModel postRentModel,int postId) {
        Optional<PostRentModel> prm = postRentRepository.findById(postId);
        if(prm.isPresent()){
            postRentRepository.save(postRentModel);
            return new ResponseEntity<>("Update successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }
    public List<PostRentModel> getPost(){
        return postRentRepository.findAllByPostIdNotNull();
    }

    public List<PostRentModel> getPostWithFilter(String type){
        return postRentRepository.getAllByItemTypeName(type);
    }
}
