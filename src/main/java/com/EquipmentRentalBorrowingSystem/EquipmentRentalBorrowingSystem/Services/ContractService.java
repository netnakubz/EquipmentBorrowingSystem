package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;


import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ContractModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.EquipmentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.ContractRepository;
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

    public void makeAgreement(ContractModel contractModel) {
        contractRepository.save(contractModel);
    }

    public Optional<ContractModel> getContract(int contractId) {
        return contractRepository.findById(contractId);
    }

    public Optional<ContractModel> generateContract(int contractId) {
        Optional<ContractModel> contractModel = contractRepository.findById(contractId);
        if (contractModel.isPresent()) {
            Optional<EquipmentModel> equipmentModel = equipmentService.getEquipmentById(contractModel.get().getItemId());
            if (equipmentModel.isPresent()) {
                int quantity = equipmentModel.get().getQuantity() - contractModel.get().getTotalRent();
                if (quantity >= 0) {
                    equipmentModel.get().setQuantity(quantity);
                    equipmentService.updateEquipment(equipmentModel.get(), "update");
                }
            }
        }
        return contractRepository.findById(contractId);
    }
}
