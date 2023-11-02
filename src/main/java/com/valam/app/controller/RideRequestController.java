package com.valam.app.controller;


import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.dto.RideRequestDto;
import com.valam.app.model.RideRequest;
import com.valam.app.service.CommonApiTokenService;
import com.valam.app.service.RideRequestService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rideRequest")
public class RideRequestController {

    @Autowired
    private RideRequestService rideRequestService;
    
    @Autowired
    private CommonApiTokenService commonTokenService;

    @ApiOperation(value = "api to fetch the ride loc record by given id")
    @GetMapping("/{id}")
    public RideRequest findRideReqById(@RequestHeader(value="common_token") String commonToken,@PathVariable Long id) {
    	RideRequest ridereq = new RideRequest();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		ridereq = rideRequestService.getRideReqByID(id);
    	}else {
    		ridereq  = null;
    	}
        return ridereq;
    }
    
    
    @ApiOperation(value = "api to fetch the Declined rides by Dispatcher_id")
    @GetMapping("/dispatcher/{id}")
    public List<RideRequest> findDeclinedByDispId(@PathVariable Long id) {
    	
    	return rideRequestService.getDeclinedByDispatcherId(id);
    }

    @ApiOperation(value = "api to fetch the records based on ride_status(like: Ride Request,Accepted Etc)")
    @PostMapping("/fetch")
    public List<RideRequest> findRideLocByStsId(@RequestHeader(value="common_token") String commonToken,@RequestBody RideRequestDto rideLoc) {
    	List<RideRequest> rides = new ArrayList<RideRequest>();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		rides = rideRequestService.getRideReqByStsDisID(rideLoc);
    	}else {
    		rides = null;
    	}
    	return rides ;
    }

    @ApiOperation(value = "api to add new ride request from user")
    @PostMapping("/add")
    public RideRequest updateRideLoc(@RequestHeader(value="common_token") String commonToken,@RequestBody RideRequestDto rideLoc) {
    	RideRequest ridereq = new RideRequest();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		ridereq = rideRequestService.addRideReq(rideLoc);
    	}else {
    		ridereq  = null;
    	}
    	return ridereq ;
    }

    @ApiOperation(value = "api to update the ride by given id")
    //@PutMapping("/update/{id}")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public RideRequest update(@RequestHeader(value="common_token") String commonToken,@PathVariable Long id, @RequestBody RideRequestDto rideSts) {
    	RideRequest ridereq = new RideRequest();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		ridereq = rideRequestService.updateRide(id, rideSts);
    	}else {
    		ridereq  = null;
    	}
    	return ridereq ;
    }

    @ApiOperation(value = "api to delete the record from Database")
    @DeleteMapping("/delete/{id}")
    public String deleteRideLoc(@RequestHeader(value="common_token") String commonToken,@PathVariable Long id) {
    	//RideRequest ridereq = new RideRequest();
    	String ridereq = null;
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		ridereq = rideRequestService.deleteRideReq(id);
    	}else {
    		ridereq  = null;
    	}
        return ridereq ;
    }

    @ApiOperation(value = "api to get all dispatcher ride requests")
    @PostMapping("/disId")
    public List<RideRequest> getAllDisById(@RequestHeader(value="common_token") String commonToken,@PathVariable Long dispatcherId) {
    	List<RideRequest> rides = new ArrayList<RideRequest>();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		rides =  rideRequestService.getallDisbyId(dispatcherId);
    	}else {
    		rides = null;
    	}
    	return rides;
    }

    @ApiOperation(value = "api to add ride requests when multiple nearest drivers availble")
    @PostMapping("saveAll")
    public List<RideRequest> saveAll(@RequestHeader(value="common_token") String commonToken,@RequestBody List<RideRequestDto> rideRequest) {
    	List<RideRequest> rides = new ArrayList<RideRequest>();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		rides =  rideRequestService.saveRequests(rideRequest);
    	}else {
    		rides = null;
    	}
        return rides ;
    }
}
