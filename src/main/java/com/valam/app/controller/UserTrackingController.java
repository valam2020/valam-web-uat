package com.valam.app.controller;

import java.util.List;
import java.util.Optional;

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
import com.valam.app.dto.UserTTDTO;
import com.valam.app.model.UserTimeTrackTable;
import com.valam.app.service.UserTimeTrackService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usertrack")
public class UserTrackingController {
	
	@Autowired
	private UserTimeTrackService uttService;
	
	@ApiOperation(value = "api to fetch the all users record")
	@GetMapping("/all")
	public List<UserTimeTrackTable> findAll() {
		return uttService.getAllTracks();
	}
	
	@ApiOperation(value = "api to get current user")
	@GetMapping("/{id}")
	public Optional<UserTimeTrackTable> findbycustomerId(@PathVariable Long id) {
		return uttService.findById(id);
	}
	
	 @ApiOperation(value = "api to update the UserTimeTrackTable id")
	 @PostMapping("/update")
	 public UserTTDTO updateCustomerLogin(@RequestBody UserTTDTO rolesTable) {
		 return uttService.updateLogoutTime(rolesTable);
	 }
	 
	 @ApiOperation(value = "api to Create the UserTimeTrackTable id")
	 @PostMapping("/create")
	 public UserTimeTrackTable createRole(@RequestBody UserTTDTO rolesTable) {
		 return uttService.saveTrack(rolesTable);
	 }
	 
	 @ApiOperation(value = "api to delete the user Track Table Login")
	 @DeleteMapping("/delete/{id}")
	 public ResponseMessage delete(@PathVariable Long id) {
		 uttService.deleteTrack(id);
		  ResponseMessage message = new ResponseMessage();
		  message.setHttpStatus(200);
		  message.setMessage("Successfully deleted !!!");
		  return message;
	 }
	
	

}
