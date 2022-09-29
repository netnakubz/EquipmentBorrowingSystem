package com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Controllers;

import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Models.ItemImgModel;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.FileSystemStorageService;
import com.EquipmentRentalBorrowingSystem.EquipmentRentalBorrowingSystem.Services.ItemImgService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class ImageController {

    private ItemImgService itemImgService;
    private FileSystemStorageService fileSystemStorageService;

    public ImageController(ItemImgService itemImgService, FileSystemStorageService fileSystemStorageService) {
        this.itemImgService = itemImgService;
        this.fileSystemStorageService = fileSystemStorageService;
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = fileSystemStorageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    @GetMapping("/{imgGroup}/{owner}/{filename:.+}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename,
                                           @PathVariable String imgGroup,
                                           @PathVariable String owner) throws IOException {

        var imgFile = new ClassPathResource(imgGroup + "/" + owner + "/" + filename);
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }



    @PostMapping("/test")
    public String test(){
        ItemImgModel e = new ItemImgModel("qwe.jpg");
        return itemImgService.saveImg(e);
    }
}
