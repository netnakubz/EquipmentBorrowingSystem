package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.TypeModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TypeController {
    
    private final TypeService typeService;
    
    public TypeController(TypeService typeService){
        this.typeService = typeService;
    }

    @GetMapping("/getItemType")
    public Iterable<TypeModel> typeModels(){
        return typeService.getAllTypes();
    }
}
