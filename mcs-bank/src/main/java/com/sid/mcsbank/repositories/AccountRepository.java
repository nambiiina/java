package com.sid.mcsbank.repositories;

import com.sid.mcsbank.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//http://localhost:8080/api/accounts?page=0&size=2&sort=balance,asc
@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {
}
