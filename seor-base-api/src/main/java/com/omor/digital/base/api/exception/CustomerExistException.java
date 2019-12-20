package com.omor.digital.base.api.exception;

public class CustomerExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomerExistException() {
		super("Customer already exist");
	}

	public CustomerExistException(String message) {
		super(message);
	}

}
