package com.valam.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.valam.app.dto.ImageUploadResponse;
import com.valam.app.model.ImageData;
import com.valam.app.service.ImageDataService;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageDataController {

    @Autowired
    private ImageDataService imageDataService;

    @PostMapping("/save/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable("id") Long id,@RequestParam("image") MultipartFile file) throws IOException {
        ImageUploadResponse response = imageDataService.uploadImage(id,file);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
    
    
//    @PostMapping("/update/{id}")
//    public ResponseEntity<?> updateImage(@PathVariable("id") Long id,@RequestParam("image") MultipartFile file) throws IOException {
//        ImageUploadResponse response = imageDataService.updateImageById(id,file);
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(response);
//    }

    @GetMapping("/info/{name}")
    public ResponseEntity<?>  getImageInfoByName(@PathVariable("name") String name){
        ImageData image = imageDataService.getInfoByImageByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getImageByName(@PathVariable("id") Long id){
        byte[] image = imageDataService.getImageById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
    
    
    @DeleteMapping("/{id}")
    public ImageUploadResponse  deleteImage(@PathVariable("id") Long id){
          return imageDataService.deleteImage(id);
    }
}