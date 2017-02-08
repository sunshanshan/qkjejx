package com.qkj.check360.action;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.struts2.ServletActionContext;
import org.iweb.sys.Parameters;

import com.opensymphony.xwork2.ActionContext;
import com.qkj.check360.domain.Score360;

public class ReportAction {
	/*public static  String conn="jdbc:sqlserver://rdsjqyjmzjrjr3q.sqlserver.rds.aliyuncs.com:3433;database=jxceshi";
	public static   String user="jxceshi";
	public static   String pwd="Iloveqkj2646it";*/
	
	public static  String conn="jdbc:sqlserver://rdsjqyjmzjrjr3q.sqlserver.rds.aliyuncs.com:3433;database=qqkjjx";
	public static   String user="huzhujx";
	public static   String pwd="iloveqkj2646huzhums02";
	private Score360 score;
	private String jaspername;
	
	public String getJaspername() {
		return jaspername;
	}

	public void setJaspername(String jaspername) {
		this.jaspername = jaspername;
	}

	public Score360 getScore() {
		return score;
	}

	public void setScore(Score360 score) {
		this.score = score;
	}

	public static String getConn() {
		return conn;
	}

	public static void setConn(String conn) {
		ReportAction.conn = conn;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		ReportAction.user = user;
	}

	public static String getPwd() {
		return pwd;
	}

	public static void setPwd(String pwd) {
		ReportAction.pwd = pwd;
	}

