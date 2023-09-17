package com.valam.app.model;

import java.math.BigDecimal;
import java.time.LocalDate;


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
@Table(name ="timeSheet" , uniqueConstraints = {
		@UniqueConstraint(columnNames ="timeSheetId")
})
public class TimeSheet {
	
	@Id
	@Column(name = "timeSheetId")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long timeSheetId;
	
	@Column(name = "employeeId")
	private @NonNull String employeeId;
	
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "hours")
	private BigDecimal hours;
	
	
	@Column(name = "timeSheet",length = 600)
	private String timeSheet;
	
}
