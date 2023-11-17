package com.valam.app.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valam.app.customObject.RideRequest_Dto;
import com.valam.app.dto.RideRequestDto;
import com.valam.app.model.CarDetails;
import com.valam.app.model.Dispatcher;
import com.valam.app.model.Driver;
import com.valam.app.model.RideHistory;
import com.valam.app.model.RideRequest;
import com.valam.app.model.RideStatus;
import com.valam.app.repo.CarRepositary;
import com.valam.app.repo.DispatcherRepositary;
import com.valam.app.repo.DispatcherSchedulerRepositary;
import com.valam.app.repo.DriverRepositary;
import com.valam.app.repo.RideHistoryRepositary;
import com.valam.app.repo.RideRequestRepositary;
import com.valam.app.repo.StatusRepositary;


@Service
public class RideRequestService {

	@Autowired
	private RideRequestRepositary rideReqRepo;
	
	@Autowired 
	private DispatcherSchedulerRepositary dispatcherSchRepo;

	
	@Autowired
	private RideHistoryRepositary rideHisRepo; 
	
	@Autowired
	private CarRepositary carRepo;
	
	@Autowired
	private DispatcherRepositary disptacherRepo;
	
	@Autowired
	private DriverRepositary driverRepo;
	
	@Autowired
	private StatusRepositary stsRepo;
	
    //to fetch the records the by given ride-location id
	public RideRequest getRideReqByID(Long id) {
		return rideReqRepo.findById(id).orElse(null);
	} 
	
	//to fetch all ride locations under given status id 
	public List<RideRequest> getRideReqByStsDisID(RideRequestDto rideReq) {
		System.out.println(rideReq.getDispatcherId());
		System.out.println(rideReq.getDriverId());
		System.out.println(rideReq.getStsId());
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate localDate = localDateTime.toLocalDate();
		return rideReqRepo.findByDisStsId(rideReq.getStsId(),rideReq.getDispatcherId(),rideReq.getDriverId(),localDate);
	} 
	
	//method to delete ride location
	public String deleteRideReq(Long id) {
		rideReqRepo.deleteById(id);
		return "RideLocation is Removed !!"+id;
	}
	
	// request to ride location by user and his status
	public RideRequest addRideReq(RideRequestDto rideReq) { 
		
		RideStatus rideSts = null;
        if(rideReq != null && rideReq.getStsId() != null) {
			rideSts = stsRepo.findById(rideReq.getStsId()).get();
		rideSts.setStsId(rideSts.getStsId());}
		
		Dispatcher dispatcher = null;
        if(rideReq != null && rideReq.getDispatcherId() != null) {
        	dispatcher = disptacherRepo.findById(rideReq.getDispatcherId()).get();
		  dispatcher.setId(dispatcher.getId());}
		
		RideHistory rideHistory =null;
		if(rideReq !=null && rideReq.getRide_id() !=null) {
		   rideHistory = rideHisRepo.findById(rideReq.getRide_id()).get();
		rideHistory.setRideId(rideHistory.getRideId());}
		
		Driver driver = null;
		if(rideReq !=null && rideReq.getDriverId() !=null) {
			driver = driverRepo.findById(rideReq.getDriverId()).get();
			driver.setId(driver.getId());
		}
		
		RideRequest ridereq = new RideRequest();
		ridereq.setRideStatus(rideSts);
		ridereq.setDriver(driver);
		ridereq.setRide_history(rideHistory);
		ridereq.setDispatcher(dispatcher);
		ridereq.setRide_date(rideReq.getRideDate());
		return rideReqRepo.save(ridereq);
        }
	
