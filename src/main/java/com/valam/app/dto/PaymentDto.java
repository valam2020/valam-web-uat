package com.valam.app.dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {

	private Long paymentId;
	private float distance;
	private float price;
	private float totalAmount;
	private Long userId;
	private Long driverId;
	private Long carId;
	private String paymentType;
	private LocalDate createdDate;
	
}
