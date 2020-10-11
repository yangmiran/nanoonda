package post.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Post implements Serializable {
	public static final long serialVersionUID = 1111L;
	
	private int postNo;
	private String postSender;
	private String postReceiver;
	private String postTitle;
	private String postContent;
	private String postFile;
	private String postRefile;
	private Date sendDate;
	private int readCheck;
	private String sendDelCheck;
	private String receiverDelCheck;
	private String saveCheck;

	public Post() {}

	public Post(int postNo, String postSender, String postReceiver, String postTitle, String postContent,
			String postFile, String postRefile, Date sendDate, int readCheck, String sendDelCheck,
			String receiverDelCheck, String saveCheck) {
		super();
		this.postNo = postNo;
		this.postSender = postSender;
		this.postReceiver = postReceiver;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postFile = postFile;
		this.postRefile = postRefile;
		this.sendDate = sendDate;
		this.readCheck = readCheck;
		this.sendDelCheck = sendDelCheck;
		this.receiverDelCheck = receiverDelCheck;
		this.saveCheck = saveCheck;
	}

	public int getPostNo() {
		return postNo;
	}

	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}

	public String getPostSender() {
		return postSender;
	}

	public void setPostSender(String postSender) {
		this.postSender = postSender;
	}

	public String getPostReceiver() {
		return postReceiver;
	}

	public void setPostReceiver(String postReceiver) {
		this.postReceiver = postReceiver;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostFile() {
		return postFile;
	}

	public void setPostFile(String postFile) {
		this.postFile = postFile;
	}

	public String getPostRefile() {
		return postRefile;
	}

	public void setPostRefile(String postRefile) {
		this.postRefile = postRefile;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public int getReadCheck() {
		return readCheck;
	}

	public void setReadCheck(int readCheck) {
		this.readCheck = readCheck;
	}

	public String getSendDelCheck() {
		return sendDelCheck;
	}

	public void setSendDelCheck(String sendDelCheck) {
		this.sendDelCheck = sendDelCheck;
	}

	public String getReceiverDelCheck() {
		return receiverDelCheck;
	}

	public void setReceiverDelCheck(String receiverDelCheck) {
		this.receiverDelCheck = receiverDelCheck;
	}

	public String getSaveCheck() {
		return saveCheck;
	}

	public void setSaveCheck(String saveCheck) {
		this.saveCheck = saveCheck;
	}

	@Override
	public String toString() {
		return "Post [postNo=" + postNo + ", postSender=" + postSender + ", postReceiver=" + postReceiver
				+ ", postTitle=" + postTitle + ", postContent=" + postContent + ", postFile=" + postFile
				+ ", postRefile=" + postRefile + ", sendDate=" + sendDate + ", readCheck=" + readCheck
				+ ", sendDelCheck=" + sendDelCheck + ", receiverDelCheck=" + receiverDelCheck + ", saveCheck="
				+ saveCheck + "]";
	}

	
	
}