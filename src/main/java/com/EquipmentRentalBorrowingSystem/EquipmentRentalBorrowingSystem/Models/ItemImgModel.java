package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;

@Entity
@Table(name = "itemImg")
public class ItemImgModel {
    @Id
    @Column(name = "item_img")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imgId;

    @Column(name="item_id")
    private int itemId;

    @Column(name = "location")
    private String location;

    public ItemImgModel(){
        super();
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
        return "itemImgModel{" +
                "imgId=" + imgId +
                ", itemId=" + itemId +
                ", location='" + location + '\'' +
                '}';
    }
}
