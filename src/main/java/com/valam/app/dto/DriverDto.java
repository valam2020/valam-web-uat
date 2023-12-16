package com.valam.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto { 
	
	private Long id;
	private Long driverId;
	private Long stsId;
	private Long dispatcherId;
	private String firstName;
	private String lastName;
	private String email;
	private String phNum;
	private String dlNum;
	private String driver_status;
	private boolean is_car_assigned;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private String address;
	private String imageUrl;
	private String password;
	private BigDecimal current_lat;
	private BigDecimal current_lng;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private BigDecimal distance;
	private LocalDate dateOfBirth;
	private String carNo;
	private String httpStatus;
	private String message;
	private Long header;
	private String token;
	private String auth_common_id;
	private String dlfrontimage;
	private String dlbackImage;
    private Boolean isAccepted;
	/*DTO, which stands for Data Transfer Object, 
	is a design pattern conceived to reduce the number of calls when working with remote interfaces.the main reason 
	for using a Data Transfer Object is to batch up what would be multiple remote calls into a single one.*/
}
