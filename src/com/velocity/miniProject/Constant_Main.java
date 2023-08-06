package com.velocity.miniProject;

import java.util.Scanner;

public class Constant_Main {

	public static void main(String[] args) {
		Constant_Main cm = new Constant_Main();
		cm.getUserChoice();
	}

	public void getUserChoice() {
		Scanner s = new Scanner(System.in);
		User_Admin_Impl impl = new User_Admin_Impl();
		System.out.println("Welcome to Console Based Quiz Application" + "\n" + "1. User" + "\n" + "2. Admin");

		System.out.println("Enter your choice-");
		int choice = s.nextInt();

		switch (choice) {

		case 1:
			System.out.println("****Welcome to Quizabi Application****");
			System.out.println("Please choose one option from below options");
			impl.userOperation();
			s.close();
			break;
		case 2:
			System.out.println("*********************Welcome***********************");
			System.out.println("Please choose one option from below options");
			impl.adminOperation();
			s.close();
			break;

		}
	}

}
