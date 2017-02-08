package com.qkj.check360.action;

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
import org.iweb.sys.ToolsUtil;
import org.iweb.sys.domain.User;
import org.iweb.sys.mail.MailSenderInfo;
import org.iweb.sys.mail.SysMail;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.basics.domain.Check;
import com.qkj.check360.dao.CheckDao;
import com.qkj.check360.dao.IndexCheckDAO;
import com.qkj.check360.dao.IndexDAO;
import com.qkj.check360.domain.Assess;
import com.qkj.check360.domain.Capacity;
import com.qkj.check360.domain.Factors;
import com.qkj.check360.domain.Index;
import com.qkj.check360.domain.IndexCheck;
import com.qkj.check360.domain.Remark360;
import com.qkj.check360.domain.Score360;

public class AssessAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(AssessAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private IndexDAO dao = new IndexDAO();
	private Assess ass;
	private List<Assess> asses;// 能力
	
	private Capacity capa;
	private List<Capacity> capas;// 能力
	private List<Capacity> capausers;
	private Factors fact;
	private List<Factors> facts;
	private Index index;
	private List<Index> indexs;
	
	
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;360管理";

	public Assess getAss() {
		return ass;
	}

	public void setAss(Assess ass) {
		this.ass = ass;
	}

	public List<Assess> getAsses() {
		return asses;
	}

	public void setAsses(List<Assess> asses) {
		this.asses = asses;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Capacity getCapa() {
		return capa;
	}

	public void setCapa(Capacity capa) {
		this.capa = capa;
	}

	public List<Capacity> getCapas() {
		return capas;
	}

	public void setCapas(List<Capacity> capas) {
		this.capas = capas;
	}

	public Factors getFact() {
		return fact;
	}

	public void setFact(Factors fact) {
		this.fact = fact;
	}

	public List<Factors> getFacts() {
		return facts;
	}

	public void setFacts(List<Factors> facts) {
		this.facts = facts;
	}

	public Index getIndex() {
		return index;
	}

	public void setIndex(Index index) {
		this.index = index;
	}

	public List<Index> getIndexs() {
		return indexs;
	}

	public void setIndexs(List<Index> indexs) {
		this.indexs = indexs;
	}

	public List<Capacity> getCapausers() {
		return capausers;
	}

	public void setCapausers(List<Capacity> capausers) {
		this.capausers = capausers;
	}

	public String list() throws Exception {
		try {
			map.clear();
			if (ass == null) {
				ass = new Assess();
			}

			map.putAll(ToolsUtil.getMapByBean(ass));
			map.putAll(ContextHelper.getDefaultRequestMap4Page());
			this.setPageSize(Integer.parseInt(map.get(Parameters.Page_Size_Str)
					.toString()));
			this.setAsses(dao.listAss(map));
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
				this.setAss(null);
				setMessage("你没有选择任何操作!");
			} else if ("add".equals(viewFlag)) {
				this.setAss(null);
				selectAss(null);
			} else if ("mdy".equals(viewFlag)) {
				if (!(ass == null || ass.getUuid() == null)) {
					this.setAss((Assess) dao.getAss(ass.getUuid()));
					selectAss(ass.getCapa_id());
				} else {
					this.setAss(null);
				}
			} else {
				this.setAss(null);
				setMessage("无操作类型!");
			}
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!load 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!load 读取数据错误:", e);
		}
		return SUCCESS;
	}

	public String add() throws Exception {
		// ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_PLAN_ADD");
		try {
			dao.addAss(ass);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		return SUCCESS;
	}
	
	public String save() throws Exception {
		// ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_PLAN_ADD");
		try {
			dao.saveAss(ass);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		return SUCCESS;
	}
	
	public String del() throws Exception {
		// ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_PLAN_DEL");
		try {
			dao.deleteAss(ass);
			setMessage("删除成功!ID=" + ass.getUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}
	
	public void selectAss(Integer c_id) throws Exception {
		if(c_id!=null){
			map.clear();
			map.put("uuid", c_id);
		}
		this.setCapausers(dao.listCapabyUser(map));
		this.setCapas(dao.listCapa(map));
		this.setFacts(dao.listFact(null));
		this.setIndexs(dao.list(null));
	}

}
