package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ChatMessageModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Config.Repositories.ChatMessageRepository;

import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public void saveMessage(ChatMessageModel chatMessageModel) {
        chatMessageRepository.save(chatMessageModel);
    }
}
