package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="message")
public class MessageModel {
    @Id
    @Column(name="message_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    @Column(name="user_ID")
    private int userId;

    @Column(name="room_ID")
    private int roomId;

    @Column(name="message")
    private String message;

    @Column(name="create_date")
    private Date create_date;

    public MessageModel(){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        this.create_date = timestamp;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    @Override
    public String toString() {
        return "message{" +
                "messageId=" + messageId +
                ", userId=" + userId +
                ", roomId=" + roomId +
                ", message='" + message + '\'' +
                ", create_date=" + create_date +
                '}';
    }
}
