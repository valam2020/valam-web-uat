package com.valam.app.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.valam.app.service.OTPService;
import com.twilio.Twilio;
import com.valam.app.model.OTPSystem;
import com.valam.app.repo.MobileUserRepo;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/otp")
public class OTPSystemController {

    @Autowired
    private MobileUserRepo mobileUserRepo;

    @Autowired
    OTPService oTPService;


    // twilio account sid and auth sid to access the account
    private final static String ACCOUNT_SID = "AC6280b24ba5328f9cc721368ae9e78ae3";
    private final static String AUTH_ID = "723098423a363af765934844a39211c3";
    public String mobileNumber;

    static {
        Twilio.init(ACCOUNT_SID, AUTH_ID);
    }

    @ApiOperation(value = "api to send the otp to given mobile number")
    @PostMapping("/verify")
    public ResponseEntity<Object> sendOTP(@RequestBody OTPSystem otpmobilenum) {
        System.out.println("In updateRecord");
        OTPSystem otpSystem = oTPService.upDateOTPMobile(otpmobilenum);
        if (otpSystem != null) {
            mobileNumber = otpSystem.getMobileNumber();
            return new ResponseEntity<>("OTP is send successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("OTP is not successfully", HttpStatus.OK);
        }
    }

    @ApiOperation(value = "api to verify the otp sent mobile number.once verified user will access the next page")
    @SuppressWarnings("unused")
    @PutMapping(value = "/verify")
    public ResponseEntity<Object> verifyOTP(@RequestBody OTPSystem requestBodyOTPSystem) {

        mobileNumber = requestBodyOTPSystem.getMobileNumber();
        OTPSystem otpSys = mobileUserRepo.findByMobileNumber(mobileNumber).get();

        if (requestBodyOTPSystem.getOtp() == null || requestBodyOTPSystem.getOtp().trim().length() <= 0) {
            return new ResponseEntity<>("Please provide OTP", HttpStatus.BAD_REQUEST);
        }

        if (requestBodyOTPSystem.getMobileNumber().equals(otpSys.getMobileNumber())) {
            OTPSystem otpSystem = otpSys;

            if (otpSystem != null) {
                if (otpSystem.getExpiryTimer() >= System.currentTimeMillis()) {

                    if (requestBodyOTPSystem.getOtp().equals(otpSystem.getOtp())) {

                        OTPSystem result = mobileUserRepo.findByMobileNumber(mobileNumber).get();
                        result.setMobileNumber(mobileNumber);
                        result.setOtp(String.valueOf(((int) (Math.random() * (10000 - 1000))) + 1000));
                        result.setExpiryTimer(System.currentTimeMillis() + 60000);
                        mobileUserRepo.save(result);

                        return new ResponseEntity<>("OTP is verified successfully", HttpStatus.OK);

                    }
                    return new ResponseEntity<>("Invaild OTP", HttpStatus.BAD_REQUEST);

                }
                return new ResponseEntity<>("OTP is expired", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Something went wrong..!!!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Mobile number not found", HttpStatus.NOT_FOUND);
    }

}
