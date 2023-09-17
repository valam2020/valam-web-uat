package com.valam.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valam.app.model.OTPSystem;

@Repository
public interface MobileUserRepo extends JpaRepository<OTPSystem, Long>{
	  Optional<OTPSystem>findByMobileNumber(String mobileNumber);
		
	  OTPSystem existsByMobileNumber(String mobileNumber);

		
}