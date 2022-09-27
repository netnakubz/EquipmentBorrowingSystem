package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;


public class Temp {

    private int _id;

    public Temp() {
        super();
    }

    public Temp(int _id) {
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
