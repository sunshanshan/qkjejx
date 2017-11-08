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
		</div>
		<s:form id="serachForm" name="serachForm" action="transverse_list" method="get" namespace="/qkjmanager" theme="simple">
			<div class="label_con">
				<div class="label_main">
					<div class='label_hang'>
						<div class='label_ltit'>主键:</div>
						<div class='label_rwben'>
							<s:textfield name='vardic.uuid' cssClass=' validate[maxSize[10],custom[integer],]' />
						</div>
					</div>
					<%-- <div class='label_hang'>
						<div class='label_ltit'>结案时间:</div>
						<div class='label_rwben'>
							<s:textfield name='closeOrder.close_time' cssClass=' datepicker validate[custom[date],]' />
						</div>
					</div> --%>
					
							
					<div class="label_hang label_button tac">
						<s:submit value="搜索" />
						<s:reset value="重置" />
					</div>
				</div>
			</div>
		</s:form>
		<fieldset class="clear">
			<legend>待考核信息</legend>
			<div class="tab_warp">
				<table>
					<tr id="coltr">
						<th class="td1">被考核人/部门</th>
						<th class="td1">部门</th>
						<th class="td1">操作</th>
					</tr>
					<s:iterator value="cvardics" status="sta">	
						<tr>
							<td class="td1 nw">${acheck_username}</td>
							<td class="td1 nw">(${df_name})${acheck_deptname}</td>
							<td class="td1 nw">
							
								<c:if test="${it:checkPermit('SYS_QKJMANAGER_HORILIST_MDY',null)==true}">
									<a class="input-blue" href="/qkjmanager/transverseDetail_listuser?vardic.u_id=${u_id }&vardic.u_code=${u_code}&vardic.check_ym=${it:formatDate(vardic.check_ym,'yyyy-MM')}&viewFlag=add">考核</a>
								</c:if> 
						    </td>
						</tr>
					</s:iterator>
					<s:iterator value="cvardicsd" status="sta">
						<tr>
							<td class="td1 nw">${acheck_deptname}</td>
							<td class="td1 nw"></td>
							<td class="td1 nw">
								<c:if test="${it:checkPermit('SYS_QKJMANAGER_HORILIST_ADD',null)==true}">
									<a class="input-blue" href="/qkjmanager/transverseDetail_list?vardic.u_code=${d_code}&vardic.check_ym=${it:formatDate(vardic.check_ym,'yyyy-MM')}&viewFlag=add">考核</a>
								</c:if> 
						    </td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</fieldset>
		<fieldset class="clear">
			<legend>已考核信息</legend>
			<div class="tab_warp">
			<table>
				<tr id="coltr">
					<th class="td1">主键</th>
					<th class="td1">考核年月</th>
					<th class="td1">被考核人</th>
					<th class="td2">职务</th>
					<th class="td1">被考核部门</th>
					<th class="td2">考核完成时间</th>
					<th class="td4">操作</th>
					<th class="td0">查看</th>
				</tr>
				<s:iterator value="vardics" status="sta">
					<tr id="showtr${uuid}">
						<td class="td1 nw">${uuid}</td>
						<td class="td1 nw">${it:formatDate(cym,'yyyy-MM')}</td>						
						<td class="td1 nw" id="aut${uuid }"><input id="au${uuid }" type="hidden" value="${acheck_user}">
						<s:if test="acheck_user!=null">
							<script type="text/javascript">
															$(function(){
																var uuid=${uuid };
																var au=$('#au'+uuid).val();
																aus(uuid,au);
																
															});
							</script>
						</s:if>
							</td>
							<td class="td2 nw" id="aup${uuid }"></td>
							<td class="td1 nw" id="adt${uuid }"><input id="ad${uuid }" type="hidden" value="${acheck_usercode}">
							<script type="text/javascript">
															$(function(){
																var uuid=${uuid };
																var au=$('#ad'+uuid).val();
																ads(uuid,au);
																
															});
							</script>
							</td>
						<td class="td2 nw">${it:formatDate(check_date,'yyyy-MM-dd')}</td>
						<td class="td4 op-area">
								<s:if test="%{acheck_user==null}">
								<a class="input-blue" href="<s:url namespace="/qkjmanager" action="transverseDetail_load"><s:param name="viewFlag">mdy</s:param><s:param name="vardic.uuid" value="uuid"></s:param></s:url>">修改</a>
								</s:if>
								<s:else>
								<a class="input-blue" href="<s:url namespace="/qkjmanager" action="transverseDetail_loadUser"><s:param name="viewFlag">mdy</s:param><s:param name="vardic.uuid" value="uuid"></s:param></s:url>">修改</a>
								</s:else>
					    	<%-- <c:if test="${it:checkPermit('SYS_QKJMANAGER_HORILIST_DEL',null)==true}">
								<a class="input-red" href="<s:url namespace="/qkjmanager" action="transverse_del"><s:param name="vardic.uuid" value="uuid"></s:param></s:url>" onclick="return isDel();">删除</a>
							</c:if> --%>
							</td>
						<td class="td0 op-area"><a href="javascript:;" onClick="showDetail('showtr${uuid}');" class="input-nostyle">查看</a></td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="listpage" class="pagination"></div>
		</fieldset>	
	</div>
</div>

<s:action name="ref_foot" namespace="/manager" executeResult="true" />
<script type="text/javascript" src="<s:url value="/js/DatePicker.js" />"></script>
<script type="text/javascript">
$(function(){
	printPagination("listpage",'${currPage}','${recCount}','${pageSize}');
 });
 
var aus= function(uuid,au){
	var ajax = new Common_Ajax('ajax_member_message');
	ajax.config.action_url = ajax_url;
	ajax.config._success = function(data, textStatus){
		var l = $(data).length;
				if(l==1){
					$('#aut'+uuid).html($(data)[0].user_name);
					$('#aup'+uuid).html($(data)[0].position_name);
				};
				
	};
	ajax.addParameter("work", "AutoComplete");
	ajax.addParameter("privilege_id", "QKJCJ_SYS_AJAXLOAD_USERBYID");
	ajax.addParameter("parameters", "uuid=" + encodeURI(au));
	ajax.sendAjax2();
};


var ads= function(uuid,ad){
	var ajax = new Common_Ajax('ajax_member_message');
	ajax.config.action_url = ajax_url;
	ajax.config._success = function(data, textStatus){
		var l = $(data).length;
				if(l==1){
					$('#adt'+uuid).html($(data)[0].dept_cname);
				}
				
	};
	ajax.addParameter("work", "AutoComplete");
	ajax.addParameter("privilege_id", "SYS_SELECT_DEPT_LISTBYDEPT");
	ajax.addParameter("parameters", "dept_code=" + encodeURI(ad));
	ajax.sendAjax2();
};
</script>
</body>
</html>