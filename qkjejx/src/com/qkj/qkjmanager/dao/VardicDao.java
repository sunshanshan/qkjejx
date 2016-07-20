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

public class VardicDao extends AbstractDAO {

	public int add(Object parameters) {
		return (int) super.add("qkjmanager_addVartics", parameters);
	}

	public List list(Map<String, Object> map) {
		return super.list("qkjmanager_getVartics", map);
	}
	
	public List Checklist(Map<String, Object> map) {
		return super.list("qkjmanager_getCheckVartics", map);
	}
	
	public List Checklistbydept(Map<String, Object> map) {
		return super.list("qkjmanager_getCheckVarticsdept", map);
	}
	
	public List getHScore(Map<String, Object> map) {
		return super.list("qkjmanager_getHScore", map);
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
	
	public void saveFin(Object parameters) {
		super.save("qkjmanager_mdyFin", parameters);
	}
	
	public void savev(Object parameters) {
		super.save("qkjmanager_mdyV", parameters);
	}
	
	public void saveScore(Object parameters) {
		super.save("qkjmanager_mdyVarticsCore", parameters);
	}
	
	public void saveay(Object parameters) {
		super.save("qkjmanager_mdyay", parameters);
	}
	
	public void saveBycheck(String uuid) {
		super.save("qkjmanager_mdyCheckCord", uuid);
	}
	
	
	public List listByPosition(Map<String, Object> map) {
		return super.list("qkjmanager_getVarticsByPosition", map);
	}

	/**
	 * 删除客户信息
	 
	 * @param parameters
	 */
	public void del(Object parameters) {
		super.save("qkjmanager_delVartic", parameters);
	}


	public int getResultCount() {
		return super.getResultCount();
	}
	
	/**
	 * 
	 * @return
	 */
	public Check check_cym(){
		//查询打开的审核日期
		Check check=new Check();
		Map<String, Object> map = new HashMap<String, Object>();
		CheckDao c=new CheckDao();
		List<Check> checks=new ArrayList();
		map.clear();
		map.put("state", 0);//状态打开的审核日期
		checks=c.list(map);
		if(checks.size()>0){
			check=checks.get(0);
		}
		return check;
	}
	
}
