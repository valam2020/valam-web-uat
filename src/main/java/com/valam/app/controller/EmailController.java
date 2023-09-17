package com.valam.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.dto.EmailDetails;
import com.valam.app.service.EmailService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mail")
public class EmailController {
	
	@Autowired private EmailService emailService;
	 
    // Sending a simple Email
    @PostMapping("/send")
    public String sendMail(@RequestBody EmailDetails details)
    {
        String status= emailService.sendSimpleMail(details);
 
        return status;
    }
 
    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details)
    {
        String status= emailService.sendMailWithAttachment(details);
 
        return status;
    }

}
