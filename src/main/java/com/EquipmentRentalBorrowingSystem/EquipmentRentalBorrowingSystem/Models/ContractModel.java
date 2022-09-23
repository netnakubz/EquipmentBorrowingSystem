package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "contract")
public class ContractModel {
    @Id
    @Column(name = "contract_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contractId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_ID_owner")
    private UserModel userIdOwner;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_ID_borrower")
    private UserModel userIdBorrower;

    @Column(name = "total_rent")
    private Integer totalRent;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "price")
    private Integer price;

    @Column(name = "fine_late")
    private Integer fineLate;

    @Column(name = "fine_broken")
    private Integer fineBroken;

    @Column(name = "edit_status", columnDefinition = "boolean default true")
    private Boolean editStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_ID")
    private EquipmentModel equipment;

    @Column(name = "creator")
    private Integer creator;

    public ContractModel() {
        super();
    }

    public ContractModel(UserModel userIdOwner, UserModel userIdBorrower, Integer totalRent, Integer price, Integer fineLate, Integer fineBroken, boolean editStatus, EquipmentModel equipmentModel,Integer userModels) {
        this.userIdOwner = userIdOwner;
        this.userIdBorrower = userIdBorrower;
        this.totalRent = totalRent;
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        this.startDate = timestamp;
        this.endDate = timestamp;
        this.price = price;
        this.fineLate = fineLate;
        this.fineBroken = fineBroken;
        this.editStatus = editStatus;
        this.equipment = equipmentModel;
        this.creator = userModels;
    }

    public int getTotalRent() {
        return totalRent;
    }

    public void setTotalRent(int totalRent) {
        this.totalRent = totalRent;
    }

    public Boolean getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(Boolean editStatus) {
        this.editStatus = editStatus;
    }

    public EquipmentModel getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentModel equipment) {
        this.equipment = equipment;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public UserModel getUserIdOwner() {
        return userIdOwner;
    }

    public void setUserIdOwner(UserModel userIdOwner) {
        this.userIdOwner = userIdOwner;
    }

    public UserModel getUserIdBorrower() {
        return userIdBorrower;
    }

    public void setUserIdBorrower(UserModel userIdBorrower) {
        this.userIdBorrower = userIdBorrower;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFineLate() {
        return fineLate;
    }

    public void setFineLate(int fineLate) {
        this.fineLate = fineLate;
    }

    public int getFineBroken() {
        return fineBroken;
    }

    public void setFineBroken(int fineBroken) {
        this.fineBroken = fineBroken;
    }

    public boolean isEditStatus() {
        return editStatus;
    }

    public void setEditStatus(boolean editStatus) {
        this.editStatus = editStatus;
    }


    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "ContractModel{" + "contractId=" + contractId + ", userIdOwner=" + userIdOwner + ", userIdBorrower=" + userIdBorrower + ", totalRent=" + totalRent + ", startDate=" + startDate + ", endDate=" + endDate + ", price=" + price + ", fineLate=" + fineLate + ", fineBroken=" + fineBroken + ", editStatus=" + editStatus + ", equipment=" + equipment + '}';
    }

}
