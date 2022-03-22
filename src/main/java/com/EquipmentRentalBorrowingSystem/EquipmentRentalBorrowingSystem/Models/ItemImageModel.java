package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;

@Entity
@Table(name = "item_img")
public class ItemImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_ID")
    private int imgId;

    @Column(name = "item_ID")
    private int itemId;

    @Column(name = "location")
    private String location;

    public ItemImageModel() {
        super();
    }

    public ItemImageModel(int imgId, int itemId, String location) {
        super();
        this.imgId = imgId;
        this.itemId = itemId;
        this.location = location;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ItemImageModel{" +
                "imgId=" + imgId +
                ", itemId=" + itemId +
                ", location='" + location + '\'' +
                '}';
    }
}
