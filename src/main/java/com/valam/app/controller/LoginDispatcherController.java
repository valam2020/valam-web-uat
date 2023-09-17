package com.valam.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.dto.ResponseMessage;
import com.valam.app.model.LoginDispatchers;
import com.valam.app.service.LoginDispatcherService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/loginDispatcher")
public class LoginDispatcherController {
	
	@Autowired
    private LoginDispatcherService dispService;
	 
	 @ApiOperation(value = "api to add new login details")
	 @PostMapping("/add")
	 public ResponseMessage addlogin(@RequestBody LoginDispatchers loginDis) {
		 dispService.createLogin(loginDis.getEmail(),loginDis.getDeviceId());	
		 ResponseMessage message = new ResponseMessage();
		 message.setHttpStatus(200);
		 message.setMessage("updated Successfully");
		 return message;
	    }
	 
	 @ApiOperation(value = "api to update logout time")
	 @PostMapping("/update")
	 public ResponseMessage update(@RequestBody LoginDispatchers loginDis) {
		 dispService.updateLogoutTime(loginDis);	
		 ResponseMessage message = new ResponseMessage();
		 message.setHttpStatus(200);
		 message.setMessage("updated Successfully");
		 return message;
	    }
     
	 @ApiOperation(value = "api to update IsLoggedIn status")
	 @PostMapping("/logStatus")
	 public ResponseMessage updatelogOut(@RequestBody LoginDispatchers loginDis) {
		 dispService.updatelogoutStatus(loginDis.getEmail());	
		 ResponseMessage message = new ResponseMessage();
		 message.setHttpStatus(200);
		 message.setMessage("updated Successfully");
		 return message;
	    }
	 
	 
	 @ApiOperation(value = "api to find by device")
	 @PostMapping("/thinkSession")
	 public LoginDispatchers findByDevice(@RequestBody LoginDispatchers loginDis) {
		// LoginDispatchers disp = dispService.findByDevice(loginDis.getEmail(),loginDis.getDeviceId());	
		 return dispService.findByDevice(loginDis.getEmail(),loginDis.getDeviceId());	
	    }
	  
}
