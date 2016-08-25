package com.qkj.qkjmanager.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.iweb.sys.ContextHelper;
import org.iweb.sys.Parameters;
import org.iweb.sys.dao.DepartmentDAO;
import org.iweb.sys.dao.KpiDAO;
import org.iweb.sys.dao.UserDAO;
import org.iweb.sys.domain.Department;
import org.iweb.sys.domain.IndexDetail;
import org.iweb.sys.domain.User;
import org.iweb.sys.domain.UserLoginInfo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qkj.basics.domain.Check;
import com.qkj.qkjmanager.dao.VardicDao;
import com.qkj.qkjmanager.dao.VardicDetailDao;
import com.qkj.qkjmanager.domain.Vartic;
import com.qkj.qkjmanager.domain.VarticDetail;

public class TransverseDetailAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(VardicDetailAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private VardicDetailDao dao = new VardicDetailDao();
	private VardicDao zdao=new VardicDao();
	private VarticDetail vd;
	private List<VarticDetail> vds;
	private List<IndexDetail> kpis;
	private Department depa;
	public Department getDepa() {
		return depa;
	}

	public void setDepa(Department depa) {
		this.depa = depa;
	}

	private Vartic vardic;
	private List<IndexDetail> ids;
	private User user;
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String aArray;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;纵向考核管理";
	private Check check;

	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}

	public List<IndexDetail> getKpis() {
		return kpis;
	}

	public void setKpis(List<IndexDetail> kpis) {
		this.kpis = kpis;
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
		ContextHelper.isPermit("SYS_QKJMANAGER_HORILIST");
		try {
			map.clear();
			if (vardic == null) {
				vardic = new Vartic();
			}else{
				//查询打开的审核日期
				check=zdao.check_cym();
				ActionContext context = ActionContext.getContext();  
				HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);  
				UserLoginInfo ulf = new UserLoginInfo();
				ulf=(UserLoginInfo) request.getSession().getAttribute(Parameters.UserLoginInfo_Session_Str);
				map.clear();
				map.put("dept_code", vardic.getU_code());
				map.put("isdept", 0);//向横考核
				map.put("ftype", 1);//向横考核
				map.put("check_deptcode", ContextHelper.getUserLoginDept());
				map.put("check_post", ulf.getPosition());
				
		        map.remove("check_ym");
		        map.put("check_ym", check.getUuid());
				IndexDetail id=new IndexDetail();
				KpiDAO kpid=new KpiDAO();
				this.setIds(kpid.list(map));
				DepartmentDAO dp=new DepartmentDAO();
				map.remove("type");
				this.setDepa((Department)dp.list(map).get(0));
			}
			
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;纵向考核列表";
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	/**
	 * 查询职务横向kpi
	 * @return
	 * @throws Exception
	 */
	public String listbyUser() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_HORILIST");
		try {
			map.clear();
			if (vardic == null) {
				vardic = new Vartic();
			}else{
				//查询打开的审核日期
				check=zdao.check_cym();
				ActionContext context = ActionContext.getContext();  
				HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);  
				UserLoginInfo ulf = new UserLoginInfo();
				ulf=(UserLoginInfo) request.getSession().getAttribute(Parameters.UserLoginInfo_Session_Str);
				//查询被考核人的职务
				String userid=vardic.getU_id();
				UserDAO ud=new UserDAO();
				this.setUser((User)ud.get(userid));
				
				map.clear();
				//map.put("", vardic.getU_id());
				map.put("positionid", user.getPosition());
				map.put("isdept", 0);//向横考核
				map.put("ftype", 1);//不取部门及班组分数
				map.put("check_deptcode", ContextHelper.getUserLoginDept());//考核人部门
				map.put("check_post", ulf.getPosition());//考核人职务
				
		        map.remove("check_ym");
		        map.put("check_ym", check.getUuid());
		        
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
					ActionContext context = ActionContext.getContext();  
					HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);  
					HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);  
					UserLoginInfo ulf = new UserLoginInfo();
					ulf=(UserLoginInfo) request.getSession().getAttribute(Parameters.UserLoginInfo_Session_Str);
					map.clear();
					map.put("score_id", vardic.getUuid());
					map.put("typea", 0);
					if (!ContextHelper.isAdmin()) {
						map.put("check_deptcode", ContextHelper.getUserLoginDept());//登录人部门是考核人部门
				        map.put("check_post", ulf.getPosition());//登录人职务是考核人职务
					}
					
					this.setVds(dao.list(map));
					//查询此部门其它kpi
					/*KpiDAO kpidao=new KpiDAO();
					map.clear();
					map.put("sp", 1);
					map.put("dept_code", vardic.getAcheck_usercode());
					this.setKpis(kpidao.list(map));*/
					
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
	
	public String loadUser() throws Exception {
		try {
			if (null == viewFlag) {
				this.setVardic(null);
				setMessage("你没有选择任何操作!");
			} else if ("add".equals(viewFlag)) {
				this.setVardic(null);
			} else if ("mdy".equals(viewFlag)) {
				if (!(vardic == null || vardic.getUuid() == null)) {
					this.setVardic((Vartic) zdao.get(vardic.getUuid()));
					ActionContext context = ActionContext.getContext();  
					HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);  
					HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);  
					UserLoginInfo ulf = new UserLoginInfo();
					ulf=(UserLoginInfo) request.getSession().getAttribute(Parameters.UserLoginInfo_Session_Str);
					map.clear();
					map.put("score_id", vardic.getUuid());
					map.put("typea", 0);
					if (!ContextHelper.isAdmin()) {
						map.put("check_deptcode", ContextHelper.getUserLoginDept());//登录人部门是考核人部门
				        map.put("check_post", ulf.getPosition());//登录人职务是考核人职务
					}
					this.setVds(dao.list(map));
					//查询此部门其它kpi
					/*KpiDAO kpidao=new KpiDAO();
					map.clear();
					map.put("sp", 1);
					map.put("dept_code", vardic.getAcheck_usercode());
					this.setKpis(kpidao.list(map));*/
					
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
		ContextHelper.isPermit("SYS_QKJMANAGER_HORILIST_ADD");
		try {
			dao.startTransaction();
			/**
			 * 填加主表
			 */
			//横向考核时如果本月此部门已经有考核记录则不在添加主表只添加子表
			if(vardic!=null && vardic.getCheck_ym()!=null){
				List<Vartic> vs=new ArrayList();
				map.put("acheck_usercode", vardic.getAcheck_usercode());
				map.put("isaunull", 0);
				map.put("check_ym", vardic.getCheck_ym());
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
			
			
			/**
			 * 填加子表
			 */
			Double sum=0.00;
			if(aArray!=null && vardic!=null && vardic.getUuid()!=null){
				aArray=aArray.replace(" ", "");
				String aa[]=aArray.split(";");
				for(int i=0;i<aa.length;i++){
					vd=new VarticDetail();
					vd.setScore_id(vardic.getUuid());
					String index=aa[i];
					String arr[] = index.split(",");
					if(index.length()>=11){
						if(dao.repeatKpi(vd.getScore_id(), Integer.parseInt(arr[1]))==true){
							if(arr[1]!=null && arr[1]!="")vd.setCheck_index(Integer.parseInt(arr[1]));
							if(arr[2]!=null && arr[2]!="")vd.setCheck_score(Double.parseDouble(arr[2]));
							if(arr[3]!=null && arr[3]!="")vd.setCheck_goal(Double.parseDouble(arr[3]));
							vd.setCheck_date(new Date());
							vd.setCheck_user(ContextHelper.getUserLoginUuid());
							vd.setCheck_usercode(ContextHelper.getUserLoginDept());
							vd.setTypea(0);//横向
							KpiDAO k=new KpiDAO();
							IndexDetail id=new IndexDetail();
							id=(IndexDetail) k.get(vd.getCheck_index());
							vd.setDepttype(id.getType());
							vd.setPosition_dept(id.getPosition_dept());
							vd.setKpi(id.getKpi());
							sum=sum+Double.parseDouble(arr[3]);
							//vd.setCheck_index(Double.parseDouble(arr[0]));
							dao.add(vd);
						}
					}
					
				}
			}else{
				log.error(this.getClass().getName() + "!没有数据分数:");
				throw new Exception(this.getClass().getName() + "!没有数据分数:");
			}
			
			/**
			 * 修改主表分数
			 */
			//修改纵向总分
			//vardic.setCheck_score(sum);
			zdao.saveBycheck(vardic.getUuid().toString());
			
			//查询部门考核是否完成：完成修改状态（查询部门kpi数量是否与已有记录相同）
			List<IndexDetail> a =new ArrayList();
			KpiDAO kd=new KpiDAO();
			map.clear();
			map.put("dept_code", vardic.getAcheck_usercode());
			a=kd.list(map);
			List<VarticDetail> vsd=new ArrayList();
			map.clear();
			map.put("score_id", vardic.getUuid());
			vsd=dao.list(map);
			if(a.size()==vsd.size()){//修改状态为完成
				zdao.saveFin(vardic);
			}
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
	
	
	public String addUser() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_HORILIST_ADD");
		try {
			dao.startTransaction();
			/**
			 * 填加主表
			 */
			//横向考核时如果本月此部门已经有考核记录则不在添加主表只添加子表
			if(vardic!=null && vardic.getCheck_ym()!=null){
				List<Vartic> vs=new ArrayList();
				map.put("acheck_usercode", vardic.getAcheck_usercode());
				map.put("acheck_user", vardic.getAcheck_user());
				map.put("check_ym", vardic.getCheck_ym());
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
			
			
			/**
			 * 填加子表
			 */
			Double sum=0.00;
			if(aArray!=null && vardic!=null && vardic.getUuid()!=null){
				aArray=aArray.replace(" ", "");
				String aa[]=aArray.split(";");
				for(int i=0;i<aa.length;i++){
					vd=new VarticDetail();
					vd.setScore_id(vardic.getUuid());
					String index=aa[i];
					String arr[] = index.split(",");
					if(index.length()>=11){
						if(dao.repeatKpi(vd.getScore_id(), Integer.parseInt(arr[1]))==true){
							if(arr[1]!=null && arr[1]!="")vd.setCheck_index(Integer.parseInt(arr[1]));
							if(arr[2]!=null && arr[2]!="")vd.setCheck_score(Double.parseDouble(arr[2]));
							if(arr[3]!=null && arr[3]!="")vd.setCheck_goal(Double.parseDouble(arr[3]));
							vd.setCheck_date(new Date());
							vd.setCheck_user(ContextHelper.getUserLoginUuid());
							vd.setCheck_usercode(ContextHelper.getUserLoginDept());
							vd.setTypea(0);//横向
							sum=sum+Double.parseDouble(arr[3]);
							KpiDAO k=new KpiDAO();
							IndexDetail id=new IndexDetail();
							id=(IndexDetail) k.get(vd.getCheck_index());
							vd.setDepttype(id.getType());
							vd.setPosition_dept(id.getPosition_dept());
							vd.setKpi(id.getKpi());
							//vd.setCheck_index(Double.parseDouble(arr[0]));
							dao.add(vd);
						}
					}
					
				}
			}else{
				log.error(this.getClass().getName() + "!没有数据分数:");
				throw new Exception(this.getClass().getName() + "!没有数据分数:");
			}
			
			/**
			 * 修改主表分数
			 */
			
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
		ContextHelper.isPermit("SYS_QKJMANAGER_HORILIST_MDY");
		try {
			dao.startTransaction();
			this.setVardic((Vartic) zdao.get(vd.getScore_id()));
			dao.save(vd);
			
			/**
			 * 修改总分数 横+纵的部门
			 */
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
	
	public String saveD() throws Exception {
		//ContextHelper.isPermit("SYS_QKJMANAGER_VERTICLIST_MDY");
		try {
			dao.startTransaction();
			this.setVardic((Vartic) zdao.get(vd.getScore_id()));
			dao.save(vd);
			/**
			 * 修改主表分数横加纵
			 */
			zdao.saveBycheck(vardic.getUuid().toString());
			//查询修改的kpi
			VarticDetail vds=new VarticDetail();
			vds=(VarticDetail) dao.get(vd.getUuid());
			//查询取此部门分数的sonscore
			if(vardic.getAcheck_usercode()!=null && vardic.getCheck_ym()!=null && vds.getKpi()!=null){
				map.clear();
				map.put("depttype", "2");
				map.put("kpi", vds.getKpi());
				map.put("position_dept", vardic.getAcheck_usercode());
				map.put("check_ym", vardic.getCheck_ym());
				List<VarticDetail> des=new ArrayList<>();
				des=dao.listmdy(map);
				if(des.size()>0){
					for(int i=0;i<des.size();i++){
						VarticDetail v=new VarticDetail();
						v=des.get(i);
						Double w=v.getCheck_goal()/v.getCheck_score();//权重
						v.setCheck_score(vd.getCheck_score());
						v.setCheck_goal(vd.getCheck_score()*w);
						dao.save(v);
						zdao.saveBycheck(v.getScore_id().toString());
					}
				}
			}
			
			
			//查询取班组的分数的人
			if(vardic.getAcheck_usercode()!=null && vardic.getCheck_ym()!=null){
			map.clear();
			map.put("depttype", "3");
			map.put("type3", vardic.getAcheck_usercode());
			map.put("check_ym", vardic.getCheck_ym());
			
			List<VarticDetail> desu=new ArrayList<>();
			desu=dao.listmdy(map);
			if(desu.size()>0){
				for(int i=0;i<desu.size();i++){
					VarticDetail v=new VarticDetail();
					v=desu.get(i);
					Double w=v.getCheck_goal()/v.getCheck_score();//权重
					//查询此人所有部门的成绩
					map.clear();
					map.put("userid", v.getAuser());
					map.put("check_ym", vardic.getCheck_ym());
					List<VarticDetail> v3=new ArrayList<>();
					v3=dao.scorebykpibydept(map);
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
					dao.save(v);
					zdao.saveBycheck(v.getScore_id().toString());
				}
			}
			}
			//修改经理级分数
			//zdao.savejl(vardic);
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
		ContextHelper.isPermit("SYS_QKJMANAGER_HORILIST_DEL");
		try {
			dao.del(vardic);
			setMessage("删除成功!ID=" + vardic.getUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}
	

}
