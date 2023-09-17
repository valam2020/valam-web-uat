package com.valam.app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDtoModel {


	private String email;
	private String password;
	private String otp;
	//private String loginId;
	//private String deviceId;
	
}