	// update the ride-location with rideLocation and his status.
	public RideRequest updateRide(Long id, RideRequestDto rideby) { 
		RideRequest req = new RideRequest();
		  
		  RideStatus rideSts = null;
	        if(rideby != null && rideby.getStsId() != null) {
				rideSts = stsRepo.findById(rideby.getStsId()).get();
			rideSts.setStsId(rideSts.getStsId());}
	        
	     if(rideSts.getStsId() == 3) {
	    	    req.setDispatcher(null);
			    req.setDriver(null);
			    req.setRide_history(null);
			    req.setRide_loc_id(id);
			    rideSts.setStsId((long) (3));
			    rideSts.setStatusName("No Driver Available");
			    updateDeclineStatusToDispatcherId(rideby.getDispatcherId(),rideby.getRide_id());
			    req.setRideStatus(rideSts);	
	     }else if(rideSts.getStsId() == 25) {
	    	 req.setDispatcher(null);
			    req.setDriver(null);
			    req.setRide_history(null);
			    req.setRide_loc_id(id);
			    rideSts.setStsId((long) (25));
			    rideSts.setStatusName("Disptacher declined");
			    updateDeclineStatusToDispatcherId(rideby.getDispatcherId(),rideby.getRide_id());
			    req.setRideStatus(rideSts);	
	     }
	     
	     else {
	    	 RideRequest req12 = rideReqRepo.isAlreadyRequestedOrNot(rideby.getRide_id());
	 		if(req12 != null && req12.getRideStatus().getStsId() == 4) {
	 			req.setDispatcher(null);
	 		    req.setDriver(null);
	 		    req.setRide_history(null);
	 		    req.setRide_loc_id(id);
	 		    rideSts.setStsId((long) (6));
	 		    rideSts.setStatusName("Already Accepted By Other Dispatcher!!!");
	 		  
	 		    req.setRideStatus(rideSts);	
	 		}else {
	 			Dispatcher dispatcher = null;
	 	        if(rideby != null && rideby.getDispatcherId() != null) {
	 	        	dispatcher = disptacherRepo.findById(rideby.getDispatcherId()).get();
	 			  dispatcher.setId(dispatcher.getId());}
	 			
	 			RideHistory rideHistory =null;
	 			if(rideby !=null && rideby.getRide_id() !=null) {
	 			   rideHistory = rideHisRepo.findById(rideby.getRide_id()).get();
	 			rideHistory.setRideId(rideHistory.getRideId());}
	 			
	 			Driver driver = null;
	 			if(rideby !=null && rideby.getDriverId() !=null) {
	 				driver = driverRepo.findById(rideby.getDriverId()).get();
	 				  CarDetails car = null;
	 				 Long car_id = driver.getCarId() ;	 				 
	 					// Long car_id = dispatcherSchRepo.findbyDriverId(driver.getId(),dispatcher.getId()).getCar_id();
	 			        //System.out.println(car_id);
	 			        if(car_id != null && driver.getDriver_status() != "ride_queue") {
	 			        	car = carRepo.findById(car_id).get();
	 			    	car.setCarId(car.getCarId());
	 			    	}
	 			        else {
	 			        	req.setDispatcher(null);
	 					    req.setDriver(null);
	 					    req.setRide_history(null);
	 					    req.setRide_loc_id(id);
	 					    rideSts.setStsId((long) (3));
	 					    rideSts.setStatusName("No Driver Available");		
	 					    req.setRideStatus(rideSts);	
	 					    return req;
	 			        }
	 				if(driver.getRideStatus().getStsId() == 2 && car.getCarId() != null ) {
	 					driver.setId(driver.getId());
	 					RideRequest  rideloc = rideReqRepo.findById(id).get();
	 					rideloc.setRideStatus(rideSts);
	 					rideloc.setDriver(driver);
	 				    rideloc.setRide_history(rideHistory);
	 				    rideloc.setDispatcher(dispatcher);
	 				    req = rideReqRepo.save(rideloc);
	 				    
	 			        RideHistory rideHis = rideHistory;
	 			        rideHis.setDispatcher(dispatcher);
	 			        rideHis.setCarDetails(car);
	 			        rideHis.setDriver(driver);
	 			        rideHis.setRideStatus(rideSts);
	 			        rideHis.setPickupDate(rideHis.getPickupDate());    
	 			        rideHis.setDropDate(rideHis.getDropDate());
	 			        rideHis.setDropLat(rideHis.getDropLat());
	 			        rideHis.setDropLng(rideHis.getDropLng());
	 			        rideHis.setPickupLat(rideHis.getPickupLat());
	 			        rideHis.setPickupLng(rideHis.getPickupLng());
	 			        rideHis.setFromAddress(rideHis.getFromAddress());
	 			        rideHis.setPaymentTotal(rideHis.getPaymentTotal());
	 			        rideHis.setPaymentType(rideHis.getPaymentType());
	 			        rideHis.setToAddress(rideHis.getToAddress());
	 			        if(driver !=null && dispatcher != null) {
	 			        	rideHis.setOtp(String.valueOf(((int)(Math.random()*(10000 - 1000))) + 1000));
	 			        }
	 			        rideHisRepo.save(rideHis);
	 			        driverRepo.updateByDriverStatus(driver.getId());
	 			        System.out.println("req.getRide_history().getRideId()"+req.getRide_history().getRideId());
	 			        if(req.getRide_loc_id() != null) {
	 			        	updateNotSelectedRideStatus(req.getRide_history().getRideId());
	 			        }
	 			        
	 			        return req;
	 				}
	 				else {
	 					req.setDispatcher(null);
	 				    req.setDriver(null);
	 				    req.setRide_history(null);
	 				    req.setRide_loc_id(id);
	 				    rideSts.setStsId((long) (3));
	 				    rideSts.setStatusName("No Driver Available");			
	 				    req.setRideStatus(rideSts);	
	 				    return req;
	 				}
	 			}else {
	 				req.setDispatcher(null);
	 			    req.setDriver(null);
	 			    req.setRide_history(null);
	 			    req.setRide_loc_id(id);
	 			    rideSts.setStsId((long) (3));
				    rideSts.setStatusName("No Driver Available");		
	 			    req.setRideStatus(rideSts);	
	 			    return req;
	 			}
	 		}
	     }
	     
		return req;
		
	}
	
