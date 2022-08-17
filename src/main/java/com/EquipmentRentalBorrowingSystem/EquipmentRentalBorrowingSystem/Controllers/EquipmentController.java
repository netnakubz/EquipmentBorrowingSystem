 package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

 import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.EquipmentModel;
 import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.EquipmentService;
 import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.FileStorageService;
 import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.SecurityService;
 import com.google.firebase.auth.FirebaseAuthException;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.*;
 import org.springframework.web.multipart.MultipartFile;

 import java.io.IOException;
 import java.util.Objects;
 import java.util.Optional;
 import java.util.stream.Stream;

 @RestController
 @RequestMapping("/api/v1")
 public class EquipmentController {

     private final EquipmentService equipmentService;
     private final FileStorageService fileStorageService;
     private final SecurityService securityService;

     public EquipmentController(EquipmentService equipmentService, FileStorageService fileStorageService,SecurityService securityService) {
         this.equipmentService = equipmentService;
         this.fileStorageService = fileStorageService;
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
     public ResponseEntity<String> handleFileUpload(@RequestParam String name, @RequestParam int price, @RequestParam int quantity,@RequestParam int type, @RequestParam MultipartFile[] files) throws IOException, FirebaseAuthException {
         EquipmentModel equipmentModel = new EquipmentModel();
         equipmentModel.setName(name);
         equipmentModel.setPrice(price);
         equipmentModel.setQuantity(quantity);
         equipmentModel.setUserId(Objects.requireNonNull(securityService.getCurrentUser().getBody()).getUserId());
         equipmentModel.setTypeId(type);
         EquipmentModel result = equipmentService.addEquipment(equipmentModel);
         Stream.of(files).forEach(file -> {
             try {
                 System.out.println(file.getOriginalFilename());
                 fileStorageService.save(file, result);
             } catch (FirebaseAuthException | IOException e) {
                 e.printStackTrace();
             }
         });
         return new ResponseEntity<>("Uploaded", HttpStatus.OK);
     }

     @GetMapping("/equipment")
     public Optional<EquipmentModel> getEquipment(@RequestParam int itemId) {
         return equipmentService.getEquipmentById(itemId);
     }

     @GetMapping("/get/all/equipment")
     public Iterable<EquipmentModel> getAllEquipment(){
         return equipmentService.getAllEquipment();
     }


 }
