package qnareply.model.vo;

import java.sql.Date;

public class QnaReply {

	public QnaReply() {}
	private int qnaNo;  //원글번호
	private String replyTitle; // 답글제목
	private String replyContent; //답글내용
	private java.sql.Date replyDate; //답글 등록일
	private String nname; // 작성자
	
	public QnaReply(int qnaNo, String replyTitle, String replyContent, Date replyDate, String nname) {
		super();
		this.qnaNo = qnaNo;
		this.replyTitle = replyTitle;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
		this.nname = nname;
	}
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getReplyTitle() {
		return replyTitle;
	}
	public void setReplyTitle(String replyTitle) {
		this.replyTitle = replyTitle;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public java.sql.Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(java.sql.Date replyDate) {
		this.replyDate = replyDate;
	}
	public String getNname() {
		return nname;
	}
	public void setNname(String nname) {
		this.nname = nname;
	}
	@Override
	public String toString() {
		return "QnaReply [qnaNo=" + qnaNo + ", replyTitle=" + replyTitle + ", replyContent=" + replyContent
				+ ", replyDate=" + replyDate + ", nname=" + nname + "]";
	}
	

	
}