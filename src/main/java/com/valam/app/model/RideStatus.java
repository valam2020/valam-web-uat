package com.valam.app.model;

import java.io.Serializable;
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
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="rideStatus", uniqueConstraints = {
		@UniqueConstraint(columnNames ="stsId")})
public class RideStatus implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  
	@Id
	@Column(name = "stsId")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long stsId;
	
	private @NonNull String StatusName; 
	
	//to map one to many relation ship with Ride Location and Ride Status
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "rideStatus", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<RideRequest> rideLoc = new HashSet<RideRequest>(0);
	
	//to map one to many relation ship with Driver and Ride Status
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "rideStatus", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<Driver> driver = new HashSet<Driver>(0);
	
	//to map one to many relation ship with RideHistory and Ride Status
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "rideStatus", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<RideHistory> rideHistory = new HashSet<RideHistory>(0);
	
	//to map one to many relation ship with CarDetails and Ride Status
		@JsonIgnore
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "rideStatus", orphanRemoval = true,fetch = FetchType.LAZY)
		private Set<CarDetails> car_details = new HashSet<CarDetails>(0);
	
}
