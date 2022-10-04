package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import java.util.Optional;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ChatMessageModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.RoomModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.User;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.ChatMessageRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.RoomRepository;

import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final ChatMessageRepository chatMessageRepository;

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
//     public Iterable<ChatMessageModel> joinRoom(RoomModel roomModel) {
//     Optional<RoomModel> RoomModel =
//     roomRepository.findRoomModelByUserOneAndUserTwoOrUserOneAndUserTwo(roomModel.getUserOne(),
//     roomModel.getUserTwo(), roomModel.getUserTwo(), roomModel.getUserOne());
//     if (RoomModel == null)
//     RoomModel = addRoom(roomModel);
//     return getMessage(RoomModel.get().getRoomId());
//     }

    /**
     * @param roomId
     * @return
     */
    public Iterable<ChatMessageModel> getMessage(int roomId) {
        Iterable<ChatMessageModel> chatMessageModel = chatMessageRepository.findChatMessageModelsByRoomId(roomId);
        // for (int i = 0; i < chatMessageModel.size(); i++) {
        // String text = chatMessageModel.get(i).getText();
        // String decrypt = AES.Decrypt(text);
        // chatMessageModel.get(i).setText(text);
        // }
        for (ChatMessageModel chat : chatMessageModel) {
            chat.setUser(new User(chat.getSenderId()));
        }
        return chatMessageModel;
    }

    /**
     * @param chatMessageModel
     */
    public void sendMessage(ChatMessageModel chatMessageModel) {
        RoomModel roomModel = roomRepository.findRoomModelByRoomId(chatMessageModel.getRoomId());
        if (roomModel.getUserOne().getUserId() == chatMessageModel.getSenderId()
                || roomModel.getUserTwo().getUserId() == chatMessageModel.getSenderId()) {
            String input = chatMessageModel.getText();
            chatMessageModel.setText(AES.Encrypt(input));
            chatMessageRepository.save(chatMessageModel);
        }
    }

    public Iterable<RoomModel> roomList(UserModel userModel) {
        return roomRepository.getAllByUserOneOrUserTwoOrderByUpdateAtDesc(userModel, userModel);
    }

    private RoomModel createNewRoom(UserModel userOne, UserModel userTwo) {
        RoomModel room = new RoomModel(userOne, userTwo);
        return roomRepository.save(room);
    }

    public void save(RoomModel roomModel) {
        roomRepository.save(roomModel);
    }

    public Iterable<RoomModel> getRoom() {
        return roomRepository.findAll();
    }

    public Optional<RoomModel> getRoom(int roomId) {
        return roomRepository.findById(roomId);
    }

    public RoomModel findRoomByTwoUserId(UserModel userOne, UserModel userTwo) {
        Optional<RoomModel> obj = roomRepository.findRoomModelByUserOneAndUserTwoOrUserOneAndUserTwo(userOne, userTwo, userTwo, userOne);
        if (obj.isEmpty()) {
            return createNewRoom(userOne, userTwo);
        }
        return obj.get();
    }
}
