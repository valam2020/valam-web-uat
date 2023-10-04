package com.valam.app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User_DtoObject {
	
	private String email;
	private String phNum;
	private long user_Id;
	private String name;

}
