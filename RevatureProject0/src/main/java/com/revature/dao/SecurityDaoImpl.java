package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.util.ConnectionUtil;

public class SecurityDaoImpl {
	public static boolean isValidCustomer=true;//for testing
	
	public boolean authenticateCustomer(String customerLoginId, String password) {

		//SecurityDaoImpl secImpl = new SecurityDaoImpl();

		String sql = "select * from bank.customer where LOGIN_ID = ?";
		// Customer custFromDB = new Customer();
		String pwdFromDB = "";

		try {
			Connection c = ConnectionUtil.getPostgreSQLConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, customerLoginId);
			ps.execute();

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pwdFromDB = rs.getString("LOGIN_PASSWORD");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if ((pwdFromDB != null) && (pwdFromDB.equals(password))) {
			isValidCustomer = true;
			return true;
		}

		return false;
	}
	
	
	public void logOut() {
		try {
			ConnectionUtil.closePostgreSQLConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isValidCustomer=false;
	} 

}