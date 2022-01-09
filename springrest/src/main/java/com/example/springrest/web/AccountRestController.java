package com.example.springrest.web;

import com.example.springrest.entities.Account;
import com.example.springrest.repositories.AccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
public class AccountRestController {

    private final AccountRepository accountRepository;


    public AccountRestController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping(path = "/accounts")
    public List<Account> accountList() {
        return accountRepository.findAll();
    }

    @GetMapping(path = "/accounts/{code}")
    public Account getAccount(@PathVariable(name = "code") Long code) {
        return accountRepository.findById(code).get();
    }

    @PostMapping(path = "/accounts")
    public Account save(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PutMapping(path = "/accounts/{code}")
    public Account update(@PathVariable(name = "code") Long code, @RequestBody Account account) {
        account.setCode(code);
        return accountRepository.save(account);
    }

    @DeleteMapping(path = "/accounts/{code}")
    public void delete(@PathVariable(name = "code") Long code) {
        accountRepository.deleteById(code);
    }
}
