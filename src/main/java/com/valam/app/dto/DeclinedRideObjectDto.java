package com.valam.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeclinedRideObjectDto {
	
	private Long ride_id;
	private String From_Address;
	private String To_Address;
	private String message;
	private Long driverId;
	private Long carId;
	private Long dispatcherId;
	private String comfortLevel;
	private User_DtoObject userObject;
	private Long sts_id;
	private String status_name;

}
