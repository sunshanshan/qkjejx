package com.qkj.check360.domain;

import java.util.Date;

public class Capacity {
	private Integer uuid;
	private String title;
	private String add_user;
	private Date add_time;
	private String user_name;
	private String user_id;
	private Integer crit_id;
	
	private String crit_title;
	
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
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Integer getCrit_id() {
		return crit_id;
	}
	public void setCrit_id(Integer crit_id) {
		this.crit_id = crit_id;
	}
	public String getCrit_title() {
		return crit_title;
	}
	public void setCrit_title(String crit_title) {
		this.crit_title = crit_title;
	}
	
}
