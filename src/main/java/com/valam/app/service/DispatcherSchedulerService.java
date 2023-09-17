package com.valam.app.service;

import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valam.app.dto.DispSchDto;
import com.valam.app.model.CarDetails;
import com.valam.app.model.Dispatcher;
import com.valam.app.model.DispatcherScheduler;
import com.valam.app.model.Driver;
import com.valam.app.repo.CarRepositary;
import com.valam.app.repo.DispatcherRepositary;
import com.valam.app.repo.DispatcherSchedulerRepositary;
import com.valam.app.repo.DriverRepositary;




@Service
public class DispatcherSchedulerService {
	
	@Autowired
	private CarRepositary carRepo; 
	
	 @Autowired
	 private CarService carService;
	 
	 @Autowired
	 private DriverService driverService;
	 
	@Autowired
	private DispatcherRepositary dispatcherRepo;
	
	@Autowired
	private DispatcherSchedulerRepositary dispSchRepo; 
	
	@Autowired
	private DriverRepositary driverRepo;
	
	//to save Dispatcher Scheduler Details with Driver id,car id dispatcher.this method is for schedule/Booking process. 
	public DispatcherScheduler saveDispSch(DispSchDto dipschDto) {
		
	    Dispatcher dispatcher = dispatcherRepo.findById(dipschDto.getDispatcherId()).get();
		dispatcher.setId(dispatcher.getId());
				
		Driver driver = driverRepo.findById(dipschDto.getDriverId()).get();
		driver.setId(driver.getId());
		
		CarDetails car = carRepo.findById(dipschDto.getCarId()).get();
		car.setCarId(car.getCarId());
		
		DispatcherScheduler dispatcherScheduler = new DispatcherScheduler();
		dispatcherScheduler.setDispatcher(dispatcher);
		dispatcherScheduler.setCarDetails(car);
		dispatcherScheduler.setDriver(driver);
		dispatcherScheduler.setBeginTime(dipschDto.getBeginTime());
		dispatcherScheduler.setEndTime(dipschDto.getEndTime());
		//DispatcherScheduler sched = dispSchRepo.save(dispatcherScheduler);
		Long carId = dipschDto.getCarId();
		Long driverId = dipschDto.getDriverId();
		carService.updateCar_Assigned(carId);
		CarDetails carData = carService.getCarByID(carId);
		driverService.updateCarAssignedStatus(carData.getCarRegisterId(),driverId);
		return dispSchRepo.save(dispatcherScheduler);
		
		
	}

	//to fetch all Dispatcher Schedulers
    public List<DispatcherScheduler> getDispSchs(){
	           return dispSchRepo.findAll();
    }
    
    
    //to fetch the Dispatcher Schedule by Id
    public DispatcherScheduler getDispSchsByID(Long id) {
	    return dispSchRepo.findById(id).orElse(null);
    } 
    
    //to update the records of Dispatcher Scheduler by given diver,car, dispatcher 
    public DispatcherScheduler updateDispSch(DispSchDto dipschDto) {
    		
    	    Dispatcher dispatcher = dispatcherRepo.findById(dipschDto.getDispatcherId()).get();
    		dispatcher.setId(dispatcher.getId());
    		
    		Driver driver = driverRepo.findById(dipschDto.getDriverId()).get();
    		driver.setId(driver.getId());
    		
    		CarDetails car = carRepo.findById(dipschDto.getCarId()).get();
    		car.setCarId(car.getCarId());
    		
    		DispatcherScheduler dispatcherScheduler = dispSchRepo.findById(dipschDto.getSchedulerId()).orElse(null);
    		dispatcherScheduler.setDispatcher(dispatcher);
    		dispatcherScheduler.setCarDetails(car);
    		dispatcherScheduler.setDriver(driver);
    		dispatcherScheduler.setBeginTime(dipschDto.getBeginTime());
    		dispatcherScheduler.setEndTime(dipschDto.getEndTime());
    		if(dipschDto.getEndTime() != null) {
    			Long car_Id = dipschDto.getCarId();
    			Long driverId  = dipschDto.getDriverId();
    	       driverRepo.updateCarAssignedFalse(driverId);
    	       carRepo.updateDriverASFalse(car_Id);
    	       DispatcherScheduler scheduler = dispSchRepo.save(dispatcherScheduler);
    	       return dispSchRepo.findById(scheduler.getSchedulerId()).get();
    		}else
    			return dispSchRepo.save(dispatcherScheduler);    		
    }
       
    /*to fetch all dispatcher scheduler records by given Dates and driver,car and dispatcher. */
    public List<DispatcherScheduler> getDateBetweenandDidandCid(DispSchDto dipschDto){
            if(dipschDto.getBeginDate() == null && dipschDto.getEndDate() == null && dipschDto.getCarId() == null && dipschDto.getDriverId() == null && dipschDto.getDispatcherId() == null) {
            	dipschDto.getBeginDate();
				dipschDto.setBeginDate(LocalDate.now());
				//dipschDto.getEndDate();
				//dipschDto.setEndDate(LocalDate.now());
		     }
    	
           return dispSchRepo.findByDateBetweenandDidandCid(dipschDto.getBeginDate(),dipschDto.getEndDate(),dipschDto.getDriverId(),dipschDto.getCarId(),dipschDto.getDispatcherId());
    }
    
    public List<DispatcherScheduler> getByEndTime(Long dispatcher_id){
    	return dispSchRepo.getByDetailsDispatcher(dispatcher_id);
    }
    
    
    
}
