package com.qkj.qkjmanager.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iweb.common.dao.CommonDAO;
import org.iweb.sys.AbstractDAO;

import com.qkj.basics.dao.CheckDao;
import com.qkj.basics.domain.Check;
import com.qkj.qkjmanager.domain.Score;

public class TalkDao extends AbstractDAO {
	public List list(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("qkjmanager_getTalks", map);
	}

	public Object get(Object uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return super.get("qkjmanager_getTalks", map);
	}
	
	public int add(Object parameters) {
		return (int) super.add("qkjmanager_addTalks", parameters);
	}


	/**
	 * lingdao修改信息
	 * 
	 * @param parameters
	 */
	public void saveLeader(Object parameters) {
		super.save("qkjmanager_mdyLeader", parameters);
	}
	
	/**
	 *员工修改信息
	 * 
	 * @param parameters
	 */
	public void saveEmp(Object parameters) {
		super.save("qkjmanager_mdyEmp", parameters);
	}

	/**
	 * 删除客户信息
	 
	 * @param parameters
	 */
	public void del(Object parameters) {
		super.save("qkjmanager_delTalk", parameters);
	}

	public int gTalkltCount() {
		return super.getResultCount();
	}
	
}
