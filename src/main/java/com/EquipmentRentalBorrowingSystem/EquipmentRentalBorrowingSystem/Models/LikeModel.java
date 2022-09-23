package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;

@Entity
@Table(name = "like_post")
public class LikeModel {
    @Id
    @Column(name = "like_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int likeId;

    @Column(name = "post_ID")
    private int postId;

    @Column(name = "user_ID")
    private int userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_ID", insertable = false, updatable = false)
    private PostRentModel postRentModel;

    public LikeModel() {
        super();
    }

    public LikeModel(int postId, int userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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
                ", postId=" + postId +
                ", userId=" + userId +
                ", postRentModel=" + postRentModel +
                '}';
    }
}
