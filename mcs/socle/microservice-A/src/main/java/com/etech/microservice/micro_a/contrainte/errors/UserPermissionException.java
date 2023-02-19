package com.etech.microservice.micro_a.contrainte.errors;

public class UserPermissionException extends FunctionalException{
	private static final long serialVersionUID = 1L;
	

	public UserPermissionException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public UserPermissionException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
	
}
