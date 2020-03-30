package com.revature.dao;

import java.util.List;

public interface ISecurityDao {
	
	public boolean authenticateCustomer(String customerLoginId, String password);
	public List authenticateCustome1r(String customerLoginId, String password);
	
	
}
