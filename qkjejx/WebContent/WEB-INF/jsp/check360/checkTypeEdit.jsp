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
			<div class="dq_step">
			${path}
			<span class="opb lb op-area"><a href="<s:url namespace="/check360" action="check_listType360"><s:param name="viewFlag">relist</s:param></s:url>">返回列表</a></span>
		</div>
			<s:form id="formEdit" name="form1" cssClass="validForm"
				action="check_addType360" namespace="/check360"
				onsubmit="return validator(this);" method="post" theme="simple">
				<s:if test="'mdy' == viewFlag">
							<div class='label_hang'>
								<div class='label_ltit'>主键:</div>
								<div class='label_rwben'>${type.uuid}<s:hidden name="type.uuid" />
								</div>
							</div>
						</s:if>
						
						<div class="label_hang">
								<div class="label_ltit">领域:</div>
								<div class="label_rwben2">
									<span class="label_rwb nw">
									<s:textfield name="type.title"></s:textfield>
									</span>
									
								</div>
						</div>
							
						<div class="label_hang">
								<div class="label_ltit">权重:</div>
								<div class="label_rwben2">
									<span class="label_rwb nw">
									<s:textfield name="type.weight"></s:textfield>
									</span>
									
								</div>
						</div>
						
						<div class="label_hang">
								<div class="label_ltit">类别:</div>
								<div class="label_rwben2">
									<span class="label_rwb nw">
									<s:select id="fdsta" name="type.state" cssClass="selectKick"
				 list="#{0:'其它',1:'自身'}"
				/>
									</span>
									
								</div>
						</div>

					<div class="label_main">
							<div class="label_hang">
								<div class="label_ltit">相关操作:</div>
								<div class="label_rwbenx">
									<font color="red"><span id="messages"></span></font>
									<s:if test="'add' == viewFlag">
											<s:submit id="add" name="add" value="添加" action="check_addType360"
												cssClass="input-blue" />
									</s:if>
									<s:elseif test="'mdy' == viewFlag">
										<s:submit id="save" name="save" value="修改" action="check_saveType360" cssClass="input-blue" />
									</s:elseif>
								</div>
							</div>
						</div>
				
				
				
			</s:form>
		</div>
	</div>
	
	
	<s:action name="ref_foot" namespace="/manager" executeResult="true" />
	<script type="text/javascript" src="<s:url value="/js/div.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/func/select_user.js" />"></script>
	<script type="text/javascript"
		src="<s:url value="/js/optiontransferselect.js" />"></script>
	
</body>
</html>