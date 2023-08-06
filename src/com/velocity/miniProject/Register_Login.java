package com.velocity.miniProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register_Login {
	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	// String uName;

	public static int insertUserdetails() {
		try {
			Scanner s = new Scanner(System.in);
			ArrayList<String> dataList = new ArrayList<String>();
			con = DbConnection.getConnectionDetails();
			System.out.println("Enter the first name: ");
			String fname = s.next();
			dataList.add(fname);
			System.out.println("Enter the last name: ");
			String lname = s.next();
			dataList.add(lname);
			System.out.println("Enter the username: ");
			String userName = s.next();
			dataList.add(userName);
			System.out.println("Enter the password: ");
			String password = s.next();
			dataList.add(password);
			System.out.println("Enter the city: ");
			String city = s.next();
			dataList.add(city);
			System.out.println("Enter the mail id: ");
			String mailid = s.next();
			String regex = "^(.+)@(.+)$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(mailid);
			if (matcher.matches()) {
				dataList.add(mailid);
			} else {
				System.out.println("Please Enter the Correct Email-id");
				mailid = s.next();
				dataList.add(mailid);
			}
			System.out.println("Enter the mobile number: ");
			String phoneNo = s.next();

			String reg = "\\d{10}";
			Pattern pat = Pattern.compile(reg);
			Matcher matche = pat.matcher(phoneNo);
			boolean val = matche.matches();
			if (val == false) {
				System.out.println("Enter the Correct No:");
				phoneNo = s.next();
				dataList.add(phoneNo);

			} else {
				dataList.add(phoneNo);
			}

			String query = "insert into user(firstName,lastName,userName,userPassword,city,mailId,mobileNumber)"
					+ "values(?,?,?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, dataList.get(0));
			ps.setString(2, dataList.get(1));
			ps.setString(3, dataList.get(2));
			ps.setString(4, dataList.get(3));
			ps.setString(5, dataList.get(4));
			ps.setString(6, dataList.get(5));
			ps.setString(7, dataList.get(6));
			int i = ps.executeUpdate();
			return i;
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				con.close();
				ps.close();
			} catch (Exception e) {
				e.getMessage();
			}
		}

		return 0;

	}

	public static HashMap<Integer, String> login() {
		HashMap<Integer, String> hs = new HashMap<Integer, String>();
		try {
			Scanner s = new Scanner(System.in);

			con = DbConnection.getConnectionDetails();

			System.out.println("Enter the User name: ");
			String uName = s.next();
			hs.put(1, uName);

			System.out.println("Enter the Password: ");
			String paswrd = s.next();
			String query = "select * from user where userName=? and userPassword = ?";
			ps = con.prepareStatement(query);

			ps.setString(1, uName);
			ps.setString(2, paswrd);
			rs = ps.executeQuery();
			if (rs.next()) {
				hs.put(2, "1");
				return hs;
			} else {
				hs.put(2, "0");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		finally {
			try {
				con.close();
				ps.close();
			} catch (Exception e) {
				e.getMessage();
			}
		}
		return hs;

	}

}
