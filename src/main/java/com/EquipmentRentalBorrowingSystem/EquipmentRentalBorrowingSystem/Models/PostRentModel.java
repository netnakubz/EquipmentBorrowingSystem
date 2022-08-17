package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "post_rent_model")
public class PostRentModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column(name = "details")
    private String details;

    @Column(name = "item_ID")
    private int itemId;

    @Column(name = "post_owner_id")
    private int userId;

    @Column(name = "create_date")
    private Date create_date;

    public void setDetails(String details) {
        this.details = details;
    }

    @Transient
    private int like;

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public PostRentModel() {
        super();
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetail(String details) {
        this.details = details;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    @Override
    public String toString() {
        return "PostRentModel{" + "postId=" + postId + ", details='" + details + '\'' + ", itemId=" + itemId + ", userId=" + userId + ", create_date=" + create_date + ", like=" + like + '}';
    }
}
