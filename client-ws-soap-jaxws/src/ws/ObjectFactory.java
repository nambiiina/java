
package ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListAccounts_QNAME = new QName("http://ws/", "listAccounts");
    private final static QName _GetAccount_QNAME = new QName("http://ws/", "getAccount");
    private final static QName _ConversionEuroToMGAResponse_QNAME = new QName("http://ws/", "ConversionEuroToMGAResponse");
    private final static QName _ListAccountsResponse_QNAME = new QName("http://ws/", "listAccountsResponse");
    private final static QName _GetAccountResponse_QNAME = new QName("http://ws/", "getAccountResponse");
    private final static QName _Account_QNAME = new QName("http://ws/", "account");
    private final static QName _ConversionEuroToMGA_QNAME = new QName("http://ws/", "ConversionEuroToMGA");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListAccounts }
     * 
     */
    public ListAccounts createListAccounts() {
        return new ListAccounts();
    }

    /**
     * Create an instance of {@link GetAccount }
     * 
     */
    public GetAccount createGetAccount() {
        return new GetAccount();
    }

    /**
     * Create an instance of {@link ConversionEuroToMGAResponse }
     * 
     */
    public ConversionEuroToMGAResponse createConversionEuroToMGAResponse() {
        return new ConversionEuroToMGAResponse();
    }

    /**
     * Create an instance of {@link ListAccountsResponse }
     * 
     */
    public ListAccountsResponse createListAccountsResponse() {
        return new ListAccountsResponse();
    }

    /**
     * Create an instance of {@link GetAccountResponse }
     * 
     */
    public GetAccountResponse createGetAccountResponse() {
        return new GetAccountResponse();
    }

    /**
     * Create an instance of {@link Account }
     * 
     */
    public Account createAccount() {
        return new Account();
    }

    /**
     * Create an instance of {@link ConversionEuroToMGA }
     * 
     */
    public ConversionEuroToMGA createConversionEuroToMGA() {
        return new ConversionEuroToMGA();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListAccounts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "listAccounts")
    public JAXBElement<ListAccounts> createListAccounts(ListAccounts value) {
        return new JAXBElement<ListAccounts>(_ListAccounts_QNAME, ListAccounts.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "getAccount")
    public JAXBElement<GetAccount> createGetAccount(GetAccount value) {
        return new JAXBElement<GetAccount>(_GetAccount_QNAME, GetAccount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConversionEuroToMGAResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "ConversionEuroToMGAResponse")
    public JAXBElement<ConversionEuroToMGAResponse> createConversionEuroToMGAResponse(ConversionEuroToMGAResponse value) {
        return new JAXBElement<ConversionEuroToMGAResponse>(_ConversionEuroToMGAResponse_QNAME, ConversionEuroToMGAResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListAccountsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "listAccountsResponse")
    public JAXBElement<ListAccountsResponse> createListAccountsResponse(ListAccountsResponse value) {
        return new JAXBElement<ListAccountsResponse>(_ListAccountsResponse_QNAME, ListAccountsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAccountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "getAccountResponse")
    public JAXBElement<GetAccountResponse> createGetAccountResponse(GetAccountResponse value) {
        return new JAXBElement<GetAccountResponse>(_GetAccountResponse_QNAME, GetAccountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Account }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "account")
    public JAXBElement<Account> createAccount(Account value) {
        return new JAXBElement<Account>(_Account_QNAME, Account.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConversionEuroToMGA }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws/", name = "ConversionEuroToMGA")
    public JAXBElement<ConversionEuroToMGA> createConversionEuroToMGA(ConversionEuroToMGA value) {
        return new JAXBElement<ConversionEuroToMGA>(_ConversionEuroToMGA_QNAME, ConversionEuroToMGA.class, null, value);
    }

}
