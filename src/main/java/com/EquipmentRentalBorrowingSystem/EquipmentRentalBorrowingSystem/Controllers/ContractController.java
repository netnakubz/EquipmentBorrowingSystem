package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ContractModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.ContractService;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ContractController {

    private final ContractService contractService;

    private final UserService userService;

    public ContractController(ContractService contractService, UserService userService) {
        this.contractService = contractService;
        this.userService = userService;
    }

    @PostMapping("/createAgreement")
    public ContractModel makeAgreement(@RequestBody ContractModel contractModel, Principal principal) {
        System.out.println(contractModel.getRoomModel());
        Optional<UserModel> userModel = userService.findByLocalId(principal.getName());
        if (userModel.isEmpty()) return null;
        contractModel.setCreator(userModel.get().getUserId());
        return contractService.makeAgreement(contractModel);
    }

    @GetMapping("/getAgreement")
    public Optional<ContractModel> getAgreement(@RequestParam int contractId) {
        return contractService.getContract(contractId);
    }

    @PutMapping("/acceptContract")
    public boolean acceptContract(@RequestParam int contractId) {
        return contractService.acceptContract(contractId);
    }

    @PostMapping("/generateContract")
    public ResponseEntity<ContractModel> generateContract(@RequestParam int contractId) {
        Optional<ContractModel> contractModel = contractService.getContract(contractId);
        if (contractModel.isPresent()) {
            return contractService.generateContract(contractId);
        }
        return null;
    }

    @PutMapping("/test")
    public Principal test(Principal principal) {
        return principal;
    }
}
