package qna.model.vo;

import java.sql.Date;

public class Qna implements java.io.Serializable {
	public static final long serialVersionUID = 4222L;

	private int qnaNo; // 글번호
	private String qnaTitle; // 글제목
	private String qnaContent; // 글내용
	private java.sql.Date qnaDate; // 등록일
	private String qnaOriginalFilepath; // 첨부파일
	private String qnaRenameFilepath; // 첨부파일
	private String nname; // 작성자
	private String qnaAlarm; // 알람

	

	public Qna() {}


	public Qna(int qnaNo, String qnaTitle, String qnaContent, Date qnaDate, String qnaOriginalFilepath,
			String qnaRenameFilepath, String nname, String qnaAlarm, String qnaLevel) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.qnaDate = qnaDate;
		this.qnaOriginalFilepath = qnaOriginalFilepath;
		this.qnaRenameFilepath = qnaRenameFilepath;
		this.nname = nname;
		this.qnaAlarm = qnaAlarm;
	
	}


	public int getQnaNo() {
		return qnaNo;
	}


	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}


	public String getQnaTitle() {
		return qnaTitle;
	}


	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}


	public String getQnaContent() {
		return qnaContent;
	}


	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}


	public java.sql.Date getQnaDate() {
		return qnaDate;
	}


	public void setQnaDate(java.sql.Date qnaDate) {
		this.qnaDate = qnaDate;
	}


	public String getQnaOriginalFilepath() {
		return qnaOriginalFilepath;
	}


	public void setQnaOriginalFilepath(String qnaOriginalFilepath) {
		this.qnaOriginalFilepath = qnaOriginalFilepath;
	}


	public String getQnaRenameFilepath() {
		return qnaRenameFilepath;
	}


	public void setQnaRenameFilepath(String qnaRenameFilepath) {
		this.qnaRenameFilepath = qnaRenameFilepath;
	}


	public String getNname() {
		return nname;
	}


	public void setNname(String nname) {
		this.nname = nname;
	}


	public String getQnaAlarm() {
		return qnaAlarm;
	}

	public void setQnaAlarm(String qnaAlarm) {
		this.qnaAlarm = qnaAlarm;
	}




	@Override
	public String toString() {
		return "Qna [qnaNo=" + qnaNo + ", qnaTitle=" + qnaTitle + ", qnaContent=" + qnaContent + ", qnaDate=" + qnaDate
				+ ", qnaOriginalFilepath=" + qnaOriginalFilepath + ", qnaRenameFilepath=" + qnaRenameFilepath
				+ ", nname=" + nname + ", qnaAlarm=" + qnaAlarm + "]";
	}


}