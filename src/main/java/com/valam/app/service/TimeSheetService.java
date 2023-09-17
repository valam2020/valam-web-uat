package com.valam.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valam.app.dto.TimeSheetDto;
import com.valam.app.model.TimeSheet;
import com.valam.app.repo.TimeSheetRepo;

@Service
public class TimeSheetService {
	
	@Autowired
	TimeSheetRepo timeSheetRepo;
	
	public Optional<TimeSheet> getById(Long id) {
		return timeSheetRepo.findById(id);
	}
	
	public List<TimeSheet> getAll() {
		return timeSheetRepo.findAll();
	}
	
	public TimeSheet save(TimeSheetDto tSheetDto) {
		
		TimeSheet sheet = new TimeSheet();
		sheet.setEmployeeId(tSheetDto.getEmployeeId());
		sheet.setHours(tSheetDto.getHours());
		sheet.setDate(tSheetDto.getDate());
		sheet.setTimeSheet(tSheetDto.getTimeSheet());
		return timeSheetRepo.save(sheet);
	}
	
	public TimeSheet update(TimeSheetDto tSheetDto) {
		TimeSheet sheet = timeSheetRepo.findById(tSheetDto.getTimeSheetId()).get();
		if(sheet != null) {
			sheet.setHours(tSheetDto.getHours());
			sheet.setTimeSheet(tSheetDto.getTimeSheet());
			return timeSheetRepo.save(sheet);
		}else {
		
			return null;
		}
	}
	
	public void deleteTimeSheet(Long id) {
		timeSheetRepo.deleteById(id); 
	}
	
	
	
	
	
	

}
