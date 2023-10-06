package com.valam.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideCommentsDto {
	

	private Long rideId;
	private String admincomments;
	private String executivecomments;
	private Boolean is_reviewbyadmin;
	private Boolean is_reviewbyexecutive;

}
