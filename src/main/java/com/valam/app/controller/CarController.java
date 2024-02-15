package com.valam.app.controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.dto.CarDto;
import com.valam.app.dto.CarListDto;
import com.valam.app.dto.ResponseMessage;
import com.valam.app.dto.RideHistory_Dto;
import com.valam.app.model.CarDetails;
import com.valam.app.model.CarList;
import com.valam.app.model.RideHistory;
import com.valam.app.repo.RideHistoryRepositary;
import com.valam.app.service.CarService;
import com.valam.app.service.CommonApiTokenService;
import com.valam.app.service.DispatcherSchedulerService;

import io.swagger.annotations.ApiOperation;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;
   
    @Autowired
    private CommonApiTokenService commonTokenService;
    
    @Autowired
    private DispatcherSchedulerService dispSchService;
    
    @Autowired
    private RideHistoryRepositary rideHisrepo;
    
    @ApiOperation(value = "api to add new car details")
    @PostMapping("/add")
    public CarDetails addCar(@RequestHeader(value="common_token") String commonToken,@RequestBody CarDto car) {
    	CarDetails carDetails = new CarDetails();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		carDetails = carService.saveCar(car);
    	}else {
    		carDetails = null;
    	}
        return carDetails;
    }
    
    @ApiOperation(value = "api to fetch all cars details ")
    @GetMapping("/carsList")
    public List<CarList> findCarsList(){
    	return carService.getCarList();
    }
    
    @ApiOperation(value = "api to add car type in CarsList ")
    @PostMapping("/addCarType")
    public CarList createCarType(@RequestBody CarListDto car){
    	return carService.createCarType(car);
    }

    @ApiOperation(value = "api to fetch all cars details ")
    @GetMapping("/cars")
    public List<CarDetails> findAllCars(@RequestHeader(value="common_token") String commonToken) {
    	List<CarDetails> carDetails = new ArrayList<>();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		carDetails = carService.getCars();
    	}else {
    		carDetails = null;
    	}
        return carDetails;
    }

    @ApiOperation(value = "api to fetch car details by given id")
    @GetMapping("/{id}")
    public CarDetails findById(@PathVariable Long id) {
        return carService.getCarByID(id);
    }

    @ApiOperation(value = "api to fetch the records of all cars under given dispatcher id in request param")
    @GetMapping("/fetch")
    public List<CarDetails> findByDisId(@RequestHeader(value="common_token") String commonToken,@RequestParam(value = "dispatcher_id") Long dispId) {
    	List<CarDetails> carDetails = new ArrayList<>();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		carDetails = carService.getCarByDisId(dispId);
    	}
    	else {
    		carDetails = null;
    	}
    	return carDetails;
    }

    @ApiOperation(value = "api to Delete car record by id ")
    @PostMapping("/delete")
    public ResponseMessage deleteCar(@RequestHeader(value="common_token") String commonToken,@RequestBody CarDto car) {
    	 ResponseMessage message = new ResponseMessage();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		boolean iscarassigned = carService.getCarByID(car.getCarId()).is_driver_assigned();
    		RideHistory_Dto rideDto = new RideHistory_Dto();
    		rideDto.setCarId(car.getCarId());
    		rideDto.setPickupDate(LocalDate.now());
    		rideDto.setStatusId((long) 15);
    		List<RideHistory> ride = rideHisrepo.findByDateBetween(rideDto.getPickupDate(),null,null,null,null,rideDto.getStatusId(),rideDto.getCarId());
    		if(iscarassigned && ride != null) {
    			carService.deleteCar(car.getCarId());
    			message.setHttpStatus(201);
    	        message.setMessage("Successfully Deleted"+car.getCarId());
    	        return message;
    		}  
    	}else {
    	
	        message.setHttpStatus(400);
	        message.setMessage("Not Deleted"+car.getCarId());
	        return message;
    	}
		return message;
        
    }

    @ApiOperation(value = "api to update car details by car id")
    @PutMapping("/update")
    public CarDetails update(@RequestHeader(value="common_token") String commonToken,@RequestBody CarDto car) {
    	CarDetails carDetails = new CarDetails();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		carDetails = carService.updateCar(car);
    	}else {
    		carDetails = null;
    	}
        return carDetails;
    }
    
    @ApiOperation(value = "api to fetch car details by given id whose status is Available")
    @GetMapping("/carStatus")
    public List<CarDetails> findByStatus(@RequestParam Long id) {
    	if(carService.getByStatus(id) != null) {
    		return carService.getByStatus(id);
    	}else 
    		return null;
    }
    
    @ApiOperation(value = "api to fetch all car details whose status is Available")
    @GetMapping("/status")
    public List<CarDetails> AvailableCars() {
    	if(carService.getAllByCarStatus() != null) {
    		return carService.getAllByCarStatus();
    	}else 
    		return null;
    }
    
    @ApiOperation(value="api to check is car exist in car_details")
    @PostMapping("/isExist")
    public ResponseMessage getByCarRegisterId(@RequestHeader(value="common_token") String commonToken,@RequestBody CarDto car) {
    	ResponseMessage message = new ResponseMessage();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		message = carService.getByRegisteredId(car.getCarRegisterId());
    	}else {
    		message.setHttpStatus(400);
    		message.setMessage("Not Exist");
    	}
    	return message;
    }
    
    
    

}

