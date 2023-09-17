/**
 * 
 */
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

/**
 * @author home
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="login_dispatchers", uniqueConstraints = {
		@UniqueConstraint(columnNames ="id")
})
public class LoginDispatchers {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "dispatcher_email")
	private @NonNull String email;
	
	@Column(name = "login_time")
	private LocalDateTime login_time;
	
	@Column(name = "logout_time")
	private LocalDateTime logout_time;
	
	@Column(name="device_id")
	private String deviceId;
	
	@Column(nullable = true,name="isLoggedIn")
	private Boolean isLoggedIn;

}
