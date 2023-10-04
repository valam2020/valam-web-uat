package com.valam.app.model;

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
	
	@Column(name = "customerId")
	private Long customerId;
	
	@Column(nullable = true,name ="rideId")
	private Long rideId;
	
	@Column(nullable = true,name ="reason")
	private String reason;
	
	@Column(name = "usercomments",length=600)
	private String usercomments;
	
	@Column(name = "admincomments",length=600)
	private String admincomments;
	
	@Column(name = "executivecomments",length=600)
	private String executivecomments;
	
	@Column(name = "reviewbyadmin",length=600)
	private String reviewbyadmin;
	
 	@Column(name = "reviewbyexecutive",length=600)
	private String reviewbyexecutive;
 	
 	@Column(name ="is_reviewbyadmin")
	private Boolean is_reviewbyadmin;
 	
 	@Column(name ="is_reviewbyexecutive")
	private Boolean is_reviewbyexecutive;

}
