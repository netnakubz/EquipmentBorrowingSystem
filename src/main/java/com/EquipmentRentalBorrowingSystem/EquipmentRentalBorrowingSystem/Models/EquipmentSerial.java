package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class EquipmentSerial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String serial;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "item_ID")
//    @JsonIgnore
//    private EquipmentModel item_ID;

    public EquipmentSerial() {
    }

    public EquipmentSerial(String serial) {
        this.serial = serial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

//    public EquipmentModel getItem_ID() {
//        return item_ID;
//    }
//
//    public void setItem_ID(EquipmentModel equipmentModel) {
//        this.item_ID = equipmentModel;
//    }

    @Override
    public String toString() {
        return "EquipmentSerial{" +
                "id=" + id +
                ", serial='" + serial + '\'' +
//                ", equipmentModel=" + item_ID +
                '}';
    }
}
