package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;
import java.sql.Timestamp;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.DTO.ChatDto;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ChatMessageModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.RoomModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.ChatMessageService;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.RoomService;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.UserService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ChatRoomController {

    private RoomService roomService;
    private ChatMessageService chatMessageService;
    private UserService userService;

    public ChatRoomController(RoomService roomService, ChatMessageService chatMessageService, UserService userService) {
        this.roomService = roomService;
        this.chatMessageService = chatMessageService;
        this.userService = userService;
    }

    @GetMapping("/getMessage")
    @CrossOrigin(origins = "*")
    public Iterable<ChatMessageModel> getMessage(@RequestParam int roomId, Principal principal) {
        Optional<UserModel> userModel = userService.findByLocalId(principal.getName());
        if (userModel.isEmpty()) return null;
        return roomService.getMessage(roomId);
    }

    @GetMapping("/getRoom")
    public Iterable<RoomModel> getRoom(Principal principal) {
        return roomService.getRoom();
    }

    @GetMapping("/getRoom/by")
    public Optional<RoomModel> getRoom(@RequestParam int roomId) {
        return roomService.getRoom(roomId);
    }


    @GetMapping("/searchRoom")
    public RoomModel searchRoom(Principal principal, @RequestParam int userTwo) {
        Optional<UserModel> userOne = userService.findByLocalId(principal.getName());
        Optional<UserModel> two = userService.findUser(userTwo);
        if (userOne.isEmpty() || two.isEmpty()) return null;
        return roomService.findRoomByTwoUserId(userOne.get(), two.get());
    }

    @GetMapping("/getListChat")
    @CrossOrigin("*")
    public Iterable<RoomModel> roomList(Principal principal) {
        Optional<UserModel> userModel = userService.findByLocalId(principal.getName());
        if (userModel.isEmpty()) return null;
        return roomService.roomList(userModel.get());
    }

    @MessageMapping("/update-{userId}")
    @SendTo("/roomList/user-{userId}")
    public Iterable<RoomModel> roomModels(@DestinationVariable("userId") Integer userId) {
//        System.out.println("alsdkjfaslkdjfhaslkdfjhasdflkajshdfiouqwehroqwuierhqweoiruhqwelkrjqhslkajdsfasdfas room list got called");
        System.out.println("Watch user id is here " + userId);
        Optional<UserModel> userModel = userService.findUser(userId);
        if (userModel.isEmpty())
            return null;
        return roomService.roomList(userModel.get());
    }

    @MessageMapping("/notification-{userId}")
    @SendTo("/notification-{userId}")
    public ChatDto notification(ChatDto chatDto, @DestinationVariable("userId") Integer userId) {
        return chatDto;
    }

    @PostMapping("/sendMessage")
    @MessageMapping("/send-{roomId}")
    @SendTo("/chat/private-{roomId}")
    public ChatMessageModel sendMessage(ChatMessageModel chat, @DestinationVariable("roomId") Integer roomId) {
        System.out.println("chat got call");
        Optional<RoomModel> roomModel = roomService.getRoom(roomId);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        if (roomModel.isEmpty())
            return null;
        roomModel.get().setUpdateAt(timestamp);
        roomService.save(roomModel.get());
        chat.setRoomId(roomId);
        chat.setSenderId(chat.getUser().get_id());
        chatMessageService.saveMessage(chat);
        return chat;
    }

}
