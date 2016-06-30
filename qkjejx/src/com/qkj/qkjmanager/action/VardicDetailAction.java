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
import org.iweb.sys.dao.DepartmentDAO;
import org.iweb.sys.dao.KpiDAO;
import org.iweb.sys.dao.UserDAO;
import org.iweb.sys.domain.Department;
import org.iweb.sys.domain.IndexDetail;
import org.iweb.sys.domain.User;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.qkjmanager.dao.VardicDao;
import com.qkj.qkjmanager.dao.VardicDetailDao;
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
	private Department dept;
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String aArray;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;纵向考核管理";


	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

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
	
	
	public String listbydept() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_VERTICLIST");
		try {
			map.clear();
			if (vardic == null) {
				vardic = new Vartic();
			}else{
				//查询考核部门信息
				String u_code=vardic.getU_code();
				Date km=vardic.getCheck_ym();
				vardic.setCheck_ym(km);
				UserDAO ud=new UserDAO();
				DepartmentDAO d=new DepartmentDAO();
				map.clear();
				map.put("dept_code", u_code);
				this.setDept((Department) d.list(map).get(0));
				
				//查询部门下面纵向考核的kpi
				map.clear();
				map.put("dept_code", vardic.getU_code());
				map.put("isdept", 1);//纵向考核
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
			//查询被考核人信息
			UserDAO ud=new UserDAO();
			KpiDAO kd=new KpiDAO();
			if(vardic!=null){
				if(vardic.getAcheck_user()!=null && !vardic.getAcheck_user().equals("")){//纵向考核人
					User user=new User();
					user=(User) ud.get(vardic.getAcheck_user());
					if(user!=null){
						map.clear();
						map.put("type", 2);//职务kpi中有部门取值
						map.put("positionid", user.getPosition());
						List<IndexDetail> ids=new ArrayList<>();
						ids=kd.list(map);
						if(ids.size()>0){//说明职务kpi中有部门取值
							for(int i=0;i<ids.size();i++){
								IndexDetail id=new IndexDetail();
								id=ids.get(i);
								map.clear();
								map.put("dept_code", id.getPosition_dept());//查询部门取值中的部门的kpi
								SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM");
								String d = sdf.format(vardic.getCheck_ym());
						        map.put("check_ym", d);
								List<IndexDetail> idsdept=new ArrayList<>();
								idsdept=kd.list(map);
								if(idsdept.size()>0){
									for(int j=0;j<idsdept.size();j++){
										IndexDetail iddept=new IndexDetail();
										iddept=idsdept.get(j);
										if(id.getKpi().equals(iddept.getKpi())){//职务的部门取值与部门分数中的kpi相等
											//查询部门kpi得分
											tsum=tsum+id.getWeight()*iddept.getGoal();//人员总分=职务kpi比重*部门相应kpi得分
										}
									}
								}
							}
							
						}
						tsum=tsum+sum;
						//修改总得分
						vardic.setAy_totelScore(tsum);
						zdao.saveay(vardic);
					}
				}else{//纵向考核部门
					//查询部门kpi
						map.clear();
						map.put("dept_code", vardic.getAcheck_dept());
						List<IndexDetail> ids=new ArrayList<>();
						ids=kd.list(map);
						if(ids.size()>0){
							for(int i=0;i<ids.size();i++){
								IndexDetail id=new IndexDetail();
								id=ids.get(i);
								//查询与此kpi相等且取此部门分数的职务给所有职务加上分数
								map.clear();
								map.put("kpi", id.getKpi());
								map.put("position_dept", id.getDept_code());
								List<IndexDetail> idsdept=new ArrayList<>();
								idsdept=kd.list(map);
								if(idsdept.size()>0){
									for(int j=0;j<idsdept.size();j++){
										IndexDetail iddept=new IndexDetail();
										iddept=idsdept.get(j);
										//查询属于此职务的所有人员
										List<User> us=new ArrayList<>();
										map.put("position", iddept.getPosition_id());
										us=ud.list(map);
										if(us.size()>0){
											for(int h=0;h<us.size();h++){
												User upate=new User();
												upate=us.get(h);
												//修改此人在考核时间中的考核信息
												SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM");
												String d = sdf.format(vardic.getCheck_ym());
												map.clear();
										        map.put("check_ym", d);
										        map.put("acheck_user", upate.getUuid());
												List<Vartic> v=new ArrayList();
												VardicDao vds=new VardicDao();
												v=vds.listByPosition(map);
											}
											
										}
									}
								}
							}
						}
						
				}
			}
			//查询被考核人的职务kpi中是否有部门权重
			
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
			}else{
				Double tsum1=0.00;
				//哪些人有此部门权重
				List<User> u1=new ArrayList();
				map.clear();
				if(vardic.getAcheck_usercode()!=null){
					map.put("position_dept", vardic.getAcheck_dept());
				}else{
					map.put("position_dept", vardic.getAcheck_usercode());
				}
				
				map.put("type", 2);
				u1=ud.listBypro(map);
				if(u1.size()>0){//有部门权重 则给人加上部门得分*权重
					for(int i=0;i<u1.size();i++){
						User user=new User();
						user=u1.get(i);
						SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM");
				        String d = sdf.format(vardic.getCheck_ym());
						//查询本月这个人的分数主表id
						map.clear();
						map.put("check_ym", d);
						map.put("acheck_user", user.getUuid());
						List<Vartic> vs=new ArrayList();
						vs=zdao.list(map);
						//查询部门分数可能是多个
						if(vs.size()>0){
							map.clear();
					        map.put("check_yms", d);
					        map.put("acheck_usercode", vardic.getAcheck_usercode());
					        map.put("position_id", u.get(i).getPosition());
					        map.put("uuid", vs.get(0).getUuid());
							List<Vartic> v=new ArrayList();
							VardicDao vds=new VardicDao();
							v=vds.listByPosition(map);
							if(v.size()>0){
								//职务kpi中部门与此一样的kpi
									for(int j=0;j<v.size();j++){
										Vartic v2=new Vartic();
										v2=v.get(j);
										if(user.getPd().equals(v2.getAcheck_usercode())){
											tsum1=tsum1+v2.getTscore()*user.getW();//部门得分*个人权重
											break;
										}
									}
									
									//tsum=tsum+sum;
									//修改总得分
									vardic.setAy_totelScore(tsum1);
									zdao.saveay(vardic);
									
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
			}else{
				//哪些人有此部门权重
				List<User> u1=new ArrayList();
				Double tsum1=0.00;
				map.clear();
				if(vardic.getAcheck_usercode()!=null){
					map.put("position_dept", vardic.getAcheck_dept());
				}else{
					map.put("position_dept", vardic.getAcheck_usercode());
				}
				
				map.put("type", 2);
				u1=ud.listBypro(map);
				if(u1.size()>0){//有部门权重 则给人加上部门得分*权重
					for(int i=0;i<u1.size();i++){
						User user=new User();
						user=u1.get(i);
						SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM");
				        String d = sdf.format(vardic.getCheck_ym());
						//查询本月这个人的分数主表id
						map.clear();
						map.put("check_ym", d);
						map.put("acheck_user", user.getUuid());
						List<Vartic> vs=new ArrayList();
						vs=zdao.list(map);
						//查询部门分数可能是多个
						if(vs.size()>0){
							map.clear();
					        map.put("check_yms", d);
					        map.put("acheck_usercode", vardic.getAcheck_usercode());
					        map.put("position_id", u.get(i).getPosition());
					        map.put("uuid", vs.get(0).getUuid());
							List<Vartic> v=new ArrayList();
							VardicDao vds=new VardicDao();
							v=vds.listByPosition(map);
							if(v.size()>0){
								//职务kpi中部门与此一样的kpi
									for(int j=0;j<v.size();j++){
										Vartic v2=new Vartic();
										v2=v.get(j);
										if(user.getPd().equals(v2.getAcheck_usercode())){
											tsum1=tsum1+v2.getTscore()*user.getW();//部门得分*个人权重
											break;
										}
									}
									
									//tsum=tsum+sum;
									//修改总得分
									vardic.setAy_totelScore(tsum1);
									zdao.saveay(vardic);
									
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
