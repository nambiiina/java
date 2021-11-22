package com.sid.mcsbank.web;

import com.sid.mcsbank.entities.Account;
import com.sid.mcsbank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/bank")
public class AccountRestJaxRSAPI {

    @Autowired
    private AccountRepository accountRepository;

    @Path("/accounts")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Account> accountList() {
        return accountRepository.findAll();
    }

    @Path("/accounts/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Account getOne(@PathParam(value = "id") Long id) {
        return accountRepository.findById(id).get();
    }

    @Path("/accounts")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Path("/accounts/{id}")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public Account update(Account account, @PathParam("id") Long id) {
        account.setId(id);
        return accountRepository.save(account);
    }

    @Path("/accounts/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public void delete(@PathParam("id") Long id) {
        accountRepository.deleteById(id);
    }

}
