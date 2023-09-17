package com.valam.app.repo;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valam.app.customObject.Ride_History_Object;
import com.valam.app.model.RideHistory;






@Repository
public interface RideHistoryRepositary extends JpaRepository<RideHistory, Serializable>{  
    
	//to fetch the records based on ride Dates
	
         
	// to fetch records by given start date,end date/driver id/dispatcher id/car id
    @Query(nativeQuery = true, value="select * FROM RIDE_HISTORY where DATE(pickup_date) >= DATE(IFNull(:pickUpDate, pickup_date)) AND  DATE(IFNULL(drop_date,CURDATE()))  <= DATE(IFNULL(:dropDate,CURDATE())) AND IFNULL(DRIVER_ID,0) = IFNULL(:dId, DRIVER_ID)\r\n"
    		+ "			AND IFNULL(User_ID,0) = IFNULL(:userId,User_ID) AND IFNULL(DISPATCHER_ID,0) = IFNULL(:dispatcherId, DISPATCHER_ID) and IFNULL(sts_id,0) = IFNULL(:stsId, sts_id) order by RIDE_ID DESC;")
    public List<RideHistory> findByDateBetween(@Param("pickUpDate") LocalDate pickup_Date, @Param("dropDate") LocalDate drop_Date,@Param("dId") Long dId,@Param("userId") Long uId,@Param("dispatcherId") Long disptId,@Param("stsId") Long statusId);
    
 // to fetch records by given start date,end date/driver id/dispatcher id/car id
    @Query(nativeQuery = true, value="select * FROM RIDE_HISTORY where DATE(pickup_date) >= DATE(IFNull(:pickUpDate, pickup_date)) AND  DATE(IFNULL(drop_date,CURDATE()))  <= DATE(IFNULL(:dropDate,CURDATE())) AND IFNULL(DRIVER_ID,0) = IFNULL(:dId, DRIVER_ID)\r\n"
    		+ "			AND IFNULL(User_ID,0) = IFNULL(:userId,User_ID) AND IFNULL(DISPATCHER_ID,0) = IFNULL(:dispatcherId, DISPATCHER_ID) and IFNULL(sts_id,0) = IFNULL(:stsId, sts_id) order by RIDE_ID DESC;")
    public RideHistory findByDDS(@Param("pickUpDate") LocalDate pickup_Date, @Param("dropDate") LocalDate drop_Date,@Param("dId") Long dId,@Param("userId") Long uId,@Param("dispatcherId") Long disptId,@Param("stsId") Long statusId);
    
    
    @Modifying
	@Transactional
	@Query(nativeQuery=true,value ="UPDATE RIDE_HISTORY SET sts_id = 3 WHERE RIDE_ID = :rideId")
    public void updateStatus(@Param("rideId") Long ride_id);
    
    @Modifying
   	@Transactional
   	@Query(nativeQuery=true,value ="UPDATE RIDE_HISTORY SET drop_date = :dropDate,sts_id = :status_id WHERE RIDE_ID = :rideId")
       public void updateStatusafterComplete(@Param("dropDate") LocalDateTime drop_date,@Param("status_id") Long sts_id,@Param("rideId") Long ride_id);
    
    @Modifying
   	@Transactional
   	@Query(nativeQuery=true,value ="UPDATE RIDE_HISTORY SET sts_id = :sts_id WHERE RIDE_ID = :rideId")
       public void updateStatusBeforeStarted(@Param("sts_id") Long sts_id,@Param("rideId") Long ride_id);
    
    
    @Query(nativeQuery=true,value= "Select Ride_ID as ride_id,FROM_ADDRESS as fromAddress,TO_ADDRESS as toAddress,payment_total,payment_type,CAST( drop_date AS DATE) as drop_date,concat(dd.first_name,dd.last_name) as driver_name,cd.car_model as car_name,cd.car_register_id as car_registered_id,dd.image_url as Image_url,rs.status_name as status from ride_history rd Inner Join driver_details dd on dd.driver_id = rd.DRIVER_ID Inner Join car_details cd on cd.car_id =rd.CAR_ID Inner join ride_status rs on rs.sts_id = rd.sts_id and rd.sts_id In (4,10) and rd.USER_ID = ifnull(:userId,rd.USER_ID) and rd.DRIVER_ID = ifnull(:driverId,rd.Driver_id) order by RIDE_ID DESC")
    public List<Ride_History_Object> rideTrips(@Param("userId") Long user_id,@Param("driverId") Long driver_id);
    
    @Query(nativeQuery=true,value="SELECT ride_id as ride_id,rs.sts_id as status_id,rs.status_name as status FROM ride_history rh Join ride_status rs on rs.sts_id = rh.sts_id where rh.sts_id = 15 and rh.RIDE_ID = :ride_id")
    public Ride_History_Object getRideByrideSts_15(@Param("ride_id") long rideId);
    
    @Query(nativeQuery=true,value="SELECT ride_id as ride_id,rs.sts_id as status_id,rs.status_name as status FROM ride_history rh Join ride_status rs on rs.sts_id = rh.sts_id where rh.sts_id = 10 and rh.RIDE_ID = :ride_id")
    public Ride_History_Object getRideByrideSts_10(@Param("ride_id") long rideId);
    
    
    @Query(nativeQuery=true,value="select * FROM RIDE_HISTORY where DATE(pickup_date) >= DATE(IFNull(:pickupdate, pickup_date)) AND  DATE(IFNULL(drop_date,CURDATE()))  <= DATE(IFNULL(null,CURDATE())) AND IFNULL(User_ID,0) = IFNULL(:userId,User_ID) and sts_id != 10 order by RIDE_ID DESC ;")
    public List<RideHistory> findByPrevRideByUserId(@Param("pickupdate") LocalDate pickup_Date,@Param("userId") Long uId);

    
	}

    
   

	
