package com.cab.service;

import com.cab.exception.DriverException;
import com.cab.model.Driver;
import com.cab.model.dto.CreateDriverDTO;

public interface DriverService {
	
	public Driver registerDriver(CreateDriverDTO driver) throws DriverException;

}
