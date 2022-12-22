package com.cab.model.dto;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	@Pattern(regexp = "^[a-zA-Z]{1,25}$", message = "First Name shouldn't have numbers and Special character")
	private String firstName;
	
	@Pattern(regexp = "^[a-zA-Z]{1,25}$", message = "Last Name shouldn't have numbers and Special character")
	private String lastName;
	
	@Pattern(regexp = "^[0-9]{10}", message = "Mobile Number should be in 10 digits and unique")
	@Column(unique = true)
	private String moblieNumber;
	
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,12}$", message = "Password should be alphanumeric and must contain 6-12 characters having at least one special character, one upper case, one lowercase, and one digit.")
	private String password;
	
	
	private ArrayList<Integer> currentPosition = new ArrayList<>();
}
