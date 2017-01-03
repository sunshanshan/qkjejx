package com.qkj.check360.domain;

import java.util.Date;


public class SonScore360 {
	private Integer uuid;// (int)系统编号
	private Integer score_id;// 主表id
	private Integer check_index;// 指标详情id
	private Double check_score;// 总得分 （横向+纵）
	private Double goal;
	private String kpi;
	private Double weight;
	
	
	private Double total;// 评分分 （横向/纵）
	private Double check_gold=0.0;//得分
	private Date check_date;// 考核人考核时间
	private String check_username;
	private String remark;
	
	private String cyc;// 周期
	private String count_way;// 计分方式
	private String definition;// 定义
	private String correctly;// 标准
	private String check_user;
	
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public Integer getScore_id() {
		return score_id;
	}
	public void setScore_id(Integer score_id) {
		this.score_id = score_id;
	}
	public Integer getCheck_index() {
		return check_index;
	}
	public void setCheck_index(Integer check_index) {
		this.check_index = check_index;
	}
	public Double getCheck_score() {
		return check_score;
	}
	public void setCheck_score(Double check_score) {
		this.check_score = check_score;
	}
	public String getKpi() {
		return kpi;
	}
	public void setKpi(String kpi) {
		this.kpi = kpi;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getCheck_gold() {
		return check_gold;
	}
	public void setCheck_gold(Double check_gold) {
		this.check_gold = check_gold;
	}
	public Date getCheck_date() {
		return check_date;
	}
	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}
	public String getCheck_username() {
		return check_username;
	}
	public void setCheck_username(String check_username) {
		this.check_username = check_username;
	}
	public Double getGoal() {
		return goal;
	}
	public void setGoal(Double goal) {
		this.goal = goal;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCyc() {
		return cyc;
	}
	public void setCyc(String cyc) {
		this.cyc = cyc;
	}
	public String getCount_way() {
		return count_way;
	}
	public void setCount_way(String count_way) {
		this.count_way = count_way;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getCorrectly() {
		return correctly;
	}
	public void setCorrectly(String correctly) {
		this.correctly = correctly;
	}
	public String getCheck_user() {
		return check_user;
	}
	public void setCheck_user(String check_user) {
		this.check_user = check_user;
	}
	
	
	
}
