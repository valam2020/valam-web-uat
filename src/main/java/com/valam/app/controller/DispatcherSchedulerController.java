package com.valam.app.controller;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.dto.DispSchDto;
import com.valam.app.model.DispatcherScheduler;
import com.valam.app.service.CommonApiTokenService;
import com.valam.app.service.DispatcherSchedulerService;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/dispSch")
public class DispatcherSchedulerController {


    @Autowired
    private DispatcherSchedulerService dispSchService;
    
    @Autowired
    private CommonApiTokenService commonTokenService;

    @ApiOperation(value = "api to add the new record for dispatcher Scheduler")
    @PostMapping("/add")
    public DispatcherScheduler addCar(@RequestHeader(value="common_token") String commonToken,@RequestBody DispSchDto dispSchDto) {
    	DispatcherScheduler disshec= new DispatcherScheduler();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		disshec =  dispSchService.saveDispSch(dispSchDto);
    	}else {
    		disshec = null;
    	}
    	return disshec;
    }

    @ApiOperation(value = "api to get all records of dispatcher schedulers")
    @GetMapping("/all")
    public List<DispatcherScheduler> findAllDispSchs(@RequestHeader(value="common_token") String commonToken) {
    	List<DispatcherScheduler> disc = new ArrayList<DispatcherScheduler>();
    			if(commonTokenService.getByTokenId(commonToken) != null) {
    				disc = dispSchService.getDispSchs();
    			} else {
    				disc = null;
    			}
        return disc;
    }

    @ApiOperation(value = "api to get dispatcher scheduled record by id")
    @GetMapping("/{id}")
    public DispatcherScheduler findById(@RequestHeader(value="common_token") String commonToken,@PathVariable Long id) {
        return dispSchService.getDispSchsByID(id);
    }

    @ApiOperation(value = "api to update the record of given dispatcher scheduler by id")
    @PutMapping("/update")
    public DispatcherScheduler update(@RequestHeader(value="common_token") String commonToken,@RequestBody DispSchDto dispSchDto) {
    	DispatcherScheduler disshec= new DispatcherScheduler();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		disshec = dispSchService.updateDispSch(dispSchDto);
    	}else {
    		disshec = null;
    	}
    	return disshec;
    }

    @ApiOperation(value = "api to fetch the records by searching with start date,end date/driver id/car id/dispatcher id in request body")
    @PostMapping("/fetch")
    public List<DispatcherScheduler> findByDataBetweenAndDidCid(@RequestHeader(value="common_token") String commonToken,@RequestBody DispSchDto dispSchDto) {
    	List<DispatcherScheduler> disshec= new ArrayList<DispatcherScheduler>();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		disshec = dispSchService.getDateBetweenandDidandCid(dispSchDto);
    	}else {
    		disshec = null;
    	}
    	return dispSchService.getDateBetweenandDidandCid(dispSchDto);
    }
    
    @ApiOperation(value = "api to get dispatcher scheduled records by dispatcher_id and whose endTime not null")
    @GetMapping("/fetchByDispatcher/{id}")
    public List<DispatcherScheduler> findByDispatcherId(@RequestHeader(value="common_token") String commonToken,@PathVariable Long id) {
    	List<DispatcherScheduler> disshec= new ArrayList<DispatcherScheduler>();
    	if(commonTokenService.getByTokenId(commonToken) != null) {
    		disshec = dispSchService.getByEndTime(id);
    	}else {
    		disshec = null;
    	}
         return disshec;
    }
    

}
