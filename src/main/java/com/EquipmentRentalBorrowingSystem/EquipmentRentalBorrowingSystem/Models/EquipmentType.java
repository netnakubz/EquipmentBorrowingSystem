package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "equipment_type")
public class EquipmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "typeId", insertable = false, updatable = false)
    private TypeModel typeModel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipmentModel", updatable = false)
    @JsonIgnore
    private EquipmentModel equipmentModel;

    public EquipmentType() {
    }

    public EquipmentType(TypeModel typeModel) {
        this.typeModel = typeModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeModel getTypeModel() {
        return typeModel;
    }

    public void setTypeModel(TypeModel typeModel) {
        this.typeModel = typeModel;
    }

    public EquipmentModel getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(EquipmentModel equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    @Override
    public String toString() {
        return "EquipmentType{" + "id=" + id + ", typeModel=" + typeModel + ", equipmentModel=" + equipmentModel + '}';
    }
}
