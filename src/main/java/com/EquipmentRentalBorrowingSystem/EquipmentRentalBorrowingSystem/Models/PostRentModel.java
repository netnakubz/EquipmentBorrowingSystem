package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "post_rent_model")
public class PostRentModel {
    @Id
    @Column(name = "post_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column(name = "item_ID")
    private int itemId;

    @Column(name = "details")
    private String details;

    @Column(name = "post_owner_id")
    private int userId;

    @Column(name = "create_date")
    private Date create_date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_ID", insertable = false, updatable = false)
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

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public EquipmentModel getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentModel equipmentModels) {
        this.equipment = equipmentModels;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "PostRentModel{" +
                "postId=" + postId +
                ", itemId=" + itemId +
                ", details='" + details + '\'' +
                ", userId=" + userId +
                ", create_date=" + create_date +
                ", equipment=" + equipment +
                ", like=" + like +
                '}';
    }
}
