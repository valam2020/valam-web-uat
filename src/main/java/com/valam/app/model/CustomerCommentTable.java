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
@Table(name ="customerCommentTable" , uniqueConstraints = {
		@UniqueConstraint(columnNames ="commentId")
})
public class CustomerCommentTable {
	
	@Id
	@Column(name = "commentId")
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long commentId;
	
	@Column(name = "empId")
	private String empId;
	
	@Column(name = "userId")
	private Long userId;
	
	@Column(nullable = true,name ="rideId")
	private Long rideId;
	
	@Column(nullable = true,name ="reason")
	private String reason;
	
	@Column(name = "usercomments",length=600)
	private String usercomments;
	
	@Column(name = "createdDate")
	private LocalDate createdDate;
	
 	@Column(name = "modifiedDate")
	private LocalDate modifiedDate;
	

}
