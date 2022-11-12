package student.registration.bean;

public class Batch {

	private int batchid;
	private String batchName;
	private int courseid;
	private int seats;
	
	
	public Batch() {}


	public Batch(int batchid, String batchName, int courseid, int seats) {
		super();
		this.batchid = batchid;
		this.batchName = batchName;
		this.courseid = courseid;
		this.seats = seats;
	}


	public int getBatchid() {
		return batchid;
	}


	public void setBatchid(int bid) {
		this.batchid = bid;
	}


	public String getBatchName() {
		return batchName;
	}


	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}


	public int getCourseid() {
		return courseid;
	}


	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}


	public int getSeats() {
		return seats;
	}


	public void setSeats(int seats) {
		this.seats = seats;
	}


	@Override
	public String toString() {
		return "Batch [batchid=" + batchid + ", batchName=" + batchName + ", courseid=" + courseid + ", seats=" + seats + "]";
	}

	
}
