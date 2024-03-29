package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.FileHandler.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class EquipmentRentalBorrowingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EquipmentRentalBorrowingSystemApplication.class, args);
    }



}
