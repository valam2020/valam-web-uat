package com.valam.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.valam.app.customObject.Ride_History_Object;
import com.valam.app.dto.ResponseMessage;
import com.valam.app.dto.RideHistoryDto;
import com.valam.app.dto.RideHistory_Dto;
import com.valam.app.model.CarDetails;
import com.valam.app.model.Dispatcher;
import com.valam.app.model.Driver;
import com.valam.app.model.RideHistory;
import com.valam.app.model.RideStatus;
import com.valam.app.model.User;
import com.valam.app.repo.CarRepositary;
import com.valam.app.repo.DispatcherRepositary;
import com.valam.app.repo.DispatcherSchedulerRepositary;
import com.valam.app.repo.DriverRepositary;
import com.valam.app.repo.RideHistoryRepositary;
import com.valam.app.repo.RideRequestRepositary;
import com.valam.app.repo.StatusRepositary;
import com.valam.app.repo.UserRepository;


@Service
public class RideHistoryService { 
	
	@Autowired
	private RideHistoryRepositary rideHisRepo; 
	
	public RideHistoryService (RideHistoryRepositary ridesRepo) {
		this.rideHisRepo = ridesRepo;
	}
	
	@Autowired
	private StatusRepositary stsRepo;
	
	@Autowired 
	private DispatcherSchedulerRepositary dispatcherSchRepo;
	
	@Autowired
	private DriverRepositary driverepo;
	
	@Autowired
	private RideRequestRepositary rideRequestRepo;
	
	@Autowired
	private CarRepositary carRepo;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private DispatcherRepositary dispatcherRepo;
	
	public List<RideHistory> getRides(){
		return rideHisRepo.findAll();
	}
    
    // getting all ride by given id 
    public RideHistory getRideByID(Long id) {
    	
    	RideHistory rideHis = rideHisRepo.findById(id).orElse(null);
    	if(rideHis.getExpiryTimer() >= System.currentTimeMillis()) {
    	        return rideHis; }
    	else {
    	rideHisRepo.updateStatus(id);
    	rideRequestRepo.update(id);
		RideHistory ride = rideHisRepo.findById(id).orElse(null);
    	return ride;}
    }
    
    //to save the ride record with user and ride status
	public RideHistory add(RideHistoryDto udridesDto){
	
		// getting RideStatus by id
		RideStatus rideSts = null;
        if(udridesDto != null && udridesDto.getStsId() != null) {
			rideSts = stsRepo.findById(udridesDto.getStsId()).get();
		rideSts.setStsId(rideSts.getStsId());}

        // Getting Dispatcher by id
        Dispatcher dispatcher = null;
        if(udridesDto != null && udridesDto.getDispatcherId() != null) {
        	dispatcher = dispatcherRepo.findById(udridesDto.getDispatcherId()).get();
		  dispatcher.setId(dispatcher.getId());}
        
		
		// Getting User  by id
        User user = null;
        if(udridesDto != null && udridesDto.getUserId() !=null) {
	    user = userrepo.findById(udridesDto.getUserId()).get();
		user.setId(user.getId());}
        
		
		// Getting Driver by id
        Driver dd = null;
        if(udridesDto != null && udridesDto.getDriverId() !=null){
        	dd = driverepo.findById(udridesDto.getDriverId()).get();
        	dd.setId(dd.getId());
        }
        
        // Getting CarDetails by id
        CarDetails car = null;
        if(udridesDto != null && udridesDto.getCarId() != null) {
    	 car = carRepo.findById(udridesDto.getCarId()).get();
    	car.setCarId(car.getCarId());}
    	
    	// Saving the  User Ride Details 
    	RideHistory uride1 = new RideHistory();
    	uride1.setUser(user);   
       	uride1.setDriver(dd);
       	uride1.setDispatcher(dispatcher);
       	uride1.setCarDetails(car);
       	uride1.setRideStatus(rideSts);
       	uride1.setPaymentTotal(udridesDto.getPaymentTotal());
       	uride1.setPaymentType(udridesDto.getPaymentType());
       	uride1.setDistance(udridesDto.getDistance());
    	uride1.setFromAddress(udridesDto.getFromAddress());
	 	uride1.setToAddress(udridesDto.getToAddress());
	    uride1.setPickupDate(udridesDto.getPickupTime());
	    uride1.setDropDate(udridesDto.getDropTime());
	    uride1.setPickupLat(udridesDto.getPickupLat());
	    uride1.setPickupLng(udridesDto.getPickupLng());
	    uride1.setDropLat(udridesDto.getDropLat());
	    uride1.setDropLng(udridesDto.getDropLng());
	    uride1.setExpiryTimer(System.currentTimeMillis()+300000);
	    return rideHisRepo.save(uride1);
    }
	
