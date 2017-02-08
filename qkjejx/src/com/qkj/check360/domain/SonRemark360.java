package com.qkj.check360.domain;

public class SonRemark360 {
	private Integer uuid;// (int)系统编号
	private Integer score_id;// 主表id
	private Integer remark_id;// 指标详情id
	private String remark;
	
	private String title;// 周期
	private String detail;// 计分方式
	private String cuname;
	
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
	public Integer getRemark_id() {
		return remark_id;
	}
	public void setRemark_id(Integer remark_id) {
		this.remark_id = remark_id;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCuname() {
		return cuname;
	}
	public void setCuname(String cuname) {
		this.cuname = cuname;
	}
	
}
