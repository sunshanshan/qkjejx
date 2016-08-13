package com.qkj.qkjmanager.domain;


public class VarticView {
	private String u_id;
	private String d_code;
	private String username;
	private String deptname;
	private String pname;
	private String kpi;
	private Double weight;// 权重 
	private Double bscore;
	private Double check_score;
	private String remark;
	private Double score;
	private Double gold;
	
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getD_code() {
		return d_code;
	}
	public void setD_code(String d_code) {
		this.d_code = d_code;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Double getGold() {
		return gold;
	}
	public void setGold(Double gold) {
		this.gold = gold;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getKpi() {
		return kpi;
	}
	public void setKpi(String kpi) {
		this.kpi = kpi;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getBscore() {
		return bscore;
	}
	public void setBscore(Double bscore) {
		this.bscore = bscore;
	}
	public Double getCheck_score() {
		return check_score;
	}
	public void setCheck_score(Double check_score) {
		this.check_score = check_score;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
