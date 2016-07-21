<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>计划管理--<s:text name="APP_NAME" /></title>
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
			<c:if test="${it:checkPermit('SYS_QKJMANAGER_BASIS_PLAN_ADD',null)==true}">
				<span class="opb lb op-area"><a href="<s:url namespace="/basics" action="plan_load"><s:param name="viewFlag">add</s:param></s:url>">提交</a></span>
			</c:if>
		</div>
		<s:form id="serachForm" name="serachForm" action="plan_list" method="get" namespace="/basics" theme="simple">
			<div class="label_con">
				<div class="label_main">
					<div class='label_hang'>
						<div class='label_ltit'>主键:</div>
						<div class='label_rwben'>
							<s:textfield name='plan.uuid' cssClass=' validate[maxSize[10],custom[integer],]' />
						</div>
					</div>
					
					
					<div class="label_hang">
						<div class="label_ltit">计划年月:</div>
						<div class="label_rwben">
							<input id="begintime" name="plan.plan_date" type="text" onclick="setmonth(this)" readonly="readonly" value="${it:formatDate(plan.plan_date,'yyyy-MM')}" />
						</div>
					</div>
							
					<div class="label_hang">
						<div class="label_ltit">类型:</div>
						<div class="label_rwben label_rwb">
							<s:select  name="plan.type" cssClass="selectKick" list="#{1:'常规',2:'临时'}" headerKey="" headerValue="--请选择--" />
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
					<th class="td1">填加人</th>
					<th class="td1">填加部门</th>
					<th class="td2">计划年月</th>
					<th class="td2">工作项目</th>
					<th class="td2">类型</th>
					<th class="td3">计划开始时间</th>
					<th class="td3">计划结束时间</th>
					<th class="td3">计划完成时间</th>
					<th class="td4">操作</th>
					<th class="td0">查看</th>
				</tr>
				<s:iterator value="plans" status="sta">
					<tr id="showtr${uuid}">
						<td class="td1 nw">${uuid}</td>
						<td class="td1 nw">${add_username}</td>
						<td class="td1 nw">${add_deptname}</td>
						<td class="td2 nw">${it:formatDate(plan_date,'yyyy-MM')}</td>
						<td class="td2 nw" title="${project}">${it:subString(project,18)}</td>
						<td class="td2 nw">
						<s:if test="type==1">常规</s:if>
						<s:else>临时</s:else>
						</td>
						<td class="td3 nw">${it:formatDate(start_time,'yyyy-MM-dd')}</td>
						<td class="td3 nw">${it:formatDate(end_time,'yyyy-MM-dd')}</td>
						<td class="td3 nw">${it:formatDate(f_time,'yyyy-MM-dd')}</td>
						<td class="td4 op-area">
								<a class="input-blue" href="<s:url namespace="/basics" action="plan_load"><s:param name="viewFlag">mdy</s:param><s:param name="plan.uuid" value="uuid"></s:param></s:url>">修改</a>
					    	<c:if test="${it:checkPermit('SYS_QKJMANAGER_BASIS_PLAN_DEL',null)==true}">
								<a class="input-red" href="<s:url namespace="/basics" action="plan_del"><s:param name="plan.uuid" value="uuid"></s:param></s:url>" onclick="return isDel();">删除</a>
							</c:if></td>
						<td class="td0 op-area"><a href="javascript:;" onClick="showDetail('showtr${uuid}');" class="input-nostyle">查看</a></td>
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
 
</script>
</body>
</html>