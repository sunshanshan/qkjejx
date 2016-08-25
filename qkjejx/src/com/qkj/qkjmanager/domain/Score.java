package com.qkj.qkjmanager.domain;

import java.sql.Date;

public class Score {
	private Integer uuid;// (int)系统编号
	private Date check_ym;
	private String check_user;
	private String check_usercode;
	private String acheck_user;
	private String acheck_usercode;
	private double check_score;
	private Date check_date;
	private String lm_user;
	private Date lm_time;
	private int typea;
	private double ay_totelScore;
	
	private String leavea;
	private String leaveb;
	private String leavec;
	private String leaved;
	
	
	
	public String getLeavea() {
		return leavea;
	}
	public void setLeavea(String leavea) {
		this.leavea = leavea;
	}
	public String getLeaveb() {
		return leaveb;
	}
	public void setLeaveb(String leaveb) {
		this.leaveb = leaveb;
	}
	public String getLeavec() {
		return leavec;
	}
	public void setLeavec(String leavec) {
		this.leavec = leavec;
	}
	public String getLeaved() {
		return leaved;
	}
	public void setLeaved(String leaved) {
		this.leaved = leaved;
	}
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public Date getCheck_ym() {
		return check_ym;
	}
	public void setCheck_ym(Date check_ym) {
		this.check_ym = check_ym;
	}
	public String getCheck_user() {
		return check_user;
	}
	public void setCheck_user(String check_user) {
		this.check_user = check_user;
	}
	public String getCheck_usercode() {
		return check_usercode;
	}
	public void setCheck_usercode(String check_usercode) {
		this.check_usercode = check_usercode;
	}
	public String getAcheck_user() {
		return acheck_user;
	}
	public void setAcheck_user(String acheck_user) {
		this.acheck_user = acheck_user;
	}
	public String getAcheck_usercode() {
		return acheck_usercode;
	}
	public void setAcheck_usercode(String acheck_usercode) {
		this.acheck_usercode = acheck_usercode;
	}
	public double getCheck_score() {
		return check_score;
	}
	public void setCheck_score(double check_score) {
		this.check_score = check_score;
	}
	public Date getCheck_date() {
		return check_date;
	}
	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
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
	public int getTypea() {
		return typea;
	}
	public void setTypea(int typea) {
		this.typea = typea;
	}
	public double getAy_totelScore() {
		return ay_totelScore;
	}
	public void setAy_totelScore(double ay_totelScore) {
		this.ay_totelScore = ay_totelScore;
	}
}
