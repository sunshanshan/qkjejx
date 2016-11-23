package com.qkj.qkjmanager.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.iweb.sys.ContextHelper;
import org.iweb.sys.Parameters;
import org.iweb.sys.dao.UserDAO;
import org.iweb.sys.domain.User;
import org.iweb.sys.domain.UserDept;
import org.iweb.sys.domain.UserLoginInfo;

import com.opensymphony.xwork2.ActionContext;
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
	private Check check;

	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}

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
			//查询所有 直属下属
			UserDAO ud=new UserDAO();
			List<User> pus=new ArrayList();
			map.clear();
			map.put("parent_user", ContextHelper.getUserLoginUuid());
			pus=ud.list(map);
			
			//开始查询已考核记录逻辑
			map.clear();
			if (vardic == null) {
				vardic = new Vartic();
			}
			ContextHelper.SimpleSearchMap4Page("SYS_QKJMANAGER_VERTICLIST", map, vardic, viewFlag);
			this.setPageSize(Integer.parseInt(map.get(Parameters.Page_Size_Str).toString()));
			
			//查询可以考核的年月
			check=dao.check_cym();
			if(check!=null){//只查询打开的已考核记录
				map.put("check_ym", check.getUuid());
			}
			
			//将下属放入map
			List<String> dlistallo = new ArrayList<>();
			Set<String> dsetallo = new HashSet<>();
			if(pus.size()>0){
				for(int s=0;s<pus.size();s++){
					dsetallo.add(pus.get(s).getUuid());
				}
				if (dsetallo.size() > 0) {
					dlistallo.addAll(dsetallo);
					map.put("userid", dlistallo);//多权限可查询多个子部门
				}
			}
			
			getManDept();//查询管理的部门
			this.setVardics(dao.list(map));
			this.setRecCount(dao.getResultCount());
			
			//需要考核的人员
			map.clear();
			map.put("userid", dlistallo);//多权限可查询多个子部门
			if (vardic == null) {
				vardic = new Vartic();
			}
			
			if(check!=null){
		        map.put("check_ym", check.getUuid());
		        map.put("typea", 1);//成绩表中所有纵向向考核已经考核过的去掉
				map.put("isdept", 1);//纵向考核
				map.put("parent_user", ContextHelper.getUserLoginUuid());//当前登录人
				map.put("check_user", ContextHelper.getUserLoginUuid());
				map.put("ex", 0);
				if(map.containsKey("userid") && map.get("userid")!=null && map.get("userid")!="" && dlistallo.size()>0){
					getManUser();//查询已经考核的人员放入map
					if(map.containsKey("checkedUser")){
						this.setCvardics(dao.Checklist(map));
					}
				}
				
				ActionContext context = ActionContext.getContext();  
				HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);  
				HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);  
				UserLoginInfo ulf = new UserLoginInfo();
				ulf=(UserLoginInfo) request.getSession().getAttribute(Parameters.UserLoginInfo_Session_Str);
				List<UserDept> uds=new ArrayList<>();
				uds=ulf.getUds();
				List<String> dlistall = new ArrayList<>();
				Set<String> dsetall = new HashSet<>();
				Set<String> dall = new HashSet<>();
				if(uds.size()>0){
					for(int s=0;s<uds.size();s++){
						if(uds.get(s).getIscheckdept()!=null&& uds.get(s).getIscheckdept()!=null&&uds.get(s).getIscheckdept()==1){
							dsetall.add(uds.get(s).getDept_code());
						}else{
							dsetall.add("o");
						}
						if(uds.get(s).getRoles().contains("2016072516956868")){//部门考核权限
							dall.add(uds.get(s).getDept_code());
						}
					}
					if (dsetall.size() > 0) {
						dlistall.addAll(dsetall);
						map.put("parent_dept", dlistall);//多权限可查询多个子部门
					}
					if(dall.size()>0){
						List<String> dlall = new ArrayList<>();
						dlall.addAll(dall);
						map.put("chdept", dlall);
					}
				}
				//linshid=dao.Checklistbydept(map);//所有可考核部门
				this.setCvardicsd(dao.Checklistbydept(map));//部门
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
			//修改纵向总分
			dao.saveBycheck(vardic.getUuid().toString());
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
	 * 查询管理的直属部门
	 * @throws Exception
	 */
	public void getManDept()throws Exception{
		ActionContext context = ActionContext.getContext();  
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);  
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);  
		UserLoginInfo ulf = new UserLoginInfo();
		ulf=(UserLoginInfo) request.getSession().getAttribute(Parameters.UserLoginInfo_Session_Str);
		List<UserDept> uds=new ArrayList<>();
		uds=ulf.getUds();
		List<String> dlistall = new ArrayList<>();
		Set<String> dsetall = new HashSet<>();
		Set<String> dall = new HashSet<>();
		if(uds.size()>0){
			for(int s=0;s<uds.size();s++){
				if(uds.get(s).getIscheckdept()!=null&& uds.get(s).getIscheckdept()!=null&&uds.get(s).getIscheckdept()==1){
					dsetall.add(uds.get(s).getDept_code());
				}else{
					dsetall.add("o");
				}
				if(uds.get(s).getRoles().contains("2016072516956868")){//部门考核权限
					dall.add(uds.get(s).getDept_code());
				}
			}
			if (dsetall.size() > 0) {
				dlistall.addAll(dsetall);
				map.put("parent_dept", dlistall);//多权限可查询多个子部门
			}
			if(dall.size()>0){
				List<String> dlall = new ArrayList<>();
				dlall.addAll(dall);
				map.put("chdept", dlall);
			}
		}
	}
	
	/**
	 * 查询已考核的下属
	 * @throws Exception
	 */
	public void getManUser()throws Exception{
		List<Vartic> vs=new ArrayList<Vartic>();
		vs=dao.UserChecked(map);
		List<String> dlistall = new ArrayList<>();
		Set<String> dsetall = new HashSet<>();
		if(vs.size()>0){
			for(int i=0;i<vs.size();i++){
				if(vs.get(i).getAcheck_user()!=null && vs.get(i).getAcheck_user()!=""){
					dsetall.add(vs.get(i).getAcheck_user());
				}
			}
			
			if (dsetall.size() > 0) {
				dlistall.addAll(dsetall);
				map.put("checkedUser", dlistall);
			}
			
		}
	}
	
}
