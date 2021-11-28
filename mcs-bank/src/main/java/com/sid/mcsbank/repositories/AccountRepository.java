package com.sid.mcsbank.repositories;

import com.sid.mcsbank.entities.Account;
import com.sid.mcsbank.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

//http://localhost:8080/api/accounts?page=0&size=2&sort=balance,asc
@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {
    //http://localhost:8080/api/accounts/search/byType?t=SAVINGS
    @RestResource(path = "/byType")
    //http://localhost:8080/api/accounts/search/findByAccountType?accountType=CURRENT
    List<Account> findByAccountType (@Param("t") AccountType accountType);
}
