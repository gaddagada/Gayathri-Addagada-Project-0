package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.exception.BankingException;
import com.revature.models.Customer;
import com.revature.util.ConnectionUtil;

public class CustomerDaoImpl implements CustomerDao {

	public CustomerDaoImpl() {
		super();
	}

	public String createCustomer(Customer newCustomer) throws BankingException {
		// TODO Auto-generated method stub

		String sql = "INSERT INTO Bank.CUSTOMER (FIRSTNAME, LASTNAME, USER_EMAIL,  LOGIN_ID, LOGIN_PASSWORD,  AUTHENCATION_TYPE) VALUES(?,?,?,?,?,?)";

		try (Connection conn = ConnectionUtil.getPostgreSQLConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, newCustomer.getFirstname());
			ps.setString(2, newCustomer.getLastname());
			ps.setString(3, newCustomer.getEmail());
			ps.setString(4, newCustomer.getLoginId());
			ps.setString(5, newCustomer.getPassword());
			ps.setString(6, newCustomer.getAuthtype());

			ps.execute();

		} catch (SQLException e) {
			throw new BankingException("User " + newCustomer.getLoginId() + " already exists");			
			//e.printStackTrace();
		}
		String result = "success";
		return result;
	}

	public String updateCustomer(Customer updatedCustomer) throws BankingException {
		if (!SecurityDaoImpl.isValidCustomer) {
			throw new BankingException("Invalid customer. Please login");
		}

		String sql = "update bank.customer set firstname = ?, lastname = ?, user_email = ?, login_id = ?, login_password = ?, authentication_type = ?,  where  = ?";

		try (Connection c = ConnectionUtil.getPostgreSQLConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, updatedCustomer.getFirstname());
			ps.setString(2, updatedCustomer.getLastname());
			ps.setString(3, updatedCustomer.getEmail());
			ps.setString(4, updatedCustomer.getLoginId());
			ps.setString(5, updatedCustomer.getPassword());
			ps.setString(6, updatedCustomer.getAuthtype());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Customer updated successfully";
	}

	public String deleteCustomer(int customerId) {

		// int rowsDeleted = 0;
		String sql = "delete from BANK.CUSTOMER where  CUSTOMER_ID= ?";

		try (Connection conn = ConnectionUtil.getPostgreSQLConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, customerId);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Deleted Customer";
	}

	public List<Customer> getCustomersForAccount(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		String sql = "select * from bank.customer";

		try (Connection conn = ConnectionUtil.getPostgreSQLConnection();
				Statement ps = conn.createStatement();
				ResultSet rs = ps.executeQuery(sql)) {

			while (rs.next()) {
				// get data from each employee
				Customer cust = new Customer();

				cust.setId(rs.getInt("CUSTOMER_ID"));
				cust.setFirstname(rs.getString("FIRSTNAME"));
				cust.setLastname(rs.getString("LASTNAME"));
				cust.setEmail(rs.getString("USER_EMAIL"));
				cust.setLoginId(rs.getString("LOGIN_ID"));
				cust.setPassword(rs.getString("LOGIN_PASSWORD"));
				cust.setAuthtype(rs.getString("AUTHENCATION_TYPE"));

				customers.add(cust);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customers;
	}

	public Customer viewCustomer(int customerId) throws BankingException {
		if (!SecurityDaoImpl.isValidCustomer) {
			throw new BankingException("Invalid customer. Please login");
		}

		String sql = "select * from bank.customer where CUSTOMER_ID= ?";
		Customer custFromDB = new Customer();

		try (Connection c = ConnectionUtil.getPostgreSQLConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, customerId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				custFromDB.setId(rs.getInt(1));
				custFromDB.setFirstname(rs.getString(2));
				custFromDB.setLastname(rs.getString(3));
				custFromDB.setEmail(rs.getString(4));
				custFromDB.setLoginId(rs.getString(5));
				custFromDB.setPassword(rs.getString(6));
				custFromDB.setAuthtype(rs.getString(7));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// String result = "Updated Customer";
		System.out.print("Customer object from DB " + custFromDB);

		return custFromDB;

	}
}
