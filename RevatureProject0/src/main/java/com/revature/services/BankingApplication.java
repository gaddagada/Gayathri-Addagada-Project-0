package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDaoImpl;
import com.revature.dao.CustomerDaoImpl;
import com.revature.dao.SecurityDaoImpl;
import com.revature.exception.BankingException;
import com.revature.models.Account;
import com.revature.models.Customer;

public class BankingApplication {
	// instantiate the SecurityDaoImpl method
	public static void main(String[] args) {
		System.out.println("Welcome to the Banking Application");

		// Use case-1: Create user account with username and password
		System.out.println("\n---- Use case-1: Create user account with username and password ----");
		System.out.println("       Creating User account");
		CustomerDaoImpl custImpl = new CustomerDaoImpl();
		Customer newCust = new Customer("GAYATHRI18", "ADDAGADA", "gayathri18.addagada@gmail.com", "gaddagada18",
				"demo123", "Basic");
		try {
			String result = custImpl.createCustomer(newCust);
			System.out.println("     User account " + newCust.getLoginId() + " is created----");
			System.out.println(
					"          Hello " + newCust.getFirstname() + ". Welcome to Banking App. Please submit your transactions");

		} catch (BankingException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());

		}

		// Use case-2: Login
		System.out.println("\n---- Use case-2: Login ----");

		System.out.println("      Login to Banking application");
		boolean isAuthenticated = false;
		Scanner sc = null;
		String userNameInput = "";
		String passwordInput = "";
		SecurityDaoImpl securityImpl;

		do {
			sc = new Scanner(System.in);
			System.out.println("            Enter your username: \n\n");
			userNameInput = sc.nextLine();
			// System.out.println(userNameInput);
			System.out.println("            Enter your password: \n\n");
			passwordInput = sc.nextLine();

			securityImpl = new SecurityDaoImpl();
			isAuthenticated = securityImpl.authenticateCustomer(userNameInput, passwordInput);
			if (!(isAuthenticated)) {
				System.out.println("Invalid credentials.Please enter again: ");
			}

		} while (isAuthenticated == false);

		// Use case-3: Create bank account and associate with user
		System.out.println("\n---- Use case-3: Create bank account with $ 1000 initial balance and associate with the new customer----");

		AccountDaoImpl acctImpl = new AccountDaoImpl();
		Account newAccount = new Account("188888888", 1, 1000.00);
		String status = "";
		try {
			status = acctImpl.createAccount(newAccount, newCust.getLoginId());// Assign the account to the new customer
																				// created
		} catch (BankingException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		System.out.println("            Account " + newAccount.getAccountNumber() + " is created ");
		// Use case-4: Deposit Money
		System.out.println("\n---- Use case-4: Deposit Money----");
		System.out.println("       Depositing $200  to Account " + newAccount.getAccountNumber());

		double newBalance = acctImpl.deposit(newAccount.getAccountNumber(), 200);
		System.out.println("       Deposit complete. Updated balance is : " + newBalance);

		// Use case-5: Withdraw Money
		System.out.println("\n---- Use case-5: Withdraw Money----");
		System.out.println("        Withdrawing $100 from Account " + newAccount.getAccountNumber());
		acctImpl = new AccountDaoImpl();
		newBalance = acctImpl.withdraw(newAccount.getAccountNumber(), 100);

		// Use case-6: View Account balance
		System.out.println("\n----Use case-6: View Account balance----");
		double accountBalance = acctImpl.viewBalance(newAccount.getAccountNumber());
		System.out.println("       Balance in the account " + newAccount.getAccountNumber() + " is : " + accountBalance);

		// Use case-7:  Add an existing customer to the new account
		System.out.println("\n----Use case-7: Add an existing customer to the new account----");	
		try {
			acctImpl.addCustomerToAccount("gaddagada2", newAccount.getAccountNumber());

		} catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("     Completed adding customer gaddagada2 to the new account");
		
		// Use case-8:  Add an existing account to the new customer
		System.out.println("\n----Use case-8:  Add an existing account to the new customer----");	
		try {
			acctImpl.addExistingAccountToCustomer("555555555", newCust.getLoginId());

		} catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("       Completed adding existing account 555555555 to new customer");
		
		// Use case-9: Display multiple customers for an account
		System.out.println("\n----Use Case-8: Display multiple customers for an account---");

		try {
			List<String> accountList = acctImpl.getCustomerListForAccount(newAccount.getAccountNumber());
			for (int i = 0; i < accountList.size(); i++) {
				System.out.println("        Customer " + (i + 1) + " for " + newAccount.getAccountNumber() + ": " + accountList.get(i));
			}
		} catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Use case-10: Display multiple accounts for a customer
		System.out.println("\n----Use case-7: Display multiple accounts for a customer----");
		try {
			List<String> accountList = acctImpl.getAccountsList(newCust.getLoginId());
			for (int i = 0; i < accountList.size(); i++) {
				System.out.println("       Account " + (i + 1) + " for " +  newCust.getLoginId() + ": " + accountList.get(i));
			}
		} catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Use case-11: Transfer money between accounts
		System.out.println("\n----Use case-11: Transfer money between accounts----");
		System.out.println("       Transferring $300 from new account to account 44444444----");
		List<Double> balances= null;
		try {
			balances = acctImpl.transferMoney(newAccount.getAccountNumber(),"44444444",300);
			
		} catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (balances != null) {
			System.out.println("       "+ newAccount.getAccountNumber() + " balance after transfer is " + balances.get(0));
			System.out.println("          44444444 balance after transfer is " + balances.get(1));
					
		}
		
		
		// Use case-12: Log out
		System.out.println("\n---- Use case-9: Log out ---");
		System.out.println("        Do you want to log out (Y/N) : \n");
		sc = new Scanner(System.in);
		String logoutResponse = sc.nextLine();
		if (logoutResponse.equals("Y")) {
			securityImpl.logOut();
			System.out.println("     User logged off. Please log back in to use the app\n");
			System.exit(0);
		} else {

			// Use case-8: Do not allow transactions without user login
			CustomerDaoImpl custDaoImpl = new CustomerDaoImpl();

			// System throws Banking exception if not logged in
			try {
				Customer customer = custDaoImpl.viewCustomer(2);
				System.out.println("      Displaying Customer Info. \n" + customer);
			} catch (BankingException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}

}