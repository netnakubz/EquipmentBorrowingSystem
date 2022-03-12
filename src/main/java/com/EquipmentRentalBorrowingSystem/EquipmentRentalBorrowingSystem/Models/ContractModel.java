package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contract")
public class ContractModel {
    @Id
    @Column(name = "contract_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contractId;

    @Column(name = "user_ID_owner")
    private int userIdOwner;

    @Column(name = "user_ID_borrower")
    private int userIdBorrower;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "price")
    private int price;

    @Column(name = "fine_late")
    private int fineLate;

    @Column(name = "fine_broken")
    private int fineBroken;

    @Column(name = "edit_status")
    private boolean editStatus;

    public ContractModel() {
        super();
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getUserIdOwner() {
        return userIdOwner;
    }

    public void setUserIdOwner(int userIdOwner) {
        this.userIdOwner = userIdOwner;
    }

    public int getUserIdBorrower() {
        return userIdBorrower;
    }

    public void setUserIdBorrower(int userIdBorrower) {
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

    @Override
    public String toString() {
        return "ContractModel{" +
                "contractId=" + contractId +
                ", userIdOwner=" + userIdOwner +
                ", userIdBorrower=" + userIdBorrower +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", fineLate=" + fineLate +
                ", fineBroken=" + fineBroken +
                ", editStatus=" + editStatus +
                '}';
    }
}
