package com.etech.microservice.micro_a.contrainte.errors;

public class UserAlreadyExistException extends FunctionalException{
	private static final long serialVersionUID = 1L;
	

	public UserAlreadyExistException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public UserAlreadyExistException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
	
}
