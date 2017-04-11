package com.qkj.check360.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iweb.sys.AbstractDAO;

public class IndexCheckDAO extends AbstractDAO {

	public List list(Map<String, Object> map) {
		return super.list("index_getIndexCheck", map);
	}
	
	public List listst(Map<String, Object> map) {
		return super.list("index_getIndexCheckSt", map);
	}
	
	public Object get(Object uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return super.get("index_getIndexCheck", map);
	}
	
	public Object add(Object parameters) {
		return super.add("user_id_addIndexChek", parameters);
	}
	
	public Object addAcheckUser(Object parameters) {
		return super.add("add_acheckUser", parameters);
	}
	
	public int delacheckUser(Object parameters) {
		return super.delete("del_acheckUser", parameters);
	}

	public int save(Object parameters) {
		return super.save("user_id_mdyIndexChek", parameters);
	}

	public int delete(Object parameters) {
		return super.delete("index_delIndexChek", parameters);
	}
	public int deletes(Object parameters) {
		return super.delete("index_delIndexChecks", parameters);
	}
	
	public int getResultCount() {
		return super.getResultCount();
	}
}
