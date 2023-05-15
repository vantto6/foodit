package com.admin;

public class AdminDTO {
	private long itemNo;
	private String itemName;
	private long price;
	private long discount;
	private int cnt;
	private String saleUnit;
	private int hitCount;
	private String description;
	private String createDate; // 상품등록날짜
	private String updateDate; // 상품수정날짜
	private String deadline;
	private int categoryNo;
	private String categoryName;
	private int brandNo;
	private String brandName;
	private long basketNo;
	private int basketCnt;
	private String memberId;
	private long questionNo;
	private String subject;
	private String content;
	private String rqcreateDate; // 문의 등록날짜
	private String rqupdateDate; // 문의 수정날짜
	private String imgcreateDate;
	private String imgupdateDate;
    private long imageNo;
	private String saveFilename;
	private String[] saveFiles;
	private long fileSize;
	private int thumbnail;
	public long getImageNo() {
		return imageNo;
	}
	public void setImageNo(long imageNo) {
		this.imageNo = imageNo;
	}
	public String getSaveFilename() {
		return saveFilename;
	}
	public void setSaveFilename(String saveFilename) {
		this.saveFilename = saveFilename;
	}
	public String[] getSaveFiles() {
		return saveFiles;
	}
	public void setSaveFiles(String[] saveFiles) {
		this.saveFiles = saveFiles;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public long getItemNo() {
		return itemNo;
	}
	public void setItemNo(long itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getDiscount() {
		return discount;
	}
	public void setDiscount(long discount) {
		this.discount = discount;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getSaleUnit() {
		return saleUnit;
	}
	public void setSaleUnit(String saleUnit) {
		this.saleUnit = saleUnit;
	}
	public int getHitCount() {
		return hitCount;
	}
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getBrandNo() {
		return brandNo;
	}
	public void setBrandNo(int brandNo) {
		this.brandNo = brandNo;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public long getBasketNo() {
		return basketNo;
	}
	public void setBasketNo(long basketNo) {
		this.basketNo = basketNo;
	}
	public int getBasketCnt() {
		return basketCnt;
	}
	public void setBasketCnt(int basketCnt) {
		this.basketCnt = basketCnt;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public long getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(long questionNo) {
		this.questionNo = questionNo;
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
	public String getRqcreateDate() {
		return rqcreateDate;
	}
	public void setRqcreateDate(String rqcreateDate) {
		this.rqcreateDate = rqcreateDate;
	}
	public String getRqupdateDate() {
		return rqupdateDate;
	}
	public void setRqupdateDate(String rqupdateDate) {
		this.rqupdateDate = rqupdateDate;
	}
	public String getImgcreateDate() {
		return imgcreateDate;
	}
	public void setImgcreateDate(String imgcreateDate) {
		this.imgcreateDate = imgcreateDate;
	}
	public String getImgupdateDate() {
		return imgupdateDate;
	}
	public void setImgupdateDate(String imgupdateDate) {
		this.imgupdateDate = imgupdateDate;
	}
	public int getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(int thumbnail) {
		this.thumbnail = thumbnail;
	}

}
