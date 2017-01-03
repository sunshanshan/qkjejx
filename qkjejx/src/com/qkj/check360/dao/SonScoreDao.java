package com.qkj.check360.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iweb.sys.AbstractDAO;

public class SonScoreDao extends AbstractDAO {

	public int add(Object parameters) {
		return (int) super.add("qkjmanager_addSonScore", parameters);
	}

	public List list(Map<String, Object> map) {
		return super.list("qkjmanager_getSonScores", map);
	}
	
	public List listview(Map<String, Object> map) {
		return super.list("qkjmanager_getSonScoresView", map);
	}
	
	public Object get(Object uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return super.get("qkjmanager_getSonScores", map);
	}

	public void save(Object parameters) {
		super.save("qkjmanager_mdySonScores", parameters);
	}
	
	public void del(Object parameters) {
		super.save("qkjmanager_delSonScore", parameters);
	}

	public int getResultCount() {
		return super.getResultCount();
	}
	
}
