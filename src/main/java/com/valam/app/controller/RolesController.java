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
import com.valam.app.model.RolesTable;
import com.valam.app.service.RoleService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/roles")
public class RolesController {
	
	@Autowired
	private RoleService roleService;
	
	@ApiOperation(value = "api to fetch the all roles record")
	@GetMapping("/all")
	public List<RolesTable> findAll() {
		return roleService.getALLRoles();
	}
	
	@ApiOperation(value = "api to get current roles")
	@GetMapping("/{id}")
	public Optional<RolesTable> findbycustomerId(@PathVariable Long id) {
		return roleService.getById(id);
	}
	
	 @ApiOperation(value = "api to update the roles id")
	 @PostMapping("/update")
	 public RolesTable updateCustomerLogin(@RequestBody RolesTable rolesTable) {
		 return roleService.update(rolesTable);
	 }
	 
	 @ApiOperation(value = "api to create the roles id")
	 @PostMapping("/create")
	 public RolesTable createRole(@RequestBody RolesTable rolesTable) {
		 return roleService.saveRoles(rolesTable);
	 }
	 
	 
	 @ApiOperation(value = "api to delete the user Track Table Login")
	 @DeleteMapping("/delete/{id}")
	 public ResponseMessage deleteRole(@PathVariable Long id) {
		 roleService.deleteRole(id);
		  ResponseMessage message = new ResponseMessage();
		  message.setHttpStatus(200);
		  message.setMessage("Successfully deleted !!!");
		  return message;
	 }

}
