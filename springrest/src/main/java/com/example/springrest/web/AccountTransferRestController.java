package com.example.springrest.web;

import com.example.springrest.dtos.RequestTransferDTO;
import com.example.springrest.service.AccountService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountTransferRestController {

    private final AccountService accountService;

    public AccountTransferRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PutMapping(path = "/accounts/transfer")
    public void transfer(@RequestBody RequestTransferDTO requestTransferDTO) {
        accountService.transfer(requestTransferDTO.getSourceCode(), requestTransferDTO.getDestinationCode(), requestTransferDTO.getAmount());
    }
}
