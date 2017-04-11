package com.qkj.check360.domain;

import java.util.Date;

public class Capacity {
	private Integer uuid;
	private String title;
	private String add_user;
	private Date add_time;
	private String ztitle;
	private Integer main_id;
	/*private String user_name;
	private String user_id;
	private Integer crit_id;*/
	
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
	public String getCrit_title() {
		return crit_title;
	}
	public void setCrit_title(String crit_title) {
		this.crit_title = crit_title;
	}
	public String getZtitle() {
		return ztitle;
	}
	public void setZtitle(String ztitle) {
		this.ztitle = ztitle;
	}
	public Integer getMain_id() {
		return main_id;
	}
	public void setMain_id(Integer main_id) {
		this.main_id = main_id;
	}
	
}
