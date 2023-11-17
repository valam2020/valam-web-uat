package com.valam.app.repo;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valam.app.customObject.DispatcherSchedularObject;
import com.valam.app.model.Dispatcher;
import com.valam.app.model.DispatcherScheduler;



@Repository
public interface DispatcherSchedulerRepositary extends JpaRepository<DispatcherScheduler, Long>, JpaSpecificationExecutor<DispatcherScheduler>{
    
	// to fetch records by given start date,end date/driver id/dispatcher id/car id
	@Query(nativeQuery = true, value="select * FROM dispatcher_scheduler where DATE(BEGIN_TIME) >= DATE(IFNULL(:startDate, BEGIN_TIME)) \r\n"
			+ "AND  DATE(IFNULL(END_TIME,CURDATE()))  <= DATE(IFNULL(:endDate,CURDATE())) AND DRIVER_ID = IFNULL(:dId, DRIVER_ID) \r\n"
			+ "AND CAR_ID=IFNULL(:cId, CAR_ID) AND DISPATCHER_ID = IFNULL(:disId, DISPATCHER_ID) order by SCHEDULER_ID DESC;")
	public List<DispatcherScheduler> findByDateBetweenandDidandCid(@Param("startDate") LocalDate fromDate, @Param("endDate") LocalDate toDate,@Param("dId") Long dId,@Param("cId") Long cId,@Param("disId") Long dispId);
	
	
	@Query(nativeQuery = true, value="SELECT * FROM dispatcher_scheduler where END_TIME  IS NULL")
	public List<DispatcherScheduler> getEndTimedata();
	
	@Query(nativeQuery = true,value ="SELECT CAR_ID as car_id FROM dispatcher_scheduler where End_Time is null and driver_id = :driverId and dispatcher_id = :disp_id")
	DispatcherSchedularObject findbyDriverId(@Param("driverId") Long dId, @Param("disp_id") Long dispatcher_id);
	
	@Query(nativeQuery = true,value="Select * from dispatcher_scheduler where dispatcher_id = :dispId and End_Time is null")
	List<DispatcherScheduler> getByDetailsDispatcher(@Param("dispId") Long dispatcher_id); 
	
	@Query(nativeQuery = true,value="Select * from dispatcher_scheduler where dispatcher_id = :dispId and driver_id = IFNULL(:driverId, driver_id) and End_Time is null")
	List<DispatcherScheduler> getByDetailsDispatcherandDriver(@Param("dispId") Long dispatcher_id,@Param("driverId") Long dId); 
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="UPDATE dispatcher_scheduler SET End_Time = :end_time WHERE dispatcher_id = :disp_id")
	public void updateEndTime(@Param("end_time") LocalDateTime endTime,@Param("disp_id") Long dispatcher_id);
	
}
