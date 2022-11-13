package student.registration.dao;




import java.util.List;

import student.registration.bean.AllCourseDetail;
import student.registration.bean.Student;
import student.registration.exception.StudentException;

public interface StudentDao {
	
	String studentRegistration(Student student) throws StudentException;
  
	Student login(String username, String password) throws StudentException;
	
	String updateDetails(int roll, String field, String newData) throws StudentException;
	
	List<AllCourseDetail> showAllCourseDetails() throws StudentException;
	
}
