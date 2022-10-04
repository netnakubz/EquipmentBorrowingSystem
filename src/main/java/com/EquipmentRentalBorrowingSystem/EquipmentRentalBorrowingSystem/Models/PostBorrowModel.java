package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "post_borrow")
public class PostBorrowModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String details;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel userModel;

    private Date create_date;

    private Integer price;

    private Integer period;


    public PostBorrowModel() {
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

    public void setDetails(String detail) {
        this.details = detail;
    }

    public UserModel getUserId() {
        return userModel;
    }

    public void setUserId(UserModel userModel) {
        this.userModel = userModel;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }


    @Override
    public String toString() {
        return "PostBorrowModel{" +
                "postId=" + postId +
                ", details='" + details + '\'' +
                ", userModel=" + userModel +
                ", create_date=" + create_date +
                ", price=" + price +
                ", period=" + period +
                '}';
    }
}
