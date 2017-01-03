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
		</div>
		<div class="tab_warp">
			<table id="oneTable">
				<tr id="coltr">
					<!-- <th class="td1">考核人</th>
					<th class="td1">总评分</th>
					<th class="td1">总得分</th> -->
					<th class="td1">kpi</th>
					<th class="td1">评分</th>
					<th class="td1">得分</th>
					<!-- <th class="td3">备注</th> -->
					<th class="td0">查看</th>
				</tr>
				 <tbody id="aa">
				<s:iterator value="sonScores" status="sta">
					<tr id="showtr${uuid}">
						<%-- <td class="td1 nw">${check_username}</td>
						<td class="td1 nw">${total}</td>
						<td class="td1 nw">${check_gold}</td> --%>
						<td class="td1 nw" >${kpi}</td>
						<td class="td1 nw">${check_score}</td>
						<td class="td1 nw">${goal}</td>
						<%-- <td class="td3 nw">${remark}</td> --%>
						<td class="td0 op-area"><a href="javascript:;" onClick="showDetail('showtr${uuid}');" class="input-nostyle">查看</a>
						
						<span class="ship_hidden_info" style="display:none;">
						<span id="uuid${sta.index+1}">${uuid}</span>
						<span id="check_username${sta.index+1}">${check_username}</span>
						<span id="check_user${sta.index+1}">${check_user}</span>
						<span id="total${sta.index+1}">${total}</span>
						<span id="check_gold${sta.index+1}">${check_gold}</span>
						<span id="remark${sta.index+1}">${remark}</span>
						</span>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
		</div>
		
	</div>
</div>

<s:action name="ref_foot" namespace="/manager" executeResult="true" />
<script type="text/javascript" src="<s:url value="/js/DatePicker.js" />"></script>
<script type="text/javascript">
$(function(){
    getRelust();
})

function getRelust(){
	$("#oneTable").find("tr").each(function(i){
		 if(i>0){
			var uuid=$("#uuid"+i).text();
			var cname=$("#check_username"+i).text();
			var cuuid=$("#check_user"+i).text();
			var total=$("#total"+i).text();
			var check_gold=$("#check_gold"+i).text();
			var remark=$("#remark"+i).text();
			
			var lastuuid=$("#check_user"+(i-1)).text();
				var pruuid=$("#check_user"+(i-1)).text();
				var nuuid=$("#check_user"+(i+1)).text();
				if(pruuid==cuuid && nuuid!=cuuid){
					//$("table tr:eq("+(i)+")").after('<tr class="fo"><td  colspan="8">考核人：'+cname+'&nbsp;&nbsp;&nbsp;总评分：'+total+'&nbsp;&nbsp;&nbsp;总得分：'+check_gold+'&nbsp;&nbsp;&nbsp;备注：'+remark+'</td></tr>');
					$("#showtr"+uuid).after('<tr class="fo"><td  colspan="8">考核人：'+cname+'&nbsp;&nbsp;&nbsp;总评分：'+total+'&nbsp;&nbsp;&nbsp;总得分：'+check_gold+'&nbsp;&nbsp;&nbsp;备注：'+remark+'</td></tr>');
				}
		 }
		
    });
}
 
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