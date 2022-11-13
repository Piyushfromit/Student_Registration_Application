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
import student.registration.bean.Student;
import student.registration.bean.StudentDTO;
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
			PreparedStatement ps =  conn.prepareStatement("INSERT INTO course(cname,fee) VALUES (?,?)");
		
			ps.setString(1, course.getCname());
			ps.setInt(2, course.getFee());
			
	
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

	
	
	
	@Override
	public String addStudentToBatch(int roll, int bid, int cid) throws AdminException {
		// TODO Auto-generated method stub
		String message = null;
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM student WHERE roll = ?");
			ps.setInt(1, roll);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
				String studentName = rs.getString("name");
				PreparedStatement ps2 =  conn.prepareStatement("SELECT * FROM course WHERE cid = ?");
				ps2.setInt(1, cid);
				
				ResultSet rs2 = ps2.executeQuery();
				
				if(rs2.next()) {
					
					String courseName = rs2.getString("cname");
					
					PreparedStatement ps3 = conn.prepareStatement("SELECT batchname,seats FROM batch WHERE batchid = ? AND courseid = ?");
					
					ps3.setInt(1, bid);
					ps3.setInt(2, cid);
					
					ResultSet rs3 = ps3.executeQuery();
					
					if(rs3.next()) {
						
						String batchName = rs3.getString("batchname");
						int batchSeats = rs3.getInt("seats");
						
						if(batchSeats > 0) {
							
							batchSeats--;
							PreparedStatement up = conn.prepareStatement("UPDATE batch SET seats = ? WHERE batchid = ?");
							up.setInt(1, batchSeats);
							up.setInt(2, bid);
							
							int r = up.executeUpdate();
							
							PreparedStatement p = conn.prepareStatement("INSERT INTO batchofstudent VALUES (?,?,?)");
							p.setInt(1, roll);
							p.setInt(2, cid);
							p.setInt(3, bid);
							
							int res = p.executeUpdate();
							
							if(res > 0) {
								
								message = "Student "+studentName+" Added to Batch "+ batchName+" of Course "+courseName+" Successfully.";
							
							}
							else {
								throw new AdminException("Batch and Course Not Matching.");
							}
							
							
						}
						else {
							throw new AdminException("No Seats Available ! Add More Seats to Add more Student.");
						}
					}else {
						throw new AdminException("Batch with Batch ID "+bid+" not Found !");
					}

				}else {
					throw new AdminException("Course with course ID "+ cid + " not Found !");
				}
				
			}else {
				throw new AdminException("Student with Roll number "+ roll+ " not Found !");
			}
			
		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AdminException(e.getMessage());
		}
		
		return message;
	}

	
	
	
	@Override
	public String updateSeatsOfBatch(int batchid, int newSeats) throws AdminException {

	String message = null;
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps =  conn.prepareStatement("UPDATE batch SET seats = ? WHERE batchid = ?");
			ps.setInt(1, newSeats);
			ps.setInt(2, batchid);
			
			int res = ps.executeUpdate();
			
			if(res>0) message = "Batch ID : "+batchid+" is Updated with New Number of Seats : "+ newSeats+" Successfully.";
			else throw new AdminException("Batch ID Error.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdminException(e.getMessage());
		}
		
		return message;
		
	}

	
	
	@Override
	public List<StudentDTO> showAllStudentWithBatch() throws AdminException {
		// TODO Auto-generated method stub
		List<StudentDTO> students = new ArrayList<>();
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps = conn.prepareStatement("SELECT s.roll,s.name,c.cid,c.cname,b.batchid,b.batchname "
					+ "FROM student s INNER JOIN batch b INNER JOIN course c INNER JOIN "
					+ "batchofstudent bs ON c.cid = bs.courseid AND b.batchid = bs.batchid");
			
			ResultSet rs = ps.executeQuery();
			
			boolean flag = true;
			
			while(rs.next()) {
				
				int roll = rs.getInt("roll");
				String sName = rs.getString("name");
				int cid = rs.getInt("cid");
				String cName = rs.getString("cname");
				int bid = rs.getInt("batchid");
				String bName = rs.getString("batchname");
				flag = false;
				
				
				StudentDTO student = new StudentDTO(roll, sName, cid, cName, bid, bName);
				students.add(student);
				
			}
			
			if(flag) throw new AdminException("No student added to Batch");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdminException(e.getMessage());
			
		}
		return students;
	}

	@Override
	public List<Student> studentList() throws AdminException {
		// TODO Auto-generated method stub
       List<Student> students = new ArrayList<>();
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps =  conn.prepareStatement("SELECT * FROM student");
		
			ResultSet rs = ps.executeQuery();
			
			boolean flag = true;
			
			while(rs.next()) {
				
				flag = false;
				int roll = rs.getInt("roll");
				String name = rs.getString("name");
				String gender= rs.getString("gender");
				String email= rs.getString("email");
				String pass= rs.getString("password");
				
				Student student = new Student(roll,name,gender,email,pass);
				
				students.add(student);
			}
			
			if(flag) throw new AdminException("No Student Data Found !");
			
			
		}catch (SQLException e) {
			// TODO: handle exception
			throw new AdminException(e.getMessage());
		}
		
		return students;
	}

	
	
	@Override
	public List<Course> courseList() throws AdminException {
		// TODO Auto-generated method stub
		List<Course> courses = new ArrayList<>();
		
		try(Connection conn = DBUtil.establishConnection()){
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM course");
			
			ResultSet rs = ps.executeQuery();
			
			boolean flag = true;
			
			while(rs.next()) {
				flag = false;
				
				int cid = rs.getInt("cid");
				String cname = rs.getString("cname");
				int fee = rs.getInt("fee");
				
				Course course = new Course(cid,cname,fee);
				
				courses.add(course);
			}
			
			if(flag) throw new AdminException("No Course Found !");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new AdminException(e.getMessage());
		}
			
		return courses;
		
	}

	
	
	
	
	
	
		
}





















