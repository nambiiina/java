package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

public class PatentNotAcceptedException extends FunctionalException {
	private static final long serialVersionUID = 1L;

	public PatentNotAcceptedException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}

	public PatentNotAcceptedException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}

}
