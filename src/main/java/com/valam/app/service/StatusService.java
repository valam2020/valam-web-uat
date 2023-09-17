package com.valam.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.valam.app.model.RideStatus;
import com.valam.app.repo.StatusRepositary;

@Service
public class StatusService { 
	
	@Autowired
	private StatusRepositary statusRepo; 
	
	//to save a record of ride status
	public RideStatus saveStatus(RideStatus rStatus) {
		return statusRepo.save(rStatus);
	}
	
	//to fetch all ride statuses
	public List<RideStatus> getAllStatus(){
		return statusRepo.findAll();
	}
	
   
	public void deleteRideStatus(Long id) {
		statusRepo.deleteById(id);
		return;
	}

	public RideStatus updateRideStatus(RideStatus rideStatus) { 

		RideStatus rideSt = statusRepo.findById(rideStatus.getStsId()).orElse(null);
			
		rideSt.setStsId(rideStatus.getStsId());
		rideSt.setStatusName(rideStatus.getStatusName());
		return statusRepo.save(rideSt);
	}
    
	public Optional<RideStatus> findByStsId(Long id) {
		return statusRepo.findById(id);
	}

}
