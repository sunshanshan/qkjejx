package com.qkj.check360.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iweb.sys.AbstractDAO;

public class IndexDAO extends AbstractDAO {

	public List list(Map<String, Object> map) {
		return super.list("index_get360", map);
	}
	
	public Object get(Object uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return super.get("index_get360", map);
	}
	
	public Object add(Object parameters) {
		return super.add("index_addIndex", parameters);
	}

	public int save(Object parameters) {
		return super.save("index_mdyIndex", parameters);
	}

	public int delete(Object parameters) {
		return super.delete("index_delIndex", parameters);
	}
	
	public int deletes(Object parameters) {
		return super.delete("index_delIndexs", parameters);
	}
	
	public int getResultCount() {
		return super.getResultCount();
	}
}
