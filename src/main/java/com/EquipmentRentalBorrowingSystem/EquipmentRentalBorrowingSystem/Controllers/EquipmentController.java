package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.EquipmentService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EquipmentController {

    private final EquipmentService equipmentService;
    public EquipmentController(EquipmentService equipmentService){
        this.equipmentService = equipmentService;
    }

    @PostMapping("/rentEquipment")
    public ResponseEntity<String> rentEquipment(@RequestParam int itemId,@RequestParam int userId){
            return equipmentService.rentEquipment(itemId, userId);
    }

    @PostMapping("/returnEquipment")
    public ResponseEntity<String> returnEquipment(@RequestParam int itemId){
        return equipmentService.returnEquipment(itemId);
    }
}
