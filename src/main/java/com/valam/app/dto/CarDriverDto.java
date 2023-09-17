package com.valam.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDriverDto {
	
	private Long carId;
	private Long dispatcherId; 
	//private Long stsId;
	private String carModel;
	private String carColor;
	private String comfortLevel;
	private Long seatCapacity;
	private Long driverid;
	private String firstName;
	private String lastName;
	private String email;
	private String phNum;
	private BigDecimal current_lat;
	private BigDecimal current_lng;

}
