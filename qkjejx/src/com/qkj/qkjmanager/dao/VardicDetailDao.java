package com.qkj.qkjmanager.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iweb.sys.AbstractDAO;
import org.iweb.sys.dao.KpiDAO;
import org.iweb.sys.domain.IndexDetail;

import com.qkj.qkjmanager.domain.Vartic;
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
	
	
	/**
	 * 修改关联分数
	 * @return
	 */
	public void updatescore(Vartic v1){
		Map<String, Object> map = new HashMap<String, Object>();
		VardicDao zdao=new VardicDao();
		//查询取班组的分数的人
		if(v1.getAcheck_usercode()!=null && v1.getCheck_ym()!=null){
		map.clear();
		map.put("depttype", "3");
		map.put("type3", v1.getAcheck_usercode());
		map.put("check_ym", v1.getCheck_ym());
		
		List<VarticDetail> desu=new ArrayList<>();
		desu=listmdy(map);
		if(desu.size()>0){
			for(int i=0;i<desu.size();i++){
				VarticDetail v=new VarticDetail();
				v=desu.get(i);
				//查询权重
				KpiDAO kpidao=new KpiDAO();
				IndexDetail indexdetail=new IndexDetail();
				indexdetail=(IndexDetail) kpidao.get(v.getCheck_index());
				Double w=indexdetail.getWeight();//权重
				//查询此人所有部门的成绩
				map.clear();
				map.put("userid", v.getAuser());
				map.put("check_ym", v1.getCheck_ym());
				List<VarticDetail> v3=new ArrayList<>();
				v3=scorebykpibydept(map);
				Double score=0.00;
				if(v3.size()>1){
					for(int j=0;j<v3.size();j++){
						score=score+v3.get(j).getCheck_score()*(w/v3.size());
					}
				}else{
					if(v3.size()>0){
						score=(score+v3.get(0).getCheck_score())*w;
					}
				}
				v.setCheck_score(score/w);
				v.setCheck_goal(score);
				save(v);
				zdao.saveBycheck(v.getScore_id().toString());
				//查询取这个部门分数据是否还是部门如果还是部门就要悠这个部门也面的人员或部门
				Vartic v2=new Vartic();
				v1=(Vartic) zdao.get(v.getScore_id());
				if(v2!=null && v2.getAcheck_user()==null){//说明是部门
					updatescore(v2);
				}
			}
		}
		}
	}

	public int getResultCount() {
		return super.getResultCount();
	}
}
