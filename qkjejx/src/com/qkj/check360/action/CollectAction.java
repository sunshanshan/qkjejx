package com.qkj.check360.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.iweb.sys.ToolsUtil;
import org.iweb.sys.dao.UserDAO;
import org.iweb.sys.domain.User;

import com.opensymphony.xwork2.ActionSupport;
import com.qkj.check360.dao.CheckDao;
import com.qkj.check360.dao.ScoreDao;
import com.qkj.check360.dao.SonScoreDao;
import com.qkj.check360.domain.Capacity;
import com.qkj.check360.domain.Index360;
import com.qkj.check360.domain.IndexCheck;
import com.qkj.check360.domain.Remark360;
import com.qkj.check360.domain.Score360;
import com.qkj.check360.domain.SonRemark360;
import com.qkj.check360.domain.SonScore360;


public class CollectAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(CollectAction.class);
	private Map<String, Object> map = new HashMap<String, Object>();
	private ScoreDao dao = new ScoreDao();
	private Score360 score;
	private List<Score360> scores;
	private SonScoreDao sondao = new SonScoreDao();
	private SonScore360 sonScore;
	private List<SonScore360> sonScores;
	private List<SonRemark360> sonremarks;
	private IndexCheck ic;
	
	private List<Remark360> remark360s;
	
	private Capacity capa;
	private Index360 index360;
	private List<Index360> index360s;
	private User user;
	private String message;
	private String path = "<a href='/manager/default'>首页</a>&nbsp;&gt;&nbsp;360成绩管理";
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Index360> getIndex360s() {
		return index360s;
	}

	public void setIndex360s(List<Index360> index360s) {
		this.index360s = index360s;
	}

	public Index360 getIndex360() {
		return index360;
	}

	public void setIndex360(Index360 index360) {
		this.index360 = index360;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Capacity getCapa() {
		return capa;
	}

	public void setCapa(Capacity capa) {
		this.capa = capa;
	}

	public List<SonRemark360> getSonremarks() {
		return sonremarks;
	}

	public void setSonremarks(List<SonRemark360> sonremarks) {
		this.sonremarks = sonremarks;
	}

	public List<Remark360> getRemark360s() {
		return remark360s;
	}

	public void setRemark360s(List<Remark360> remark360s) {
		this.remark360s = remark360s;
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


	public String loadSonView() throws Exception {
		try {
			map.clear();
			map.putAll(ToolsUtil.getMapByBean(sonScore));
			this.setSonScores(sondao.listview(map));
			if(sonScores.size()>0){
				//this.setScore((Score360) dao.get(sonScores.get(0).getScore_id().toString()));
				map.clear();
				map.put("score_id", sonScores.get(0).getScore_id());
				this.setSonremarks(sondao.listremark(map));
			}
			
		} catch (Exception e) {
			log.error(this.getClass().getName() + "!load 读取数据错误:", e);
			throw new Exception(this.getClass().getName() + "!load 读取数据错误:", e);
		}
		return SUCCESS;
	}
	
	public String loadDetail() throws Exception {
		if(score!=null&&score.getCheck_ym()!=null&&score.getUser_id()!=null){
			CheckDao checkdao = new CheckDao();
			UserDAO ud=new UserDAO();
			this.setIndex360s(checkdao.list(null));
			//查询被考核人
			this.setUser((User) ud.get(score.getUser_id()));
			System.out.println(index360s.size()+"aaaaaaaaaaaaaaaaaaaaaaaaaaa");
		}else{
		this.setMessage("无操作类型");
		}
		return SUCCESS;
	}
	
	public String loadIndexDetail() throws Exception {
		if(score!=null&&score.getCheck_ym()!=null&&score.getUser_id()!=null){
			CheckDao checkdao = new CheckDao();
			UserDAO ud=new UserDAO();
			this.setIndex360s(checkdao.list(null));
			//查询被考核人
			this.setUser((User) ud.get(score.getUser_id()));
		}else{
		this.setMessage("无操作类型");
		}
		return SUCCESS;
	}
	
	public String loadAbst() throws Exception {
		if(score!=null&&score.getCheck_ym()!=null&&score.getUser_id()!=null){
			CheckDao checkdao = new CheckDao();
			UserDAO ud=new UserDAO();
			this.setIndex360s(checkdao.list(null));
			//查询被考核人
			this.setUser((User) ud.get(score.getUser_id()));
		}else{
		this.setMessage("无操作类型");
		}
		return SUCCESS;
	}
	
	public void exportExcel() throws Exception {
		ReportAction ra=new ReportAction();
		ra.exportExcel();
	}
	
}
