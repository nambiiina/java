package com.sid.mcsbank.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "mobile", types = Account.class)
public interface MobileAccountProjection {
    double getBalance();
    AccountType getAccountType();
}
