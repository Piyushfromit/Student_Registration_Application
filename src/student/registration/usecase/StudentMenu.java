package student.registration.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import student.registration.bean.AllCourseDetail;
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
	
	public int login() {
		int check = 0;
		
        Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Your email (case sensitive)");
		String username = sc.next();
		
		System.out.println("Enter Your Password (case sensitive)");
		String password = sc.next();
		
		StudentDao sd = new StudentDaoImpl();
		
		try {
			Student student = sd.login(username, password);
			System.out.println("Welcome ! Your Credentials are : ");
			System.out.println("----------------------------");
			System.out.println(student);
		
			check = student.getRoll();
			
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return check;
	}
	
	
	
// ******************************************************

	
	
	public void updateDetail(int rollNumber) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Update Own Detail ");
		System.out.println("------------------ ");

		
		// Student can edit only his own data
		
//		System.out.println("Enter your roll number : ");
//		int roll = sc.nextInt();
		
	 
		int roll = rollNumber;
		
		System.out.println("Type  field name to update (name/gender/email/password): ");
		String field = sc.next();
		field = field.toLowerCase();
		
		
		sc.nextLine();
		System.out.println("Enter the new Data : ");
		String data = sc.nextLine();
		
		// student can not change Roll number
		if(field.equals("roll")) {
			System.out.println("No Access to change own Roll number");
		}else {
			
			StudentDao sd = new StudentDaoImpl();
			try {
				System.out.println( sd.updateDetails(roll, field, data));
			} catch (StudentException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	
	// ******************************************************

	
	public void showAllCourse() {
		StudentDao sd = new StudentDaoImpl();
		
		List<AllCourseDetail> courses = new ArrayList<>();
		
		try {
			courses = sd.showAllCourseDetails();
			
			if(courses.size() == 0) {
				System.out.println("No course to Show.");
			}else {
				for(AllCourseDetail c: courses) {
					System.out.println(c);
					System.out.println("------------------------");
				}
			}
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	
	
}
