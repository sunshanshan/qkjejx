<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绩效反馈--<s:text name="APP_NAME" /></title>
<s:action name="ref_head" namespace="/manager" executeResult="true" />
</head>
<style type="text/css">

td{
background-color: #fff;border:solid 1px #add9c0;
} 
.kss{
height: 60px;
}
.dkss{
height: 10px;
}

 
 @media print{
 
 div{min-height: 25%;overflow:auto;max-height: 25%;overflow: auto;
    position: relative;}
 .qianming{width: 50px;}
 }
</style>
<body>
	<!-- 顶部和左侧菜单导航 -->
	<s:action name="nav" namespace="/manage" executeResult="true" />
	<div class="tab_right">
		<div class="tab_warp main">
			<div class="dq_step noprint">
				<a href="javascript:;" onclick="window.focus();window.print();">打印本页</a>
				<!-- <a href="javascript:;" onclick="Print();">打印本页</a> -->
				<input id="begintime" type="hidden" value="${cymprint}">
			</div>
			<font size="3px;" style="margin:0 0 0 70%;">考核日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${cymprint }</font>
			<s:iterator value="vvs" status="sta">
			<div id="pd${sta.index+1 }" 
			<s:if test="(#sta.index+1)%5==0">style="page-break-after: always;"</s:if>
			 >
			<table id="adept${sta.index+1 }"
					>
				<tr id="rone" height="20px;">
					<td rowspan="2" >名称
					</td>
					<td rowspan="2" >岗位</td>
					<td rowspan="2" id="dept${sta.index+1}">部门</td>
					<td rowspan="2">合计</td>
					<td rowspan="2">加扣分项</td>
					<td rowspan="2">签名</td>
				</tr>
				<s:if test="%{d_code!=null}">
				<tr id="rtwo${d_code }">
				</tr>
				</s:if>
				<s:else>
				<tr id="rtwo${u_id }">
				</tr>
				</s:else>
				<tr id="rthree">
					<td rowspan="2">
					<s:if test="%{u_id!=null}">${username }
					<script type="text/javascript">
							$(function(){
								var u=${u_id}
								var cym=$("#begintime").val();
								var af=${sta.index+1};
								aus(u,cym,af);
						});
						</script>
					</s:if>
					<s:if test="%{d_code!=null}">${deptname}
					<script type="text/javascript">
							$(function(){
									var d=${d_code};
									var cym=$('#begintime').val();
									var af=${sta.index+1};
									ads(d,cym,af);
							});
					</script>
					</s:if>
					</td>
					<td rowspan="2">${pname }</td>
					<td rowspan="2" id="deptv${sta.index+1}">${deptname }</td>
					<td rowspan="2" id="z${sta.index+1}">${check_score }</td>
					<td rowspan="2">${bscore }</td>
					<td rowspan="2" class="qianming"></td>
				</tr>
				<s:if test="%{d_code!=null}">
				<tr id="rthfour${d_code }">
				</tr>
				</s:if>
				<s:else>
				<tr id="rthfour${u_id }">
				</tr>
				</s:else>
				<tr>
					<td>备注</td>
					<td colspan="30">${remark}</td>
				</tr>
				<tr id="fy" class="dkss"></tr>
				</table>
				</div>
			</s:iterator>

			
		</div>
	</div>
	
	<s:action name="ref_foot" namespace="/manager" executeResult="true" />
</body>

<script type="text/javascript">

var ads= function(dept,cym,af){
	var ajax = new Common_Ajax('ajax_member_message');
	ajax.config.action_url = ajax_url;
	ajax.config._success = function(data, textStatus){
		var l = $(data).length;
		if(l>0){
			$.each(data, function(i, n){
				$("#dept"+af).after("<td>"+n.kpi+"</td>");
				$("#rtwo"+dept).append("<td>"+n.weight+"</td>");
				$("#deptv"+af).after("<td>"+n.score+"</td>");
				$("#rthfour"+dept).append("<td>"+n.gold+"</td>");
			});
		}else{
			$("#z"+af).html("未考核");
		};
	};
	ajax.addParameter("work", "AutoComplete");
	ajax.addParameter("privilege_id", "QKJCJ_SYS_AJAXLOAD_VIEWPRINT");
	ajax.addParameter("parameters", "acheck_usercode=" + encodeURI(dept)+"&cym="+encodeURI(cym));
	ajax.sendAjax2();
};

var aus= function(u,cym,af){
	var ajax = new Common_Ajax('ajax_member_message');
	ajax.config.action_url = ajax_url;
	ajax.config._success = function(data, textStatus){
		var l = $(data).length;
		if(l>0){
			$.each(data, function(i, n){
				if(n.kpi==null||n.kpi==""){
					$("#dept"+af).after("<td>"+"取部门分数"+"</td>");
					$("#rtwo"+u).append("<td>"+1+"</td>");
				}else{
					$("#dept"+af).after("<td>"+n.kpi+"</td>");
					$("#rtwo"+u).append("<td>"+n.weight+"</td>");
				}
				
				$("#deptv"+af).after("<td>"+n.score+"</td>");
				$("#rthfour"+u).append("<td>"+n.gold+"</td>");
			});
		}else{
			$("#dept"+af).after("<td>"+"0"+"</td>");
			$("#rtwo"+u).append("<td>"+0+"</td>");
			$("#dept"+af).after("<td>"+0+"</td>");
			$("#rtwo"+u).append("<td>"+0+"</td>");
			$("#z"+af).html("未考核");
		};		
	};
	ajax.addParameter("work", "AutoComplete");
	ajax.addParameter("privilege_id", "QKJCJ_SYS_AJAXLOAD_VIEWPRINT");
	ajax.addParameter("parameters", "acheck_user=" + encodeURI(u)+"&cym="+encodeURI(cym));
	ajax.sendAjax2();
};


</script>
 


</html>