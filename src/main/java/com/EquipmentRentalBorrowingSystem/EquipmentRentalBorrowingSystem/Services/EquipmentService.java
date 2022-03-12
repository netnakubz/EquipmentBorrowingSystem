package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.EquipmentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.EquipmentRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
* @ author Kanit
*
* This Class is about managing equipment
* use CRUD method
*
* */
@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final ReceiptService receiptService;
    private final UserRepository userRepository;
    public EquipmentService(EquipmentRepository equipmentRepository, ReceiptService receiptService,UserRepository userRepository) {
        this.equipmentRepository = equipmentRepository;
        this.receiptService = receiptService;
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> addEquipment(@NotNull EquipmentModel equipmentModel) {
        equipmentRepository.save(equipmentModel);
        return new ResponseEntity<>("Save", HttpStatus.OK);
    }
    public ResponseEntity<String> deleteEquipment(int itemId){
        Optional<EquipmentModel> equipmentModel = equipmentRepository.findById(itemId);
      if(equipmentModel.isPresent()){
          equipmentRepository.deleteById(itemId);
          return new ResponseEntity<>("Deleted",HttpStatus.OK);
      }
    return new ResponseEntity<>("Delete failed",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> rentEquipment(int itemId,int userId) {
        Optional<EquipmentModel> equipmentModel = equipmentRepository.findById(itemId);
        Optional<UserModel> userModel = userRepository.findById(userId);
        if (equipmentModel.isPresent() && userModel.isPresent()) {
            if(equipmentModel.get().getQuantity() > 0){
                updateEquipment(equipmentModel.get(),"Borrow");
                receiptService.createReceipt(equipmentModel.get(),userModel.get());
                return new ResponseEntity<>("rent successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("equipment not enough", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("rent failed", HttpStatus.BAD_REQUEST);
    }


    public void updateEquipment(EquipmentModel equipmentModel, @NotNull String status) {
        if(status.equalsIgnoreCase("borrow")){
            equipmentModel.setQuantity(equipmentModel.getQuantity() - 1);
            equipmentModel.setTotalRent(equipmentModel.getTotalRent() + 1);
            equipmentModel.setStatus(true);
        }else if(status.equalsIgnoreCase("return")){
            equipmentModel.setQuantity(equipmentModel.getQuantity() + 1);
            equipmentModel.setStatus(false);
        }
        equipmentRepository.save(equipmentModel);
    }
}
