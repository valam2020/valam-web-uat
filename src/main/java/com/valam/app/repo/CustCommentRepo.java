package com.valam.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.valam.app.model.CustomerCommentTable;


@Repository
public interface CustCommentRepo extends JpaRepository<CustomerCommentTable,Long>{

	
	@Query(nativeQuery = true, value="select * from customer_comment_table where customer_id = :customerId")
	public CustomerCommentTable findByCustomerId(@Param("customerId") Long customerId);
	
	@Query(nativeQuery = true, value="select * from customer_comment_table where ride_id = :rideId")
	public List<CustomerCommentTable> findByRideId(@Param("rideId") Long rideId);
}
