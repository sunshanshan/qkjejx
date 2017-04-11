package com.qkj.check360.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iweb.sys.AbstractDAO;

public class IndexDAO extends AbstractDAO {
	/**
	 * 第四级考核问答
	 * @param map
	 * @return
	 */
	public List listAss(Map<String, Object> map) {
		return super.list("index_getAssess", map);
	}
	
	public Object getAss(Object uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return super.get("index_getAssess", map);
	}
	
	public Object addAss(Object parameters) {
		return super.add("index_addAssess", parameters);
	}

	public int saveAss(Object parameters) {
		return super.save("index_mdyAssess", parameters);
	}

	public int deleteAss(Object parameters) {
		return super.delete("index_delAssess", parameters);
	}
	
	/**
	 * 第三级管理维度
	 * @param map
	 * @return
	 */
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
	
	/**
	 * 第二级管理维度
	 * @param map
	 * @return
	 */
	public List listFact(Map<String, Object> map) {
		return super.list("index_getFactors", map);
	}
	
	public Object getFact(Object uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return super.get("index_getFactors", map);
	}
	
	public Object addFact(Object parameters) {
		return super.add("index_addFactors", parameters);
	}

	public int saveFact(Object parameters) {
		return super.save("index_mdyFactors", parameters);
	}

	public int deleteFact(Object parameters) {
		return super.delete("index_delFactors", parameters);
	}
	
	/**
	 * 第一级管理维度
	 * @param map
	 * @return
	 */
	
	public List listMain(Map<String, Object> map) {
		return super.list("index_getmainca", map);
	}
	
	public Object getMain(Object uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return super.get("index_getmainca", map);
	}
	
	public Object addMain(Object parameters) {
		return super.add("index_addmainca", parameters);
	}

	public int saveMain(Object parameters) {
		return super.save("index_mdymainca", parameters);
	}

	public int deleteMain(Object parameters) {
		return super.delete("index_delmainca", parameters);
	}
	
	
	public List listCapa(Map<String, Object> map) {
		return super.list("index_getCapacitys", map);
	}
	
	public Object getCapa(Object uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return super.get("index_getCapacitys", map);
	}
	
	public Object addCapa(Object parameters) {
		return super.add("index_addCapacity", parameters);
	}

	public int saveCapa(Object parameters) {
		return super.save("index_mdyCapacity", parameters);
	}

	public int deleteCapa(Object parameters) {
		return super.delete("index_delCapacity", parameters);
	}
	
	
	
	/**
	 * 评估领域
	 * @param parameters
	 * @return
	 */
	public List listType(Map<String, Object> map) {
		return super.list("qkjmanager_getCtypes", map);
	}
	
	public Object getType(Object uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return super.get("qkjmanager_getCtypes", map);
	}
	
	public Object addType(Object parameters) {
		return super.add("qkjmanager_addCtype", parameters);
	}

	public int saveType(Object parameters) {
		return super.save("qkjmanager_mdyCtype", parameters);
	}

	public int deleteType(Object parameters) {
		return super.delete("qkjmanager_delCtype", parameters);
	}
	
	public int getResultCount() {
		return super.getResultCount();
	}
}
