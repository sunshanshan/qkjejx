package com.qkj.basics.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iweb.common.dao.CommonDAO;
import org.iweb.sys.AbstractDAO;

public class PlanDao extends AbstractDAO {

	public int add(Object parameters) {
		return (int) super.add("basics_addPlan", parameters);
	}

	public List list(Map<String, Object> map) {
		//setCountMapid("basics_getPlanCounts");
		return super.list("basics_getPlans", map);
	}

	public Object get(Object uuid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uuid", uuid);
		return super.get("basics_getPlans", map);
	}

	/**
	 * 修改信息
	 * 
	 * @param parameters
	 */
	public void save(Object parameters) {
		super.save("basics_mdyPlan", parameters);
	}

	/**
	 * 删除客户信息
	 * 
	 * @param parameters
	 */
	public void del(Object parameters) {
		super.save("basics_delPlan", parameters);
	}

	/**
	 * 检查提交重复性
	 * 
	 * @param linkid
	 * @param sec
	 * @return
	 */
	/*public int checkSecNO(String linkid, String sec) {
		CommonDAO cd = new CommonDAO();
		String sql1 = "SELECT Count(*) FROM web_h_cus_info i WHERE i.linkid = '" + linkid + "' AND i.`security` = '" + sec + "'";
		long i1 = (long) cd.commonSelectObject(sql1);
		if (i1 > 0) {
			// 该用户已经录入过
			return 1;
		}

		String sql2 = "SELECT COUNT(*) FROM web_t_cus_source_2014 WHERE `zqzh` =  '" + sec + "'";
		long i2 = (long) cd.commonSelectObject(sql2);
		if (i2 == 0) {
			// 不是预定人群
			return 2;
		}
		return 0;
	}*/

	public int getResultCount() {
		return super.getResultCount();
	}
}
