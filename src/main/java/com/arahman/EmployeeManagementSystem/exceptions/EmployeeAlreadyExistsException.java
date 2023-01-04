package com.arahman.EmployeeManagementSystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SEE_OTHER)
public class EmployeeAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EmployeeAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EmployeeAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmployeeAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmployeeAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	
}
