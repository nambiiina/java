package com.sid.mcsbank.web;

import com.sid.mcsbank.entities.Account;
import com.sid.mcsbank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class AccountRestControllerAPI {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping(path = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Account> accountList() {
        return accountRepository.findAll();
    }

    @GetMapping(path = "/accounts/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Account getOne(@PathVariable("id") Long id) {
        return accountRepository.findById(id).get();
    }

    @PostMapping(path = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Account save(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PutMapping(path = "/accounts/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Account update(@RequestBody Account account, @PathVariable("id") Long id) {
        account.setId(id);
        return accountRepository.save(account);
    }

    @DeleteMapping(path = "/accounts/{id}")
    public void delete(@PathVariable("id") Long id) {
        accountRepository.deleteById(id);
    }

}
