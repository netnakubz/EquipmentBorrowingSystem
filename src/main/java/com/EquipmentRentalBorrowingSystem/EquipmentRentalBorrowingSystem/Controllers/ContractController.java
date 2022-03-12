package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ContractModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.ContractService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ContractController {
    
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("/makeAgreement")
    public ResponseEntity<String> makeAgreement(@RequestBody ContractModel contractModel){
        contractService.makeAgreement(contractModel);
        return new ResponseEntity<>("Created agreement", HttpStatus.OK);
    }

    @GetMapping("/getAgreement")
    public Optional<ContractModel> getAgreement(@RequestParam int contractId){
        return contractService.getContract(contractId);
    }

    @PostMapping("/generateContract")
    public Optional<ContractModel> generateContract(@RequestParam int contractId){
        Optional<ContractModel> contractModel = contractService.getContract(contractId);
        if(contractModel.isPresent()){
            return contractService.generateContract(contractId);
        }
        return Optional.empty();
    }
}
