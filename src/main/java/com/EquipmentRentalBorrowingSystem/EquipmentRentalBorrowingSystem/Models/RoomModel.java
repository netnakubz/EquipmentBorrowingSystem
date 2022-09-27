package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;

@Entity()
@Table(name = "room")
public class RoomModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_one")
    private UserModel userOne;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_two")
    private UserModel userTwo;

    public RoomModel() {
        super();

    }

    public RoomModel(UserModel userOne, UserModel userTwo) {
        this.userOne = userOne;
        this.userTwo = userTwo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
                "id=" + id +
                ", userOne=" + userOne +
                ", userTwo=" + userTwo +
                '}';
    }
}
