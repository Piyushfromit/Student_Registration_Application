package student.registration.usecase;

import java.util.Scanner;


import student.registration.bean.Admin;
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
	

}