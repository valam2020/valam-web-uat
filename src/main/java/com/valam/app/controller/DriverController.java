package com.valam.app.controller;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;

import org.joda.time.LocalDate;
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

import com.valam.app.customObject.CarObject;
import com.valam.app.dto.CarDriverDto;
import com.valam.app.dto.DispDriverDto;
import com.valam.app.dto.DriverDto;
import com.valam.app.dto.DriverDtoModel;
import com.valam.app.dto.DriverDtoModel2;
import com.valam.app.dto.EmailDetails;
import com.valam.app.dto.ResponseMessage;
import com.valam.app.exception.BadRequestException;
import com.valam.app.model.CarDetails;
import com.valam.app.model.CommonAPIToken;
import com.valam.app.model.DispatcherScheduler;
import com.valam.app.model.Driver;
import com.valam.app.model.DriverLogin;
import com.valam.app.model.RideStatus;
import com.valam.app.repo.CarRepositary;
import com.valam.app.repo.DispatcherSchedulerRepositary;
import com.valam.app.repo.DriverRepositary;
import com.valam.app.service.CarService;
import com.valam.app.service.CommonApiTokenService;
import com.valam.app.service.DispatcherSchedulerService;
import com.valam.app.service.DriverLoginService;
import com.valam.app.service.DriverService;
import com.valam.app.service.EmailService;
import com.valam.app.service.JWTTokenCreator;
import com.valam.app.service.StatusService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverRepositary driverrepo;
    
    @Autowired
    private JWTTokenCreator jwtCreator;
    
    @Autowired
    private CommonApiTokenService capiTokenService;
    
    @Autowired
    private DriverLoginService driverLoginService;
    
    @Autowired
    private StatusService statusService;
    

    @Autowired
    private CarRepositary carRepo;
    

    
    @Autowired private EmailService emailService;
    

    @ApiOperation(value = "api to add a record of Driver ")
    @PostMapping("/add")
    public Driver addDriver(@RequestBody DriverDto driver) {
    	 if (driverrepo.existsByEmail(driver.getEmail())) {
             throw new BadRequestException("Email address already in use.");
         }
    	 Driver drive = driverService.saveDriver(driver);
    	 EmailDetails details = new EmailDetails();
    	 details.setRecipient(driver.getEmail());
    	 details.setSubject("Registered with Valam");
    	 details.setMsgBody("Your are Successfully registered with Valam"+"\n"+"Thank you");
    	 emailService.sendSimpleMail(details);
        return drive;
    }
	
	/*to save all driver records.
	@PostMapping("/addDrivers")
	public List<Driver> saveDrivers(@RequestBody List<Driver> drivers){
		return driverService.saveDrivers(drivers);
	}*/

    @ApiOperation(value = "api to fetch records of all driver under given dispatcher")
    @GetMapping("/fetch")
    public List<Driver> findByDisId(@RequestHeader(value="common_token") String commonToken,@RequestParam(value = "dispatcher_id") Long dispId) {
    	List<Driver> driver = new ArrayList<>();
    	if(capiTokenService.getByTokenId(commonToken).getAuth_common_id() !=null) {
    		driver = driverService.getDriverByDisId(dispId);
    		
    	}else {
    		driver = null;
    	}
    	return driver;
    }

//    @ApiOperation(value = "api to fetch all drivers records without status has InActive")
//    @GetMapping("/test")
//    public List<Driver> findAllDrivers() {
//        return driverService.getDrivers();
//    }

    @ApiOperation(value = "api to fetch driver records by given driver id")
    @GetMapping("/{id}")
    public Driver findDriverById(@RequestHeader(value="token") String token,@PathVariable Long id) {
    	Driver driver = null;
    	if(driverrepo.getByToken(token).getId() != null) {
    		driver = driverService.getDriverByID(id);
        return driver;
    	} else {
    		 return driver;
    	}
    	
    }

    @ApiOperation(value = "api to update the driver records by driver")
    @PutMapping("/update")
    public Driver updateDriver(@RequestHeader(value="token") String token,@RequestBody DriverDto driver) {
    	Driver driverdata = null;
    	if(driverrepo.getByToken(token).getId() != null) {
    			driverdata = driverService.updateDriver(driver);
    	}
		return driverdata;
        
    }

    @ApiOperation(value = "api to delete the driver by given id")
    @PostMapping("/delete")
    public ResponseMessage deleteDriver(@RequestHeader(value="common_token") String token,@RequestBody DriverDto driver) {
    		return  driverService.deleteDriver(token,driver.getId());
    }
    

