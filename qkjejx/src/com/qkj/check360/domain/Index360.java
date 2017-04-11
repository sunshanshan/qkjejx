package com.qkj.check360.domain;

import java.util.Date;

public class Index360 {
	private Integer uuid;
	private String title;// 人id
	private Integer state;
	private String add_user;// (varchar)地址
	private Date add_time;// (datetime)添加时间
	private String lm_user;// (varchar)最后修改人
	private Date lm_time;// (datetime)最后修改时间
	private String crit_id;
	private Integer main_id;
	
	private Integer typeUUID;
	private String typeTitle;
	private String uid;
	
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public String getLm_user() {
		return lm_user;
	}
	public void setLm_user(String lm_user) {
		this.lm_user = lm_user;
	}
	public Date getLm_time() {
		return lm_time;
	}
	public void setLm_time(Date lm_time) {
		this.lm_time = lm_time;
	}
	
	public String getCrit_id() {
		return crit_id;
	}
	public void setCrit_id(String crit_id) {
		this.crit_id = crit_id;
	}
	public Integer getMain_id() {
		return main_id;
	}
	public void setMain_id(Integer main_id) {
		this.main_id = main_id;
	}
	public String getTypeTitle() {
		return typeTitle;
	}
	public void setTypeTitle(String typeTitle) {
		this.typeTitle = typeTitle;
	}
	public Integer getTypeUUID() {
		return typeUUID;
	}
	public void setTypeUUID(Integer typeUUID) {
		this.typeUUID = typeUUID;
	}
	
	
}
