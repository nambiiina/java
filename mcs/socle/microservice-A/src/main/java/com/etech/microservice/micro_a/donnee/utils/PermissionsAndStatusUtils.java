package com.etech.microservice.micro_a.donnee.utils;

public interface PermissionsAndStatusUtils {

	String ROLEUSER = "hasAnyAuthority('perm_user')";

	String ROLEPARTNER = "hasAnyAuthority('perm_partner')";

	String ROLEADMIN = "hasAnyAuthority('perm_admin')";

	String ROLEUSERANDADMIN = "hasAnyAuthority('perm_user','perm_admin')";

	String ROLEUSERDATAFLOW = "hasAnyAuthority('perm_etl')";

	String STATUSINACTIF = "status_inactif";

	String STATUSBLOCKED = "status_blocked";
}
