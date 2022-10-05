package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ReceiptModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final UserService userService;

    public ReceiptService(ReceiptRepository receiptRepository, UserService userService) {
        this.receiptRepository = receiptRepository;
        this.userService = userService;
    }

    public void createReceipt(ReceiptModel receiptModel) {
//        ReceiptModel receiptModel = new ReceiptModel(userModel.getUserId(),equipmentModel.getItemId(),equipmentModel.getPrice());
        receiptRepository.save(receiptModel);
    }

    public Iterable<ReceiptModel> getReceipt(Principal principal) {
        Optional<UserModel> userModel = userService.findByLocalId(principal.getName());
        System.out.println(userModel.isEmpty());
        if (userModel.isEmpty())
            return null;
        return receiptRepository.findAllByUserModel(userModel.get());
    }

    public Iterable<ReceiptModel> findRenting(Principal principal) {
        Optional<UserModel> userModel = userService.findByLocalId(principal.getName());
        if (userModel.isEmpty())
            return null;
        return receiptRepository.findAllByBorrower(userModel.get());
    }
}
