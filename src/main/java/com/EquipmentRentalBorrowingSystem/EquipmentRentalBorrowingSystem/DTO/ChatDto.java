package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.DTO;

public class ChatDto {
    private String sender;
    private String message;

    public ChatDto() {
    }

    public ChatDto(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Dto{" +
                "sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
