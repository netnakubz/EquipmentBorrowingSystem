package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models;

import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    void init() throws FirebaseAuthException;

    void save(MultipartFile file) throws IOException, FirebaseAuthException;


    Resource load(String fileName);

    void deleteAll();

    Stream<Path> loadAll();
}
