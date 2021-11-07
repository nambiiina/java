import java.util.List;

import ws.Account;
import ws.BankService;
import ws.BankWS;

/**
 * 
 * @author thierry
 *
 * Client ws using jaxws generate by wsimport (in jdk)
 *
 */
public class ClientWSSOAPJaxWS {

	public static void main(String[] args) {
		BankService stub = new BankWS().getBankServicePort();
		Double amount = (double) 200;
		System.out.println(stub.conversionEuroToMGA(amount));
		Account account = stub.getAccount(3);
		System.out.println("code : " + account.getCode());
		System.out.println("balance : " + account.getBalance());
		
		List<Account> accounts = stub.listAccounts();
		for(Account c : accounts) {
			System.out.println("code : " + c.getCode());
			System.out.println("balance : " + c.getBalance());
		}
	}

}
