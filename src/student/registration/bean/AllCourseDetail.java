package student.registration.bean;

public class AllCourseDetail {
	
	private int cid;
	private String cname;
	private int fee;
	private int seats;
	
	public AllCourseDetail() {}

	public AllCourseDetail(int cid, String cname, int fee, int seats) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.fee = fee;
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "AllCourseDetail [cid=" + cid + ", cname=" + cname + ", fee=" + fee + ", seats=" + seats + "]";
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
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

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}



}
