package com.etech.microservice.micro_a.contrainte.errors;

import java.util.HashMap;
import java.util.Map;

public enum ErrorsEnum {

	ERR_MCS_PROFIL_EMPTY_EMAIL("ERR_MCS_PROFIL_0001", "Email cannot be null.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_PROFIL_INVALID_EMAIL("ERR_MCS_PROFIL_0002", "Invalid Email format.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_USER_ALREADY_EXIST("ERR_MCS_USER_0001", "User Already exist.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_UNKW("ERR_MCS_UNKW", "Unknown exception occurred.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_INACTIF_USER("ERR_MCS_INACTIF_USER",
			"Your account is not validated, would you like to receive a new activation email?", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_BLOCKED_USER("ERR_MCS_BLOCKED_USER",
			"Your account is blocked. Please contact an administrator for more details.", Boolean.TRUE, Boolean.FALSE),

	ERR_MCS_EMPTY_SIREN_NUMBER("ERR_MCS_GESTION_PROFIL_0001", "Siren number cannot be null.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_COMPANY_NAME("ERR_MCS_GESTION_PROFIL_0002", "Company name cannot be null.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_EMAIL("ERR_MCS_GESTION_PROFIL_0003", "Email cannot be null.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_EMPTY_LAST_NAME("ERR_MCS_GESTION_PROFIL_0004", "LastName cannot be null.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_EMPTY_FIRST_NAME("ERR_MCS_GESTION_PROFIL_0005", "FirstName cannot be null.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_AGREED_TERMS_FALSE("ERR_MCS_GESTION_PROFIL_0006", "Agreed terms cannot be false.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_INVALID_EMAIL("ERR_MCS_GESTION_PROFIL_0007", "Invalid Email format.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_NOTIFICATION_SEND_EMAIL("ERR_MCS_GESTION_PROFIL_0008", "Error while sending mail.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMAIL_ALREADY_EXIST("ERR_MCS_GESTION_PROFIL_0009", "Email already exist.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_SIREN_NUMBER_ALREADY_EXIST("ERR_MCS_GESTION_PROFIL_0010", "Siren number already exist.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_INVALID_DATA_USER("ERR_MCS_GESTION_PROFIL_0011", "Invalid data : user cannot be null.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_INVALID_DATA_COMPANY("ERR_MCS_GESTION_PROFIL_0012", "Invalid data : company cannot be null.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_DATA_CONSTRAINT_VIOLATION("ERR_MCS_GESTION_PROFIL_0013", "Some data constraints are not respected.",
			Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_OBJECT_NOT_SAVED("ERR_MCS_GESTION_PROFIL_0014", "Object not saved in database.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_USER_NOT_SAVED("ERR_MCS_GESTION_PROFIL_0015", "User not saved in database.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_INVALID_DATA_REGISTRATION("ERR_MCS_GESTION_PROFIL_0016", "Invalid data : registration cannot be null.",
			Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_REGISTRATION_NOT_SAVED("ERR_MCS_GESTION_PROFIL_0017", "Registration not saved in database.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_INVALID_DATA_REGISTRATION_ID("ERR_MCS_GESTION_PROFIL_0018", "Invalid data : registrationId cannot be null.",
			Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_REGISTRATION_ID_DOES_NOT_EXIST("ERR_MCS_GESTION_PROFIL_0019", "Registration id does not exist.",
			Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_CANNOT_GET_REGISTRATION_CREATED_DATE("ERR_MCS_GESTION_PROFIL_0020", "Cannot get registration created date.",
			Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_REGISTRATION_TOKEN_EXPIRED("ERR_MCS_GESTION_PROFIL_0021", "Registration token expired.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_INVALID_PG_ACCOUNT("ERR_MCS_GESTION_PROFIL_0022", "Invalid related registration token Postgres account.",
			Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_FEIGN_CONNECTION_FAILED_ON_AUTHENTICATION_RIGHTS_MCS("ERR_MCS_GESTION_PROFIL_0023",
			"Connection refused on Authentication MCS.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_CANNOT_FIND_REGISTRATION("ERR_MCS_GESTION_PROFIL_0024", "Cannot find registration.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_CANNOT_DELETE_REGISTRATION("ERR_MCS_GESTION_PROFIL_0025", "Cannot delete registration.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_PASSWORD("ERR_MCS_GESTION_PROFIL_0026", "Password cannot be null.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_EMPTY_PASSWORD_CONFIRMATION("ERR_MCS_GESTION_PROFIL_0027", "Password confirmation cannot be null.",
			Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_MATCH_PASSWORD("ERR_MCS_GESTION_PROFIL_0028", "Password and password confirmation do not match.",
			Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_INVALID_DATA_ACCOUNT_VALIDATION("ERR_MCS_GESTION_PROFIL_0029", "Account validaiton cannot be null.",
			Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_CANNOT_FIND_TOKEN_RELATED_USER("ERR_MCS_GESTION_PROFIL_0030", "Cannot find token related user.",
			Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_CANNOT_ACTIVATE_ACCOUNT("ERR_MCS_GESTION_PROFIL_0031", "Cannot activate acccount.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_LDAP_SAVE_ERROR("ERR_MCS_GESTION_PROFIL_0032", "Error while saving user in LDAP.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_COMPANY_ACTIVITY_FIELD("ERR_MCS_GESTION_PROFIL_0033", "Company activity cannot be null.",
			Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_CANNOT_FIND_USER_PROFILE("ERR_MCS_GESTION_PROFIL_0034", "Cannot find user profile.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_INVALID_DATA_PRODUCT("ERR_MCS_GESTION_PROFIL_0035", "Invalid data : product cannot be null.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_PRODUCT_NAME("ERR_MCS_GESTION_PROFIL_0036", "Product name cannot be empty.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_KEYWORDS("ERR_MCS_GESTION_PROFIL_0037", "Keywords cannot be empty.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_CANNOT_FIND_COMPANY("ERR_MCS_GESTION_PROFIL_0038", "Cannot find company", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_CANNOT_FIND_ACTIVITY("ERR_MCS_GESTION_PROFIL_0039", "Cannot find activity", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_CANNOT_FIND_USER_TYPE("ERR_MCS_GESTION_PROFIL_0040", "Cannot find user type", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_EMPTY_PRODUCTS_LIST("ERR_MCS_GESTION_PROFIL_0041", "Products list cannot be empty.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_PRODUCTS_NOT_SAVED("ERR_MCS_GESTION_PROFIL_0042", "Products list not saved in Database.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_PRODUCTS_NOT_DELETED("ERR_MCS_GESTION_PROFIL_0043", "Products list not deleted in Database.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_NOT_ENOUGH_PERMISSION("ERR_MCS_GESTION_PROFIL_0044", "Products list not deleted in Database.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_PARTNER_ALREADY_EXISTS("ERR_MCS_GESTION_PROFIL_0045", "Partner already exists.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_PARTNER_NOT_SAVED("ERR_MCS_GESTION_PROFIL_0047", "Partner not saved.", Boolean.TRUE, Boolean.FALSE),

	ERR_MCS_EMPTY_COMPANY("ERR_MCS_GESTION_PROFIL_0048", "Company cannot be null.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_CANNOT_FIND_PRODUCT("ERR_MCS_GESTION_PROFIL_0049", "Cannot find product", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_CANNOT_FIND_KEYWORD("ERR_MCS_GESTION_PROFIL_0050", "Cannot find keyword", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_SCRIPT_NOT_EXECUTED("ERR_MCS_GESTION_PROFIL_0051", "Cannot execute script", Boolean.TRUE, Boolean.FALSE),

         ERR_MCS_BREVET_NOT_FOUND("ERR_MCS_GESTION_PROFIL_0052", "Brevet not founds", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_LIMITE_REACHED_BREVET("ERR_MCS_GESTION_PROFIL_0053", "You cannot have more than 100 patents in your portfolio.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_BREVET_NOT_FOUND_IN_WALLET("ERR_MCS_GESTION_PROFIL_0054", "Brevet not found in the wallet.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_EMPTY_BREVET("ERR_MCS_GESTION_PROFIL_0055", "Brevet cannot be null.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_INVALID_USER_TYPE("ERR_MCS_GESTION_PROFIL_0056", "User type cannot be null or empty.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_COMPANY_ALREADY_ASSOCIATED("ERR_MCS_GESTION_PROFIL_0057", "Another user is already associated with this company.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_NO_COMPANY_FOR_USER("ERR_MCS_GESTION_PROFIL_0058", "No company associated with this user", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_DOCUMENT_NOT_FOUND_FOR_COMPANY("ERR_MCS_GESTION_PROFIL_0059", "Document not found for company", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_EMPTY_DOCUMENT("ERR_MCS_GESTION_PROFIL_0060", "Document must not be null", Boolean.TRUE, Boolean.FALSE),

	ERR_MCS_MUST_NOT_BE_NULL("ERR_MCS_0001", "must not be null", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_MUST_BE_NULL("ERR_MCS_0002", "must be null", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_CALL("ERR_MCS_0003", "the called MCS returned an error", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_INVALID_DATA("ERR_MCS_0004", "invalid data", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_VALIDATION_ERROR("ERR_MCS_VALIDATION_ERROR", "Validation error", Boolean.TRUE, Boolean.FALSE),



	ERR_MCS_COMPANY_PERMISSION_WALLET("WRN_MCS_GESTION_PROFIL_0063", "you dont have permission to add patent in wallet", Boolean.FALSE, Boolean.TRUE),


        ERR_MCS_BREVET("ERR_MCS_GESTION_BREVET", "error mcs brevet", Boolean.TRUE, Boolean.FALSE),

	WRN_MCS_COMPANY_NO_DOCUMENT_TO_DELETE("WRN_MCS_GESTION_PROFIL_0001", "No document were deleted", Boolean.FALSE, Boolean.TRUE),
	WRN_MCS_NO_COMPANY_FOR_USER("WRN_MCS_GESTION_PROFIL_0002", "No company associated with this user", Boolean.FALSE, Boolean.TRUE),
	WRN_MCS_SUCCESSFULY_REMOVED_PATENT_FROM_WALLETS("WRN_MCS_GESTION_PROFIL_0003", "The patent has been removed from wallets", Boolean.FALSE, Boolean.TRUE),
	WRN_MCS_USER_ALREADY_SUBSCRIBED("WRN_MCS_GESTION_PROFIL_0004", "The user is already registered on this subscription", Boolean.FALSE, Boolean.TRUE),
	WRN_MCS_USER_NOT_SUBSCRIBED("WRN_MCS_GESTION_PROFIL_000", "Cannot unsubscribed users who have not been subscribed", Boolean.FALSE, Boolean.TRUE),
	ERR_MCS_COMPANY_EMPTY_IBAN("ERR_MCS_GESTION_PROFIL_0064", "IBAN must not be empty", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_COMPANY_EMPTY_CODE_BIC("ERR_MCS_GESTION_PROFIL_0065", "CODE BIC must not be empty", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_COMPANY_EMPTY_TITULAIRES("ERR_MCS_GESTION_PROFIL_0066", "Bank Titulaires must not be empty", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_COMPANY_EMPTY_BANK_INFO("ERR_MCS_GESTION_PROFIL_0067", "Bank Info must not be empty", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_REMOVING_PATENT("ERR_MCS_GESTION_PROFIL_0068", "An error occured when removing patent", Boolean.TRUE, Boolean.FALSE),

	ERR_MCS_CGU_NOT_FOUND("ERR_MCS_GESTION_PROFIL_0069", "CGU not found. restart MCS", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_CGV_NOT_FOUND("ERR_MCS_GESTION_PROFIL_0070", "CGV not found. restart MCS", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_UNKNOWN_VALUE("ERR_MCS_GESTION_PROFIL_0071", "Unknown Value", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_BEVET_ALREADY_IN_WALLET("ERR_MCS_GESTION_PROFIL_0072", "this brevet is already in wallet", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_SUBSCRIPTION_NOT_FOUND("ERR_MCS_GESTION_PROFIL_0073", "Subscription not found.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_SUBSCRIPTION_FAILED("ERR_MCS_GESTION_PROFIL_0074", "Subscription save failed.", Boolean.TRUE, Boolean.FALSE)
	;



	private final String errorCode;
	private final String errorMessage;
	private final Boolean error;
	private final Boolean warning;

	/**
	 * liste de correspondance entre un enum et errorCode
	 */
	private static final Map<String, ErrorsEnum> BYID = new HashMap<String, ErrorsEnum>();
	static {
		for (ErrorsEnum e : ErrorsEnum.values()) {
			if (BYID.put(e.getErrorCode(), e) != null) {
				throw new IllegalArgumentException("duplicate id: " + e.getErrorCode());
			}
		}
	}

	ErrorsEnum(String errorCode, String errorMessage, Boolean error, Boolean warning) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.error = error;
		this.warning = warning;
	}

	@Override
	public String toString() {
		return "ErrorCode : " + errorCode + " errorMessage : " + errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public static ErrorsEnum getById(String id) {
		return BYID.get(id);
	}

	public Boolean getError() {
		return error;
	}

	public Boolean getWarning() {
		return warning;
	}

}
