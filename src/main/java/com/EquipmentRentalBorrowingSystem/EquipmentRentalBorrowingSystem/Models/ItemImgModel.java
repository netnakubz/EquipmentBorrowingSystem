package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "itemImg")
public class ItemImgModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imgId;

    private String location;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_ID", nullable = false)
    private EquipmentModel itemId;

    public EquipmentModel getEquipmentModel() {
        return itemId;
    }

    public void setEquipmentModel(EquipmentModel equipmentModel) {
        this.itemId = equipmentModel;
    }

    public ItemImgModel() {
        super();
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
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
                ", location='" + location + '\'' +
                '}';
    }
}
