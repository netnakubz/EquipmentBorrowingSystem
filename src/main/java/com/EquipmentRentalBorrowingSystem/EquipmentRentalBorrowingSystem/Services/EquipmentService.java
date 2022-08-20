package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.EquipmentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.TypeModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.EquipmentRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.TypeRepository;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final ReceiptService receiptService;
    private final UserRepository userRepository;
    private final TypeRepository typeRepository;
    public EquipmentService(EquipmentRepository equipmentRepository, ReceiptService receiptService, UserRepository userRepository, TypeRepository typeRepository) {
        this.equipmentRepository = equipmentRepository;
        this.receiptService = receiptService;
        this.userRepository = userRepository;
        this.typeRepository = typeRepository;
    }

    /**
     * store equipment that user add into system
     * to database
     *
     * @param equipmentModel
     * @return
     */
    public EquipmentModel addEquipment(@NotNull EquipmentModel equipmentModel) {
        return equipmentRepository.save(equipmentModel);

    }

    /**
     * delete equipment but not really delete in database
     * set equipment display to false
     *
     * @param itemId
     * @return
     */
    public ResponseEntity<String> deleteEquipment(int itemId) {
        Optional<EquipmentModel> equipmentModel = equipmentRepository.findById(itemId);
        if (equipmentModel.isPresent()) {
            equipmentModel.get().setDisplay(false);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Delete failed", HttpStatus.BAD_REQUEST);
    }

    /**
     * get item id that user need to borrow then
     * check that item is existed if exist
     * check that item quantity is equal or more than user need to borrow
     *
     * @param itemId
     * @param userId
     * @return
     */
    public ResponseEntity<String> rentEquipment(int itemId, int userId) {
        Optional<EquipmentModel> equipmentModel = equipmentRepository.findById(itemId);
        Optional<UserModel> userModel = userRepository.findById(userId);
        if (equipmentModel.isPresent() && userModel.isPresent()) {
            if (equipmentModel.get().getQuantity() > 0) {
                updateEquipment(equipmentModel.get(), "Borrow");
                receiptService.createReceipt(equipmentModel.get(), userModel.get());
                return new ResponseEntity<>("rent successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("equipment not enough", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("rent failed", HttpStatus.BAD_REQUEST);
    }

    /**
     * update equipment by id by using EquipmentModel and status
     *
     * @param equipmentModel
     * @param status
     */
    public void updateEquipment(EquipmentModel equipmentModel, @NotNull String status) {
        if (status.equalsIgnoreCase("borrow")) {
            equipmentModel.setQuantity(equipmentModel.getQuantity() - 1);
            equipmentModel.setTotalRent(equipmentModel.getTotalRent() + 1);
            equipmentModel.setDisplay(true);
        } else if (status.equalsIgnoreCase("return")) {
            equipmentModel.setQuantity(equipmentModel.getQuantity() + 1);
            equipmentModel.setDisplay(false);
        }
        equipmentRepository.save(equipmentModel);
    }

    public ResponseEntity<String> returnEquipment(int itemId) {
        Optional<EquipmentModel> equipmentModel = equipmentRepository.findById(itemId);
        if (equipmentModel.isPresent()) {
            int quantity = equipmentModel.get().getQuantity();
            equipmentModel.get().setQuantity(quantity + 1);
            equipmentRepository.save(equipmentModel.get());
            return new ResponseEntity<>("Return successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Return failed or item not found", HttpStatus.BAD_REQUEST);
    }

    //     /**
//      * get list of items when user post to lend
//      * @param principal
//      * @return
//      */
//     public ResponseEntity<Iterable<EquipmentModel>> getAllEquipment(Principal principal){
//         return equipmentRepository.findAllByUserId(principal.getName());
//     }
    public Iterable<EquipmentModel> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Optional<EquipmentModel> getEquipmentById(int equipmentId) {
        return equipmentRepository.findById(equipmentId);
    }

    public Iterable<EquipmentModel> getEquipmentByUserId(Integer userId){
        return equipmentRepository.getEquipmentByUserId(userId);
    }

    public Iterable<TypeModel> getItemType(){
        return typeRepository.findAll();
    }
}
