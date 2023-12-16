package com.valam.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDispatcherDto {
	
	private Long dispatcherId;
	private String lastName;
	private String firstName;
	private String email;
	private String phNum;
	private String address;
	private String password;
	private String disRegId;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private String imageUrl;
	private String pincode;
	private LocalDate acceptedDate;
	private Boolean isAccepted;

}
