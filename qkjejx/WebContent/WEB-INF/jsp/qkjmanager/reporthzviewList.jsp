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

 .PageNext{page-break-after: always;}
</style>
<body>
	<!-- 顶部和左侧菜单导航 -->
	<s:action name="nav" namespace="/manage" executeResult="true" />
	<div class="tab_right">
		<div class="tab_warp main">
			<div class="dq_step noprint">
				<a href="javascript:;" onclick="window.focus();window.print();">打印本页</a>
			</div>
			<input id="begintime" type="hidden" value="${cymprint}">
			<table id="adept" class="PageNext">
			<s:iterator value="vvs" status="sta">
				<tr id="rone">
					<td rowspan="2" style="width:120px;">名称
					</td>
					<td rowspan="2" style="width:120px;" id="dept${sta.index+1}">岗位</td>
					<td rowspan="2">合计</td>
					<td rowspan="2">签名</td>
					<td rowspan="2"  style="width:120px;">备注</td>
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
					<td rowspan="2" id="deptv${sta.index+1}">${pname }</td>
					<td rowspan="2" id="z${sta.index+1}">${check_score }</td>
					<td rowspan="2"></td>
					<td rowspan="2">${remark}</td>
				</tr>
				<s:if test="%{d_code!=null}">
				<tr id="rthfour${d_code }">
				</tr>
				<script type="text/javascript">
							$(function(){
									var d=${sta.index+1};
									var dept=${d_code};
									if(d%4==0){
										$("#rthfour"+dept).after("<tr id='fy' style='height: 10px;'></tr>");
									}
							});
				</script>
				</s:if>
				<s:else>
				<tr id="rthfour${u_id }">
				</tr>
					<script type="text/javascript">
							$(function(){
									var d=${sta.index+1};
									var dept=${u_id };
									if(d%4==0){
										$("#rthfour"+dept).after("<tr id='fy' 'height: 10px;'></tr>");
									}
							});
				</script>
				</s:else>
				<tr id="fy" class="dkss"></tr>
			</s:iterator>

			</table>
		</div>
	</div>
	<s:action name="ref_foot" namespace="/manager" executeResult="true" />
</body>

<script type="text/javascript">
window.onload=func;

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
				$("#dept"+af).after("<td style='width:100px;'>"+n.kpi+"</td>");
				$("#rtwo"+u).append("<td>"+n.weight+"</td>");
				$("#deptv"+af).after("<td style='height: 30px;'>"+n.score+"</td>");
				$("#rthfour"+u).append("<td style='height: 30px;'>"+n.gold+"</td>");
			});
		}else{
			$("#z"+af).html("未考核");
		};		
	};
	ajax.addParameter("work", "AutoComplete");
	ajax.addParameter("privilege_id", "QKJCJ_SYS_AJAXLOAD_VIEWPRINT");
	ajax.addParameter("parameters", "acheck_user=" + encodeURI(u)+"&cym="+encodeURI(cym));
	ajax.sendAjax2();
};

function func() {
	  factory.printing.portrait = true;    //true为纵向打印，flase为横向打印
	  factory.printing.leftMargin = 1.0; //左页边距
	  factory.printing.topMargin = 1.0;    //上页边距
	  factory.printing.rightMargin = 1.0;//右页边距
	  factory.printing.bottomMargin = 1.0; //下页边距
	}
</script>



</html>