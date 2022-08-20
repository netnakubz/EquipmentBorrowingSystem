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
    @Column(name = "message_ID")
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String _id;

    @Column(name = "room_ID")
    private int roomId;

    @Column(name = "user_ID")
    private int senderId;

    @Column(name = "message")
    private String text;

    @Column(name = "create_date")
    private Date createdAt;

    @Column(name="systemtext")
    private boolean system;

    @Column(name="function")
    private String function;

    @Transient
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                + senderId + ", text=" + text + ", user=" + user + "]";
    }



}
