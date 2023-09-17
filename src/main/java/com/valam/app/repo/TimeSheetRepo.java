package com.valam.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valam.app.model.TimeSheet;

@Repository
public interface TimeSheetRepo  extends JpaRepository<TimeSheet, Long>{
	
	

}
