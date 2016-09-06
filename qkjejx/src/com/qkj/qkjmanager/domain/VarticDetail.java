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
	private Double weight;//权重
	private String definition;//定义
	private String correctly;//标准
	private Integer typea;//0横向1纵向  
	private String depttype;//是否取部门分数
	private String position_dept;//取哪个部门
	
	private String check_user;// 考核人
	private String check_usercode;// 考核人部门
	private Integer kpi_id;
	private Integer isdept;
	private Integer dtype;
	private String auser;
	
	private String check_user_name;
	private String acheck_user_name;
	private String position_name;
	private String dept_name;
	private String ym;
	
	
	
	public String getCheck_user_name() {
		return check_user_name;
	}

	public void setCheck_user_name(String check_user_name) {
		this.check_user_name = check_user_name;
	}

	public String getAcheck_user_name() {
		return acheck_user_name;
	}

	public void setAcheck_user_name(String acheck_user_name) {
		this.acheck_user_name = acheck_user_name;
	}

	public String getPosition_name() {
		return position_name;
	}

	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getAuser() {
		return auser;
	}

	public void setAuser(String auser) {
		this.auser = auser;
	}

	public String getPosition_dept() {
		return position_dept;
	}

	public void setPosition_dept(String position_dept) {
		this.position_dept = position_dept;
	}
	
	public String getDepttype() {
		return depttype;
	}

	public void setDepttype(String depttype) {
		this.depttype = depttype;
	}

	public Integer getDtype() {
		return dtype;
	}

	public void setDtype(Integer dtype) {
		this.dtype = dtype;
	}

	public Integer getTypea() {
		return typea;
	}

	public void setTypea(Integer typea) {
		this.typea = typea;
	}

	public Integer getKpi_id() {
		return kpi_id;
	}

	public void setKpi_id(Integer kpi_id) {
		this.kpi_id = kpi_id;
	}

	public String getCheck_user() {
		return check_user;
	}

	public void setCheck_user(String check_user) {
		this.check_user = check_user;
	}

	public String getCheck_usercode() {
		return check_usercode;
	}

	public void setCheck_usercode(String check_usercode) {
		this.check_usercode = check_usercode;
	}

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

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
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

	public Integer getIsdept() {
		return isdept;
	}

	public void setIsdept(Integer isdept) {
		this.isdept = isdept;
	}

}
