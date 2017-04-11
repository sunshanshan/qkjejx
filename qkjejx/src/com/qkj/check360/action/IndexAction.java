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
import com.qkj.check360.dao.ScoreDao;
import com.qkj.check360.domain.Capacity;
import com.qkj.check360.domain.CheckType360;
import com.qkj.check360.domain.Factors;
import com.qkj.check360.domain.Index;
import com.qkj.check360.domain.Index360;
import com.qkj.check360.domain.IndexCheck;
import com.qkj.check360.domain.MainCa;
import com.qkj.check360.domain.Remark360;
import com.qkj.check360.domain.Score360;

public class IndexAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(IndexAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private IndexDAO dao = new IndexDAO();
	private IndexCheckDAO cdao = new IndexCheckDAO();
	private MainCa mainca;
	private Index360 check;
	private List<MainCa> maincas;
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
	
	public Index360 getCheck() {
		return check;
	}

	public void setCheck(Index360 check) {
		this.check = check;
	}

	public MainCa getMainca() {
		return mainca;
	}

	public void setMainca(MainCa mainca) {
		this.mainca = mainca;
	}

	public List<MainCa> getMaincas() {
		return maincas;
	}

	public void setMaincas(List<MainCa> maincas) {
		this.maincas = maincas;
	}

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
	
	public String listMain() throws Exception {
		try {
			map.clear();
			if (mainca == null) {
				mainca = new MainCa();
			}
			map.putAll(ToolsUtil.getMapByBean(mainca));
			map.putAll(ContextHelper.getDefaultRequestMap4Page());
			this.setPageSize(Integer.parseInt(map.get(Parameters.Page_Size_Str)
					.toString()));
			this.setMaincas(dao.listMain(map));
			this.setRecCount(dao.getResultCount());
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;列表";
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	public String loadMain() throws Exception {
		try {
			if (null == viewFlag) {
				this.setMainca(null);
				setMessage("你没有选择任何操作!");
			} else if ("add".equals(viewFlag)) {
				this.setMainca(null);
			} else if ("mdy".equals(viewFlag)) {
				if (!(mainca == null || mainca.getUuid() == null)) {
					this.setMainca((MainCa) dao.getMain(mainca.getUuid()));
					map.clear();
					map.put("main_id", mainca.getUuid());
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
					}else{
						this.setFacts(new ArrayList<Factors>());
					}
				} else {
					this.setMainca(null);
				}
			} else {
				this.setMainca(null);
				setMessage("无操作类型!");
			}
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!load 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!load 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	public String addMain() throws Exception {
		try {
			dao.addMain(mainca);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		return SUCCESS;
	}
	
	public String saveMain() throws Exception {
		try {
			dao.saveMain(mainca);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!save 数据更新失败:", e);
			throw new Exception(this.getClass().getName() + "!save 数据更新失败:", e);
		}
		return SUCCESS;
	}
	
	public String delMain() throws Exception {
		try {
			dao.deleteMain(mainca);
			setMessage("删除成功!ID=" + mainca.getUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
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
			this.setCapas(dao.listCapa(map));
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
				if (!(capa == null || capa.getMain_id() == null)) {
					this.setCrits(checkdao.listCrit(null));
					map.clear();
					map.put("main_id", capa.getMain_id());
					this.setCapas(dao.listCapa(map));
					this.setRemark360s(checkdao.listremark(map));
					if(capas.size()>0){
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
			//将被考核人插入被考核人列表
			cdao.addAcheckUser(null);
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
			//将被考核人插入被考核人列表
			cdao.delacheckUser(null);
			setMessage("删除成功!ID=" + indexCheck.getUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}

	public String send_email() throws Exception {
		try {
			if(check!=null&&check.getUuid()!=null){
				this.setCheck((Index360) checkdao.get(check.getUuid()));
				send(check.getUuid(), check.getCrit_id(),null);
			}
			this.setMessage("发送成功！");
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	public void pro_send_email() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String ds = request.getParameter("params");//考核活动
			String typeid = request.getParameter("type");//被考核人数据
			String checku=request.getParameter("check_user");//考核人
			
			send(Integer.parseInt(ds), typeid,checku);

		} catch (Exception e) {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.getWriter().print("1");
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}

	}
	

	private void send(Integer index_id, String crit_id,String check_user) throws IOException {
		try {
			String[] uroles=crit_id.split(",");//被考核人数组
			List<User> us=new ArrayList<User>();
			if(uroles.length>0){
				UserDAO userd=new UserDAO();
				List<String> cs = new ArrayList<>();
				Set<String> setcs = new HashSet<>();
				
				List<String> pgs = new ArrayList<>();
				Set<String> pg = new HashSet<>();
				
				String grage=null;
				for(int i=0;i<uroles.length;i++){
					if(Integer.parseInt(uroles[i])==1){//总经理
						pg.add(uroles[i]);
					}else if(Integer.parseInt(uroles[i])==2){//副总经理
						pg.add(uroles[i]);
					}else if(Integer.parseInt(uroles[i])==3){//总监
						pg.add(uroles[i]);
					}else if(Integer.parseInt(uroles[i])==4){//经理
						pg.add(uroles[i]);
					}else if(Integer.parseInt(uroles[i])==5){//主管
						pg.add(uroles[i]);
					}else if(Integer.parseInt(uroles[i])==10){//副部及以上
						grage="2";
					}else if(Integer.parseInt(uroles[i])==9){//总监及以上
						grage="3";
					}else if(Integer.parseInt(uroles[i])==8){//经理及以上
						grage="4";
					}else if(Integer.parseInt(uroles[i])==7){//主管及以上
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
					if(users.size()>0){//被考核人数组
						map.clear();
						map.put("ausers", users);
						iss=cdao.list(map); 
						for(IndexCheck ic:iss){
							List<String> toa = new ArrayList<String>();
							if(check_user!=null){
								String a[]=check_user.split(",");
								Boolean iskip = ToolsUtil.isIn(ic.getCheck_user(), a);// 判断在在数组中
								map.clear();
								map.put("check_user_id", ic.getUuid());
								map.put("check_ym", index_id);//活动
								ScoreDao sd=new ScoreDao();
								List<Score360> s360s=new ArrayList<Score360>();
								s360s=sd.list(map);
								if(iskip==true&&s360s.size()<=0){
									System.out.println("催收："+ic.getCheckuser_name());
									toa.add(ic.getCheck_user_email());  
								}
							}else{
								toa.add(ic.getCheck_user_email());
							}
							String sub = ic.getCheck_user_name() + "考核";
							StringBuffer url = new StringBuffer();
							HttpServletRequest request = ServletActionContext.getRequest();
							String url2=request.getRequestURL().toString();
			 				String uri=request.getRequestURI();
			 				url.append(url2.substring(0, url2.indexOf(uri)));
							url.append("/check360/check_360ScoreLoad?");
							url.append("ic.uuid="+ic.getUuid());//被考核人与考核人中间表
							url.append("&index360.uuid="+ index_id);//活动id
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
							if(toa.size()>0){
								SysMail.sendHtml(toa, sub, content.toString());
							}
							
						}
					}
					
				}
			}
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		
	}

}
