package com.qkj.qkjmanager.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.iweb.sys.ContextHelper;
import org.iweb.sys.ExcelUtil;
import org.iweb.sys.Parameters;
import org.iweb.sys.domain.Department;
import org.iweb.sys.domain.User;
import org.iweb.sys.domain.UserDept;
import org.iweb.sys.domain.UserLoginInfo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qkj.basics.dao.CheckDao;
import com.qkj.basics.domain.Check;
import com.qkj.qkjmanager.dao.VardicDao;
import com.qkj.qkjmanager.dao.reportDao;
import com.qkj.qkjmanager.domain.Score;
import com.qkj.qkjmanager.domain.Vartic;

public class ReportAction extends ActionSupport {
	private reportDao dao = new reportDao();
	private static Log log = LogFactory.getLog(ReportAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private List<Score> score;
	private Vartic vardic;

	public Vartic getVardic() {
		return vardic;
	}

	public void setVardic(Vartic vardic) {
		this.vardic = vardic;
	}

	private List<Vartic> vardics;
	private List<Vartic> vardicsbyd;
	private List<Vartic> vardicsc;
	private List<Vartic> vardicsb;
	private List<Vartic> vardicwillu;
	private List<Vartic> vardicswilld;
	private List<Department> hzds;
	private List<User> hzus;
	private List<Department> bgs;
	private List<User> bgus;
	private User user;
	private String adept;
	private String auser;
	private String cymprint;
	
	public String getCymprint() {
		return cymprint;
	}

	public void setCymprint(String cymprint) {
		this.cymprint = cymprint;
	}

	public String getAdept() {
		return adept;
	}

	public void setAdept(String adept) {
		this.adept = adept;
	}

	public String getAuser() {
		return auser;
	}

	public void setAuser(String auser) {
		this.auser = auser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getBgus() {
		return bgus;
	}

	public void setBgus(List<User> bgus) {
		this.bgus = bgus;
	}

	public List<Department> getBgs() {
		return bgs;
	}

	public void setBgs(List<Department> bgs) {
		this.bgs = bgs;
	}

	public List<User> getHzus() {
		return hzus;
	}

	public void setHzus(List<User> hzus) {
		this.hzus = hzus;
	}

	public List<Department> getHzds() {
		return hzds;
	}

	public void setHzds(List<Department> hzds) {
		this.hzds = hzds;
	}

	public List<Vartic> getVardicwillu() {
		return vardicwillu;
	}

	public void setVardicwillu(List<Vartic> vardicwillu) {
		this.vardicwillu = vardicwillu;
	}

	public List<Vartic> getVardicswilld() {
		return vardicswilld;
	}

	public void setVardicswilld(List<Vartic> vardicswilld) {
		this.vardicswilld = vardicswilld;
	}

	public List<Vartic> getVardicsb() {
		return vardicsb;
	}

	public void setVardicsb(List<Vartic> vardicsb) {
		this.vardicsb = vardicsb;
	}

	public List<Vartic> getVardicsc() {
		return vardicsc;
	}

	public void setVardicsc(List<Vartic> vardicsc) {
		this.vardicsc = vardicsc;
	}

	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;考核报表";

	public List<Vartic> getVardics() {
		return vardics;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setVardics(List<Vartic> vardics) {
		this.vardics = vardics;
	}

	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;

	public List<Score> getScore() {
		return score;
	}

	public void setScore(List<Score> score) {
		this.score = score;
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

	public List<Vartic> getVardicsbyd() {
		return vardicsbyd;
	}

	public void setVardicsbyd(List<Vartic> vardicsbyd) {
		this.vardicsbyd = vardicsbyd;
	}

	public String listhz() throws Exception {
		// 查询考核部门
		map.clear();
		// 查询打开的考核日期
		if (vardic == null) {
			Check cs = new Check();
			VardicDao vd = new VardicDao();
			cs = vd.check_cym();
			vardic = new Vartic();
			vardic.setCym(cs.getYm());
		} else {
			ContextHelper.SimpleSearchMap4Page(null, map, vardic, viewFlag);
		}

		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		UserLoginInfo ulf = new UserLoginInfo();
		ulf = (UserLoginInfo) request.getSession().getAttribute(
				Parameters.UserLoginInfo_Session_Str);
		List<UserDept> uds = new ArrayList<>();
		uds = ulf.getUds();
		List<String> dlistall = new ArrayList<>();
		Set<String> dsetall = new HashSet<>();
		Set<String> dall = new HashSet<>();
		if (uds.size() > 0) {
			for (int s = 0; s < uds.size(); s++) {
				if (uds.get(s).getRoles().contains("2016060815212623")) {
					dsetall.add(uds.get(s).getDept_code());
				}
				if (uds.get(s).getRoles().contains("2016072516956868")) {// 部门考核权限
					dall.add(uds.get(s).getDept_code());
				}
			}
			if (dsetall.size() > 0) {
				dlistall.addAll(dsetall);
				map.put("parent_dept", dlistall);// 多权限可查询多个子部门
			}
			if (dall.size() > 0) {
				List<String> dlall = new ArrayList<>();
				dlall.addAll(dall);
				map.put("chdept", dlall);
			}
		}
		this.setHzds(dao.listhzd(map));
		map.clear();
		map.put("parent_user", ContextHelper.getUserLoginUuid());
		this.setHzus(dao.listhzu(map));
		return SUCCESS;
	}

	public String listbg() throws Exception {
		// 查询考核部门
		map.clear();
		// 查询打开的考核日期
		if (vardic == null) {
			Check cs = new Check();
			VardicDao vd = new VardicDao();
			cs = vd.check_cym();
			vardic = new Vartic();
			vardic.setCym(cs.getYm());
		}
		this.setBgs(dao.listhbg(map));
		return SUCCESS;
	}

	public String listbgu() throws Exception {
		// 查询考核部门
		map.clear();
		// 查询打开的考核日期
		if (vardic != null) {
			if (vardic.getCym() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				String str = sdf.format(vardic.getCym());
				map.put("cym", str);
			}
			if (vardic.getAcheck_usercode() != null) {
				map.put("dept_code", vardic.getAcheck_usercode());
			}
			this.setBgus(dao.listhbgu(map));
		}
		return SUCCESS;
	}

	public String listjx() throws Exception {
		try {
			// 查询打开的审核日期
			CheckDao c = new CheckDao();
			List<Check> checks = new ArrayList();
			map.clear();
			map.put("state", 0);// 状态打开的审核日期
			checks = c.list(map);
			map.clear();
			if (vardic == null) {
				vardic = new Vartic();
			}
			// ContextHelper.setSearchDeptPermit4Search("SYS_QKJMANAGER_CHECKLIST",
			// map, "apply_depts", "apply_user");
			ContextHelper.SimpleSearchMap4Page("SYS_QKJMANAGER_CHECKLIST", map,
					vardic, viewFlag);
			// this.setPageSize(Integer.parseInt(map.get(Parameters.Page_Size_Str).toString()));
			if (map.get("cym") != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				String d = sdf.format(vardic.getCym());
				map.remove("cym");
				map.put("cym", d);
			}

			if (checks.size() > 0) {// 只查询打开的已考核记录
				map.put("check_ym", checks.get(0).getUuid());
			}
			UserLoginInfo ulf = new UserLoginInfo();
			ActionContext context = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) context
					.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) context
					.get(ServletActionContext.HTTP_RESPONSE);
			ulf = (UserLoginInfo) request.getSession().getAttribute(
					Parameters.UserLoginInfo_Session_Str);
			List<UserDept> uds = new ArrayList<>();
			uds = ulf.getUds();
			Set<String> dall = new HashSet<>();
			if (uds.size() > 0) {
				for (int s = 0; s < uds.size(); s++) {
					if (uds.get(s).getRoles().contains("2016072516956868")
							|| uds.get(s).getDepsubover() == 1) {// 部门考核权限
						dall.add(uds.get(s).getDept_code());
					}
				}
				if (dall.size() > 0) {
					List<String> dlall = new ArrayList<>();
					dlall.addAll(dall);
					map.put("apply_depts", dlall);
				}
			}
			map.put("apply_userDouble", ContextHelper.getUserLoginUuid());
			this.setVardics(dao.listU(map));
			this.setVardicsbyd(dao.listD(map));
			// this.setRecCount(dao.getResultCount());
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;纵向考核列表";
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		return SUCCESS;
	}

	public String report() throws Exception {
		if (vardic != null && vardic.getCym() != null) {
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM");
			vardic.setCheck_yms(sim.format(vardic.getCym()));
			map.put("cym", vardic.getCheck_yms());
		}
		ContextHelper.setSearchDeptPermit4Search("SYS_QKJMANAGER_CHECKLIST",
				map, "apply_depts", "apply_user");
		ContextHelper.SimpleSearchMap4Page("SYS_QKJMANAGER_CHECKLIST", map,
				vardic, viewFlag);
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		String fileName = "汇总表格";
		Vartic v1 = new Vartic();
		int count = 0;

		this.setVardics(dao.list(map));
		String columnNames[] = { "主键", "考核年月", "被考核人", "被考核人部门", "考核完成时间", "分数" };
		String keys[] = { "uuid", "check_ym", "acheck_username",
				"acheck_deptname", "check_date", "ay_totelScore" };
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ExcelUtil.createWorkBook(vardics, keys, columnNames).write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");

		ServletOutputStream out = null;
		try {
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
			out = response.getOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

		return SUCCESS;
	}


	public String print() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
        
		HttpServletRequest request = ServletActionContext.getRequest();
		String cym = this.getCymprint();
		JSONArray adepts = JSONArray.fromObject(this.getAdept());
		JSONArray ausers = JSONArray.fromObject(this.getAuser());
		map.clear();
		String fileName = ContextHelper.getUserLoginName()+"汇总表格";
		if (adepts.size()>0 || adepts.size()>0) {
			WritableWorkbook wwb = null;
			// 设这输出的类型和文件格式
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
			// 设置文件名和并且解决中文名不能下载
			OutputStream os = response.getOutputStream();
			// 创建可写入的Excel工作簿
			wwb = Workbook.createWorkbook(os);
			// 创建工作表
			WritableSheet ws = wwb.createSheet(ContextHelper.getUserLoginName()
					+ "汇总", 0);

			ws.setColumnView(0, 15);
			ws.setColumnView(1, 25);
			ws.setColumnView(2, 15);
			ws.setColumnView(3, 25);
			ws.setColumnView(4, 25);
			ws.setColumnView(5, 25);
			ws.setColumnView(7, 25);
			ws.setColumnView(8, 35);
			WritableFont font1 = new WritableFont(WritableFont.ARIAL, 11);

			WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
			
			// 查询数据库中所有的数据
			map.clear();
			map.put("notype", 1);
			// 要插入到的Excel表格的行号，默认从0开始
			Label labelId = new Label(0, 0, "部门/人员编号", cellFormat1);// 表示第1列1个
			Label labelName = new Label(1, 0, "考核年月", cellFormat1);// 第2列1个
			Label labelMeName = new Label(2, 0, "被考核人:", cellFormat1);// 第五列1 行
			Label labelMeName2 = new Label(3, 0, "职务", cellFormat1);// 第五列1 行
			Label labelDate = new Label(4, 0, "被考核人部门", cellFormat1);// 第五列1 行
			Label labelDa = new Label(5, 0, "分数", cellFormat1);// 第五列1 行
			Label labelSt = new Label(6, 0, "评级", cellFormat1);// 第五列1 行
			Label labelBa = new Label(7, 0, "系数", cellFormat1);// 第五列1 行
			Label labelBa2 = new Label(8, 0, "备注", cellFormat1);// 第五列1 行

			ws.addCell(labelId);
			ws.addCell(labelName);
			ws.addCell(labelMeName);
			ws.addCell(labelMeName2);
			ws.addCell(labelDate);
			ws.addCell(labelDa);
			ws.addCell(labelSt);
			ws.addCell(labelBa);
			ws.addCell(labelBa2);

			for (short i = 0; i < adepts.size(); i++) {
				// 创建一行，在页sheet上
				JSONObject a1 = JSONObject.fromObject(adepts.get(i).toString());
				String uuid=a1.getString("部门编号").substring(0,a1.getString("部门编号").indexOf("$")).trim();
				Label labelres_i = new Label(0, i + 2, uuid);
				Label labelres_c = new Label(1, i + 2, cym);
				Label labelres_d = new Label(4, i + 2, a1.getString("部门")
						.toString());
				Label labelres_s = new Label(5, i + 2, a1.getString("本月得分")
						.toString());
				Label labelres_p = new Label(6, i + 2, a1.getString("本月评级")
						.toString());
				Label labelres_x = new Label(7, i + 2, a1.getString("本月系数")
						.toString());
				Label labelres_b = new Label(8, i + 2, a1.getString("备注")
						.toString());
				ws.addCell(labelres_i);
				ws.addCell(labelres_c);
				ws.addCell(labelres_d);
				ws.addCell(labelres_s);
				ws.addCell(labelres_p);
				ws.addCell(labelres_x);
				ws.addCell(labelres_b);

			}
			
			for (short i = 0; i < ausers.size(); i++) {
				// 创建一行，在页sheet上
				JSONObject a1 = JSONObject.fromObject(ausers.get(i).toString());
				String uuid=a1.getString("员工编号").substring(0,a1.getString("员工编号").indexOf("$")).trim();
				Label labelres_i = new Label(0, adepts.size() + 3+i, uuid);
				Label labelres_c = new Label(1, adepts.size() + 3+i, cym);
				Label labelres_n = new Label(2, adepts.size() + 3+i, a1.getString("姓名"));
				Label labelres_po = new Label(3, adepts.size() + 3+i, a1.getString("岗位"));
				Label labelres_d = new Label(4, adepts.size() + 3+i, a1.getString("部门")
						.toString());
				Label labelres_s = new Label(5, adepts.size() + 3+i, a1.getString("本月得分")
						.toString());
				Label labelres_p = new Label(6, adepts.size() + 3+i, a1.getString("本月评级")
						.toString());
				Label labelres_x = new Label(7, adepts.size() + 3+i, a1.getString("本月系数")
						.toString());
				Label labelres_b = new Label(8, adepts.size() + 3+i, a1.getString("备注")
						.toString());
				ws.addCell(labelres_i);
				ws.addCell(labelres_c);
				ws.addCell(labelres_n);
				ws.addCell(labelres_po);
				ws.addCell(labelres_d);
				ws.addCell(labelres_s);
				ws.addCell(labelres_p);
				ws.addCell(labelres_x);
				ws.addCell(labelres_b);

			}

			// 写进文档
			wwb.write();
			// 关闭Excel工作簿对象
			wwb.close();
			os.close();
			response.flushBuffer();
		}
		return null;
	}

	public String check_sure() throws Exception {
		try {
			CheckDao cd = new CheckDao();
			cd.saveState();
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;列表";
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		return SUCCESS;
	}
}
