package student.registration.main;

import java.util.Scanner;

import student.registration.usecase.StudentMenu;



public class Main {
	
	static Scanner sc = new Scanner(System.in);
	
	
	public static void studentAuth() {
		
		System.out.println("PLEASE SELECT FROM THE FOLLOWING OPTIONS ");
		System.out.println("1. Student Login");
		System.out.println("2. New Student Registration");
		System.out.println("3. Show All Courses and Seats Available");
		System.out.println("\n\n");
		System.out.println("0. Go Back");
		System.out.println("99. Exit The Application");
		
		int option = sc.nextInt();
		StudentMenu student = new StudentMenu();
		
		switch(option){
		case 0 : menu();
			break;
		case 2 : student.regStudent();
		System.out.println("----------------------------------------");
				studentAuth();
			break;
//		case 1 : int check = student.login();
//		System.out.println("----------------------------------------");
//			if(check != 0) studentActivity(check);
//			else studentAuth();
//			break;
//		case 3 : student.showAllCourse();
//		System.out.println("----------------------------------------");
//			studentAuth();
//			break;
		case 99 :
			System.out.println("Thank you for using Application");
			break;
		default : 
			System.out.println("Invalid Selection ");
			System.out.println("----------------------------------------");
			studentAuth();
	}
	}

	
// *******************************************************************************
	
	public static void menu() {
		
		System.out.println("Please Select from the Option");
		System.out.println("1. I am a Student ");
		System.out.println("2. I am a Admin ");
		System.out.println("\n\n");
		System.out.println("99. To Exit From Application");
		
		int opt1 = sc.nextInt();
		
		switch (opt1) {
		case 1 :
			System.out.println("----------------------------------------");
		    studentAuth(); 
		case 2 :
			System.out.println("----------------------------------------");
			//adminAuth(); 
		case 99:
			System.out.println("Thank you for using Application");
			break;
		default:
			System.out.println("Invalid Selection !");
			//adminActivity();
		}
			
		
	}
	
	
	
// ********************************************************************************
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("          Student Management System");
		System.out.println("          *************************");
		
		menu();
	}

}
