package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ContractModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.EquipmentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.RoomModel;
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

    public ContractService(ContractRepository contractRepository, EquipmentService equipmentService, RoomService roomService) {
        this.contractRepository = contractRepository;
        this.equipmentService = equipmentService;
        this.roomService = roomService;
    }

    /**
     * @param contractModel save the contract model
     */
    public ContractModel makeAgreement(ContractModel contractModel) {
        System.out.println(contractModel);
        Optional<EquipmentModel> equipment = equipmentService.getEquipmentById(contractModel.getEquipmentModel().getItemId());
        Optional<RoomModel> room = roomService.getRoom(contractModel.getRoom().getRoomId());
        if (equipment.isPresent() && room.isPresent()) {
            contractModel.setEquipmentModel(equipment.get());
            contractModel.setRoom(room.get());
        }
        return contractRepository.save(contractModel);
    }

    /**
     * @return contract model
     */
    public Optional<ContractModel> getContract(int contractId) {
        Optional<ContractModel> contract = contractRepository.findById(contractId);
        if (contract.isPresent()) {
            //check that creator is not the one who request this.
            if (!contract.get().getCreator().equals(10002) && !contract.get().getEditStatus()) {
                contract.get().setEditAble(true);
            }else{
                contract.get().setEditAble(false);
            }
        }
        return contract;
    }

    public boolean acceptContract(int contractId) {
        Optional<ContractModel> contract = contractRepository.findById(contractId);
        if (contract.isPresent()) {
            contract.get().setEditStatus(true);
            contractRepository.save(contract.get());
            return true;
        }
        return false;
    }

    /**
     * after borrower and owner success their agreements then
     * system will generate contract
     * update equipment and contract to disable editing
     *
     * @param contractId find that contractId is existed
     * @return return contract
     */
    public ResponseEntity<ContractModel> generateContract(int contractId) {
        Optional<ContractModel> contractModel = getContract(contractId);
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
