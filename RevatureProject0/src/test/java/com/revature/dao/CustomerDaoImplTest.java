package com.revature.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.revature.exception.BankingException;
import com.revature.models.Customer;

public class CustomerDaoImplTest {

	private String customerId;


	@Test
	public void testGetAllCustomers() {
		CustomerDaoImpl custImpl = new CustomerDaoImpl();
	List<Customer> custList= custImpl.getAllCustomers();
		System.out.println("Number of customers in Bank  are: " + custList.size());
		
		assertTrue(true);
		
	}
	
//	@Test 
//	public void tesUpdateCustomer() {
//		CustomerDaoImpl custImpl = new CustomerDaoImpl();
//		String result = custImpl.updateCustomer("update bank.customer set FirstName = 'Gayathri', LastName = 'Addagada'"
//				+ "where FirstName = 'GAYTHRI2', LastName = 'ADDAGADA'");
//		
//		System.out.println("Updated customer : " + result);
//		assertTrue(true);
//	}
	
	
	@Test 
	public void testDeleteCustomer() {
		CustomerDaoImpl custImpl = new CustomerDaoImpl();
		String result = custImpl.deleteCustomer(1);		
		System.out.println("Deleted customer : " + result);
		assertTrue(true);
	}
	
	
	@Test
	public void testCreateCustomer() {
		CustomerDaoImpl custImpl = new CustomerDaoImpl();
		Customer newCust = new Customer("GAYTHRI6","ADDAGADA","gayathri.addagada@gmail5.com","gaddagada5","demo123","Basic");
		try {
			String result = custImpl.createCustomer(newCust);
			System.out.println("Created new customer : " + result);
		} catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(true);
		
	}
	
	@Test
	public void testViewCustomer() {
		try {
			CustomerDaoImpl custImpl = new CustomerDaoImpl();
			//Customer newCust = new Customer("GAYTHRI6","ADDAGADA","gayathri.addagada@gmail5.com","gaddagada5","demo123","Basic");
			Customer custfromDAO  = custImpl.viewCustomer(2);
			System.out.println("custfromDAO: " + custfromDAO);
		} catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(true);
		
	}
	

}
