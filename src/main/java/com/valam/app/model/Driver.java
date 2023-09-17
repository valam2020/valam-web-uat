package com.valam.app.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="driver_details" , uniqueConstraints = {
		@UniqueConstraint(columnNames ="driverId")
})
public class Driver { 
	
	@Id
	@Column(name = "driverId")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
    
	@Column(name = "firstName", length = 30)
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Email
	@Column(name = "email")
	private String email;
	
	@Column(name = "phNum")
	private String phNum;
	
	@Column(name = "dlNum")
	private String dlNum;
	
	@Column(name = "createdDate")
	private LocalDate createdDate;
	
 	@Column(name = "modifiedDate")
	private LocalDate modifiedDate;
	
	@Column(name = "address")
	private String address;
	
	@Column(nullable=true,name = "imageUrl")
	private String imageUrl; 
	
	@Column(nullable=true,name = "dlfrontImage")
	private String dlfrontimage; 
	
	@Column(nullable=true,name = "dlbackImage")
	private String dlbackimage; 
	
	@Column(name = "driver_status")
	private String driver_status;
	
	@Column(name="is_car_assigned")
	private boolean  is_car_assigned;
	
	@Column(name="dateofbirth")
	private LocalDate dateOfBirth;
	
	@Column(name="car_no")
	private String carNo;
	
	@Column(name="header")
	private Long header;
	
	@Column(name="token",nullable=true,length=500)
	private String token;
	
	@JsonIgnore
	@Column(nullable=true,name="password")
	private String password;
	
	@Column(columnDefinition = "DECIMAL(10,8)", name = "distance")
	private BigDecimal distance;
	
	// Current_latitude of Driver
	@Column(columnDefinition = "DECIMAL(10,8)", name = "current_lat",nullable=true)
	private BigDecimal current_lat;
	
	// Current_longtitude of Driver
	@Column(columnDefinition = "DECIMAL(11,8)", name = "current_lng",nullable=true)
	private BigDecimal  current_lng;
	
	// Ride History maps the Driver columns by driver_id.
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "driver", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<RideHistory> udrides = new HashSet<RideHistory>(0); 
	
	// DispatcherScedular maps the Driver columns by driver_id.
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "driver", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<DispatcherScheduler> dispSchler = new HashSet<DispatcherScheduler>(0);
	
	// to map the one to many relationship with driver to payment Details
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "driver", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<PaymentDetails> payment = new HashSet<PaymentDetails>(0);
	
	// to map the one to many relationship with driver to Ride Request
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "driver", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<RideRequest> rideReq = new HashSet<RideRequest>(0);
	
	// Many to one Relationship between Driver and Dispatcher by dispatcher_id. 
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dispatcherId",nullable=false, referencedColumnName="dispatcherId")
    @JsonIgnoreProperties(value = {"dispatcher", "hibernateLazyInitializer"})       
    private Dispatcher dispatcher; 
	
	// Many to one Relationship between Driver and Status by status_id. To know status of Driver.
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="stsId ",nullable=false, referencedColumnName="stsId")
    @JsonIgnoreProperties(value = {"rideStatus", "hibernateLazyInitializer"})       
    private RideStatus rideStatus;

	// Many to one Relationship between Driver and Status by status_id. To know status of Driver.
	//	@ManyToOne(fetch = FetchType.LAZY)
	//    @JoinColumn(name="carId ",nullable=false, referencedColumnName="carId")
	//    @JsonIgnoreProperties(value = {"carDetails", "hibernateLazyInitializer"})       
	//    private CarDetails carDetails;

	}
