package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "itemImg")
public class ItemImgModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_ID")
    private int img_ID;

    @Column(name = "location")
    private String location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_ID")
    @JsonIgnore
    private EquipmentModel itemId;

    public ItemImgModel(String location) {
        this.location = location;
    }

    public ItemImgModel() {

    }

    public int getImg_ID() {
        return img_ID;
    }

    public void setImg_ID(int img_ID) {
        this.img_ID = img_ID;
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
                "img_ID=" + img_ID +
                ", location='" + location + '\'' +
                ", itemId=" + itemId +
                '}';
    }
}
