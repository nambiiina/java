package com.etech.microservice.micro_a.contrainte.errors;

public class DataConstraintViolationException extends TechnicalException{

	private static final long serialVersionUID = -5343932778059182862L;

	public DataConstraintViolationException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
	
	public DataConstraintViolationException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}

}
