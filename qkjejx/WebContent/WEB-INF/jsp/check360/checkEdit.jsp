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
					<legend>kpi列表</legend>
					<!--<span class="shaddress">--------------------收货地址--------------------</span>-->
					<div class="label_main">
						<table width="100%" cellpadding="0" cellspacing="0" border="0"
							class="lb_jpin">
							<tr>
								<th>kpi</th>
								<th>周期</th>
								<th>权重</th>
								<th>计分方式</th>
								<th>指标定义</th>
								<th>指标标准</th>
								<th><a
									href="javascript:;" id="addkpi">添加指标</a></th>
							</tr>
							<s:iterator value="indexs" status="sta">
								<tr
									class="<s:if test="#sta.odd == true">oddStyle</s:if><s:else>evenStyle</s:else>">
									<td>${kpi}</td>
									<td>${cyc }</td>
									<td>${weight }</td>
									<td>${count_way }</td>
									<td class="td2 longnote" title="${definition}">
										${it:subString(definition,18)}</td>
									<td class="td2 longnote" title="${correctly}">
										${it:subString(correctly,18)}</td>
									<td>[<a class="ship_info" data="${uuid}" >修改</a>]
										[<a
										href="<s:url namespace="/check360" action="check_del360"><s:param name="index.uuid" value="uuid" /><s:param name="user.uuid" value="user_id"/></s:url>"
										onclick="return isDel();">删除</a>]
										
										<span class="ship_hidden_info" style="display:none;">
										<span id="ship_no_${uuid}">${uuid}</span>
										<span id="ship_cloud_${uuid }">${kpi}</span>
										<span id="ship_type_${uuid}">${cyc}</span>
										<span id="ship_date_${uuid}">${count_way}</span>
										<span id="ship_phone_${uuid}">${definition}</span>
										<span id="ship_status_${uuid}">${correctly}</span>
										<span id="ship_w_${uuid}">${weight}</span>
									</span>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</fieldset>
				
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
								<th>权重</th>
								<th><a href="javascript:;" id="addkpiUser">添加考核人邮箱</a></th>
							</tr>
							<s:iterator value="indexChecks" status="sta">
								<tr
									class="<s:if test="#sta.odd == true">oddStyle</s:if><s:else>evenStyle</s:else>">
									<td>${check_user}</td>
									<td>${cuname}</td>
									<td>${check_user_email}</td>
									<td>${weight }</td>
									<td>[<a class="ship_info2" data="${uuid}" >修改</a>]
										[<a
										href="<s:url namespace="/check360" action="check_Checkdel"><s:param name="indexCheck.uuid" value="uuid" /><s:param name="user.uuid" value="user_id"/></s:url>"
										onclick="return isDel();">删除</a>]
										
											<span class="ship_hidden_info" style="display:none;">
											<span id="check_email_${uuid}">${check_user_email}</span>
											<span id="check_no_${uuid}">${uuid}</span>
											<span id="check_user_${uuid }">${check_user}</span>
											<span id="check_user_name${uuid }">${cuname}</span>
											<span id="check_w_${uuid}">${weight}</span>
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
	<div id="changePWDdiv" style="display: none;" title="添加kpi">
		<s:form name="form2" cssClass="validForm" action="check_add"
			namespace="/check360" onsubmit="return validator(this);" method="post"
			theme="simple">
			<table class="ilisttable" width="100%">
				<tr>
					<td width="25%" align="right">角色ID:</td>
					<td>${user.uuid}<s:hidden name="index.user_id" value="%{user.uuid }" />
				</tr>
				<tr>
					<td align="right">用户名:</td>
					<td>${user.title}<s:hidden id="user.title" name="user.title" /></td>
				</tr>
				<tr>
					<td align="right">kpi:</td>
					<td>
					<s:textfield id="e_active_ship_cloud" name="index.kpi" cssClass="validate[required]"></s:textfield>		
					</td>
				</tr>
				<tr>
					<td align="right">周期:</td>
					<td>
					<s:hidden name="index.uuid" id="e_active_ship_no"></s:hidden>
					<s:textfield id="e_active_ship_type" name="index.cyc" cssClass="validate[required]"></s:textfield>	
					</td>
				</tr>
				
				<tr>
					<td align="right">权重:</td>
					<td>
					<s:textfield id="e_active_ship_weight" name="index.weight" cssClass="validate[required]"></s:textfield>	
					</td>
				</tr>
				
				<tr>
					<td align="right">计分方式:</td>
					<td><s:textfield id="e_active_ship_date"  name="index.count_way" cssClass="validate[required]"/></td>
				</tr>
				<tr>
					<td align="right">指标定义:</td>
					<td><s:textarea id="e_active_ship_phone" name="index.definition"
							cssClass="validate[required]" /></td>
				</tr>
				<tr>
					<td align="right">指标标准:</td>
					<td><s:textarea id="e_active_ship_status" name="index.correctly"
							cssClass="validate[required]" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="buttonarea"><s:submit id="addcheck" value="添加" action="check_add360"/> 
					
					<s:submit id="mdycheck" value="修改" action="check_save360" />
					<input
						type="button" value="返回" onclick="closemDiv();" /></td>
				</tr>
			</table>
		</s:form>
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
					<td align="right">权重:</td>
					<s:hidden name="indexCheck.uuid" id="check_no"></s:hidden>
					<td><s:textfield id="checkwith"  name="indexCheck.weight" cssClass="validate[required]"/></td>
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
			$("#changePWDdiv").dialog({
				autoOpen : false,
				width : 420,
				height : 320,
				modal : true
			});
			addTransferSelect("aroles", "uroles");
			
			$("#changeCheckUser").dialog({
				autoOpen : false,
				width : 420,
				height : 320,
				modal : true
			});
			
			SimpleLoadUser(ajax_url, $.noop);
		});

		$("#addkpi").click(function(){
			$("#addcheck").show();
			$("#mdycheck").hide();
			$("#e_active_uuid").val(null);
			$("#e_active_ship_phone").val(null);
			$("#e_active_ship_type").val(null);
			$("#e_active_ship_no").val(null);
			$("#e_active_ship_cloud").val(null);
			$("#e_active_ship_date").val(null);
			$("#e_active_ship_status").val(null);
			$("#e_active_remark").text(null);
			$("#changePWDdiv").dialog("open");
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
		
		if($(".ship_info").length>0) {
			$(".ship_info").bind("click",function(){
				setShipVal($(this).attr("data"));
				$("#changePWDdiv").dialog("open");
			});
		}
		
		if($(".ship_info2").length>0) {
			$(".ship_info2").bind("click",function(){
				setShipVal2($(this).attr("data"));
				$("#changeCheckUser").dialog("open");
			});
		}
		
		
		function setShipVal(p_uuid) {
			
			$("#addcheck").hide();
			$("#mdycheck").show();
			$("#e_active_uuid").val(p_uuid);
			$("#e_active_ship_phone").val($("#ship_phone_"+p_uuid).text());
			$("#e_active_ship_type").val($("#ship_type_"+p_uuid).text());
			$("#e_active_ship_no").val($("#ship_no_"+p_uuid).text());
			$("#e_active_ship_cloud").val($("#ship_cloud_"+p_uuid).text());
			$("#e_active_ship_date").val($("#ship_date_"+p_uuid).text());
			$("#e_active_ship_status").val($("#ship_status_"+p_uuid).text());
			$("#e_active_remark").text($("#active_remark_"+p_uuid).text());
			$("#e_active_ship_weight").text($("#ship_w_"+p_uuid).text());
			
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