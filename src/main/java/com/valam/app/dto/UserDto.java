package com.valam.app.dto;



import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  
	//private boolean is_existed;
	private String email;
	private String phNum;
	private long httpStatus;
	private String message;
    private boolean isExist;
	private long id;
	private String name;
	private String firstName;
	private String lastName;
	private String middleName;
	private String social_signup_id;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private String imageUrl;
	private String token;
	private String common_token_id;
	//private Long loginId;
	//private String deviceId;
}
