package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.EquipmentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/deleteEquipment")
    public ResponseEntity<String> deleteEquipment(@RequestParam int itemId){
        return equipmentService.deleteEquipment(itemId);
    }

    @PostMapping("/addEquipment")
    public ResponseEntity<String> addEquipment(@RequestBody EquipmentModel equipmentModel){
        return equipmentService.addEquipment(equipmentModel);
    }

}
