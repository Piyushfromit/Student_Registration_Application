package student.registration.dao;

import student.registration.bean.Student;
import student.registration.exception.StudentException;

public interface StudentDao {
	
	String studentRegistration(Student student) throws StudentException;
  
	Student login(String username, String password) throws StudentException;
	
}
