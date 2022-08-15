package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import java.util.List;
import java.util.Map;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ChatMessageModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.RoomModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.User;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.ChatMessageRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, ChatMessageRepository chatMessageRepository) {
        this.roomRepository = roomRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    /**
     * Create room
     * store two users into the room
     *
     * @param roomModel
     * @return
     */
    public RoomModel addRoom(RoomModel roomModel) {
        RoomModel chatRoom = new RoomModel(roomModel.getUserOne(), roomModel.getUserTwo());
        return roomRepository.save(chatRoom);
    }

    /**
     *
     * @param roomModel
     * @return
     */
    // public Iterable<ChatMessageModel> joinRoom(RoomModel roomModel) {
    // RoomModel RoomModel =
    // roomRepository.findRoomModelByUserOneAndUserTwoOrUserOneAndUserTwo(roomModel.getUserOne(),
    // roomModel.getUserTwo(), roomModel.getUserTwo(), roomModel.getUserOne());
    // if (RoomModel == null)
    // RoomModel = addRoom(roomModel);
    // return getMessage(RoomModel.getId());
    // }

    /**
     * return messages to user
     *
     * @param id
     * @return
     */
    public Iterable<ChatMessageModel> getMessage(int id, int userId) {
        Iterable<ChatMessageModel> chatMessageModel = chatMessageRepository.findChatMessageModelsByRoomId(id);
        // for (int i = 0; i < chatMessageModel.size(); i++) {
        // String text = chatMessageModel.get(i).getText();
        // String decrypt = AES.Decrypt(text);
        // chatMessageModel.get(i).setText(text);
        // }
        for (ChatMessageModel chat : chatMessageModel) {
            int _id = chat.getSenderId() == userId ? 1 : 2;
            chat.setUser(new User(chat.getSenderId()));
        }
        return chatMessageModel;
    }

    /**
     * @param chatMessageModel
     */
    public void sendMessage(ChatMessageModel chatMessageModel) {
        RoomModel roomModel = roomRepository.findRoomModelById(chatMessageModel.getRoomId());
        if (roomModel.getUserOne() == chatMessageModel.getSenderId() || roomModel.getUserTwo() == chatMessageModel.getSenderId()) {
            String input = chatMessageModel.getText();
            chatMessageModel.setText(AES.Encrypt(input));
            chatMessageRepository.save(chatMessageModel);
        }
    }

    public List<Map<String, Object[]>> roomList(int userId) {
        return roomRepository.getRoomList(userId);
    }

    private RoomModel createNewRoom(int userOne, int userTwo) {
        RoomModel room = new RoomModel(userOne, userTwo);
        return roomRepository.save(room);
    }

    public Map<String, Object> findRoomByTwoUserId(int userOne, int userTwo) {
        Map<String, Object> obj = roomRepository.findRoomByTwoUserId(userOne, userTwo);
        if (obj.isEmpty()) {
            return (Map<String, Object>) createNewRoom(userOne, userTwo);
        }
        return roomRepository.findRoomByTwoUserId(userOne, userTwo);
    }
}
