package student.registration.usecase;

import java.util.Scanner;


import student.registration.bean.Student;
import student.registration.dao.StudentDao;
import student.registration.dao.StudentDaoImpl;
import student.registration.exception.StudentException;



public class StudentMenu {
	
// *****************************************************
	
	public void regStudent() {
        Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Student Details ");
		System.out.println("**********************");
		
		System.out.println("Enter Name : ");
		String name = sc.nextLine();
		
		System.out.println("Select Gender (M/F) : ");
		String gender = sc.next();
		
		System.out.println("Enter Email : ");
		String email = sc.next();
		
		System.out.println("Enter Password : ");
		String password = sc.next();
		
		
		Student student = new Student();
		
		student.setName(name);
		student.setGender(gender);
		student.setEmail(email);
		student.setPassword(password);
		
		StudentDao sd = new StudentDaoImpl();
		
		try {
			System.out.println(sd.studentRegistration(student));
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		}
	}
	
// ******************************************************
	

	
	
}
