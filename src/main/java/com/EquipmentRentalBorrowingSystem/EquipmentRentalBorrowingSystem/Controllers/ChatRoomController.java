package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ChatMessageModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.RoomModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.RoomService;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1")
public class ChatRoomController {

    private final RoomService roomService;

    public ChatRoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/getMessage")
    @CrossOrigin(origins = "http://192.168.10.35:3000")
    public List<ChatMessageModel> getMessage(@RequestParam int roomId){
        List<ChatMessageModel> chatMessageModel = roomService.getMessage(roomId);
        System.out.println(chatMessageModel);
        return chatMessageModel;
    }

    @PostMapping("/joinChatRoom")
    public List<ChatMessageModel> joinChatRoom(@RequestBody RoomModel roomModel) {
        return roomService.joinRoom(roomModel);
    }

    @PostMapping("/sendMessage")
    @MessageMapping("/send")
    @SendTo("/chat/private")
    public List<ChatMessageModel> sendMessage(@RequestBody ChatMessageModel chat){
        Date date = new Date();
        Timestamp timestamp2 = new Timestamp(date.getTime());
        chat.setSendTime(timestamp2);
        roomService.sendMessage(chat);
        List<ChatMessageModel> chatMessageModel = roomService.getMessage(chat.getRoomId());
        return chatMessageModel;
    }
}
