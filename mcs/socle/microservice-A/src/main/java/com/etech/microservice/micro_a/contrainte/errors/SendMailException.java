package com.etech.microservice.micro_a.contrainte.errors;


public class SendMailException extends TechnicalException{
	private static final long serialVersionUID = 1L;
	
	public SendMailException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public SendMailException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
	
}
