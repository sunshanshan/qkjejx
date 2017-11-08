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
				<span class="opb lb op-area"><a href="<s:url namespace="/qkjmanager" action="scoure_excle"><s:param name="viewFlag">add</s:param><s:param name="vardic.cym">${vardic.cym}</s:param></s:url>">导出考核</a></span>
			</c:if>
		</div> 
		<s:form id="serachForm" name="serachForm" action="report_list" method="get" namespace="/qkjmanager" theme="simple">
			<div class="label_con">
				<div class="label_main">
					<div class="label_hang">
						<div class="label_ltit">考核年月:</div>
						<div class="label_rwben">
							<input id="begintime" name="vardic.cym" value="${it:formatDate(vardic.cym,'yyyy-MM')}"  type="text" onclick="setmonth(this)" readonly="readonly"/>
						</div>
					</div>
							
					<div class="label_hang label_button tac">
						<s:submit value="搜索" />
						<s:reset value="重置" />
						<c:if test="${it:checkPermit('SYS_QKJMANAGER_VERTICLIST_CHECKSURE',null)==true}">
						<span class="opb lb op-area"><a href="<s:url namespace="/qkjmanager" action="check_sure"></s:url>">审核考核成绩</a></span>
						</c:if>
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
					<th class="td2">状态</th>
					<th class="td1">操作</th>
				</tr>
				<s:iterator value="vardics" status="sta">
					<tr id="showtr${uuid}">
						<td class="td1 nw">${uuid}</td>
						<td class="td1 nw">${it:formatDate(cym,'yyyy-MM')}</td>
						<td class="td1 nw">${acheck_username}</td>
						<td class="td1 nw">${acheck_deptname}</td>
						<td class="td2 nw">${it:formatDate(check_date,'yyyy-MM-dd')}</td>
						<td class="td2 nw">
						${check_score}
						</td>
						<td class="td2 nw">
						<s:if test="cstate==0">
						打开
						</s:if>
						<s:elseif test="cstate==1">关闭</s:elseif>
						<s:elseif test="cstate==2">已审核</s:elseif>
						</td>
						<td class="td1 nw">
						<s:if test="%{acheck_username==null}">
							<a class="input-blue" href="<s:url namespace="/qkjmanager" action="varticDetail_loadDept"><s:param name="viewFlag">mdy</s:param><s:param name="vardic.uuid" value="uuid"></s:param></s:url>">查看</a>
							</s:if>
							<s:else>
							<a class="input-blue" href="<s:url namespace="/qkjmanager" action="varticDetail_load"><s:param name="viewFlag">mdy</s:param><s:param name="vardic.uuid" value="uuid"></s:param></s:url>">查看</a>
							</s:else>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="listpage" class="pagination"></div>
		
		<fieldset class="clear">
			<legend>未考核部门</legend>
			<s:iterator value="vardicswilld" status="sta">
			${acheck_deptname }&nbsp;&nbsp;
			</s:iterator>
		</fieldset>
		<fieldset class="clear">
			<legend>未考核人员</legend>
			<s:iterator value="vardicwillu" status="sta">
			${acheck_username}&nbsp;&nbsp;
			</s:iterator>
		</fieldset>
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