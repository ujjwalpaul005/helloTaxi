package com.cab.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserException extends Exception {

	
	public UserException(String str) {
		super(str);
	}
}
