package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;


import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.TypeModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeService {

    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository){
        this.typeRepository = typeRepository;
    }

    public Iterable<TypeModel> getAllTypes(){
        return typeRepository.findAll();
    }

    public Optional<TypeModel> get(Integer typeId){
        return typeRepository.findById(typeId);
    }
}
