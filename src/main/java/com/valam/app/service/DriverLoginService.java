package com.valam.app.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valam.app.model.DriverLogin;
import com.valam.app.repo.LoginDriverRepo;

@Service
public class DriverLoginService {

	@Autowired
	private LoginDriverRepo driverLoginRepo;
	
	public DriverLogin createLogin(String loginId,String deviceId) {
		DriverLogin disp = new DriverLogin();
		LocalDateTime date = LocalDateTime.now();
		disp.setEmail(loginId);
		disp.setDeviceId(deviceId);
		disp.setLogin_time(date);
		disp.setIsLoggedIn(true);
		return driverLoginRepo.save(disp);
	}
	
	public Optional<DriverLogin> findById(Long id) {
		return driverLoginRepo.findById(id);
	}
	
	public DriverLogin findByemail(String email)
	{
		return driverLoginRepo.findByEmailId(email);
	}
	
	public DriverLogin findByDevice(DriverLogin login)
	{
		return driverLoginRepo.findByDevice(login.getEmail(),login.getDeviceId());
	}
		
	public void updateLogoutTime(DriverLogin login) {
		LocalDateTime date = LocalDateTime.now();
		login.setLogout_time(date);
		driverLoginRepo.updatelogoutTime(login.getLogout_time(), login.getEmail());
	}
}
