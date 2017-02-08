<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表--<s:text name="APP_NAME" /></title>
<s:action name="ref_head" namespace="/manager" executeResult="true" />
<style type="text/css">
.input-a select {
	height: auto;
}

.even {
	background: #fff;
}

.ship_info {
cursor: pointer;
}
</style>


</head>
<body>
	<s:action name="nav" namespace="/manage" executeResult="true" />
	<div class="tab_right">
		<div class="tab_warp main">
			<div class="dq_step"></div>
			<s:form id="formEdit" name="form1" cssClass="validForm"
				action="user_add" namespace="/sys"
				onsubmit="return validator(this);" method="post" theme="simple">
				<s:if test="null != user">
					<div class="label_hang">
						<div class="label_ltit">用户:</div>
						<div class="label_rwben">${user.user_name}<s:hidden
								name="user.uuid" />
						</div>
					</div>
				</s:if>

				<fieldset class="clear">
					<legend>考核人列表</legend>
					<!--<span class="shaddress">--------------------收货地址--------------------</span>-->
					<div class="label_main">
						<table width="100%" cellpadding="0" cellspacing="0" border="0"
							class="lb_jpin">
							<tr>
								<th>考核人编号</th>
								<th>考核姓名</th>
								<th>邮箱</th>
								<th>类别</th>
								<th><a href="javascript:;" id="addkpiUser">添加考核人邮箱</a></th>
							</tr>
							<s:iterator value="indexChecks" status="sta">
								<tr
									class="<s:if test="#sta.odd == true">oddStyle</s:if><s:else>evenStyle</s:else>">
									<td>${check_user}</td>
									<td>${cuname}</td>
									<td>${check_user_email}</td>
									<td>${title }</td>
									<td>[<a class="ship_info2" data="${uuid}" >修改</a>]
										[<a
										href="<s:url namespace="/check360" action="check_Checkdel"><s:param name="indexCheck.uuid" value="uuid" /><s:param name="user.uuid" value="user_id"/></s:url>"
										onclick="return isDel();">删除</a>]
										
											<span class="ship_hidden_info" style="display:none;">
											<span id="check_email_${uuid}">${check_user_email}</span>
											<span id="check_no_${uuid}">${uuid}</span>
											<span id="check_user_${uuid }">${check_user}</span>
											<span id="check_user_name${uuid }">${cuname}</span>
											<span id="check_w_${uuid}">${ctuuid}</span>
										</span>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</fieldset>
			</s:form>
		</div>
	</div>
	
	<div id="changeCheckUser" style="display: none;" title="添加考核人">
		<s:form name="form2" cssClass="validForm" action="check_add"
			namespace="/check360" onsubmit="return validator(this);" method="post"
			theme="simple">
			<table class="ilisttable" width="100%">
				<tr>
					<td width="25%" align="right">角色ID:</td>
					<td>${user.uuid}<s:hidden name="indexCheck.user_id" value="%{user.uuid }" />
				</tr>
				<tr>
					<td align="right">用户名:</td>
					<td>${user.title}<s:hidden id="user.title" name="user.title" /></td>
				</tr>
				<tr>
					<td align="right">考核人姓名:</td>
					<td>
					<s:hidden id="order_user_id"  name="indexCheck.check_user" />
					<s:textfield id="order_user_name" name="indexCheck.cuname" cssClass="validate[required]"></s:textfield>	
					</td>
				</tr>
				<tr>
					<td align="right">考核人邮箱:</td>
					<td>
					<s:textfield id="order_user_email" name="indexCheck.check_user_email" cssClass="validate[required]"></s:textfield>	
					</td>
				</tr>
				<tr>
					<td align="right">类别:</td>
					<s:hidden name="indexCheck.uuid" id="check_no"></s:hidden>
					<td>
					<s:select id="checkwith" name="indexCheck.checkType_id" list="types" listKey="uuid" listValue="title" cssClass="validate[required]" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="buttonarea"><s:submit id="addcheckUser" value="添加" action="check_Checkadd"/> 
					
					<s:submit id="mdycheckUser" value="修改" action="check_Checksave" />
					<input
						type="button" value="返回" onclick="closemDiv();" /></td>
				</tr>
			</table>
		</s:form>
	</div>
	
	<s:action name="ref_foot" namespace="/manager" executeResult="true" />
	<script type="text/javascript" src="<s:url value="/js/div.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/func/select_user.js" />"></script>
	<script type="text/javascript"
		src="<s:url value="/js/optiontransferselect.js" />"></script>
	<script type="text/javascript">
		$(function() {
			$("#changeCheckUser").dialog({
				autoOpen : false,
				width : 420,
				height : 320,
				modal : true
			});
			
			SimpleLoadUser(ajax_url, $.noop);
		});

		$("#addkpiUser").click(function(){
			$("#addcheckUser").show();
			$("#mdycheckUser").hide();
			$("#checkemail").val(null);
			$("#checkwith").val(null);
			$("#changeCheckUser").dialog("open");
		});
		
		function view(obj) {
			var str = "";
			for ( var i in obj) {
				str += i + "\t";
			}
			$('message').innerHTML = str;
		}
		
		if($(".ship_info2").length>0) {
			$(".ship_info2").bind("click",function(){
				setShipVal2($(this).attr("data"));
				$("#changeCheckUser").dialog("open");
			});
		}
		
		function setShipVal2(p_uuid) {
			$("#addcheckUser").hide();
			$("#mdycheckUser").show();
			$("#order_user_email").val($("#check_email_"+p_uuid).text());
			$("#order_user_name").val($("#check_user_name"+p_uuid).text());
			$("#order_user_id").val($("#check_user_"+p_uuid).text());
			$("#checkwith").val($("#check_w_"+p_uuid).text());
			$("#check_no").val($("#check_no_"+p_uuid).text());
		}
	</script>
</body>
</html>