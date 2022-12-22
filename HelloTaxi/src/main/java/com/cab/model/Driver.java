package com.cab.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer driverId;
	
	@Pattern(regexp = "^[a-zA-Z]{1,25}$", message = "First Name shouldn't have numbers and Special character")
	private String firstName;
	
	@Pattern(regexp = "^[a-zA-Z]{1,25}$", message = "Last Name shouldn't have numbers and Special character")
	private String lastName;
	
	@Pattern(regexp = "^[0-9]{10}", message = "Mobile Number should be in 10 digits")
	@Column(unique = true)
	private String moblieNumber;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,12}$", message = "Password should be alphanumeric and must contain 6-12 characters having at least one special character, one upper case, one lowercase, and one digit.")
	private String password;
	
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cab cab;
	
}
