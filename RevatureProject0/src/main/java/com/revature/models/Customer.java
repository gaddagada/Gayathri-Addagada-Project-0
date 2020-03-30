package com.revature.models;

public class Customer {
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private String loginID;
	private String password;
	private String authtype;

	// Constructor with fields
	public Customer() {
		super();
	}

	public Customer(String firstname, String lastname, String email, String loginID, String password, String authtype) {
		super();

		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.loginID = loginID;
		this.password = password;
		this.authtype = authtype;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLoginId() {
		return loginID;
	}

	public void setLoginId(String username) {
		this.loginID = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthtype() {
		return authtype;
	}

	public void setAuthtype(String authtype) {
		this.authtype = authtype;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", loginID=" + loginID + ", password=" + password + ", authtype=" + authtype + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(int int1) {
		// TODO Auto-generated method stub

	}

}
