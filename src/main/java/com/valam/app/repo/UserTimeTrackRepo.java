package com.valam.app.repo;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valam.app.customObject.UserTTObject;
import com.valam.app.model.UserTimeTrackTable;

@Repository
public interface UserTimeTrackRepo extends JpaRepository<UserTimeTrackTable,Long>{
	
	@Query(nativeQuery=true,value="SELECT TIMESTAMPDIFF(Hour,MAX(login_time), MIN(logout_time)) as time_taken FROM user_time_track_table WHERE time_trackingid=:timeTrackingID")
	public UserTTObject getUserTrackingTime(@Param("timeTrackingID") Long timeTrackingID);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="UPDATE user_time_track_table SET logout_time = :logoutTime WHERE customer_loginid = :customerloginid")
	public void updateLogoutTime(@Param("logoutTime") LocalDateTime logout_time, @Param("customerloginid") Long customer_loginid);

}
