package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

public class AdvancedSearchEmptyParamsException extends FunctionalException{
	private static final long serialVersionUID = 1L;
	

	public AdvancedSearchEmptyParamsException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public AdvancedSearchEmptyParamsException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
	
}
