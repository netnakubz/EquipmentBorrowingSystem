package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.*;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.ContractRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractService {
    private final ContractRepository contractRepository;
    private final EquipmentService equipmentService;
    private final RoomService roomService;
    private final ReceiptService receiptService;
    private final UserService userService;
    public ContractService(ContractRepository contractRepository, EquipmentService equipmentService, RoomService roomService,ReceiptService receiptService,UserService userService) {
        this.contractRepository = contractRepository;
        this.equipmentService = equipmentService;
        this.roomService = roomService;
        this.receiptService = receiptService;
        this.userService = userService;
    }

    /**
     * @param contractModel save the contract model
     */
    public ContractModel makeAgreement(ContractModel contractModel) {
        Optional<EquipmentModel> equipment = equipmentService.getEquipmentById(contractModel.getEquipmentModel().getItemId());
        Optional<RoomModel> room = roomService.getRoom(contractModel.getRoomModel().getRoomId());

        if (equipment.isPresent() && room.isPresent()) {
            contractModel.setEquipmentModel(equipment.get());
            contractModel.setRoomModel(room.get());
        }
        return contractRepository.save(contractModel);
    }

    /**
     * @return contract model
     * have to fix this critical bug !!
     */
    public Optional<ContractModel> getContract(int contractId,String localId) {
        Optional<UserModel> userModel = userService.findByLocalId(localId);
        Optional<ContractModel> contract = contractRepository.findById(contractId);
        if (contract.isPresent()) {
            //check that creator is not the one who request this.
            if (!contract.get().getCreator().equals(userModel.get().getUserId()) && !contract.get().getEditStatus()) {
                contract.get().setEditAble(true);
            } else {
                contract.get().setEditAble(false);
            }
        }
        return contract;
    }

    public boolean acceptContract(int contractId) {
        Optional<ContractModel> contract = contractRepository.findById(contractId);
        if (contract.isEmpty())
            return false;
        ReceiptModel receiptModel = new ReceiptModel(contract.get());
        receiptModel.setUserModel(contract.get().getEquipmentModel().getUser());
        UserModel borrower = contract.get().getRoomModel().getUserOne() == contract.get().getEquipmentModel().getUser() ? contract.get().getRoomModel().getUserTwo():contract.get().getRoomModel().getUserOne();
        receiptModel.setBorrower(borrower);
        receiptService.createReceipt(receiptModel);
        contract.get().setEditStatus(true);
        contractRepository.save(contract.get());
        return true;
    }

    /**
     * after borrower and owner success their agreements then
     * system will generate contract
     * update equipment and contract to disable editing
     *
     * @param contractId find that contractId is existed
     * @return return contract
     */
    public ResponseEntity<ContractModel> generateContract(int contractId,String localId) {
        Optional<ContractModel> contractModel = getContract(contractId,localId);
//        if (contractModel.isPresent()) {
//            Optional<EquipmentModel> equipmentModel = equipmentService
//                    .getEquipmentById(contractModel.get().getEquipment().getItemId());
//            if (equipmentModel.isPresent()) {
//                int quantity = equipmentModel.get().getQuantity() - contractModel.get().getTotalRent(); //check quantity of equipment
//                if (quantity >= 0) {
//                    equipmentModel.get().setQuantity(quantity);
//                    equipmentService.updateEquipment(equipmentModel.get(), "update"); // update equipment in database
//                    contractModel.get().setEditStatus(false); //disable editing status
//                    this.makeAgreement(contractModel.get()); // update contract
//                } else {
//                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//                }
//            }
//        }
        return new ResponseEntity<>(contractModel.get(), HttpStatus.OK);
    }


}
