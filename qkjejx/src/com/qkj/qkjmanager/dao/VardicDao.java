package com.qkj.qkjmanager.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iweb.common.dao.CommonDAO;
import org.iweb.sys.AbstractDAO;

public class VardicDao extends AbstractDAO {

	public int add(Object parameters) {
		return (int) super.add("qkjmanager_addVartics", parameters);
	}

	public List list(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("qkjmanager_getVartics", map);
	}
	
	public List Checklist(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("qkjmanager_getCheckVartics", map);
	}

	public Object get(Object uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return super.get("qkjmanager_getVartics", map);
	}

	/**
	 * 修改信息
	 * 
	 * @param parameters
	 */
	public void save(Object parameters) {
		super.save("qkjmanager_mdyVartics", parameters);
	}
	
	public void saveScore(Object parameters) {
		super.save("qkjmanager_mdyVarticsCore", parameters);
	}
	
	public void saveBycheck(String uuid) {
		super.save("qkjmanager_mdyCheckCord", uuid);
	}
	
	public void saveByay(Object parameters) {
		super.save("qkjmanager_mdyayCord", parameters);
	}

	/**
	 * 删除客户信息
	 * 
	 * @param parameters
	 */
	public void del(Object parameters) {
		super.save("qkjmanager_delVartic", parameters);
	}


	public int getResultCount() {
		return super.getResultCount();
	}
}
