package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "post_rent_model")
public class PostBorrowModel {
    @Id
    @Column(name = "post_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column(name = "detail")
    private String detail;

    @Column(name = "item_ID")
    private int itemId;

    @Column(name = "user_ID")
    private int userId;

    @Column(name = "create_date")
    private Date create_date;
    public PostBorrowModel(){
        super();

    }
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
        return "PostRentModel{" +
                "postId=" + postId +
                ", detail='" + detail + '\'' +
                ", itemId=" + itemId +
                ", userId=" + userId +
                ", create_date=" + create_date +
                '}';
    }
}
