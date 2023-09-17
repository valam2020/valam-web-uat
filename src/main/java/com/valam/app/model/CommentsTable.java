package com.valam.app.model;

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


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="comments_table" , uniqueConstraints = {
		@UniqueConstraint(columnNames ="comment_id")
})
public class CommentsTable {
	
	@Id
	@Column(name = "comment_Id")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long comment_Id;
	
	@Column(name="comment",length=600)
	private String comment;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	
	@Column(name="rating")
	private long rating;
	
	@Column(name="user_id")
	private long userId;
	
	@Column(name="driver_id")
	private long driverId;
	
}
