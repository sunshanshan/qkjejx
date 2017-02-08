package com.qkj.check360.domain;

public class SonScore360 {
	private Integer uuid;// (int)系统编号
	private Integer score_id;// 主表id
	private Integer check_index;// 指标详情id
	private Double check_score;// 总得分 （横向+纵）
	private Double goal;
	private String kpi;
	private Double weight;
	
	
	private Integer index_id;
	private String title;
	private String detail;
	private String ititle;
	private String idetail;
	
	private String check_user;
	private String user_id;
	private Integer check_ym;
	
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
	public Double getGoal() {
		return goal;
	}
	public void setGoal(Double goal) {
		this.goal = goal;
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
	public Integer getIndex_id() {
		return index_id;
	}
	public void setIndex_id(Integer index_id) {
		this.index_id = index_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getItitle() {
		return ititle;
	}
	public void setItitle(String ititle) {
		this.ititle = ititle;
	}
	public String getIdetail() {
		return idetail;
	}
	public void setIdetail(String idetail) {
		this.idetail = idetail;
	}
	public String getCheck_user() {
		return check_user;
	}
	public void setCheck_user(String check_user) {
		this.check_user = check_user;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Integer getCheck_ym() {
		return check_ym;
	}
	public void setCheck_ym(Integer check_ym) {
		this.check_ym = check_ym;
	}
	
	
}
