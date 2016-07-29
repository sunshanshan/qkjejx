package com.qkj.qkjmanager.dao;

import java.util.List;
import java.util.Map;

import org.iweb.sys.AbstractDAO;

public class reportDao extends AbstractDAO{
	/**
	 * 报表
	 * @param map
	 * @return
	 */
	public List list(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("qkjmanager_getVarticsReport", map);
	}
	/**
	 * 汇总
	 * @param map
	 * @return
	 */
	public List listhzd(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("qkjmanager_getVarticsHZD", map);
	}
	
	public List listhzu(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("qkjmanager_getVarticsHZU", map);
	}
	/**
	 * 绩效报告
	 * @param map
	 * @return
	 */
	public List listhbg(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("qkjmanager_getVarticsBG", map);
	}
	
	public List listhbgu(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("qkjmanager_getVarticsBGU", map);
	}
	
	
	
	public List listU(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("qkjmanager_getVarticsFKbyU", map);
	}
	
	public List listD(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("qkjmanager_getVarticsFKbyD", map);
	}
	
	public List listwillu(Map<String, Object> map) {
		return super.list("qkjmanager_getCheckWillbyu", map);
	}
	
	public List listwilld(Map<String, Object> map) {
		return super.list("qkjmanager_getCheckWillbyd", map);
	}
}
