package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "receipt")
public class ReceiptModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receipt_ID")
    private int receiptId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "item_ID")
    private int itemId;

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "price")
    private int price;

    public ReceiptModel() {
        super();

    }

    public ReceiptModel(int userId,int itemId,int price){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        this.create_date = timestamp;
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

    @Override
    public String toString() {
        return "ReceiptModel{" +
                "receiptId=" + receiptId +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", create_date=" + create_date +
                ", price=" + price +
                '}';
    }
}
