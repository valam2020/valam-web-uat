package com.valam.app.dto;

import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDtoModel2 {
	
	private BigDecimal latitude;
	private BigDecimal longitude;
	private Long dispatcherId;
	private String comfort_level;
	
}
