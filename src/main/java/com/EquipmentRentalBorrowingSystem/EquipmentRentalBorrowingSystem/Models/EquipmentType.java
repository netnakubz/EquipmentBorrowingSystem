package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "equipment_type")
public class EquipmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type_ID")
    private int typeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_ID", insertable = false, updatable = false)
    private TypeModel typeModel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_ID", updatable = false)
    @JsonIgnore
    private EquipmentModel equipmentModel;

    public EquipmentType() {
    }

    public EquipmentType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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
        return "EquipmentType{" + "id=" + id + ", typeId=" + typeId + ", typeModel=" + typeModel + ", equipmentModel=" + equipmentModel + '}';
    }
}
