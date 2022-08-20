package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.FileHandler.StorageException;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.FileHandler.StorageFileNotFoundException;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.FileHandler.StorageProperties;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.FileHandler.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;
    private final String Direct;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) throws IOException {
        this.Direct = "itemImg/Kanit-10001";
        this.rootLocation = Paths.get(properties.getLocation() + "\\" + this.Direct.replace("/", "\\"));
        if (Files.notExists(this.rootLocation)) {
            try {
                Files.createDirectories(this.rootLocation);
            } catch (IOException e) {
                throw e;
            }
        }
    }

    @Override
    public String store(MultipartFile file) {
        Date date = new Date();
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            String newFileName = 10001 + "" + date.getTime() + ".jpg";
            Path destinationFile = this.rootLocation.resolve(Paths.get(Objects.requireNonNull(newFileName))).normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                return this.Direct + "/" + newFileName;
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation)).map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
