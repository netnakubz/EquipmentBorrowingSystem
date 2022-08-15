 package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

 import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Interfaces.FileStorageServiceInterface;
 import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.EquipmentModel;
 import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ItemImgModel;
 import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.UserModel;
 import com.google.firebase.auth.FirebaseAuthException;
 import org.springframework.core.io.Resource;
 import org.springframework.core.io.UrlResource;
 import org.springframework.http.ResponseEntity;
 import org.springframework.stereotype.Service;
 import org.springframework.web.multipart.MultipartFile;

 import java.io.IOException;
 import java.net.MalformedURLException;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.nio.file.Paths;
 import java.util.Date;
 import java.util.Objects;
 import java.util.stream.Stream;

 @Service
 public class FileStorageService implements FileStorageServiceInterface {
     private Path root;
     private final SecurityService securityService;
     private final EquipmentService equipmentService;
     private final ItemImgService itemImgService;

     public FileStorageService(SecurityService securityService, ItemImgService itemImgService, EquipmentService equipmentService) {
         super();
         this.securityService = securityService;
         this.itemImgService = itemImgService;
         this.equipmentService = equipmentService;
     }

     @Override
     public void init() throws FirebaseAuthException {
         ResponseEntity<UserModel> userModel = securityService.getCurrentUser();
         root = Paths.get("uploads/", Objects.requireNonNull(userModel.getBody()).getName() + "-" + userModel.getBody().getUserId());
         if (Files.notExists(root)) {
             try {
                 Files.createDirectory(root);
             } catch (IOException e) {
                 System.out.println("Could not create directory : " + e.getMessage());
             }
         }
     }

     // TODO: 23/3/2022 AD insert equipment into database first then get id of item
     @Override
     public void save(MultipartFile file, EquipmentModel equipmentModel) throws FirebaseAuthException, IOException {
         this.init();
         ResponseEntity<UserModel> userModel = securityService.getCurrentUser();
         Date date = new Date();
         String newName = Objects.requireNonNull(userModel.getBody()).getUserId() + "" + date.getTime() + ".jpg";
         //Copy file from user to server
         try {
             Files.copy(file.getInputStream(), this.root.resolve(newName));
         } catch (Exception e) {
             throw new RuntimeException("Could not copy file : " + e.getMessage());
         }
         ItemImgModel itemImgModel = new ItemImgModel();
         itemImgModel.setLocation("uploads/" + userModel.getBody().getName() + "-" + userModel.getBody().getUserId() + "/" + newName);
         itemImgModel.setItemId(equipmentModel.getItemId());
         String result = itemImgService.saveImg(itemImgModel);
         System.out.println(result);
     }

     @Override
     public Resource load(String fileName) {
         try {
             Path file = root.resolve(fileName);
             Resource resource = new UrlResource((file.toUri()));
             if (resource.exists() || resource.isReadable()) {
                 return resource;
             } else {
                 throw new RuntimeException("Could not read the file!");
             }
         } catch (MalformedURLException e) {
             throw new RuntimeException("Error: " + e.getMessage());
         }
     }

     @Override
     public void deleteAll() {
         System.out.println("Delete All");
     }

     @Override
     public Stream<Path> loadAll() {
         System.out.println("load All");
         return null;
     }
 }
