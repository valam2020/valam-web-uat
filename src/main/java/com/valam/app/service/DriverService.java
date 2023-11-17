package com.valam.app.service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.valam.app.dto.DriverDto;
import com.valam.app.dto.DriverDtoModel;
import com.valam.app.dto.ResponseMessage;
import com.valam.app.model.Dispatcher;
import com.valam.app.model.Driver;
import com.valam.app.model.RideStatus;
import com.valam.app.repo.DispatcherRepositary;
import com.valam.app.repo.DriverRepositary;
import com.valam.app.repo.StatusRepositary;

@Service
public class DriverService {
    
	@Autowired
	private DriverRepositary driverrepo;
	
	@Autowired
	private  StatusRepositary statusRepo;
	
	//@Autowired
	//private CarRepositary carRepo;
	
	 @Autowired
	    private CommonApiTokenService capiTokenService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
		
	@Autowired
	private DispatcherRepositary dispatcherRepo;
	
	// to save driver records with dispatcher and status
	
	public Driver saveDriver(DriverDto driver) {
		
		Dispatcher dispatcher = dispatcherRepo.findById(driver.getDispatcherId()).get();
		dispatcher.setId(dispatcher.getId());
		
		RideStatus status = statusRepo.findById(driver.getStsId()).get();
		status.setStsId(status.getStsId());
		
		//CarDetails car = null;
		//		if(driver != null  && driver.getCarId() !=null) {
		//		carRepo.findById(driver.getCarId()).get();
		//         car.setCarId(car.getCarId());}
		
		
		Driver driverd = new Driver();
		driverd.setDispatcher(dispatcher);
		//driverd.setCarDetails(car);
		driverd.setRideStatus(status);
		driverd.setFirstName(driver.getFirstName());
		driverd.setLastName(driver.getLastName());
		driverd.setDriver_status(driver.getDriver_status());
		driverd.set_car_assigned(driver.is_car_assigned());
		driverd.setEmail(driver.getEmail());
		driverd.setPhNum(driver.getPhNum());
		driverd.setDlNum(driver.getDlNum());
		driverd.setAddress(driver.getAddress());
		driverd.setImageUrl(driver.getImageUrl());
		driverd.setDistance(driver.getDistance());
		driverd.setPassword(driver.getPassword());
		driverd.setCurrent_lat(driver.getCurrent_lat());
		driverd.setCurrent_lng(driver.getCurrent_lng());
		driverd.setCarNo(driver.getCarNo());
		driverd.setDateOfBirth(driver.getDateOfBirth());
		driverd.setCreatedDate(LocalDate.now());
		driverd.setPassword(passwordEncoder.encode(driver.getPassword()));
		driverd.setDlbackimage(driver.getDlbackImage());
		driverd.setDlfrontimage(driver.getDlfrontimage());
		driverd.setToken(driver.getToken());		
		return driverrepo.save(driverd);
	}
    
	// to save all driver records with dispatcher and status
	public List<Driver> saveDrivers(List<Driver> drivers){
		return driverrepo.saveAll(drivers);
	}
	
	// to fetch all driver records 
	public List<Driver> getDrivers(){
		return driverrepo.findByALLDrivers();
	}
	
	public List<Driver> getAllDrivers() {
		return driverrepo.findAll();
	}
	
	// to fetch driver records by driver id
	public Driver getDriverByID(Long id) {
		return driverrepo.findById(id).orElse(null);
	}
	
	
	// to fetch driver record under the given dispatcher 
	public List<Driver> getDriverByDisId(Long disId) {
		return driverrepo.findByDisId(disId);
	} 
	
	// to delete driver record from Database
	public ResponseMessage deleteDriver(String commonToken,Long id) {
		ResponseMessage message = new ResponseMessage();
		
		if(capiTokenService.getByTokenId(commonToken).getAuth_common_id() !=null) {
			Driver driver = driverrepo.findById(id).get();
			if(driver.getDriver_status() == "no_ride" && driver.is_car_assigned()) { 
				driverrepo.deleteByDriverId(id);
				message.setHttpStatus(200);
				message.setMessage("Successfully Deleted" +id);
			}else {
				message.setHttpStatus(400);
				message.setMessage(" driver is on ride can't delete it now" +id);
			}
		}
		return message;
	}
	
