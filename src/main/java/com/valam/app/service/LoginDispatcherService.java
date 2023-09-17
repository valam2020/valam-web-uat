package com.valam.app.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valam.app.model.LoginDispatchers;
import com.valam.app.repo.LoginDisRepo;

@Service
public class LoginDispatcherService {

	@Autowired
	private LoginDisRepo loginDisp; 
	
	public LoginDispatchers createLogin(String email,String device) {
		LoginDispatchers disp = new LoginDispatchers();
		LocalDateTime date = LocalDateTime.now();
		disp.setEmail(email);
		disp.setDeviceId(device);
		disp.setIsLoggedIn(true);
		disp.setLogin_time(date);
		return loginDisp.save(disp);
	}
	
	public Optional<LoginDispatchers> findById(Long id) {
		return loginDisp.findById(id);
	}
	
	public LoginDispatchers findByemail(String email)
	{
		return loginDisp.findByEmailId(email);
	}
	
	public LoginDispatchers findByDevice(String email,String device)
	{
		return loginDisp.findByDevice(email,device);
	}
		
		
	public void updateLogoutTime(LoginDispatchers login) {
		LocalDateTime date = LocalDateTime.now();
		login.setLogout_time(date);
		loginDisp.updatelogoutTime(login.getLogout_time(), login.getEmail());
	}
	
	public void updatelogoutStatus(String email) {
	   loginDisp.updatelogout(email);
	}
	
	
	
	
}
