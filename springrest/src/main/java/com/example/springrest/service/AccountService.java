package com.example.springrest.service;

public interface AccountService {
    void transfer(Long codeSource, Long codeDestination, double amount);
}
