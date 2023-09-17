package com.valam.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.dto.EmailDetails;
import com.valam.app.dto.ResponseMessage;
import com.valam.app.model.Driver;
import com.valam.app.repo.DriverRepositary;
import com.valam.app.service.EmailService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/valam_mail")
public class ValamEmailController {

	@Autowired
	private EmailService emailService;

	@Autowired
	private DriverRepositary driverRepo;

	@SuppressWarnings("null")
	@PostMapping("/sendOtp")
	public ResponseMessage sendMail(@RequestBody EmailDetails details) {
		String otp = null;
		String subject = null;
		String status = null;
		ResponseMessage message = new ResponseMessage();
		Driver driver = driverRepo.findByEmail(details.getRecipient());

		if (details.getRecipient() != null && driver.getEmail() != null) {
			subject = "Valam Driver Validation";
			otp = String.valueOf(((int) (Math.random() * (10000 - 1000))) + 1000);
			String msg = "Your OTP is" + " " + otp;
			details.setMsgBody(msg);
			details.setSubject(subject);
			details.setId(driver.getId());
			status = emailService.sendSimpleMail(details);
			message.setHttpStatus(200);
			message.setMessage(status);
			driverRepo.updateDriverEmailOTP(otp, details.getId());
			return message;
		} else {
			message.setHttpStatus(200);
			message.setMessage("Email is Not Registered");
			return message;
		}
	}

}
