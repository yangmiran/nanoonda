package pro.model.vo;

import java.sql.Date;

public class Pro implements java.io.Serializable{
	private static final long serialVersionUid = 2111L;
	
	private int proNo;
	private String proContent;
	private String adminNname;
	private java.sql.Date proDate;
	
	
	public Pro () {}


	public Pro(int proNo, String proContent, String adminNname, Date proDate) {
		super();
		this.proNo = proNo;
		this.proContent = proContent;
		this.adminNname = adminNname;
		this.proDate = proDate;
	}


	public void setProNo(int proNo) {
		this.proNo = proNo;
	}


	public void setProContent(String proContent) {
		this.proContent = proContent;
	}


	public void setAdminNname(String adminNname) {
		this.adminNname = adminNname;
	}


	public void setProDate(java.sql.Date proDate) {
		this.proDate = proDate;
	}


	public int getProNo() {
		return proNo;
	}


	public String getProContent() {
		return proContent;
	}


	public String getAdminNname() {
		return adminNname;
	}


	public java.sql.Date getProDate() {
		return proDate;
	}
	
	
	
}
