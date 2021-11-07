/**
 * BankService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ws;

public interface BankService extends java.rmi.Remote {
    public java.lang.Double conversionEuroToMGA(java.lang.Double amount) throws java.rmi.RemoteException;
    public ws.Account getAccount(java.lang.Integer arg0) throws java.rmi.RemoteException;
    public ws.Account[] listAccounts() throws java.rmi.RemoteException;
}
