package com.arkeup.lencify.gestion_brevets_mcs.contrainte.errors;

import java.util.HashMap;
import java.util.Map;

public enum ErrorsEnum {

	ERR_MCS_UNKW("ERR_MCS_UNKW", "Unknown exception occurred.", Boolean.TRUE, Boolean.FALSE),

	ERR_MCS_GESTION_BREVETS_ESPACENET_API("ERR_MCS_GESTION_BREVETS_0001", "EspaceNet API server rrror", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_GESTION_BREVETS_INVALID_PAGE_SIZE("ERR_MCS_GESTION_BREVETS_0002", "Invalid page size.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_PATENT_EPODOC_NUMBER("ERR_MCS_GESTION_BREVETS_0003", "Epodoc number cannot be null.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_PATENT_DOCDB_NUMBER("ERR_MCS_GESTION_BREVETS_0004", "Docdb number cannot be null.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_PATENT_COUNTRY("ERR_MCS_GESTION_BREVETS_0005", "Country cannot be null.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_PATENT_TITLES("ERR_MCS_GESTION_BREVETS_0006", "Titles cannot be null or empty.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_PATENT_DESCRIPTIONS("ERR_MCS_GESTION_BREVETS_0007", "Descriptions cannot be null.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_PATENT_INVENTORS("ERR_MCS_GESTION_BREVETS_0008", "Inventors cannot be null.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_PATENT_DEMANDEURS("ERR_MCS_GESTION_BREVETS_0009", "Demandeurs cannot be null.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_COMPANY("ERR_MCS_GESTION_BREVETS_00010", "Company cannot be null.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_EMPTY_BREVET("ERR_MCS_GESTION_BREVETS_00011", "Brevet cannot be null.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_EMPTY_COMPANY_ID("ERR_MCS_GESTION_BREVETS_00012", "Company.id cannot be null.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_COMPANY_SIREN_NUMBER("ERR_MCS_GESTION_BREVETS_0013", "company.sirenNumber cannot be null.",
			Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_EMPTY_COMPANY_NAME("ERR_MCS_GESTION_BREVETS_0014", "Company.name cannot be null.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_COMPANY_BREVET_ALREADY_EXIST("ERR_MCS_GESTION_BREVETS_0015",
			"This relation company - brevet already exist.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_COMPANY_BREVET_NOT_EXIST("ERR_MCS_GESTION_BREVETS_0016", "This relation company - brevet does not exist.",
			Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_BREVET_NOT_FOUND("ERR_MCS_GESTION_BREVETS_0017", "Patent does not exist in database.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_BREVET_NOT_ACCEPTED("ERR_MCS_GESTION_BREVETS_0018", "This patent is not accepted yet.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_BREVET_ALREADY_EXIST("ERR_MCS_GESTION_BREVETS_0019", "Patent already exist.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_EMPTY_PACK_LABEL("ERR_MCS_GESTION_BREVETS_0020", "pack.label cannot be empty or null.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_PACK_COMPANY_ID("ERR_MCS_GESTION_BREVETS_0021", "pack.companyId cannot be empty or null.",
			Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_PACK_ALREADY_EXIST("ERR_MCS_GESTION_BREVETS_0022", "Pack already exist.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_PACK_NOT_FOUND("ERR_MCS_GESTION_BREVETS_0023", "Pack not found.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_BREVET_ALREADY_EXIST_IN_PACK("ERR_MCS_GESTION_BREVETS_0024", "Patent already exist in pack.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_BREVET_NOT_FOUND_IN_PACK("ERR_MCS_GESTION_BREVETS_0025", "Brevet not found in this pack.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_EMPTY_PACK("ERR_MCS_GESTION_BREVETS_00026", "Pack cannot be null.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_MAX_CHARS_PACK_LABEL("ERR_MCS_GESTION_BREVETS_0027", "pack.label : max length 50 characters.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_MAX_PACK_KEYWORDS("ERR_MCS_GESTION_BREVETS_0028", "pack.keywords : 5 keywords max.", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_FIND_EXCEPTION("ERR_MCS_GESTION_BREVETS_00029", "Object error finding process", Boolean.TRUE,
			Boolean.FALSE),
	ERR_MCS_PARSE_EXCEPTION("ERR_MCS_GESTION_BREVETS_00030", "Cannot parse date", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_ALL_SEARCH_PARAMS_EMPTY("ERR_MCS_GESTION_BREVETS_0031",
			"All search params are empty. You must give at least one search parameter", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_KEYWORD_NOT_FOUND("ERR_MCS_GESTION_BREVETS_0032", "Keyword not found.", Boolean.TRUE, Boolean.FALSE),
	ERR_MCS_BREVET_NOT_SAVED("ERR_MCS_GESTION_BREVETS_0033", "Error while trying to save Brevet.", Boolean.TRUE, Boolean.FALSE),
        ERR_MCS_LENCIPACK_NOT_FOUND("ERR_MCS_GESTION_BREVETS_0034", "Lencipack not found.", Boolean.TRUE, Boolean.FALSE),
        ERR_MCS_SCRIPT_NOT_EXECUTED("ERR_MCS_GESTION_BREVETS_0035", "Cannot execute script", Boolean.TRUE, Boolean.FALSE),
    ERR_MCS_REMOVING_PATENT_FROM_PACKS("ERR_MCS_GESTION_BREVETS_0036", "An error occured when removing patent from packs", Boolean.TRUE, Boolean.FALSE),
    ERR_MCS_REMOVING_PATENT("ERR_MCS_GESTION_BREVETS_0037", "An error occured when removing patent from packs", Boolean.TRUE, Boolean.FALSE),
    ERR_MCS_MUST_NOT_BE_NULL("ERR_MCS_GESTION_BREVETS_0038", "must not be null", Boolean.TRUE, Boolean.FALSE),
    ERR_MCS_CALL("ERR_MCS_GESTION_BREVETS_0039", "the called MCS returned an error", Boolean.TRUE, Boolean.FALSE),
    WRN_MCS_SUCCESSFULY_REMOVED_PATENT_FROM_PACKS("WRN_MCS_GESTION_PROFIL_0001", "The patent has been removed from packs", Boolean.FALSE, Boolean.TRUE),
    WRN_MCS_SUCCESSFULY_DELETED_PATENT("WRN_MCS_GESTION_PROFIL_0002", "The patent has been deleted", Boolean.FALSE, Boolean.TRUE),

    ERR_MCS_BREVET_VALIDATION_DATE_INVALID("ERR_MCS_BREVET_VALIDATION_DATE_INVALID", "the validation date of patent is invalid.", Boolean.TRUE, Boolean.FALSE),
    ERR_MCS_BREVET_STATUS_INVALID("ERR_MCS_BREVET_STATUS_INVALID", "the status of patent is invalid.", Boolean.TRUE, Boolean.FALSE),
    ERR_MCS_BREVET_ESPACE_NET_NOT_SAVED("ERR_MCS_BREVET_ESPACE_NET_NOT_SAVED", "Error while trying to save Brevet espace net.", Boolean.TRUE, Boolean.FALSE),
    ERR_MCS_LENCIFY_PARTNER_NOT_OWNER("ERR_MCS_LENCIFY_PARTNER_NOT_OWNER", "The current connected user are not the owner of the patent.", Boolean.TRUE, Boolean.FALSE),
    ERR_MCS_LENCIFY_USER_NOT_FOUND("ERR_MCS_LENCIFY_USER_NOT_FOUND", "Lencify user does not exist in database.", Boolean.TRUE, Boolean.FALSE),;

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
