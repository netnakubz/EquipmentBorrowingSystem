package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;


import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ContractModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.ContractRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractService {
    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public void makeAgreement(ContractModel contractModel){
        contractRepository.save(contractModel);
    }
    public Optional<ContractModel> getContract(int contractId){
        return contractRepository.findById(contractId);
    }
    public Optional<ContractModel> generateContract(int contractId){
        return contractRepository.findById(contractId);
    }
}
