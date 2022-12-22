package com.cab.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cab.exception.DriverException;
import com.cab.exception.LoginException;
import com.cab.exception.UserException;
import com.cab.model.Cab;
import com.cab.model.CurrentUserSession;
import com.cab.model.Driver;
import com.cab.model.User;
import com.cab.model.dto.LoginDTO;
import com.cab.model.dto.ShowDriver;
import com.cab.model.dto.UserDTO;
import com.cab.repository.DriverRepo;
import com.cab.repository.SessionRepo;
import com.cab.repository.UserRepo;
import com.cab.service.UserService;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private DriverRepo dRepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private SessionRepo sRepo;
	
	
	

	@Override
	public UserDTO registerUser(UserDTO uDto) throws UserException {
		User user = getUserFromUserDto(uDto);
		
		User temp = null;
		
		try {
			temp = uRepo.save(user);
		}catch (Exception e) {
			throw new UserException("mobile number already registered.");
		}
		
		return getUserDtofromUser(temp);
	}


	@Override
	public String loginUser(LoginDTO login) throws UserException, LoginException {
		
		List<User> existingUser= uRepo.findByMoblieNumber(login.getMobileNumber());
		
		if(existingUser.size() == 0) {
			throw new LoginException("Please Enter a valid mobile number");
		}
		
		User existingCustomer = existingUser.get(0);

		Optional<CurrentUserSession> validCustomerSessionOpt =  sRepo.findById(existingCustomer.getMoblieNumber());
		
		if(validCustomerSessionOpt.isPresent()) {
			
			throw new LoginException("User already Logged In with this number");
			
		}
		
		if(existingCustomer.getPassword().equals(login.getPassword())) {
			
			String key= RandomString.make(6);
		
			CurrentUserSession currentUserSession = new CurrentUserSession(existingCustomer.getMoblieNumber(),key,LocalDateTime.now());
			
			sRepo.save(currentUserSession);

			return currentUserSession.toString();
		}
		else
			throw new LoginException("Please Enter a valid password");
		
	}


	@Override
	public List<ShowDriver> findride(String uuid) throws DriverException, LoginException {
		CurrentUserSession userSession = null;
		userSession = sRepo.findByUuid(uuid);
		
		if(userSession == null) {
			throw new LoginException("Please Login first");
		}
		
		
		User user = uRepo.findByMoblieNumber(userSession.getMobileNumber()).get(0);
		
		List<Driver> all = dRepo.findAll();
		
		if(all.size() == 0) {
			throw new DriverException("No cab found!");
		}
		
		
		List<Driver> selected = new ArrayList<>();
		
		Integer userX = user.getCurrentPosition().get(0);
		Integer userY = user.getCurrentPosition().get(1);
		
		for(Driver d : all) {
			
			Integer cabX = d.getCab().getCurrentPos().get(0);
			Integer cabY = d.getCab().getCurrentPos().get(1);
			
			double distance = Math.sqrt((Math.pow(userX - cabX, 2) + Math.pow(userY - cabY, 2)));
			
			if(distance <= 5) {
				selected.add(d);
			}
			
		}
		
		
		if(selected.size() == 0) {
			throw new DriverException("No nearby cab found!");
		}
		
		
		
		
		List<ShowDriver> selectedCabs = new ArrayList<>();
		
		for(Driver d : selected) {
			ShowDriver cab = getShowDriverfromDriver(d);
			
			Integer cabX = d.getCab().getCurrentPos().get(0);
			Integer cabY = d.getCab().getCurrentPos().get(1);
			
			double distance = Math.sqrt((Math.pow(userX - cabX, 2) + Math.pow(userY - cabY, 2)));
			
			cab.setCabDistance(distance);
			
			selectedCabs.add(cab);
		}
		
		
		
		return selectedCabs;
	}


	@Override
	public ShowDriver bookCab(Integer driverId, Integer x, Integer y, String uuid) throws DriverException, LoginException {
		CurrentUserSession userSession = null;
		userSession = sRepo.findByUuid(uuid);
		
		if(userSession == null) {
			throw new LoginException("Please Login first");
		}
		
		
		User user = uRepo.findByMoblieNumber(userSession.getMobileNumber()).get(0);
		
		Optional<Driver> tempDriver = dRepo.findById(driverId);
		
		if(tempDriver.isEmpty()) {
			throw new DriverException("No driver found with this id : " + driverId );
		}
		
		Driver driver = tempDriver.get();
		
		Integer userX = user.getCurrentPosition().get(0);
		Integer userY = user.getCurrentPosition().get(1);
		
		Integer cabX = driver.getCab().getCurrentPos().get(0);
		Integer cabY = driver.getCab().getCurrentPos().get(1);
		
		double distance = Math.sqrt((Math.pow(userX - cabX, 2) + Math.pow(userY - cabY, 2)));
		
		if(distance > 5) {
			throw new DriverException("You can't book this cab, It's more than 5 km away.");
		}
		
		ShowDriver driverDto = getShowDriverfromDriver(driver);
		driverDto.setCabDistance(distance);
		
		Cab newCab = driver.getCab();
		
		ArrayList<Integer> newPos = new ArrayList<>();
		newPos.add(x);
		newPos.add(y);
		
		newCab.setCurrentPos(newPos);
		
		driver.setCab(newCab);
		
		dRepo.save(driver);
		
		user.setCurrentPosition(newPos);
		
		uRepo.save(user);
		
		
		return driverDto;
	}
	
	
	
	
	
	
	public UserDTO getUserDtofromUser(User user) {
		UserDTO uDto = modelmapper.map(user, UserDTO.class);
		
		return uDto;
	}
	
	public User getUserFromUserDto(UserDTO uDto) {
		User user = modelmapper.map(uDto, User.class);
		
		return user;
	}
	
	public ShowDriver getShowDriverfromDriver(Driver driver) {
		ShowDriver dto = modelmapper.map(driver, ShowDriver.class);
		dto.setCabNumber(driver.getCab().getCabNumber());
		dto.setCabModel(driver.getCab().getModel());
		
		return dto;
	}


}