	public RideHistory update(RideHistoryDto rideHis) {
		
		RideStatus rideSts = null;
        if(rideHis != null && rideHis.getStsId() != null) {
			rideSts = stsRepo.findById(rideHis.getStsId()).get();
		rideSts.setStsId(rideSts.getStsId());}

        Dispatcher dispatcher = null;
        if(rideHis != null && rideHis.getDispatcherId() != null) {
        	dispatcher = dispatcherRepo.findById(rideHis.getDispatcherId()).get();
		  dispatcher.setId(dispatcher.getId());}
        
		// Getting User  by id
        User user = null;
        if(rideHis != null && rideHis.getUserId() !=null) {
	    user = userrepo.findById(rideHis.getUserId()).get();
		user.setId(user.getId());}
        
		// Getting Driver by id
        Driver dd = null;
        if(rideHis != null && rideHis.getDriverId() !=null){
        	dd = driverepo.findById(rideHis.getDriverId()).get();
        	dd.setId(dd.getId());
        }
        
        CarDetails car = null;
        //Long car_id = dispatcherSchRepo.findbyDriverId(rideHis.getDriverId(),rideHis.getDispatcherId()).getCar_id();
        if(rideHis != null && rideHis.getCarId() != null) {
    	 car = carRepo.findById(rideHis.getCarId()).get();
    	 car.setCarId(car.getCarId());}
    	
    	RideHistory uride1 = rideHisRepo.findById(rideHis.getRideId()).orElse(null);
    	uride1.setUser(user);   
       	uride1.setDriver(dd);
       	uride1.setDispatcher(dispatcher);
       	uride1.setCarDetails(car);
       	uride1.setRideStatus(rideSts);
       	uride1.setPaymentTotal(uride1.getPaymentTotal());
       	uride1.setPaymentType(uride1.getPaymentType());
       	uride1.setDistance(uride1.getDistance());
    	uride1.setFromAddress(uride1.getFromAddress());
	 	uride1.setToAddress(uride1.getToAddress());
	    uride1.setPickupDate(uride1.getPickupDate());
	    uride1.setDropDate(rideHis.getDropTime());
	    uride1.setDropLat(uride1.getDropLat());
	    uride1.setDropLng(uride1.getDropLng());
	    uride1.setPickupLat(uride1.getPickupLat());
	    uride1.setPickupLng(uride1.getPickupLng());
	    if(rideHis.getOtp() != null) {
	    	if(uride1.getOtp().equals(rideHis.getOtp())){
	    		uride1.setOtp(String.valueOf(((int)(Math.random()*(10000 - 1000))) + 1000));
	    		return rideHisRepo.save(uride1);
	    	} }
	          return  rideHisRepo.save(uride1);  	
	    }
	
