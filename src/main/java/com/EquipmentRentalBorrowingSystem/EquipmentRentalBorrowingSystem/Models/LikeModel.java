package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;

@Entity
@Table(name = "like_post")
public class LikeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeId;

    private int userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postId")
    private PostRentModel postRentModel;

    public LikeModel() {
        super();
    }

    public LikeModel(PostRentModel postRentModel, int userId) {
        this.postRentModel = postRentModel;
        this.userId = userId;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public PostRentModel getPostRentModel() {
        return postRentModel;
    }

    public void setPostRentModel(PostRentModel postRentModel) {
        this.postRentModel = postRentModel;
    }

    @Override
    public String toString() {
        return "LikeModel{" +
                "likeId=" + likeId +
                ", userId=" + userId +
                ", postRentModel=" + postRentModel +
                '}';
    }
}
