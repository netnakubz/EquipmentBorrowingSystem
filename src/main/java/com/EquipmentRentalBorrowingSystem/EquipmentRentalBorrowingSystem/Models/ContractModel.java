package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;
import java.sql.Timestamp;
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
    private Date start_date;

    @Column(name = "end_date")
    private Date end_date;

    @Column(name = "price")
    private int price;

    @Column(name = "fine_late")
    private int fineLate;

    @Column(name = "fine_broken")
    private int fineBroken;

    @Column(name="edit_status")
    private boolean editStatus;

    public ContractModel(){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        this.start_date = timestamp;
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

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
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
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", price=" + price +
                ", fineLate=" + fineLate +
                ", fineBroken=" + fineBroken +
                ", editStatus=" + editStatus +
                '}';
    }
}
