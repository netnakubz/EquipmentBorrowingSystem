package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "equipment")
public class EquipmentModel {
    @Id
    @Column(name = "item_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    @Column(name = "display")
    private boolean display;

    @Column(name = "total_rent")
    private int totalRent;

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

    @Column(name = "name")
    private String name;

    @Column(name = "user_ID")
    private int userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_ID", insertable = false, updatable = false)
    private UserModel user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_ID")
    private Set<ItemImgModel> itemImg;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_ID", insertable = false, updatable = false)
    private Set<SuggestionModel> suggestion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_ID")
    private Set<EquipmentType> equipmentTypes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="item_ID")
    private Set<EquipmentSerial> equipmentSerials;

    public EquipmentModel() {
    }

    public EquipmentModel(int quantity, int price, String name, int userId) {
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.userId = userId;
    }

    public int getItem_ID() {
        return itemId;
    }

    public void setItem_ID(int item_ID) {
        this.itemId = item_ID;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Set<ItemImgModel> getItemImg() {
        return itemImg;
    }

    public void setItemImg(Set<ItemImgModel> itemImgModels) {
        this.itemImg = itemImgModels;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Set<SuggestionModel> getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Set<SuggestionModel> suggestionModels) {
        this.suggestion = suggestionModels;
    }

    public Set<EquipmentSerial> getEquipmentSerials() {
        return equipmentSerials;
    }

    public void setEquipmentSerials(Set<EquipmentSerial> equipmentSerials) {
        this.equipmentSerials = equipmentSerials;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }


    public Set<EquipmentType> getEquipmentTypes() {
        return equipmentTypes;
    }

    public void setEquipmentTypes(Set<EquipmentType> equipmentTypes) {
        this.equipmentTypes = equipmentTypes;
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
                ", user=" + user +
                ", itemImg=" + itemImg +
                ", suggestion=" + suggestion +
                ", equipmentTypes=" + equipmentTypes +
                ", equipmentSerials=" + equipmentSerials +
                '}';
    }
}
