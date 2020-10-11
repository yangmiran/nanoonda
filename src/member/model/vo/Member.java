package member.model.vo;

import java.sql.Date;

public class Member implements java.io.Serializable {
	private static final long serialVersionUID = 5111L;
	
	private String id;
	private String pwd;
	private String email;
	private String emailauth;
	private String nName;
	private java.sql.Date enrollDate;
	private java.sql.Date lastModified;
	private int dcCount;
	private String loginLimit;
	private String status;
	private String resetpass;
	
	public Member() {}


	public Member(String id, String pwd, String email, String emailauth, String nName, Date enrollDate,
			Date lastModified, int dcCount, String loginLimit, String status, String resetpass) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.emailauth = emailauth;
		this.nName = nName;
		this.enrollDate = enrollDate;
		this.lastModified = lastModified;
		this.dcCount = dcCount;
		this.loginLimit = loginLimit;
		this.status = status;
		this.resetpass = resetpass;
	}
	
	public String getId() {
		return id;
	}

	public String getPwd() {
		return pwd;
	}

	public String getEmail() {
		return email;
	}

	public String getEmailauth() {
		return emailauth;
	}

	public String getnName() {
		return nName;
	}

	public java.sql.Date getEnrollDate() {
		return enrollDate;
	}

	public java.sql.Date getLastModified() {
		return lastModified;
	}

	public int getDcCount() {
		return dcCount;
	}

	public String getLoginLimit() {
		return loginLimit;
	}

	public String getStatus() {
		return status;
	}

	public String getResetpass() {
		return resetpass;
	}


	public void setId(String id) {
		this.id = id;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmailauth(String emailauth) {
		this.emailauth = emailauth;
	}

	public void setnName(String nName) {
		this.nName = nName;
	}

	public void setEnrollDate(java.sql.Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public void setLastModified(java.sql.Date lastModified) {
		this.lastModified = lastModified;
	}

	public void setDcCount(int dcCount) {
		this.dcCount = dcCount;
	}

	public void setLoginLimit(String loginLimit) {
		this.loginLimit = loginLimit;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setResetpass(String resetpass) {
		this.resetpass = resetpass;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", pwd=" + pwd + ", email=" + email + ", emailauth=" + emailauth + ", nName="
				+ nName + ", enrollDate=" + enrollDate + ", lastModified=" + lastModified + ", dcCount=" + dcCount
				+ ", loginLimit=" + loginLimit + ", status=" + status + ", resetpass=" + resetpass + "]";
	}
}

	
	