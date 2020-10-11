package statistics.model.vo;

import java.sql.Date;

public class Visitor {

	public Visitor() {
	}

	private String id; // 아이디
	private String pwd; // 비밀번호
	private java.sql.Date accessDate; // 접속일

	public Visitor(String id, String pwd, Date accessDate) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.accessDate = accessDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public java.sql.Date getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(java.sql.Date accessDate) {
		this.accessDate = accessDate;
	}

	@Override
	public String toString() {
		return "visitor [id=" + id + ", pwd=" + pwd + ", accessDate=" + accessDate + "]";
	}

}
