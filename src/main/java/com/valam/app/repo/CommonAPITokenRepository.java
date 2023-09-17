package com.valam.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.valam.app.model.CommonAPIToken;


@Repository
public interface CommonAPITokenRepository extends JpaRepository<CommonAPIToken,Long>{
	
	@Query(nativeQuery=true,value="Select * from common_API_Token where auth_common_id=:commonId")
	CommonAPIToken getByTokenId(@Param("commonId") String commonId);
	

}
