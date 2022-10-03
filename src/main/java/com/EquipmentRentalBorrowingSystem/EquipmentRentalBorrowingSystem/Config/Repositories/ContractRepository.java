package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Config.Repositories;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ContractModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ContractRepository extends JpaRepository<ContractModel, Integer> {

    Boolean deleteAllByEditStatus(Boolean editStatus);


}
