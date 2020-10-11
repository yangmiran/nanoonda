package reply.model.vo;

import java.sql.Date;

public class Reply implements java.io.Serializable {
	private static final long serialVersionUID = 3001L;
	
	private int replyNo;
	private int diaryNo;
	private String replyContent;
	private String replyWriter;
	private Date replyDate;
	private int likeCount;
	private int hateCount;
	private String alarmOk;
	
	public Reply() {}

	public Reply(int replyNo, int diaryNo, String replyContent, String replyWriter, Date replyDate, int likeCount,
			int hateCount, String alarmOk) {
		super();
		this.replyNo = replyNo;
		this.diaryNo = diaryNo;
		this.replyContent = replyContent;
		this.replyWriter = replyWriter;
		this.replyDate = replyDate;
		this.likeCount = likeCount;
		this.hateCount = hateCount;
		this.alarmOk = alarmOk;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public int getDiaryNo() {
		return diaryNo;
	}

	public void setDiaryNo(int diaryNo) {
		this.diaryNo = diaryNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getHateCount() {
		return hateCount;
	}

	public void setHateCount(int hateCount) {
		this.hateCount = hateCount;
	}

	public String getAlarmOk() {
		return alarmOk;
	}

	public void setAlarmOk(String alarmOk) {
		this.alarmOk = alarmOk;
	}

	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", diaryNo=" + diaryNo + ", replyContent=" + replyContent
				+ ", replyWriter=" + replyWriter + ", replyDate=" + replyDate + ", likeCount=" + likeCount
				+ ", hateCount=" + hateCount + ", alarmOk=" + alarmOk + "]";
	}

	
}
