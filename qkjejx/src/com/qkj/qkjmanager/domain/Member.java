package com.qkj.qkjmanager.domain;


public class Member {
	private Integer uuid;// (varchar)会员号
	private String company_name;// (varchar)公司
	
	
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	

}
