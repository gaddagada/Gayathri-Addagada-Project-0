package com.revature.models;

public class AuthenticationType {

	private String basicAuth;
	private String encryptedAuth;
	
	public String getBasicAuth() {
		return basicAuth;
	}
	public void setBasicAuth(String basicAuth) {
		this.basicAuth = basicAuth;
	}
	public String getEncryptedAuth() {
		return encryptedAuth;
	}
	public void setEncryptedAuth(String encryptedAuth) {
		this.encryptedAuth = encryptedAuth;
	}

}
