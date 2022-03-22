package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ItemImgModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.ItemImgRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemImgService {
    private final ItemImgRepository itemImgRepository;

    public ItemImgService(ItemImgRepository itemImgRepository) {
        this.itemImgRepository = itemImgRepository;
    }

    public String saveImg(ItemImgModel itemImgModel){
        itemImgRepository.save(itemImgModel);
        return "Saved";
    }
}
