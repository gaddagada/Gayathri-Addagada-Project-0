package com.revature.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class SecurityDaoImplTest {
	private SecurityDaoImpl sd = new SecurityDaoImpl();

	@Test
	public void testAuthenticateCustomer() {
		try {
			SecurityDaoImpl sd = new SecurityDaoImpl();
			boolean isAuthenticated = sd.authenticateCustomer("gaddagada9", "demo123");
			assertTrue(isAuthenticated);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	@Test
//	public void testAuthenticateInvalidCustomer() {
//		try {
//			SecurityDaoImpl sd = new SecurityDaoImpl();
//			boolean isAuthenticated = sd.authenticateCustomer("gaddagada7", "demo123");
//			assertFalse(isAuthenticated);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	
//	@Test 
//	public void testAuthenticateCustomerIfInvalid() {
//		try {
//			SecurityDaoImpl sd = new SecurityDaoImpl();
//			boolean isAuthenticated = sd.authenticateCustomer("gaddagada6", "demo123");
//			assertFalse(isAuthenticated);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
