package com.velocity.miniProject;

import java.util.HashMap;
import java.util.Scanner;

public class User_Admin_Impl implements User, Admin {
	
	@Override
	public void userOperation() {
		Scanner s = new Scanner(System.in);

		System.out.println("1. Student Registration for the first time visitor" + "\n"
				+ "2. Student Login for registered student");
		int choice = s.nextInt();
		switch (choice) {

		case 1:
			int i = Register_Login.insertUserdetails();
			if (i == 1) {
				System.out.println("Registration Sucessfull.");				
			} else {
				System.out.println("Failed to Register, please try again");
			}
			break;
		case 2:
			int count = 1;
			do {
				HashMap<Integer, String> res = Register_Login.login();
				String userName = res.get(1);
				String val = res.get(2);

				if (val == "1") {
					System.out.println("Logged into System Sucessfully");
					System.out
							.println("1. Do you want to attempt quiz ? Press 1 " + "\n" + "2. Want to exit ? Press 2");
					choice = s.nextInt();

					switch (choice) {
					case 1:
						Questions.getQuestion(userName);
						break;
					case 2:
						System.out.print("Thank You. Visit Again");

						break;

					}
					s.close();
					System.exit(0);
				}

				else {
					count++;
					if (count == 6) {
						System.out.println("You have finished your login attempts");
						System.out.println("Please Relaunch the App and enter the correct Credentials.");

						s.close();
						System.exit(0);
					} else {
						System.out.println("Login Attempt failed. Login Attempt- " + count);
					}
				}
			} while (count < 6);

		default:
			System.out.println("Invalid Input try again");
		}

	}

	@Override
	public void adminOperation() {
		Scanner s = new Scanner(System.in);
		Students_Record sr = new Students_Record();
		System.out.println("1. Display all students score as per ascending order" + "\n"
				+ "2. Fetch student score by using id" + "\n" + "3. Add question with 4 options into database");

		int choice = s.nextInt();

		switch (choice) {

		case 1:
			sr.displayallStudentsScore();
			break;
		case 2:
			System.out.println("Please Enter Student-ID to fetch Score");
			int id = s.nextInt();
			sr.fetchscorebyid(id);
			break;
		case 3:
			Questions.addQuestions();
			String ch;
			do {
				System.out.println("Do you want to add more question? (Y/N)");
				ch = s.next();
				if (ch.equalsIgnoreCase("Y")) {
					Questions.addQuestions();
				} else {
					System.out.println("Thank You Visit Again");
					ch = "N";
					break;
				}
			} while (!ch.equalsIgnoreCase("N"));

			break;
		}
		s.close();

	}

}
