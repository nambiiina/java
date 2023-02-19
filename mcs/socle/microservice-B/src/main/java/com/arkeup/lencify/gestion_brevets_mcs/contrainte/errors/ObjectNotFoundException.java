package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

public class ObjectNotFoundException extends FunctionalException {
	
	private static final long serialVersionUID = 69L;

	public ObjectNotFoundException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}

	public ObjectNotFoundException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
}


