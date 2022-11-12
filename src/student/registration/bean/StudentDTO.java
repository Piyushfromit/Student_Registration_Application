package student.registration.bean;

public class StudentDTO {

	private int roll;
	private String sName;
	private int cid;
	private String cName;
	private int bid;
	private String bName;
	
	public StudentDTO() {}

	public StudentDTO(int roll, String sName, int cid, String cName, int bid, String bName) {
		super();
		this.roll = roll;
		this.sName = sName;
		this.cid = cid;
		this.cName = cName;
		this.bid = bid;
		this.bName = bName;
	}

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	@Override
	public String toString() {
		return "Student ID : "+this.roll+"\nStudent Name : "+this.sName+"\nCourse ID : "+this.cid+"\nCourse Name : "+this.cName+"\nBatch ID : "+this.bid+"\nBatch Name : "+this.bName ;
	}
	
	
	
	
}
