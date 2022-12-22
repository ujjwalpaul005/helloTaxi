package com.cab.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.exception.DriverException;
import com.cab.model.Cab;
import com.cab.model.Driver;
import com.cab.model.dto.CreateDriverDTO;
import com.cab.repository.DriverRepo;
import com.cab.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService{
	
	@Autowired
	private DriverRepo dRepo;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public Driver registerDriver(CreateDriverDTO driverDto) throws DriverException {
		Driver temp = null;
		
		Driver driver = getDriverfromDriver(driverDto);
		
		try {
			temp = dRepo.save(driver);
		}catch (Exception e) {
			throw new DriverException("use unique mobile number or unique cab number.");
		}
	
		return temp;
	}
	
	public Driver getDriverfromDriver(CreateDriverDTO driverDto) {
		Driver driver = modelmapper.map(driverDto, Driver.class);
		
		driver.setCab(new Cab(driverDto.getCabNumber(), driverDto.getCabPos(),  driverDto.getCabModel()));
		
		return driver;
	}

}
