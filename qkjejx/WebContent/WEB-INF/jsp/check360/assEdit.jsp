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

.title {
width:270% !important;
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
			<s:form id="formEdit" name="form1" cssClass="validForm" namespace="/check360" onsubmit="return validator(this);" method="post" theme="simple">
				<div class="label_hang">
		            <div class="label_ltit">所属项目:</div>
		        </div>
				<div class="label_hang">
		            <div class="label_rwbenx">
		            	<s:select id="user"  list="capausers" listKey="uuid" listValue="title" cssClass="validate[required]" />
		            </div>
		        </div>
		        <div class="label_hang">
		        	<div class="label_rwbenx">
		            	<select id="capa" name="ass.capa_id"></select>
		            </div>
		        </div>&nbsp;&nbsp;
		        <div class="label_hang">
		        	<div class="label_rwbenx">
		            	<select id="fact" name="ass.fact_id"></select>
		            </div>
		        </div>&nbsp;&nbsp;
		        <div class="label_hang">
		        	<div class="label_rwbenx">
		            	<select id="index" name="ass.index_id" class="validate[required]"></select>
		            </div>
		        </div>
		        
		        <div class="label_main">
		        <div class="label_hang">
		            <div class="label_ltit">标题:</div>
		            <div class="label_hang">
		        	<div class="label_rwbenx">
		            	<s:textfield name="ass.title" class="title validate[required]"></s:textfield>
		            </div>
		        </div>
		        </div>
		        </div>
		        
		        <div class="label_main">
		        <div class="label_hang">
		            <div class="label_ltit">副标题:</div>
		            <div class="label_hang">
		        	<div class="label_rwbenx">
		            	<s:textarea name="ass.detail"  cssClass="label_hang_linput inputNote validate[maxSize[65535]]" />
		            	
		            </div>
		        </div>
		        </div>
		        </div>
		        
		        <div class="label_main">
						<div class="label_hang">
							<div class="label_ltit">相关操作:</div>
							<div class="label_rwbenx">
								<font color="red"><span id="messages"></span></font>
										<s:submit id="add" name="add" value="填加" action="check_addAss" cssClass="input-blue" />
								<s:if test="'mdy' == viewFlag">
									<s:hidden name="ass.uuid" value="%{ass.uuid }"></s:hidden>
										<s:submit id="save" name="save" value="保存" action="check_saveAss" cssClass="input-blue" />
										<s:submit id="delete" name="delete" value="删除" action="check_delAss" onclick="return isDel();" cssClass="input-red" />
								</s:if>
							</div>
						</div>
					</div>
			</s:form>
			
			<s:iterator value="capas" status="sta">
					<tr id="showtr${uuid}">
						<td>
						<input type="hidden" name="capauser" value="${main_id}" cuid="${uuid }" ctitle="${title }">
						</td>
					</tr>
			</s:iterator>
			<s:iterator value="facts" status="sta">
					<tr id="showtr${uuid}">
						<td>
						<input type="hidden" name="facttitle" value="${capacity_id}" cuid="${uuid }" ctitle="${title }">
						</td>
					</tr>
			</s:iterator>
			<s:iterator value="indexs" status="sta">
					<tr id="showtr${uuid}">
						<td>
						<input type="hidden" name="indext" value="${factors_id}" cuid="${uuid }" ctitle="${manifestation }">
						</td>
					</tr>
			</s:iterator>
			<s:if test="'mdy' == viewFlag">
			<s:hidden id="ca_id" name="ass.capa_id" />
			<s:hidden id="fa_id" name="ass.fact_id" />
			</s:if>
		</div>
	</div>
	<s:action name="ref_foot" namespace="/manager" executeResult="true" />
	<script type="text/javascript" src="<s:url value="/js/div.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/func/select_user.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.cudialog.iframe.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.manyselect.js" />"></script>
	<script type="text/javascript"
		src="<s:url value="/js/optiontransferselect.js" />"></script>
	<script type="text/javascript">
	$(function() {
		var userid=$('#user').val();
		selectcapa(userid);
		
	});
	
	$('#user').change(function(){ 
		var capau=$('#user').val();
		selectcapa(capau);	
	});
	
	$('#capa').change(function(){ 
		var capau=$('#capa').val();
		selectfact(capau);	
	});
	
	$('#fact').change(function(){ 
		var capau=$('#fact').val();
		selectindex(capau);	
	});
	
	var selectcapa = function(userid) {
		var capa = $("#capa");
		capa.clearAllOption();
		$("input[name='capauser']").each(function() {
			var user_id=$(this).val();
			if(user_id==userid){
				capa.addOption($(this).attr("ctitle"), $(this).attr("cuid"));
			}
		});
		var ca_id=$('#ca_id').val();
		if(ca_id!=null && ca_id!=''){
			capa.val(ca_id);
			selectfact(capa.val());	
		}else{
			selectfact(capa.val());	
		}
		
	}
	
	var selectfact = function(capaid) {
		var fact = $("#fact");
		fact.clearAllOption();
		$("input[name='facttitle']").each(function() {
			var user_id=$(this).val();
			if(user_id==capaid){
				fact.addOption($(this).attr("ctitle"), $(this).attr("cuid"));
			}
		});
		
		var ca_id=$('#fa_id').val();
		if(ca_id!=null && ca_id!=''){
			fact.val(ca_id);
			selectindex(fact.val());		
		}else{
			selectindex(fact.val());	
		}
		
	}
	
	var selectindex = function(capaid) {
		var fact = $("#index");
		fact.clearAllOption();
		$("input[name='indext']").each(function() {
			var user_id=$(this).val();
			if(user_id==capaid){
				fact.addOption($(this).attr("ctitle"), $(this).attr("cuid"));
			}
		});
		
	}
	</script>
</body>
</html>