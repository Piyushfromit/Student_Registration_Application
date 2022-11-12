package student.registration.dao;



import java.util.List;


import student.registration.bean.Admin;
import student.registration.bean.Batch;
import student.registration.bean.Course;
import student.registration.bean.CourseDTO;
import student.registration.exception.AdminException;

public interface AdminDao {
	
	String adminRegistration(Admin admin) throws AdminException;

	Admin login(String username, String password) throws AdminException;

	String addCourse(Course course) throws AdminException;

	String updateFee(int cid, int newFee) throws AdminException;
	
	String deleteCourse(int cid) throws AdminException;
	
	String addBatchToCourse(Batch batch) throws AdminException;
	
	List<CourseDTO> searchCourse(int cid) throws AdminException;
}
