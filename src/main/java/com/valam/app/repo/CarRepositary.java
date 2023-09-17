package com.valam.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valam.app.customObject.CarObject;
import com.valam.app.dto.CarDto;
import com.valam.app.model.CarDetails;

@Repository
public interface CarRepositary extends JpaRepository<CarDetails, Long>{

	CarDetails save(CarDto car);
    // to fetch the records of all cars under given dispatcher id
	@Query(nativeQuery = true, value="select * from Car_Details where sts_id != 11 and DISPATCHER_ID = :dispId")
	public List<CarDetails> findByDisId(@Param("dispId") Long dispatcherId);
	
	@Query(nativeQuery = true, value="SELECT * FROM car_details where sts_id = 13 and DISPATCHER_ID = :dispId")
	public List<CarDetails> findByStatus(@Param("dispId") Long dispatcher_id);
	
	@Query(nativeQuery = true, value="SELECT * FROM car_details where sts_id = 13")
	public  List<CarDetails> findByAllCarStatus();
	
	@Query(nativeQuery = true, value="SELECT * FROM car_details where car_register_id = :car_registerId")
	CarDetails findByCarRegisterId(@Param("car_registerId") String registerId);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value ="UPDATE car_details SET is_driver_assigned = true WHERE car_id = :carId")
	public void updateDriverAssigned(@Param("carId") Long id);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value ="UPDATE car_details SET is_driver_assigned = false WHERE car_id = :car_Id")
	public void updateDriverASFalse(@Param("car_Id") Long carId);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value ="UPDATE car_details SET sts_id = 11 WHERE car_id = :car_Id")
	public void updateCarAsInActive(@Param("car_Id") Long carId);
    
	
}
