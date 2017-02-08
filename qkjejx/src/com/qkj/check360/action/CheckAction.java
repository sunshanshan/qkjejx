package com.qkj.check360.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iweb.sys.ContextHelper;
import org.iweb.sys.Parameters;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.check360.dao.CheckDao;
import com.qkj.check360.dao.IndexCheckDAO;
import com.qkj.check360.dao.IndexDAO;
import com.qkj.check360.domain.Capacity;
import com.qkj.check360.domain.Index360;
import com.qkj.check360.domain.IndexCheck;

public class CheckAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(CheckAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private CheckDao dao = new CheckDao();
	private Index360 check;
	private List<Index360> checks;
	private List<Index360> crits;
	private List<IndexCheck> ics;
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;考核管理";
	
	public List<IndexCheck> getIcs() {
		return ics;
	}

	public void setIcs(List<IndexCheck> ics) {
		this.ics = ics;
	}

	public List<Index360> getCrits() {
		return crits;
	}

	public void setCrits(List<Index360> crits) {
		this.crits = crits;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Index360 getCheck() {
		return check;
	}

	public void setCheck(Index360 check) {
		this.check = check;
	}

	public List<Index360> getChecks() {
		return checks;
	}

	public void setChecks(List<Index360> checks) {
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
		ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_ASSETLIST360");
		try {
			map.clear();
			if (check == null) {
				check = new Index360();
			}
			
			ContextHelper.setSearchDeptPermit4Search("SYS_QKJMANAGER_BASIS_ASSETLIST360", map, "apply_depts", "apply_user");
			ContextHelper.SimpleSearchMap4Page("SYS_QKJMANAGER_BASIS_ASSETLIST360", map, check, viewFlag);
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
				this.setCrits(dao.listCrit(null));
			} else if ("mdy".equals(viewFlag)) {
				if (!(check == null || check.getUuid() == null)) {
					this.setCheck((Index360) dao.get(check.getUuid()));
					this.setCrits(dao.listCrit(null));
					//查询类别相同的考题
					List<Capacity> cas=new ArrayList<Capacity>();
					map.clear();
					map.put("crit_id", check.getCrit_id());
					IndexDAO idd=new IndexDAO();
					cas=idd.listCapabyUser(map);
					List<String> cs = new ArrayList<>();
					if(cas.size()>0){
						Set<String> setcs = new HashSet<>();
						for(Capacity c:cas){
							String[] userid=c.getUser_id().split(",");
							if(userid.length>0){
								for(int i=0;i<userid.length;i++){
									setcs.add(userid[i]);
								}
							}
						}
						cs.addAll(setcs);
					}
					//查询未完成考核的人
					IndexCheckDAO icd=new IndexCheckDAO();
					map.clear();
					map.put("checkym_id", check.getUuid());
					map.put("crit_user", cs);
					if(cs.size()>0){
						this.setIcs(icd.listst(map));
					}
					
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
		ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_ASSETLIST360");
		try {
			check.setAdd_user(ContextHelper.getUserLoginUuid());
			check.setAdd_time(new Date());
			check.setLm_user(ContextHelper.getUserLoginUuid());
			check.setLm_time(new Date());
			dao.add(check);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		return SUCCESS;
	}

	public String save() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_ASSETLIST360");
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
		ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_ASSETLIST360");
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
