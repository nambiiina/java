package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

public abstract class TechnicalException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private ErrorsEnum errorsEnum;
	
	public TechnicalException(ErrorsEnum errorsEnum,Exception e) {
		super(e);
		this.errorsEnum = errorsEnum;
	}
	
	public TechnicalException(ErrorsEnum errorsEnum) {
		super();
		this.errorsEnum = errorsEnum;
	}
	
	public ErrorsEnum getErrorsEnum() {
		return errorsEnum;
	}
	public void setErrorsEnum(ErrorsEnum errorsEnum) {
		this.errorsEnum = errorsEnum;
	}
	
}
