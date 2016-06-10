package com.qkj.qkjmanager.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VarticDetail {
	private Integer uuid;// (int)系统编号
	private Date check_date;// 考核人考核时间
	private Integer score_id;// 主表id
	private Integer check_index;// 指标详情id
	private Double check_score;// 总得分 （横向+纵）
	private Double check_goal;
	
	
	private String kpi;
	private String cyc;//周期
	private double weight;//权重
	private String definition;//定义
	private String correctly;//标准

	private List<VarticDetail> vdes = new ArrayList<>();

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public Date getCheck_date() {
		return check_date;
	}

	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}

	public Integer getScore_id() {
		return score_id;
	}

	public void setScore_id(Integer score_id) {
		this.score_id = score_id;
	}

	public Double getCheck_score() {
		return check_score;
	}

	public void setCheck_score(Double check_score) {
		this.check_score = check_score;
	}

	public Double getCheck_goal() {
		return check_goal;
	}

	public void setCheck_goal(Double check_goal) {
		this.check_goal = check_goal;
	}

	public Integer getCheck_index() {
		return check_index;
	}

	public void setCheck_index(Integer check_index) {
		this.check_index = check_index;
	}

	public List<VarticDetail> getVdes() {
		return vdes;
	}

	public void setVdes(List<VarticDetail> vdes) {
		this.vdes = vdes;
	}

	public String getKpi() {
		return kpi;
	}

	public void setKpi(String kpi) {
		this.kpi = kpi;
	}

	public String getCyc() {
		return cyc;
	}

	public void setCyc(String cyc) {
		this.cyc = cyc;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
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

}
