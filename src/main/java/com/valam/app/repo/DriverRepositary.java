package com.valam.app.repo;

import java.math.BigDecimal;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valam.app.dto.DriverDto;
import com.valam.app.model.Driver;

@Repository
public interface DriverRepositary extends JpaRepository<Driver, Long>{

	Driver save(DriverDto driver);
    
	//to fetch all driver records by given dispatcher id
	@Query(nativeQuery = true, value="select * from Driver_Details where DISPATCHER_ID = :disId and sts_id !=11")
	public List<Driver> findByDisId(@Param("disId") Long dispatcherId);
	
	@Query(nativeQuery = true, value="select * from Driver_Details where DISPATCHER_ID = :disId and sts_id !=11")
	public List<Driver> findBydriverBystatus(@Param("disId") Long dispatcherId);
	
	/*
	 * @Query(nativeQuery = true,
	 * value="SELECT driver_id FROM driver_details WHERE dispatcher_id = :disId ")
	 * public List<Driver> findByDisId(@Param("disId") Long dispatcherId);
	 */
	
	/* Great Circle Diagram. here it draws the dynamic all circle with driver latitude 
	and longitude and finds the distance. by ASC will nearest Driver with certain radius.*/
	@Query(nativeQuery = true,value = "select *,\n" +
            "          ( 111.1111  * acos( cos( radians(:latitude) ) \n" +
            "      * cos( radians( current_lat )) \n" +
            "      * cos( radians( current_lng ) - radians(:longitude)) \n" +
            "      + sin( radians(:latitude) ) \n" +
            "      * sin( radians( current_lat ) ) ) ) AS distance \n" +
            "        from Driver_Details where driver_status != 'ride_queue' AND sts_id=2 AND sts_id != 11 AND DISPATCHER_ID = IFNULL(:disId, DISPATCHER_ID)\n" +
            " and is_car_assigned = true HAVING distance < 50 Order By distance" )
      public List<Driver> findDistance(
            @Param("latitude") BigDecimal latitude,
            @Param("longitude") BigDecimal longitude, @Param("disId") Long dispatcherId);
	
	@Query(nativeQuery = true, value="select * from Driver_Details where Driver_ID =1")
	public List<Driver> findByDriverId();
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="Update Driver_Details SET driver_status = 'no_ride',is_car_assigned=false,sts_id = 26,DISPATCHER_ID = 1 where Driver_ID = :id")
    public void deleteByDriverId(@Param("id") Long id);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="Update Driver_Details SET driver_status = 'ride_queue' where Driver_ID = :id")
    public void updateByDriverStatus(@Param("id") Long id);
	
	@Query(nativeQuery = true, value="select * from Driver_Details where sts_id !=11")
	public List<Driver> findByALLDrivers();
	
	Boolean existsByEmail(String email);
    
	@Query(nativeQuery = true, value="select * from Driver_Details where email = :emailId And password = :passcode and sts_id !=11")
	Driver findByEmailId(@Param("emailId") String email, @Param("passcode") String password);
	
	@Query(nativeQuery = true, value="select * from Driver_Details where sts_id !=11 and email = :emailId")
	Driver findByEmail(@Param("emailId") String email);
	
	@Query(nativeQuery = true, value="select * from Driver_Details where sts_id !=11 and email = IFNULL(:emailid, email) and ph_num = IFNULL(:phNum, ph_num) and dl_num = IFNULL(:dlNum, dl_num)")
	Driver findByExist (@Param("emailid") String emailid, @Param("phNum") String phNum, @Param("dlNum") String dlNum);
	
	@Query(nativeQuery = true,value ="select * from Driver_Details where sts_id !=11 and driver_status = \"Available\" and Driver_ID = :id")
	Driver getAvailableDrivers (@Param("id") Long id);
	
	@Query(nativeQuery = true,value="select *from Driver_Details where sts_id !=11 and driver_status = \"Available\"")
	List<Driver> getAllAvailableDrivers();
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="UPDATE Driver_Details SET is_car_assigned = true,car_no= :carNo WHERE sts_id !=11 and driver_id = :driverid")
	public void updateCarAssigned(@Param("carNo") String car_no,@Param("driverid") Long id);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="UPDATE Driver_Details SET DISPATCHER_ID =:dispatcherId,sts_id = 30 WHERE driver_id = :driverid")
	public void updateDispatcherId(@Param("dispatcherId") Long dispatcherId,@Param("driverid") Long id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="UPDATE Driver_Details SET is_car_assigned = false WHERE sts_id !=11 and driver_id = :driverid")
	public void updateCarAssignedFalse(@Param("driverid") Long driverId);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="UPDATE Driver_Details SET current_lat = :lat,current_lng=:lng WHERE driver_id =:driverid")
	public void updatedriverLatLng(@Param("lat") BigDecimal lat,@Param("lng") BigDecimal lng,@Param("driverid") Long driverId);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true,value="Update Driver_Details SET token =:tokenData where driver_id =:driverid")
	public void updateToken(@Param("tokenData") String token,@Param("driverid") Long driverId);
	
	@Query(nativeQuery = true,value="Select * from Driver_Details where token = :tokenData")
	public Driver getByToken(@Param("tokenData") String token);
	
	
}
