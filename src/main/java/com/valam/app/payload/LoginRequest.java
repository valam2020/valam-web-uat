package com.valam.app.payload;

import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    
	// login request to email and password
	@Email
	private @NonNull String email;
	
	private @NonNull String password;
	
}
