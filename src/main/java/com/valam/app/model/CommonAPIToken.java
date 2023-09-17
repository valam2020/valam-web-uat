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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="common_API_Token" , uniqueConstraints = {
		@UniqueConstraint(columnNames ="token_id")
})
public class CommonAPIToken {
	
	@Id
	@Column(name = "token_id")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long token_id;

	@Column(name="auth_common_id")
	private String auth_common_id;
	
	@Column(name="loggedin_user_name")
	private String loggedin_user_name;
	
	@Column(name="loggedin_date")
	private LocalDateTime loggedin_date;
	
	@Column(name="is_enabled",nullable=true)
	private boolean is_enabled;
}
