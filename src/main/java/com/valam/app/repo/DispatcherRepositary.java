package com.valam.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valam.app.model.Dispatcher;
import lombok.NonNull;

@Repository
public interface DispatcherRepositary extends JpaRepository<Dispatcher, Long>{

	boolean existsByEmail(@NonNull String email);

	@Query(nativeQuery = true, value="select * from Dispatcher_Details where is_deleted != true and email = :emailId And password = :passcode")
	Dispatcher findByEmailId(@Param("emailId") String email, @Param("passcode") String password);
	
	
	@Query(nativeQuery = true, value="select * from Dispatcher_Details where is_deleted != true and address = :add")
	Dispatcher findByAddress(@Param("add") String address);
	
	@Query(nativeQuery = true, value="select * from Dispatcher_Details where is_deleted != true and email = :emailId")
	Dispatcher findByEmail(@Param("emailId") String email);
	
	@Modifying
	@Query(nativeQuery = true, value="UPDATE dispatcher_details SET password = :password WHERE is_deleted != true and email= :emailId ")
	Dispatcher forgotPassword(@Param("password") String password,@Param("emailId") String email);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value="UPDATE dispatcher_details SET is_deleted = true WHERE dispatcher_id = :dispatcherId")
	void deleteDispatcherById(@Param("dispatcherId") Long dispatcherId);
    
	@Modifying
	@Transactional
	@Query(nativeQuery = true,value="Update dispatcher_details SET token =:tokenData where email =:emailId")
	public void updateToken(@Param("tokenData") String token,@Param("emailId") String email);
	
	@Query(nativeQuery = true,value="Select * from dispatcher_details where token = :tokenData")
	public Dispatcher getByToken(@Param("tokenData") String token);
	/*
	 * @Query(nativeQuery = true, value="SELECT car_id carId,driver_id DriverId\r\n"
	 * + "FROM car_details  JOIN driver_details\r\n" + "USING (dispatcher_id);")
	 * Dispatcher getByDispatcher();
	 */
}
