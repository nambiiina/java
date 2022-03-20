package com.example.userservice.controller;

import com.example.userservice.entities.AppUser;
import com.example.userservice.entities.UserToRegister;
import com.example.userservice.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private AccountService accountService;

    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "/register")
    AppUser register(@RequestBody UserToRegister userToRegister) {
        return accountService.saveUser(userToRegister.getUsername(), userToRegister.getPassword(), userToRegister.getConfirmedPassword());
    }
}
