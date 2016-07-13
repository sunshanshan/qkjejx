package com.qkj.qkjmanager.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iweb.sys.ContextHelper;
import org.iweb.sys.Parameters;
import org.iweb.sys.dao.KpiDAO;
import org.iweb.sys.dao.UserDAO;
import org.iweb.sys.domain.IndexDetail;
import org.iweb.sys.domain.User;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.basics.dao.CheckDao;
import com.qkj.basics.domain.Check;
import com.qkj.qkjmanager.dao.VardicDao;
import com.qkj.qkjmanager.dao.VardicDetailDao;
import com.qkj.qkjmanager.domain.Vartic;
import com.qkj.qkjmanager.domain.VarticDetail;

public class VardicAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(VardicAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private VardicDao dao = new VardicDao();
	private Vartic vardic;
	private List<Vartic> vardics;
	private List<Vartic> cvardics;
	private List<Vartic> cvardicsd;
	private List<Check> checks;
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;纵向考核管理";

	public List<Vartic> getCvardicsd() {
		return cvardicsd;
	}

	public void setCvardicsd(List<Vartic> cvardicsd) {
		this.cvardicsd = cvardicsd;
	}

	public List<Check> getChecks() {
		return checks;
	}

	public void setChecks(List<Check> checks) {
		this.checks = checks;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	public Vartic getVardic() {
		return vardic;
	}

	public void setVardic(Vartic vardic) {
		this.vardic = vardic;
	}

	public List<Vartic> getVardics() {
		return vardics;
	}

	public void setVardics(List<Vartic> vardics) {
		this.vardics = vardics;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(String viewFlag) {
		this.viewFlag = viewFlag;
	}

	public int getRecCount() {
		return recCount;
	}

	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public List<Vartic> getCvardics() {
		return cvardics;
	}

	public void setCvardics(List<Vartic> cvardics) {
		this.cvardics = cvardics;
	}

	public String list() throws Exception {
		try {
			map.clear();
			if (vardic == null) {
				vardic = new Vartic();
			}
			
			ContextHelper.setSearchDeptPermit4Search("SYS_QKJMANAGER_VERTICLIST", map, "apply_depts", "apply_user");
			ContextHelper.SimpleSearchMap4Page("SYS_QKJMANAGER_VERTICLIST", map, vardic, viewFlag);
			this.setPageSize(Integer.parseInt(map.get(Parameters.Page_Size_Str).toString()));

	        if(map.get("cym")!=null){
	        	SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM");
		        String d = sdf.format(vardic.getCym());
	        map.remove("cym");
	        map.put("cym", d);
	        }
			map.put("typea", "1");
			map.put("check_user", ContextHelper.getUserLoginUuid());
			this.setVardics(dao.list(map));//已经考核的记录
			this.setRecCount(dao.getResultCount());
			//需要考核的人员
			map.clear();
			if (vardic == null) {
				vardic = new Vartic();
			}
			//查询打开的审核日期
			CheckDao c=new CheckDao();
			List<Check> checks=new ArrayList();
			map.clear();
			map.put("state", 0);//状态打开的审核日期
			checks=c.list(map);
			if(checks.size()>0){
				Check check=new Check();
				check=checks.get(0);
		        map.put("check_ym", check.getUuid());
		        map.put("typea", 1);//成绩表中所有纵向向考核已经考核过的去掉
				map.put("isdept", 1);//纵向考核
				map.put("parent_user", ContextHelper.getUserLoginUuid());//当前登录人
				map.put("parent_dept", ContextHelper.getUserLoginDept());
				map.put("check_user", ContextHelper.getUserLoginUuid());
				map.put("ex", 0);
				List<Vartic> linshi=new ArrayList();
				linshi=dao.Checklist(map);//所有可考核人
				List<Vartic> linshid=new ArrayList();
				linshid=dao.Checklistbydept(map);//所有可考核部门
				//this.setCvardicsd(dao.Checklistbydept(map));//部门
				if(linshi.size()>0){//去除有部门取值且取值的部门kpi考核日期内还未评分的
					cvardics=new ArrayList<>();
					for(int i=0;i<linshi.size();i++){
						Boolean flag=true;
						Vartic lc=new Vartic();
						lc=linshi.get(i);
						//根据用户id查询type=2的kpi
						UserDAO ud=new UserDAO();
						map.clear();
						map.put("uuid", lc.getU_id());
						map.put("type", "2");
						List<User> kpis=new ArrayList<>();
						kpis=ud.listBypro(map);//查询此人职务的kpit type=2或3的
						if(kpis.size()>0){
				        	for(int j=0;j<kpis.size();j++){
				        		map.clear();
								map.put("check_ym", check.getUuid());
								map.put("kpiid", kpis.get(j).getKpi());
								VardicDetailDao  vdd=new VardicDetailDao();
								List<VarticDetail> vdds=new ArrayList<>();//查询这些部门kpi已经有分数则加入list,找不到则不加入list
								vdds=vdd.list(map);
								if(vdds.size()>0){
								}else{
									flag=false;
									break;
								}
				        	}
				        	
						}else{
							flag=true;
						}
						if(flag==true){
			        		cvardics.add(linshi.get(i));
			        	}
						
					}
				}
				
				if(linshid.size()>0){//去除有部门取值且取值的部门kpi考核日期内还未评分的
					cvardicsd=new ArrayList<>();
					for(int i=0;i<linshid.size();i++){
						Boolean flag=true;
						Vartic lc=new Vartic();
						lc=linshid.get(i);
						KpiDAO ud=new KpiDAO();
						map.clear();
						map.put("dept_code", lc.getD_code());
						map.put("type2", "2");
						List<IndexDetail> kpis=new ArrayList<>();
						kpis=ud.list(map);//查询此人部门的kpitype=2
						if(kpis.size()>0){
				        	for(int j=0;j<kpis.size();j++){
				        		map.clear();
								map.put("check_ym", check.getUuid());
								map.put("kid", kpis.get(j).getUuid());
								VardicDetailDao  vdd=new VardicDetailDao();
								List<VarticDetail> vdds=new ArrayList<>();//查询这些部门kpi已经有分数则加入list,找不到则不加入list
								vdds=vdd.list(map);
								if(vdds.size()>0){
								}else{
									flag=false;
									break;
								}
				        	}
				        	
						}else{
							flag=true;
						}
						if(flag==true){
							cvardicsd.add(linshid.get(i));
			        	}
						
					}
				}
				
				
				//System.out.println(cvardics.size());
				CheckDao cd =new CheckDao();
				map.clear();
				map.put("state", 0);
				map.put("a", new Date());
				this.setChecks(cd.list(map));
				
			}else{
				System.out.println("没有可以考核的人或部门");
			}
			
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;纵向考核列表";
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		return SUCCESS;
	}

	public String relist() throws Exception {
		return SUCCESS;
	}

	public String load() throws Exception {
		try {
			if (null == viewFlag) {
				this.setVardic(null);
				setMessage("你没有选择任何操作!");
			} else if ("add".equals(viewFlag)) {
				this.setVardic(null);
				CheckDao cd =new CheckDao();
				map.clear();
				map.put("state", 0);
				map.put("d", new Date());
				this.setChecks(cd.list(map));
			} else if ("mdy".equals(viewFlag)) {
				if (!(vardic == null || vardic.getUuid() == null)) {
					this.setVardic((Vartic) dao.get(vardic.getUuid()));
				} else {
					this.setVardic(null);
				}
			} else {
				this.setVardic(null);
				setMessage("无操作类型!");
			}
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!load 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!load 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	public String add() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_VERTICLIST_ADD");
		try {
			
			//vardic.setCheck_user(ContextHelper.getUserLoginUuid());
			vardic.setCheck_date(new Date());
			vardic.setLm_user(ContextHelper.getUserLoginUuid());
			vardic.setLm_time(new Date());
			dao.add(vardic);
			//addProcess("CLOSEORDER_ADD", "新增结案提货单", ContextHelper.getUserLoginUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		return SUCCESS;
	}

	public String save() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_VERTICLIST_MDY");
		try {
			vardic.setLm_user(ContextHelper.getUserLoginUuid());
			vardic.setLm_time(new Date());
			dao.save(vardic);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!save 数据更新失败:", e);
			throw new Exception(this.getClass().getName() + "!save 数据更新失败:", e);
		}
		return SUCCESS;
	}
	
	public String savev() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_VERTICLIST_MDY");
		try {
			vardic.setLm_user(ContextHelper.getUserLoginUuid());
			vardic.setLm_time(new Date());
			dao.savev(vardic);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!save 数据更新失败:", e);
			throw new Exception(this.getClass().getName() + "!save 数据更新失败:", e);
		}
		return SUCCESS;
	}

	public String del() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_VERTICLIST_DEL");
		try {
			dao.startTransaction();
			dao.del(vardic);
			VardicDetailDao vd=new VardicDetailDao();
			VarticDetail vdc=new VarticDetail();
			vdc.setScore_id(vardic.getUuid());
			vd.delformV(vdc);
			setMessage("删除成功!ID=" + vardic.getUuid());
			dao.commitTransaction();
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}finally {
			dao.endTransaction();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询纵向考核年月中未有考核记录的直属下级及部门
	 * @return
	 * @throws Exception
	 */
	public String checklist() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_VERTICLIST");
		try {
			//查询纵向考核人
			map.clear();
			if (vardic == null) {
				vardic = new Vartic();
			}
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM");
	        String d = sdf.format(vardic.getCheck_ym());
	        map.remove("check_ym");
	        map.put("check_ym", d);
	        map.put("typea", 1);//成绩表中所有纵向向考核已经考核过的去掉
			map.put("isdept", 1);//纵向考核
			map.put("parent_user", ContextHelper.getUserLoginUuid());//当前登录人
			map.put("parent_dept", ContextHelper.getUserLoginDept());
			map.put("check_user", ContextHelper.getUserLoginUuid());
			map.put("ex", 0);
			List<Vartic> linshi=new ArrayList();
			linshi=dao.Checklist(map);//所有可考核人
			List<Vartic> linshid=new ArrayList();
			linshid=dao.Checklistbydept(map);//所有可考核部门
			//this.setCvardicsd(dao.Checklistbydept(map));//部门
			if(linshi.size()>0){//去除有部门取值且取值的部门kpi考核日期内还未评分的
				cvardics=new ArrayList<>();
				for(int i=0;i<linshi.size();i++){
					Boolean flag=true;
					Vartic lc=new Vartic();
					lc=linshi.get(i);
					//根据用户id查询type=2的kpi
					UserDAO ud=new UserDAO();
					map.clear();
					map.put("uuid", lc.getU_id());
					map.put("type", "2");
					List<User> kpis=new ArrayList<>();
					kpis=ud.listBypro(map);//查询此人职务的kpit type=2或3的
					if(kpis.size()>0){
			        	for(int j=0;j<kpis.size();j++){
			        		map.clear();
							map.put("check_ym", d);
							map.put("kpiid", kpis.get(j).getKpi());
							VardicDetailDao  vdd=new VardicDetailDao();
							List<VarticDetail> vdds=new ArrayList<>();//查询这些部门kpi已经有分数则加入list,找不到则不加入list
							vdds=vdd.list(map);
							if(vdds.size()>0){
							}else{
								flag=false;
								break;
							}
			        	}
			        	
					}else{
						flag=true;
					}
					if(flag==true){
		        		cvardics.add(linshi.get(i));
		        	}
					
				}
			}
			
			if(linshid.size()>0){//去除有部门取值且取值的部门kpi考核日期内还未评分的
				cvardicsd=new ArrayList<>();
				for(int i=0;i<linshid.size();i++){
					Boolean flag=true;
					Vartic lc=new Vartic();
					lc=linshid.get(i);
					KpiDAO ud=new KpiDAO();
					map.clear();
					map.put("dept_code", lc.getD_code());
					map.put("type2", "2");
					List<IndexDetail> kpis=new ArrayList<>();
					kpis=ud.list(map);//查询此人部门的kpitype=2
					if(kpis.size()>0){
			        	for(int j=0;j<kpis.size();j++){
			        		map.clear();
							map.put("check_ym", d);
							map.put("kpiid", kpis.get(j).getUuid());
							VardicDetailDao  vdd=new VardicDetailDao();
							List<VarticDetail> vdds=new ArrayList<>();//查询这些部门kpi已经有分数则加入list,找不到则不加入list
							vdds=vdd.list(map);
							if(vdds.size()>0){
							}else{
								flag=false;
								break;
							}
			        	}
			        	
					}else{
						flag=true;
					}
					if(flag==true){
						cvardicsd.add(linshid.get(i));
		        	}
					
				}
			}
			
			
			//System.out.println(cvardics.size());
			CheckDao cd =new CheckDao();
			map.clear();
			map.put("state", 0);
			map.put("a", new Date());
			this.setChecks(cd.list(map));
			this.setRecCount(dao.getResultCount());
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;添加考核";
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!checklist 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!checklist 读取数据错误:", e);
		}
		return SUCCESS;
	}

	
}
