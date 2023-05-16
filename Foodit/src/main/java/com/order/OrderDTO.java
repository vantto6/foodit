package com.order;

public class OrderDTO {
	private int orderNo;
	private int clientNo;
	private String addressCode;
	private String address;
	private String addressDetail;
	private int totPrice;
	private int confirm;
	private int field;
	private int ordetailNo;
	private int itemNo;
	private int ordetailCnt;
	private int price;
	private String payOption;
	private String payDate;
	private int disPrice;
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getClientNo() {
		return clientNo;
	}
	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}
	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public int getTotPrice() {
		return totPrice;
	}
	public void setTotPrice(int totPrice) {
		this.totPrice = totPrice;
	}
	public int getConfirm() {
		return confirm;
	}
	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}
	public int getOrdetailNo() {
		return ordetailNo;
	}
	public void setOrdetailNo(int ordetailNo) {
		this.ordetailNo = ordetailNo;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public int getOrdetailCnt() {
		return ordetailCnt;
	}
	public void setOrdetailCnt(int ordetailCnt) {
		this.ordetailCnt = ordetailCnt;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPayOption() {
		return payOption;
	}
	public void setPayOption(String payOption) {
		this.payOption = payOption;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public int getDisPrice() {
		return disPrice;
	}
	public void setDisPrice(int disPrice) {
		this.disPrice = disPrice;
	}
	public int getField() {
		return field;
	}
	public void setField(int field) {
		this.field = field;
	}
}
