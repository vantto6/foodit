package com.notice;

public class NoticeDTO {
	private long noticeNo;
	private String content;
	private String subject;
	private String createDate;
	private String updateDate;

	public long getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(long noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "NoticeDTO [noticeNo=" + noticeNo + ", content=" + content + ", subject="
				+ subject + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
	
	

	
}
