package com.valam.app.service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.Deflater;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.valam.app.repo.UserRepository;

@Service
public class ImageUpload {
   
	
	@Autowired
	private UserRepository userRepo;
	
    public String addImage(long id, MultipartFile file) {
        //File file1 = new File("C:\\Users\\AjayAbhi\\Pictures\\water-drop.jpg");
        
        byte[] imageByteArray = null;
        String imageDataString = null;
		try {
			/*
			 * Reading a Image file from file system
			 */
			//FileInputStream imageInFile = new FileInputStream(file1);
			 //compressBytes(file.getBytes());
			byte imageData[] = compressBytes(file.getBytes());
			//imageInFile.read(imageData);
			
			/*
			 * Converting Image byte array into Base64 String 
			 */
			imageDataString = encodeImage(imageData);
			userRepo.updateImage(imageDataString,id);
			/*
			 * Converting a Base64 String into Image byte array 
			 */
			imageByteArray = decodeImage(imageDataString);
			
			/*
			 * Write a image byte array into file system  
			 */
			//FileOutputStream imageOutFile = new FileOutputStream("C:\\Users\\AjayAbhi\\Pictures\\water-drop-Copy.jpg");
			//imageOutFile.write(imageByteArray);
			
			//imageInFile.close();
			//imageOutFile.close();
			
			System.out.println("Image Successfully Manipulated!");
			System.out.println(id);
			
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
        return imageDataString;
    }   
    
    /**
	 * Encodes the byte array into base64 string
	 * @param imageByteArray - byte array
	 * @return String a {@link java.lang.String}
	 */
	public static String encodeImage(byte[] imageByteArray){		
		return Base64.encodeBase64URLSafeString(imageByteArray);		
	}
	
	/**
	 * Decodes the base64 string into byte array
	 * @param imageDataString - a {@link java.lang.String} 
	 * @return byte array
	 */
	public static byte[] decodeImage(String imageDataString) {		
		return Base64.decodeBase64(imageDataString);
	}
	
	
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}
}
