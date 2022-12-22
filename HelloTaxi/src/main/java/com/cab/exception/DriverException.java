package com.cab.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DriverException extends Exception {

	
	public DriverException(String str) {
		super(str);
	}
}
