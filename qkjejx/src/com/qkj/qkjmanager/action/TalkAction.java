package com.qkj.qkjmanager.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iweb.sys.ContextHelper;
import org.iweb.sys.Parameters;
import org.iweb.sys.dao.KpiDAO;
import org.iweb.sys.dao.UserDAO;
import org.iweb.sys.domain.IndexDetail;
import org.iweb.sys.domain.User;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.basics.dao.CheckDao;
import com.qkj.basics.domain.Check;
import com.qkj.qkjmanager.dao.TalkDao;
import com.qkj.qkjmanager.dao.VardicDao;
import com.qkj.qkjmanager.dao.VardicDetailDao;
import com.qkj.qkjmanager.domain.Talk;
import com.qkj.qkjmanager.domain.Vartic;
import com.qkj.qkjmanager.domain.VarticDetail;

public class TalkAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(TalkAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private TalkDao dao = new TalkDao();
	private Talk talk;
	private List<Talk> talks;
	private Vartic vardic;
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;纵向考核管理";

	public Vartic getVardic() {
		return vardic;
	}

	public void setVardic(Vartic vardic) {
		this.vardic = vardic;
	}

	public Talk getTalk() {
		return talk;
	}

	public void setTalk(Talk talk) {
		this.talk = talk;
	}

	public List<Talk> getTalks() {
		return talks;
	}

	public void setTalks(List<Talk> talks) {
		this.talks = talks;
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


	public String load() throws Exception {
		try {
			if (null == viewFlag) {
				this.setTalk(null);
				setMessage("你没有选择任何操作!");
			} else if ("add".equals(viewFlag)) {
				this.setTalk(null);
				//查询成绩信息
				VardicDao vd=new VardicDao();
				if(vardic!=null){
					this.setVardic((Vartic) vd.get(vardic.getUuid()));
				}
			} else if ("mdy".equals(viewFlag)) {
				if (!(talk == null || talk.getUuid() == null)) {
					this.setTalk((Talk) dao.get(talk.getUuid()));
				} else {
					this.setTalk(null);
				}
				
				//查询成绩信息
				VardicDao vd=new VardicDao();
				if(vardic!=null){
					this.setVardic((Vartic) vd.get(vardic.getUuid()));
					if(talk==null){
						map.clear();
						map.put("suid", vardic.getUuid());
						this.setTalks(dao.list(map));
						if(talks.size()>0){
							this.setTalk((Talk) dao.list(map).get(0));
						}
					}
				}else{
					if(talk!=null&&talk.getVartic_id()!=null){
						this.setVardic((Vartic) vd.get(talk.getVartic_id()));
					}
				}
			} else {
				this.setTalk(null);
				setMessage("无操作类型!");
			}
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!load 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!load 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	public String add() throws Exception {
		try {
			talk.setAdd_user(ContextHelper.getUserLoginUuid());
			talk.setAdd_time(new Date());
			talk.setLm_time(new Date());
			talk.setLm_user(ContextHelper.getUserLoginUuid());
			dao.add(talk);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		return SUCCESS;
	}

	public String saveLeader() throws Exception {
		try {
			talk.setLm_user(ContextHelper.getUserLoginUuid());
			talk.setLm_time(new Date());
			dao.saveLeader(talk);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!save 数据更新失败:", e);
			throw new Exception(this.getClass().getName() + "!save 数据更新失败:", e);
		}
		return SUCCESS;
	}
	
	public String saveEmp() throws Exception {
		try {
			talk.setLm_user(ContextHelper.getUserLoginUuid());
			talk.setLm_time(new Date());
			dao.saveEmp(talk);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!save 数据更新失败:", e);
			throw new Exception(this.getClass().getName() + "!save 数据更新失败:", e);
		}
		return SUCCESS;
	}
	
	
	public String del() throws Exception {
		try {
			dao.del(talk);
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}
	
}
