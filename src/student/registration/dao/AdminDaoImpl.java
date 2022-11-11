package student.registration.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import student.registration.bean.Admin;
import student.registration.exception.AdminException;
import student.registration.util.DBUtil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public String adminRegistration(Admin admin) throws AdminException {
		String message = null;
		
         try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO admin(aname,ausername,apassword) VALUES (?,?,?)");
			
			ps.setString(1, admin.getAname());
			ps.setString(2, admin.getUsername());
			ps.setString(3, admin.getPassword());
			
			int res = ps.executeUpdate();
			
			if(res > 0 ) {
				message = "Admin Registration Successfull";
			}else {
				throw new AdminException("Not Registered ");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = e.getMessage();
			throw new AdminException(message);
		}
		
		return message;
	}
	

}