	public List<RideRequest> getallDisbyId(Long dispatcher_id){
		return rideReqRepo.findAllByDispatcherId(dispatcher_id);
	}
	
	public List<RideRequest> getDeclinedByDispatcherId(Long dispatcher_id){
		
		List<RideRequest> rideData = rideReqRepo.getdeclinedRidesbydisp();
		List<RideRequest> declinedRides = new ArrayList<>();
		for(RideRequest req:rideData) {
			if(req.getDispatcher() != null && dispatcher_id.equals(req.getDispatcher().getId())) {
				declinedRides.add(req);
			}
		}
		return declinedRides;
	}
	
	
	public void updateNotSelectedRideStatus(Long rideId) {
		List<RideRequest_Dto> requests = rideReqRepo.findByRideId(rideId);
	    //System.out.println(requests);
		if(requests!=null) {
		for(RideRequest_Dto req:requests) {
			System.out.println("req.getRide_loc_id()"+req.getRide_loc_id());
			rideReqRepo.update(req.getRide_loc_id());
		}}
	}
	
	public void updateDeclineStatusToDispatcherId(Long dispatcherId,Long rideId) {
		List<RideRequest_Dto> requests = rideReqRepo.findByDisId(dispatcherId,rideId);
	    //System.out.println(requests);
		for(RideRequest_Dto req:requests) {
			rideReqRepo.updateByRideLocId(req.getRide_loc_id());
		}
	}
	
	
	
	public List<RideRequest> saveRequests(List<RideRequestDto> rideRequest){
		
		List<RideRequest> requestList = new ArrayList<RideRequest>();
		
		for(RideRequestDto requestDto:rideRequest ) {
			
			Dispatcher dispatcher = null;
	        if(requestDto != null && requestDto.getDispatcherId() != null) {
	        	dispatcher = disptacherRepo.findById(requestDto.getDispatcherId()).get();
			  dispatcher.setId(dispatcher.getId());}
	        
	        RideStatus rideSts = null;
	        if(requestDto != null && requestDto.getStsId() != null) {
				rideSts = stsRepo.findById(requestDto.getStsId()).get();
			rideSts.setStsId(rideSts.getStsId());}
	        
	        RideHistory rideHistory =null;
			if(requestDto !=null && requestDto.getRide_id() !=null) {
			   rideHistory = rideHisRepo.findById(requestDto.getRide_id()).get();
			rideHistory.setRideId(rideHistory.getRideId());}
	        
			RideRequest req = new RideRequest();
			req.setDispatcher(dispatcher);
			req.setRide_date(LocalDateTime.now());
			req.setRide_history(rideHistory);
			req.setRideStatus(rideSts);
			requestList.add(req);
			
		}
		
		return rideReqRepo.saveAll(requestList);
	}
}
