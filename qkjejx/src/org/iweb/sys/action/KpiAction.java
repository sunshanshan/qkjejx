package org.iweb.sys.action;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;
import org.iweb.sys.ContextHelper;
import org.iweb.sys.dao.KpiDAO;
import org.iweb.sys.domain.IndexDetail;

import com.opensymphony.xwork2.ActionSupport;

public class KpiAction extends ActionSupport {
	private KpiDAO dao = new KpiDAO();
	private Map<String, Object> map = new HashMap<String, Object>();
	private List<IndexDetail> inds;
	private List<IndexDetail> inps;

	public List<IndexDetail> getInds() {
		return inds;
	}

	public void setInds(List<IndexDetail> inds) {
		this.inds = inds;
	}

	public List<IndexDetail> getInps() {
		return inps;
	}

	public void setInps(List<IndexDetail> inps) {
		this.inps = inps;
	}

	public String report() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		map.clear();
		map.put("dept", 1);
		this.setInds(dao.listPrint(map));
		map.clear();
		map.put("position", 1);
		this.setInps(dao.listPrint(map));
		String fileName = ContextHelper.getUserLoginName() + "汇总表格";
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
		ws.setColumnView(8, 15);
		ws.setColumnView(9, 35);
		ws.setColumnView(10, 35);
		WritableFont font1 = new WritableFont(WritableFont.ARIAL, 11);

		WritableCellFormat cellFormat1 = new WritableCellFormat(font1);

		// 查询数据库中所有的数据
		// 要插入到的Excel表格的行号，默认从0开始
		Label labelId = new Label(0, 0, "所属部门", cellFormat1);// 表示第1列1个
		Label labelName = new Label(1, 0, "所属职务", cellFormat1);// 第2列1个
		Label labelMeName = new Label(2, 0, "取部门值的部门", cellFormat1);// 第五列1 行
		Label labelMeName2 = new Label(3, 0, "是否取部门值", cellFormat1);// 第五列1 行
		Label labelDate = new Label(4, 0, "横纵考核", cellFormat1);// 第五列1 行
		Label labelDa = new Label(5, 0, "kpi", cellFormat1);// 第五列1 行
		Label labelSt = new Label(6, 0, "周期", cellFormat1);// 第五列1 行
		Label labelBa = new Label(7, 0, "权重", cellFormat1);// 第五列1 行
		Label labelBa2 = new Label(8, 0, "评分方法", cellFormat1);// 第五列1 行
		Label labelBa3 = new Label(9, 0, "定义", cellFormat1);// 第五列1 行
		Label labelBa4 = new Label(10, 0, "定义详细", cellFormat1);// 第五列1 行
		Label labelBa5 = new Label(11, 0, "横向考核部门", cellFormat1);// 第五列1 行
		Label labelBa6 = new Label(12, 0, "横向考核职务", cellFormat1);// 第五列1 行

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
		ws.addCell(labelBa5);
		ws.addCell(labelBa6);

		for (short i = 0; i < inps.size(); i++) {
			// 创建一行，在页sheet上
			Label au = new Label(0, i + 1,inps.get(i).getPo_deptname()==null?
					"无":inps.get(i).getPo_deptname());
			Label ap = new Label(1, i + 1,inps.get(i).getPostion()==null?"无":inps.get(i).getPostion());
			Label ad = new Label(2, i + 1, inps.get(i).getPdname()==null?"无":inps.get(i).getPdname());
			Label auuid = new Label(3, i + 1,inps.get(i).getTypename() == null ? "无" : inps.get(i).getTypename());
			Label acode = new Label(4, i + 1, inps.get(i).getIsdeptname() == null ? "无" : inps.get(i).getIsdeptname());
			Label score = new Label(5, i + 1, inps.get(i).getKpi()== null ? "无" : inps.get(i).getKpi());
			Label checkdate = new Label(6, i + 1, inps.get(i).getCyc()== null ? "无" : inps.get(i).getCyc());
			Label checkym = new Label(7, i + 1, Double.toString(inps.get(i).getWeight()));
			Label remark = new Label(8, i + 1,inps.get(i).getCount_way() == null ? "无" : inps.get(i).getCount_way());
			Label bscor = new Label(9, i + 1,inps.get(i).getDefinition() == null ? "无" : inps.get(i).getDefinition());
			Label jtype = new Label(10, i + 1,inps.get(i).getCorrectly() == null ? "无" : inps.get(i).getCorrectly());
			
			Label jtype1 = new Label(11, i + 1,inps.get(i).getCheckde() == null ? "无" : inps.get(i).getCheckde());
			Label jtype2 = new Label(12, i + 1,inps.get(i).getCheckp() == null ? "无" : inps.get(i).getCheckp());
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
			
			ws.addCell(jtype1);
			
			ws.addCell(jtype2);

		}
		
		for (short i = 0; i < inds.size(); i++) {
			// 创建一行，在页sheet上
			Label au = new Label(0, i + 1,
					inds.get(i).getDname() == null ? "无" : inds.get(i).getDname());
			Label ap = new Label(1, i + 1,"无");
			Label ad = new Label(2, i + 1, inds.get(i).getPdname()==null?"无":inds.get(i).getPdname());
			Label auuid = new Label(3, i + 1,inds.get(i).getTypename() == null ? "无" : inds.get(i).getTypename());
			Label acode = new Label(4, i + 1, inds.get(i).getIsdeptname() == null ? "无" : inds.get(i).getIsdeptname());
			Label score = new Label(5, i + 1, inds.get(i).getKpi()== null ? "无" : inds.get(i).getKpi());
			Label checkdate = new Label(6, i + 1, inds.get(i).getCyc()== null ? "无" : inds.get(i).getCyc());
			Label checkym = new Label(7, i + 1, Double.toString(inds.get(i).getWeight()));
			Label remark = new Label(8, i + 1,inds.get(i).getCount_way() == null ? "无" : inds.get(i).getCount_way());
			Label bscor = new Label(9, i + 1,inds.get(i).getDefinition() == null ? "无" : inds.get(i).getDefinition());
			Label jtype = new Label(10, i + 1,inds.get(i).getCorrectly() == null ? "无" : inds.get(i).getCorrectly());
			
			Label jtype1 = new Label(11, i + 1,inds.get(i).getCheckde() == null ? "无" : inds.get(i).getCheckde());
			Label jtype2 = new Label(12, i + 1,inds.get(i).getCheckp() == null ? "无" : inds.get(i).getCheckp());
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
			
			ws.addCell(jtype1);
			
			ws.addCell(jtype2);

		}

		// 写进文档
		wwb.write();
		// 关闭Excel工作簿对象
		wwb.close();
		os.close();
		response.flushBuffer();
		return null;
	}
}
