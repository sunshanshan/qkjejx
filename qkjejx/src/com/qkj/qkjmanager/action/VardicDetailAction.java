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
import org.iweb.sys.dao.KpiDAO;
import org.iweb.sys.dao.UserDAO;
import org.iweb.sys.domain.IndexDetail;
import org.iweb.sys.domain.User;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.qkjmanager.dao.VardicDao;
import com.qkj.qkjmanager.dao.VardicDetailDao;
import com.qkj.qkjmanager.domain.Score;
import com.qkj.qkjmanager.domain.Vartic;
import com.qkj.qkjmanager.domain.VarticDetail;

public class VardicDetailAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(VardicDetailAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private VardicDetailDao dao = new VardicDetailDao();
	private VardicDao zdao=new VardicDao();
	private VarticDetail vd;
	private List<VarticDetail> vds;
	
	private Vartic vardic;
	private List<IndexDetail> ids;
	private User user;
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String aArray;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;纵向考核管理";


	public List<IndexDetail> getIds() {
		return ids;
	}

	public void setIds(List<IndexDetail> ids) {
		this.ids = ids;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	public Vartic getVardic() {
		return vardic;
	}

	public void setVardic(Vartic vardic) {
		this.vardic = vardic;
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

	
	public VarticDetail getVd() {
		return vd;
	}

	public void setVd(VarticDetail vd) {
		this.vd = vd;
	}

	public List<VarticDetail> getVds() {
		return vds;
	}

	public void setVds(List<VarticDetail> vds) {
		this.vds = vds;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public String getaArray() {
		return aArray;
	}

	public void setaArray(String aArray) {
		this.aArray = aArray;
	}

	public String list() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_VERTICLIST");
		try {
			map.clear();
			if (vardic == null) {
				vardic = new Vartic();
			}else{
				//查询考核人的用户信息
				String userid=vardic.getU_id();
				Date km=vardic.getCheck_ym();
				vardic.setCheck_ym(km);
				UserDAO ud=new UserDAO();
				this.setUser((User)ud.get(userid));
				
				//查询部门下面职务的kpi
				map.clear();
//				map.put("dept_code", vardic.getU_code());
				map.put("isdept", 1);//纵向考核
				map.put("positionid", user.getPosition());//职务
				map.put("type", 1);//查询职务的kpi
				IndexDetail id=new IndexDetail();
				KpiDAO kpid=new KpiDAO();
				this.setIds(kpid.list(map));
			
			}
			
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;纵向考核列表";
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
				this.setVardic(null);
				setMessage("你没有选择任何操作!");
			} else if ("add".equals(viewFlag)) {
				this.setVardic(null);
			} else if ("mdy".equals(viewFlag)) {
				if (!(vardic == null || vardic.getUuid() == null)) {
					this.setVardic((Vartic) zdao.get(vardic.getUuid()));
					map.clear();
					map.put("score_id", vardic.getUuid());
					this.setVds(dao.list(map));
					
				} else {
					this.setVardic(null);
				}
			} else {
				this.setVardic(null);
				setMessage("无操作类型!");
			}
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!load 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!load 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	public String add() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_VERTICLIST_ADD");
		try {
			dao.startTransaction();
			/**
			 * 填加主表
			 */
			vardic.setCheck_user(ContextHelper.getUserLoginUuid());
			vardic.setCheck_date(new Date());
			vardic.setLm_user(ContextHelper.getUserLoginUuid());
			vardic.setLm_time(new Date());
			vardic.setTypea(1);
			vardic.setCheck_score(0.00);
			vardic.setCheck_date(new Date());
			zdao.add(vardic);
			
			/**
			 * 填加子表
			 */
			Double sum=0.00;
			if(aArray!=null){
				aArray=aArray.replace(" ", "");
				String aa[]=aArray.split(";");
				for(int i=0;i<aa.length;i++){
					vd=new VarticDetail();
					vd.setScore_id(vardic.getUuid());
					String index=aa[i];
					String arr[] = index.split(",");
					if(arr[1]!=null && arr[1]!="")vd.setCheck_index(Integer.parseInt(arr[1]));
					if(arr[2]!=null && arr[2]!="")vd.setCheck_score(Double.parseDouble(arr[2]));
					if(arr[3]!=null && arr[3]!="")vd.setCheck_goal(Double.parseDouble(arr[3]));
					vd.setCheck_date(new Date());
					sum=sum+Double.parseDouble(arr[3]);
					//vd.setCheck_index(Double.parseDouble(arr[0]));
					dao.add(vd);
				}
			}
			
			/**
			 * 修改主表分数横加纵
			 */
			//修改纵向总分
			vardic.setCheck_score(sum);
			zdao.saveScore(vardic);
			
			/**
			 * 修改总分数 横+纵
			 */
			Double tsum=0.00;
			//查询被考核人的职务kpi中是否有部门权重
			UserDAO ud=new UserDAO();
			List<User> u=new ArrayList();
			map.clear();
			map.put("uuid", vardic.getAcheck_user());
			map.put("type", 2);
			u=ud.listBypro(map);
			if(u.size()>0){//有部门权重 则加上部门得分*权重
				//查询部门分数可能是多个
				SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM");
		        String d = sdf.format(vardic.getCheck_ym());
		        map.clear();
		        map.put("check_yms", d);
		        map.put("acheck_usercode", vardic.getAcheck_usercode());
		        map.put("position_id", u.get(0).getPosition());
		        map.put("uuid", vardic.getUuid());
				List<Vartic> v=new ArrayList();
				VardicDao vds=new VardicDao();
				v=vds.listByPosition(map);
				if(v.size()>0){
					//职务kpi中部门与此一样的kpi
					for(int j=0;j<u.size();j++){
						User user=new User();
						user=u.get(j);
						for(int i=0;i<v.size();i++){
							Vartic v2=new Vartic();
							v2=v.get(i);
							if(user.getPd().equals(v2.getAcheck_usercode())){
								tsum=tsum+v2.getTscore()*user.getW();//部门得分*个人权重
								break;
							}
						}
					}
					
				}
			}
			tsum=tsum+sum;
			
			//修改总得分
			vardic.setAy_totelScore(tsum);
			zdao.saveay(vardic);
			dao.commitTransaction();
			//addProcess("CLOSEORDER_ADD", "新增结案提货单", ContextHelper.getUserLoginUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}
		finally {
			dao.endTransaction();
		}
		return SUCCESS;
	}

	public String save() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_VERTICLIST_MDY");
		try {
			dao.startTransaction();
			this.setVardic((Vartic) zdao.get(vd.getScore_id()));
			dao.save(vd);
			
			/**
			 * 修改主表分数横加纵
			 */
			//修改纵向总分
			zdao.saveBycheck(vardic.getUuid().toString());
			Vartic c=new Vartic();
			c=(Vartic) zdao.get(vardic.getUuid());
			Double sum=c.getCheck_score();
			/**
			 * 修改总分数 横+纵
			 */
			Double tsum=0.00;
			//查询被考核人的职务kpi中是否有部门权重
			UserDAO ud=new UserDAO();
			List<User> u=new ArrayList();
			map.clear();
			map.put("uuid", vardic.getAcheck_user());
			map.put("type", 2);
			u=ud.listBypro(map);
			if(u.size()>0){//有部门权重 则加上部门得分*权重
				//查询部门分数可能是多个
				SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM");
		        String d = sdf.format(vardic.getCheck_ym());
		        map.clear();
		        map.put("check_yms", d);
		        map.put("acheck_usercode", vardic.getAcheck_usercode());
		        map.put("position_id", u.get(0).getPosition());
		        map.put("uuid", vardic.getUuid());
				List<Vartic> v=new ArrayList();
				VardicDao vds=new VardicDao();
				v=vds.listByPosition(map);
				if(v.size()>0){
					//职务kpi中部门与此一样的kpi
					for(int j=0;j<u.size();j++){
						User user=new User();
						user=u.get(j);
						for(int i=0;i<v.size();i++){
							Vartic v2=new Vartic();
							v2=v.get(i);
							if(user.getPd().equals(v2.getAcheck_usercode())){
								tsum=tsum+v2.getTscore()*user.getW();//部门得分*个人权重
								break;
							}
						}
					}
					
				}
			}
			tsum=tsum+sum;
			
			//修改总得分
			vardic.setAy_totelScore(tsum);
			zdao.saveay(vardic);
			
			dao.commitTransaction();
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!save 数据更新失败:", e);
			throw new Exception(this.getClass().getName() + "!save 数据更新失败:", e);
		}
		finally {
			dao.endTransaction();
		}
		return SUCCESS;
	}

	public String del() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_VERTICLIST_DEL");
		try {
			dao.del(vardic);
			setMessage("删除成功!ID=" + vardic.getUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}
	

	
}
