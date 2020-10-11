package notice.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable {
	private static final long serialVersionUID = 4111L;

	private int noticeNo; // 글번호
	private String noticeTitle; // 글제목
	private java.sql.Date noticeDate; // 등록일
	private String noticeContent; // 글내용
	private String originalFilepath; // 첨부파일
	private String renameFilepath; // 첨부파일
	private String nname; // 작성자 ('admin01', 'admin02')

	public Notice() {
	}

	public Notice(int noticeNo, String noticeTitle, Date noticeDate, String noticeContent, String originalFilepath,
			String renameFilepath, String nname) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeDate = noticeDate;
		this.noticeContent = noticeContent;
		this.originalFilepath = originalFilepath;
		this.renameFilepath = renameFilepath;
		this.nname = nname;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public java.sql.Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(java.sql.Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getOriginalFilepath() {
		return originalFilepath;
	}

	public void setOriginalFilepath(String originalFilepath) {
		this.originalFilepath = originalFilepath;
	}

	public String getRenameFilepath() {
		return renameFilepath;
	}

	public void setRenameFilepath(String renameFilepath) {
		this.renameFilepath = renameFilepath;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeDate=" + noticeDate
				+ ", noticeContent=" + noticeContent + ", originalFilepath=" + originalFilepath + ", renameFilepath="
				+ renameFilepath + ", nname=" + nname + "]";
	}
	

	}