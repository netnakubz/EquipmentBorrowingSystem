package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.EquipmentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ReceiptModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.ReceiptRepository;
import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    public ReceiptService(ReceiptRepository receiptRepository){
        this.receiptRepository = receiptRepository;
    }
    /**
     * Create receipt
     * @param equipmentModel
     * @param userModel
     */
    public void createReceipt(EquipmentModel equipmentModel, UserModel userModel){
        ReceiptModel receiptModel = new ReceiptModel(userModel.getUserId(),equipmentModel.getItem_ID(),equipmentModel.getPrice());
        receiptRepository.save(receiptModel);
    }
}
