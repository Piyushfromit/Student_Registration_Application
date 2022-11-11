package student.registration.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import student.registration.bean.Student;
import student.registration.exception.StudentException;
import student.registration.util.DBUtil;

public class StudentDaoImpl implements StudentDao {

	@Override
	public String studentRegistration(Student student) throws StudentException {
		// TODO Auto-generated method stub
		
		String massage = null;
		
		
		
		try (Connection con = DBUtil.establishConnection();){
			
			PreparedStatement ps1 =  con.prepareStatement("INSERT INTO student(name,gender,email,password) VALUES (?,?,?,?)");
		
			ps1.setString(1, student.getName());
			ps1.setString(2, student.getGender());
			ps1.setString(3, student.getEmail());
			ps1.setString(4, student.getPassword());
		
			int x=ps1.executeUpdate();
		
			if(x >0)
				massage = "Student Registered Sucessfully..";
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new StudentException();
		}
		
		return massage;
	}

	
	
	
	@Override
	public Student login(String username, String password) throws StudentException {
		// TODO Auto-generated method stub
		
		Student student = null;
		
		
		
		try (Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps =  conn.prepareStatement("SELECT * FROM student WHERE email = ? AND password = ?");
		    
			ps.setString(1,username);
			ps.setString(2,password);
		
			ResultSet rs = ps.executeQuery();
		
			if(rs.next()) {
				int roll = rs.getInt("roll");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				String pass = rs.getString("password");
		
				student = new Student(roll,name,gender,email,pass);
				
			}else {
				throw new StudentException("Student Not Found");
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return student;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