	//to fetch the records by given ride Dates
	public List<RideHistory> getDataByDate(RideHistory_Dto rideDto) { 
//		if(rideDto.getPickupDate() ==null && rideDto.getDropDate() ==null && rideDto.getDriverId() == null &&rideDto.getUserId() == null && rideDto.getDispatcherId() == null) {
//			rideDto.getPickupDate();
//			rideDto.setPickupDate(LocalDate.now());
//		}
		
		List<RideHistory> rideData = new ArrayList<RideHistory>();
		if(rideDto.getPickupDate() ==null && rideDto.getDropDate() ==null) {
			rideDto.getPickupDate();
			rideDto.setPickupDate(LocalDate.now());
		}
		
		rideData = rideHisRepo.findByDateBetween(rideDto.getPickupDate(),rideDto.getDropDate(),rideDto.getDriverId(),rideDto.getUserId(),rideDto.getDispatcherId(),rideDto.getStatusId());
	    if(rideData != null) {
	    	for (RideHistory rideHistory : rideData) {
		    	  
		  	    	if(rideHistory.getRideStatus().getStsId() == 4) {
		  				System.out.println("Hi You have ride now");
		  				return rideData;
		  			}else {
		  				System.out.println("Hi You have no rides");
		  				//rideData = new ArrayList<RideHistory>();
		  				//RideHistory ride = new RideHistory();
		  				//rideData.add(ride);
		  			}
		  	   }
         }else {
				System.out.println("Hi You have no rides");
				return rideData ;
			}
		return rideData;
	}
	
	public RideHistory getDDS(RideHistory_Dto rideDto) { 
		RideHistory rideData;
		if(rideDto.getPickupDate() ==null && rideDto.getDropDate() ==null) {
			rideDto.getPickupDate();
			rideDto.setPickupDate(LocalDate.now());
		}
	    rideData = rideHisRepo.findByDDS(rideDto.getPickupDate(),rideDto.getDropDate(),rideDto.getDriverId(),rideDto.getUserId(),rideDto.getDispatcherId(),rideDto.getStatusId());
	    if(rideData != null) {
	    	if(rideData.getRideStatus().getStsId() == 4) {
				System.out.println("Hi You have ride now");
				return rideData;
			}else {
				System.out.println("Hi You have no rides");
				rideData = new RideHistory();
			}
	    }else {
			System.out.println("Hi You have no rides");
			rideData = new RideHistory();
		//	return rideData;
		}
		return rideData;
	}
     
	public String updateRideBeforeComplete(Long sts_id,Long ride_id) {
		rideHisRepo.updateStatusBeforeStarted(sts_id,ride_id);
		return "Successfully Updated";
	}
	
	public String updateRideAfterComplete(LocalDateTime drop_date,Long sts_id,Long ride_id) {
		rideHisRepo.updateStatusafterComplete(drop_date,sts_id,ride_id);
		return "Successfully Updated";
	}
	
	
	
	public List<Ride_History_Object> trips(RideHistory_Dto rideHistoryDto) {
		return rideHisRepo.rideTrips(rideHistoryDto.getUserId(),rideHistoryDto.getDriverId());
	}
	
	public ResponseMessage getByRideStatus_15(Long ride_id) {
		Ride_History_Object ride;
		ResponseMessage message = new ResponseMessage();
		ride = rideHisRepo.getRideByrideSts_15(ride_id);
		if(ride != null && ride.getStatus_id() == 15) {
			message.setHttpStatus(200);
			message.setMessage(ride.getStatus());
			return message;
		}else {
			message.setHttpStatus(400);
			message.setMessage("Not Yet Started");
			return message;
		}
		//return rideHisRepo.getRideByrideSts_15(ride_id);
	}
	
	public ResponseMessage getByRideStatus_10(Long ride_id) {
		Ride_History_Object ride;
		ResponseMessage message = new ResponseMessage();
		ride = rideHisRepo.getRideByrideSts_10(ride_id);
		if(ride != null && ride.getStatus_id() == 10) {
			message.setHttpStatus(200);
			message.setMessage(ride.getStatus());
			return message;
		}else {
			message.setHttpStatus(400);
			message.setMessage("Not Yet Completed");
			return message;
		}
		//return rideHisRepo.getRideByrideSts_10(ride_id);
	}
	
	public List<RideHistory> findByUserId(RideHistoryDto rideHist){
		return rideHisRepo.findByPrevRideByUserId(rideHist.getPickupDate(), rideHist.getUserId());
	}


}
