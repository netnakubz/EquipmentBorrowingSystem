package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contract")
public class ContractModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;
    private Integer totalRent;
    private Date startDate;
    private Date endDate;
    private Integer price;
    private Integer creator;
    private Integer fineLate;
    private Integer fineBroken;
    private Integer quantity;
    private Boolean editStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomId")
    private RoomModel roomModel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipmentId")
    private EquipmentModel equipmentModel;

    @Transient
    private Boolean editAble;

    public ContractModel() {
        super();
        this.editStatus = false;
    }

    public ContractModel(Integer contractId, RoomModel roomId, Integer totalRent, Date startDate, Date endDate, Integer price, Integer creator, Integer fineLate, Integer fineBroken, Boolean editStatus, EquipmentModel equipmentModel, Boolean editAble,Integer quantity) {
        this.contractId = contractId;
        this.roomModel = roomId;
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
        this.quantity = quantity;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
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

    public RoomModel getRoomModel() {
        return roomModel;
    }

    public void setRoomModel(RoomModel roomModel) {
        this.roomModel = roomModel;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ContractModel{" +
                "contractId=" + contractId +
                ", totalRent=" + totalRent +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", creator=" + creator +
                ", fineLate=" + fineLate +
                ", fineBroken=" + fineBroken +
                ", quantity=" + quantity +
                ", editStatus=" + editStatus +
                ", roomModel=" + roomModel +
                ", equipmentModel=" + equipmentModel +
                ", editAble=" + editAble +
                '}';
    }
}
