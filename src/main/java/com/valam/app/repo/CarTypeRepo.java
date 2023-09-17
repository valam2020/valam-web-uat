package com.valam.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valam.app.model.CarList;

@Repository
public interface CarTypeRepo extends JpaRepository<CarList,Long>{
	
	

}
