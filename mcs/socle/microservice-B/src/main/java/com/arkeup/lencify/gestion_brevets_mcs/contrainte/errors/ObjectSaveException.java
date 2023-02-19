package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

public class ObjectSaveException extends TechnicalException {
	
	private static final long serialVersionUID = 69L;

	public ObjectSaveException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}

	public ObjectSaveException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}
}


