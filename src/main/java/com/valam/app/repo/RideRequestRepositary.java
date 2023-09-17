package com.valam.app.repo;


import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.valam.app.customObject.RideRequest_Dto;
import com.valam.app.model.RideRequest;

@Repository
public interface RideRequestRepositary extends JpaRepository<RideRequest, Long>{


	//Optional<RideLocation> findByStsId(Long stsId); 
	//to fetch the all ride location under given status id and DispatcherId
	@Query(nativeQuery = true, value="Select * from Ride_Request where STS_ID = IFNULL(:stsId,STS_ID) AND dispatcher_id = IFNULL(:disId,dispatcher_id) AND IFNULL(DRIVER_ID,0) = IFNULL(:dId, 0) AND DATE(Ride_date) >= DATE(IFNull(:rideDate, Ride_date));")
	List<RideRequest> findByDisStsId(@Param("stsId") Long stsId,@Param("disId") Long dispatcherId,@Param("dId") Long driverId,@Param("rideDate") LocalDate ride_date);
    
	@Modifying
	@Transactional
	@Query(nativeQuery =true,value="UPDATE Ride_Request SET STS_ID = 11 WHERE ride_loc_id = :rideLocId")
	void update(@Param("rideLocId") Long ride_LocId);
	
	@Modifying
	@Transactional
	@Query(nativeQuery =true,value="UPDATE Ride_Request SET STS_ID = 3 WHERE ride_loc_id = :rideLocId")
	void updateByRideReqId(@Param("rideLocId") Long ride_LocId);
	
	
	@Query(nativeQuery = true,value =" Select * from Ride_Request where dispatcher_id = :disId")
	List<RideRequest> findAllByDispatcherId(@Param("disId") Long dispatcherId);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true,value="UPDATE Ride_Request SET STS_ID = 3 WHERE ride_loc_id = :rideId")
	void updateByRideId(@Param("rideId") long ride_id); 
	
	@Query(nativeQuery = true, value="Select ride_loc_id,dispatcher_id,ride_id,sts_id from Ride_Request where STS_ID != 4 and dispatcher_id = IFNULL(:disId,dispatcher_id) and IFNULL(RIDE_ID,0) = IFNULL(:ride_id,0);")
	List<RideRequest_Dto> findByDisId(@Param("disId") Long dispatcherId,@Param("ride_id") Long rideId);
    
	@Query(nativeQuery = true,value="SELECT * FROM ride_request where ride_id = :rideId and sts_id = 4")
	RideRequest isAlreadyRequestedOrNot(@Param("rideId") Long ride_id);

	
	}
