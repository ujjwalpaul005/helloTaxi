package com.cab.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginException extends Exception {
	
	public LoginException(String message) {
		super(message);
	}

	
}
