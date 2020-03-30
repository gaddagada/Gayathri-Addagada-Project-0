package com.revature.models;

public class AccountType {
	private int id;
	private String accountType;
	
	public AccountType() {
		
	}
	
	public AccountType(String accountType) {
		super();
		this.accountType = accountType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
}
