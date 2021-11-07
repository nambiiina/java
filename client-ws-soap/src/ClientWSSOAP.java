import ws.Account;
import ws.BankServiceProxy;

/**
 * 
 * @author thierry
 * library used : AXIS
 */
public class ClientWSSOAP {

	public static void main(String[] args) throws Exception {
		BankServiceProxy stub = new BankServiceProxy();
		Double amount = (double) 200;
		System.out.println(stub.conversionEuroToMGA(amount));
		Account account = stub.getAccount(3);
		System.out.println("code : " + account.getCode());
		System.out.println("balance : " + account.getBalance());
		
		Account[] accounts = stub.listAccounts();
		for(Account c : accounts) {
			System.out.println("code : " + c.getCode());
			System.out.println("balance : " + c.getBalance());
		}
	}

}
