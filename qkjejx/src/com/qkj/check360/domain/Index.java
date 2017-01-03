package com.qkj.check360.domain;

public class Index {
	private int uuid;
	private String user_id;// 人id
	private String kpi;
	private String cyc;// 周期
	private String count_way;// 计分方式
	private String definition;// 定义
	private String correctly;// 标准
	private Double weight;
	
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	
	
	
}
