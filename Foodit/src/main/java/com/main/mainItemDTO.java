package com.main;

public class mainItemDTO {
	private long itemNo;
	private String itemName;
	private long price;
	private long discount;
	private long discountPrice;
	private String brandName;
	private int category;
	private String saveFilename;
	
	
	
	
	public String getSaveFilename() {
		return saveFilename;
	}
	public void setSaveFilename(String saveFilename) {
		this.saveFilename = saveFilename;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
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
	public long getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice() {
		double result = price - (price * discount/100.0);
		
		this.discountPrice =  (long)Math.floor(result);
	}
}
