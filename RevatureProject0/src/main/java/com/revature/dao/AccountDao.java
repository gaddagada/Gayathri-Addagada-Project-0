package com.revature.dao;

import java.util.List;

import com.revature.exception.BankingException;
import com.revature.models.Account;

public interface AccountDao {

	public String createAccount(Account account, String loginId) throws BankingException;
	public double deposit(String accountNumber, double depositAmount);
	public double withdraw(String accountNumber, double depositAmount);
	public double viewBalance(String accountNumber);
	public List<String> getAccountsList(String customerLoginID) throws BankingException;
	public List<String> getCustomerListForAccount(String accountNumber) throws BankingException;
	public String addCustomerToAccount(String logind, String accoutNumber) throws BankingException;
	public void addExistingAccountToCustomer(String string, String loginId) throws BankingException;
	public List<Double> transferMoney(String fromAccount, String toAccount, double transferAmount) throws BankingException;
		
}
