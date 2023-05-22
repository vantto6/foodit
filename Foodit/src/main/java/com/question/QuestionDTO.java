package com.question;

public class QuestionDTO {

	private long questionNo;
	private String category;

	private String subject;
	private String content;
	
	
	public long getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(long questionNo) {
		this.questionNo = questionNo;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "QuestionDTO [questionNo=" + questionNo + ", subject=" + subject + ", content=" + content + "]";
	}
	
	
	
}
