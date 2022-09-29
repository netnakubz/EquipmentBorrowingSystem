package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "itemImg")
public class ItemImgModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imgId;

    private String location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemId")
    @JsonIgnore
    private EquipmentModel itemId;

    public ItemImgModel(String location) {
        this.location = location;
    }

    public ItemImgModel() {

    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int img_ID) {
        this.imgId = img_ID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public EquipmentModel getItemId() {
        return itemId;
    }

    public void setItemId(EquipmentModel itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "ItemImgModel{" +
                "img_ID=" + imgId +
                ", location='" + location + '\'' +
                ", itemId=" + itemId +
                '}';
    }
}
