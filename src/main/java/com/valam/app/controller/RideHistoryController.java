package com.valam.app.controller;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.customObject.DeclinedRideObject;
import com.valam.app.customObject.Ride_History_Object;
import com.valam.app.dto.DeclinedRideObjectDto;
import com.valam.app.dto.ResponseMessage;
import com.valam.app.dto.RideCommentsDto;
import com.valam.app.dto.RideHistoryDto;
import com.valam.app.dto.RideHistory_Dto;
import com.valam.app.model.RideHistory;
import com.valam.app.service.CommonApiTokenService;
import com.valam.app.service.RideHistoryService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/ride")
public class RideHistoryController {

    @Autowired
    private RideHistoryService rideHisService;
    
    @Autowired
    private CommonApiTokenService commonTokenService;

    @ApiOperation(value = "api to get all rides")
    @GetMapping("all")
    public List<RideHistory> findAll(@RequestHeader(value="common_token") String commonToken) {
    	List<RideHistory> rideHis = new ArrayList<RideHistory>();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		rideHis = rideHisService.getRides();
    	}else {
    		rideHis = null;
    	}
        return rideHis;
    }

    @ApiOperation(value = "api to fetch the records between two dates and driverid/dispatcherid/carid from ride history")
    @PostMapping("/fetch")
    public List<RideHistory> findByDataAndDidCid(@RequestHeader(value="common_token") String commonToken,@RequestBody RideHistory_Dto rideDto) {
    	List<RideHistory> rideHis = new ArrayList<RideHistory>();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		rideHis = rideHisService.getDataByDate(rideDto);
    	}else {
    		rideHis = null;
    	}
    	return rideHis;
    }
    
