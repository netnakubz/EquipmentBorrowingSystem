package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.DTO;

public class UpdateEquipmentDto {
    private Integer itemId;
    private String name;

    public UpdateEquipmentDto() {
    }

    public UpdateEquipmentDto(Integer itemId, String name) {
        this.itemId = itemId;
        this.name = name;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
