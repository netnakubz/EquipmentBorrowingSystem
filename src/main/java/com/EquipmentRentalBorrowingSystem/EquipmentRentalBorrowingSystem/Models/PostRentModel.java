package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post_rent_model")
public class PostRentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    private String details;

    private int userId;

    private Date createDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment")
    private EquipmentModel equipment;


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


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date create_date) {
        this.createDate = create_date;
    }

    public EquipmentModel getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentModel equipmentModels) {
        this.equipment = equipmentModels;
    }



    @Override
    public String toString() {
        return "PostRentModel{" +
                "postId=" + postId +
                ", details='" + details + '\'' +
                ", userId=" + userId +
                ", create_date=" + createDate +
                ", equipment=" + equipment +
                ", like=" + like +
                '}';
    }
}
