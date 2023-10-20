package com.valam.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.dto.CustomerLoginDto;
import com.valam.app.dto.ResponseMessage;
import com.valam.app.exception.BadRequestException;
import com.valam.app.model.CustomerLogin;
import com.valam.app.repo.CustomerLoginRepo;
import com.valam.app.service.CustomerLoginService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/customer_login")
public class CustomerLoginController {

	@Autowired
	private CustomerLoginService customerloginService;
	
	@Autowired
	private CustomerLoginRepo customerLoginRepo;

	@ApiOperation(value = "api to fetch the all users record")
	@GetMapping("/all")
	public List<CustomerLogin> findAll() {
		return customerloginService.findAll();
	}

	@ApiOperation(value = "api to user signup with valid details.")
	@PostMapping("/signup")
	public CustomerLogin createCustomer(@Valid @RequestBody CustomerLoginDto customer) {
		return customerloginService.saveCustomerLogin(customer);
	}

	@ApiOperation(value = "api to get current user")
	@GetMapping("/customer/{id}")
	public Optional<CustomerLogin> findbycustomerId(@PathVariable Long id) {
		return customerloginService.findById(id);
	}
       
	@ApiOperation(value = "api to get current user")
	@GetMapping("/empId/{id}")
	public CustomerLogin findbycustomerId(@PathVariable String id) {
		return customerloginService.findByEmpId(id);
	}
	
	@ApiOperation(value = "api to verify existing user by phone number or e-mail")
	@PostMapping("/login")
	public CustomerLoginDto login(@Valid @RequestBody CustomerLoginDto customer) {
		 if (customerLoginRepo.existsByEmail(customer.getEmail()) == false) {
	            throw new
	                    BadRequestException("Not Yet Registered");
	        }
		 CustomerLoginDto customerlogin = customerloginService.getByEmailId(customer);
		 if(customerlogin != null) {
			 return  customerlogin;
		 }else {
			 return null ;
		 }
	}

	@ApiOperation(value = "api to change driver password")
	@PostMapping("/forgot-password")
	public CustomerLogin forgetPassword(@RequestBody CustomerLoginDto customer) {
		return customerloginService.resetPassword(customer);
	}
	
	 @ApiOperation(value = "api to update the dispatcher id")
	 @PostMapping("/update")
	 public CustomerLogin updateCustomerLogin(@RequestBody CustomerLoginDto customer) {
		 return customerloginService.updateCustomerLogin(customer);
	 }
	 
	 @ApiOperation(value = "api to delete the customer Login")
	 @DeleteMapping("/delete/{id}")
	 public ResponseMessage delete(@PathVariable Long id) {
		 customerloginService.deleteCustomerLogin(id);
		  ResponseMessage message = new ResponseMessage();
		  message.setHttpStatus(200);
		  message.setMessage("Successfully deleted !!!");
		  return message;
	 }

}
