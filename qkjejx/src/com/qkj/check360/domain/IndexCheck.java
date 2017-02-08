package com.qkj.check360.domain;

public class IndexCheck {

	private int uuid;
	private String user_id;// 人id
	private String check_user;
	private String check_user_email;
	private Double weight;// 周期
	private Integer checkType_id;

	private String check_user_name;
	private String cuname;
	private String title;
	private Integer ctuuid;

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

	public String getCheck_user() {
		return check_user;
	}

	public void setCheck_user(String check_user) {
		this.check_user = check_user;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getCheck_user_name() {
		return check_user_name;
	}

	public void setCheck_user_name(String check_user_name) {
		this.check_user_name = check_user_name;
	}

	public String getCheck_user_email() {
		return check_user_email;
	}

	public void setCheck_user_email(String check_user_email) {
		this.check_user_email = check_user_email;
	}

	public String getCuname() {
		return cuname;
	}

	public void setCuname(String cuname) {
		this.cuname = cuname;
	}

	public Integer getCheckType_id() {
		return checkType_id;
	}

	public void setCheckType_id(Integer checkType_id) {
		this.checkType_id = checkType_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCtuuid() {
		return ctuuid;
	}

	public void setCtuuid(Integer ctuuid) {
		this.ctuuid = ctuuid;
	}

}