//    @ApiOperation(value = "api to fetch nearest driver records from given latitude and longitude and dispatcher_id")
//    @PostMapping("/nearest-driver")
//    public List<Driver> findNearestDriver(@RequestHeader(value="common_token") String commonToken,@RequestBody DriverDtoModel2 driver) {
//    	List<Driver> driverList = new ArrayList<>();
//    	if(capiTokenService.getByTokenId(commonToken).getAuth_common_id() !=null) {
//    		driverList = driverService.getNearestDriver(driver.getLatitude(),driver.getLongitude(),driver.getDispatcherId());
//    	}else {
//    		driverList = null;
//    	}
//    	return driverList;
//    }
    
    
    @ApiOperation(value = "api to fetch nearest driver records from given latitude and longitude and dispatcher_id")
    @PostMapping("/nearest-driver")
    public List<Driver> findNearestDrivers(@RequestHeader(value="common_token") String commonToken,@RequestBody DriverDtoModel2 driver) {
    	List<Driver> driverList = new ArrayList<>();
    	List<Driver> driverNewList = new ArrayList<>();
    	List<CarObject> carList = new ArrayList<>();
         Set<Long> cars = new HashSet<>();
       
    	if(capiTokenService.getByTokenId(commonToken).getAuth_common_id() !=null) {
    		driverList = driverService.getNearestDriver(driver.getLatitude(),driver.getLongitude(),driver.getDispatcherId());
    		carList = carRepo.getByComfortLevel(driver.getComfort_level());
    		
    		if(driverList != null && carList != null) {
    			for(CarObject car:carList) {
    				cars.add(car.getCar_id());
    			}
    			for(Driver ndriver :driverList) {
    				if(cars != null && cars.contains(ndriver.getCarId())) {
    					driverNewList.add(ndriver);
    				}else {
    					continue;
    				}
    			}
    			return driverNewList;
    		}
    		
    		return driverNewList;
    	}else {
    		
    		return	driverNewList;
    	} 
    	
    }
    
    public CarDriverDto getcarObj() {
    	CarDriverDto carDriver = new CarDriverDto();
    	carDriver.setCarId(null);    	
		carDriver.setCarColor(null);
		carDriver.setCarModel(null);
		carDriver.setComfortLevel(null);
		carDriver.setCurrent_lat(null);
		carDriver.setCurrent_lng(null);
		carDriver.setDriverid(null);
		carDriver.setDriverid(null);
		carDriver.setFirstName(null);
		carDriver.setLastName(null);
		carDriver.setPhNum(null);
		return carDriver;
    	
    }


    @ApiOperation(value = "api to register driver with valid details.")
    @PostMapping("/signup")
    public DriverDto registerDispatcher(@Valid @RequestBody DriverDto signUpRequest) {
    	DriverDto driverdto = new DriverDto();
    	boolean isExist = driverrepo.existsByEmail(signUpRequest.getEmail());
        if (isExist) {
            driverdto.setHeader((long) 400);
            driverdto.setHttpStatus("Email address already in use.");
            return driverdto;
        }else {  
        	  String token = jwtCreator.getJWTToken(signUpRequest.getEmail());
        	  signUpRequest.setToken(token);
              Driver driver = driverService.saveDriver(signUpRequest);
              CommonAPIToken cpToken = new CommonAPIToken();
              cpToken.setAuth_common_id("Driver-"+LocalDate.now()+"-"+String.valueOf(((int) (Math.random() * (10000 - 1000))) + 1000));
              cpToken.setLoggedin_user_name(driver.getFirstName());
          	  cpToken = capiTokenService.save(cpToken);          	  
          	driverdto.setAddress(driver.getAddress());
        	driverdto.setId(driver.getId());
        	driverdto.set_car_assigned(driver.is_car_assigned());
        	driverdto.setCreatedDate(driver.getCreatedDate());
        	driverdto.setCurrent_lat(driver.getCurrent_lat());
        	driverdto.setCurrent_lng(driver.getCurrent_lng());
        	driverdto.setDateOfBirth(driver.getDateOfBirth());
        	driverdto.setDispatcherId(driver.getDispatcher().getId());
        	driverdto.setDistance(driver.getDistance());
        	driverdto.setDlNum(driver.getDlNum());
        	driverdto.setDriver_status(driver.getDriver_status());
        	driverdto.setEmail(driver.getEmail());
        	driverdto.setFirstName(driver.getFirstName());
        	driverdto.setCarNo(driver.getCarNo());
        	driverdto.setLastName(driver.getLastName());
        	driverdto.setImageUrl(driver.getImageUrl());
        	driverdto.setPhNum(driver.getPhNum());
        	driverdto.setStsId(driver.getRideStatus().getStsId());
        	driverdto.setToken(token);
        	driverdto.setAuth_common_id(cpToken.getAuth_common_id());	  
          	  driverdto.setHeader((long) 200);
              driverdto.setHttpStatus("Driver Registered Successfully");
              return driverdto;
        }
    }

