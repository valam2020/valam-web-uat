package com.valam.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DispatcherLoginDto {

	private String email;
	private String password;
	private String message;
	private long httpStatus;
	private String loginId;
	private String deviceId;
}
