package com.qkj.qkjmanager.domain;

import java.util.Date;

public class VarticDetail {
	private Integer uuid;// (int)系统编号
	private Date check_date;// 考核人考核时间
	private Integer score_id;// 主表id 
	private Integer check_index;//指标详情id 
	private Double check_score;// 总得分 （横向+纵）
	private Double check_goal;
	private Date ym;

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

	public Date getYm() {
		return ym;
	}

	public void setYm(Date ym) {
		this.ym = ym;
	}

	public Integer getCheck_index() {
		return check_index;
	}

	public void setCheck_index(Integer check_index) {
		this.check_index = check_index;
	}
	

	
}
