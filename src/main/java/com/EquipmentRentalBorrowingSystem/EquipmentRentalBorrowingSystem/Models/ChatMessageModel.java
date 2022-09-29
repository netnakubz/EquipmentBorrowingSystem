package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;

@Entity
@Table(name = "message")
public class ChatMessageModel {
    @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String _id;

    private int roomId;

    private int senderId;

    private String text;

    private Date createdAt;

    private boolean system;

    private String function;

    @Transient
    private Temp temp;

    public ChatMessageModel() {
        super();
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Temp getUser() {
        return temp;
    }

    public void setUser(Temp temp) {
        this.temp = temp;
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    @Override
    public String toString() {
        return "ChatMessageModel [_id=" + _id + ", createdAt=" + createdAt + ", roomId=" + roomId + ", senderId="
                + senderId + ", text=" + text + ", user=" + temp + "]";
    }



}
