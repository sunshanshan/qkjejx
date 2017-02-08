<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags"%>
<%@page import="com.qkj.check360.action.*"%>
<%@ page
import="java.io.*,
net.sf.jasperreports.engine.*,
net.sf.jasperreports.engine.util.*,
java.util.*,java.sql.*,
net.sf.jasperreports.engine.export.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title></title>
<s:action name="ref_head" namespace="/manager" executeResult="true" />
<link rel="stylesheet" type="text/css" href="../css/layout.css" />
<style type="text/css">
table{
border: 1px !important;
}
td {
margin-left: 0px;
}

</style>
</head>
<body>

<%
		//参数获取
		String check_ym =(String) request.getParameter("score.check_ym");
		String user_id =(String) request.getParameter("score.user_id");
		String con =ReportAction.conn;
		String user=ReportAction.user;
		String pwd =ReportAction.pwd;
		String path=application.getRealPath("/");
		//调用报表模版
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path+"/WEB-INF/jsp/check360/jasper/ass_report.jasper");
		
		//向报表传递参数
		
		Map parameters = new HashMap();
		parameters.put("check_ym", check_ym);
		parameters.put("user_id", user_id);
		System.out.print(user_id+con+user+pwd+"bbbbbbbbbbbbbbbb");
		//JDBC连接数据源
		
		Connection conn =null;
		try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(
		con, user, pwd);
		JasperPrint jasperPrint=new JasperPrint();
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
		
		JRHtmlExporter exporter = new JRHtmlExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
		
		exporter.exportReport();
		out.flush(); 
		conn.close();
		}catch(Exception e){
		if (null != conn) conn.close();
		e.printStackTrace();
		}finally{
			System.out.print(user_id+con+user+pwd+"bbbbbbbbbbbbbbbb");
		if (null != conn) conn.close();
		}
		%>
		
<script type="text/javascript">
$(function(){
	$("table:eq(0)").find($("tr:eq(0)")).find($("td:eq(0)").css("width", "0.1%"));
	$("table:eq(0)").find($("tr:eq(0)")).find($("td:eq(1)").css("align", "left"));
 });
</script>
</body>
</html>