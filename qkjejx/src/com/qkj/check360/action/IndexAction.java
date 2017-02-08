package com.qkj.check360.action;

import java.io.IOException;
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
import org.apache.struts2.json.JSONUtil;
import org.iweb.sys.ContextHelper;
import org.iweb.sys.Parameters;
import org.iweb.sys.ToolsUtil;
import org.iweb.sys.dao.UserDAO;
import org.iweb.sys.domain.User;
import org.iweb.sys.mail.MailSenderInfo;
import org.iweb.sys.mail.SysMail;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.check360.dao.CheckDao;
import com.qkj.check360.dao.IndexCheckDAO;
import com.qkj.check360.dao.IndexDAO;
import com.qkj.check360.domain.Capacity;
import com.qkj.check360.domain.CheckType360;
import com.qkj.check360.domain.Factors;
import com.qkj.check360.domain.Index;
import com.qkj.check360.domain.Index360;
import com.qkj.check360.domain.IndexCheck;
import com.qkj.check360.domain.Remark360;

public class IndexAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(IndexAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private IndexDAO dao = new IndexDAO();
	private IndexCheckDAO cdao = new IndexCheckDAO();
	private CheckDao checkdao=new  CheckDao();
	private MailSenderInfo mailInfo = new MailSenderInfo();
	private Index index;
	private List<Index> indexs;
	private IndexCheck indexCheck;
	private List<IndexCheck> indexChecks;
	private User user;
	private Remark360 remark360;
	private List<Remark360> remark360s;
	private Capacity capa;
	private List<Capacity> capas;// 能力
	private Factors fact;
	private List<Factors> facts;
	private List<CheckType360> types;//考核类别（上级、下级。。）
	private List<Index360> crits;//类别（年度晋升转正。。。）
	private Index360 index360;
	
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;360管理";
	private Integer isCheckUser=0;
	
	public Integer getIsCheckUser() {
		return isCheckUser;
	}

	public void setIsCheckUser(Integer isCheckUser) {
		this.isCheckUser = isCheckUser;
	}

	public Index360 getIndex360() {
		return index360;
	}

	public void setIndex360(Index360 index360) {
		this.index360 = index360;
	}

	public List<Index360> getCrits() {
		return crits;
	}

	public void setCrits(List<Index360> crits) {
		this.crits = crits;
	}

	public List<CheckType360> getTypes() {
		return types;
	}

	public void setTypes(List<CheckType360> types) {
		this.types = types;
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

	public Remark360 getRemark360() {
		return remark360;
	}

	public void setRemark360(Remark360 remark360) {
		this.remark360 = remark360;
	}

	public List<Remark360> getRemark360s() {
		return remark360s;
	}

	public void setRemark360s(List<Remark360> remark360s) {
		this.remark360s = remark360s;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public IndexCheck getIndexCheck() {
		return indexCheck;
	}

	public void setIndexCheck(IndexCheck indexCheck) {
		this.indexCheck = indexCheck;
	}

	public List<IndexCheck> getIndexChecks() {
		return indexChecks;
	}

	public void setIndexChecks(List<IndexCheck> indexChecks) {
		this.indexChecks = indexChecks;
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
		// ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_PLAN");
		try {
			map.clear();
			if (index == null) {
				index = new Index();
			}

			map.putAll(ToolsUtil.getMapByBean(index));
			map.putAll(ContextHelper.getDefaultRequestMap4Page());
			this.setPageSize(Integer.parseInt(map.get(Parameters.Page_Size_Str)
					.toString()));
			this.setIndexs(dao.list(map));
			this.setRecCount(dao.getResultCount());
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;考核管理列表";
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	public String listCapa() throws Exception {
		try {
			map.clear();
			if (index == null) {
				index = new Index();
			}
			map.putAll(ToolsUtil.getMapByBean(index));
			map.putAll(ContextHelper.getDefaultRequestMap4Page());
			this.setPageSize(Integer.parseInt(map.get(Parameters.Page_Size_Str)
					.toString()));
			//this.setCapas(dao.listCapa(map));
			this.setCapas(dao.listCapabyUser(map));
			this.setRecCount(dao.getResultCount());
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;列表";
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
				this.setIndex(null);
				setMessage("你没有选择任何操作!");
			} else if ("add".equals(viewFlag)) {
				this.setIndex(null);
			} else if ("mdy".equals(viewFlag)) {
				map.clear();
				map.put("uuid", user.getUuid());
				UserDAO ud = new UserDAO();
				this.setUser((User) ud.get(user.getUuid()));
				
				map.clear();
				map.put("user_id", user.getUuid());
				this.setIndexChecks(cdao.list(map));
				
				//查询类别
				this.setTypes(dao.listType(null));
			} else {
				this.setIndex(null);
				setMessage("无操作类型!");
			}
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!load 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!load 读取数据错误:", e);
		}
		return SUCCESS;
	}

	public String loadCapa() throws Exception {
		try {
			if (null == viewFlag) {
				this.setCapa(null);
				setMessage("你没有选择任何操作!");
			} else if ("add".equals(viewFlag)) {
				this.setCapa(null);
				this.setCrits(checkdao.listCrit(null));
			} else if ("mdy".equals(viewFlag)) {
				if (!(capa == null || capa.getUser_id() == null)) {
					this.setCrits(checkdao.listCrit(null));
					map.clear();
					map.put("user_id", capa.getUser_id());
					this.setCapas(dao.listCapa(map));
					this.setRemark360s(checkdao.listremark(map));
					if(capas.size()>0){
						this.setCapa(capas.get(0));
						List<String> cs = new ArrayList<>();
						Set<String> setcs = new HashSet<>();
						for(Capacity c:capas){
							setcs.add(c.getUuid().toString());
						}
						cs.addAll(setcs);
						map.clear();
						map.put("capacity_id", cs);
						this.setFacts(dao.listFact(map));
						if(facts.size()>0){
							cs = new ArrayList<>();
							setcs = new HashSet<>();
							for(Factors f:facts){
								setcs.add(f.getUuid().toString());
							}
							cs.addAll(setcs);
							map.clear();
							map.put("factors_id", cs);
							this.setIndexs(dao.list(map));
						}
					}
				} else {
					this.setCapa(null);
				}
			} else {
				this.setCapa(null);
				setMessage("无操作类型!");
			}
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!load 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!load 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	public String addCapa() throws Exception {
		try {
			capa.setAdd_time(new Date());
			capa.setAdd_user(ContextHelper.getUserLoginUuid());
			dao.addCapa(capa);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		return SUCCESS;
	}
	
	public String saveCapa() throws Exception {
		try {
			capa.setUser_id(capa.getUser_id().trim());
			capa.setUser_name(capa.getUser_name().trim());
			dao.saveCapa(capa);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!save 数据更新失败:", e);
			throw new Exception(this.getClass().getName() + "!save 数据更新失败:", e);
		}
		return SUCCESS;
	}
	
	public String delCapa() throws Exception {
		try {
			dao.deleteCapa(capa);
			setMessage("删除成功!ID=" + fact.getUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}
	
	//删除被考核人
	public String delCapaUser() throws Exception {
		try {
			//查询考核人的一级考核标题
			if(capa!=null && capa.getUser_id()!=null){
				/*map.put("user_id", capa.getUser_id());
				this.setCapas(dao.listCapa(map));
				List<String> cs = new ArrayList<>();
				if(capas.size()>0){
					Set<String> setcs = new HashSet<>();
					for(Capacity c:capas){
						setcs.add(c.getUuid().toString());
					}
					cs.addAll(setcs);
				}*/
				//删除一级
				dao.deleteCapaByUser(capa);
				//查询第二级id
				/*map.clear();
				map.put("capacity_id", cs);
				this.setFacts(dao.listFact(map));*/
				//删除二级
				 
			}
			setMessage("删除成功!ID=" + fact.getUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}
	
	public String addFact() throws Exception {
		try {
			dao.addFact(fact);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		return SUCCESS;
	}
	
	public String saveFact() throws Exception {
		try {
			dao.saveFact(fact);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!save 数据更新失败:", e);
			throw new Exception(this.getClass().getName() + "!save 数据更新失败:", e);
		}
		return SUCCESS;
	}
	
	public String delFact() throws Exception {
		try {
			dao.deleteFact(fact);
			setMessage("删除成功!ID=" + fact.getUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}
	
	public String add() throws Exception {
		// ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_PLAN_ADD");
		try {
			dao.add(index);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		return SUCCESS;
	}
	
	public String addremark() throws Exception {
		try {
			checkdao.addremark(remark360);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		return SUCCESS;
	}

	public String addCheck() throws Exception {
		// ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_PLAN_ADD");
		try {
			cdao.add(indexCheck);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		return SUCCESS;
	}

	public String save() throws Exception {
		try {
			dao.save(index);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!save 数据更新失败:", e);
			throw new Exception(this.getClass().getName() + "!save 数据更新失败:", e);
		}
		return SUCCESS;
	}

	public String saveCheck() throws Exception {
		try {
			cdao.save(indexCheck);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!save 数据更新失败:", e);
			throw new Exception(this.getClass().getName() + "!save 数据更新失败:", e);
		}
		return SUCCESS;
	}

	public String del() throws Exception {
		// ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_PLAN_DEL");
		try {
			dao.delete(index);
			setMessage("删除成功!ID=" + index.getUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}
	
	public String delremark() throws Exception {
		try {
			checkdao.delremark(remark360);
			setMessage("删除成功!ID=" + remark360.getUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}

	public String delCheck() throws Exception {
		// ContextHelper.isPermit("SYS_QKJMANAGER_BASIS_PLAN_DEL");
		try {
			cdao.delete(indexCheck);
			setMessage("删除成功!ID=" + indexCheck.getUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}

	public void send_email() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String ds = request.getParameter("params");
			String typeid = request.getParameter("type");
			// 查询所有考核人发送邮件
			this.setIndexChecks(cdao.list(null));
			send(ds, typeid);

		} catch (Exception e) {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.getWriter().print("1");
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}

	}
	
	public void pro_send_email() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String ds = request.getParameter("params");
			String typeid = request.getParameter("type");
			String checku=request.getParameter("check_user");
			// 查询所有考核人发送邮件
			String[] users=checku.split(",");
			List<String> cs = new ArrayList<>();
			if(users.length>0){
				Set<String> setcs = new HashSet<>();
				for(int i=0;i<users.length;i++){
					setcs.add(users[i]);
				}
				cs.addAll(setcs);
			}
			map.clear();
			map.put("users", cs);
			this.setIndexChecks(cdao.list(map));
			send(ds, typeid);

		} catch (Exception e) {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.getWriter().print("1");
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}

	}
	
	public String ori_send_email() throws Exception {
		try {
			// 查询所有考核人发送邮件 要据考核人查询信息
			String[] users=capa.getUser_id().split(",");
			List<String> cs = new ArrayList<>();
			if(users.length>0){
				Set<String> setcs = new HashSet<>();
				for(int i=0;i<users.length;i++){
					setcs.add(users[i]);
				}
				cs.addAll(setcs);
			}
			map.clear();
			map.put("users", cs);
			this.setIndexChecks(cdao.list(map));
			send(index360.getUuid().toString(), capa.getCrit_id().toString());
			if(this.getIsCheckUser()==1){
				this.setMessage("失败.");
			}else if(this.getIsCheckUser()==2){
				this.setMessage("考核人没有考核项.");
			}else{
				this.setMessage("成功.");
			}
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		return SUCCESS;
	}

	private void send(String ds, String typeid) throws IOException {
		try {
			//查询与typeid相符的考核人
			map.clear();
			map.put("crit_id", typeid);
			this.setCapas(dao.listCapabyUser(map));
			if (indexChecks.size() > 0 && capas.size()>0) {
				for (IndexCheck ic : indexChecks) {
					List<String> toa = new ArrayList<String>();
					for(Capacity ca:capas){
						String [] uid=ca.getUser_id().split(",");
						for(int i=0;i<uid.length;i++){
							if(ic.getUser_id()!=null&&ic.getUser_id().equals(uid[i])){
								toa.add(ic.getCheck_user_email());
								String sub = ic.getCheck_user_name() + "考核";
								StringBuffer url = new StringBuffer();
								//本地
								//url.append("http://localhost:8080");
								//正式
								url.append("http://djx.51qkj.com/");
								url.append("/check360/check_360ScoreLoad?");
								url.append("ic.uuid="+ic.getUuid());
								url.append("&index360.uuid="+ ds);
								url.append("&capa.crit_id="+ typeid);
								url.append("&viewFlag=mdy");
								StringBuffer content = new StringBuffer();
								content.append("<div><div style='margin-left:4%;'>");
								content.append("<p style='color:red;'>");
								content.append("您好：</p>");
								content.append("<p style='text-indent: 2em;'>您正在使用360考核功能，请点击下面的链接完成考核。</p>");
								content.append("<p style='text-indent: 2em;display: block;word-break: break-all;'>");
								content.append("链接地址：<a style='text-decoration: none;' href='"
										+ url.toString()
										+ "'>"
										+ url.toString()
										+ "</a></p>");
								content.append("</div>");
								content.append("<ul style='color: rgb(169, 169, 189);font-size: 18px;'>");
								/*content.append("<li>为了保障您帐号的安全，该链接有效期为12小时。</li>");
								content.append("<li>该链接只能使用一次，请周知。</li>");*/
								content.append("<li>如果该链接无法点击，请直接复制以上网址到浏览器地址栏中访问。</li>");
								content.append("<li>请您妥善保管，此邮件无需回复。</li>");
								content.append("</ul>");
								content.append("</div>");
								SysMail.sendHtml(toa, sub, content.toString());
							}
						}
					}
					
				}

				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.getWriter().print("0");
			} else {
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.getWriter().print("2");
				this.setIsCheckUser(2);
			}
		} catch (Exception e) {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.getWriter().print("1");
			this.setIsCheckUser(1);
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		
	}

}
