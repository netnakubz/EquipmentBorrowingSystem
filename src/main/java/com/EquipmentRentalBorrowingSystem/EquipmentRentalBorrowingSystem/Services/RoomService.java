package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import java.util.List;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ChatMessageModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.RoomModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.ChatMessageRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository,ChatMessageRepository chatMessageRepository){
        this.roomRepository = roomRepository;
        this.chatMessageRepository = chatMessageRepository;
    }
    public RoomModel addRoom(RoomModel roomModel) {
        RoomModel chatRoom = new RoomModel(roomModel.getUserOne(),roomModel.getUserTwo());
        return  roomRepository.save(chatRoom);
    }

    public List<ChatMessageModel> joinRoom(RoomModel roomModel) {
        RoomModel RoomModel = roomRepository.findRoomModelByUserOneAndUserTwoOrUserOneAndUserTwo(roomModel.getUserOne(), roomModel.getUserTwo(), roomModel.getUserTwo(), roomModel.getUserOne());
        if (RoomModel == null)
            RoomModel = addRoom(roomModel);
        return getMessage(RoomModel.getId());
    }

    public List<ChatMessageModel> getMessage(int id) {
        List<ChatMessageModel> chatMessageModel = chatMessageRepository.findChatMessageModelsByRoomId(id);
        for(int i =0;i<chatMessageModel.size();i++){
            String text = chatMessageModel.get(i).getMessage();
            String decrypt = AES.Decrypt(text);
            chatMessageModel.get(i).setMessage(decrypt);
        }
        return chatMessageModel;
    }

    public ChatMessageModel sendMessage(ChatMessageModel chatMessageModel) {
        RoomModel roomModel = roomRepository.findRoomModelById(chatMessageModel.getRoomId());
        if(roomModel.getUserOne() == chatMessageModel.getSenderId() || roomModel.getUserTwo() == chatMessageModel.getSenderId()){
            String input = chatMessageModel.getMessage();
            chatMessageModel.setMessage(AES.Encrypt(input));
            return chatMessageRepository.save(chatMessageModel);
        }
        return null;
    }
}
