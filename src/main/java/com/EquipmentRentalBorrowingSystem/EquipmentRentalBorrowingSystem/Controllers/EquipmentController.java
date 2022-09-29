package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.*;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.*;
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
    private final TypeService typeService;
    private final UserService userService;

    public EquipmentController(EquipmentService equipmentService, FileSystemStorageService fileStorageService, SecurityService securityService, UserService userService,TypeService typeService) {
        this.equipmentService = equipmentService;
        this.storageService = fileStorageService;
        this.securityService = securityService;
        this.userService = userService;
        this.typeService = typeService;
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
                                   @RequestParam Integer[] types,
                                   Principal principal) {
        Optional<UserModel> userModel = userService.userInformation(principal);
        if (userModel.isEmpty())
            return null;
        EquipmentModel equipmentModel = new EquipmentModel(quantity, price, name, userModel.get());
        equipmentModel.setUser(userModel.get());
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
            Optional<TypeModel> t = typeService.get(type);
            if(t.isEmpty())
                return;
            equipmentTypes.add(new EquipmentType(t.get()));
        });
        Arrays.stream(serials).forEach(serial -> {
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
    public EquipmentModel getEquipment(@RequestParam int itemId) {
        Optional<EquipmentModel> equipment = equipmentService.getEquipmentById(itemId);
        return equipment.orElse(null);
    }

    @GetMapping("/get/all/equipment")
    public Iterable<EquipmentModel> getAllEquipment() {
        return equipmentService.getAllEquipment();
    }

    @GetMapping("/get/equipment/by")
    public Iterable<EquipmentModel> getEquipmentBy(@RequestParam Integer userId) {
        return equipmentService.getEquipmentByUserId(userId);
    }


    @GetMapping("/get/itemType")
    public Iterable<TypeModel> getItemType() {
        return equipmentService.getItemType();
    }
}
