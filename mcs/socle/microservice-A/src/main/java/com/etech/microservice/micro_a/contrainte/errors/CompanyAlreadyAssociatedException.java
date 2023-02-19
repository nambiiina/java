package com.etech.microservice.micro_a.contrainte.errors;

public class CompanyAlreadyAssociatedException extends FunctionalException {

	private static final long serialVersionUID = 4429797734514042532L;

	public CompanyAlreadyAssociatedException(ErrorsEnum errorsEnum, Exception e) {
		super(errorsEnum, e);
	}
	
	public CompanyAlreadyAssociatedException(ErrorsEnum errorsEnum) {
		super(errorsEnum);
	}

}
