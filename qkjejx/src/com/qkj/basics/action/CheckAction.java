package com.qkj.basics.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iweb.sys.ContextHelper;
import org.iweb.sys.Parameters;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.basics.dao.CheckDao;
import com.qkj.basics.domain.Check;

public class CheckAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(CheckAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private CheckDao dao = new CheckDao();
	private Check check;
	private List<Check> checks;
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;考核管理";


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}

	public List<Check> getChecks() {
		return checks;
	}

	public void setChecks(List<Check> checks) {
		this.checks = checks;
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
		ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_ASSETLIST");
		try {
			map.clear();
			if (check == null) {
				check = new Check();
			}
			
			ContextHelper.setSearchDeptPermit4Search("SYS_QKJMANAGER_BASIS_ASSETLIST", map, "apply_depts", "apply_user");
			ContextHelper.SimpleSearchMap4Page("SYS_QKJMANAGER_BASIS_ASSETLIST", map, check, viewFlag);
			this.setPageSize(Integer.parseInt(map.get(Parameters.Page_Size_Str).toString()));
			this.setChecks(dao.list(map));
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
				this.setCheck(null);
				setMessage("你没有选择任何操作!");
			} else if ("add".equals(viewFlag)) {
				this.setCheck(null);
			} else if ("mdy".equals(viewFlag)) {
				if (!(check == null || check.getUuid() == null)) {
					this.setCheck((Check) dao.get(check.getUuid()));
				} else {
					this.setCheck(null);
				}
			} else {
				this.setCheck(null);
				setMessage("无操作类型!");
			}
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!load 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!load 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	public String add() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_ASSETLIST_ADD");
		try {
			check.setAdd_user(ContextHelper.getUserLoginUuid());
			check.setAdd_time(new Date());
			check.setLm_user(ContextHelper.getUserLoginUuid());
			check.setLm_time(new Date());
			dao.add(check);
			//addProcess("CLOSEORDER_ADD", "新增结案提货单", ContextHelper.getUserLoginUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		return SUCCESS;
	}

	public String save() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_ASSETLIST_MDY");
		try {
			check.setLm_user(ContextHelper.getUserLoginUuid());
			check.setLm_time(new Date());
			dao.save(check);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!save 数据更新失败:", e);
			throw new Exception(this.getClass().getName() + "!save 数据更新失败:", e);
		}
		return SUCCESS;
	}

	public String del() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_ASSETLIST_DEL");
		try {
			dao.del(check);
			setMessage("删除成功!ID=" + check.getUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}

	
}
