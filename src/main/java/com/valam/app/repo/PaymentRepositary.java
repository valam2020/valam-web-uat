package com.valam.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valam.app.model.PaymentDetails;

@Repository
public interface PaymentRepositary extends JpaRepository<PaymentDetails, Long>{
	
	

}
