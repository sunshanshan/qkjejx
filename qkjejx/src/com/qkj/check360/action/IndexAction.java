package com.qkj.check360.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.iweb.sys.ContextHelper;
import org.iweb.sys.Parameters;
import org.iweb.sys.ToolsUtil;
import org.iweb.sys.dao.UserDAO;
import org.iweb.sys.domain.User;
import org.iweb.sys.mail.MailSenderInfo;
import org.iweb.sys.mail.SimpleMailSender;
import org.iweb.sys.mail.SysMail;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.check360.dao.IndexCheckDAO;
import com.qkj.check360.dao.IndexDAO;
import com.qkj.check360.domain.Index;
import com.qkj.check360.domain.IndexCheck;

public class IndexAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(IndexAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private IndexDAO dao = new IndexDAO();
	private IndexCheckDAO cdao = new IndexCheckDAO();
	private MailSenderInfo mailInfo = new MailSenderInfo();
	private Index index;
	private List<Index> indexs;
	private IndexCheck indexCheck;
	private List<IndexCheck> indexChecks;
	private User user;

	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;360管理";

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
				// 查询指标
				map.clear();
				map.put("user_id", user.getUuid());
				this.setIndexs(dao.list(map));
				// 查询考核人及权重
				this.setIndexChecks(cdao.list(map));
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
			// 查询所有考核人发送邮件
			this.setIndexChecks(cdao.list(null));
			if (indexChecks.size() > 0) {
				for (IndexCheck ic : indexChecks) {
					List<String> toa = new ArrayList<String>();
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

				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.getWriter().print("0");
			} else {
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.getWriter().print("2");
			}

		} catch (Exception e) {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.getWriter().print("1");
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}

	}

}
