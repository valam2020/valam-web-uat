package com.valam.app.payload;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
//signup request to map the user info
public class SignUpRequest { 
	
	private  String name;
	private  String firstName;
	private  String lastName;
	private  String middleName;
	private  Boolean isAccepted;
	private  String email;
	private  String password;
	private  String phNum;
	private boolean is_major;
	@Column(columnDefinition = "DECIMAL(10,8)", name = "latitude")
	private BigDecimal latitude;
	@Column(columnDefinition = "DECIMAL(11,8)", name = "longitude")
	private BigDecimal  longitude; 

}
