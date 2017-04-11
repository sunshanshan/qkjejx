package com.qkj.check360.domain;

import java.util.Date;

public class Score360 {
	private Integer uuid;// (int)系统编号
	private Double check_score=0.0;// 评分分 （横向/纵）
	private Double check_gold=0.0;//得分
	private Date check_date;// 考核人考核时间
	private Integer check_user_id;//考核邮箱列表id
	private String lm_user;// (varchar)最后修改人
	private Date lm_time;// (datetime)最后修改时间
	private Integer check_ym;// 考核管理表考核打开的年月
	private String remark;
	private Double bscore;
	private Double weight;
	
	//查询
	private String acheck_user_name;
	private String acheck_user;
	private String check_email;
	private String check_user_name;
	private String check_user;
	private String user_id;
	private String user_name;
	private String title;
	private String cttitle;
	private Double sumscore;
	
	public String getAcheck_user() {
		return acheck_user;
	}
	public void setAcheck_user(String acheck_user) {
		this.acheck_user = acheck_user;
	}
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public Double getCheck_score() {
		return check_score;
	}
	public void setCheck_score(Double check_score) {
		this.check_score = check_score;
	}
	public Date getCheck_date() {
		return check_date;
	}
	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}
	public Integer getCheck_user_id() {
		return check_user_id;
	}
	public void setCheck_user_id(Integer check_user_id) {
		this.check_user_id = check_user_id;
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
	public Integer getCheck_ym() {
		return check_ym;
	}
	public void setCheck_ym(Integer check_ym) {
		this.check_ym = check_ym;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getBscore() {
		return bscore;
	}
	public void setBscore(Double bscore) {
		this.bscore = bscore;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getAcheck_user_name() {
		return acheck_user_name;
	}
	public void setAcheck_user_name(String acheck_user_name) {
		this.acheck_user_name = acheck_user_name;
	}
	public String getCheck_email() {
		return check_email;
	}
	public void setCheck_email(String check_email) {
		this.check_email = check_email;
	}
	public String getCheck_user_name() {
		return check_user_name;
	}
	public void setCheck_user_name(String check_user_name) {
		this.check_user_name = check_user_name;
	}
	public Double getCheck_gold() {
		return check_gold;
	}
	public void setCheck_gold(Double check_gold) {
		this.check_gold = check_gold;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCttitle() {
		return cttitle;
	}
	public void setCttitle(String cttitle) {
		this.cttitle = cttitle;
	}
	public String getCheck_user() {
		return check_user;
	}
	public void setCheck_user(String check_user) {
		this.check_user = check_user;
	}
	public Double getSumscore() {
		return sumscore;
	}
	public void setSumscore(Double sumscore) {
		this.sumscore = sumscore;
	}

}
