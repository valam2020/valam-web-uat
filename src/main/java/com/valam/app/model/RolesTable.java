package com.valam.app.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="rolesTable" , uniqueConstraints = {
		@UniqueConstraint(columnNames ="roleId"),@UniqueConstraint(columnNames ="roleName"),
		@UniqueConstraint(columnNames ="roleCode")
})
public class RolesTable {
	
	@Id
	@Column(name = "roleId")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long roleId;
	
	@Column(name = "roleName")
	private @NonNull String roleName;
	
	@Column(name="roleDescription",length=600)
	private String roleDescription;
	
	@Column(name = "createdDate")
	private LocalDate createdDate;
	
 	@Column(name = "modifiedDate")
	private LocalDate modifiedDate;
 	
 	@Column(name = "createdByUser")
	private Long createdByUser;
	
 	@Column(name = "modifiedByUser")
	private Long modifiedByUser;
	
	@Column(name = "roleCode")
	private @NonNull String roleCode;

	
	

}
