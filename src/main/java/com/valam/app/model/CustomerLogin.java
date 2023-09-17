package com.valam.app.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="customerLogin" , uniqueConstraints = {
		@UniqueConstraint(columnNames ="customerId")
})
public class CustomerLogin {


	@Id
	@Column(name = "customerId")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long customerId;
	
	@Column(name = "userName")
	private @NonNull String userName;
	
	@Column(name = "empId")
	private String empId;
	
	@Column(nullable = true,name = "firstName")
	private String firstName;

	@Column(nullable = true,name = "lastName")
	private String lastName;
	
	@Column(nullable = true,name = "address")
	private String address;
	
	@Email
	@Column(name = "email")
    private @NonNull String email;
	
	@Column(nullable = true,name ="is_major")
	private boolean is_major;
	
	@Column(nullable = true)
	private Boolean emailVerified = true; 
	
	@Column(name = "phoneNumber")
	private @NonNull String phoneNumber;

	@JsonIgnore
	private String password;
	
	@Column(nullable = true,name ="role_id")
	private String role_id;
	
	@Column(nullable = true,name ="reason")
	private String reason;
	
	@Column(nullable = true,name ="is_active")
	private boolean is_active;
	
	@Column(name = "usercomments",length=600)
	private String usercomments;
	
 	@Column(name = "reviewbyadmin",length=600)
	private String reviewbyadmin;
	
 	@Column(name = "reviewbyexecutive",length=600)
	private String reviewbyexecutive;
 	
 	@Column(name ="is_reviewbyadmin")
	private boolean is_reviewbyadmin;
 	
 	@Column(name ="is_reviewbyexecutive")
	private boolean is_reviewbyexecutive;
 	
 	
}
