package com.etech.microservice.micro_a.contrainte.errors;

public abstract class FunctionalException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private ErrorsEnum errorsEnum;
	
	public FunctionalException(ErrorsEnum errorsEnum,Exception e) {
		super(errorsEnum.getErrorMessage(),e);
		this.errorsEnum = errorsEnum;
	}
	
	public FunctionalException(ErrorsEnum errorsEnum) {
		super(errorsEnum.getErrorMessage());
		this.errorsEnum = errorsEnum;
	}
	
	public ErrorsEnum getErrorsEnum() {
		return errorsEnum;
	}
	public void setErrorsEnum(ErrorsEnum errorsEnum) {
		this.errorsEnum = errorsEnum;
	}
	
}
