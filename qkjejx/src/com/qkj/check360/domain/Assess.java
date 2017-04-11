package com.qkj.check360.domain;

public class Assess {
	private Integer uuid;
	private Integer index_id;// 因素id
	private Integer capa_id;
	private Integer fact_id;
	private Integer main_id;
	private String title;
	private String detail;
	
	private String ftitle;
	private String ititle;
	private String ctitle;
	private String catitle;
	
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
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
	public String getFtitle() {
		return ftitle;
	}
	public void setFtitle(String ftitle) {
		this.ftitle = ftitle;
	}
	public Integer getCapa_id() {
		return capa_id;
	}
	public void setCapa_id(Integer capa_id) {
		this.capa_id = capa_id;
	}
	public Integer getFact_id() {
		return fact_id;
	}
	public void setFact_id(Integer fact_id) {
		this.fact_id = fact_id;
	}
	public String getItitle() {
		return ititle;
	}
	public void setItitle(String ititle) {
		this.ititle = ititle;
	}
	public String getCtitle() {
		return ctitle;
	}
	public void setCtitle(String ctitle) {
		this.ctitle = ctitle;
	}
	public String getCatitle() {
		return catitle;
	}
	public void setCatitle(String catitle) {
		this.catitle = catitle;
	}
	public Integer getMain_id() {
		return main_id;
	}
	public void setMain_id(Integer main_id) {
		this.main_id = main_id;
	}
	
	
}
