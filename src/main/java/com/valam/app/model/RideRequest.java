package com.valam.app.model;

import java.io.Serializable;
import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="ride_request" , uniqueConstraints = {
		@UniqueConstraint(columnNames ="ride_loc_id")
})
public class RideRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ride_loc_id")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long ride_loc_id;
	
	
	@Column(name="ride_date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime ride_date;
	
	
	//to map many to one relation ship with Ride_Status and Ride Location
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="stsId ",nullable=false, referencedColumnName="stsId")
    @JsonIgnoreProperties(value = {"rideStatus", "hibernateLazyInitializer"})       
    private RideStatus rideStatus;
	

	//to map many to one relation ship with Ride_Status and Ride Location
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ride_id ",nullable=false, referencedColumnName="ride_id")
	@JsonIgnoreProperties(value = {"ride_history", "hibernateLazyInitializer"})       
	private RideHistory ride_history;
	
	//to map many to one relation ship with Ride_Status and Ride Location
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="dispatcherId ",nullable=false, referencedColumnName="dispatcherId")
    @JsonIgnoreProperties(value = {"dispatcher", "hibernateLazyInitializer"})       
	private Dispatcher dispatcher;
	
	 //to map many to one relation ship with Driver and Ride History
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="driverId ",nullable=true, referencedColumnName="driverId")
    @JsonIgnoreProperties(value = {"driver", "hibernateLazyInitializer"})       
    private Driver driver;

	
}
