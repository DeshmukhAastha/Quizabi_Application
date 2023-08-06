package com.velocity.miniProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Students_Record {
	static Connection con = null;
	static PreparedStatement ps = null;
	ResultSet rs = null;

	public void displayallStudentsScore() {

		String query = "SELECT userid,firstName,lastName,city,mailId,mobileNumber,score FROM user ORDER BY score ASC";

		try {

			con = DbConnection.getConnectionDetails();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery(query);

			while (rs.next()) {
				String q = rs.getString("userid");
				String a = rs.getString("firstName");
				String b = rs.getString("lastName");
				String c = rs.getString("city");
				String d = rs.getString("mailId");
				String e = rs.getString("mobileNumber");
				int f = rs.getInt("score");

				System.out.println("User-Id= " + q + "\n" + "First Name= " + a + "\n" + "Last Name= " + b + "\n"
						+ "City = " + c + "\n" + "Email-Id = " + d + "\n" + "Mobile Number = " + e + "\n" + "Score= "
						+ f + "\n");
				System.out.println("-----------------------------");
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	}

	public void fetchscorebyid(int id) {
		String query = "SELECT firstName,lastName,score from user where userid=" + id;
		try {
			con = DbConnection.getConnectionDetails();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery(query);

			while (rs.next()) {
				String a = rs.getString("firstName");
				String b = rs.getString("lastName");
				int f = rs.getInt("score");

				System.out.println("First Name= " + a + "  " + "Last Name= " + b + "  " + "Score= " + f);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (Exception e) {

				e.printStackTrace();
			}

		}

	}

	public static int getAnswer(String q, String a, String b, String c, String d) {

		System.out.println(q);
		System.out.println("1." + a);
		System.out.println("2." + b);
		System.out.println("3." + c);
		System.out.println("4." + d);
		System.out.println("Enter option :");
		Scanner sc = new Scanner(System.in);
		int ans = sc.nextInt();

		return ans;

	}

	public static boolean isAnsValidate(int anscaptureAns, int correctAnswer) {

		if (correctAnswer == anscaptureAns) {

			return true;
		} else {

			// System.out.println("Wrong ANswer");
			return false;
		}
	}

	public static void setScore(int score, String uName) {

		try {

			String query = "update user set Score=? where userName=?";

			con = DbConnection.getConnectionDetails();
			ps = con.prepareStatement(query);

			ps.setInt(1, score);
			ps.setString(2, uName);

			ps.execute();
			System.out.println("Score Updated successfully=======");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
	}

}
