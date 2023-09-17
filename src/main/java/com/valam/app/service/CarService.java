package com.valam.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valam.app.dto.CarDto;
import com.valam.app.dto.CarListDto;
import com.valam.app.dto.ResponseMessage;
import com.valam.app.model.CarDetails;
import com.valam.app.model.CarList;
import com.valam.app.model.Dispatcher;
import com.valam.app.model.RideStatus;
import com.valam.app.repo.CarRepositary;
import com.valam.app.repo.CarTypeRepo;
import com.valam.app.repo.DispatcherRepositary;
import com.valam.app.repo.StatusRepositary;

@Service
public class CarService { 
	
	@Autowired
	private CarRepositary carRepo; 
	
	@Autowired
	private  StatusRepositary statusRepo;
	
	@Autowired
	private DispatcherRepositary dispatcherRepo;
	
	@Autowired
	private CarTypeRepo carTypeRepo;
	
	//to save car details under given dispatcher
	public CarDetails saveCar(CarDto car) {
		
		Dispatcher dispatcher = dispatcherRepo.findById(car.getDispatcherId()).get();
		dispatcher.setId(dispatcher.getId());
		
		RideStatus status = statusRepo.findById(car.getStsId()).get();
		status.setStsId(status.getStsId());
		
		CarDetails carDetails = new CarDetails();
		carDetails.setDispatcher(dispatcher);
		carDetails.setRideStatus(status);
		carDetails.setCreatedDate(LocalDate.now());
		carDetails.setCarModel(car.getCarModel());
		carDetails.set_driver_assigned(car.is_driver_assigned());
		carDetails.setCarColor(car.getCarColor());
		carDetails.setCarType(car.getCarType());
		carDetails.setSeatCapacity(car.getSeatCapacity());
		carDetails.setComfortLevel(car.getComfortLevel());
		carDetails.setCarRegisterId(car.getCarRegisterId());
		carDetails.setImageUrl(car.getImageUrl());
		
		return carRepo.save(carDetails);
	}
	
	public List<CarList>  getCarList(){
		return carTypeRepo.findAll();
	}
	
	public CarList createCarType(CarListDto car) {
		CarList carList = new CarList();
		carList.setComfortLevel(car.getComfortLevel());
		carList.setSeatCapacity(null);
		return carTypeRepo.save(carList);
	}
	
	//to fetch all cars
	public List<CarDetails> getCars(){
		return carRepo.findAll();
	}
    
	//to fetch car by given car id
	public CarDetails getCarByID(Long id) {
		return carRepo.findById(id).orElse(null);
	} 
	
	//to fetch all cars under given dispatcher
	public List<CarDetails> getCarByDisId(Long disId) {
		return carRepo.findByDisId(disId);
	} 
	
	//to delete a car by id
	public ResponseMessage deleteCar(Long id) {
		carRepo.updateCarAsInActive(id);
		ResponseMessage message = new ResponseMessage();
		if(carRepo.existsById(id) == true) {
			message.setHttpStatus(200);
			message.setMessage("Successfully Deleted" +id);
		}
		else {
			message.setHttpStatus(400);
			message.setMessage("Not Found" +id);
		}
		return message;
	}
    
	//to update the car details by car id
	public CarDetails updateCar(CarDto carDto) { 
		
		Dispatcher dispatcher = dispatcherRepo.findById(carDto.getDispatcherId()).get();
		dispatcher.setId(dispatcher.getId());
		
		RideStatus status = statusRepo.findById(carDto.getStsId()).get();
		status.setStsId(status.getStsId());

		CarDetails carDetails = carRepo.findById(carDto.getCarId()).orElse(null);
		carDetails.setDispatcher(dispatcher);
		carDetails.setRideStatus(status);
		carDetails.setCarModel(carDto.getCarModel());
		carDetails.setCarColor(carDto.getCarColor());
		carDetails.set_driver_assigned(carDto.is_driver_assigned());
		carDetails.setCarType(carDto.getCarType());
		carDetails.setModifiedDate(carDto.getModifiedDate());
		carDetails.setSeatCapacity(carDto.getSeatCapacity());
		carDetails.setComfortLevel(carDto.getComfortLevel());
		carDetails.setCarRegisterId(carDto.getCarRegisterId());
		carDetails.setImageUrl(carDto.getImageUrl());
		return carRepo.save(carDetails);
	}
	
	public List<CarDetails> getByStatus(Long id) {
		return carRepo.findByStatus(id);
	}
	
	public  List<CarDetails> getAllByCarStatus(){
		return carRepo.findByAllCarStatus();
	}
	
	public void updateCar_Assigned(Long carId) {
		carRepo.updateDriverAssigned(carId);
		return ;
	}
	
	public ResponseMessage getByRegisteredId(String email) {
		String registeredId = carRepo.findByCarRegisterId(email).getCarRegisterId();
		ResponseMessage message = new ResponseMessage();
		if(registeredId != null) {
			message.setMessage("Car details found by registered id");
			message.setExist(true);
			message.setHttpStatus(200);
		}
		else {
			message.setMessage("Car details not found");
			message.setExist(false);
			message.setHttpStatus(400);
		}
		return message;
	}  
	
	

}
