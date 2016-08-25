package com.qkj.qkjmanager.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iweb.common.dao.CommonDAO;
import org.iweb.sys.AbstractDAO;

import com.qkj.qkjmanager.domain.VarticDetail;

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
	
	public List listmdy(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("scorebymdy", map);
	}
	
	public List scorebykpibydept(Map<String, Object> map) {
		//setCountMapid("basics_getCheckCounts");
		return super.list("scorebykpibydept", map);
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
	
	public void delformV(Object parameters) {
		super.save("qkjmanager_delVarticDetailfromV", parameters);
	}
	
	/**
	 * 判断考核分数是否重复
	 * @return
	 */
	public Boolean repeatKpi(Object score_id,Object check_index){
		Boolean flag=true;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("score_id", score_id);
		map.put("check_index", check_index);
		List<VarticDetail> vds=new ArrayList();
		vds=super.list("qkjmanager_getVarticDetails", map);
		if(vds.size()>0){
			flag=false;
		}
		return flag;
	}

	public int getResultCount() {
		return super.getResultCount();
	}
}
