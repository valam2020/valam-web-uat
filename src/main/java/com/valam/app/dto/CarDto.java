package com.valam.app.dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
	
	private Long carId;
	private Long dispatcherId; 
	private Long stsId;
	private String carModel;
	private String carColor;
	private String comfortLevel;
	private boolean is_driver_assigned;
	private String carRegisterId;
	private String imageUrl;
	private String carType;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private Long seatCapacity;
	/*DTO, which stands for Data Transfer Object, 
	is a design pattern conceived to reduce the number of calls when working with remote interfaces.the main reason 
	for using a Data Transfer Object is to batch up what would be multiple remote calls into a single one.*/
	
}