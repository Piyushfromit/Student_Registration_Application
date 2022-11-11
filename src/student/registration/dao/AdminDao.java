package student.registration.dao;

import student.registration.bean.Admin;
import student.registration.exception.AdminException;

public interface AdminDao {
	
	String adminRegistration(Admin admin) throws AdminException;

}
