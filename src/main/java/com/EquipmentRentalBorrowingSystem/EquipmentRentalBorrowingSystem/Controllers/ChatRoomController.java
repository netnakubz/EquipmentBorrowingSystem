package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ChatMessageModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.RoomModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.ChatMessageService;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.RoomService;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ChatRoomController(RoomService roomService,
                              ChatMessageService chatMessageService,
                              UserService userService) {
        this.roomService = roomService;
        this.chatMessageService = chatMessageService;
        this.userService = userService;
    }

    @GetMapping("/getMessage")
    @CrossOrigin(origins = "*")
    public Iterable<ChatMessageModel> getMessage(@RequestParam int roomId, @RequestParam int userId) {
        System.out.println(roomId + " " + userId);
        return roomService.getMessage(roomId, userId);
    }

    @GetMapping("/getRoom")
    public Iterable<RoomModel> getRoom(Principal principal) {
        return roomService.getRoom();
    }

    @GetMapping("/getRoom/by")
    public Optional<RoomModel> getRoom(@RequestParam int roomId) {
        return roomService.getRoom(roomId);
    }

    // @PostMapping("/joinChatRoom")
    // public Iterable<ChatMessageModel> joinChatRoom(@RequestBody RoomModel
    // roomModel) {
    // return roomService.joinRoom(roomModel);
    // }
    @GetMapping("/searchRoom")
    public  Map<String,Object> searchRoom(@RequestParam int userOne, @RequestParam int userTwo) {
        return roomService.findRoomByTwoUserId(userOne, userTwo);
    }

    // @SendTo("/listChat-{userId}")
    @GetMapping("/getListChat")
    @CrossOrigin("*")
    public Iterable<RoomModel> roomList(Principal principal) {
        Optional<UserModel> userModel = userService.userInformation(principal);
        if(userModel.isEmpty())
            return null;
        return roomService.roomList(userModel.get());
    }

    @PostMapping("/sendMessage")
    @MessageMapping("/send-{roomId}")
    @SendTo("/chat/private-{roomId}")
    public ChatMessageModel sendMessage(ChatMessageModel chat, @DestinationVariable("roomId") int roomId) {
        chat.setRoomId(roomId);
        chat.setSenderId(chat.getUser().get_id());
        chatMessageService.saveMessage(chat);
        return chat;
    }
}
