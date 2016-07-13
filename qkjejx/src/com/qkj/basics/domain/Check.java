package com.qkj.basics.domain;

import java.util.Date;

public class Check {
	private Integer uuid;// (int)系统编号
	private Date ym;// 考核年月
	private Integer state;// 0打开1关闭3已审核
	private Date acheck_startdate;// 横向考核开始时间
	private Date acheck_closedate;// 横向考核结束时间
	private Date dcheck_startdate;// 部门考核开始时间
	private Date dcheck_closedate;// 部门考核结束时间
	private Date echeck_startdate;// 员工考核开始时间
	private Date echeck_closedate;// 员工考核结束时间
	private String add_user;// (varchar)地址
	private Date add_time;// (datetime)添加时间
	private String lm_user;// (varchar)最后修改人
	private Date lm_time;// (datetime)最后修改时间
	
	private String add_username;
	private String lm_username;
	
	public String getAdd_username() {
		return add_username;
	}
	public void setAdd_username(String add_username) {
		this.add_username = add_username;
	}
	public String getLm_username() {
		return lm_username;
	}
	public void setLm_username(String lm_username) {
		this.lm_username = lm_username;
	}
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	
	public Date getYm() {
		return ym;
	}
	public void setYm(Date ym) {
		this.ym = ym;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getAcheck_startdate() {
		return acheck_startdate;
	}
	public void setAcheck_startdate(Date acheck_startdate) {
		this.acheck_startdate = acheck_startdate;
	}
	public Date getAcheck_closedate() {
		return acheck_closedate;
	}
	public void setAcheck_closedate(Date acheck_closedate) {
		this.acheck_closedate = acheck_closedate;
	}
	public Date getDcheck_startdate() {
		return dcheck_startdate;
	}
	public void setDcheck_startdate(Date dcheck_startdate) {
		this.dcheck_startdate = dcheck_startdate;
	}
	public Date getDcheck_closedate() {
		return dcheck_closedate;
	}
	public void setDcheck_closedate(Date dcheck_closedate) {
		this.dcheck_closedate = dcheck_closedate;
	}
	public Date getEcheck_startdate() {
		return echeck_startdate;
	}
	public void setEcheck_startdate(Date echeck_startdate) {
		this.echeck_startdate = echeck_startdate;
	}
	public Date getEcheck_closedate() {
		return echeck_closedate;
	}
	public void setEcheck_closedate(Date echeck_closedate) {
		this.echeck_closedate = echeck_closedate;
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

	

}
