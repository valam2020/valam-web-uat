package com.valam.app.controller;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.dto.DispDriverDto;
import com.valam.app.dto.DispatcherDto;
import com.valam.app.dto.DispatcherDto_1;
import com.valam.app.dto.DispatcherLoginDto;
import com.valam.app.exception.BadRequestException;
import com.valam.app.model.Dispatcher;
import com.valam.app.model.Driver;
import com.valam.app.model.LoginDispatchers;
import com.valam.app.model.RideStatus;
import com.valam.app.repo.DispatcherRepositary;
import com.valam.app.service.CommonApiTokenService;
import com.valam.app.service.DispatcherService;
import com.valam.app.service.DriverService;
import com.valam.app.service.LoginDispatcherService;
import com.valam.app.service.StatusService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dispatcher")
public class DispatcherController {

    @Autowired
    private DispatcherService dispatcherService;

    
    @Autowired
    private DispatcherRepositary dispRepo;
    
    @Autowired
    private CommonApiTokenService capiTokenService;
    
    @Autowired
    private LoginDispatcherService dispService;
    
    @Autowired
    private StatusService statusService;
    
    @Autowired
    private DriverService driverService;


    @ApiOperation(value = "api to get all dispatchers")
    @GetMapping("/all")
    public List<Dispatcher> findAll() {
        return dispatcherService.getAll();
    }

    /*
     * @GetMapping("/getAll") public Dispatcher findAllId() { return
     * dispatcherService.getAllId(); }
     */
    @ApiOperation(value = "api to add new dispatcher record ")
    @PostMapping("/add")
    public Dispatcher addDispatcher(@RequestBody DispatcherDto dispatcherDto) {
        return dispatcherService.saveDispatcher(dispatcherDto);
    }

    @ApiOperation(value = "api to update the dispatcher record by id ")
    @PostMapping("/update")
    public Dispatcher updateDisp(@RequestHeader(value="token") String token,@RequestBody DispatcherDto dispatcherDto) {
    	Dispatcher dispatcher = null;
    	if(dispRepo.getByToken(token).getId() != null) {
    		dispatcher = dispatcherService.updateDispatcher(dispatcherDto);
    	}
        return dispatcher;
    }

    @ApiOperation(value = "api to get the dispatcher record by id ")
    @GetMapping("/{id}")
    public Dispatcher findById(@RequestHeader(value="token") String token,@PathVariable Long id) {
    	Dispatcher dispatcher = null;
    	if(dispRepo.getByToken(token).getId() != null) {
    		dispatcher = dispatcherService.getDispatcherByID(id);
    	}
        return dispatcher;
    }

    @ApiOperation(value = "api to delete dispatcher by id")
    @PostMapping("/delete")
    public DispatcherDto_1 deleteDisp(@RequestHeader(value="token") String token,@RequestBody DispatcherDto dispatcherDto) {
    	DispatcherDto_1 disdto = null;
    	if(dispRepo.getByToken(token).getId() != null) { 
    		disdto = dispatcherService.deleteDispatcher(dispatcherDto.getDispatcherId());
    	}
        return disdto;
    }

    @ApiOperation(value = "api to register the dispatcher")
    @PostMapping("/signup")
    public Dispatcher register1Dispatcher(@Valid @RequestBody DispatcherDto dispatcherDto) {
        if (dispRepo.existsByEmail(dispatcherDto.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }
        return dispatcherService.saveDispatcher(dispatcherDto);
    }

    @SuppressWarnings("null")
	@ApiOperation(value = "api to verify login dispatcher")
    @PostMapping("/signin")
    public DispatcherDto login(@Valid @RequestBody DispatcherLoginDto dispatcher) {
          DispatcherDto dispatcher1 = null;
        if (dispRepo.existsByEmail(dispatcher.getEmail()) == false) {
        	 Long stsId = (long) 29;
         	RideStatus status = getById(stsId);
         	if(status.getStsId() !=null) {
         		dispatcher1.setStsId(status.getStsId());
             	dispatcher1.setMessage(status.getStatusName());
         	}
         	
        }
        LoginDispatchers logindisp = dispService.findByemail(dispatcher.getEmail());
        if(logindisp !=null && logindisp.getLogout_time() == null) {
        	 dispatcher1 = new DispatcherDto();
        	 Long stsId = (long) 28;
          	RideStatus status = getById(stsId);
          	dispatcher1.setStsId(status.getStsId());
          	dispatcher1.setMessage(status.getStatusName());
        	//dispatcher1.setMessage("Dispatcher was already loggedin with different device");
        	return dispatcher1; 
        }else { 
        	DispatcherDto_1  dispatcher_ = new DispatcherDto_1();
        	dispatcher_.setEmail(dispatcher.getEmail());
        	dispatcher_.setPassword(dispatcher.getPassword());      	
        	DispatcherDto  dispatcher2 = dispatcherService.getByEmailId(dispatcher_);
        	if(logindisp == null) {
        		dispService.createLogin(dispatcher.getEmail(),dispatcher.getDeviceId());
        	}else if (dispatcher2 != null) {
                 	//dispService.updatelogInTime(dispatcher.getEmail());
        	          Long stsId = (long) 27;
              	      RideStatus status = getById(stsId);
              	      dispatcher2.setStsId(status.getStsId());
              	      dispatcher2.setMessage(status.getStatusName());
        		      
                     return dispatcher2;
                 } else 
                	 dispatcher2 = null;
                      return dispatcher2;
        }
    }
    
    @ApiOperation(value = "api to verify login dispatcher")
    @PostMapping("/login")
    public DispatcherDto findByEmailId(@Valid @RequestBody DispatcherDto_1 dispatcher) {

        if (dispRepo.existsByEmail(dispatcher.getEmail()) == false) {
            throw new
                    BadRequestException("Not Yet Registered");
        }
        DispatcherDto dispatcher1 = dispatcherService.getByEmailId(dispatcher);
        if (dispatcher1 != null) {
            return dispatcher1;
        } else
            return null;
    }
    
    public RideStatus getById(Long id) {
    	RideStatus status = statusService.findByStsId(id).get();
    	return status;
    	}
    
    @ApiOperation(value = "api for forgot password to change dispatcher login password")
    @PostMapping("/forgot-password")
    public Dispatcher forgotPassword(@RequestBody DispatcherDto_1 dispatcher) {
        return dispatcherService.resetPassword(dispatcher);
    }

    @ApiOperation(value = "api to get dispatcher by email")
    @PostMapping("/get-email")
    public boolean getByEmail(@RequestBody DispatcherDto_1 dispatcher) {
        return dispatcherService.CheckDispatcherExists(dispatcher.getEmail());
    }
    
  

}
