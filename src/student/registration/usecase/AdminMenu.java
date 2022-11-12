package student.registration.usecase;

import java.util.List;
import java.util.Scanner;


import student.registration.bean.Admin;
import student.registration.bean.Batch;
import student.registration.bean.Course;
import student.registration.bean.CourseDTO;
import student.registration.dao.AdminDao;
import student.registration.dao.AdminDaoImpl;
import student.registration.exception.AdminException;



public class AdminMenu {
	
	public void regAdmin() {
		
        Scanner sc = new Scanner(System.in);
		
        System.out.println("Enter Admin Details");
		System.out.println("-------------------");
		
		System.out.println("Enter name : ");
		String name = sc.nextLine();
		
		System.out.println("Enter username : ");
		String user = sc.next();
		
		System.out.println("Enter Password : ");
		String pass = sc.next();
		
		Admin admin = new Admin();
		
		admin.setAname(name);
		admin.setUsername(user);
		admin.setPassword(pass);
		
		AdminDao ad = new AdminDaoImpl();
		
		try {
			System.out.println(ad.adminRegistration(admin));
		}catch (AdminException ae) {

			System.out.println(ae.getMessage());
			
		}
		
	}
	
	
	public int login() {
		int check = 0;
		
        Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Username : ");
		String userName = sc.next();
		
		System.out.println("Enter Password : ");
		String password = sc.next();
		
		AdminDao ad = new AdminDaoImpl();
		
		
        try {
			
			Admin admin = ad.login(userName, password);
			
			System.out.println(admin);
			
			check = 1;
			
		}catch(AdminException e){
			
			System.out.println(e.getMessage());
		}
		
		return check;
	}
	
	
	public void addCourse() {
     
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Course Details ");
		System.out.println("---------------------");
		
		System.out.println("Course Name : ");
		String name = sc.nextLine();
		
		System.out.println("Course Fee : ");
		
		String f;
		int fee = 0;
		try {
			f = sc.next();
			fee = Integer.parseInt(f);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Try Again !");
			addCourse();
		}
		
        Course course = new Course();
		course.setCname(name);
		course.setFee(fee);
		
		
		AdminDao ad = new AdminDaoImpl();
		
		try {
			System.out.println(ad.addCourse(course));
		} catch (AdminException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	public void updateFee() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Course ID : ");
		String c;
		int cid = 0;
		
		try {
			c = sc.next();
			cid = Integer.parseInt(c);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Try Again !");
			updateFee();
		}
		
		System.out.println("Enter New Fees : ");
		String f;
		int fee = 0;
		try {
			f = sc.next();
			fee = Integer.parseInt(f);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Try Again !");
			updateFee();
		}
		
		AdminDao ad = new AdminDaoImpl();
		
		try {
			System.out.println(ad.updateFee(cid, fee));
		}catch(AdminException ae){
			System.out.println(ae.getMessage());
		}
	}
	
	
	public void deleteCourse() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Course ID : ");
		String c;
		int cid = 0;
		
		try {
			c = sc.next();
			cid = Integer.parseInt(c);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Try Again !");
			deleteCourse();
		}
		
		AdminDao ad = new AdminDaoImpl();
		
		try {
			
			System.out.println(ad.deleteCourse(cid));
			
		}catch(AdminException ae) {
			System.out.println(ae.getMessage());
		}
	}
	
	
	
	
	
	
	
public void addBatchToCourse() {

		
		Scanner sc = new Scanner(System.in);

		System.out.println("          New Batch Details ");
		System.out.println("      --------------------------");
		
		System.out.println("Enter the Course ID of the batch : ");
		String c;
		int courseid = 0;
		
		try {
			c = sc.next();
			courseid = Integer.parseInt(c);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Try Again !");
			addBatchToCourse();
		}
		
		System.out.println("Enter the Batch ID (Must be Unique) : ");
		String b;
		int batchid = 0;
		
		try {
			b = sc.next();
			batchid = Integer.parseInt(b);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Try Again !");
			addBatchToCourse();
		}
		
		System.out.println("Enter the batch name : ");
		String batchname = sc.next();
		
		
		System.out.println("Enter the number of seats in this batch : ");
		int seats = sc.nextInt();
		
		
		Batch batch = new Batch(batchid, batchname, courseid, seats);
		
		AdminDao ad = new AdminDaoImpl();
		
		try {
			System.out.println(ad.addBatchToCourse(batch));
		}catch(AdminException ae) {
			System.out.println(ae.getMessage());
		}
		
	}
	
	


   
public void searchCourse() {
	
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Enter the course ID : ");
	String c;
	int cid = 0;
	
	try {
		c = sc.next();
		cid = Integer.parseInt(c);
		
	}catch(Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Try Again !");
		searchCourse();
	}
	
	AdminDao ad = new AdminDaoImpl();
	
	try {
		
		List<CourseDTO> courses = ad.searchCourse(cid);
		
		for(CourseDTO cd: courses) {
			
			System.out.println(cd);
			
			System.out.println("----------------------------------------");
			
		}
		
		if(courses.size() == 0) System.out.println("No Data to Show. ");
		
	}catch(AdminException e) {
		
		System.out.println(e.getMessage());
		
	}
	
	
}

public void addStudentToBatch() {
	
	Scanner sc = new Scanner(System.in);
	
	System.out.println("       Add Student To Batch ");
	System.out.println("      ----------------------");
	
	System.out.println("Enter the Student Roll Number : ");
	String r;
	int roll = 0;
	
	try {
		r = sc.next();
		roll = Integer.parseInt(r);
		
	}catch(Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Try Again !");
		addStudentToBatch();
	}
	
	System.out.println("Enter Course ID : ");
	String c;
	int cid = 0;
	
	try {
		c = sc.next();
		cid = Integer.parseInt(c);
		
	}catch(Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Try Again !");
		addStudentToBatch();
	}
	
	System.out.println("Enter Batch ID : ");
	String b;
	int bid = 0;
	
	try {
		b = sc.next();
		bid = Integer.parseInt(b);
		
	}catch(Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Try Again !");
		addStudentToBatch();
	}
	
	AdminDao ad = new AdminDaoImpl();
	
	try {
		
		System.out.println(ad.addStudentToBatch(roll, bid, cid));
		
	} catch (AdminException e) {
		// TODO: handle exception
		System.out.println(e.getMessage());
	} 
}


    

  public void updateSeats() {
	
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Enter Batch ID : ");
	String b;
	int batchid = 0;
	
	try {
		b = sc.next();
		batchid = Integer.parseInt(b);
		
	}catch(Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Try Again !");
		updateSeats();
	}
	
	
	System.out.println("Enter New Number of Seats : ");
	String ns;
	int newSeat = 0;
	
	try {
		ns = sc.next();
		newSeat = Integer.parseInt(ns);
		
	}catch(Exception e) {
		System.out.println(e.getMessage());
		System.out.println("Try Again !");
		updateSeats();
		
	}
	
	AdminDao ad = new AdminDaoImpl();
	
	try {
		
		System.out.println(ad.updateSeatsOfBatch(batchid, newSeat));
		
	}catch(AdminException ae) {
		
		System.out.println(ae.getMessage());
		
	}
	
	

}





	

}
