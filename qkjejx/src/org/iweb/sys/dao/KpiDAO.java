package org.iweb.sys.dao;

import java.util.List;
import java.util.Map;

import org.iweb.sys.AbstractDAO;

public class KpiDAO extends AbstractDAO {

	public List list(Map<String, Object> map) {
		return super.list("sys_getDeptsKpi", map);
	}
	
	
	public List listbyp(Map<String, Object> map) {
		return super.list("sys_getDeptsKpiByp", map);
	}
	
	
	public List listbydept(Map<String, Object> map){
		return super.list("flagDept", map);
	}
	
	
	
	public int getResultCount() {
		return super.getResultCount();
	}
}
