package com.qkj.check360.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iweb.common.dao.CommonDAO;
import org.iweb.sys.AbstractDAO;

public class CheckDao extends AbstractDAO {

	public int add(Object parameters) {
		return (int) super.add("user_id_addIndex360", parameters);
	}
	
	public int addremark(Object parameters) {
		return (int) super.add("user_id_addremark360", parameters);
	}

	public List list(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("index_getIndex360", map);
	}
	
	public List listCrit(Map<String, Object> map) {
		return super.list("index_getIndexCrit360", map);
	}
	
	public List listremark(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("index_getremark360", map);
	}
	

	public Object get(Object uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return super.get("index_getIndex360", map);
	}

	/**
	 * 修改信息
	 * 
	 * @param parameters
	 */
	public void save(Object parameters) {
		super.save("user_id_mdyIndex360", parameters);
	}

	/**
	 * 删除客户信息
	 * 
	 * @param parameters
	 */
	public void del(Object parameters) {
		super.save("index_delIndex360", parameters);
	}
	
	public void delremark(Object parameters) {
		super.save("index_delremark360", parameters);
	}


	public int getResultCount() {
		return super.getResultCount();
	}
}
