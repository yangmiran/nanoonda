package statistics.model.vo;

import java.sql.Date;

public class Statistics {

	public Statistics() {
	}

	private String reasonquit; // 탈퇴이유
	private String enrollpath; // 가입경로
	private int visitor; // 방문자
	private java.sql.Date visitorDate; // 방문일

	public Statistics(String reasonquit, String enrollpath, int visitor, Date visitorDate) {
		super();
		this.reasonquit = reasonquit;
		this.enrollpath = enrollpath;
		this.visitor = visitor;
		this.visitorDate = visitorDate;
	}

	public String getReasonquit() {
		return reasonquit;
	}

	public void setReasonquit(String reasonquit) {
		this.reasonquit = reasonquit;
	}

	public String getEnrollpath() {
		return enrollpath;
	}

	public void setEnrollpath(String enrollpath) {
		this.enrollpath = enrollpath;
	}

	public int getVisitor() {
		return visitor;
	}

	public void setVisitor(int visitor) {
		this.visitor = visitor;
	}

	public java.sql.Date getVisitorDate() {
		return visitorDate;
	}

	public void setVisitorDate(java.sql.Date visitorDate) {
		this.visitorDate = visitorDate;
	}

	@Override
	public String toString() {
		return "Statistics [reasonquit=" + reasonquit + ", enrollpath=" + enrollpath + ", visitor=" + visitor
				+ ", visitorDate=" + visitorDate + "]";
	}

}