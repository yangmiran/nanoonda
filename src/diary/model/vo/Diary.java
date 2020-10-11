package diary.model.vo;

import java.sql.Date;

public class Diary implements java.io.Serializable {
	private static final long serialVersionUID = 3000L;
	
	private int diaryNo;
	private String diaryTitle;
	private String diaryContent;
	private String diaryWriter;
	private Date diaryDate;
	private String diaryOriginfile;
	private String diaryRenamefile;
	private int readCount;
	private String tempOk;
	private String openOk;
	
	public Diary() {}

	public Diary(int diaryNo, String diaryTitle, String diaryContent, String diaryWriter, Date diaryDate,
			String diaryOriginfile, String diaryRenamefile, int readCount, String tempOk, String openOk) {
		super();
		this.diaryNo = diaryNo;
		this.diaryTitle = diaryTitle;
		this.diaryContent = diaryContent;
		this.diaryWriter = diaryWriter;
		this.diaryDate = diaryDate;
		this.diaryOriginfile = diaryOriginfile;
		this.diaryRenamefile = diaryRenamefile;
		this.readCount = readCount;
		this.tempOk = tempOk;
		this.openOk = openOk;
	}

	public int getDiaryNo() {
		return diaryNo;
	}

	public void setDiaryNo(int diaryNo) {
		this.diaryNo = diaryNo;
	}

	public String getDiaryTitle() {
		return diaryTitle;
	}

	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}

	public String getDiaryContent() {
		return diaryContent;
	}

	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}

	public String getDiaryWriter() {
		return diaryWriter;
	}

	public void setDiaryWriter(String diaryWriter) {
		this.diaryWriter = diaryWriter;
	}

	public Date getDiaryDate() {
		return diaryDate;
	}

	public void setDiaryDate(Date diaryDate) {
		this.diaryDate = diaryDate;
	}

	public String getDiaryOriginfile() {
		return diaryOriginfile;
	}

	public void setDiaryOriginfile(String diaryOriginfile) {
		this.diaryOriginfile = diaryOriginfile;
	}

	public String getDiaryRenamefile() {
		return diaryRenamefile;
	}

	public void setDiaryRenamefile(String diaryRenamefile) {
		this.diaryRenamefile = diaryRenamefile;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getTempOk() {
		return tempOk;
	}

	public void setTempOk(String tempOk) {
		this.tempOk = tempOk;
	}

	public String getOpenOk() {
		return openOk;
	}

	public void setOpenOk(String openOk) {
		this.openOk = openOk;
	}

	@Override
	public String toString() {
		return "Diary [diaryNo=" + diaryNo + ", diaryTitle=" + diaryTitle + ", diaryContent=" + diaryContent
				+ ", diaryWriter=" + diaryWriter + ", diaryDate=" + diaryDate + ", diaryOriginfile=" + diaryOriginfile
				+ ", diaryRenamefile=" + diaryRenamefile + ", readCount=" + readCount + ", tempOk=" + tempOk
				+ ", openOk=" + openOk + "]";
	}
	
	
	
}	
	