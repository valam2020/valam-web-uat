package com.valam.app.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="userTimeTrackTable" , uniqueConstraints = {
		@UniqueConstraint(columnNames ="timeTrackingID")
})
public class UserTimeTrackTable {
	
	@Id
	@Column(name = "timeTrackingID")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long timeTrackingID;
	
	@Column(name = "customerLoginID")
	private Long customerLoginID;
	
	@Column(name = "login_time")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
	private LocalDateTime login_time;
	
	@Column(name = "logout_time")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
	private LocalDateTime logout_time;
	
	
	
//	@Column(name = "time_duration")
//	private LocalTime time_duration;
	
}
