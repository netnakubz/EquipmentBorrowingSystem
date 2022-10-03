package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;


public class User {

    private int _id;

    public User() {
        super();
    }

    public User(int _id) {
        this._id = _id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "User [_id=" + _id + "]";
    }

}
