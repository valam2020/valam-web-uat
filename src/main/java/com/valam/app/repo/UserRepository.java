package com.valam.app.repo;

import java.util.List;
import java.util.Optional;

import com.valam.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	
	Boolean existsByEmail(String email);
	
	@Query(nativeQuery = true, value="select * from USER_DETAILS where email = :emailId")
	public List<User> findByEmailId(@Param("emailId") String emailId);
	
	@Query(nativeQuery = true, value = "SELECT * FROM USER_DETAILS where PH_NUM = IFNULL(:mob_num, PH_NUM) and email = IFNULL(:emailid, email)") 
	public User findByMobileNumAndEmail(@Param("mob_num") String mob_num, @Param("emailid") String email);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true,value="UPDATE user_details SET Image_Url = :imageUrl WHERE USER_ID = :Id")
	public void updateImage(@Param("imageUrl") String image,@Param("Id") Long id); 
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true,value="Update user_details SET token= :tokendata where USER_ID = :Id")
	public void updateToken(@Param("tokendata") String token,@Param("Id") Long id);
	
	@Query(nativeQuery=true,value="Select * from USER_DETAILS where token= :tokendata")
	public User findByToken(@Param("tokendata") String token);
}
