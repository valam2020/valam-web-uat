package com.valam.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.dto.ResponseMessage;
import com.valam.app.model.DriverLogin;
import com.valam.app.model.LoginUsers;
import com.valam.app.service.LoginUsersService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/loginUser")
public class LoginUsersController {

	@Autowired
    private LoginUsersService loginService;
	
	 @ApiOperation(value = "api to add new login details")
	 @PostMapping("/add")
	 public ResponseMessage addlogin(@RequestBody LoginUsers loginDis) {
		 loginService.createLogin(loginDis);	
		 ResponseMessage message = new ResponseMessage();
		 message.setHttpStatus(200);
		 message.setMessage("updated Successfully");
		 return message;
	    }
	 
	 @ApiOperation(value = "api to update logout time required ")
	 @PostMapping("/update")
	 public ResponseMessage update(@RequestBody LoginUsers loginDis) {
		 loginService.updateLogoutTime(loginDis);	
		 ResponseMessage message = new ResponseMessage();
		 message.setHttpStatus(200);
		 message.setMessage("updated Successfully");
		 return message;
	    }
	 
	 
	 @ApiOperation(value = "api to find by device")
	 @PostMapping("/findByDevice")
	 public LoginUsers getByDevice(@RequestBody LoginUsers loginDis) {
		 return loginService.findByDevice(loginDis);
	    }

}
