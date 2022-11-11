package student.registration.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	@Override
	public Admin login(String username, String password) throws AdminException {
		// TODO Auto-generated method stub
		
		Admin admin = null;
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps =  conn.prepareStatement("SELECT * FROM admin WHERE ausername = ? AND apassword = ?");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				int id = rs.getInt("adminid");
				String name = rs.getString("aname");
				String user = rs.getString("ausername");
				String pass = rs.getString("apassword");
				
				admin = new Admin(id, name, user, pass);

			}
			else {
				throw new AdminException("Authentication Error ! ");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdminException(e.getMessage());
		}
		
		return admin;
	}
	

}
