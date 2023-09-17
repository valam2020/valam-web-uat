package com.valam.app.service;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valam.app.dto.PaymentDto;
import com.valam.app.model.CarDetails;
import com.valam.app.model.Driver;
import com.valam.app.model.PaymentDetails;
import com.valam.app.model.User;
import com.valam.app.repo.CarRepositary;
import com.valam.app.repo.DriverRepositary;
import com.valam.app.repo.PaymentRepositary;
import com.valam.app.repo.UserRepository;

@Service
public class PaymentService {
	
	@Autowired
	private CarRepositary carRepo; 
	
	@Autowired
	private DriverRepositary driverRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PaymentRepositary paymentrepo;
	
	
	public PaymentDetails savePayment(PaymentDto paymentDto) {
		
		Driver driver = driverRepo.findById(paymentDto.getDriverId()).get();
		driver.setId(driver.getId());
		
		CarDetails car = carRepo.findById(paymentDto.getCarId()).get();
		car.setCarId(car.getCarId());
		
		
		User user = userRepo.findById(paymentDto.getUserId()).get(); 
    	user.setId(user.getId());
    	    	
    	PaymentDetails payment = new PaymentDetails();
    	payment.setCarDetails(car);
    	payment.setDriver(driver);
    	payment.setUser(user);
    	payment.setDistance(paymentDto.getDistance());
    	payment.setPaymentType(paymentDto.getPaymentType());
    	payment.setPrice(paymentDto.getPrice());
        payment.setTotalAmount(paymentDto.getTotalAmount());    	
    	
    	return paymentrepo.save(payment);
		
	}
	
	//to fetch all PaymentDetails
    public List<PaymentDetails> getPayment(){
	           return paymentrepo.findAll();
    }
    
    //to fetch the PaymentDetails by Id
    public PaymentDetails getpaymentByID(Long id) {
	    return paymentrepo.findById(id).orElse(null);
    } 
    
    
    
    public float getTotalPayment(float distance) {
    	
    	 float price = (float) 4.50;
    	 DecimalFormat decimalFormat = new DecimalFormat("#.##");
    	 float total_amount = 0;
    	 if(distance <= 1 ) {
    		 
    		// total_amount = price;
    		 total_amount=  Float.valueOf(decimalFormat.format(total_amount));
    	 }else {
    		 
    	  total_amount = (float) ((float) price + (distance - 1) * 1.75) ;
    	  total_amount=  Float.valueOf(decimalFormat.format(total_amount));
    	 }
		 return total_amount; 
		 
		 
		// DecimalFormat decimalFormat = new DecimalFormat("#.##");
		 //float twoDigitsF = Float.valueOf(decimalFormat.format(f));
    }
}
