package com.valam.app.repo;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valam.app.model.LoginUsers;

@Repository
public interface LoginUsersRepo extends JpaRepository<LoginUsers,Long>{
 
	@Query(nativeQuery = true, value="select * from login_users where login_id = :id and logout_time is Null")
	public LoginUsers findByLoginId(@Param("id") Long id);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true,value="UPDATE login_users SET logout_time = :logoutTime,is_logged_in =false WHERE login_id = :email")
	public void updatelogoutTime(@Param("logoutTime") LocalDateTime logout_time,@Param("email") Long id); 
	
	@Query(nativeQuery = true, value="Select * from login_users where login_id = :emailId and device_id= :deviceId ")
	public LoginUsers findByDevice(@Param("emailId") Long emailId,@Param("deviceId") String deviceId);
}
