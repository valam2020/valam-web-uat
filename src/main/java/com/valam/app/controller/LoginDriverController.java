package com.valam.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.dto.ResponseMessage;
import com.valam.app.model.DriverLogin;
import com.valam.app.model.LoginDispatchers;
import com.valam.app.service.DriverLoginService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/logindriver")
public class LoginDriverController {
	
	@Autowired
    private DriverLoginService dispService;
	 
	 @ApiOperation(value = "api to add new login details")
	 @PostMapping("/add")
	 public ResponseMessage addlogin(@RequestBody DriverLogin loginDis) {
		 dispService.createLogin(loginDis.getEmail(),loginDis.getDeviceId());	
		 ResponseMessage message = new ResponseMessage();
		 message.setHttpStatus(200);
		 message.setMessage("updated Successfully");
		 return message;
	    }
	 
	 @ApiOperation(value = "api to update logout time")
	 @PostMapping("/update")
	 public ResponseMessage update(@RequestBody DriverLogin loginDis) {
		 dispService.updateLogoutTime(loginDis);	
		 ResponseMessage message = new ResponseMessage();
		 message.setHttpStatus(200);
		 message.setMessage("updated Successfully");
		 return message;
	    }
	 
	 @ApiOperation(value = "api to find by device")
	 @PostMapping("/device")
	 public DriverLogin findByDevice(@RequestBody DriverLogin loginDis) {
		// LoginDispatchers disp = dispService.findByDevice(loginDis.getEmail(),loginDis.getDeviceId());	
		 return dispService.findByDevice(loginDis);	
	    }

}
