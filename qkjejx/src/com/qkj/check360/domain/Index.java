package com.qkj.check360.domain;

public class Index {
	private Integer uuid;
	private Integer factors_id;// 因素id
	private String manifestation;
	private String detail;
	
	private String ftitle;
	private String ctitle;
	
	public String getCtitle() {
		return ctitle;
	}
	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	public Integer getFactors_id() {
		return factors_id;
	}
	public void setFactors_id(Integer factors_id) {
		this.factors_id = factors_id;
	}
	public String getManifestation() {
		return manifestation;
	}
	public void setManifestation(String manifestation) {
		this.manifestation = manifestation;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getFtitle() {
		return ftitle;
	}
	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}
	
}
