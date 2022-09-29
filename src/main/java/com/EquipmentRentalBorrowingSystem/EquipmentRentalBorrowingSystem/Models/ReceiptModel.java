package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "receipt")
public class ReceiptModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int receiptId;

    private int userId;

    private int itemId;

    private Date createDate;

    private int price;

    public ReceiptModel() {
        super();

    }

    public ReceiptModel(int userId,int itemId,int price){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        this.createDate = timestamp;
        this.price = price;
        this.userId = userId;
        this.itemId = itemId;
    }



    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date create_date) {
        this.createDate = create_date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ReceiptModel{" +
                "receiptId=" + receiptId +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", create_date=" + createDate +
                ", price=" + price +
                '}';
    }
}
