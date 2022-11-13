package student.registration.bean;

public class CourseDTO {
	
	private int cid;
	private String cname;
	private String bname;
	private int fee;
	private int seats;
	
	public CourseDTO(){}
	
	

	public CourseDTO(int cid, String cname, String bname, int fee, int seats) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.fee = fee;
		this.seats = seats;
		this.bname = bname;
	}



	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	@Override
	public String toString() {
		return "Course ID : "+this.cid +"\nCourse Name : "+this.cname+"\nBatch Name : "+this.bname+"\nBatch Fee : "+this.fee+"\nBatch Seats : "+this.seats;
	}
	
	

}
