package com.etech.microservice.micro_a.contrainte.errors;

public class FeignException extends TechnicalException {

	private static final long serialVersionUID = -8815242004547887548L;

	public FeignException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public FeignException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}

}
