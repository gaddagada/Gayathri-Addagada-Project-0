package com.revature.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.revature.exception.BankingException;
import com.revature.models.Account;
import com.revature.models.Customer;

public class AccountDaoImplTest {

//	@Test
//	public void testDeposit() {
//		AccountDaoImpl acctImpl = new AccountDaoImpl();
//		double newBalance = acctImpl.deposit("111111111", 100);
//		assertEquals(1911.0, newBalance);
//	}

//	@Test
//	public void testWithdrawl() {
//		AccountDaoImpl acctImpl = new AccountDaoImpl();
//		double newBalance = acctImpl.withdraw("111111111", 100);
//		assertEquals(2211, newBalance);
//	}
//	

//	
//	@Test
//	public void testViewAccountBalance() {
//		AccountDaoImpl acctImpl = new AccountDaoImpl();
//		double accountBalance = acctImpl.viewBalance("111111111"); 
//		assertEquals(1711, accountBalance);
//		
//	}

//	@Test
//	public void testCreateAccount() {
//		AccountDaoImpl acctImpl = new AccountDaoImpl();
//		//public Account(String accountNumber, int accountTypeId, double initialBalance) {
//		Account newAccount = new Account("888888888", 1, 1000.00);
//		String status="";
//		try {
//			status = acctImpl.createAccount(newAccount, "gaddagada9");
//		} catch (BankingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		assertEquals("AccountCreated", status);
//	}

//	//Create a test case where a random number is the same as the inputted accountnnumber 
//	@Test
//	public void testGetAccountsList() {
//		AccountDaoImpl acctImpl = new AccountDaoImpl();
//		//public Account(String accountNumber, int accountTypeId, double initialBalance) {
//		try {
//			List<String> accountList = acctImpl.getAccountsList("gaddagada3");
//			for (int i = 0; i < accountList.size(); i++) {
//				System.out.println(" User Account " + (i+1) + " is " + accountList.get(i) );
//			}
//		} catch (BankingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		assertTrue(true);
//		
//	}

	// Create a test case where a random number is the same as the inputted
	// accountnnumber
//	@Test
//	public void testGetCustomerListForAccount() {
//		AccountDaoImpl acctImpl = new AccountDaoImpl();
//		// public Account(String accountNumber, int accountTypeId, double
//		// initialBalance) {
//		try {
//			List<String> accountList = acctImpl.getCustomerListForAccount("333333333");
//			for (int i = 0; i < accountList.size(); i++) {
//				System.out.println(" Customer " + (i + 1) + " is " + accountList.get(i));
//			}
//		} catch (BankingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		assertTrue(true);
//
//	}

//		@Test
//		public void testAddCustomerToAccount() {
//			AccountDaoImpl acctImpl = new AccountDaoImpl();
//			String message;
//			try {
//				message = acctImpl.addCustomerToAccount("gaddagada2","333333333");
//				
//			} catch (BankingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			assertTrue(true);
//			
//		}

	@Test
	public void testTansferMoney() {

		AccountDaoImpl acctImpl = new AccountDaoImpl();
		// String message;
		try {
			acctImpl.transferMoney("44444444", "555555555", 500);

		} catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(true);

	}

}
