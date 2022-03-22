package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.EquipmentModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.EquipmentService;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.FileStorageServiceImpl;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EquipmentController {

    private final EquipmentService equipmentService;
    private final FileStorageServiceImpl file;

    public EquipmentController(EquipmentService equipmentService,FileStorageServiceImpl file) {
        this.equipmentService = equipmentService;
        this.file = file;
    }

    @PostMapping("/rentEquipment")
    public ResponseEntity<String> rentEquipment(@RequestParam int itemId, @RequestParam int userId) {
        return equipmentService.rentEquipment(itemId, userId);
    }

    @PostMapping("/returnEquipment")
    public ResponseEntity<String> returnEquipment(@RequestParam int itemId) {
        return equipmentService.returnEquipment(itemId);
    }

    @DeleteMapping("/deleteEquipment")
    public ResponseEntity<String> deleteEquipment(@RequestParam int itemId) {
        return equipmentService.deleteEquipment(itemId);
    }

    //    @PostMapping("/addEquipment")
//    public ResponseEntity<String> addEquipment(@RequestBody EquipmentModel equipmentModel,@RequestParam("image") MultipartFile multipartFile) throws IOException {
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
//        return equipmentService.addEquipment(equipmentModel);
//    }
    @PostMapping("/uploadEquipment")
    public void handleFileUpload(@RequestParam("file") MultipartFile[] multipartFile) throws IOException, FirebaseAuthException {
        for (MultipartFile value : multipartFile) {
            file.save(value);
        }
    }

    @GetMapping("/Equipment")
    public Optional<EquipmentModel> getEquipment(@RequestParam int itemId) {
        return equipmentService.getEquipmentById(itemId);
    }


}
