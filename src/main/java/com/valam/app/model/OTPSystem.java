package com.valam.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="otp_system", uniqueConstraints = {
		@UniqueConstraint(columnNames ="mobilenumber")
})
//Otp Entity to send the otp to mobile number
public class OTPSystem { 
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	private @NonNull String mobileNumber;
	private String otp;
    private long expiryTimer;
	private boolean isSignUp;
	
	public boolean isSignUp() {
		return isSignUp;
	}
}
