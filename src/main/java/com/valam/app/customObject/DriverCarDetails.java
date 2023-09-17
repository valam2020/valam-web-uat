package com.valam.app.customObject;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface DriverCarDetails {
	
	Long getSchedular_id();
	LocalDateTime getBegin_time();
	LocalDateTime getEnd_time();
	Long getDispatcher_id();
	Long getDriver_id();
	Long getCar_id();
	String getCar_color();
	String getCar_model();
	String getCar_register_id();
	boolean getIs_driver_assigned();
	String getFirst_name();
	String getLast_name();
	String getEmail();
	boolean getIs_car_assigned();
	String getPh_num();
	String getDl_num();
	BigDecimal getDriverCurrent_lat();
	BigDecimal getDriverCurrent_lng();
	boolean getDriverSts_id();

}
