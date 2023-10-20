package com.valam.app.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.valam.app.model.CustomerLogin;


@Repository
public interface CustomerLoginRepo extends JpaRepository<CustomerLogin,Long>{
	
	boolean existsByEmail(String email);
	
	@Query(nativeQuery = true, value="select * from customer_login where email = :emailId")
	public CustomerLogin findByEmailId(@Param("emailId") String emailId);
	
	@Query(nativeQuery = true, value="select * from customer_login where emp_id = :empId")
	public CustomerLogin findByEmpId(@Param("empId") String empId);
	

}
