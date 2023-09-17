package com.valam.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valam.app.dto.UserTTDTO;
import com.valam.app.model.UserTimeTrackTable;
import com.valam.app.repo.UserTimeTrackRepo;

@Service
public class UserTimeTrackService {
	
	@Autowired
	private UserTimeTrackRepo userTTRepo;
	
	public List<UserTimeTrackTable> getAllTracks() {
		return userTTRepo.findAll();
	}
	
	public Optional<UserTimeTrackTable> findById(Long id) {
		return userTTRepo.findById(id);
	}
	
	public UserTimeTrackTable saveTrack(UserTTDTO userTrack) {
		UserTimeTrackTable userTimeTrackTable = new UserTimeTrackTable();
		userTimeTrackTable.setLogout_time(userTrack.getLogin_time());
		userTimeTrackTable.setCustomerLoginID(userTrack.getCustomerLoginID());
		//userTrack.setTime_taken(userTTRepo.getUserTrackingTime(userTimeTrackTable.getTimeTrackingID()).getTime_taken());
		return userTTRepo.save(userTimeTrackTable);
	}
	
	public UserTTDTO updateLogoutTime(UserTTDTO userTrack) {
		UserTimeTrackTable userTimeTrackTable = userTTRepo.findById(userTrack.getTimeTrackingID()).get();
		userTTRepo.updateLogoutTime(userTrack.getLogout_time(), userTrack.getTimeTrackingID());
		userTrack.setTime_taken(userTTRepo.getUserTrackingTime(userTimeTrackTable.getCustomerLoginID()).getTime_taken());
		return userTrack;
	}
	
	public void deleteTrack(long id) {
		userTTRepo.deleteById(id); 
	}
	

}
