package com.valam.app.controller;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valam.app.model.RideStatus;
import com.valam.app.service.StatusService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/status")
public class StatusController {


    @Autowired
    private StatusService statusService;

    @ApiOperation(value = "api to add new status type in status table")
    @PostMapping("/add")
    public RideStatus addSts(@RequestBody RideStatus status) {
        return statusService.saveStatus(status);
    }

    @ApiOperation(value = "api to fetch all ride_status ")
    @GetMapping("/all")
    public List<RideStatus> findAllSts() {
        return statusService.getAllStatus();
    }

    @ApiOperation(value = "api to delete the record of ride_status by id")
    @DeleteMapping("/deleteSts/{id}")
    public String deleteRS(@PathVariable Long id) {
        statusService.deleteRideStatus(id);
        return "Status is Removed !!" + id;
    }

    @ApiOperation(value = "api to change/update the ride_status by id")
    @PutMapping("/updateStatus")
    public RideStatus update(@RequestBody RideStatus rs) {
        return statusService.updateRideStatus(rs);
    }
    
//    @ApiOperation(value = "api to find ride_status by id")
//    @GetMapping("/all")
//    public RideStatus findByStsId(@) {
//        return statusService.getAllStatus();
//    }

}
