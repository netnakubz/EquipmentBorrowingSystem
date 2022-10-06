package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ReceiptModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Repositories.ReceiptRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
import java.util.Date;

@EnableAsync
public class ComputeFine {
    private final ReceiptRepository receiptRepository;

    public ComputeFine(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Async
    @Scheduled(cron = "0 0 * * *")
    public void compute() {
        Iterable<ReceiptModel> receiptModels = receiptRepository.findAll();
        receiptModels.forEach(receiptModel -> {
            Date date = new Date();
            Timestamp t = new Timestamp(date.getTime());
            if (!receiptModel.getStatus() && date.compareTo(receiptModel.getContractModel().getEndDate()) > 0) {
                int fine = receiptModel.getContractModel().getFineLate();
                receiptModel.setFineLate(receiptModel.getFineLate() + fine);
                receiptRepository.save(receiptModel);
            }
        });
    }
}