	public void exportExcel(){
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		try {
			
			//response.setCharacterEncoding("UTF-8");  
			//得到jasper文件
			String a=Parameters.getAbsolutePath();
			String url=a+"/WEB-INF/jsp/check360/jasper/"+jaspername;
			Connection con =null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				con = DriverManager.getConnection(
				conn, user, pwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			HashMap hm = new HashMap();
			if(score!=null&&score.getCheck_ym()!=null&&score.getUser_id()!=null){
				hm.put("check_ym", score.getCheck_ym());
				hm.put("user_id", score.getUser_id());
			}else{
				hm.put("user_id", 0);
			}
			String path=a+"/WEB-INF/jsp/check360/jasper/";
			hm.put("SUBREPORT_DIR", "D:\\poject\\iReport-5.5.1\\ireport\\libs\\");
			JasperReport jasperReport= (JasperReport)JRLoader.loadObjectFromFile(url);
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,hm,con);
			response.setHeader("Content-Disposition", "attachment;filename=first.xls");
			response.setContentType("application/vnd_ms-excel");
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(("huizong.xls").getBytes(), "iso-8859-1"));
	        OutputStream os= new BufferedOutputStream(response.getOutputStream());  
	        JRXlsExporter exporter=new JRXlsExporter();
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, response.getOutputStream());
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
	        exporter.exportReport();
	        os.flush();  
	        os.close();  
	        response.flushBuffer();
	     			
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try {
		String a=Parameters.getAbsolutePath();
		String fileName=a+"WEB-INF/jsp/check360/jasper/main_ass.jasper";
		String url = "jdbc:jtds:sqlserver://rdsjqyjmzjrjr3q.sqlserver.rds.aliyuncs.com:3433;DatabaseName=jxceshi";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
		.newInstance();
		Connection conn = DriverManager.getConnection(url, "jxceshi",
		"Iloveqkj2646it");
		HashMap hm = new HashMap();
		if(score!=null&&score.getCheck_ym()!=null&&score.getUser_id()!=null){
			hm.put("check_ym", score.getCheck_ym());
			hm.put("user_id", score.getUser_id());
		}else{
			hm.put("user_id", 0);
		}
		// Fill the report using an empty data source
		JasperPrint print = JasperFillManager.fillReport(fileName, hm,conn);
		JRHtmlExporter exporter_html  =   new  JRHtmlExporter();
		exporter_html.setParameter(JRExporterParameter.OUTPUT_WRITER,
		out);//这里说明，我们要将报表打印到页面上
		exporter_html.setParameter(JRExporterParameter.JASPER_PRINT, print);  
		exporter_html.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);//注意：这里必须为false，否则显示异常
		exporter_html.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);//这里是控制行的样式，可以有，也可以去掉
		exporter_html.exportReport();
		out.flush();
		System.out.println("success!");

		} catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
		}
		}
	
	/**//*
		 *   * 生成HTML报表显示  
		 */
	public void selectHtmlReport(List list, HttpServletResponse response,
			String url, Map map) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		File reportFile = new File(url);
		JasperReport jasperReport = null;
		try {
			jasperReport = (JasperReport) JRLoader
					.loadObjectFromFile(reportFile.getPath());
		} catch (JRException e) {
			e.printStackTrace();
		}

		JRBeanCollectionDataSource jrbean = new JRBeanCollectionDataSource(list);
		JasperPrint jasperPrint = null;
		try {
			jasperPrint = JasperFillManager.fillReport(jasperReport, map,
					jrbean);
		} catch (JRException e) {
			e.printStackTrace();
		}
		JRHtmlExporter exporter = new JRHtmlExporter();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
				Boolean.FALSE);
		exporter.setParameter(
				JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		try {
			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/**//*
		 *   * 导出EXCEL报表  
		 */
	public void selectExcelReport(List list, HttpServletResponse response,
			String url, Map map, String reportName) {
		try {
			// 加载jasper文件
			File reportFile = new File(url);
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObjectFromFile(reportFile.getPath());
			// 装载数据
			JRBeanCollectionDataSource jrbean = new JRBeanCollectionDataSource(
					list);
			// 构造jasperPrint对象
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, map, jrbean);
			// 输出流
			ByteArrayOutputStream oStream = new ByteArrayOutputStream();
			// 构造输出对象
			JExcelApiExporter exporter = new JExcelApiExporter();
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
					"./image?image=");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStream);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE); // 删除记录最下面的空行
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);// 删除多余的ColumnHeader
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);// 显示边框
			exporter.exportReport();
			// excel文件名
			String fileName = reportName + ".xls";
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ URLEncoder.encode(fileName, "utf-8") + "\"");
			// 写输出流
			byte[] bytes = oStream.toByteArray();
			if (bytes != null && bytes.length > 0) {
				response.setContentType("application/vnd.ms-excel;charset=utf-8");
				response.setContentLength(bytes.length);
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(bytes, 0, bytes.length);
				ouputStream.flush();
				ouputStream.close();
			} else {

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 *   * 功能：导出多sheet报表的处理方法   * 方法名:selectAllExcelReport   * @param List list
	 * 数据集   * @param HttpServletResponse response   * @param String url 解析的报表文件
	 *   * @param String reportName 生成excel保存的名字   * @author 郭洪治   * 创建时间：Nov 3,
	 * 2009 2:08:31 PM  
	 */
	public void selectAllExcelReport(List list, HttpServletResponse response,
			String url, String reportName) {
		try {
			// 加载jasper文件
			File reportFile = new File(url);
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObjectFromFile(reportFile.getPath());
			List jasperPrintList = new ArrayList();
			int listSize = list.size();
			String sheetNamesArray[] = new String[listSize];
			for (int i = 0; i < listSize; i++) {
				Map eachMap = (Map) list.get(i);
				List eachList = (List) eachMap.get("ls");
				sheetNamesArray[i] = (String) eachMap.get("title");
				// 装载数据
				JRBeanCollectionDataSource jrbean = new JRBeanCollectionDataSource(
						eachList);
				// 构造jasperPrint对象
				JasperPrint jasperPrint = JasperFillManager.fillReport(
						jasperReport, eachMap, jrbean);
				jasperPrintList.add(jasperPrint);
			}
			// 输出流
			ByteArrayOutputStream oStream = new ByteArrayOutputStream();
			// 构造输出对象
			JExcelApiExporter exporter = new JExcelApiExporter();
			exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
					"./image?image=");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
					jasperPrintList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStream);
			exporter.setParameter(
					JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
					Boolean.TRUE); // 删除记录最下面的空行
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
					Boolean.FALSE);// 删除多余的ColumnHeader
			exporter.setParameter(
					JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
					Boolean.FALSE);// 显示边框
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES,
					sheetNamesArray);
			exporter.exportReport();

			// 写输出流
			byte[] bytes = oStream.toByteArray();
			if (bytes != null && bytes.length > 0) {
				response.reset();
				response.setContentType("application/vnd.ms-excel;charset=utf-8");
				response.setContentLength(bytes.length);
				// excel文件名
				String fileName = reportName + ".xls";
				response.setHeader(
						"Content-Disposition",
						"attachment;filename=\""
								+ URLEncoder.encode(fileName, "utf-8") + "\"");
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(bytes, 0, bytes.length);
				ouputStream.flush();
				ouputStream.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	
	public HashMap export(HttpServletRequest request,
			HttpServletResponse response, String reportFilePath, Map params)
			throws JRRuntimeException, SQLException {
		StringBuffer sbuffer = new StringBuffer();
		int pageIndex = 0;
		int lastPageIndex = 0;
		int perpagecount = 100;
		HashMap values = new HashMap();
		try {
			Connection con = con = DriverManager.getConnection(
					conn, user, pwd);
			con.setAutoCommit(true);
			try {
				
				String a=Parameters.getAbsolutePath();
				String url=a+"/WEB-INF/jsp/check360/jasper/"+reportFilePath;
				JasperReport jasperReport= (JasperReport)JRLoader.loadObjectFromFile(url);
				JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,params,con);
				
				values.put("lastPageIndex", lastPageIndex);
				values.put("pageIndex", pageIndex);
				JRHtmlExporter exporter = new JRHtmlExporter();
				request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,jasperPrint);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER,sbuffer);
				exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,"./servlets/image?image=");
				exporter.setParameter(JRExporterParameter.PAGE_INDEX, pageIndex);
				exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
				exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
				exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
				
				values.put("sbuffer", sbuffer);
			}catch (Exception e) {
				e.printStackTrace();
				throw new JRRuntimeException("�ڵ���Html��ʽ����ʱ�������!");
			} finally {
				con.close();
			}
		} catch (Exception ioe) {
			ioe.printStackTrace();
			throw new JRRuntimeException("��Response��ȡ��PrintWriterʱ�������!");
		}
		return values;
	}

}
