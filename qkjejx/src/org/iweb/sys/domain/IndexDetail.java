package org.iweb.sys.domain;

public class IndexDetail {
	private int uuid;
	private String dept_code;// 部门code
	private String kpi;
	private String cyc;// 周期
	private double weight;// 权重
	private String count_way;// 计分方式
	private String definition;// 定义
	private String correctly;// 标准
	private String info_file;// 信息来源文件
	private String info_deptcode;// 信息来源部门
	private String check_deptcode;// 横向考核部门
	private String check_post;// 横向考核岗位
	private String isdept;// 是否部门得分 0 横1纵
	private String position_id;
	private String type;// 1：职务权重 2：部门权重
	private String position_dept;
	private Double goal;
	private Double score;
	private String acheck_user;
	private String code;
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public String getDept_code() {
		return dept_code;
	}
	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
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
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
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
	public String getInfo_file() {
		return info_file;
	}
	public void setInfo_file(String info_file) {
		this.info_file = info_file;
	}
	public String getInfo_deptcode() {
		return info_deptcode;
	}
	public void setInfo_deptcode(String info_deptcode) {
		this.info_deptcode = info_deptcode;
	}
	public String getCheck_deptcode() {
		return check_deptcode;
	}
	public void setCheck_deptcode(String check_deptcode) {
		this.check_deptcode = check_deptcode;
	}
	public String getCheck_post() {
		return check_post;
	}
	public void setCheck_post(String check_post) {
		this.check_post = check_post;
	}
	public String getIsdept() {
		return isdept;
	}
	public void setIsdept(String isdept) {
		this.isdept = isdept;
	}
	public String getPosition_id() {
		return position_id;
	}
	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPosition_dept() {
		return position_dept;
	}
	public void setPosition_dept(String position_dept) {
		this.position_dept = position_dept;
	}
	public Double getGoal() {
		return goal;
	}
	public void setGoal(Double goal) {
		this.goal = goal;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getAcheck_user() {
		return acheck_user;
	}
	public void setAcheck_user(String acheck_user) {
		this.acheck_user = acheck_user;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
