package com.member;

public class MemberDTO {
	
	private String memberId;
	private String pwd;
	private String email;
	private String gender;
	private String name;
	private String tel;
	private String addressCode; 
	private String address;
	private String addressDetail;
	private long clientNo;
	private String createDate;
	private String updateDate;
	private String deleteDate;
	private String gubun;

	
	
	
	@Override
	public String toString() {
		return "MemberDTO [memberId=" + memberId + ", pwd=" + pwd + ", email=" + email + ", gender=" + gender
				+ ", name=" + name + ", tel=" + tel + ", addressCode=" + addressCode + ", address=" + address
				+ ", addressDetail=" + addressDetail + ", clientNo=" + clientNo + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", deleteDate=" + deleteDate + ", gubun=" + gubun + "]";
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
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
	public long getClientNo() {
		return clientNo;
	}
	public void setClientNo(long clientNo) {
		this.clientNo = clientNo;
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
	public String getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	
	
}
