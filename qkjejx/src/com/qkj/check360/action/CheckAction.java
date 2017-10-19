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
import org.iweb.sys.ToolsUtil;
import org.iweb.sys.dao.UserDAO;
import org.iweb.sys.domain.User;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.check360.dao.CheckDao;
import com.qkj.check360.dao.IndexCheckDAO;
import com.qkj.check360.dao.IndexDAO;
import com.qkj.check360.domain.Index360;
import com.qkj.check360.domain.IndexCheck;
import com.qkj.check360.domain.MainCa;

public class CheckAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(CheckAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private CheckDao dao = new CheckDao();
	private Index360 check;
	private List<Index360> checks;
	private List<Index360> crits;
	private List<Index360> doubleCrits;
	private List<IndexCheck> ics;
	private List<MainCa> maincas;
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String[] uroles;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;考核管理";
	
	public List<Index360> getDoubleCrits() {
		return doubleCrits;
	}

	public void setDoubleCrits(List<Index360> doubleCrits) {
		this.doubleCrits = doubleCrits;
	}

	public List<MainCa> getMaincas() {
		return maincas;
	}

	public void setMaincas(List<MainCa> maincas) {
		this.maincas = maincas;
	}

	public String[] getUroles() {
		return uroles;
	}

	public void setUroles(String[] uroles) {
		this.uroles = uroles;
	}

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
			IndexDAO maincad = new IndexDAO();
			if (null == viewFlag) {
				this.setCheck(null);
				setMessage("你没有选择任何操作!");
			} else if ("add".equals(viewFlag)) {
				this.setCheck(null);
				this.setCrits(dao.listCrit(null));
				this.setMaincas(maincad.listMain(null));
				this.setDoubleCrits(null);
			} else if ("mdy".equals(viewFlag)) {
				if (!(check == null || check.getUuid() == null)) {
					this.setCheck((Index360) dao.get(check.getUuid()));
					this.setCrits(dao.listCrit(null));
					this.setMaincas(maincad.listMain(null));

					if (!ToolsUtil.isEmpty(check.getCrit_id())) {
						map.clear();
						map.put("uids", check.getCrit_id().split(","));
						this.setDoubleCrits(dao.listCrit(map));
					} else {
						this.setDoubleCrits(null);
					}
					
					
					//查询未完成考核的人
					IndexCheckDAO cdao = new IndexCheckDAO();
					String[] uroles=check.getCrit_id().split(",");//被考核人数组
					List<User> us=new ArrayList<User>();
					if(uroles.length>0){
						UserDAO userd=new UserDAO();
						List<String> cs = new ArrayList<>();
						Set<String> setcs = new HashSet<>();
						
						List<String> pgs = new ArrayList<>();
						Set<String> pg = new HashSet<>();
						
						String grage=null;
						for(int i=0;i<uroles.length;i++){
							if(uroles[i].equals("1")){//总经理
								pg.add(uroles[i]);
							}else if(uroles[i].equals("2")){//副总经理
								pg.add(uroles[i]);
							}else if(uroles[i].equals("3")){//总监
								pg.add(uroles[i]);
							}else if(uroles[i].equals("4")){//经理
								pg.add(uroles[i]);
							}else if(uroles[i].equals("5")){//主管
								pg.add(uroles[i]);
							}else if(uroles[i].equals("10")){//副部及以上
								grage="2";
							}else if(uroles[i].equals("9")){//总监及以上
								grage="3";
							}else if(uroles[i].equals("8")){//经理及以上
								grage="4";
							}else if(uroles[i].equals("7")){//主管及以上
								grage="5";
							}else{//被考核人
								setcs.add(uroles[i]);
							}
						}
						cs.addAll(setcs);
						pgs.addAll(pg);
						map.clear();
						if(cs.size()>0)map.put("uuids", cs);
						if(pgs.size()>0)map.put("pogrades", pgs);
						if(grage!=null)map.put("grade", grage);
						if(map.get("uuids")!=null || map.get("pogrades")!=null || map.get("grade")!=null){
							us=userd.listEmail(map);
						}
						if(us.size()>0){//所有被考核人列表
							List<IndexCheck> iss=new ArrayList<IndexCheck>();
							List<String> users = new ArrayList<>();
							Set<String> u = new HashSet<>();
							for(User user:us){
								//查询被考核人的考核人
								u.add(user.getUuid());
							}
							users.addAll(u);
							
							IndexCheckDAO icd=new IndexCheckDAO();
							map.clear();
							map.put("checkym_id", check.getUuid());
							map.put("crit_user", users);
							this.setIcs(icd.listst(map));
						}
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
			check.setCrit_id(ToolsUtil.Array2String(uroles == null ? new String[] {} : uroles, ","));
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
			check.setCrit_id(ToolsUtil.Array2String(uroles == null ? new String[] {} : uroles, ","));
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