//    @ApiOperation(value = "api to verify driver login")
//    @PostMapping("/login")
//    public Driver findByEmailId(@Valid @RequestBody DriverDtoModel driver) {
//        if (driverrepo.existsByEmail(driver.getEmail()) == null) {
//            throw new BadRequestException("Not Yet Registered");
//        }
//        if (driverService.getByEmailId(driver) != null) {
//            return driverService.getByEmailId(driver);
//        } else
//            throw new BadRequestException("Unauthorized");
//    }

    @ApiOperation(value = "api to verify driver login")
    @PostMapping("/login")
    public DriverDto findByEmailId(@Valid @RequestBody DriverDtoModel driver) {
    	
    	DriverDto driverdto = new DriverDto();
    	CommonAPIToken cpToken = new CommonAPIToken();
    	String token = jwtCreator.getJWTToken(driver.getEmail());
    	Driver driverdata = driverService.getByEmailId(driver);
    	
    	
        if (driverrepo.existsByEmail(driver.getEmail()) == null) {
        	DriverDto driverDto = new DriverDto();
        	//driverDto.setHttpStatus("no");
            return driverDto;
        }
        if (driverdata != null) {
        	cpToken.setAuth_common_id("Driver-"+LocalDate.now()+"-"+String.valueOf(((int) (Math.random() * (10000 - 1000))) + 1000));
        	cpToken.setLoggedin_user_name(driverdata.getFirstName());
        	cpToken = capiTokenService.save(cpToken);
        	driverrepo.updateToken(token, driverdata.getId());
        	//Driver drvr = driverService.getByEmailId(driver);
        	
        	driverdto.setAddress(driverdata.getAddress());
        	driverdto.setId(driverdata.getId());
        	driverdto.set_car_assigned(driverdata.is_car_assigned());
        	driverdto.setCreatedDate(driverdata.getCreatedDate());
        	driverdto.setCurrent_lat(driverdata.getCurrent_lat());
        	driverdto.setCurrent_lng(driverdata.getCurrent_lng());
        	driverdto.setDateOfBirth(driverdata.getDateOfBirth());
        	driverdto.setDispatcherId(driverdata.getDispatcher().getId());
        	driverdto.setDistance(driverdata.getDistance());
        	driverdto.setDlNum(driverdata.getDlNum());
        	driverdto.setDriver_status(driverdata.getDriver_status());
        	driverdto.setEmail(driverdata.getEmail());
        	driverdto.setFirstName(driverdata.getFirstName());
        	driverdto.setCarNo(driverdata.getCarNo());
        	driverdto.setLastName(driverdata.getLastName());
        	driverdto.setHttpStatus("yes");
        	driverdto.setImageUrl(driverdata.getImageUrl());
        	driverdto.setPhNum(driverdata.getPhNum());
        	driverdto.setStsId(driverdata.getRideStatus().getStsId());
        	driverdto.setToken(token);
        	driverdto.setAuth_common_id(cpToken.getAuth_common_id());
            return driverdto;
        }else { 
        	
        	driverdto.setHttpStatus("400");
        	driverdto.setMessage(" Incorrect Password");        
        	return driverdto;
        }
    }
    
    public RideStatus getById(Long id) {
    	RideStatus status = statusService.findByStsId(id).get();
    	return status;
    	}
    
    @ApiOperation(value = "api to find all drivers")
    @GetMapping("/all")
    public List<Driver> findAll(@RequestHeader(value="common_token") String commonToken) {
    	List<Driver> driverList = new ArrayList<>();
    	if(capiTokenService.getByTokenId(commonToken).getAuth_common_id() !=null) {
    	driverList = driverService.getAllDrivers();
    	}else {
    		driverList = null;
    	}
        return driverList;
    }
    
    @ApiOperation(value = "api to change driver password")
    @PostMapping("/forgot-password")
    public Driver forgetPassword(@RequestBody DriverDtoModel driver) {
    	return driverService.resetPassword(driver);
    }
    
    @ApiOperation(value = "api to check driver isExist or not")
    @PostMapping("/exist")
    public Driver isExistorNot(@RequestBody DriverDto driver) {
    	
    	if(driverService.isDriverExist(driver.getEmail(),driver.getPhNum(),driver.getDlNum()) !=null) {
		return driverService.isDriverExist(driver.getEmail(),driver.getPhNum(),driver.getDlNum());}
    	else
    		 throw new BadRequestException("Driver Not Existed");
    }
    
    @ApiOperation(value = "api to find all available drivers")
    @GetMapping("/availableDrivers")
    public List<Driver> getAllAvailableDrivers() {
        return driverService.findAllAvailableDrivers();
    }
    
    @ApiOperation(value = "api to fetch Available driver records by given driver id ")
    @GetMapping("/status/{id}")
    public Driver findDriverAvailableById(@RequestHeader(value="token") String token,@PathVariable Long id) {
    	Driver driver = null;
    	if(driverrepo.getByToken(token).getId() != null) {
    		driver= driverService.isDriverAvailable(id);
    	}
        return driver;
    }
    
    @ApiOperation(value = "api to update the dispatcher id")
    @PostMapping("/updatedispId")
    //@CrossOrigin(origins = "*")
    public Driver updatedispId(@RequestHeader(value="common_token") String commonToken,@RequestBody DispDriverDto driver) {
    	Driver dri = null;
    	if(capiTokenService.getByTokenId(commonToken).getAuth_common_id() !=null) {
        	driverService.updateDispatcherId(driver.getDispatcherId(),driver.getDriverId());  	
        	dri = driverService.getDriverByID(driver.getDriverId());
        	return dri;
    	}else {
    		return dri;
    	}
		
    }
    
//    @ApiOperation(value = "api to fetch Available driver records by given driver id ")
//    @PostMapping("/updateLatLng")
//    public Optional<Driver> updateLatLng(@RequestHeader(value="token") String token,@RequestBody DriverDto driver) {
//    	Optional<Driver> driverdata = null;
//    	if(driverrepo.getByToken(token).getId() != null) {
//    		Driver data = new Driver();
//        	data.setId(driver.getId());
//        	data.setCurrent_lat(driver.getCurrent_lat());
//        	data.setCurrent_lng(driver.getCurrent_lng());
//        	driverdata = driverService.updateLatLngDriver(data);
//    	}
//    	return driverdata;
//    }
    
    @ApiOperation(value = "api to change driver password")
    @PostMapping("/statUpdate")
    public DriverDto changepassword(@RequestBody DriverDto driver) {
    	 driverService.updateByStatusbydriver(driver.getStsId(),driver.getDriverId());
    	 driver.setHttpStatus("200");
    	 driver.setMessage("Successfully Updated");
    	 return driver;
    }
    
}

     
