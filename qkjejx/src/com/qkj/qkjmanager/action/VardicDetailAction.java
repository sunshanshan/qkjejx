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
import org.iweb.sys.dao.DepartmentDAO;
import org.iweb.sys.dao.KpiDAO;
import org.iweb.sys.dao.UserDAO;
import org.iweb.sys.domain.Department;
import org.iweb.sys.domain.IndexDetail;
import org.iweb.sys.domain.User;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.basics.dao.CheckDao;
import com.qkj.basics.domain.Check;
import com.qkj.qkjmanager.dao.VardicDao;
import com.qkj.qkjmanager.dao.VardicDetailDao;
import com.qkj.qkjmanager.domain.Vartic;
import com.qkj.qkjmanager.domain.VarticDetail;

public class VardicDetailAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(VardicDetailAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private VardicDetailDao dao = new VardicDetailDao();
	private VardicDao zdao=new VardicDao();
	private VarticDetail vd;
	private List<VarticDetail> vds;
	private Check check;
	
	private Vartic vardic;
	private List<IndexDetail> ids;
	private User user;
	private Department dept;
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String aArray;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;纵向考核管理";

	
	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public List<IndexDetail> getIds() {
		return ids;
	}

	public void setIds(List<IndexDetail> ids) {
		this.ids = ids;
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

	
	public VarticDetail getVd() {
		return vd;
	}

	public void setVd(VarticDetail vd) {
		this.vd = vd;
	}

	public List<VarticDetail> getVds() {
		return vds;
	}

	public void setVds(List<VarticDetail> vds) {
		this.vds = vds;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getaArray() {
		return aArray;
	}

	public void setaArray(String aArray) {
		this.aArray = aArray;
	}

	public String list() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_VERTICLIST");
		try {
			map.clear();
			if (vardic == null) {
				vardic = new Vartic();
			}else{
				//查询打开的审核日期
				CheckDao c=new CheckDao();
				List<Check> checks=new ArrayList();
				map.clear();
				map.put("state", 0);//状态打开的审核日期
				checks=c.list(map);
				if(checks.size()>0){
					check=checks.get(0);
				}
				//查询考核人的用户信息
				String userid=vardic.getU_id();
				UserDAO ud=new UserDAO();
				this.setUser((User)ud.get(userid));
				
				//查询部门下面职务的kpi
				map.clear();
//				map.put("dept_code", vardic.getU_code());
				//map.put("isdept", 1);//纵向考核
				map.put("positionid", user.getPosition());//职务
				//map.put("type", 1);//查询职务的kpi
				IndexDetail id=new IndexDetail();
				KpiDAO kpid=new KpiDAO();
				this.setIds(kpid.listbyp(map));
			
			}
			
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;纵向考核列表";
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	
	public String listbydept() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_VERTICLIST");
		try {
			map.clear();
			if (vardic == null) {
				vardic = new Vartic();
			}else{
				//查询打开的审核日期
				CheckDao c=new CheckDao();
				List<Check> checks=new ArrayList();
				map.clear();
				map.put("state", 0);//状态打开的审核日期
				checks=c.list(map);
				if(checks.size()>0){
					check=checks.get(0);
				}
				//查询考核部门信息
				String u_code=vardic.getU_code();
				UserDAO ud=new UserDAO();
				DepartmentDAO d=new DepartmentDAO();
				map.clear();
				map.put("dept_code", u_code);
				this.setDept((Department) d.list(map).get(0));
				
				//查询部门下面纵向考核的kpi
				map.clear();
				map.put("dept_code", vardic.getU_code());
				//map.put("isdept", 1);//纵向考核
				IndexDetail id=new IndexDetail();
				KpiDAO kpid=new KpiDAO();
				this.setIds(kpid.list(map));
			
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
			} else if ("mdy".equals(viewFlag)) {
				if (!(vardic == null || vardic.getUuid() == null)) {
					this.setVardic((Vartic) zdao.get(vardic.getUuid()));
					map.clear();
					map.put("score_id", vardic.getUuid());
					this.setVds(dao.list(map));
					
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
			dao.startTransaction();
			/**
			 * 填加主表
			 */
			if(vardic.getAcheck_user()==null){//部门考核
				//考核时如果本月此部门已经有考核记录则不在添加主表只添加子表
				if(vardic!=null){
					cym();//查询考核日期
					if(check!=null){
						List<Vartic> vs=new ArrayList();
						map.remove("check_ym");
				        map.put("check_ym", check.getUuid());
						map.put("acheck_usercode", vardic.getAcheck_usercode());
						map.put("isaunull", 0);
						vs=zdao.list(map);
						if(vs.size()>0){//说明本月此部门已经有考核记录
							vardic=vs.get(0);//一个部门一个月考核主表记录数只有一条
						}else{
							vardic.setCheck_date(new Date());
							vardic.setLm_user(ContextHelper.getUserLoginUuid());
							vardic.setLm_time(new Date());
							vardic.setCheck_score(0.00);
							vardic.setCheck_date(new Date());
							zdao.add(vardic);
						}
					}
					
				}
			}else{//人员考核
				//vardic.setCheck_user(ContextHelper.getUserLoginUuid());
				cym();//查询考核日期
				if(check!=null){
					List<Vartic> vs=new ArrayList();
			        map.remove("check_ym");
			        map.put("check_ym", check.getUuid());
					map.put("acheck_usercode", vardic.getAcheck_usercode());
					map.put("acheck_user", vardic.getAcheck_user());
					vs=zdao.list(map);
					if(vs.size()>0){//说明本月此部门已经有考核记录
						vardic=vs.get(0);//一个部门一个月考核主表记录数只有一条
					}else{
						vardic.setCheck_date(new Date());
						vardic.setLm_user(ContextHelper.getUserLoginUuid());
						vardic.setLm_time(new Date());
						vardic.setCheck_score(0.00);
						vardic.setCheck_date(new Date());
						zdao.add(vardic);
					}
				}
				
				
			}
			
			
			/**
			 * 填加子表
			 */
			Double sum=0.00;
			if(aArray!=null){
				aArray=aArray.replace(" ", "");
				String aa[]=aArray.split(";");
				for(int i=0;i<aa.length;i++){
					vd=new VarticDetail();
					vd.setScore_id(vardic.getUuid());
					String index=aa[i];
					String arr[] = index.split(",");
					if(index.length()>=11){
						if(arr[1]!=null && arr[1]!="")vd.setCheck_index(Integer.parseInt(arr[1]));
						if(arr[2]!=null && arr[2]!="")vd.setCheck_score(Double.parseDouble(arr[2]));
						if(arr[3]!=null && arr[3]!="")vd.setCheck_goal(Double.parseDouble(arr[3]));
						vd.setCheck_date(new Date());
						vd.setCheck_user(ContextHelper.getUserLoginUuid());
						vd.setCheck_usercode(ContextHelper.getUserLoginDept());
						sum=sum+Double.parseDouble(arr[3]);
						vd.setTypea(1);
						//vd.setCheck_index(Double.parseDouble(arr[0]));
						dao.add(vd);
					}
					
				}
			}else{
				log.error(this.getClass().getName() + "!没有数据分数:");
				throw new Exception(this.getClass().getName() + "!没有数据分数:");
			}
			
			/**
			 * 修改主表分数横加纵
			 */
			//修改纵向总分
			zdao.saveBycheck(vardic.getUuid().toString());
			dao.commitTransaction();
			//addProcess("CLOSEORDER_ADD", "新增结案提货单", ContextHelper.getUserLoginUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		finally {
			dao.endTransaction();
		}
		return SUCCESS;
	}

	public String save() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_VERTICLIST_MDY");
		try {
			dao.startTransaction();
			this.setVardic((Vartic) zdao.get(vd.getScore_id()));
			dao.save(vd);
			
			/**
			 * 修改主表分数横加纵
			 */
			//修改纵向总分
				zdao.saveBycheck(vardic.getUuid().toString());
			
			dao.commitTransaction();
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!save 数据更新失败:", e);
			throw new Exception(this.getClass().getName() + "!save 数据更新失败:", e);
		}
		finally {
			dao.endTransaction();
		}
		return SUCCESS;
	}

	public String del() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_VERTICLIST_DEL");
		try {
			dao.del(vardic);
			setMessage("删除成功!ID=" + vardic.getUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	private void cym(){
		//查询打开的审核日期
		CheckDao c=new CheckDao();
		List<Check> checks=new ArrayList();
		map.clear();
		map.put("state", 0);//状态打开的审核日期
		checks=c.list(map);
		if(checks.size()>0){
			check=checks.get(0);
		}
	}
	
}
