package com.example.springrest.entities;

import org.springframework.data.rest.core.config.Projection;


/**
 * url to access it : localhost:8080/accounts?projection=balance
 */
@Projection(name = "balance", types = Account.class)
public interface AccountBalanceProjection {
    double getBalance();
}
