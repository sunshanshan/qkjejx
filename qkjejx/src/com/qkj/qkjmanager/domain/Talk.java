package com.qkj.qkjmanager.domain;

import java.util.Date;

public class Talk {
	private Integer uuid;// (int)系统编号
	private Integer vartic_id;//考核成绩id
	private String gjob;//上月内突出的业绩
	private String bjob;//上月内工作中存在的不足及需要提升的技能或能力。
	private Integer isnext;//双方沟通下月工作计划及目标是否达成一致？0 是1不是
	private String eujob;//对于上月内工作中相对薄弱的环节，你计划采取什么方式或行动弥补？（
	private String enjob;//在下月度内完成既定的工作目标有哪些困难或需要协调的事项？
	private String add_user;
	private Date add_time;
	private String lm_user;// (varchar)最后修改人
	private Date lm_time;// (datetime)最后修改时间
	
	private Integer cstate;
	public Integer getUuid() {
		return uuid;
	}
	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}
	
	public Integer getVartic_id() {
		return vartic_id;
	}
	public void setVartic_id(Integer vartic_id) {
		this.vartic_id = vartic_id;
	}
	public String getGjob() {
		return gjob;
	}
	public void setGjob(String gjob) {
		this.gjob = gjob;
	}
	public String getBjob() {
		return bjob;
	}
	public void setBjob(String bjob) {
		this.bjob = bjob;
	}
	public Integer getIsnext() {
		return isnext;
	}
	public void setIsnext(Integer isnext) {
		this.isnext = isnext;
	}
	public String getEujob() {
		return eujob;
	}
	public void setEujob(String eujob) {
		this.eujob = eujob;
	}
	public String getEnjob() {
		return enjob;
	}
	public void setEnjob(String enjob) {
		this.enjob = enjob;
	}
	public String getAdd_user() {
		return add_user;
	}
	public void setAdd_user(String add_user) {
		this.add_user = add_user;
	}
	public Date getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
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
	public Integer getCstate() {
		return cstate;
	}
	public void setCstate(Integer cstate) {
		this.cstate = cstate;
	}

	
}
