package com.qkj.check360.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iweb.sys.AbstractDAO;

public class ScoreDao extends AbstractDAO {

	public int add(Object parameters) {
		return (int) super.add("qkjmanager_addScore", parameters);
	}

	public List list(Map<String, Object> map) {
		return super.list("qkjmanager_getScores", map);
	}
	
	public List sumlist(Map<String, Object> map) {
		return super.list("qkjmanager_getSumScores", map);
	}
	
	public Object get(Object uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return super.get("qkjmanager_getScores", map);
	}

	public void save(Object parameters) {
		super.save("qkjmanager_mdyScores", parameters);
	}
	
	public void del(Object parameters) {
		super.save("qkjmanager_delScore", parameters);
	}

	public int getResultCount() {
		return super.getResultCount();
	}
	
}
