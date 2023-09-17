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
@Table(name ="dispatcherScheduler" , uniqueConstraints = {
		@UniqueConstraint(columnNames ="schedulerId")
})
public class DispatcherScheduler implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "schedulerId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long schedulerId;
    
	@Column(name="beginTime")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime beginTime;
	
	@Column(name="endTime")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime endTime;
	
	
	//to map many to one relation ship with Dispatcher and dispatcher scheduler
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="dispatcherId",nullable=false, referencedColumnName="dispatcherId")
    @JsonIgnoreProperties(value = {"dispatcher", "hibernateLazyInitializer"})       
    private Dispatcher dispatcher;
	
	//to map many to one relation ship with Car Details and dispatcher scheduler
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="carId",nullable=false, referencedColumnName="carId")
    @JsonIgnoreProperties(value = {"carDetails", "hibernateLazyInitializer"})       
    private CarDetails carDetails;
	
	//to map many to one relation ship with Driver and dispatcher scheduler
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="driverId",nullable=false, referencedColumnName="driverId")
    @JsonIgnoreProperties(value = {"driver", "hibernateLazyInitializer"})       
    private Driver driver;
	 
}
