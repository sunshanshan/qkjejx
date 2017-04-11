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

.input-a select{height:auto;}
.even{background:#fff;}
</style>
</head>

<body>
	<s:action name="nav" namespace="/manage" executeResult="true" />
	
	<div class="tab_right">
		<div class="tab_warp main">
			<div class="dq_step">${path}
				<span class="opb lb op-area">
						<a href="<s:url action="check_360list" namespace="/check360"><s:param name="viewFlag">relist</s:param></s:url>">返回列表</a>
				</span>
			</div>
			<s:form id="editForm" name="editForm" cssClass="validForm" action="check_load360" namespace="/check360" method="post" theme="simple">
					<div class="label_main">
						<s:if test="'mdy' == viewFlag">
							<div class='label_hang'>
								<div class='label_ltit'>主键:</div>
								<div class='label_rwben'>${check.uuid}<s:hidden name="check.uuid" />
								</div>
							</div>
						</s:if>
						
						<div class="label_main">
							<div class="label_hang">
								<div class="label_ltit">主题:</div>
								<div class="label_rwbenx">
									<s:textfield name="check.title" title="主题" cssClass="label_hang_linput validate[required,maxSize[255]]" />
								</div>
							</div>
						</div>
								
						<div class="label_hang">
								<div class="label_ltit">状态:</div>
								<div class="label_rwben label_rwb">
									<s:select  name="check.state" cssClass="selectKick" list="#{0:'打开',1:'关闭',2:'已审核'}" headerKey=""/>
								</div>
						</div>
						<div class="label_hang">
								<div class="label_ltit">考核体系:</div>
								<div class="label_rwben label_rwb">
								<s:select name="check.main_id" list="maincas" listKey="uuid" listValue="title" cssClass="validate[required]" />
						</div>
					
					</div>
					
					<div class="label_hang clear">
				       <div class="label_ltit">被考核人:</div>
				       <div class="label_rwbenx">
				       		<s:optiontransferselect            
						     label="用户角色"
						     name="aroles" 
						     leftTitle="未赋予的被考核人"
						     rightTitle="已经赋予的被考核人"
						     list="crits" 
						     multiple="true"
						     listKey="uid"
						     listValue="typeTitle"
						     headerKey=""
						     headerValue="-- 请选择 --"
						     doubleName="uroles"
						     doubleList="doubleCrits" 
						     doubleListKey="uid"
						     doubleListValue="typeTitle"
						     doubleHeaderKey=""
						     doubleHeaderValue="-- 请选择 --" 
						     doubleMultiple="true"
						     allowUpDownOnLeft="false"
						     allowUpDownOnRight="false"
						     allowAddAllToRight="false"
						     allowSelectAll="false" />
				       </div>
				       
					</div>
					
					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit">相关操作:</div>
							<div class="label_rwbenx">
								<font color="red"><span id="messages">${message }</span></font>
								<s:if test="'add' == viewFlag">
										<s:submit id="add" name="add" value="填加" action="check_360add" cssClass="input-blue" />
								</s:if>
								<s:elseif test="'mdy' == viewFlag">
										<s:submit id="save" name="save" value="保存" action="check_360save" cssClass="input-blue" />
										<s:if test="check.state==0">
										<s:submit value="发送邮件" action="send_email" cssClass="input-blue" />
										</s:if>
										<s:submit id="delete" name="delete" value="删除" action="check_360del" onclick="return isDel();" cssClass="input-red" />
								</s:elseif>
							</div>
						</div>
					</div>
					
					
					<fieldset class="clear">
						<legend>未考核人</legend>
							<s:iterator value="ics" status="sta">
								${cuname }&nbsp;&nbsp;&nbsp;
								<input type="hidden" name="check_user" value="${check_user }">
							</s:iterator>
							<s:if test="'mdy' == viewFlag&&check.state==0">
							<a href="javascript:;" onclick="add_user(${check.uuid},${check.crit_id});" >催收</a>
							</s:if>
					</fieldset>
					</div>
			</s:form>

		</div>
	</div>
	<s:action name="ref_foot" namespace="/manager" executeResult="true" />
	<script type="text/javascript" src="<s:url value="/include/jQuery/jquery.ui.datepicker-zh.js" />"></script>
	<script type="text/javascript" src="<s:url value="/js/optiontransferselect.js" />"></script>
</body>

<script type="text/javascript">
$(function(){
	addTransferSelect("aroles","uroles");
})

function add_user(uuid,typeid){
	var chu="";
	$("input[name='check_user']").each(
			function() {
				chu+=$(this).val()+",";
			});
	 $.ajax({
	     type:'POST',
	     url: '/check360/pro_send_email',
	     data: "params="+uuid+"&type="+typeid+"&check_user="+chu,
	     success: function(data){
	    	if(data=="1"){
	    		 alert("失败.");
	 		}else {
	 			alert("成功.");
	 		}
	    }			    
	  });
}
</script>


</html>