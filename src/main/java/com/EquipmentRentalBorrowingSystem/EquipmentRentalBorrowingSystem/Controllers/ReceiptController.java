package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ReceiptModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.ReceiptService;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ReceiptController {
    private final ReceiptService receiptService;
    private final UserService userService;
    public ReceiptController(ReceiptService receiptService,UserService userService) {
        this.receiptService = receiptService;
        this.userService = userService;
    }

    @GetMapping("/get/receipt")
    public Iterable<ReceiptModel> receiptModels(Principal principal) {
        System.out.println(principal.getName());
        return receiptService.getReceipt(principal);
    }

    @GetMapping("/get/renting")
    public Iterable<ReceiptModel> renting(Principal principal){
        return receiptService.findRenting(principal);
    }

    @GetMapping("/return")
    public ReceiptModel returnItem(@RequestParam Integer  receiptId){
        System.out.println(receiptId);
        return receiptService.returnItem(receiptId);
    }
}
