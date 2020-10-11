package report.model.vo;

import java.sql.Date;

public class Report implements java.io.Serializable{
		private static final long serialVersionUid = 2222L;
		
		private int reportNo;
		private String reportStatus;
		private String reportTiltle;
		private String reportContent;
		private String senderNname;
		private String receiverNname;
		private Date reportRegDate;
		private Date reportComDate;
		private String reportType;
		private int diaryNo;
		private int postNo;
		
		public Report() {}

		public Report(int reportNo, String reportStatus, String reportTiltle, String reportContent, String senderNname,
				String receiverNname, Date reportRegDate, Date reportComDate, String reportType, int diaryNo, int postNo) {
			super();
			this.reportNo = reportNo;
			this.reportStatus = reportStatus;
			this.reportTiltle = reportTiltle;
			this.reportContent = reportContent;
			this.senderNname = senderNname;
			this.receiverNname = receiverNname;
			this.reportRegDate = reportRegDate;
			this.reportComDate = reportComDate;
			this.reportType = reportType;
			this.diaryNo = diaryNo;
			this.postNo = postNo;
		}

		public void setReportNo(int reportNo) {
			this.reportNo = reportNo;
		}

		public void setReportStatus(String reportStatus) {
			this.reportStatus = reportStatus;
		}

		public void setReportTiltle(String reportTiltle) {
			this.reportTiltle = reportTiltle;
		}

		public void setReportContent(String reportContent) {
			this.reportContent = reportContent;
		}

		public void setsenderNname(String senderNname) {
			this.senderNname = senderNname;
		}

		public void setreceiverNname(String receiverNname) {
			this.receiverNname = receiverNname;
		}

		public void setReportRegDate(Date reportRegDate) {
			this.reportRegDate = reportRegDate;
		}

		public void setReportComDate(Date reportComDate) {
			this.reportComDate = reportComDate;
		}

		public void setReportType(String reportType) {
			this.reportType = reportType;
		}

		public void setDiaryNo(int diaryNo) {
			this.diaryNo = diaryNo;
		}

		public void setPostNo(int postNo) {
			this.postNo = postNo;
		}

		public int getReportNo() {
			return reportNo;
		}

		public String getReportStatus() {
			return reportStatus;
		}

		public String getReportTiltle() {
			return reportTiltle;
		}

		public String getReportContent() {
			return reportContent;
		}

		public String getsenderNname() {
			return senderNname;
		}

		public String getreceiverNname() {
			return receiverNname;
		}

		public Date getReportRegDate() {
			return reportRegDate;
		}

		public Date getReportComDate() {
			return reportComDate;
		}

		public String getReportType() {
			return reportType;
		}

		public int getDiaryNo() {
			return diaryNo;
		}

		public int getPostNo() {
			return postNo;
		}

		@Override
		public String toString() {
			return "Report [reportNo=" + reportNo + ", reportStatus=" + reportStatus + ", reportTiltle=" + reportTiltle
					+ ", reportContent=" + reportContent + ", senderNname=" + senderNname + ", receiverNname=" + receiverNname
					+ ", reportRegDate=" + reportRegDate + ", reportComDate=" + reportComDate + ", reportType="
					+ reportType + ", diaryNo=" + diaryNo + ", postNo=" + postNo + "]";
		}
		
		

}
