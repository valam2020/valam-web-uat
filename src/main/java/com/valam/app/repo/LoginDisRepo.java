package com.valam.app.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valam.app.model.LoginDispatchers;

@Repository
public interface LoginDisRepo extends JpaRepository<LoginDispatchers,Long> {
	
	
	@Query(nativeQuery = true, value="select * from login_dispatchers where dispatcher_email = :emailId and logout_time is Null")
	public LoginDispatchers findByEmailId(@Param("emailId") String emailId);
	
	@Query(nativeQuery = true, value="Select * from login_dispatchers where dispatcher_email = :emailId and device_id= :deviceId ")
	public LoginDispatchers findByDevice(@Param("emailId") String emailId,@Param("deviceId") String deviceId);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true,value="UPDATE login_dispatchers SET is_logged_in = false WHERE dispatcher_email = :email")
	public void updatelogout(@Param("email") String email); 
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true,value="UPDATE login_dispatchers SET logout_time = :logoutTime,is_logged_in =false WHERE dispatcher_email = :email")
	public void updatelogoutTime(@Param("logoutTime") LocalDateTime logout_time,@Param("email") String email); 
	
//	@Modifying
//	@Transactional
//	@Query(nativeQuery = true,value="UPDATE login_dispatchers SET login_time = :loginTime WHERE dispatcher_email = :email")
//	public void updatelogInTime(@Param("loginTime") LocalDateTime login_time,@Param("email") String email); 

}
