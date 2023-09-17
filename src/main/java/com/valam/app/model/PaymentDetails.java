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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="paymentDetails" , uniqueConstraints = {
		@UniqueConstraint(columnNames ="paymentId")
})
public class PaymentDetails implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "paymentId")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long paymentId;
    
	@Column(name = "distance", nullable =false)
	private float  distance;
	
	@Column(name="price")
	private float price;
	
	@Column(name="paymentType")
	private String paymentType; 
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId ",nullable=false, referencedColumnName="userId")
    @JsonIgnoreProperties(value = {"user", "hibernateLazyInitializer"})       
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="driverId",nullable=false, referencedColumnName="driverId")
    @JsonIgnoreProperties(value = {"driver", "hibernateLazyInitializer"})       
    private Driver driver;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="carId",nullable=false, referencedColumnName="carId")
    @JsonIgnoreProperties(value = {"carDetails", "hibernateLazyInitializer"})       
    private CarDetails carDetails;
	
	@Column(name="totalAmount")
	private float totalAmount;
	
	@Column(name = "createdDate")
	private LocalDateTime createdDate;


}
