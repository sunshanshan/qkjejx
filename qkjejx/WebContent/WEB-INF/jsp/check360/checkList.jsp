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
				<span class="opb lb op-area"><a href="<s:url namespace="/check360" action="check_360load"><s:param name="viewFlag">add</s:param></s:url>">提交</a></span>
		</div>
		<s:form id="serachForm" name="serachForm" action="check_360list" method="get" namespace="/check360" theme="simple">
			<div class="label_con">
				<div class="label_main">
					<div class='label_hang'>
						<div class='label_ltit'>主键:</div>
						<div class='label_rwben'>
							<s:textfield name='check.uuid' cssClass=' validate[maxSize[10],custom[integer],]' />
						</div>
					</div>
					<%-- <div class='label_hang'>
						<div class='label_ltit'>结案时间:</div>
						<div class='label_rwben'>
							<s:textfield name='closeOrder.close_time' cssClass=' datepicker validate[custom[date],]' />
						</div>
					</div> --%>
					
					<div class="label_hang">
						<div class="label_ltit">考核年月:</div>
						<div class="label_rwben">
							<input id="begintime" name="check.ym" type="text" onclick="setmonth(this)" readonly="readonly"/>
						</div>
					</div>
							
					<div class="label_hang">
						<div class="label_ltit">状态:</div>
						<div class="label_rwben label_rwb">
							<s:select  name="check.state" cssClass="selectKick" list="#{0:'打开',1:'关闭',2:'已审核'}" headerKey="" headerValue="--请选择--" />
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
					<th class="td3">状态</th>
					<th class="td4">操作</th>
					<th class="td0">查看</th>
				</tr>
				<s:iterator value="checks" status="sta">
					<tr id="showtr${uuid}">
						<td class="td1 nw">${uuid}</td>
						<td class="td1 nw">${ym}</td>
						<td class="td3 nw">
						<s:if test="state==0">打开
						<a href="javascript:;" onclick="add_user(${uuid});" >发送本年度考核</a>
						</s:if>
						<s:if test="state==1">关闭</s:if>
						<s:if test="state==2">已审核</s:if>
						</td>
						<td class="td4 op-area">
								<a class="input-blue" href="<s:url namespace="/check360" action="check_360load"><s:param name="viewFlag">mdy</s:param><s:param name="check.uuid" value="uuid"></s:param></s:url>">修改</a>
								<a class="input-red" href="<s:url namespace="/check360" action="check_360del"><s:param name="check.uuid" value="uuid"></s:param></s:url>" onclick="return isDel();">删除</a>
							</td>
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
 
function add_user(uuid){
	 $.ajax({
	     type:'POST',
	     url: '/check360/send_email',
	     data: "params="+uuid,
	     success: function(data){
	    	 if(data=="0"){
	    		 alert("成功.");
	 		}else if(data=="2"){
	 			 alert("没有考核人.");
	 		}   	 
	    	 else {
	 			alert("失败");
	 		}
	    }			    
	  });
}
</script>
</body>
</html>