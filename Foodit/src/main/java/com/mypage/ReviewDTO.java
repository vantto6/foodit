package com.mypage;

public class ReviewDTO {
	private int inquiryNo;
	private int itemNo;
	private String subject;
	private String createDate;
	private String itemName;
	
	public int getInquiryNo() {
		return inquiryNo;
	}
	public void setInquiryNo(int inquiryNo) {
		this.inquiryNo = inquiryNo;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	
	
}
