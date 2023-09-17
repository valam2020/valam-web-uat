package com.valam.app.repo;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valam.app.model.DriverLogin;

@Repository
public interface LoginDriverRepo  extends JpaRepository<DriverLogin,Long>{
	
	@Query(nativeQuery = true, value="select * from login_drivers where driver_email = :emailId and logout_time is Null")
	public DriverLogin findByEmailId(@Param("emailId") String emailId);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true,value="UPDATE login_drivers SET logout_time = :logoutTime,is_logged_in =false WHERE driver_email = :email")
	public void updatelogoutTime(@Param("logoutTime") LocalDateTime logout_time,@Param("email") String email); 
	
	@Query(nativeQuery = true, value="Select * from login_drivers where driver_email = :emailId and device_id= :deviceId ")
	public DriverLogin findByDevice(@Param("emailId") String emailId,@Param("deviceId") String deviceId);

}
