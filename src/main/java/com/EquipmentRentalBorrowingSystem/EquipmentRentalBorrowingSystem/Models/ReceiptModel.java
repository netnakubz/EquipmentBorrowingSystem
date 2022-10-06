package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "receipt")
public class ReceiptModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int receiptId;

    private Date createDate;
    private Boolean status;
    private Integer fineLate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contractId")
    private ContractModel contractModel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private UserModel userModel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="borrowerId")
    private UserModel borrower;

    public ReceiptModel(ContractModel contractModel) {
        this.contractModel = contractModel;
        Date date = new Date();
        this.createDate = new Timestamp(date.getTime());
    }

    public ReceiptModel() {
        this.status = false;

    }

    public Integer getFineLate() {
        return fineLate;
    }

    public void setFineLate(Integer fineLate) {
        this.fineLate = fineLate;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ContractModel getContractModel() {
        return contractModel;
    }

    public void setContractModel(ContractModel contractModel) {
        this.contractModel = contractModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public UserModel getBorrower() {
        return borrower;
    }

    public void setBorrower(UserModel borrower) {
        this.borrower = borrower;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReceiptModel{" +
                "receiptId=" + receiptId +
                ", createDate=" + createDate +
                ", contractModel=" + contractModel +
                ", userModel=" + userModel +
                '}';
    }
}