//    @ApiOperation(value = "api to fetch the records between two dates and driverid/dispatcherid/carid from ride history")
//    @PostMapping("/fetch")
//    public RideHistory findByDDS(@RequestBody RideHistory_Dto rideDto) {
//        return rideHisService.getDDS(rideDto);
//    }
    

    @ApiOperation(value = "api to add new record to ride history")
    @PostMapping(value = "/add", consumes = {"application/json"})
    public RideHistory addRide(@RequestHeader(value="common_token") String commonToken,@RequestBody RideHistoryDto udRidesDto) {
    	RideHistory rideHis = new RideHistory();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		rideHis = rideHisService.add(udRidesDto);
    	}else {
    		rideHis  = null;
    	}
    	return rideHis; 
    }

    @ApiOperation(value = "api to fetch the records by given ride id")
    @GetMapping("/{id}")
    public RideHistory findRideById(@RequestHeader(value="common_token") String commonToken,@PathVariable Long id) {
    	RideHistory rideHis = new RideHistory();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		rideHis = rideHisService.getRideByID(id);
    	}else {
    		rideHis = null;
    	}
    	return rideHis;
   
    }

    @ApiOperation(value = "api to update created ride with dispatcher/driver/car id's")
    @PutMapping("/update")
    public RideHistory updateRide(@RequestHeader(value="common_token") String commonToken,@RequestBody RideHistoryDto udRidesDto) {
    	RideHistory rideHis = new RideHistory();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		rideHis = rideHisService.update(udRidesDto);
    	}else {
    		rideHis = null;
    	}
    	return  rideHis;
    }
    
    @ApiOperation(value = "api to update ride after Completed with status id 10 as completed 15 as started ")
    @PostMapping("/rideUpdate/{id}")
    public ResponseMessage updateRideBeforeStart(@RequestHeader(value="common_token") String commonToken,@PathVariable Long id,@RequestBody RideHistoryDto udRidesDto) {
    	ResponseMessage msg = new ResponseMessage();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    	if(udRidesDto.getStsId() == 15) {
    		 rideHisService.updateRideBeforeComplete(udRidesDto.getStsId(), id);
    		 msg.setHttpStatus(200);
    	     msg.setMessage("Ride Started Successfully");
    	}
    	
    	else {
    		 rideHisService.updateRideAfterComplete(udRidesDto.getDropTime(),udRidesDto.getStsId(),id,udRidesDto.getDriverId(),udRidesDto.getMessage());
    		    if(udRidesDto.getStsId() == 8) {
    		    	 msg.setHttpStatus(200);
    	    	    	msg.setMessage("Ride Declined Successfully");
    		    }
    		    else {
    		    	 msg.setHttpStatus(200);
    	    	    	msg.setMessage("Ride Completed Successfully");
    		    }
    	   }   
    	}     
    	
		return msg;
	}
    
    @ApiOperation(value="api to get ride Trips for User based on userid from Ride History")
    @PostMapping("/trips")
    public List<Ride_History_Object> rideTrips(@RequestHeader(value="common_token") String commonToken,@RequestBody RideHistory_Dto rideHistoryDto){
    	List<Ride_History_Object> rides = new ArrayList<Ride_History_Object>();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		rides =  rideHisService.trips(rideHistoryDto);
    	}else {
    		rides = null;
    	}
    	return rides;
    }
    
    @ApiOperation(value="api to get ride sts by 15 to check ride is started or not from Ride History")
    @PostMapping("/sts_isstarted")
    public ResponseMessage getByRideStatus_15(@RequestHeader(value="common_token") String commonToken,@RequestBody RideHistoryDto ride) {
    	ResponseMessage message = new ResponseMessage();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		message = rideHisService.getByRideStatus_15(ride.getRideId());
    	}else {
    		message = null;
    	}
    	return message; 
	}
    @ApiOperation(value="api to get ride sts by 10 to check ride is started or not from Ride History")
    @PostMapping("/sts_iscompleted")
	public ResponseMessage getByRideStatus_10(@RequestHeader(value="common_token") String commonToken,@RequestBody RideHistoryDto ride) {
    	ResponseMessage message = new ResponseMessage();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		message = rideHisService.getByRideStatus_10(ride.getRideId());
    	}else {
    		message = null;
    	}
    	return message;
	}
    
    @ApiOperation(value="Api to get previous ride details based on pickup date")
    @PostMapping("/getRideByUserId")
    public List<RideHistory> getRideByuserId(@RequestHeader(value="common_token") String commonToken,@RequestBody RideHistoryDto rideDto){
    	List<RideHistory> rideHis = new ArrayList<RideHistory>();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		rideHis = rideHisService.findByPendingUserId(rideDto);
    	    return rideHis;
    	
    	}else {
    		return rideHis;
    	}
     }
    
    @ApiOperation(value="Api to get Ongoing rides by pickupdate,uderid,driverid,comfort_level")
    @PostMapping("/ongoing_rides")
    public List<RideHistory> findOngoingRides(@RequestHeader(value="common_token") String commonToken,@RequestBody RideHistoryDto rideHist){
    	List<RideHistory> rideHis = new ArrayList<RideHistory>();
    	if(commonTokenService.getByTokenId(commonToken) != null) { 
    		rideHis = rideHisService.findByPendingUserId(rideHist);
    		return rideHis;
    	}else {
    		return rideHis;
    	}
    }
    
    @ApiOperation(value="Api to get Declined Rides")
    @PostMapping("/declined_rides")
    public List<DeclinedRideObjectDto> findDeclinedRides(){
    	return rideHisService.getDeclinedRides();
    }
    
    @ApiOperation(value="Api to update ride commnets by admin/executive")
    @PostMapping("/updateride_comments")
    public ResponseMessage updateRideComments(@RequestBody RideCommentsDto rideCommentsDto){
    	rideHisService.updateRideCommentByadmin(rideCommentsDto);
    	ResponseMessage message = new ResponseMessage();
    	message.setHttpStatus(200);
    	message.setMessage("Ride Comments Updated Successfully");
    	return message;
    }
    
    
}
