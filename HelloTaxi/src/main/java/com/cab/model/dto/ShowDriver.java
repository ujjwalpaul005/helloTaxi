package com.cab.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDriver {
	
	private Integer driverId;

	private String firstName;
	
	private String lastName;
	
	private String moblieNumber;
	
	private String password;
	
	private Integer cabNumber;
	
	private String cabModel;
	
	private double cabDistance;
}
