package com.velocity.miniProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Questions {
	static Connection con = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	Students_Record sr = new Students_Record();

	public static  void addQuestions() {
		Scanner sc = new Scanner(System.in);

		try {

			con = DbConnection.getConnectionDetails();

			System.out.println("Enter the question : ");
			String question = sc.nextLine();

			System.out.println("option 1 : ");
			String optionA = sc.next();

			System.out.println("option 2 : ");
			String optionB = sc.next();

			System.out.println("option 3 : ");
			String optionC = sc.next();

			System.out.println("option 4 : ");
			String optionD = sc.next();

			System.out.println("Enter correct Option(Like(1,2,3,4) : ");
			String correctAns = sc.next();

			String query = "insert into questions(question,option_A,option_B,option_C,option_D,correctAns) values (?,?,?,?,?,?)";
			ps = con.prepareStatement(query);

			ps.setString(1, question);
			ps.setString(2, optionA);
			ps.setString(3, optionB);
			ps.setString(4, optionC);
			ps.setString(5, optionD);
			ps.setString(6, correctAns);
			int i = ps.executeUpdate();
			if (i == 1) {
				System.out.println("Question added Successfully");
			}

			else {
				System.out.println("Failed to add question, please try again");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
			} catch (Exception e) {
				e.getMessage();
			} finally {
				try {
					con.close();
					ps.close();
				} catch (Exception e) {
					e.getMessage();
				}
			}

		}

	}

	public static void getQuestion(String userName) {
		int scoreCounter = 0;
		String question = "select * from questions ORDER BY RAND() LIMIT 10";

		try {

			con = DbConnection.getConnectionDetails();

			ps = con.prepareStatement(question);
			rs = ps.executeQuery(question);

			while (rs.next()) {
				String q = rs.getString("question");
				String a = rs.getString("option_a");
				String b = rs.getString("option_b");
				String c = rs.getString("option_c");
				String d = rs.getString("option_d");
				int correctAnswer = rs.getInt("correctAns");
				int captureAns = Students_Record.getAnswer(q, a, b, c, d);
				boolean bA = Students_Record.isAnsValidate(captureAns, correctAnswer);

				if (bA == true) {
					System.out.println("*************************************");
					scoreCounter++;

				} else {
					System.out.println("**********************************");
				}

			}
			Students_Record.setScore(scoreCounter,userName);
			System.out.println("Your score is : " + scoreCounter);
			System.out.println("Thank you. visit Again.");

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

}
