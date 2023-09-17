package com.valam.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.valam.app.dto.ImageUploadResponse;
import com.valam.app.model.ImageData;
import com.valam.app.repo.ImageDataRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

    @Autowired
    private ImageDataRepository imageDataRepository;

    public ImageUploadResponse uploadImage(Long id,MultipartFile file) throws IOException {
        imageDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .id(id)
                .imageData(ImageUtil.compressImage(file.getBytes())).build());
        ImageUploadResponse image = new ImageUploadResponse();
        image.setFileName(file.getOriginalFilename());
        image.setMessage("Image uploaded successfully");
        return image;
    }
    
//    public ImageUploadResponse updateImageById(Long id,MultipartFile file) throws IOException {
//    	
//    	ImageData imageData = ImageData.builder()
//        		.name(file.getOriginalFilename())
//                .type(file.getContentType())
//        		.imageData(ImageUtil.compressImage(file.getBytes())).build();
//    	
//        imageDataRepository.updateImageById(imageData,id);
//        
//        ImageUploadResponse image = new ImageUploadResponse();
//        image.setFileName(file.getOriginalFilename());
//        image.setMessage("Image uploaded successfully");
//        return image;		
//    }
//    
   

    @Transactional
    public ImageData getInfoByImageByName(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);

        return ImageData.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();

    }
    


    @Transactional
    public byte[] getImage(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
        return image;
    }
    
    @Transactional
    public byte[] getImageById(Long id) {
        Optional<ImageData> dbImage = imageDataRepository.findByDriverId(id);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
        return image;
    }
     
    public ImageUploadResponse deleteImage(Long id) {
    	imageDataRepository.deleteById(id);
    	
    	 ImageUploadResponse image = new ImageUploadResponse();
         image.setFileName(""+id);
         image.setMessage("Image Deleted successfully");
         return image;
    }
    


}