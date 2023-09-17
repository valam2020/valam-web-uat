package com.valam.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideHistoryDto {
	
	private Long userId;
	private Long rideId;
	private Long driverId; 
	private Long carId;
	private Long dispatcherId;
	private String fromAddress;
	private String toAddress;
    private String paymentType;
	private Float paymentTotal;
	private Float distance;
	private Long stsId;
	private long expiryTimer;
	private String otp;
	private String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate pickupDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dropDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	private LocalDateTime pickupTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
	private LocalDateTime dropTime;
	private BigDecimal pickupLat;
	private BigDecimal  pickupLng;
	private BigDecimal dropLat;
	private BigDecimal dropLng;
	private String comfort_level;
	
	/*DTO, which stands for Data Transfer Object, 
	is a design pattern conceived to reduce the number of calls when working with remote interfaces.the main reason 
	for using a Data Transfer Object is to batch up what would be multiple remote calls into a single one.*/
	
}
