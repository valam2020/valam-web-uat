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
	String getCar_name();
	String getCar_registered_id();
	String getImage_url();
	String getStatus();
	//String getImageUrl();
	Long getStatus_id();
	float getDistance();
	String getComfortLevel();
	Long setStatus_id();
	Long SetRide_id();
	String setStatus();

	
}
