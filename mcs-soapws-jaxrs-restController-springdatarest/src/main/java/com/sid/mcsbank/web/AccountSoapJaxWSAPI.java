package com.sid.mcsbank.web;

import com.sid.mcsbank.entities.Account;
import com.sid.mcsbank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/*
    1- create jax ws based in soap
    2- deploy jax ws using spring
    3- url : http://localhost:8888/bankWS?wsdl
 */

@Component
@WebService(serviceName = "bankAccountSoapWS")
public class AccountSoapJaxWSAPI {

    @Autowired
    private AccountRepository accountRepository;

    @WebMethod
    public List<Account> accountList(){
        return accountRepository.findAll();
    }

    @WebMethod
    public Account getOne(@WebParam(name = "id") Long id){
        return accountRepository.findById(id).get();
    }
}
