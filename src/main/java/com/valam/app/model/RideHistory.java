package com.valam.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name ="ride_history", uniqueConstraints = {
		@UniqueConstraint(columnNames ="ride_id")})
public class RideHistory implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ride_id")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long rideId;
	
	@Column(length = 255, name = "fromAddress")
	private @NonNull String fromAddress;
	
	@Column(length = 255, name = "toAddress")
	private @NonNull String toAddress;
    
    @Column(name="pickupDate")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime pickupDate;
	
	@Column(name="dropDate")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dropDate;
	
	@Column(name = "distance", nullable =false)
	private float distance;
	
	@Column(name="payment_type")
	private String paymentType; 
	
	@Column(name="payment_total")
	private float paymentTotal;
	
	@Column(columnDefinition = "DECIMAL(10,8)", name = "pickupLat",nullable=false)
	private BigDecimal pickupLat;
	
	@Column(columnDefinition = "DECIMAL(11,8)", name = "pickupLng",nullable=false)
	private BigDecimal  pickupLng;
	
	@Column(columnDefinition = "DECIMAL(10,8)", name = "dropLat",nullable=false)
	private BigDecimal dropLat;
	
	@Column(columnDefinition = "DECIMAL(11,8)", name = "dropLng",nullable=false)
	private BigDecimal dropLng;
	@Column(name="expiryTimer")
	private long expiryTimer;
	
	@Column(name="otp")
	private String otp;
	
	@Column(name="message")
	private String message;
	
	@Column(name="comfort_level")
	private String comfort_level;
	
	@Column(name = "admincomments",length=600)
	private String admincomments;
	
 	@Column(name = "executivecomments",length=600)
	private String executivecomments;
 	
 	@Column(name ="is_reviewbyadmin")
	private Boolean is_reviewbyadmin;
 	
 	@Column(name ="is_reviewbyexecutive")
	private Boolean is_reviewbyexecutive;
	  //to map many to one relation ship with User and RideHistory
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name="userId ",nullable=true, referencedColumnName="userId")
	  @JsonIgnoreProperties(value = {"user", "hibernateLazyInitializer"}) 
	  private User user;
	 
   
       //to map many to one relation ship with Driver and Ride History
       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name="driverId ",nullable=true, referencedColumnName="driverId")
       @JsonIgnoreProperties(value = {"driver", "hibernateLazyInitializer"})       
       private Driver driver;
     
       //to map many to one relation ship with Car Details and RideHistory
       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name="carId",nullable=true, referencedColumnName="carId")
       @JsonIgnoreProperties(value = {"carDetails", "hibernateLazyInitializer"})       
       private CarDetails carDetails;
       
          
       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name="dispatcherId",nullable=true, referencedColumnName="dispatcherId")
       @JsonIgnoreProperties(value = {"dispatcher", "hibernateLazyInitializer"})       
       private Dispatcher dispatcher;
       
     //to map many to one relation ship with Ride_Status and Ride Location
       @ManyToOne(fetch = FetchType.LAZY)
       @JoinColumn(name="stsId ",nullable=true, referencedColumnName="stsId")
       @JsonIgnoreProperties(value = {"rideStatus", "hibernateLazyInitializer"})       
       private RideStatus rideStatus;
       
       
      @JsonIgnore
  	  @OneToMany(cascade = CascadeType.ALL, mappedBy = "ride_history", orphanRemoval = true,fetch = FetchType.LAZY)
  	  private Set<RideRequest> rideReq = new HashSet<RideRequest>(0);


     }   
