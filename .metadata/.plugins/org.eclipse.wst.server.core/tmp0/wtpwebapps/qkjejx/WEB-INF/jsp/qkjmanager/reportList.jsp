<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报表管理--<s:text name="APP_NAME" /></title>
<s:action name="ref_head" namespace="/manager" executeResult="true" />
</head>
<style type="text/css">
.ship_info {
cursor: pointer;
}
</style>
<body>
	<!-- 顶部和左侧菜单导航 -->
<s:action name="nav" namespace="/manage" executeResult="true" />
<div class="tab_right">
 	<div class="tab_warp main" >
		<div class="dq_step">
			${path}
			<c:if test="${it:checkPermit('SYS_QKJMANAGER_VERTICLIST_ADD',null)==true}">
				<span class="opb lb op-area"><a href="<s:url namespace="/qkjmanager" action="scoure_excle"><s:param name="viewFlag">add</s:param><s:param name="vardic.check_ym">${vardic.check_yms}</s:param></s:url>">保存考核</a></span>
			</c:if>
		</div>
		<s:form id="serachForm" name="serachForm" action="report_list" method="get" namespace="/qkjmanager" theme="simple">
			<div class="label_con">
				<div class="label_main">
					<div class="label_hang">
						<div class="label_ltit">考核年月:</div>
						<div class="label_rwben">
							<input id="begintime" name="vardic.check_ym" value="${vardic.check_yms}" type="text" onclick="setmonth(this)" readonly="readonly"/>
						</div>
					</div>
							
					<div class="label_hang tac">
						<s:checkbox id="search_mcondition" name="search_mcondition" fieldValue="true" value="true" cssClass="regular-checkbox" />
						<label for="search_mcondition"></label>更多条件
						<s:submit value="搜索" />
						<s:reset value="重置" />
					</div>
				</div>
			</div>
		</s:form>
		<div class="tab_warp">
			<table>
				<tr id="coltr">
					<th class="td1">主键</th>
					<th class="td1">考核年月</th>
					<th class="td1">被考核人</th>
					<th class="td1">被考核人部门</th>
					<th class="td2">考核完成时间</th>
					<th class="td2">分数</th>
				</tr>
				<s:iterator value="vardics" status="sta">
					<tr id="showtr${uuid}">
						<td class="td1 nw">${uuid}</td>
						<td class="td1 nw">${it:formatDate(check_ym,'yyyy-MM')}</td>
						<td class="td1 nw">${acheck_username}</td>
						<td class="td1 nw">${acheck_deptname}</td>
						<td class="td1 nw">${it:formatDate(check_date,'yyyy-MM-dd')}</td>
						<td class="td2 nw">
						<s:if test="acheck_username!=null">
						${ay_totelScore}
						</s:if>
						<s:else>
						${check_score}
						</s:else>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="listpage" class="pagination"></div>
	</div>
</div>

<s:action name="ref_foot" namespace="/manager" executeResult="true" />
<script type="text/javascript" src="<s:url value="/js/DatePicker.js" />"></script>
<script type="text/javascript">
$(function(){
	printPagination("listpage",'${currPage}','${recCount}','${pageSize}');
 });
 
 


function savee(date){
	
	alert($("#begintime").val())
	
	
}
</script>
</body>
</html>