package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "equipment")
public class EquipmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    private boolean display;

    private int totalRent;

    private Date create_date;

    private int quantity;

    private int price;

    private String name;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private UserModel userId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemId")
    private Set<ItemImgModel> itemImg;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemId")
    private Set<SuggestionModel> suggestion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemId")
    private Set<EquipmentType> equipmentTypes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemId")
    private Set<EquipmentSerial> equipmentSerials;


    public EquipmentModel() {
    }

    public EquipmentModel(int quantity, int price, String name, UserModel userModel) {
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.userId = userModel;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public int getTotalRent() {
        return totalRent;
    }

    public void setTotalRent(int totalRent) {
        this.totalRent = totalRent;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    public UserModel getUser() {
        return userId;
    }

    public void setUser(UserModel user) {
        this.userId = user;
    }

    public Set<ItemImgModel> getItemImg() {
        return itemImg;
    }

    public void setItemImg(Set<ItemImgModel> itemImg) {
        this.itemImg = itemImg;
    }

    public Set<SuggestionModel> getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Set<SuggestionModel> suggestion) {
        this.suggestion = suggestion;
    }

    public Set<EquipmentType> getEquipmentTypes() {
        return equipmentTypes;
    }

    public void setEquipmentTypes(Set<EquipmentType> equipmentTypes) {
        this.equipmentTypes = equipmentTypes;
    }

    public Set<EquipmentSerial> getEquipmentSerials() {
        return equipmentSerials;
    }

    public void setEquipmentSerials(Set<EquipmentSerial> equipmentSerials) {
        this.equipmentSerials = equipmentSerials;
    }

    @Override
    public String toString() {
        return "EquipmentModel{" +
                "itemId=" + itemId +
                ", display=" + display +
                ", totalRent=" + totalRent +
                ", create_date=" + create_date +
                ", quantity=" + quantity +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", itemImg=" + itemImg +
                ", suggestion=" + suggestion +
                ", equipmentTypes=" + equipmentTypes +
                ", equipmentSerials=" + equipmentSerials +
                '}';
    }
}
