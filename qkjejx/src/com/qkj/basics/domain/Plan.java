package com.qkj.basics.domain;

import java.util.Date;

public class Plan {
	private Integer uuid;// (int)系统编号
	private Date plan_date;// 计划工作年月
	private String project;//工作项目 
	private String content;//内容 
	private Integer type;//1常规2临时e 
	private Date start_time;//计划开始时间
	private Date end_time;//计划结束时间
	private Date f_time;//计划完成时间
	private String them;//备注
	private String add_user;// (varchar)地址
	private Date add_time;// (datetime)添加时间 
	private String lm_user;// (varchar)最后修改人
	private Date lm_time;// (datetime)最后修改时间
	private String add_dept;//填加部门
	
	private String add_username;
	private String lm_username; 
	private String add_deptname;
	
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
	
	public Date getPlan_date() {
		return plan_date;
	}
	public void setPlan_date(Date plan_date) {
		this.plan_date = plan_date;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public Date getF_time() {
		return f_time;
	}
	public void setF_time(Date f_time) {
		this.f_time = f_time;
	}
	public String getThem() {
		return them;
	}
	public void setThem(String them) {
		this.them = them;
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
	public String getAdd_dept() {
		return add_dept;
	}
	public void setAdd_dept(String add_dept) {
		this.add_dept = add_dept;
	}
	public String getAdd_deptname() {
		return add_deptname;
	}
	public void setAdd_deptname(String add_deptname) {
		this.add_deptname = add_deptname;
	}

	

}
