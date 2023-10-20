package com.valam.app.customObject;

import java.time.LocalDate;

public interface DeclinedRideObject {

	Long getRide_id();
	String getFromAddress();
	String getToAddress();
	String getMessage();
	String getComfortLevel();
	Long getCar_id();
	Long getUser_id();
	Long getDriver_id();
	Long getDispatcher_id();
	Long getStatus_id();
	String getStatus_name();
	LocalDate getRideDate();
	
	
}
