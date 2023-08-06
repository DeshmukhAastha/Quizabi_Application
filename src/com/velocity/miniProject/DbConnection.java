package com.velocity.miniProject;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	static Connection con = null;

	public static Connection getConnectionDetails() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "root");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;

	}
}
