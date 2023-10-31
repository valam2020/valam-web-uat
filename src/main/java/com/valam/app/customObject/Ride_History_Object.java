package com.valam.app.customObject;

import java.time.LocalDate;

public interface Ride_History_Object {
    
	Long getRide_id();
	String getFromAddress();
	String getToAddress();
	double getPayment_total();
	String getPayment_type();
	LocalDate getDrop_date();
	String getDriver_name();
	String getDriver_email();
	String getDriver_phnum();
	String getCar_name();
	String getCar_registered_id();
	String getStatus();
	//String getImageUrl();
	Long getSts_id();
	float getDistance();
	String getComfortLevel();
	Long setStatus_id();
	Long SetRide_id();
	String setStatus();
    String getuser_name();
    Long getUser_id();
    Long getDriver_id();
    Long getDispatcher_id();
    Long getCar_id();
    String getEmail();
    String getuser_phnum();
	
}
