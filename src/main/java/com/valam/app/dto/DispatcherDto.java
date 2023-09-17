package com.valam.app.dto;



import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DispatcherDto {
    
	private Long dispatcherId;
	private String lastName;
	private String email;
	private String phNum;
	private String address;
	private String disRegId;
	private String firstName;
	private String imageUrl;
	private String password;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private String pincode;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private String token;
	private String auth_common_id;
	private Long StsId; 
	private String message;
	
}
