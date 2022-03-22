package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.FileStorageService;
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
public class FileStorageServiceImpl implements FileStorageService {
    private Path root;
    private final SecurityService securityService;
    private final EquipmentService equipmentService;

    public FileStorageServiceImpl(SecurityService securityService, EquipmentService equipmentService) {
        super();
        this.securityService = securityService;
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
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void save(MultipartFile file) throws FirebaseAuthException {
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
