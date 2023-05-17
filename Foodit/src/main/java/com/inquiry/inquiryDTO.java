package com.inquiry;

public class inquiryDTO {

	private long inquiryNo;
	private long clientNo;
	private String content;
	private String subject;
	private String inquiryDate;
	private int isPublic;
	private String answer;
	private String answerDate;
	
	
	public long getInquiryNo() {
		return inquiryNo;
	}
	public void setInquiryNo(long inquiryNo) {
		this.inquiryNo = inquiryNo;
	}
	public long getClientNo() {
		return clientNo;
	}
	public void setClientNo(long clientNo) {
		this.clientNo = clientNo;
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
	public String getInquiryDate() {
		return inquiryDate;
	}
	public void setInquiryDate(String inquiryDate) {
		this.inquiryDate = inquiryDate;
	}
	public int getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(int isPublic) {
		this.isPublic = isPublic;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}
	
	
	
}
