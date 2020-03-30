package com.revature.models;

public class Account {
	private int id;
	private String accountNumber;
	private double balance = 0.0;
	private int accountTypeId;

	public Account() {

	}

	public Account(int id, String accountNumber, int accountTypeId, double initialBalance) {
		super();
		this.id = id;
		this.accountTypeId = accountTypeId;
		this.balance = initialBalance;

	}

	public Account(String accountNumber, int accountTypeId, double initialBalance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = initialBalance;
		this.accountTypeId = accountTypeId;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", balance=" + balance + ", accountTypeId=" + accountTypeId + "getId()="
				+ getId() + ", getBalance()=" + getBalance() + ", getAccountTypeId()=" + getAccountTypeId()
				+ ", toString()=" + super.toString() + "]";
	}

}
