package com.valam.app.service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.valam.app.model.OTPSystem;
import com.valam.app.repo.MobileUserRepo;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber; 

@Service
public class OTPService { 
	
	@Autowired
	private MobileUserRepo mobileUserRepo;
	
	 private OTPService() {
		
	 }
	 
   //to get otp to given mobile number and once verifies the otp from user input.
	 // here i used the twilio account services to get messages. using registered mobile number.
   public OTPSystem upDateOTPMobile(OTPSystem otp) {
	
		   String mobileNumber = otp.getMobileNumber();
		   Optional<OTPSystem> moe = mobileUserRepo.findByMobileNumber(mobileNumber);
			 if(moe.isPresent()) {
				 OTPSystem otpSystem = mobileUserRepo.findByMobileNumber(otp.getMobileNumber()).get();
				 otpSystem.setMobileNumber(mobileNumber);
				 otpSystem.setOtp(String.valueOf(((int)(Math.random()*(10000 - 1000))) + 1000));
				 otpSystem.setExpiryTimer(System.currentTimeMillis()+60000);
				 mobileUserRepo.save(otpSystem);
				 System.out.println(otpSystem.getId());
				 System.out.println(otpSystem.getOtp());
		         Message.creator(new PhoneNumber(otp.getMobileNumber()), new PhoneNumber("++16176168604"),"Your OTP is "+otpSystem.getOtp()).create();			 
				return otpSystem;   
			 }
			 else {
					otp.setMobileNumber(mobileNumber);
					otp.setOtp(String.valueOf(((int)(Math.random()*(10000 - 1000))) + 1000));
				    otp.setExpiryTimer(System.currentTimeMillis()+60000);
			        mobileUserRepo.save(otp); 
				 System.out.println(otp.getId());
				 System.out.println(otp.getOtp());
			     Message.creator(new PhoneNumber(otp.getMobileNumber()), new PhoneNumber("++16176168604"),"Your OTP is "+otp.getOtp()).create();	
				 return otp;				
			 }	 
	} 
}
