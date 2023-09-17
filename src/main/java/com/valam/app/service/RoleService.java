package com.valam.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valam.app.model.RolesTable;
import com.valam.app.repo.RoleRepo;

@Service
public class RoleService {
	

	
	@Autowired
	private RoleRepo roleRepo;
	
	
	public List<RolesTable> getALLRoles() {
		return roleRepo.findAll();
	}
	
	public Optional<RolesTable> getById(Long id) {
		return roleRepo.findById(id);
	}
	
	public RolesTable saveRoles(RolesTable roles) {
		return roleRepo.save(roles);
	}
	
	public RolesTable update(RolesTable roles) {
		RolesTable role = roleRepo.findById(roles.getRoleId()).get();
		role.setRoleName(roles.getRoleName());
		role.setRoleDescription(roles.getRoleDescription());
		role.setModifiedByUser(roles.getModifiedByUser());
		role.setCreatedByUser(role.getCreatedByUser());
		role.setCreatedDate(role.getCreatedDate());
		role.setModifiedDate(roles.getModifiedDate());
		return roleRepo.save(role);
	}
	
	
	public void deleteRole(long id) {
		roleRepo.deleteById(id); 
	}
	

}
