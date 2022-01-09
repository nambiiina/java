package com.example.springrest.repositories;

import com.example.springrest.entities.Account;
import com.example.springrest.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * url to access resource /byType : localhost:8080/accounts/search/byType?type=CURRENT
 */
@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {
    @RestResource(path = "/byType")
    List<Account> findByType(@Param(value = "type") AccountType accountType);
}
