package com.valam.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.dto.ResponseMessage;
import com.valam.app.dto.TimeSheetDto;
import com.valam.app.model.TimeSheet;
import com.valam.app.service.TimeSheetService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/timeSheet")
public class TimeSheetController {
	
	 @Autowired
	 private TimeSheetService timeSheetService;
	 
	 @ApiOperation(value = "api to add new car details")
	 @GetMapping("/{id}")
	 public Optional<TimeSheet> findById(@PathVariable Long id){
		 
		 return timeSheetService.getById(id);
	 }
	 
	 @ApiOperation(value = "api to find all timesheets entered by customer")
	 @GetMapping("/all")
	 public List<TimeSheet> findAll(){
		 return timeSheetService.getAll();
	 }
	 
	 @ApiOperation(value = "api to save the timesheet")
	 @PostMapping("/add")
	 public TimeSheet save(@RequestBody TimeSheetDto tsDto) {
		 return timeSheetService.save(tsDto);
	 }
	 
	 @ApiOperation(value = "api to update the timesheet")
	 @PostMapping("/update")
	 public TimeSheet updateTimeSheet(@RequestBody TimeSheetDto tsDto) {
		 return timeSheetService.update(tsDto);
	 }
	 
	 @ApiOperation(value = "api to update the timesheet")
	 @DeleteMapping("/delete/{id}")
	 public ResponseMessage deleteTimeSheet(@PathVariable Long id) {
		  timeSheetService.deleteTimeSheet(id);
		  ResponseMessage message = new ResponseMessage();
		  message.setHttpStatus(200);
		  message.setMessage("Successfully deleted !!!");
		  return message;
	 }
	 
	 
	 
	 
	 

}
