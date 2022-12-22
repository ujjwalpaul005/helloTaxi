package com.cab.service;

import java.util.List;

import com.cab.exception.DriverException;
import com.cab.exception.LoginException;
import com.cab.exception.UserException;
import com.cab.model.dto.LoginDTO;
import com.cab.model.dto.ShowDriver;
import com.cab.model.dto.UserDTO;


public interface UserService {

	public UserDTO registerUser(UserDTO user) throws UserException;
	
	public String loginUser(LoginDTO login) throws UserException, LoginException;
	
	public List<ShowDriver> findride(String uuid) throws DriverException, LoginException;
	
	public ShowDriver bookCab(Integer driverId, Integer x, Integer y, String uuid) throws DriverException, LoginException;
}
