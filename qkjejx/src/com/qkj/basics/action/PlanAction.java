package com.qkj.basics.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iweb.sys.ContextHelper;
import org.iweb.sys.Parameters;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.basics.dao.PlanDao;
import com.qkj.basics.domain.Check;
import com.qkj.basics.domain.Plan;

public class PlanAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(PlanAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private PlanDao dao = new PlanDao();
	private Plan plan;
	private List<Plan> plans;
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;计划管理";


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
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

	public String list() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_PLAN");
		try {
			map.clear();
			if (plan == null) {
				plan = new Plan();
			}
			
			ContextHelper.setSearchDeptPermit4Search("SYS_QKJMANAGER_BASIS_PLAN", map, "apply_depts", "apply_user");
			ContextHelper.SimpleSearchMap4Page("SYS_QKJMANAGER_BASIS_PLAN", map, plan, viewFlag);
			this.setPageSize(Integer.parseInt(map.get(Parameters.Page_Size_Str).toString()));
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM");
			if(plan!=null && plan.getPlan_date()!=null){
				String str = sdf.format(plan.getPlan_date());
				map.remove("plan_date");
				map.put("plan_date", str);
			}
			
			this.setPlans(dao.list(map));
			this.setRecCount(dao.getResultCount());
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;考核管理列表";
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
				this.setPlan(null);
				setMessage("你没有选择任何操作!");
			} else if ("add".equals(viewFlag)) {
				this.setPlan(null);
			} else if ("mdy".equals(viewFlag)) {
				if (!(plan == null || plan.getUuid() == null)) {
					this.setPlan((Plan) dao.get(plan.getUuid()));
				} else {
					this.setPlan(null);
				}
			} else {
				this.setPlan(null);
				setMessage("无操作类型!");
			}
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!load 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!load 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	public String add() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_PLAN_ADD");
		try {
			plan.setAdd_dept(ContextHelper.getUserLoginDept());
			plan.setAdd_user(ContextHelper.getUserLoginUuid());
			plan.setAdd_time(new Date());
			plan.setLm_user(ContextHelper.getUserLoginUuid());
			plan.setLm_time(new Date());
			dao.add(plan);
			//addProcess("CLOSEORDER_ADD", "新增结案提货单", ContextHelper.getUserLoginUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		return SUCCESS;
	}

	public String save() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_PLAN_MDY");
		try {
			plan.setLm_user(ContextHelper.getUserLoginUuid());
			plan.setLm_time(new Date());
			dao.save(plan);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!save 数据更新失败:", e);
			throw new Exception(this.getClass().getName() + "!save 数据更新失败:", e);
		}
		return SUCCESS;
	}

	public String del() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_PLAN_DEL");
		try {
			dao.del(plan);
			setMessage("删除成功!ID=" + plan.getUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}

	
}
