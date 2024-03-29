package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="message")
public class MessageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    private int userId;

    private int roomId;

    private String message;

    private Date create_date;



    public MessageModel(){
        super();

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
