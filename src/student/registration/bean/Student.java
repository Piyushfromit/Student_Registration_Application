package student.registration.bean;

public class Student {
	
	private int roll;
	private String name;
	private String gender;
	private String email;
	private String password;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(int roll, String name, String gender, String email, String password) {
		super();
		this.roll = roll;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.password = password;
	}

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Student [roll=" + roll + ", name=" + name + ", gender=" + gender + ", email=" + email + ", password="
				+ password + "]";
	}

}
