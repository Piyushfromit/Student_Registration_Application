package student.registration.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import student.registration.bean.Admin;
import student.registration.bean.Batch;
import student.registration.bean.Course;
import student.registration.bean.CourseDTO;
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

	@Override
	public String addCourse(Course course) throws AdminException {
		// TODO Auto-generated method stub
		
		String message = null;
		
	
		try (Connection conn = DBUtil.establishConnection()) {
			PreparedStatement ps =  conn.prepareStatement("INSERT INTO course(cname,fee,seats) VALUES (?,?,?)");
		
			ps.setString(1, course.getCname());
			ps.setInt(2, course.getFee());
			ps.setInt(3, course.getSeats());
	
            int res = ps.executeUpdate();
			
			if(res > 0) {
				message = "Course Added Successfully";
			}else {
				throw new AdminException("Error Adding Course Details");
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
	}
	
	
	

	@Override
	public String updateFee(int cid, int newFee) throws AdminException {
		
		String message = null;
		
        try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps = conn.prepareStatement("UPDATE course SET fee = ? WHERE cid = ?");
			
			ps.setInt(1, newFee);
			
			ps.setInt(2, cid);
			
			int res = ps.executeUpdate();
			
			if(res > 0) message = "Course ID : "+cid+" New Fees : "+newFee;
			else throw new AdminException("Error Updating Fee ! Check Course ID. ");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			throw new AdminException(e.getMessage());
			
		}
		
		return message;
	}
	
	
	

	@Override
	public String deleteCourse(int cid) throws AdminException {
		// TODO Auto-generated method stub
		String message = null;
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM course WHERE cid = ?");
			ps.setInt(1, cid);
			
			int res = ps.executeUpdate();
			
			if(res>0) message = "Course Removed Successfully whose course id is "+ cid;
			else throw new AdminException("Course ID Error! Not Found");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdminException(e.getMessage());
		}
		
		return message;
	}
	
	

	@Override
	public String addBatchToCourse(Batch batch) throws AdminException {
		// TODO Auto-generated method stub
	String message = null;
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps =  conn.prepareStatement("INSERT INTO batch VALUES (?,?,?,?)");
			
			ps.setInt(1, batch.getBatchid() );
			ps.setString(2, batch.getBatchName());
			ps.setInt(3, batch.getCourseid());
			ps.setInt(4, batch.getSeats());
			
			int res = ps.executeUpdate();
			
			if(res > 0) message = "Batch "+batch.getBatchName() + " Added to Course ID : "+ batch.getCourseid();
			
			else throw new AdminException("Batch Error ! Check Credentials Again.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdminException(e.getMessage());
		}
		return message;
	}

	@Override
	public List<CourseDTO> searchCourse(int cid) throws AdminException {
		// TODO Auto-generated method stub
		List<CourseDTO> courses = new ArrayList<>();
		
		try(Connection con = DBUtil.establishConnection()){
			
			
			PreparedStatement p = con.prepareStatement("SELECT * FROM course WHERE cid = ?");
			p.setInt(1, cid);
			
			ResultSet r = p.executeQuery();
			
			if(r.next()){
			
				PreparedStatement ps = con.prepareStatement("SELECT b.seats,b.batchname,c.cname,c.fee,c.cid "
															+ "FROM batch b INNER JOIN course c "
															+ "ON b.courseid = c.cid AND c.cid= (?)");
				ps.setInt(1, cid);
				
				ResultSet rs = ps.executeQuery();
				
				boolean flag = true;
				
				while(rs.next()) {
					
					flag = false;
					int seats = rs.getInt("b.seats");
					String bname = rs.getString("b.batchname");
					String cname = rs.getString("c.cname");
					int fee = rs.getInt("c.fee");
					int c_id = rs.getInt("c.cid");
					
					CourseDTO course = new CourseDTO(c_id,cname,bname,fee,seats);
					
					courses.add(course);
				}
				
				if(flag) throw new AdminException("No Data Found! ");
			
			}
			else {
			
				throw new AdminException("Course ID Error !");
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdminException(e.getMessage());
		}

		return courses;
	}

}





















