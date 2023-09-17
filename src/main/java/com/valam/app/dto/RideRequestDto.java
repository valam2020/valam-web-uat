package com.valam.app.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto { 
	
	private Long ride_loc_id;
	private Long stsId;
    private LocalDateTime rideDate;
    private Long dispatcherId;
	private Long ride_id;
	private Long driverId;
	/*DTO, which stands for Data Transfer Object, 
	is a design pattern conceived to reduce the number of calls when working with remote interfaces.the main reason 
	for using a Data Transfer Object is to batch up what would be multiple remote calls into a single one.*/

}
