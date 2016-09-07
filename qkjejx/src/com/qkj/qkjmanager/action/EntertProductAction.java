package com.qkj.qkjmanager.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iweb.sys.ContextHelper;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.qkjmanager.dao.EntertProductDAO;
import com.qkj.qkjmanager.domain.EntertMember;
import com.qkj.qkjmanager.domain.Entertain;
import com.qkj.qkjmanager.domain.EntertainProduct;

public class EntertProductAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(EntertProductAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private EntertProductDAO dao=new EntertProductDAO();
	private Entertain entert;
	private List<Entertain> enterts;
	private EntertainProduct entertProduct;
	private List<EntertainProduct> entertProducts;
	private EntertMember entertMem;
	private List<EntertMember> entertMems;
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;招待用酒列表";
	
	
	
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public EntertMember getEntertMem() {
		return entertMem;
	}
	public void setEntertMem(EntertMember entertMem) {
		this.entertMem = entertMem;
	}
	public List<EntertMember> getEntertMems() {
		return entertMems;
	}
	public void setEntertMems(List<EntertMember> entertMems) {
		this.entertMems = entertMems;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Entertain getEntert() {
		return entert;
	}
	public void setEntert(Entertain entert) {
		this.entert = entert;
	}
	public List<Entertain> getEnterts() {
		return enterts;
	}
	public void setEnterts(List<Entertain> enterts) {
		this.enterts = enterts;
	}
	public EntertainProduct getEntertProduct() {
		return entertProduct;
	}
	public void setEntertProduct(EntertainProduct entertProduct) {
		this.entertProduct = entertProduct;
	}
	public List<EntertainProduct> getEntertProducts() {
		return entertProducts;
	}
	public void setEntertProducts(List<EntertainProduct> entertProducts) {
		this.entertProducts = entertProducts;
	}
	
	public String list() throws Exception {
		ContextHelper.isPermit("QKJ_QKJMANAGE_ENTERTAI");
		try {
			map.clear();
			if (entert == null) entert = new Entertain();
			//ContextHelper.setSearchDeptPermit4Search("QKJ_QKJMANAGE_ENTERTAI", map, "apply_depts", "apply_user");
			ContextHelper.SimpleSearchMap4Page("QKJ_QKJMANAGE_ENTERTAI", map, entert, viewFlag);
			this.setPageSize(ContextHelper.getPageSize(map));
			this.setCurrPage(ContextHelper.getCurrPage(map));
			this.setEntertProducts(dao.list(map));
			this.setRecCount(dao.getResultCount());
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;列表";
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		
		
		return SUCCESS;
	}
	
	public String add() throws Exception {
		try {
			dao.add(entertProduct);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		return SUCCESS;
	}

	public String del() throws Exception {
		try {
			dao.delete(entertProduct);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}
	
	public String addMem() throws Exception {
		try {
			dao.addmem(entertMem);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		return SUCCESS;
	}

	public String delMem() throws Exception {
		try {
			dao.deletemem(entertMem);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}
	

}
