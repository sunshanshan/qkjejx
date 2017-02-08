package com.qkj.check360.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.iweb.sys.Parameters;

/**
 * ���ñ������HTML��ʽ����
 */
public class HTMLExport {

	private int page_nums = 1;

	public int getPage_nums() {
		return page_nums;
	}

	public void setPage_nums(int page_nums) {
		this.page_nums = page_nums;
	}

	/**
	 * ��������
	 * 
	 * @param request
	 * @param response
	 * @param reportFilePath
	 * @param params
	 * @param con
	 * @throws SQLException
	 * @throws JasperReportException
	 */
	public void export(HttpServletRequest request,
			HttpServletResponse response, String reportFilePath, Map params)
			throws JRRuntimeException, SQLException {
		StringBuffer sbuffer = new StringBuffer();
		int pageIndex = 0;
		int lastPageIndex = 0;
		int perpagecount = 100;
		HashMap values = new HashMap();
		try {
			System.out.println(ReportAction.getConn());
			PrintWriter out=response.getWriter();
			Connection con = con = DriverManager.getConnection(
					ReportAction.getConn(), ReportAction.getUser(), ReportAction.getPwd());
			con.setAutoCommit(true);
			try {
				
				String a=Parameters.getAbsolutePath();
				String url=a+"/WEB-INF/jsp/check360/jasper/"+reportFilePath;
				JasperReport jasperReport= (JasperReport)JRLoader.loadObjectFromFile(url);
				JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,params,con);
				
				JRHtmlExporter exporter = new JRHtmlExporter();
				exporter.setParameter(JRExporterParameter.OUTPUT_WRITER,
						out);//这里说明，我们要将报表打印到页面上
				request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,jasperPrint);
				exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER,sbuffer);
				exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,"./servlets/image?image=");
				exporter.setParameter(JRExporterParameter.PAGE_INDEX, pageIndex);
				exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
				exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
				exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
				out.flush();
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
		//return values;
	}

	public Map getWhereparts(Map parameters, String wherepart) {
		String parts = "";
		int flag = 0;
		boolean plusand = true;
		while (flag != -1) {
			int first = wherepart.indexOf("'");
			int last = wherepart.indexOf("'", first + 1);
			if (first != -1 && last != -1) {
				parts = wherepart.substring(0, last + 1);
				if (plusand) {
					parts = "and " + parts;
					plusand = false;
				}
				int begin = parts.indexOf("and");
				int end = parts.indexOf("=");
				if (end == -1)
					end = parts.indexOf("like");
				String key = parts.substring(begin + 3, end);
				key = key.trim();
				wherepart = wherepart.substring(last + 1);
				parameters.put(key, parts);
			}
			flag = wherepart.indexOf("'");
		}
		return parameters;
	}
}
