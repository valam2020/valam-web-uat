package com.valam.app.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="dispatcher_details" , uniqueConstraints = {
		@UniqueConstraint(columnNames ="dispatcherId")
})
public class Dispatcher {
	
	@Id
	@Column(name = "dispatcherId")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
    
	@Column(name = "lastName")
	private @NonNull String lastName;
	
	@Email
	@Column(name = "email")
    private @NonNull String email;
	
	@Column(name = "phNum")
	private @NonNull String phNum;
	
	@Column(name = "address")
	private @NonNull String address;
	
	@Column(name = "firstName")
	private @NonNull String firstName;
	
	@JsonIgnore
	@Column(nullable=true,name="password")
	private String password;
	
	@Column(name = "disRegId")
	private @NonNull String disRegId;
	
	@Column(nullable=true)
	private String imageUrl;
	
	@Column(name = "createdDate")
	private LocalDate createdDate;
	
	@Column(name = "modifiedDate")
	private LocalDate modifiedDate;
	
	@Column(name="token",nullable=true,length=500)
	private String token;
	
	@Column(columnDefinition = "DECIMAL(10,8)", name = "current_lat",nullable=true)
	private BigDecimal latitude;
		
	@Column(columnDefinition = "DECIMAL(11,8)", name = "current_lng",nullable=true)
	private BigDecimal  longitude;
	
	@Column(name = "pincode")
	private String pincode;
	
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	//to Map the one to many relationship with  car to Dispatcher 
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dispatcher", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<CarDetails> car = new HashSet<CarDetails>(0);
	
	//to Map the one to many relationship with  Driver to Dispatcher
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dispatcher", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<Driver> driver = new HashSet<Driver>(0);
	
	//to Map the one to many relationship with  Dispatcher to RideRequest
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dispatcher", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<RideRequest> rideReq = new HashSet<RideRequest>(0);
	
    
	//to Map the one to many relationship with Dispatcher scheduler to dispatcher 
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dispatcher", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<DispatcherScheduler> dispSchler = new HashSet<DispatcherScheduler>(0);	
	
	//to Map the one to many relationship with Dispatcher scheduler to dispatcher 
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dispatcher", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<RideHistory> rideHistory = new HashSet<RideHistory>(0);

	
	
}
