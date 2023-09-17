package com.valam.app.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="login_users", uniqueConstraints = {
		@UniqueConstraint(columnNames ="id")
})
public class LoginUsers {

	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "login_id")
	private @NonNull Long login_id;
	
	@Column(name = "login_time")
	private LocalDateTime login_time;
	
	@Column(name = "logout_time")
	private LocalDateTime logout_time;
	
	@Column(name="device_id")
	private String deviceId;
	
	@Column(nullable = true,name="isLoggedIn")
	private Boolean isLoggedIn;
}
