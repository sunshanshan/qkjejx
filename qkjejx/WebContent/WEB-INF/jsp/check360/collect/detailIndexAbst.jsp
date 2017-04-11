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
<s:action name="nav" namespace="/manage" executeResult="true" />
<div class="tab_right">
<div class="tab_warp main">
<div class="dq_step">
<span class="opb lb op-area">
<a href="<s:url namespace="/check360" action="check_loadDetailExcel"><s:param name="score.user_id" value="score.user_id"></s:param><s:param name="score.check_ym" value="score.check_ym"></s:param><s:param name="jaspername">main_report_index_abst.jasper</s:param></s:url>">导出Excel</a>
</span>
</div>

<s:form id="serachForm" name="serachForm" action="check_360loadAbst" method="get" namespace="/check360" theme="simple">
			<div class="label_con">
				<div class="label_main">
					<div class='label_hang'>
						<div class='label_ltit'>被考核人:</div>
						<div class='label_rwben'>
							${user.user_name }
							<input name="score.user_id" value="${user.uuid}" type="hidden">
						</div>
					</div>
					<div class="label_hang">
						<div class="label_ltit">考核年度:</div>
						<div class="label_rwben">
							<select name="score.check_ym">
							<s:iterator value="index360s" status="sta">
							<option value="${uuid }" 
							<s:if test="%{score.check_ym==uuid }">selected="selected"</s:if>
							>${title}</option>
							</s:iterator>
    						</select>
						</div>
					</div>
					<div>${message}</div>
					<div class="label_hang tac">
						<s:checkbox id="search_mcondition" name="search_mcondition" fieldValue="true" value="true" cssClass="regular-checkbox" />
						<label for="search_mcondition"></label>更多条件
						<s:submit value="搜索" />
						<s:reset value="重置" />
					</div>
				</div>
			</div>
		</s:form>
		
		
</div>

<%
		//参数获取
		
		String check_ym =(String) request.getParameter("score.check_ym");
		String user_id =(String) request.getParameter("score.user_id");
		String url=(String) request.getParameter("score.user_id");
		String con =ReportAction.conn;
		String user=ReportAction.user;
		String pwd =ReportAction.pwd;
		String path=application.getRealPath("/");
		//调用报表模版
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(path+"/WEB-INF/jsp/check360/jasper/main_report_index_abst.jasper");
		
		//向报表传递参数
		
		Map parameters = new HashMap();
		parameters.put("check_ym", check_ym);
		parameters.put("user_id", user_id);
		System.out.print(user_id);
		//JDBC连接数据源
		
		Connection conn =null;
		try{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(
		con, user, pwd);
		JasperPrint jasperPrint = JasperFillManager.fillReport(
		jasperReport, parameters, conn);
		
		JRHtmlExporter exporter = new JRHtmlExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
		
		// 判断是否正常
				List<JRPrintPage> pages = jasperPrint.getPages();
				if (pages.size() == 0) {
				  // 没有数据
				 request.setAttribute("message", "没有数据");
				  return;
				}
		exporter.exportReport();
		out.flush(); 
		conn.close();
		}catch(Exception e){
		if (null != conn) conn.close();
		e.printStackTrace();
		}finally{
		if (null != conn) conn.close();
		}
		%>
		

</div>
<s:action name="ref_foot" namespace="/manager" executeResult="true" />
<script type="text/javascript">
$(function(){
	$("table:eq(0)").find($("tr:eq(0)")).find($("td:eq(0)").css("width", "0.1%"));
	$("table:eq(0)").find($("tr:eq(0)")).find($("td:eq(1)").css("align", "left"));
 });
</script>
</body>
</html>