<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考核管理--<s:text name="APP_NAME" /></title>
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
				<span class="opb lb op-area"><a href="<s:url namespace="/qkjmanager" action="vartic_load"><s:param name="viewFlag">add</s:param></s:url>">添加考核</a></span>
			</c:if>
		</div>
		
		<div class="label_main">
			<fieldset class="clear">
				<legend>指标</legend>
					<table width="100%" cellpadding="0" cellspacing="0" border="0" class="lb_jpin">
						<tr>
							<th>kpi</th>
							<th>权重</th>
							<th>评分</th>
							<th>得分</th>
							<th>周期</th>
							<th>定义</th>
							<th>标准</th>
						</tr>
										<!-- lading.promotions -->
						<s:iterator value="ids" status="sta">
							<tr>
								<td class="nw">kpi</td>
								<td class="nw">weight</td>
								<td class="nw"><input name="vd.check_score" type="text"/></td>
								<td class="nw"><input name="vd.check_goal" type="text" readonly="readonly"/></td>
								<td class="nw">cyc</td>
								<td class="longnote" title="${definition}">${it:subString(definition,18)}</td>
								<td class="longnote" title="${correctly}">${it:subString(correctly,18)}</td>			
							</tr>
						</s:iterator>
					</table>
				</fieldset>
			</div>
	</div>
</div>

<s:action name="ref_foot" namespace="/manager" executeResult="true" />
<script type="text/javascript" src="<s:url value="/js/DatePicker.js" />"></script>
</body>
</html>