 package com.valam.app.model;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="user_details", uniqueConstraints = {
		@UniqueConstraint(columnNames ="userId"),@UniqueConstraint(columnNames ="email")
})
public class User {
 
	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private @NonNull String name;
	
	@Column(nullable = true,name = "firstName")
	private String firstName;

	@Column(nullable = true,name = "lastName")
	private String lastName;
	
	@Column(nullable = true,name = "middleName")
	private String middleName;

	@Email
	@Column(name = "email")
    private @NonNull String email;
	
	@Column(nullable = true,name ="is_major")
	private boolean is_major;
	
	private String imageUrl;
	
	@Column(nullable = true)
	private Boolean emailVerified = true; 
	
	@Column(name = "phNum")
	private @NonNull String phNum;

	@JsonIgnore
	private String password;
	
	@Column(name="token",nullable=true,length = 500)
	private String token;
	
	@Enumerated
	private AuthProvider provider;
	
	private @NonNull String social_signup_id;
	
	@Column(name = "createdDate")
	private LocalDate createdDate;
	
	@Column(name = "modifiedDate")
	private LocalDate modifiedDate;
	
	@Column(columnDefinition = "DECIMAL(10,8)", name = "latitude")
	private BigDecimal latitude;
	
	@Column(columnDefinition = "DECIMAL(11,8)", name = "longitude")
	private BigDecimal  longitude; 
	
	//to map one to many relation ship with Ride Details and User
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<RideHistory> rideDetails = new HashSet<RideHistory>(0); 
	
		
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true,fetch = FetchType.LAZY)
	private Set<PaymentDetails> payment = new HashSet<PaymentDetails>(0);


}
