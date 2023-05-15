package com.basket;

public class BasketDTO {
	private int basketNo;
	private int basketCnt;
	private String memberId;
	private int itemNo;
	
	public int getBasketNo() {
		return basketNo;
	}
	public void setBasketNo(int basketNo) {
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
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

}
