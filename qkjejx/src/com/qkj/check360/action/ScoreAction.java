package com.qkj.check360.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.iweb.sys.ContextHelper;
import org.iweb.sys.Parameters;
import org.iweb.sys.ToolsUtil;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.check360.dao.CheckDao;
import com.qkj.check360.dao.IndexCheckDAO;
import com.qkj.check360.dao.IndexDAO;
import com.qkj.check360.dao.ScoreDao;
import com.qkj.check360.dao.SonScoreDao;
import com.qkj.check360.domain.Index;
import com.qkj.check360.domain.Index360;
import com.qkj.check360.domain.IndexCheck;
import com.qkj.check360.domain.Score360;
import com.qkj.check360.domain.SonScore360;


public class ScoreAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(ScoreAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private ScoreDao dao = new ScoreDao();
	private Score360 score;
	private List<Score360> scores;
	private SonScoreDao sondao = new SonScoreDao();
	private SonScore360 sonScore;
	private List<SonScore360> sonScores;
	private IndexCheck ic;
	private Index360 index360;
	private List<Index360> index360s;
	
	private IndexDAO id=new IndexDAO();
	private Index kpi;
	private List<Index> kpis;
	
	private String ic_uuid;
	private String in_uuid;
	private String kpiid;
	private String checkkpis;
	private String scorejson;
	private String callback; 
	private Integer checkeds=0;
	private String sonweight;
	private String remark;
	private String goldjson;
	
	private String message;
	private String viewFlag;
	private int recCount;
	private int pageSize;
	private int currPage;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;360成绩管理";
	
	
	public String getGoldjson() {
		return goldjson;
	}

	public void setGoldjson(String goldjson) {
		this.goldjson = goldjson;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSonweight() {
		return sonweight;
	}

	public void setSonweight(String sonweight) {
		this.sonweight = sonweight;
	}

	public List<Index360> getIndex360s() {
		return index360s;
	}

	public void setIndex360s(List<Index360> index360s) {
		this.index360s = index360s;
	}

	public String getCheckkpis() {
		return checkkpis;
	}

	public void setCheckkpis(String checkkpis) {
		this.checkkpis = checkkpis;
	}

	public Integer getCheckeds() {
		return checkeds;
	}

	public void setCheckeds(Integer checkeds) {
		this.checkeds = checkeds;
	}

	public String getIc_uuid() {
		return ic_uuid;
	}

	public void setIc_uuid(String ic_uuid) {
		this.ic_uuid = ic_uuid;
	}

	public String getIn_uuid() {
		return in_uuid;
	}

	public void setIn_uuid(String in_uuid) {
		this.in_uuid = in_uuid;
	}

	public String getKpiid() {
		return kpiid;
	}

	public void setKpiid(String kpiid) {
		this.kpiid = kpiid;
	}

	public String getScorejson() {
		return scorejson;
	}

	public void setScorejson(String scorejson) {
		this.scorejson = scorejson;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public Index getKpi() {
		return kpi;
	}

	public void setKpi(Index kpi) {
		this.kpi = kpi;
	}

	public List<Index> getKpis() {
		return kpis;
	}

	public void setKpis(List<Index> kpis) {
		this.kpis = kpis;
	}

	public Index360 getIndex360() {
		return index360;
	}

	public void setIndex360(Index360 index360) {
		this.index360 = index360;
	}

	public IndexCheck getIc() {
		return ic;
	}

	public void setIc(IndexCheck ic) {
		this.ic = ic;
	}

	public Score360 getScore() {
		return score;
	}

	public void setScore(Score360 score) {
		this.score = score;
	}

	public List<Score360> getScores() {
		return scores;
	}

	public void setScores(List<Score360> scores) {
		this.scores = scores;
	}

	public SonScore360 getSonScore() {
		return sonScore;
	}

	public void setSonScore(SonScore360 sonScore) {
		this.sonScore = sonScore;
	}

	public List<SonScore360> getSonScores() {
		return sonScores;
	}

	public void setSonScores(List<SonScore360> sonScores) {
		this.sonScores = sonScores;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
		ContextHelper.isPermit("SYS_QKJMANAGER_SCORE360_LIST");
		try {
			map.clear();
			if (score == null) {
				score = new Score360();
			}
			
			ContextHelper.setSearchDeptPermit4Search("SYS_QKJMANAGER_SCORE360_LIST", map, "apply_depts", "apply_user");
			ContextHelper.SimpleSearchMap4Page("SYS_QKJMANAGER_SCORE360_LIST", map, score, viewFlag);
			this.setPageSize(Integer.parseInt(map.get(Parameters.Page_Size_Str).toString()));
			this.setScores(dao.sumlist(map));
			this.setRecCount(dao.getResultCount());
			path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;成绩列表";
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
				this.setScore(null);
				setMessage("你没有选择任何操作!");
			} else if ("add".equals(viewFlag)) {
				this.setScore(null);
			} else if ("mdy".equals(viewFlag)) {
				if (!(score == null || score.getUuid() == null)) {
					this.setScore((Score360) dao.get(score.getUuid()));
				} else {
					this.setScore(null);
				}
				//查询考核人(ic.uuid)在本年度（index360.uuid）是否考核
				map.clear();
				map.put("check_user_id", ic.getUuid());
				map.put("check_ym", index360.getUuid());
				this.setScores(dao.list(map));
				if(scores.size()>0){//已考核
					//考核状态打开时为修改否则
					CheckDao checkdao = new CheckDao();
					index360=(Index360) checkdao.get(index360.getUuid());
					if(index360.getState()!=null && index360.getState()==0){
						//查询考核人信息
						IndexCheckDAO icd=new IndexCheckDAO();
						ic=(IndexCheck) icd.get(ic.getUuid());
						this.setScore(scores.get(0));
						//查询子表
						map.clear();
						map.put("score_id", scores.get(0).getUuid());
						this.setSonScores(sondao.list(map));
						
						this.setCheckeds(2);
					}else{
						this.setCheckeds(1);
					}
					
				}else{
					//查询考核人信息
					IndexCheckDAO icd=new IndexCheckDAO();
					ic=(IndexCheck) icd.get(ic.getUuid());
					//查询kpi
					map.clear();
					map.put("user_id", ic.getUser_id());
					this.setKpis(id.list(map));
				}
				
			} else {
				this.setScore(null);
				setMessage("无操作类型!");
			}
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!load 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!load 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	
	public String view() throws Exception{
		if(sonScores==null){
			this.setScore(null);
		}else{
			map.putAll(ToolsUtil.getMapByBean(sonScores));
		}
		this.setSonScores(sondao.listview(map));
		return SUCCESS;
	}
	
	public void add() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setHeader("Pragma", "no-cache");
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			// 设置参数
			if (!ToolsUtil.isEmpty(ic_uuid)) {
				ic_uuid = java.net.URLDecoder.decode(ic_uuid, "UTF-8");
				log.info("初始化参数成功:" + ic_uuid);
			}
			if (!ToolsUtil.isEmpty(in_uuid)) {
				in_uuid = java.net.URLDecoder.decode(in_uuid, "UTF-8");
				log.info("初始化参数成功:" + in_uuid);
			}
			if (!ToolsUtil.isEmpty(scorejson)) {
				scorejson = java.net.URLDecoder.decode(scorejson, "UTF-8");
				log.info("初始化参数成功:" + scorejson);
			}
			
			if (!ToolsUtil.isEmpty(kpiid)) {
				kpiid = java.net.URLDecoder.decode(kpiid, "UTF-8");
				log.info("初始化参数成功:" + kpiid);
			}
			
			if (!ToolsUtil.isEmpty(checkkpis)) {
				checkkpis = java.net.URLDecoder.decode(checkkpis, "UTF-8");
				log.info("初始化参数成功:" + checkkpis);
			}
			
			if (!ToolsUtil.isEmpty(sonweight)) {
				sonweight = java.net.URLDecoder.decode(sonweight, "UTF-8");
				log.info("初始化参数成功:" + sonweight);
			}
			if (!ToolsUtil.isEmpty(remark)) {
				remark = java.net.URLDecoder.decode(remark, "UTF-8");
				log.info("初始化参数成功:" + remark);
			}
			dao.startTransaction();
			//评分及kpi
			String [] scoreds=scorejson.split(",");
			String [] kpied=kpiid.split(",");
			String [] kpieds=checkkpis.split(",");
			String [] sonw=sonweight.split(",");
			//总分
			Double sum=0.00;
			for(int i=0;i<scoreds.length;i++){
				sum+=Double.parseDouble(scoreds[i])*Double.parseDouble(sonw[i]);
			}
			//查询考核人信息
			IndexCheckDAO icd=new IndexCheckDAO();
			ic=(IndexCheck) icd.get(Integer.parseInt(ic_uuid));
			//添加主表
			score=new Score360();
			score.setCheck_score(sum);
			score.setCheck_gold(sum*ic.getWeight());
			score.setCheck_date(new Date());
			score.setCheck_user_id(Integer.parseInt(ic_uuid));
			score.setWeight(ic.getWeight());
			score.setCheck_ym(Integer.parseInt(in_uuid));
			score.setRemark(remark);
			dao.add(score);
			//添加子表
			for(int i=0;i<kpied.length;i++){
				sonScore=new SonScore360();
				sonScore.setScore_id(score.getUuid());
				sonScore.setCheck_index(Integer.parseInt(kpied[i]));
				sonScore.setCheck_score(Double.parseDouble(scoreds[i]));
				sonScore.setGoal(Double.parseDouble(scoreds[i])*Double.parseDouble(sonw[i]));
				sonScore.setKpi(kpieds[i]);
				sonScore.setWeight(Double.parseDouble(sonw[i]));
				sondao.add(sonScore);
			}
			dao.commitTransaction();
			response.getWriter().print("0");
		} catch (Exception e) {
			response.getWriter().print("1");
			log.error(this.getClass().getName() + "!add 数据添加失败:", e);
			throw new Exception(this.getClass().getName() + "!add 数据添加失败:", e);
		}finally {
			dao.endTransaction();
		}
	}

	public String save() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			if (!ToolsUtil.isEmpty(ic_uuid)) {
				ic_uuid = java.net.URLDecoder.decode(ic_uuid, "UTF-8");
				log.info("初始化参数成功:" + ic_uuid);
			}
			
			if (!ToolsUtil.isEmpty(scorejson)) {//评分
				scorejson = java.net.URLDecoder.decode(scorejson, "UTF-8");
				log.info("初始化参数成功:" + scorejson);
			}
			
			if (!ToolsUtil.isEmpty(kpiid)) {//uuid
				kpiid = java.net.URLDecoder.decode(kpiid, "UTF-8");
				log.info("初始化参数成功:" + kpiid);
			}
			
			if (!ToolsUtil.isEmpty(goldjson)) {//得分
				goldjson = java.net.URLDecoder.decode(goldjson, "UTF-8");
				log.info("初始化参数成功:" + goldjson);
			}
			
			if (!ToolsUtil.isEmpty(remark)) {
				remark = java.net.URLDecoder.decode(remark, "UTF-8");
				log.info("初始化参数成功:" + remark);
			}
			
			if (!ToolsUtil.isEmpty(in_uuid)) {
				in_uuid = java.net.URLDecoder.decode(in_uuid, "UTF-8");
				log.info("初始化参数成功:" + in_uuid);
			}
			
			//评分及kpi
			String [] scoreds=scorejson.split(",");
			String [] kpied=kpiid.split(",");
			String [] goals=goldjson.split(",");
			Double sum=0.00;
			dao.startTransaction();
			for(int i=0;i<kpied.length;i++){
				sonScore=new SonScore360();
				sonScore.setUuid(Integer.parseInt(kpied[i]));;
				sonScore.setCheck_score(Double.parseDouble(scoreds[i]));
				sonScore.setGoal(Double.parseDouble(goals[i]));
				sum+=Double.parseDouble(goals[i]);
				sondao.save(sonScore);
			}
			
			//修改主表备注，及总评分，总得分
			//查询考核人信息
			IndexCheckDAO icd=new IndexCheckDAO();
			ic=(IndexCheck) icd.get(Integer.parseInt(ic_uuid));
			score=new Score360();
			score.setUuid(Integer.parseInt(in_uuid));
			score.setCheck_score(sum);
			score.setCheck_gold(sum*ic.getWeight());
			score.setRemark(remark);
			score.setLm_user(ContextHelper.getUserLoginUuid());
			score.setLm_time(new Date());
			dao.save(score);
			dao.commitTransaction();
			response.getWriter().print("0");
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!save 数据更新失败:", e);
			throw new Exception(this.getClass().getName() + "!save 数据更新失败:", e);
		}finally {
			dao.endTransaction();
		}
		return SUCCESS;
	}

	public String del() throws Exception {
		ContextHelper.isPermit("SYS_QKJMANAGER_SCORE360_DEL");
		try {
			dao.del(score);
			setMessage("删除成功!ID=" + score.getUuid());
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!del 数据删除失败:", e);
			throw new Exception(this.getClass().getName() + "!del 数据删除失败:", e);
		}
		return SUCCESS;
	}

	
}
