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
    private Integer contractId;


    @Column(name = "total_rent")
    private Integer totalRent;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "price")
    private Integer price;
    @Column(name = "creator")
    private Integer creator;
    @Column(name = "fine_late")
    private Integer fineLate;

    @Column(name = "fine_broken")
    private Integer fineBroken;

    @Column(name = "edit_status")
    private Boolean editStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_ID")
    private RoomModel room;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_ID")
    private EquipmentModel equipmentModel;

    @Transient
    private Boolean editAble;

    public ContractModel() {
        super();
    }

    public ContractModel(Integer contractId, RoomModel roomId, Integer totalRent, Date startDate, Date endDate, Integer price, Integer creator, Integer fineLate, Integer fineBroken, Boolean editStatus, EquipmentModel equipmentModel, Boolean editAble) {
        this.contractId = contractId;
        this.room = roomId;
        this.totalRent = totalRent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.creator = creator;
        this.fineLate = fineLate;
        this.fineBroken = fineBroken;
        this.editStatus = editStatus;
        this.equipmentModel = equipmentModel;
        this.editAble = editAble;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public RoomModel getRoom() {
        return room;
    }

    public void setRoom(RoomModel room) {
        this.room = room;
    }

    public Integer getTotalRent() {
        return totalRent;
    }

    public void setTotalRent(Integer totalRent) {
        this.totalRent = totalRent;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getFineLate() {
        return fineLate;
    }

    public void setFineLate(Integer fineLate) {
        this.fineLate = fineLate;
    }

    public Integer getFineBroken() {
        return fineBroken;
    }

    public void setFineBroken(Integer fineBroken) {
        this.fineBroken = fineBroken;
    }

    public Boolean getEditStatus() {
        return editStatus;
    }

    public void setEditStatus(Boolean editStatus) {
        this.editStatus = editStatus;
    }

    public EquipmentModel getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(EquipmentModel equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public Boolean getEditAble() {
        return editAble;
    }

    public void setEditAble(Boolean editAble) {
        this.editAble = editAble;
    }

    @Override
    public String toString() {
        return "ContractModel{" +
                "contractId=" + contractId +
                ", roomId=" + room +
                ", totalRent=" + totalRent +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", creator=" + creator +
                ", fineLate=" + fineLate +
                ", fineBroken=" + fineBroken +
                ", editStatus=" + editStatus +
                ", equipmentModel=" + equipmentModel +
                ", editAble=" + editAble +
                '}';
    }
}