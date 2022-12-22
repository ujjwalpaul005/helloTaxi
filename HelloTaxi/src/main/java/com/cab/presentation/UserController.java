package com.cab.presentation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cab.exception.DriverException;
import com.cab.exception.LoginException;
import com.cab.exception.UserException;
import com.cab.model.dto.LoginDTO;
import com.cab.model.dto.ShowDriver;
import com.cab.model.dto.UserDTO;
import com.cab.service.UserService;

@RestController
@RequestMapping("/masaicab/user")
public class UserController {
	
	@Autowired
	private UserService uService;
	

	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUserHandler(@Valid @RequestBody UserDTO uDto) throws UserException{
		UserDTO user = uService.registerUser(uDto);
		
		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUserHandler(@Valid @RequestBody LoginDTO log) throws UserException, LoginException{
		String uuid = uService.loginUser(log);
		
		return new ResponseEntity<String>(uuid, HttpStatus.OK);
	}
	
	@GetMapping("/findride")
	public ResponseEntity<List<ShowDriver>> findrideHandler(@RequestParam String uuid) throws DriverException, LoginException{
		List<ShowDriver> available = uService.findride(uuid);
		
		return new ResponseEntity<List<ShowDriver>>(available, HttpStatus.OK);
	}
	
	@PutMapping("book/{driverId}/{x}/{y}")
	public ResponseEntity<ShowDriver> bookCabHandler(@PathVariable Integer driverId,
													 @PathVariable Integer x,
													 @PathVariable Integer y,
													 @RequestParam String uuid)throws DriverException, LoginException{
		
		ShowDriver cab = uService.bookCab(driverId, x, y, uuid);
		
		return new ResponseEntity<ShowDriver>(cab, HttpStatus.OK);
		
	}
	
}
