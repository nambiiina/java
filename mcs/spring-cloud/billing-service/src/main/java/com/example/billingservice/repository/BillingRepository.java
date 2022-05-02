package com.example.billingservice.repository;

import com.example.billingservice.entities.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BillingRepository extends JpaRepository<Billing, Long> {
}
