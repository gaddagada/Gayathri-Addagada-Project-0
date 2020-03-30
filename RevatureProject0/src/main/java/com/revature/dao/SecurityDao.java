package com.revature.dao;

import java.util.List;

public abstract class SecurityDao {
	public abstract boolean authenticateCustomer(String customerLoginId, String password);
}
