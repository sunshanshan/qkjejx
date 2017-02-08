package com.qkj.check360.domain;

import java.util.Date;

public class CheckType360 {
	private Integer uuid;
	private String title;// 人id
	private Double weight;
	private String add_user;// (varchar)地址
	private Date add_time;// (datetime)添加时间
	private Integer state;//0:其它人1：自身
	
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getAdd_user() {
		return add_user;
	}
	public void setAdd_user(String add_user) {
		this.add_user = add_user;
	}
	public Date getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
