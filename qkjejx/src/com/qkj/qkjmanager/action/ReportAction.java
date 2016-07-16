package com.qkj.qkjmanager.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.iweb.sys.ContextHelper;
import org.iweb.sys.ExcelUtil;
import org.iweb.sys.Parameters;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qkj.basics.dao.CheckDao;
import com.qkj.qkjmanager.dao.VardicDao;
import com.qkj.qkjmanager.dao.reportDao;
import com.qkj.qkjmanager.domain.Score;
import com.qkj.qkjmanager.domain.Vartic;
import com.sun.org.apache.bcel.internal.generic.NEW;

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
	private List<Vartic> vardicsc;
	private List<Vartic> vardicsb;
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
	public String list() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_REPORT");
		try {
			map.clear();
			if (vardic == null) {
				vardic = new Vartic();
			}
			ContextHelper.setSearchDeptPermit4Search("SYS_QKJMANAGER_REPORT", map, "apply_depts", "apply_user");
			ContextHelper.SimpleSearchMap4Page("SYS_QKJMANAGER_REPORT", map, vardic, viewFlag);
			this.setPageSize(Integer.parseInt(map.get(Parameters.Page_Size_Str).toString()));
			if(map.get("cym")!=null){
	        	SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM");
		        String d = sdf.format(vardic.getCym());
		        map.remove("cym");
		        map.put("cym", d);
	        }
			this.setVardics(dao.list(map));
			this.setRecCount(dao.getResultCount());
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;纵向考核列表";
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	public String listjx() throws Exception {
		try {
			map.clear();
			if (vardic == null) {
				vardic = new Vartic();
			}
			ContextHelper.setSearchDeptPermit4Search("SYS_QKJMANAGER_CHECKLIST", map, "apply_depts", "apply_user");
			ContextHelper.SimpleSearchMap4Page("SYS_QKJMANAGER_CHECKLIST", map, vardic, viewFlag);
			this.setPageSize(Integer.parseInt(map.get(Parameters.Page_Size_Str).toString()));
	        if(map.get("cym")!=null){
	        	SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM");
		        String d = sdf.format(vardic.getCym());
		        map.remove("cym");
		        map.put("cym", d);
	        }
			this.setVardics(dao.list(map));
			this.setRecCount(dao.getResultCount());
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;纵向考核列表";
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		return SUCCESS;
	}

	public String report() throws Exception {
		if(vardic!=null){
	      SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM");
	      vardic.setCheck_yms(sim.format(vardic.getCheck_ym()));
	      map.put("check_yms", vardic.getCheck_yms());
		}
		ActionContext context = ActionContext.getContext();  
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);  
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);  
		String fileName="汇总表格";
		Vartic v1=new Vartic();
		int count=0;

		this.setVardics(dao.list(map));
		String columnNames[]={"主键","考核年月","被考核人","被考核人部门","考核完成时间","分数"};
		String keys[]={"uuid","check_ym","acheck_username","acheck_deptname","check_date","ay_totelScore"};
		ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(vardics,keys,columnNames).write(os);
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
			response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
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
	
	public String check_sure() throws Exception {
		try {
			CheckDao cd=new CheckDao();
			cd.saveState();
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;列表";
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!list 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!list 读取数据错误:", e);
		}
		return SUCCESS;
	}
}
