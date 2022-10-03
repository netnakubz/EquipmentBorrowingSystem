package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity()
@Table(name = "room")
public class RoomModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    private Date updateAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userOne")
    private UserModel userOne;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userTwo")
    private UserModel userTwo;

    public RoomModel() {
        super();
    }

    public RoomModel(UserModel userOne, UserModel userTwo) {
        this.userOne = userOne;
        this.userTwo = userTwo;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public UserModel getUserOne() {
        return userOne;
    }

    public void setUserOne(UserModel userOne) {
        this.userOne = userOne;
    }

    public UserModel getUserTwo() {
        return userTwo;
    }

    public void setUserTwo(UserModel userTwo) {
        this.userTwo = userTwo;
    }

    @Override
    public String toString() {
        return "RoomModel{" +
                "roomId=" + roomId +
                ", updateAt=" + updateAt +
                ", userOne=" + userOne +
                ", userTwo=" + userTwo +
                '}';
    }
}
