package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;

@Entity
@Table(name = "type")
public class TypeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeId;

    private String name;

    public TypeModel(){
        super();
    }
    public TypeModel(Integer typeId){
        this.typeId = typeId;
    }
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TypeModel{" +
                "typeId=" + typeId +
                ", name='" + name + '\'' +
                '}';
    }
}
