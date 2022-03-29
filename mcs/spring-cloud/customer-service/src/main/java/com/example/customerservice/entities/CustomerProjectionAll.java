package com.example.customerservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "all", types = Customer.class)
public interface CustomerProjectionAll {
    Long getId();
    String getName();
    String getEmail();
}
