package com.valam.app.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="subjectLines" , uniqueConstraints = {
		@UniqueConstraint(columnNames ="subjectId")
})
public class SubjectLines {
	
	@Id
	@Column(name = "subjectId")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long subjectId;
	
	@Column(name="subject")
	private String subject;
	
	@Column(name = "createdDate")
	private LocalDate createdDate;
	
	@Column(name = "modifiedDate")
	private LocalDate modifiedDate;

}
