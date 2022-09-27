package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.*;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.EquipmentService;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.FileSystemStorageService;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class EquipmentController {

    private final FileSystemStorageService storageService;
    private final EquipmentService equipmentService;
    private final SecurityService securityService;

    public EquipmentController(EquipmentService equipmentService, FileSystemStorageService fileStorageService, SecurityService securityService) {
        this.equipmentService = equipmentService;
        this.storageService = fileStorageService;
        this.securityService = securityService;
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

    @PostMapping("/uploadEquipment")
    public String handleFileUpload(@RequestParam("files") MultipartFile[] files,
                                   @RequestParam Integer quantity,
                                   @RequestParam Integer price,
                                   @RequestParam String[] serials,
                                   @RequestParam String name,
                                   @RequestParam Integer userId,
                                   @RequestParam Integer[] types) {
        EquipmentModel equipmentModel = new EquipmentModel(quantity, price, name, userId);
        Set<ItemImgModel> itemImgModels = new HashSet<ItemImgModel>();
        Set<EquipmentType> equipmentTypes = new HashSet<EquipmentType>();
        Set<EquipmentSerial> equipmentSerials = new HashSet<EquipmentSerial>();
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Arrays.stream(files).forEach(file -> {
            String fileName = storageService.store(file);
            itemImgModels.add(new ItemImgModel(fileName));
        });
        Arrays.stream(types).forEach(type -> {
            equipmentTypes.add(new EquipmentType(type));
        });
        Arrays.stream(serials).forEach(serial -> {
            System.out.println(serial);
            equipmentSerials.add(new EquipmentSerial(serial));
        });
        equipmentModel.setItemImg(itemImgModels);
        equipmentModel.setCreate_date(timestamp);
        equipmentModel.setEquipmentSerials(equipmentSerials);
        equipmentModel.setEquipmentTypes(equipmentTypes);
        equipmentService.addEquipment(equipmentModel);
        return "redirect:/";
    }

    @GetMapping("/equipment")
    public Optional<EquipmentModel> getEquipment(@RequestParam int itemId) {
        Optional<EquipmentModel> temp = equipmentService.getEquipmentById(itemId);
        return temp;
    }

    @GetMapping("/get/all/equipment")
    public Iterable<EquipmentModel> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @GetMapping("/get/equipment/by")
    public Iterable<EquipmentModel> getEquipmentBy(@RequestParam int userId) {
        return equipmentService.getEquipmentByUserId(userId);
    }

    @GetMapping("/get/itemType")
    public Iterable<TypeModel> getItemType() {
        return equipmentService.getItemType();
    }
}
