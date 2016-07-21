package com.qkj.qkjmanager.domain;

import java.util.Date;

public class Vartic {
	private Integer uuid;// (int)系统编号
	private Date check_date;// 考核人考核时间
	private String acheck_user;// 被考核人
	private String acheck_usercode;// 被考核人部门
	private String lm_user;// (varchar)最后修改人
	private Date lm_time;// (datetime)最后修改时间
	private Integer check_ym;// 考核管理表考核打开的年月
	private Double check_score=0.0;// 总得分 （横向/纵）
	
	private Double ay_totelScore;//总得分 （横向+纵）
	
	private String check_username;
	private String acheck_username;
	private String lm_username;
	private String check_deptname;
	private String acheck_deptname;
	private String u_id;  
	private String u_code;
	
	private String check_yms;
	private Date acheck_startdate;// 横向考核开始时间
	private Date acheck_closedate;// 横向考核结束时间
	private Date dcheck_startdate;// 部门考核开始时间
	private Date dcheck_closedate;// 部门考核结束时间
	private Date echeck_startdate;// 员工考核开始时间
	private Date echeck_closedate;// 员工考核结束时间
	private String df_name;//父部门
	private String acheck_dept;//纵向考核部门 
	private Double bscore;
	private String remark;
	private String hremark;
	
	private String acd_cname;
	private Double tscore;
	private String d_code; 
	private Integer cstate;
	private Double ss_goal;
	
	private Integer finish;//部门是否考核完成，，用于取班组分数时判断是否班组考核完成
	
	public Integer getFinish() {
		return finish;
	}

	public void setFinish(Integer finish) {
		this.finish = finish;
	}

	public Double getSs_goal() {
		return ss_goal;
	}

	public void setSs_goal(Double ss_goal) {
		this.ss_goal = ss_goal;
	}

	public String getHremark() {
		return hremark;
	}

	public void setHremark(String hremark) {
		this.hremark = hremark;
	}

	private Date cym;
	

	public Integer getCstate() {
		return cstate;
	}

	public void setCstate(Integer cstate) {
		this.cstate = cstate;
	}

	public Date getCym() {
		return cym;
	}

	public void setCym(Date cym) {
		this.cym = cym;
	}

	public String getAcd_cname() {
		return acd_cname;
	}

	public void setAcd_cname(String acd_cname) {
		this.acd_cname = acd_cname;
	}

	public String getD_code() {
		return d_code;
	}

	public void setD_code(String d_code) {
		this.d_code = d_code;
	}

	public String getDf_name() {
		return df_name;
	}

	public void setDf_name(String df_name) {
		this.df_name = df_name;
	}

	public String getCheck_yms() {
		return check_yms;
	}

	public void setCheck_yms(String check_yms) {
		this.check_yms = check_yms;
	}

	public Double getAy_totelScore() {
		return ay_totelScore;
	}

	public void setAy_totelScore(Double ay_totelScore) {
		this.ay_totelScore = ay_totelScore;
	}

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

	public String getAcheck_user() {
		return acheck_user;
	}

	public void setAcheck_user(String acheck_user) {
		this.acheck_user = acheck_user;
	}

	public String getAcheck_usercode() {
		return acheck_usercode;
	}

	public void setAcheck_usercode(String acheck_usercode) {
		this.acheck_usercode = acheck_usercode;
	}


	public Double getCheck_score() {
		return check_score;
	}

	public void setCheck_score(Double check_score) {
		this.check_score = check_score;
	}

	public String getCheck_username() {
		return check_username;
	}

	public void setCheck_username(String check_username) {
		this.check_username = check_username;
	}

	public String getAcheck_username() {
		return acheck_username;
	}

	public void setAcheck_username(String acheck_username) {
		this.acheck_username = acheck_username;
	}

	public String getLm_username() {
		return lm_username;
	}

	public void setLm_username(String lm_username) {
		this.lm_username = lm_username;
	}

	public String getLm_user() {
		return lm_user;
	}

	public void setLm_user(String lm_user) {
		this.lm_user = lm_user;
	}

	public Date getLm_time() {
		return lm_time;
	}

	public void setLm_time(Date lm_time) {
		this.lm_time = lm_time;
	}

	public String getCheck_deptname() {
		return check_deptname;
	}

	public void setCheck_deptname(String check_deptname) {
		this.check_deptname = check_deptname;
	}

	public String getAcheck_deptname() {
		return acheck_deptname;
	}

	public void setAcheck_deptname(String acheck_deptname) {
		this.acheck_deptname = acheck_deptname;
	}

	public Integer getCheck_ym() {
		return check_ym;
	}

	public void setCheck_ym(Integer check_ym) {
		this.check_ym = check_ym;
	}

	public Date getAcheck_startdate() {
		return acheck_startdate;
	}

	public void setAcheck_startdate(Date acheck_startdate) {
		this.acheck_startdate = acheck_startdate;
	}

	public Date getAcheck_closedate() {
		return acheck_closedate;
	}

	public void setAcheck_closedate(Date acheck_closedate) {
		this.acheck_closedate = acheck_closedate;
	}

	public Date getDcheck_startdate() {
		return dcheck_startdate;
	}

	public void setDcheck_startdate(Date dcheck_startdate) {
		this.dcheck_startdate = dcheck_startdate;
	}

	public Date getDcheck_closedate() {
		return dcheck_closedate;
	}

	public void setDcheck_closedate(Date dcheck_closedate) {
		this.dcheck_closedate = dcheck_closedate;
	}

	public Date getEcheck_startdate() {
		return echeck_startdate;
	}

	public void setEcheck_startdate(Date echeck_startdate) {
		this.echeck_startdate = echeck_startdate;
	}

	public Date getEcheck_closedate() {
		return echeck_closedate;
	}

	public void setEcheck_closedate(Date echeck_closedate) {
		this.echeck_closedate = echeck_closedate;
	}

	public String getU_id() {
		return u_id;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public String getU_code() {
		return u_code;
	}

	public void setU_code(String u_code) {
		this.u_code = u_code;
	}

	public Double getTscore() {
		return tscore;
	}

	public void setTscore(Double tscore) {
		this.tscore = tscore;
	}

	public String getAcheck_dept() {
		return acheck_dept;
	}

	public void setAcheck_dept(String acheck_dept) {
		this.acheck_dept = acheck_dept;
	}

	public Double getBscore() {
		return bscore;
	}

	public void setBscore(Double bscore) {
		this.bscore = bscore;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	
}
