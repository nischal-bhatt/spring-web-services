package com.in28minutes.rest.webservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
		
		super(message);
		System.out.println("here in user not found exception");
		// TODO Auto-generated constructor stub
	}

}
