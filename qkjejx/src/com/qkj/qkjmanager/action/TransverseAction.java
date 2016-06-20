package com.qkj.qkjmanager.action;

import java.text.SimpleDateFormat;
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
import org.iweb.sys.dao.UserDeptDAO;
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

public class TransverseAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(VardicAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private VardicDao dao = new VardicDao();
	private Vartic vardic;
	private List<Vartic> vardics;
	private List<Vartic> cvardics;
	private List<Check> checks;
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;纵向考核管理";


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
		ContextHelper.isPermit("SYS_QKJMANAGER_HORILIST");
		try {
			map.clear();
			if (vardic == null) {
				vardic = new Vartic();
			}
			
			ContextHelper.setSearchDeptPermit4Search("SYS_QKJMANAGER_HORILIST", map, "apply_depts", "apply_user");
			ContextHelper.SimpleSearchMap4Page("SYS_QKJMANAGER_HORILIST", map, vardic, viewFlag);
			this.setPageSize(Integer.parseInt(map.get(Parameters.Page_Size_Str).toString()));
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM");
	        String d = sdf.format(vardic.getCheck_ym());
	        if(map.get("check_ym")!=null){
	        map.remove("check_ym");
	        map.put("check_ym", d);
	        }
			map.put("typea", "0");
			map.put("check_user", ContextHelper.getUserLoginUuid());
			this.setVardics(dao.list(map));
			this.setRecCount(dao.getResultCount());
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;横向考核列表";
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
				CheckDao cd =new CheckDao();
				map.clear();
				map.put("state", 0);
				map.put("a", new Date());
				this.setChecks(cd.list(map));
				this.setVardic(null);
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
		ContextHelper.isPermit("SYS_QKJMANAGER_HORILIST_ADD");
		try {
			vardic.setTypea(0);//横向考核
			vardic.setCheck_user(ContextHelper.getUserLoginUuid());
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
		ContextHelper.isPermit("SYS_QKJMANAGER_HORILIST_MDY");
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

	public String del() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_HORILIST_DEL");
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
	 * 查询考核年月中未有考核记录的横向考核部门人员
	 * @return
	 * @throws Exception
	 */
	public String checklist() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_HORILIST");
		try {
			map.clear();
			if (vardic == null) {
				vardic = new Vartic();
			}
			map.put("fdept", ContextHelper.getUserLoginDept());//当前登录人部门
			ContextHelper.SimpleSearchMap4Page("SYS_QKJMANAGER_HORILIST", map, vardic, viewFlag);
			this.setPageSize(Integer.parseInt(map.get(Parameters.Page_Size_Str).toString()));
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM");
	        String d = sdf.format(vardic.getCheck_ym());
	        map.remove("check_ym");
	        map.put("check_ym", d);
	        UserLoginInfo ulf = new UserLoginInfo();
	        ActionContext context = ActionContext.getContext();  
			HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);  
			HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);  
			ulf=(UserLoginInfo) request.getSession().getAttribute(Parameters.UserLoginInfo_Session_Str);
			map.put("userid", ulf.getUuid());
	        map.put("dept_code", ulf.getDept_code());
	        
	        /*List<UserDept> u=new ArrayList<>();
	        String us=ContextHelper.getUserLoginUuid();
	        UserDeptDAO udd=new UserDeptDAO();
	        Map<String, Object> map2 = new HashMap<String, Object>();
	        map2.clear();
	        map2.put("user_id", us);
	        u=udd.list(map2);
	        List<String> dlist = new ArrayList<>();
	        if(u.size()>0){
	        	Set<String> dset = new HashSet<>();
	        	for(int i=0;i<u.size();i++){
	        		String t1=u.get(i).getPosition();
	        		dset.add(t1);
	        	}
	        	dlist.addAll(dset);
	        }
	        map.put("p", dlist);*/
	        map.put("typea", 0);//成绩表中所有横向考核已经考核过的去掉
			map.put("isdept", 0);//向横考核
			this.setCvardics(dao.Checklist1(map));
			System.out.println(cvardics.size());
			this.setRecCount(dao.getResultCount());
			CheckDao cd =new CheckDao();
			map.clear();
			map.put("state", 0);
			map.put("a", new Date());
			this.setChecks(cd.list(map));
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;添加考核";
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!checklist 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!checklist 读取数据错误:", e);
		}
		return SUCCESS;
	}
}
