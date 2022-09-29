package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;

@Entity()
@Table(name = "room")
public class RoomModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

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

    public void setRoomId(int id) {
        this.roomId = id;
    }

    public int getRoomId() {
        return roomId;
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
                "id=" + roomId +
                ", userOne=" + userOne +
                ", userTwo=" + userTwo +
                '}';
    }
}
