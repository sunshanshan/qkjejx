<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品列表--<s:text name="APP_NAME" /></title>
<s:action name="ref_head" namespace="/manager" executeResult="true" />
</head>
<body>
<!-- 顶部和左侧菜单导航 -->

<div class="">
 	<div class="tab_warp main" >
		<div class="tab_warp">
 		<table>
 		<tr id="coltr">
	    <th class="td1">申请人</th>
	    <th class="td1">申请时间</th>
		<th class="td1">产品</th>
		<th class="td1">规格</th>
		<th class="td1">数量</th>
		<th class="td1">件数</th>
	  	</tr>
	  	<s:iterator value="entertProducts" status="sta">
	  	<tr id="showtr${uuid}">
			<td class="td1 nw">${apply_user_name }</td>
			<td class="td1 nw">${it:formatDate(apply_date,'yyyy-MM-dd')}</td>
			<td class="td1 nw">${product_name}</td>
			<td class="td1 nw">${spec}</td>
			<td class="td1 nw">${num}</td>
			<td class="td1 nw">${num/case_spec }件</td>
	  	</tr>
	  	</s:iterator>
 		</table>
 	</div>
 	<div id="listpage" class="pagination"></div>
</div>
</div>
<s:action name="ref_foot" namespace="/manager" executeResult="true" />
<script type="text/javascript" src="<s:url value="/js/jqueryPlugins/select3/jquery.cityselect.js" />"></script>
<script type="text/javascript">
$(function(){
	$("#mmtype").citySelect({
		url:'<s:url value="/js/jqueryPlugins/select3/mm.js" />',
		prov:"${assets.typea}",
		city:"${assets.typeb}",
		dist:" ${assets.typec}",
		nodata:"none",
		required:false
	});
	printPagination("listpage",'${currPage}','${recCount}','${pageSize}');
 });
</script>
</body>
</html>