package com.qkj.qkjmanager.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.qkjmanager.dao.VardicDao;
import com.qkj.qkjmanager.dao.reportDao;
import com.qkj.qkjmanager.domain.Score;
import com.qkj.qkjmanager.domain.Vartic;

public class ReportAction extends ActionSupport {
	private reportDao dao = new reportDao();
	private Map<String, Object> map = new HashMap<String, Object>();
	private List<Score> score;
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	public List<Score> getScore() {
		return score;
	}
	public void setScore(List<Score> score) {
		this.score = score;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getViewFlag() {
		return viewFlag;
	}
	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}
	public int getRecCount() {
		return recCount;
	}
	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public String list() throws Exception {
		score=dao.list(map);
		return SUCCESS;
	}
}
