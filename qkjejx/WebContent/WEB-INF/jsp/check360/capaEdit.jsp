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

.manyselect{
 	position:fixed;   
    color:#00CC99;
	width:30%;
	background-color:#CC9933;
	border-color:#0000FF;
	border: 2px solid;
	display:none;
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
				<div class="label_hang">
		            <div class="label_ltit">被考核人部门:</div>
		            <div class="label_rwben2">
		            	<span class="label_rwb">
						<s:textfield title="部门名称" id="userdept_nameid"  readonly="true" />
						<s:hidden title="部门代码" id="userdept_codeid" readonly="true" />
						</span>
						<span class="lb nw">
						<img class="detail vatop" src='<s:url value="/images/open2.gif" />' onclick="selectCheckUser('userdept_codeid','userdept_nameid',true);" />
						<s:checkbox id="apply_is_sub_dept" name="active.is_sub_dept" cssClass="regular-checkbox" />
						<span id="ajax_member_message"></span>
						</span>
		            </div>
		        </div>
		        <div class="label_hang">
		            <div class="label_ltit">被考核人:</div>
				</div>
		        <div class="label_hang" style="width: 30%">
		        		<input type="hidden" id="id_div" name="capa.user_id" value="${capa.user_id }"/>
		            	<input type="text" id="test_div" name="capa.user_name" readonly="readonly" value=" ${capa.user_name }"/> 
						<div class="manyselect"></div> 
		        </div>
		        
		        <div class="label_hang">
		            <div class="label_ltit">考核类别:</div>
		            <div class="label_rwbenx">
		            <s:select id="critid" name="capa.crit_id" list="crits" listKey="typeUUID" listValue="typeTitle" cssClass="validate[required]" />
		            </div>
		        </div>
				
				<div>
				<fieldset class="clear">
					<legend>能力列表</legend>
					<!--<span class="shaddress">--------------------收货地址--------------------</span>-->
					<div class="label_main">
						<table width="100%" cellpadding="0" cellspacing="0" border="0"
							class="lb_jpin">
							<tr>
								<th>标题</th>
								<th><a
									href="javascript:;" id="addCapa">添加能力</a></th>
							</tr>
							<s:iterator value="capas" status="sta">
								<tr
									class="<s:if test="#sta.odd == true">oddStyle</s:if><s:else>evenStyle</s:else>">
									<td>${title}</td>
									<td>[<a class="ship_info" data="${uuid}" >修改</a>]
										[<a
										href="<s:url namespace="/check360" action="check_delCapa360"><s:param name="capa.uuid" value="uuid" /><s:param name="capa.user_id" value="capa.user_id"/></s:url>"
										onclick="return isDel();">删除</a>]
										
										<span class="ship_hidden_info" style="display:none;">
											<span id="ship_no_${uuid}">${uuid}</span>
											<span id="ship_cloud_${uuid }">${title}</span>
										</span>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</fieldset>
				
				<fieldset class="clear">
					<legend>因素列表</legend>
					<!--<span class="shaddress">--------------------收货地址--------------------</span>-->
					<div class="label_main">
						<table width="100%" cellpadding="0" cellspacing="0" border="0"
							class="lb_jpin">
							<tr>
								<th>能力</th>
								<th>标题</th>
								<th><a
									href="javascript:;" id="addFact">添加因素</a></th>
							</tr>
							<s:iterator value="facts" status="sta">
								<tr>
									<td>${ctitle}</td>
									<td>${title}</td>
									<td>[<a class="ship_fact" data="${uuid}" >修改</a>]
										[<a
										href="<s:url namespace="/check360" action="check_delFact360"><s:param name="fact.uuid" value="uuid" /><s:param name="capa.user_id" value="capa.user_id"/></s:url>"
										onclick="return isDel();">删除</a>]
										
										<span class="ship_hidden_info" style="display:none;">
										<span id="fact_no_${uuid}">${uuid}</span>
										<span id="fact_title_${uuid }">${title}</span>
										<span id="fact_capacityid_${uuid}">${capacity_id}</span>
									</span>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</fieldset>
				
				<fieldset class="clear">
					<legend>考核项目</legend>
					<!--<span class="shaddress">--------------------收货地址--------------------</span>-->
					<div class="label_main">
						<table width="100%" cellpadding="0" cellspacing="0" border="0"
							class="lb_jpin">
							<tr>
								<th>因素</th>
								<th>标题</th>
								<th>详细</th>
								<th><a
									href="javascript:;" id="addm">添加项目</a></th>
							</tr>
							<s:iterator value="indexs" status="sta">
								<tr>
									<td>${ftitle}</td>
									<td>${manifestation}</td>
									<td  title="${detail}">
										${it:subString(detail,18)}</td>
									<td>[<a class="index_info" data="${uuid}" >修改</a>]
										[<a
										href="<s:url namespace="/check360" action="check_del360"><s:param name="index.uuid" value="uuid" /><s:param name="capa.user_id" value="capa.user_id"/></s:url>"
										onclick="return isDel();">删除</a>]
										
										<span class="ship_hidden_info" style="display:none;">
										<span id="index_no_${uuid}">${uuid}</span>
										<span id="index_manifestation_${uuid }">${manifestation}</span>
										<span id="index_factors_id_${uuid}">${factors_id}</span>
										<span id="index_detail_${uuid}">${detail}</span>
									</span>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</fieldset>
				
				<fieldset class="clear">
					<legend>自由反馈列表</legend>
					<!--<span class="shaddress">--------------------收货地址--------------------</span>-->
					<div class="label_main">
						<table width="100%" cellpadding="0" cellspacing="0" border="0"
							class="lb_jpin">
							<tr>
								<th>编号</th>
								<th>自由评价标题</th>
								<th><a
									href="javascript:;" id="addremark">添加评价</a></th>
							</tr>
							<s:iterator value="remark360s" status="sta">
								<tr
									class="<s:if test="#sta.odd == true">oddStyle</s:if><s:else>evenStyle</s:else>">
									<td>${uuid}</td>
									<td>${title }</td>
									
									<td>
										[<a
										href="<s:url namespace="/check360" action="check_remarkdel360"><s:param name="remark360.uuid" value="uuid" /><s:param name="remark360.user_id" value="user_id"/></s:url>"
										onclick="return isDel();">删除</a>]
										
										<span class="ship_hidden_info" style="display:none;">
										<span id="remark_no_${uuid}">${uuid}</span>
										<span id="remark_title_${uuid }">${title}</span>
										</span>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</fieldset>
				
				</div>
			</s:form>
		</div>
	</div>
	<div id="changeCapa" style="display: none;" title="添加kpi">
		<s:form name="form2" cssClass="validForm" namespace="/check360" onsubmit="return validator(this);" method="post" theme="simple">
			<table class="ilisttable" width="100%">
				<tr>
					<td align="right">标题:</td>
					<td>
					<s:hidden name="capa.uuid" id="capa_uuid"></s:hidden>
					<s:textfield id="capa_title" name="capa.title" cssClass="validate[required]"></s:textfield>		
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;
					<input type="hidden" id="capa_userid" name="capa.user_id"/> 
		            <input type="hidden" id="capa_username" name="capa.user_name" /> 
		            <input type="hidden" id="capa_crit" name="capa.crit_id" /> 
					</td>
					<td class="buttonarea"><s:submit id="addcheck" value="添加" action="check_addCapa360"/> 
					<s:submit id="mdycheck" value="修改" action="check_saveCapa360" />
					<input
						type="button" value="返回" onclick="closemDiv();" /></td>
				</tr>
			</table>
		</s:form>
	</div>
	
	<div id="changeRemark" style="display: none;" title="添加评价">
		<s:form name="form3" cssClass="validForm" action="check_remarkadd360"
			namespace="/check360" onsubmit="return validator(this);" method="post"
			theme="simple">
			<table class="ilisttable" width="100%">
				<tr>
					<td align="right">自由回答标题:</td>
					<td><s:textarea id="remark_title"  name="remark360.title" cssClass="validate[required]"/></td>
				</tr>
				<tr>
					<td>&nbsp;
					<input type="hidden" id="remark_userid" name="remark360.user_id"/> 
		            <input type="hidden" id="remark_username" name="capa.user_name" />
					</td>
					<td class="buttonarea"><s:submit id="addcheck" value="添加" action="check_remarkadd360"/> 
				</tr>
			</table>
		</s:form>
	</div>
	<s:if test="'mdy' == viewFlag">
	<div id="changeFact" style="display: none;" title="添加因素">
		<s:form name="form2" cssClass="validForm" namespace="/check360" onsubmit="return validator(this);" method="post" theme="simple">
			<table class="ilisttable" width="100%">
				<tr>
					<td align="right">能力:</td>
					<td>
					<s:hidden id="fact_no"  name="fact.uuid" />
					<s:select id="fact_capacityid" name="fact.capacity_id" list="capas" listKey="uuid" listValue="title" cssClass="validate[required]" />
					</td>
				</tr>
				<tr>
					<td align="right">标题:</td>
					<td>
					<s:textfield id="fact_title" name="fact.title" cssClass="validate[required]"></s:textfield>	
					</td>
				</tr>
				<tr>
					<td>&nbsp;
					<input type="hidden" id="fact_userid" name="capa.user_id"/> 
		            <input type="hidden" id="fact_username" name="capa.user_name" /> 
					</td>
					<td class="buttonarea"><s:submit id="addcheckUser" value="添加" action="check_addFact360"/> 
					
					<s:submit id="mdycheckUser" value="修改" action="check_saveFact360" />
					<input
						type="button" value="返回" onclick="closemDiv();" /></td>
				</tr>
			</table>
		</s:form>
	</div>
	
	<div id="changeIndex" style="display: none;" title="添加项目">
		<s:form name="form2" cssClass="validForm" namespace="/check360" onsubmit="return validator(this);" method="post" theme="simple">
			<table class="ilisttable" width="100%">
				<tr>
					<td align="right">因素:</td>
					<td>
					<s:hidden id="index_no"  name="index.uuid" />
					<s:select id="index_factid" name="index.factors_id" list="facts" listKey="uuid" listValue="title" cssClass="validate[required]" />
					</td>
				</tr>
				<tr>
					<td align="right">表现:</td>
					<td>
					<s:textfield id="index_manifestation" name="index.manifestation" cssClass="validate[required]"></s:textfield>	
					</td>
				</tr>
				<tr>
					<td align="right">详细:</td>
					<td><s:textarea id="index_detail"  name="index.detail"/></td>
				</tr>
				
				<tr>
					<td>&nbsp;
					<input type="hidden" id="index_userid" name="capa.user_id"/> 
		            <input type="hidden" id="index_username" name="capa.user_name" /> 
					</td>
					<td class="buttonarea"><s:submit id="addindex" value="添加" action="check_add360"/> 
					
					<s:submit id="mdyindex" value="修改" action="check_del360" />
					<input
						type="button" value="返回" onclick="closemDiv();" /></td>
				</tr>
			</table>
		</s:form>
	</div>
	</s:if>
	<s:action name="ref_foot" namespace="/manager" executeResult="true" />
	<script type="text/javascript" src="<s:url value="/js/div.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/func/select_user.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.cudialog.iframe.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.manyselect.js" />"></script>
	
	

	<script type="text/javascript"
		src="<s:url value="/js/optiontransferselect.js" />"></script>
	<script type="text/javascript">
		$(function() {
			$("#capa_userid").val($("#id_div").val());
			$("#capa_username").val($("#test_div").val());
			$("#capa_crit").val($("#critid").val());
			
			$("#fact_userid").val($("#id_div").val());
			$("#fact_username").val($("#test_div").val());
			$("#index_userid").val($("#id_div").val());
			$("#index_username").val($("#test_div").val());
			$("#remark_userid").val($("#id_div").val());
			$("#remark_username").val($("#test_div").val());
			
			$("#changeCapa").dialog({
				autoOpen : false,
				width : 420,
				height : 320,
				modal : true
			});
			addTransferSelect("aroles", "uroles");
			
			$("#changeFact").dialog({
				autoOpen : false,
				width : 420,
				height : 320,
				modal : true
			});
			
			$("#changeIndex").dialog({
				autoOpen : false,
				width : 680,
				height : 320,
				modal : true
			});
			
			SimpleLoadUser(ajax_url, $.noop);
			
			$("#changeRemark").dialog({
				autoOpen : false,
				width : 420,
				height : 320,
				modal : true
			});
		});

		$("#addCapa").click(function(){
			$("#addcheck").show();
			$("#mdycheck").hide();
			$("#capa_uuid").val(null);
			$("#capa_title").val(null);
			
			$("#capa_userid").val($("#id_div").val());
			$("#capa_username").val($("#test_div").val());
			$("#capa_crit").val($("#critid").val());
			
			if($("#id_div").val()!=null && $("#id_div").val()!="" && $("#test_div").val()!=null && $("#test_div").val()!=""){
				$("#changeCapa").dialog("open");
			}else{
				alert("被考核人不能为空！");
			}
			
		});
		
		$("#addFact").click(function(){
			$("#addcheckUser").show();
			$("#mdycheckUser").hide();
			$("#checkemail").val(null);
			$("#checkwith").val(null);
			$("#fact_userid").val($("#id_div").val());
			$("#fact_username").val($("#test_div").val());
			
			$("#changeFact").dialog("open");
		});
		
		$("#addm").click(function(){
			$("#addindex").show();
			$("#mdyindex").hide();
			$("#index_factid").val(null);
			$("#index_manifestation").val(null);
			$("#index_no").val(null);
			
			$("#index_userid").val($("#id_div").val());
			$("#index_username").val($("#test_div").val());
			
			$("#changeIndex").dialog("open");
		});
		
		
		$("#addremark").click(function(){
			$("#remark_userid").val($("#id_div").val());
			$("#remark_username").val($("#test_div").val());
			$("#changeRemark").dialog("open");
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
				setCapa($(this).attr("data"));
				$("#changeCapa").dialog("open");
			});
		}
		
		if($(".ship_fact").length>0) {
			$(".ship_fact").bind("click",function(){
				setFact($(this).attr("data"));
				$("#changeFact").dialog("open");
			});
		}
		
		if($(".index_info").length>0) {
			$(".index_info").bind("click",function(){
				setIndex($(this).attr("data"));
				$("#changeIndex").dialog("open");
			});
		}
		
		
		function setCapa(p_uuid) {
			$("#addcheck").hide();
			$("#mdycheck").show();
			$("#capa_uuid").val($("#ship_no_"+p_uuid).text());
			$("#capa_title").val($("#ship_cloud_"+p_uuid).text());
			
		}
		
		function setFact(p_uuid) {
			$("#addcheckUser").hide();
			$("#mdycheckUser").show();
			$("#fact_capacityid").val($("#fact_capacityid_"+p_uuid).text());
			$("#fact_title").val($("#fact_title_"+p_uuid).text());
			$("#fact_no").val($("#fact_no_"+p_uuid).text());
		}
		
		function setIndex(p_uuid) {
			$("#addindex").hide();
			$("#mdyindex").show();
			$("#index_factid").val($("#index_factors_id_"+p_uuid).text());
			$("#index_manifestation").val($("#index_manifestation_"+p_uuid).text());
			$("#index_no").val($("#index_no_"+p_uuid).text());
			$("#index_detail").val($("#index_detail_"+p_uuid).text());
		}
	</script>
</body>
</html>