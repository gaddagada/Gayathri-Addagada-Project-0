package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exception.BankingException;
import com.revature.models.Account;
import com.revature.util.ConnectionUtil;

public class AccountDaoImpl implements AccountDao {

	@Override
	public String createAccount(Account account, String loginid) throws BankingException {

		String insertAccountSQL = "insert into Bank.Account (ACCOUNT_NUMBER,ACC_TYPE_ID, ACC_BALANCE) values (?,?,?)";
		String customerSQL = "Select * from BANK.Customer where login_id=?";
		String connectionTableSQL = "insert into BANK.Customer_Account (CUSTOMER_ID,ACC_ID) values (?,?)";

		String selectAccountSQL = "Select * from BANK.Account where account_number=?";

		int newAccountId = 0;
		int customerId = 0;
		Connection conn = null;
		try {
			conn = ConnectionUtil.getPostgreSQLConnection();
			PreparedStatement ps = conn.prepareStatement(insertAccountSQL);
			ps.setString(1, account.getAccountNumber());
			ps.setInt(2, account.getAccountTypeId());
			ps.setDouble(3, account.getBalance());

			ps.execute();

			// get ID of the newly created account
			PreparedStatement ps2 = conn.prepareStatement(selectAccountSQL);
			ps2.setString(1, account.getAccountNumber());

			ResultSet rs = ps2.executeQuery();
			// Get the account ID for the new account created
			while (rs.next()) {
				newAccountId = rs.getInt("ACC_ID");
			}
		} catch (SQLException e) {
			throw new BankingException(e.getMessage());
			// e.printStackTrace();
		}

		// Get customer ID for the loggedin user
		try {
			PreparedStatement ps2 = conn.prepareStatement(customerSQL);
			ps2.setString(1, loginid);
			ResultSet rs2 = ps2.executeQuery();

			while (rs2.next()) {
				customerId = rs2.getInt("customer_Id");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException(e.getMessage());
			// e.printStackTrace();
			// return "Account Creation Failed";
		}

		// Get custmoer ID for the loggedin user
		try {
			PreparedStatement ps3 = conn.prepareStatement(connectionTableSQL);
			ps3.setInt(1, customerId);
			ps3.setInt(2, newAccountId);

			ps3.execute();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException(e.getMessage());
			// e.printStackTrace();
			// return "Account Creation Failed";

		}

		return "AccountCreated";
	}

	@Override
	public double deposit(String accountNumber, double depositAmount) {
		String curBalanceSQL = "select acc_balance from Bank.ACCOUNT where account_number=?";
		String updateBalanceSQL = "update Bank.ACCOUNT set acc_balance= ? where account_number= ?";

		double currBalance = 0.0;
		double newBalance = 0.0;
		try (Connection conn = ConnectionUtil.getPostgreSQLConnection();
				PreparedStatement ps = conn.prepareStatement(curBalanceSQL)) {
			ps.setString(1, accountNumber);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				currBalance = rs.getDouble("acc_balance");
			}
			newBalance = currBalance + depositAmount;
			try {
				PreparedStatement ps2 = conn.prepareStatement(updateBalanceSQL);
				ps2.setDouble(1, (currBalance + depositAmount));
				ps2.setString(2, accountNumber);
				int result = ps2.executeUpdate();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newBalance;
	}

	@Override
	public double withdraw(String accountNumber, double withdrawAmount) {
		String curBalanceSQL = "select acc_balance from Bank.ACCOUNT where account_number=?";
		String updateBalanceSQL = "update Bank.ACCOUNT set acc_balance= ? where account_number= ?";

		double currBalance = 0.0;
		double newBalance = 0.0;
		try (Connection conn = ConnectionUtil.getPostgreSQLConnection();
				PreparedStatement ps = conn.prepareStatement(curBalanceSQL)) {
			ps.setString(1, accountNumber);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				currBalance = rs.getDouble("acc_balance");
			}
			newBalance = currBalance - withdrawAmount;
			try {
				PreparedStatement ps2 = conn.prepareStatement(updateBalanceSQL);
				ps2.setDouble(1, newBalance);
				ps2.setString(2, accountNumber);
				int result = ps2.executeUpdate();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newBalance;
	}

	@Override
	public double viewBalance(String accountNumber) {
		String sql = "select * from bank.account where account_number= ?";
		Account acctFromDB = new Account();

		try (Connection c = ConnectionUtil.getPostgreSQLConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, accountNumber);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				acctFromDB.setAccountNumber(rs.getString("account_number"));
				acctFromDB.setBalance(rs.getDouble("acc_balance"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acctFromDB.getBalance();
	}

	@Override
	public List<String> getAccountsList(String customerLoginID) throws BankingException {
		// TODO Auto-generated method stub
		int customerId = getCustomerIDForLoginID(customerLoginID);

		String accountListSQL = "select acc_id from BANK.Customer_Account where customer_id=?";
		List<String> accountList = new ArrayList<String>();
		try {
			Connection conn = ConnectionUtil.getPostgreSQLConnection();
			PreparedStatement ps2 = conn.prepareStatement(accountListSQL);
			ps2.setInt(1, customerId);
			ResultSet rs2 = ps2.executeQuery();

			while (rs2.next()) {
				accountList.add(getAccountNumberForAcctID(rs2.getInt("acc_id")));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException(e.getMessage());
			// e.printStackTrace();
			// return "Account Creation Failed";
		}

		return accountList;
	}

	private int getCustomerIDForLoginID(String loginId) throws BankingException {
		String customerSQL = "Select * from BANK.Customer where login_id=?";
		// Get customer ID for the loggedin user
		int customerId = 0;
		try {
			Connection conn = ConnectionUtil.getPostgreSQLConnection();
			PreparedStatement ps2 = conn.prepareStatement(customerSQL);
			ps2.setString(1, loginId);
			ResultSet rs2 = ps2.executeQuery();

			while (rs2.next()) {
				customerId = rs2.getInt("customer_Id");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException(e.getMessage());
			// e.printStackTrace();
			// return "Account Creation Failed";
		}
		return customerId;

	}

	private String getLoginIDForCustomerID(int custIdFromDB) throws BankingException {
		String customerSQL = "Select login_id from BANK.Customer where customer_Id=?";
		// Get customer ID for the loggedin user
		String loginId = "";
		try {
			Connection conn = ConnectionUtil.getPostgreSQLConnection();
			PreparedStatement ps2 = conn.prepareStatement(customerSQL);
			ps2.setInt(1, custIdFromDB);
			ResultSet rs2 = ps2.executeQuery();

			while (rs2.next()) {
				loginId = rs2.getString("login_id");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException(e.getMessage());
			// e.printStackTrace();
			// return "Account Creation Failed";
		}
		return loginId;

	}

	private String getAccountNumberForAcctID(int account_id) throws BankingException {
		String accountNumberSQL = "Select account_number from BANK.Account where acc_id=?";
		// Get customer ID for the loggedin user
		String accountNumber = "";
		try {
			Connection conn = ConnectionUtil.getPostgreSQLConnection();
			PreparedStatement ps2 = conn.prepareStatement(accountNumberSQL);
			ps2.setInt(1, account_id);
			ResultSet rs2 = ps2.executeQuery();

			while (rs2.next()) {
				accountNumber = rs2.getString("account_number");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException(e.getMessage());
			// e.printStackTrace();
			// return "Account Creation Failed";
		}
		return accountNumber;

	}

	private int getAccountIDForAccountNumber(String acctNumber) throws BankingException {
		String accountIdSQL = "Select acc_id from BANK.Account where account_number=?";
		// Get customer ID for the loggedin user
		int accountId = 0;
		try {
			Connection conn = ConnectionUtil.getPostgreSQLConnection();
			PreparedStatement ps2 = conn.prepareStatement(accountIdSQL);
			ps2.setString(1, acctNumber);
			ResultSet rs2 = ps2.executeQuery();

			while (rs2.next()) {
				accountId = rs2.getInt("acc_id");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException(e.getMessage());
			// e.printStackTrace();
			// return "Account Creation Failed";
		}
		return accountId;

	}

	public String addCustomerToAccount(String loginId, String accoutNumber) throws BankingException {

		int customerId = this.getCustomerIDForLoginID(loginId);
		int accuntId = this.getAccountIDForAccountNumber(accoutNumber);
		String connectionTableSQL = "insert into BANK.Customer_Account (CUSTOMER_ID,ACC_ID) values (?,?)";

		// Get customerr ID for the loggedin user
		try {
			Connection conn = ConnectionUtil.getPostgreSQLConnection();
			PreparedStatement ps3 = conn.prepareStatement(connectionTableSQL);
			ps3.setInt(1, customerId);
			ps3.setInt(2, accuntId);

			ps3.execute();

		} catch (Exception e) {

			// TODO Auto-generated catch block
			throw new BankingException("Failed to add Customer to the account");
			// e.printStackTrace();
			// return "Account Creation Failed";

		}

		return "Cusomer Added to Account";

	}

	@Override
	public List<String> getCustomerListForAccount(String accountNumber) throws BankingException {
		// TODO Auto-generated method stub
		int accountId = this.getAccountIDForAccountNumber(accountNumber);

		String accountListSQL = "select customer_id from BANK.Customer_Account where acc_id=?";
		List<String> customerList = new ArrayList<String>();
		try {
			Connection conn = ConnectionUtil.getPostgreSQLConnection();
			PreparedStatement ps2 = conn.prepareStatement(accountListSQL);
			ps2.setInt(1, accountId);
			ResultSet rs2 = ps2.executeQuery();

			while (rs2.next()) {
				customerList.add(this.getLoginIDForCustomerID(rs2.getInt("customer_id")));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BankingException(e.getMessage());
			// e.printStackTrace();
			// return "Account Creation Failed";
		}

		return customerList;
	}

	public void addExistingAccountToCustomer(String accountNumber, String loginId) throws BankingException {

		int customerId = this.getCustomerIDForLoginID(loginId);
		int accuntId = this.getAccountIDForAccountNumber(accountNumber);
		String connectionTableSQL = "insert into BANK.Customer_Account (CUSTOMER_ID,ACC_ID) values (?,?)";

		// Get customer ID for the loggedin user
		try {
			Connection conn = ConnectionUtil.getPostgreSQLConnection();
			PreparedStatement ps3 = conn.prepareStatement(connectionTableSQL);
			ps3.setInt(1, customerId);
			ps3.setInt(2, accuntId);

			ps3.execute();

		} catch (Exception e) {

			// TODO Auto-generated catch block
			throw new BankingException("Failed to add account to the customer");
			// e.printStackTrace();
			// return "Account Creation Failed";

		}

	}

	public List<Double> transferMoney(String fromAccount, String toAccount, double transferAmount) throws BankingException{


		
		String curBalanceSQL = "select acc_balance from Bank.ACCOUNT where account_number=?";
		String updateBalanceSQL = "update Bank.ACCOUNT set acc_balance= ? where account_number= ?";
		List<Double>  newBalances = new ArrayList<Double>();
		double currBalance = 0.0;
		double newBalance = 0.0;
		//Deduct money from the fromaccount
		try {
			Connection conn = ConnectionUtil.getPostgreSQLConnection();
			PreparedStatement ps1 = conn.prepareStatement(curBalanceSQL);				
			ps1.setString(1, fromAccount);
			ResultSet rs = ps1.executeQuery();
			while (rs.next()) {
				currBalance = rs.getDouble("acc_balance");
			}
			newBalance = currBalance - transferAmount;
			
			PreparedStatement ps2 = conn.prepareStatement(updateBalanceSQL);
			ps2.setDouble(1, newBalance);
			ps2.setString(2, fromAccount);
			int result = ps2.executeUpdate();
			//if everything goes well till here add from account balance to list
			newBalances.add(newBalance)	;

			PreparedStatement ps3 = conn.prepareStatement(curBalanceSQL);				
			ps3.setString(1, toAccount);
			ResultSet rs2 = ps3.executeQuery();
			while (rs2.next()) {
				currBalance = rs2.getDouble("acc_balance");
			}
			newBalance = currBalance + transferAmount;
				
			PreparedStatement ps4 = conn.prepareStatement(updateBalanceSQL);
			ps4.setDouble(1, newBalance);
			ps4.setString(2, toAccount);
			int result2 = ps4.executeUpdate();
			//if everything goes well till here add the to account balance to list
			newBalances.add(newBalance)	;

		} catch (SQLException e) {
			throw new BankingException("Transfer money from account " + fromAccount + " to account " + toAccount + " failed");
		}
		

		return newBalances;
	}

}
