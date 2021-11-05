package ws;

import businesslayer.Account;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * POJO (Plain Old Java Object)
 */

@WebService (serviceName = "BankWS")
public class BankService {
    @WebMethod (operationName = "ConversionEuroToMGA")
    public Double convert(@WebParam(name = "amount") Double amount) {
        return amount*4500;
    }
    @WebMethod
    public Account getAccount(Integer id) {
        return new Account(id, Math.random()*3000, new Date());
    }
    @WebMethod
    public List<Account> listAccounts(){
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1, Math.random()*3000, new Date()));
        accounts.add(new Account(2, Math.random()*2500, new Date()));
        accounts.add(new Account(3, Math.random()*1500, new Date()));
        return accounts;
    }
}
