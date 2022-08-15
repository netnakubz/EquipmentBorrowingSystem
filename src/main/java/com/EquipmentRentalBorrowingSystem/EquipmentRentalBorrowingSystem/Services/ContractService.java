package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ContractModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.EquipmentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.ContractRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractService {
    private final ContractRepository contractRepository;
    private final EquipmentService equipmentService;

    public ContractService(ContractRepository contractRepository, EquipmentService equipmentService) {
        this.contractRepository = contractRepository;
        this.equipmentService = equipmentService;
    }

    /**
     * @param contractModel save the contract model
     */
    public ResponseEntity<ContractModel> makeAgreement(ContractModel contractModel) {
//         Optional<ContractModel> resultContractModel = contractRepository.findById(contractModel.getContractId());
//         if (resultContractModel.isEmpty())
//             return new ResponseEntity<>("contract model not found", HttpStatus.BAD_REQUEST);
//         //Check quantity of equipment is more than or less than user wants
//         Optional<EquipmentModel> equipmentModel = equipmentService.getEquipmentById(resultContractModel.get().getItemId());
//         if(equipmentModel.isPresent()){
//             if(equipmentModel.get().getQuantity() <= contractModel.getTotalRent()){
//                 contractRepository.save(contractModel);
//             }
//             return new ResponseEntity<>("Saved", HttpStatus.OK);
//         }
//         return new ResponseEntity<>("There is something went wrong",HttpStatus.BAD_REQUEST);
        ContractModel contractModel1 = contractRepository.save(contractModel);
        return new ResponseEntity<>(contractModel1, HttpStatus.OK);
    }

    /**
     * @return contract model
     */
    public Optional<ContractModel> getContract(int contractId) {
        return contractRepository.findById(contractId);
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
        if (contractModel.isPresent()) {
            Optional<EquipmentModel> equipmentModel = equipmentService
                    .getEquipmentById(contractModel.get().getItemId());
            if (equipmentModel.isPresent()) {
                int quantity = equipmentModel.get().getQuantity() - contractModel.get().getTotalRent(); //check quantity of equipment
                if (quantity >= 0) {
                    equipmentModel.get().setQuantity(quantity);
                    equipmentService.updateEquipment(equipmentModel.get(), "update"); // update equipment in database
                    contractModel.get().setEditStatus(false); //disable editing status
                    this.makeAgreement(contractModel.get()); // update contract
                } else {
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }
            }
        }
        return new ResponseEntity<>(contractModel.get(), HttpStatus.OK);
    }
}
