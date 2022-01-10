package com.example.springrest.service;

import com.example.springrest.entities.Account;
import com.example.springrest.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void transfer(Long codeSource, Long codeDestination, double amount) {
        Account src = accountRepository.getById(codeSource);
        Account dest = accountRepository.getById(codeDestination);
        src.setBalance(src.getBalance() - amount);
        dest.setBalance(dest.getBalance() + amount);
        accountRepository.save(src);
        accountRepository.save(dest);
    }
}
