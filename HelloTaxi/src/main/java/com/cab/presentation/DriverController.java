package com.cab.presentation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cab.exception.DriverException;
import com.cab.model.Driver;
import com.cab.model.dto.CreateDriverDTO;
import com.cab.service.DriverService;

@RestController
@RequestMapping("/masaicab/driver")
public class DriverController {
	
	@Autowired
	private DriverService dService;

	@PostMapping("/register")
	public ResponseEntity<Driver> registerDriver(@Valid @RequestBody CreateDriverDTO driverDto) throws DriverException{
		Driver driver = dService.registerDriver(driverDto);
		
		return new ResponseEntity<Driver>(driver, HttpStatus.CREATED);
	}
}
