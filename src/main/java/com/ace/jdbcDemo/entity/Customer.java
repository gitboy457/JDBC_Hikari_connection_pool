package com.ace.jdbcDemo.entity;

import java.sql.Date;

public class Customer {
	
	//private int id;

	private String custId;
	private String cust_name;
	private String mobile;
	private String email;
	private Date createDate;
	private Date updateDate;
	
	
	/*
	 * public int getId() { return id; } public void setId(int id) { this.id = id; }
	 */
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", cust_name=" + cust_name + ", mobile=" + mobile + ", email=" + email
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
	
	
	

}
