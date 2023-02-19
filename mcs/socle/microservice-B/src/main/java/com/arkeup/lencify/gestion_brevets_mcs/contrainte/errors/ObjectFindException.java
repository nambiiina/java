package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

public class ObjectFindException extends TechnicalException {
	
	private static final long serialVersionUID = 69L;

	public ObjectFindException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}

	public ObjectFindException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
}


