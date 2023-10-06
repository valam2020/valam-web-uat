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


import com.valam.app.dto.ResponseMessage;
import com.valam.app.model.CustomerCommentTable;
import com.valam.app.service.CustCommentService;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cust_comment")
public class CustCommentController {
	
	@Autowired
	private CustCommentService custCommentService;
	
	@ApiOperation(value = "api to get comments ")
	@GetMapping("/all")
	public List<CustomerCommentTable> findComments(){
		return custCommentService.findAll();
	}
	
	@ApiOperation(value = "api to get current user")
	@GetMapping("/{id}")
	public Optional<CustomerCommentTable> findbycommentId(@PathVariable Long id) {
		return custCommentService.findById(id);
	}
	
	@ApiOperation(value = "api to add new comment ")
	@PostMapping("/add")
	public CustomerCommentTable createCustComment(@Valid @RequestBody CustomerCommentTable customer) {
		return custCommentService.saveComment(customer);
	}
	
	@ApiOperation(value = "api to add update comment ")
	@PostMapping("/update")
	public CustomerCommentTable updateCustComment(@Valid @RequestBody CustomerCommentTable customer) {
		return custCommentService.updateComment(customer);
	}
	
	
	@ApiOperation(value = "api to get current user")
	@GetMapping("/customer/{id}")
	public CustomerCommentTable findbycustId(@PathVariable Long id) {
		return custCommentService.getByCustomer(id);
	}
	
	@ApiOperation(value = "api to get by rideId")
	@GetMapping("/ride/{id}")
	public List<CustomerCommentTable> findbyRideId(@PathVariable Long id) {
		return custCommentService.getByRideId(id);
	}
	
	 @ApiOperation(value = "api to delete the customer Login")
	 @DeleteMapping("/delete/{id}")
	 public ResponseMessage delete(@PathVariable Long id) {
		 custCommentService.deleteCustomerComment(id);
		  ResponseMessage message = new ResponseMessage();
		  message.setHttpStatus(200);
		  message.setMessage("Successfully deleted !!!");
		  return message;
	 }
	
	

}
