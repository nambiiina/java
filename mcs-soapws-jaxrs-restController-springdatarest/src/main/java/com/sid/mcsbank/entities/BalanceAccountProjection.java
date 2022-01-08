package com.sid.mcsbank.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "balance", types = Account.class)
public interface BalanceAccountProjection {
    public double getBalance();
}
