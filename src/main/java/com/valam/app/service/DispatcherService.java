package com.valam.app.service;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.valam.app.dto.DispatcherDto;
import com.valam.app.dto.DispatcherDto_1;
import com.valam.app.model.CommonAPIToken;
import com.valam.app.model.Dispatcher;

import com.valam.app.repo.DispatcherRepositary;


@Service
public class DispatcherService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 @Autowired
	    private JWTTokenCreator jwtCreator;
	 
	 @Autowired
	 private CommonApiTokenService tokenService;
		
	@Autowired
	private DispatcherRepositary dispatcherRepo;
	
	// to fetch dispatcher by given dispatcher id
	public Dispatcher getDispatcherByID(Long id) {
		return dispatcherRepo.findById(id).orElse(null);
	} 
	
	//to delete a dispatcher by given id
	public DispatcherDto_1 deleteDispatcher(Long id) {
		Dispatcher dispatcher = dispatcherRepo.findById(id).orElse(null);
		dispatcherRepo.deleteDispatcherById(id);
		DispatcherDto_1 message = new DispatcherDto_1();
		if(dispatcher != null) {
			message.setHttpStatus(200);
			message.setMessage("Successfully removed"+id);
		}
		else {
			message.setHttpStatus(400);
			message.setMessage("Not found");
		}
		return message;
	}
	
	public List<Dispatcher> getAll(){
		return dispatcherRepo.findAll();
	}
	
	// to save the record of Dispatcher with diver and car
    public Dispatcher saveDispatcher(DispatcherDto dispatcherDto) { 
		
		//Driver driver = driverRepo.findById(dispatcherDto.getDriverId()).get(); 
		//driver.setId(driver.getId());
		
		//CarDetails carDetails = carRepo.findById(dispatcherDto.getCarId()).get();
		//carDetails.setCarId(carDetails.getCarId());
			
		Dispatcher dispatcher = new Dispatcher();
		dispatcher.setLastName(dispatcherDto.getLastName());
		dispatcher.setFirstName(dispatcherDto.getFirstName());
		dispatcher.setEmail(dispatcherDto.getEmail());
		dispatcher.setPhNum(dispatcherDto.getPhNum());
		dispatcher.setAddress(dispatcherDto.getAddress());
		dispatcher.setPassword(dispatcherDto.getPassword());
		dispatcher.setDisRegId(dispatcherDto.getDisRegId());
		dispatcher.setCreatedDate(LocalDate.now());
		dispatcher.setLatitude(dispatcherDto.getLatitude());
		dispatcher.setLongitude(dispatcherDto.getLongitude());
		dispatcher.setPincode(dispatcherDto.getPincode());
		dispatcher.setImageUrl(dispatcher.getImageUrl());
		dispatcher.setPassword(dispatcher.getPassword());
		dispatcher.setDeleted(false);
		dispatcher.setPassword(passwordEncoder.encode(dispatcher.getPassword()));
		return dispatcherRepo.save(dispatcher);
	}
    
    public DispatcherDto getByEmailId(DispatcherDto_1 dispatcher){
    	
    	CommonAPIToken apiToken = new CommonAPIToken();
    	Dispatcher disptcr = dispatcherRepo.findByEmail(dispatcher.getEmail());
    	String dispatcher1 = disptcr.getPassword();
    	System.out.println(dispatcher1);
    	DispatcherDto dispatcherDto = new DispatcherDto();
    	boolean isPasswordMatch = passwordEncoder.matches(dispatcher.getPassword(), dispatcher1);
    	System.out.println("Password : " + dispatcher.getPassword() + "   isPasswordMatch    : " + isPasswordMatch);
        if(isPasswordMatch == true) {
        	String token = jwtCreator.getJWTToken(dispatcher.getEmail());
        	dispatcherRepo.updateToken(token, dispatcher.getEmail());
        	apiToken.setAuth_common_id("Dispatcher"+"-"+LocalDate.now()+"-"+String.valueOf(((int) (Math.random() * (10000 - 1000))) + 1000));
        	apiToken.setLoggedin_user_name(disptcr.getFirstName());
        	apiToken = tokenService.save(apiToken);
        	//Dispatcher disp = dispatcherRepo.findByEmail(dispatcher.getEmail());
        	dispatcherDto.setAddress(disptcr.getAddress());
        	dispatcherDto.setAuth_common_id(apiToken.getAuth_common_id());
        	dispatcherDto.setCreatedDate(disptcr.getCreatedDate());
        	dispatcherDto.setDispatcherId(disptcr.getId());
        	dispatcherDto.setDisRegId(disptcr.getDisRegId());
        	dispatcherDto.setEmail(disptcr.getEmail());
        	dispatcherDto.setFirstName(disptcr.getFirstName());
        	dispatcherDto.setImageUrl(disptcr.getImageUrl());
        	dispatcherDto.setLastName(disptcr.getLastName());
        	dispatcherDto.setLatitude(disptcr.getLatitude());
        	dispatcherDto.setLongitude(disptcr.getLongitude());
        	dispatcherDto.setPhNum(disptcr.getPhNum());
        	dispatcherDto.setPincode(disptcr.getPincode());
        	dispatcherDto.setToken(token);
        	return dispatcherDto;
        }
        else 
         return null;
	}
	
    public Dispatcher updateDispatcher(DispatcherDto disp) {
    	
    	Dispatcher dispatcher = dispatcherRepo.findById(disp.getDispatcherId()).get();
    	if(disp.getLastName() !=null) {
    		dispatcher.setLastName(disp.getLastName());
    	}else {
    		dispatcher.setLastName(dispatcher.getLastName());
    	} if(disp.getFirstName() !=null) {
    		dispatcher.setFirstName(disp.getFirstName());
    	}else {
    		dispatcher.setFirstName(dispatcher.getFirstName());
    	}
    	if(disp.getEmail() !=null) {
    		dispatcher.setEmail(disp.getEmail());
    	}else {
    		dispatcher.setEmail(dispatcher.getEmail());
    	}
    	if(disp.getPhNum() !=null) {
    		dispatcher.setPhNum(disp.getPhNum());
    	}else {
    		dispatcher.setPhNum(dispatcher.getPhNum());
    	}
    	if(disp.getAddress() !=null) {
    		dispatcher.setAddress(disp.getAddress());
    	}else {
    		dispatcher.setAddress(dispatcher.getAddress());
    	}
    	if(disp.getPassword() !=null) {
    		dispatcher.setPassword(disp.getPassword());
    	}else {
    		dispatcher.setPassword(dispatcher.getPassword());
    	}
    	if(disp.getDisRegId() !=null) {
    		dispatcher.setDisRegId(disp.getDisRegId());
    	}else {
    		dispatcher.setDisRegId(dispatcher.getDisRegId());
    	}
    	if(disp.getLatitude() !=null) {
    		dispatcher.setLatitude(disp.getLatitude());
    	}else {
    		dispatcher.setLatitude(dispatcher.getLatitude());
    	}
    	if(disp.getLongitude() !=null) {
    		dispatcher.setLongitude(disp.getLongitude());
    	}else {
    		dispatcher.setLongitude(dispatcher.getLongitude());
    	}
    	if(disp.getPincode() !=null) {
    		dispatcher.setPincode(disp.getPincode());
    	}else {
    		dispatcher.setPincode(dispatcher.getPincode());
    	}
    	if(disp.getImageUrl() !=null) {
    		dispatcher.setImageUrl(disp.getImageUrl());
    	}else {
    		dispatcher.setImageUrl(dispatcher.getImageUrl());
    	}
    	dispatcher.setModifiedDate(disp.getModifiedDate());
    	dispatcher.setDeleted(false);
    	return dispatcherRepo.save(dispatcher);
    }
   
	  public Dispatcher resetPassword(DispatcherDto_1 dispatcher) { 
		  
		  if(dispatcherRepo.existsByEmail(dispatcher.getEmail())) { 
			  Long id = dispatcherRepo.findByEmail(dispatcher.getEmail()).getId();
			  Dispatcher disp = dispatcherRepo.findById(id).get();
			  disp.setPassword( passwordEncoder.encode(dispatcher.getPassword()));
		     return dispatcherRepo.save(disp);
		   }
		  else {
			  
		  return null;
		  }
	  }
	  
	  
		public boolean CheckDispatcherExists(String email) { 
			   
			  if(dispatcherRepo.existsByEmail(email) == true) { 
				  System.out.println("Existed Dispatcher");
				  return true;
			  }
			  else
				  System.out.println("Not Existed Dispatcher");
				  return false;
	  }
	 
}
