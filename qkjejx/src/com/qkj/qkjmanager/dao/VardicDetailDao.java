package com.qkj.qkjmanager.dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iweb.sys.AbstractDAO;
import org.iweb.sys.dao.KpiDAO;
import org.iweb.sys.dao.UserDeptDAO;
import org.iweb.sys.domain.IndexDetail;
import org.iweb.sys.domain.UserDept;

import com.qkj.qkjmanager.domain.Vartic;
import com.qkj.qkjmanager.domain.VarticDetail;

public class VardicDetailDao extends AbstractDAO {

	public int add(Object parameters) {
		return (int) super.add("qkjmanager_addVarticDetails", parameters);
	}

	public List list(Map<String, Object> map) {
		return super.list("qkjmanager_getVarticDetails", map);
	}
	
	public List listh(Map<String, Object> map) {
		return super.list("qkjmanager_getVarticDetailsh", map);
	}
	
	public List Checklist(Map<String, Object> map) {
		return super.list("qkjmanager_getCheckVarticDetails", map);
	}
	
	public List listmdy(Map<String, Object> map) {
		return super.list("scorebymdy", map);
	}
	
	public List listmdyupdate(Map<String, Object> map) {
		return super.list("scorebymdyupdate", map);
	}
	
	public List scorebykpibydept(Map<String, Object> map) {
		return super.list("scorebykpibydept", map);
	}
	
	public Object get(Object uuid) {
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
	
	public void savestate(String value) {
		super.save("qkjmanager_mdyVarticDetailsBystate", value);
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
	
	
	public void updateScore(Integer check_ym,String achCode,VarticDetail vd,Integer depttype){
		//查询本月所有有可能修改的记录
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("check_ym", check_ym);
		map.put("position_dept", achCode);
		if(depttype==3){
			map.put("depttype", depttype);
		}else{
			map.put("kpitype", depttype);
		}
		
		List<VarticDetail> desu=new ArrayList<>();
		desu=listmdyupdate(map);
		if(desu.size()>0){
			VarticDetail v=new VarticDetail();
			//修改取部门评分(部门不会有取班组分)
			Double w=0.00;//权重
			if(desu.size()>0&&depttype==2){
				for(VarticDetail first : desu){
					w=getWei(first.getCheck_index(),first.getCheck_goal(),first.getCheck_score());
					if(vd.getKpi()!=null && vd.getKpi().equals(first.getKpi())){//取部门同向kpi
						updateScoreBykpi(first,vd,w);
						if(first.getAuser()==null){
							map.clear();//查询是否有调用此部门的班组分数的人并修改
							map.put("check_ym", check_ym);
							map.put("position_dept", first.getAuser_code());
							map.put("depttype", 3);
							List<VarticDetail> sons=new ArrayList<>();
							sons=listmdyupdate(map);
							
							for(VarticDetail son : sons){
								w=getWei(son.getCheck_index(),son.getCheck_goal(),son.getCheck_score());
								//取班组分
								updateScoreBydept(son,check_ym,w);
							}
						}
					}
				}
			}
			
			//修改取班组评分
			if(desu.size()>0&&depttype==3){
				for(VarticDetail first : desu){
					w=getWei(first.getCheck_index(),first.getCheck_goal(),first.getCheck_score());
					//取班组分
					updateScoreBydept(first,check_ym,w);
				}
			}
			
		}
	}
	
	
	//计算权重
	public Double getWei(Integer Index,Double goal,Double score){
		double w=0.00;
		if(Index==0){
			w=1.00;//权重 经理取部门分
		}else{
			if((goal!=null && goal==0.00) || (score!=null && score==0.00)){
				//查询权重
				KpiDAO kpd=new KpiDAO();
				IndexDetail ind=new IndexDetail();
				ind=(IndexDetail) kpd.get(Index);
				w=ind.getWeight();
			}
			else{
			w=goal/score;
			
			}
			DecimalFormat df = new DecimalFormat("#.###");    
	        w=Double.valueOf(df.format(w));
	        System.out.println("权重"+w); 
		}
		
		return w;
	}
	
	//修改同项kpi分数
	public void updateScoreBykpi(VarticDetail v,VarticDetail vd,Double w){
		VardicDetailDao dao = new VardicDetailDao();
		VardicDao zdao=new VardicDao();
		v.setCheck_score(vd.getCheck_score());
		v.setCheck_goal(vd.getCheck_score()*w);
		dao.save(v);
		zdao.saveBycheck(v.getScore_id().toString());
	}
	
	public void updateScoreBydept(VarticDetail v,Integer check_ym,Double w){
		Map<String, Object> map = new HashMap<String, Object>();
		VardicDao zdao=new VardicDao();
		map.clear();
		if(w==1){//经理级
			map.put("jingliuser", v.getAuser());
		}else{
			map.put("userid", v.getAuser());
		}
		map.put("check_ym", check_ym);
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
	}

	public int getResultCount() {
		return super.getResultCount();
	}
}
