package com.itemInquiry;

public class ReplyDTO {
	private String content;
	private String reg_date;
	private long inquiryNo;
	
	
	
	public long getInquiryNo() {
		return inquiryNo;
	}
	public void setInquiryNo(long inquiryNo) {
		this.inquiryNo = inquiryNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