	//to update the driver record by id 
	public Driver updateDriver(DriverDto driver) { 
		
		RideStatus rideSts = null;
        if(driver != null && driver.getStsId() != null) {
			rideSts = statusRepo.findById(driver.getStsId()).get();
		rideSts.setStsId(rideSts.getStsId());}

        Dispatcher dispatcher = null;
        if(driver != null && driver.getDispatcherId() != null) {
        	dispatcher = dispatcherRepo.findById(driver.getDispatcherId()).get();
		  dispatcher.setId(dispatcher.getId());}
		
		Driver existingDriver= driverrepo.findById(driver.getId()).orElse(null);
		existingDriver.setDispatcher(dispatcher);
		existingDriver.setRideStatus(rideSts);
		existingDriver.setCurrent_lat(driver.getCurrent_lat());
		existingDriver.setCurrent_lng(driver.getCurrent_lng());
		existingDriver.setHeader(driver.getHeader());
		if(driver.getEmail() == null) {
			 existingDriver.setEmail(existingDriver.getEmail());
		}else {
		existingDriver.setEmail(driver.getEmail());		
		}
        if(driver.getAddress() == null) {
			 existingDriver.setAddress(existingDriver.getAddress());
		 }
		 else {
			 existingDriver.setAddress(driver.getAddress());
		 }
		if(driver.getDlNum() == null) {
			existingDriver.setDlNum(existingDriver.getDlNum());
		}else {
			existingDriver.setDlNum(driver.getDlNum());
		}
		
		if(driver.getDlbackImage() == null) {
			existingDriver.setDlbackimage(existingDriver.getDlbackimage());
		}else {
			existingDriver.setDlbackimage(driver.getDlbackImage());
		}
		
		if(driver.getDlfrontimage() == null) {
			existingDriver.setDlfrontimage(existingDriver.getDlfrontimage());
		}else {
			existingDriver.setDlfrontimage(driver.getDlfrontimage());
		}
		
		if(driver.getFirstName() == null) {
			existingDriver.setFirstName(existingDriver.getFirstName());
		}else {
			existingDriver.setFirstName(driver.getFirstName());
		}
		if(driver.getLastName() == null) {
			existingDriver.setLastName(existingDriver.getLastName());
		}else {
			existingDriver.setLastName(driver.getLastName());
		}
		
		if(driver.getPhNum() == null) {
			existingDriver.setPhNum(existingDriver.getPhNum());
		}else {
			existingDriver.setPhNum(driver.getPhNum());
		}
		if(driver.getDriver_status() == null) {
			existingDriver.setDriver_status(existingDriver.getDriver_status());
		}
		else {
			existingDriver.setDriver_status(driver.getDriver_status());
		}
		
		if(driver.getDateOfBirth() == null) {
			existingDriver.setDateOfBirth(existingDriver.getDateOfBirth());
		}else {
			existingDriver.setDateOfBirth(driver.getDateOfBirth());
		}
		existingDriver.setCarNo(existingDriver.getCarNo());
		existingDriver.set_car_assigned(existingDriver.is_car_assigned());
		existingDriver.setModifiedDate(driver.getModifiedDate());
		return driverrepo.save(existingDriver);
	}
	
	//to get nearest drivers based on user latitude and longitude using great circle diagram.
	public List<Driver> getNearestDriver(BigDecimal latitude,BigDecimal longitude, Long dispId) {
		List<Driver> driver = driverrepo.findDistance(latitude,longitude,dispId);
		
		return driver;
		
		
	}
	
   public Driver getByEmailId(DriverDtoModel driver){
		
		String driverPass = driverrepo.findByEmail(driver.getEmail()).getPassword();
		System.out.println(driverPass);
		boolean isPasswordMatch = passwordEncoder.matches(driver.getPassword(),driverPass);
		System.out.println(isPasswordMatch);
		if(isPasswordMatch == true) {
			return driverrepo.findByEmail(driver.getEmail());
		}
		else 
		   return null;
	}
	
   public Driver resetPassword(DriverDtoModel driver) {
		
		if(driverrepo.existsByEmail(driver.getEmail())) {
			Long id = driverrepo.findByEmail(driver.getEmail()).getId();
			Driver driv = driverrepo.findById(id).get();
			if(driv.getOtp().equals(driver.getOtp())){
				driv.setPassword(passwordEncoder.encode(driver.getPassword()));
				return driverrepo.save(driv);
			}else {
				driv.setPassword(passwordEncoder.encode(driver.getPassword()));
				return driverrepo.save(driv);
			}
		}
		else 
			return null;
		
	}
   
   public DriverDtoModel getByEmail(DriverDto driver) {
	   Driver driverbyEMail = driverrepo.findByEmail(driver.getEmail());
	   DriverDtoModel driver_new = new DriverDtoModel();
	   driver_new.setOtp(driverbyEMail.getOtp());
	   driver_new.setEmail(driverbyEMail.getEmail());
	   return driver_new;
   }
	
	public Driver isDriverExist(String email,String PhNum,String dlNum) {
		return driverrepo.findByExist(email,PhNum,dlNum);
	}
	
	public Driver isDriverAvailable(Long id) {
		return driverrepo.getAvailableDrivers(id);
	}
	
	public List<Driver> findAllAvailableDrivers(){
		return driverrepo.getAllAvailableDrivers();
	}
	
	public void updateCarAssignedStatus(Long carId,String car_no,Long id) {
		driverrepo.updateCarAssigned(carId,car_no,id);
		
	}
	
	public Optional<Driver> updateLatLngDriver(Driver driver) {
		driverrepo.updatedriverLatLng(driver.getCurrent_lat(), driver.getCurrent_lng(), driver.getId());
		return driverrepo.findById(driver.getId());
	}
	
	public void updateDispatcherId(Long dispatcherId,Long driverId) {
		driverrepo.updateDispatcherId(dispatcherId,driverId);
		return ;
	}
	
	public void updateByStatusbydriver(Long statusId,Long driverId) {
		driverrepo.updateByStatusbydriver(statusId,driverId);
	}

}

