package com.valam.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="carDetails" , uniqueConstraints = {
		@UniqueConstraint(columnNames ="carId")
})
public class CarDetails implements Serializable { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "carId")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long carId;
    
	@Column(name = "carModel")
	private @NonNull String carModel;
	
	@Column(name="carType")
	private String carType;
	
	@Column(name = "carColor")
	private @NonNull String carColor;
	
	@Column(name = "seat_capacity")
	private  Long seatCapacity;
	
	@Column(name="comfortLevel")
	private @NonNull String comfortLevel;

	@Column(name="is_driver_assigned")
	private boolean is_driver_assigned;
	
	@Column(name="carRegisterId",unique=true)
	private @NonNull String carRegisterId;
	
	@Column(nullable=true)
	private String imageUrl;
	
	@Column(name = "createdDate")
	private LocalDate createdDate;
	
	@Column(name = "modifiedDate")
	private LocalDate modifiedDate;
	
	//to Map the many to one relationship with Dispatcher to car	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dispatcherId",nullable=false, referencedColumnName="dispatcherId")
    @JsonIgnoreProperties(value = {"dispatcher", "hibernateLazyInitializer"})       
    private Dispatcher dispatcher;
	
	// Many to one Relationship between Driver and Status by status_id. To know status of Driver.
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="stsId ",nullable=false, referencedColumnName="stsId")
    @JsonIgnoreProperties(value = {"rideStatus", "hibernateLazyInitializer"})       
    private RideStatus rideStatus;
	
	
	//to Map the one to many relationship with car to Dispatcher
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "carDetails", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<DispatcherScheduler> dispatcherScheduler = new HashSet<DispatcherScheduler>(0);	
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "carDetails", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<RideHistory> rideHistory = new HashSet<RideHistory>(0);
	
	// to map the one to many relationship with payment to payment Details
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "carDetails", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<PaymentDetails> paymentDetails = new HashSet<PaymentDetails>(0);
	
	
	

	
}
