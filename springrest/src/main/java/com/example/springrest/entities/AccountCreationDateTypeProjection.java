package com.example.springrest.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * url to access it : http://localhost:8080/accounts?projection=creationDateType
 */
@Projection(name = "creationDateType", types = Account.class)
public interface AccountCreationDateTypeProjection {
    Date getCreationDate();
    AccountType getType();
}
