package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.HomeAddressModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.HomeAdressRepository;
import org.springframework.stereotype.Service;

@Service
public class HomeAdressService {

    private HomeAdressRepository homeAdressRepository;
    public  HomeAdressService(HomeAdressRepository homeAdressRepository){
        this.homeAdressRepository = homeAdressRepository;
    }

    public HomeAddressModel save(HomeAddressModel homeAddressModel){
        return homeAdressRepository.save(homeAddressModel);
    }
}
