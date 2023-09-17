package com.valam.app.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valam.app.model.LoginUsers;
import com.valam.app.repo.LoginUsersRepo;

@Service
public class LoginUsersService {

	@Autowired
	private LoginUsersRepo loginuserRepo;
	
	public LoginUsers createLogin(LoginUsers user) {
		LoginUsers disp = new LoginUsers();
		LocalDateTime date = LocalDateTime.now();
		disp.setLogin_id(user.getLogin_id());
		disp.setDeviceId(user.getDeviceId());
		disp.setLogin_time(date);
		disp.setIsLoggedIn(true);
		return loginuserRepo.save(disp);
	}
	
	public Optional<LoginUsers> findById(Long id) {
		return loginuserRepo.findById(id);
	}
	
	public LoginUsers findByloginId(Long id)
	{
		return loginuserRepo.findByLoginId(id);
	}
		
	public void updateLogoutTime(LoginUsers login) {
		LocalDateTime date = LocalDateTime.now();
		login.setLogout_time(date);
		loginuserRepo.updatelogoutTime(login.getLogout_time(), login.getLogin_id());
	}
	
	public LoginUsers findByDevice(LoginUsers user)
	{
		return loginuserRepo.findByDevice(user.getLogin_id(),user.getDeviceId());
	}
	
}
