package com.valam.app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLoginDto {
	
	private Long customerId;
	private String userName;
	private String firstName;
	private String lastName;
	private String address;
	private  String email;
	private  String phoneNumber;
	private String password;
	private String role_id;
	private String reason;
	private String roleCode;
	private String auth_common_id;

}
