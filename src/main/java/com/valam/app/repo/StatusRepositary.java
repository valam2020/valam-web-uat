package com.valam.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.valam.app.customObject.RideRequest_Dto;
import com.valam.app.model.RideStatus;

@Repository
public interface StatusRepositary extends JpaRepository<RideStatus, Long>{
	
//	@Query(nativeQuery = true, value="Select * from ride_status where STS_ID = :stsId ")
//	List<RideRequest_Dto> findByDisId(@Param("stsId") Long stsId);
}
