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
.fo td{
text-align: left !important;
}
</style>
<body>
	<!-- 顶部和左侧菜单导航 -->
<s:action name="nav" namespace="/manage" executeResult="true" />
<div class="tab_right">
 	<div class="tab_warp main" >
		<div class="dq_step">
			${path}
			<span class="opb lb op-area"><a href="<s:url namespace="/check360" action="card_excel"><s:param name="score.user_id" value="user.uuid"></s:param><s:param name="score.check_ym" value="score.check_ym"></s:param></s:url>">导出</a></span>
		</div>
		<s:form id="serachForm" name="serachForm" action="check_360ScoreView" method="get" namespace="/check360" theme="simple">
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
						<div class="label_rwbenx">
							${index360.title}
						</div>
					</div>
					<div>${message}</div>
				</div>
			</div>
		</s:form>
		<div class="tab_warp">
		<fieldset class="clear">
			<legend>考核人</legend>
			<table id="oneTable">
				<tr id="coltr">
					<th class="td1">考核人</th>
					<th class="td1">得分</th>
					<th class="td1">等级</th>
					<th class="td2">考核时间</th>
					<th class="td1">操作</th>
					<th class="td0">查看</th>
				</tr>
				 <tbody id="aa">
				<s:iterator value="scores" status="sta">
					<tr id="showtr${uuid}">
						<td class="td1 nw">${check_user_name}</td>
						<td class="td1 nw">${check_score}</td>
						<td class="td1 nw">${cttitle}</td>
						<td class="td2 nw">${it:formatDate(check_date,'yyyy-MM-dd hh:mm:ss')}</td>
						<td class="td4 op-area">
						<input type="hidden" id="acu${sta.index}" value="${acheck_user_name}">
						<a href="javascript:;" onclick="openCustomerView(${check_user},${acheck_user },${check_ym });">查看</a>
						</td>
						<td class="td0 op-area"><a href="javascript:;" onClick="showDetail('showtr${uuid}');" class="input-nostyle">查看</a>
						</td>
						
					</tr>
				</s:iterator>
				</tbody>
			</table>
		</fieldset>
		</div>
		
	</div>
</div>

<s:action name="ref_foot" namespace="/manager" executeResult="true" />
<script type="text/javascript" src="<s:url value="/js/DatePicker.js" />"></script>
<script type="text/javascript">
$(function(){
	createCustomerView();
	$("#acheck_u").text($("#acu0").val());
});

var openCustomerView = function(icuuid,inuuid,critid) {
	var iframeId = sobj02.getConid() + "iframe";
	$("#"+iframeId).attr("src","/check360/check_sonView?sonScore.check_user=" + icuuid+"&sonScore.user_id="+inuuid+"&sonScore.check_ym="+critid);
	sobj02.open();
};
var sobj02;
var createCustomerView = function() {
	var w_width = $(window).width();
	var w_height = $(window).height();
	sobj02 = new DialogIFrame({
		src:'',
		title:"查看",
		width:w_width*0.85,
		height:w_height*0.85
	});
	sobj02.selfAction = function(val1,val2) {};
	sobj02.create();
}
</script>
</body>
</html>