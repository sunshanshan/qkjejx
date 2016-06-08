<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考核管理--<s:text name="APP_NAME" /></title>
<s:action name="ref_head" namespace="/manager" executeResult="true" />
<style type="text/css">
#win {
	/*窗口的高度和宽度*/
	width: 300px; height: 200px;
	/*窗口的位置*/ position: absolute; top: 100px; left: 350px;
	/*开始时窗口不可见*/ display: none;
}
/*控制背景色的样式*/
#title {
	/*控制标题栏的左内边距*/
	padding-left: 3px;
}

#cotent {
	padding-left: 3px; padding-top: 5px;
}
/*控制关闭按钮的位置*/
#close {
	margin-left: 188px;
	/*当鼠标移动到X上时，出现小手的效果*/ cursor: pointer;
}
s
</style>
</head>

<body>
	<s:action name="nav" namespace="/manage" executeResult="true" />
	
	<div class="tab_right">
		<div class="tab_warp main">
			<div class="dq_step">${path}
				<span class="opb lb op-area">
						<a href="<s:url action="vartic_list" namespace="/qkjmanager"></s:url>">返回列表</a>
				</span>
			</div>
			
			<s:form id="serachForm" name="serachForm" action="vartic_checklist" method="get" namespace="/qkjmanager" theme="simple">
				<div class="label_con">
					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit">考核年月:</div>
							<div class="label_rwben">
								<input id="begintime" name="vardic.check_ym" type="text" onclick="setmonth(this)" readonly="readonly" value="${it:formatDate(vardic.check_ym,'yyyy-MM')}"/>
							</div>
						</div>
								
						<div class="label_hang tac">
							<s:checkbox id="search_mcondition" name="search_mcondition" fieldValue="true" value="true" cssClass="regular-checkbox" />
							<label for="search_mcondition"></label>更多条件
							<s:submit value="查询" />
							<s:reset value="重置" />
						</div>
					</div>
				</div>
			</s:form>
			
			<div class="tab_warp">
				<table>
					<tr id="coltr">
						<th class="td1">被考核人</th>
						<th class="td1">部门</th>
						<th class="td1">分数</th>
						<th class="td4">操作</th>
					</tr>
					<s:iterator value="cvardics" status="sta">
						<tr>
							<td class="td1 nw">${acheck_username}</td>
							<td class="td1 nw">${acheck_deptname}</td>
							<td class="td1 nw">${check_score }</td>
							<td class="td4 op-area">
								<c:if test="${it:checkPermit('SYS_QKJMANAGER_VERTICLIST_MDY',null)==true}">
									<a class="input-blue" href="/qkjmanager/varticDetail_list?vardic.u_id=${u_id }&vardic.u_code=${u_code}&vardic.check_ym=${it:formatDate(vardic.check_ym,'yyyy-MM')}&viewFlag=add">考核</a>
								</c:if> 
						    </td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div id="listpage" class="pagination"></div>

		</div>
	</div>
	<s:action name="ref_foot" namespace="/manager" executeResult="true" />
	<script type="text/javascript" src="<s:url value="/include/jQuery/jquery.ui.datepicker-zh.js" />"></script>
</body>
<script type="text/javascript">
	$(function(){
		$("#editForm :input").change(function(){
			//if()cellarOrder_check0 10 15 20
			//$("#rebates_mdyRebatesStatus0").attr("disabled","disabled");
			if ($("#mdyStatus0").length > 0) {
				$("#mdyStatus0").attr("disabled", "disabled");
			}

			$("#messages").text("请先保存后才能做其他操作!");
		});
	});
</script>
<script type="text/javascript" src="<s:url value="/include/jQuery/jquery.ui.datepicker-zh.js" />"></script>
<script type="text/javascript" src="<s:url value="/js/DatePicker.js" />"></script>
<script type="text/javascript">
$(function(){
	printPagination("listpage",'${currPage}','${recCount}','${pageSize}');
 });
 
</script>
</html>