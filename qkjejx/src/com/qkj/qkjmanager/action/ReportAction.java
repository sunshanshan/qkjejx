package com.qkj.qkjmanager.action;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.apache.struts2.ServletActionContext;
import org.iweb.sys.ContextHelper;
import org.iweb.sys.Parameters;
import org.iweb.sys.dao.DepartmentDAO;
import org.iweb.sys.dao.UserDAO;
import org.iweb.sys.domain.Department;
import org.iweb.sys.domain.User;
import org.iweb.sys.domain.UserDept;
import org.iweb.sys.domain.UserLoginInfo;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qkj.basics.dao.CheckDao;
import com.qkj.basics.domain.Check;
import com.qkj.qkjmanager.dao.VardicDao;
import com.qkj.qkjmanager.dao.VardicDetailDao;
import com.qkj.qkjmanager.dao.reportDao;
import com.qkj.qkjmanager.domain.Score;
import com.qkj.qkjmanager.domain.Vartic;
import com.qkj.qkjmanager.domain.VarticDetail;
import com.qkj.qkjmanager.domain.VarticView;

public class ReportAction extends ActionSupport {
	private reportDao dao = new reportDao();
	private static Log log = LogFactory.getLog(ReportAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private List<Score> score;
	private List<VarticView> vvs;
	private List<VarticDetail> vvsd;
	private Vartic vardic;
	private List<Vartic> vardics;
	private List<Vartic> vardicsbyd;
	private List<Vartic> vardicsc;
	private List<Vartic> vardicsb;
	private List<Vartic> vardicwillu;
	private List<Vartic> vardicswilld;
	private List<Vartic> leaves;
	private Score leave;
	private List<Department> hzds;
	private List<User> hzus;
	private List<Department> bgs;
	private List<User> bgus;
	private User user;
	private String adept;
	private String auser;
	private String cymprint;
	private static String adeptview;
	private static String auserview;

	public List<VarticDetail> getVvsd() {
		return vvsd;
	}

	public void setVvsd(List<VarticDetail> vvsd) {
		this.vvsd = vvsd;
	}

	public Score getLeave() {
		return leave;
	}

	public void setLeave(Score leave) {
		this.leave = leave;
	}

	public static String getAdeptview() {
		return adeptview;
	}

	public static void setAdeptview(String adeptview) {
		ReportAction.adeptview = adeptview;
	}

	public static String getAuserview() {
		return auserview;
	}

	public static void setAuserview(String auserview) {
		ReportAction.auserview = auserview;
	}

	public Vartic getVardic() {
		return vardic;
	}

	public void setVardic(Vartic vardic) {
		this.vardic = vardic;
	}
	
	public List<VarticView> getVvs() {
		return vvs;
	}

	public void setVvs(List<VarticView> vvs) {
		this.vvs = vvs;
	}

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

	public List<Vartic> getLeaves() {
		return leaves;
	}

	public void setLeaves(List<Vartic> leaves) {
		this.leaves = leaves;
	}

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
				//有部门权限及子部门
				if (uds.get(s).getDepsubover()!=null && uds.get(s).getDepsubover()==1 && uds.get(s).getSubover()!=null && uds.get(s).getSubover()==1) {
					dsetall.add(uds.get(s).getDept_code());
				}
				//只有部门权限
				if (uds.get(s).getRoles().contains("2016072516956868")||(uds.get(s).getIscheckdept()!=null&&uds.get(s).getIscheckdept()==1)) {// 部门考核权限
					dall.add(uds.get(s).getDept_code());
				}
			}
			if (dsetall.size() > 0) {
				dlistall.addAll(dsetall);
				map.put("parent_dept", dlistall);// 多权限可查询多个子部门
			}else{
				dsetall.add("o");
				dlistall.addAll(dsetall);
				map.put("parent_dept", dlistall);
			}
			if (dall.size() > 0) {
				List<String> dlall = new ArrayList<>();
				dlall.addAll(dall);
				map.put("chdept", dlall);
			}else{
				dall.add("o");
				dlistall.addAll(dall);
				map.put("parent_dept", dlistall);
			}
		}else{
			map.put("parent_dept", 0);
		}
		this.setHzds(dao.listhzd(map));
		//map.clear();
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
			CheckDao cdd=new CheckDao();
			cs = (Check) cdd.gettop1();
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
			/*this.setLeaves(dao.listleave(map));
			if(leaves.size()>0){
			this.setLeave(leaves.get(0));
			}*/
			this.setLeave((Score) dao.getleave(map));
			System.out.println(leave.getLeaveb());
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
				if (checks.size() > 0) {// 只查询打开的已考核记录
					vardic.setCym(checks.get(0).getYm());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
					String d = sdf.format(vardic.getCym());
					map.remove("cym");
					map.put("cym", d);
				}
			}else{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
				String d = sdf.format(vardic.getCym());
				map.remove("cym");
				map.put("cym", d);
				if(vardic.getStarcym()!=null){
					String star=sdf.format(vardic.getStarcym());
					map.put("starcym", star+"-01");
				}
				if(vardic.getClocym()!=null){
					String close=sdf.format(vardic.getClocym());
					map.put("clocym", close+"-31");
				}
				
			}
			if (map.get("starcym") != null || map.get("clocym") != null) {//如果时间段不为空则按时间段取值
				map.remove("cym");
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
					if (uds.get(s).getRoles().contains("2016060815212623") &&uds.get(s).getIscheckdept()!=null && uds.get(s).getIscheckdept()==1) {
						dall.add(uds.get(s).getDept_code());
					}
					if (uds.get(s).getRoles().contains("2016072516956868")) {// 部门考核权限
						dall.add(uds.get(s).getDept_code());
					}
				}
				if (dall.size() > 0) {
					List<String> dlall = new ArrayList<>();
					dlall.addAll(dall);
					map.put("apply_depts", dlall);
				}else{
					dall.add("o");
					List<String> dlall = new ArrayList<>();
					dlall.addAll(dall);
					map.put("apply_depts", dlall);
				}
			}
			map.put("apply_userDouble", ContextHelper.getUserLoginUuid());
			this.setVardics(dao.listU(map));
			if(vardics.size()>0){
				Double totle=0.00;
				for(int i=0;i<vardics.size();i++){
					totle+=vardics.get(i).getCheck_score();
				}
				vardic.setAveu(totle/vardics.size());
			}
			//map.clear();
			map.remove("apply_depts");
			map.remove("apply_userDouble");
			map.put("user_login_dept", ContextHelper.getUserLoginDept());
			this.setVardicsbyd(dao.listD(map));
			
			if(vardicsbyd.size()>0){
				Double totle=0.00;
				for(int i=0;i<vardicsbyd.size();i++){
					totle+=vardicsbyd.get(i).getCheck_score();
				}
				vardic.setAved(totle/vardicsbyd.size());
			}
			// this.setRecCount(dao.getResultCount());
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;纵向考核列表";
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	public String hreport() throws Exception {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		        
				HttpServletRequest request = ServletActionContext.getRequest();
				VardicDetailDao dd =new VardicDetailDao();
				map.clear();
				map.put("typea", 0);
				this.setVvsd(dd.listh(map));
				String fileName = ContextHelper.getUserLoginName()+"汇总表格";
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
					ws.setColumnView(1, 15);
					ws.setColumnView(2, 15);
					ws.setColumnView(3, 15);
					ws.setColumnView(4, 15);
					ws.setColumnView(5, 15);
					ws.setColumnView(7, 15);
					ws.setColumnView(8, 35);
					WritableFont font1 = new WritableFont(WritableFont.ARIAL, 11);

					WritableCellFormat cellFormat1 = new WritableCellFormat(font1);

					// 查询数据库中所有的数据
					map.clear();
					map.put("notype", 1);
					// 要插入到的Excel表格的行号，默认从0开始
					Label labelId = new Label(0, 0, "姓名", cellFormat1);// 表示第1列1个
					Label labelName = new Label(1, 0, "职务", cellFormat1);// 第2列1个
					Label labelMeName = new Label(2, 0, "部门", cellFormat1);// 第五列1 行
					Label labelMeName2 = new Label(3, 0, "考核人", cellFormat1);// 第五列1 行
					Label labelDate = new Label(4, 0, "评分", cellFormat1);// 第五列1 行
					Label labelDa = new Label(5, 0, "得分", cellFormat1);// 第五列1 行
					Label labelSt = new Label(6, 0, "考核日期", cellFormat1);// 第五列1 行
					Label labelBa = new Label(7, 0, "考核年月编号", cellFormat1);// 第五列1 行
					Label labelBa2 = new Label(8, 0, "kpi", cellFormat1);// 第五列1 行
					Label labelBa3 = new Label(9, 0, "权重", cellFormat1);// 第五列1 行
					Label labelBa4 = new Label(10, 0, "主表编号", cellFormat1);// 第五列1 行

					ws.addCell(labelId);
					ws.addCell(labelName);
					ws.addCell(labelMeName);
					ws.addCell(labelMeName2);
					ws.addCell(labelDate);
					ws.addCell(labelDa);
					ws.addCell(labelSt);
					ws.addCell(labelBa);
					ws.addCell(labelBa2);
					ws.addCell(labelBa3);
					ws.addCell(labelBa4);

					for (short i = 0; i < vvsd.size(); i++) {
						// 创建一行，在页sheet上
						Label au = new Label(0, i+1, vvsd.get(i).getAcheck_user_name()==null?"无":vvsd.get(i).getAcheck_user_name());
						Label ap = new Label(1, i+1, vvsd.get(i).getPosition_name()==null?"无":vvsd.get(i).getPosition_name());
						Label ad = new Label(2, i+1, vvsd.get(i).getDept_name());
						Label auuid = new Label(3, i+1, vvsd.get(i).getCheck_user_name()==null?"无":vvsd.get(i).getCheck_user_name());
						Label acode = new Label(4, i+1, Double.toString(vvsd.get(i).getCheck_score()));
						Label score = new Label(5, i+1, Double.toString(vvsd.get(i).getCheck_goal()));
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String str = sdf.format(vvsd.get(i).getCheck_date());
						
						Label checkdate = new Label(6, i+1, str);
						SimpleDateFormat sc = new SimpleDateFormat("yyyy-MM");
						String strym = sc.format(vvsd.get(i).getYm());
						Label checkym = new Label(7, i+1, strym);
						
						Label remark = new Label(8, i+1, vvsd.get(i).getKpi()==null?"无":vvsd.get(i).getKpi());
						Double a=vvsd.get(i).getCheck_goal()/vvsd.get(i).getCheck_score();
						Label bscor = new Label(9, i+1, Double.toString(a));
						Label jtype = new Label(10, i+1, vvsd.get(i).getScore_id().toString());
						ws.addCell(au);
						ws.addCell(ap);
						ws.addCell(ad);
						ws.addCell(auuid);
						ws.addCell(acode);
						ws.addCell(score);
						ws.addCell(checkdate);
						ws.addCell(checkym);
						ws.addCell(remark);
						ws.addCell(bscor);
						ws.addCell(jtype);
						
					}

					// 写进文档
					wwb.write();
					// 关闭Excel工作簿对象
					wwb.close();
					os.close();
					response.flushBuffer();
				return null;
			}

	public String report() throws Exception {
		
HttpServletResponse response = ServletActionContext.getResponse();
        
		HttpServletRequest request = ServletActionContext.getRequest();
		map.clear();
		this.setVardics(dao.list(null));
		String fileName = ContextHelper.getUserLoginName()+"汇总表格";
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
			ws.setColumnView(1, 15);
			ws.setColumnView(2, 15);
			ws.setColumnView(3, 15);
			ws.setColumnView(4, 15);
			ws.setColumnView(5, 15);
			ws.setColumnView(7, 15);
			ws.setColumnView(8, 35);
			WritableFont font1 = new WritableFont(WritableFont.ARIAL, 11);

			WritableCellFormat cellFormat1 = new WritableCellFormat(font1);

			// 查询数据库中所有的数据
			map.clear();
			map.put("notype", 1);
			// 要插入到的Excel表格的行号，默认从0开始
			Label labelId = new Label(0, 0, "姓名", cellFormat1);// 表示第1列1个
			Label labelName = new Label(1, 0, "职务", cellFormat1);// 第2列1个
			
			Label oned = new Label(2, 0, "一级部门", cellFormat1);// 第五列1 行
			Label twod = new Label(3, 0, "二级部门", cellFormat1);// 第五列1 行
			
			Label labelMeName = new Label(4, 0, "部门", cellFormat1);// 第五列1 行
			Label labelMeName2 = new Label(5, 0, "人员编号", cellFormat1);// 第五列1 行
			Label labelDate = new Label(6, 0, "部门编号", cellFormat1);// 第五列1 行
			Label labelDa = new Label(7, 0, "分数", cellFormat1);// 第五列1 行
			Label labelSt = new Label(8, 0, "考核日期", cellFormat1);// 第五列1 行
			Label labelBa = new Label(9, 0, "考核年月编号", cellFormat1);// 第五列1 行
			Label labelBa2 = new Label(10, 0, "备注", cellFormat1);// 第五列1 行
			Label labelBa3 = new Label(11, 0, "可扣分项", cellFormat1);// 第五列1 行
			Label labelBa4 = new Label(12, 0, "类型", cellFormat1);// 第五列1 行
			Label labelBa5 = new Label(13, 0, "评级", cellFormat1);// 第五列1 行
			Label labelBa6 = new Label(14, 0, "系数", cellFormat1);// 第五列1 行

			ws.addCell(labelId);
			ws.addCell(labelName);ws.addCell(oned);ws.addCell(twod);
			ws.addCell(labelMeName);
			ws.addCell(labelMeName2);
			ws.addCell(labelDate);
			ws.addCell(labelDa);
			ws.addCell(labelSt);
			ws.addCell(labelBa);
			ws.addCell(labelBa2);
			ws.addCell(labelBa3);
			ws.addCell(labelBa4);ws.addCell(labelBa5);ws.addCell(labelBa6);

			for (short i = 0; i < vardics.size(); i++) {
				// 创建一行，在页sheet上
				Label au = new Label(0, i+1, vardics.get(i).getAcheck_user()==null?"无":vardics.get(i).getAcheck_username());
				Label ap = new Label(1, i+1, vardics.get(i).getPosition_name()==null?"无":vardics.get(i).getPosition_name());
				
				Label adone = new Label(2, i+1, vardics.get(i).getAcd_cname()==null?"无":vardics.get(i).getAcd_cname());
				Label adtwo = new Label(3, i+1, vardics.get(i).getDf_name()==null?"无":vardics.get(i).getDf_name());
				
				Label ad = new Label(4, i+1, vardics.get(i).getAcheck_deptname());
				Label auuid = new Label(5, i+1, vardics.get(i).getAcheck_user()==null?"无":vardics.get(i).getAcheck_user());
				Label acode = new Label(6, i+1, vardics.get(i).getAcheck_usercode());
				Label score = new Label(7, i+1, vardics.get(i).getCheck_score().toString());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String str = sdf.format(vardics.get(i).getCheck_date());
				
				Label checkdate = new Label(8, i+1, str);
				Label checkym = new Label(9, i+1, vardics.get(i).getCheck_ym().toString());
				Label remark = new Label(10, i+1, vardics.get(i).getRemark()==null?"无":vardics.get(i).getRemark());
				Label bscor = new Label(11, i+1, vardics.get(i).getBscore()==null?"无":vardics.get(i).getBscore().toString());
				Label jtype = new Label(12, i+1, vardics.get(i).getJltype()==null?"无":vardics.get(i).getJltype().toString());
				String p="";
				String x="";
				if(vardics.get(i).getCheck_score()>=90){
					p="A";
					x="1.3";
				}else if(vardics.get(i).getCheck_score()>=80&&vardics.get(i).getCheck_score()<90){
					p="B";
					x="1.1";
				}else if(vardics.get(i).getCheck_score()>=60&&vardics.get(i).getCheck_score()<80){
					p="C";
					x="1.0";
				}else if(vardics.get(i).getCheck_score()<60){
					p="D";
					x="0.8";
				};
				Label pp = new Label(13, i+1, p);
				Label xx = new Label(14, i+1, x);
				ws.addCell(au);
				ws.addCell(ap);ws.addCell(adone);ws.addCell(adtwo);
				ws.addCell(ad);
				ws.addCell(auuid);
				ws.addCell(acode);
				ws.addCell(score);
				ws.addCell(checkdate);
				ws.addCell(checkym);
				ws.addCell(remark);
				ws.addCell(bscor);
				ws.addCell(jtype);
				ws.addCell(pp);
				ws.addCell(xx);
				
			}

			// 写进文档
			wwb.write();
			// 关闭Excel工作簿对象
			wwb.close();
			os.close();
			response.flushBuffer();
		return null;
	}
	
	public String view1() throws Exception {
		this.setAdeptview(null);
		this.setAuserview(null);
		this.setAdeptview(this.getAdept());
		this.setAuserview(this.getAuser());
		String cym = this.getCymprint();
		JSONArray adepts = JSONArray.fromObject(this.getAdeptview());
		JSONArray ausers = JSONArray.fromObject(this.getAuserview());
		vvs=new ArrayList<>();
		if (adepts.size()>0 || ausers.size()>0) {
			for (short i = 0; i < adepts.size(); i++) {
				// 创建一行，在页sheet上
				JSONObject a1 = JSONObject.fromObject(adepts.get(i).toString());
				String dept_code=a1.getString("部门编号").substring(0,a1.getString("部门编号").indexOf("$")).trim();
				map.clear();
				map.put("dept_code", dept_code);
				DepartmentDAO dd=new DepartmentDAO();
				List<Department> d=new ArrayList();
				d=dd.list(map);
				String parname=null;
				if(d.size()>0&&d.get(0).getType()!=null&&d.get(0).getType()==1){
					parname=d.get(0).getPardname();
				}
				VarticView vv=new VarticView();
				vv.setD_code(dept_code);
				if(parname!=null){
					vv.setDeptname("("+parname+")"+a1.getString("部门"));
				}else{
					vv.setDeptname(a1.getString("部门"));
				}
				vv.setCheck_score(Double.parseDouble(a1.getString("本月得分")));
				vv.setRemark(a1.getString("备注"));
				if(a1.getString("加扣分项")!=null&&!a1.getString("加扣分项").equals("")){
					vv.setBscore(Double.parseDouble(a1.getString("加扣分项")));
				}
				
				vvs.add(vv);
				
			}
			
			UserDAO ud=new UserDAO();
			List<User> us=new ArrayList();
			map.clear();
			map.put("parent_user", ContextHelper.getUserLoginUuid());
			us=ud.list(map);
			String parname=null;
			for (short i = 0; i < ausers.size(); i++) {
				// 创建一行，在页sheet上
				JSONObject a1 = JSONObject.fromObject(ausers.get(i).toString());
				String uuid=a1.getString("员工编号").substring(0,a1.getString("员工编号").indexOf("$")).trim();
				Boolean flag=false;
				if(us.size()>0){
					for(int j=0;j<us.size();j++){
						if(us.get(j).getUuid()!=null && us.get(j).getUuid().equals(uuid)){
							if(us.get(j).getDepttype()!=null&&us.get(j).getDepttype()==1){
								parname=us.get(j).getPardname();
							}else if(us.get(j).getDepttype()!=null&&us.get(j).getDepttype()==2){
								parname=us.get(j).getPparname();
							}
							flag=true;
							break;
						}
					}
				}
				if(flag==true){
					VarticView vv=new VarticView();
					vv.setU_id(uuid);
					vv.setUsername(a1.getString("姓名"));
					vv.setPname(a1.getString("岗位"));
					if(parname!=null){
						vv.setDeptname("("+parname+")"+a1.getString("部门"));
					}else{
						vv.setDeptname(a1.getString("部门"));
					}
					vv.setCheck_score(Double.parseDouble(a1.getString("本月得分")));
					vv.setRemark(a1.getString("备注"));
					if(a1.getString("加扣分项")!=null&&!a1.getString("加扣分项").equals("")){
					vv.setBscore(Double.parseDouble(a1.getString("加扣分项")));
					}
					vvs.add(vv);
				}
				
			}

		}
		return SUCCESS;
	}

	public void view() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String cym = this.getCymprint();
		JSONArray adepts = JSONArray.fromObject(this.getAdeptview());
		JSONArray ausers = JSONArray.fromObject(this.getAuserview());
		vvs=new ArrayList<>();
		if (adepts.size()>0 || adepts.size()>0) {
			for (short i = 0; i < adepts.size(); i++) {
				// 创建一行，在页sheet上
				JSONObject a1 = JSONObject.fromObject(adepts.get(i).toString());
				String dept_code=a1.getString("部门编号").substring(0,a1.getString("部门编号").indexOf("$")).trim();
				VarticView vv=new VarticView();
				map.clear();
				map.put("acheck_usercode", dept_code);
				map.put("cym", cym);
				List<VarticView> ls=new ArrayList();
				ls=dao.listview(map);
				if(ls.size()>0){
					for(int j=0;j<ls.size();j++){
						vv=ls.get(j);
						vv.setD_code(dept_code);
						vv.setDeptname(a1.getString("部门"));
						vv.setCheck_score(Double.parseDouble(a1.getString("本月得分")));
						vv.setRemark(a1.getString("备注"));
						if(a1.getString("加扣分项")!=null&&!a1.getString("加扣分项").equals("")){
						vv.setBscore(Double.parseDouble(a1.getString("加扣分项")));
						}
						vvs.add(vv);
					}
				}
				
			}
			
			for (short i = 0; i < ausers.size(); i++) {
				// 创建一行，在页sheet上
				JSONObject a1 = JSONObject.fromObject(ausers.get(i).toString());
				String uuid=a1.getString("员工编号").substring(0,a1.getString("员工编号").indexOf("$")).trim();
				VarticView vv=new VarticView();
				map.clear();
				map.put("acheck_user", uuid);
				map.put("cym", cym);
				List<VarticView> ls=new ArrayList();
				ls=dao.listview(map);
				for(int j=0;j<ls.size();j++){
					vv=ls.get(j);
					vv.setU_id(uuid);
					vv.setUsername(a1.getString("姓名"));
					vv.setPname(a1.getString("岗位"));
					vv.setDeptname(a1.getString("部门"));
					vv.setCheck_score(Double.parseDouble(a1.getString("本月得分")));
					vv.setRemark(a1.getString("备注"));
					if(a1.getString("加扣分项")!=null&&!a1.getString("加扣分项").equals("")){
					vv.setBscore(Double.parseDouble(a1.getString("加扣分项")));
					}
					vvs.add(vv);
				}
			}

		}
		JSONArray jsonArray = JSONArray.fromObject(this.getVvs());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(jsonArray);
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
			Label labelBa3 = new Label(9, 0, "加扣分项", cellFormat1);// 第五列1 行

			ws.addCell(labelId);
			ws.addCell(labelName);
			ws.addCell(labelMeName);
			ws.addCell(labelMeName2);
			ws.addCell(labelDate);
			ws.addCell(labelDa);
			ws.addCell(labelSt);
			ws.addCell(labelBa);
			ws.addCell(labelBa2);
			ws.addCell(labelBa3);

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
				Label labelres_bb = new Label(9, i + 2, a1.getString("加扣分项")
						.toString());
				ws.addCell(labelres_i);
				ws.addCell(labelres_c);
				ws.addCell(labelres_d);
				ws.addCell(labelres_s);
				ws.addCell(labelres_p);
				ws.addCell(labelres_x);
				ws.addCell(labelres_b);
				ws.addCell(labelres_bb);

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
				Label labelres_bb = new Label(9, i + 2, a1.getString("加扣分项")
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
				ws.addCell(labelres_bb);
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
