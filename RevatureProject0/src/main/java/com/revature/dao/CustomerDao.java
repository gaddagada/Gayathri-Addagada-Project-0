package com.revature.dao;

import java.util.List;

import com.revature.exception.BankingException;
import com.revature.models.Customer;

public interface CustomerDao {

	public String createCustomer(Customer newCustomer) throws BankingException;

	public Customer viewCustomer(int customerId) throws BankingException;

	public String updateCustomer(Customer updatedCustomer) throws BankingException;

	public String deleteCustomer(int customerId) throws BankingException;

	public List<Customer> getCustomersForAccount(String accountNumber) throws BankingException;

	public List<Customer> getAllCustomers() throws BankingException;

}
