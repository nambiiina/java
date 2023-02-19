package com.etech.microservice.micro_a.contrainte.errors;

public class InvalidDataException extends FunctionalException{
	private static final long serialVersionUID = 1L;
	

	public InvalidDataException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public InvalidDataException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
	
}
