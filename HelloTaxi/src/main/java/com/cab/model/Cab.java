package com.cab.model;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cab {
	
	@Id
	@Column(unique = true)
	private Integer cabNumber;
	
	
	private ArrayList<Integer> currentPos;
	
	
	private String model;
	
	
	
	
}
