package com.qkj.qkjmanager.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iweb.common.dao.CommonDAO;
import org.iweb.sys.AbstractDAO;

public class VardicDetailDao extends AbstractDAO {

	public int add(Object parameters) {
		return (int) super.add("qkjmanager_addVarticDetails", parameters);
	}

	public List list(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("qkjmanager_getVarticDetails", map);
	}
	
	public List Checklist(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("qkjmanager_getCheckVarticDetails", map);
	}
	
	
/*	public List kpilist(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("sys_getDeptsKpi", map);
	}
*/	public Object get(Object uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return super.get("qkjmanager_getVarticDetails", map);
	}

	/**
	 * 修改信息
	 * 
	 * @param parameters
	 */
	public void save(Object parameters) {
		super.save("qkjmanager_mdyVarticDetails", parameters);
	}

	/**
	 * 删除客户信息
	 * 
	 * @param parameters
	 */
	public void del(Object parameters) {
		super.save("qkjmanager_delVarticDetail", parameters);
	}


	public int getResultCount() {
		return super.getResultCount();
	}
}
