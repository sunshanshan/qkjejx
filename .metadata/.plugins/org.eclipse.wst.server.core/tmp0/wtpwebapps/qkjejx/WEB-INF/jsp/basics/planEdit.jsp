<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>计划管理--<s:text name="APP_NAME" /></title>
<s:action name="ref_head" namespace="/manager" executeResult="true" />
<style type="text/css">
#win {
	/*窗口的高度和宽度*/
	width: 300px;
	height: 200px;
	/*窗口的位置*/
	position: absolute;
	top: 100px;
	left: 350px;
	/*开始时窗口不可见*/
	display: none;
}
/*控制背景色的样式*/
#title {
	/*控制标题栏的左内边距*/
	padding-left: 3px;
}

#cotent {
	padding-left: 3px;
	padding-top: 5px;
}
/*控制关闭按钮的位置*/
#close {
	margin-left: 188px;
	/*当鼠标移动到X上时，出现小手的效果*/
	cursor: pointer;
}
s
</style>
</head>

<body>
	<s:action name="nav" namespace="/manage" executeResult="true" />

	<div class="tab_right">
		<div class="tab_warp main">
			<div class="dq_step">${path}
				<span class="opb lb op-area"> <a
					href="<s:url action="plan_list" namespace="/basics"><s:param name="viewFlag">relist</s:param></s:url>">返回列表</a>
				</span>
			</div>
			<s:form id="editForm" name="editForm" cssClass="validForm"
				action="plan_load" namespace="/basics" method="post" theme="simple">
				<div class="label_con">
					<div class="label_main">
						<s:if test="'mdy' == viewFlag">
							<div class='label_hang'>
								<div class='label_ltit'>主键:</div>
								<div class='label_rwben'>${plan.uuid}<s:hidden
										name="plan.uuid" />
								</div>
							</div>
							<div class='label_hang'>
								<div class='label_ltit'>添加人:</div>
								<div class='label_rwben'>${plan.add_username}</div>
							</div>
							<div class='label_hang'>
								<div class='label_ltit'>添加时间:</div>
								<div class='label_rwbenx'>${it:formatDate(plan.add_time,'yyyy-MM-dd hh:mm:ss')}</div>
							</div>
						</s:if>

						<div class="label_hang">
							<div class="label_ltit">计划年月:</div>
							<div class="label_rwben2">
								<span class="label_rwb nw"> <input id="begintime"
									name="plan.plan_date" type="text" onclick="setmonth(this)"
									readonly="readonly"
									value="${it:formatDate(plan.plan_date,'yyyy-MM')}" />
								</span>
							</div>
						</div>

					</div>

					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit">工作项目:</div>
							<div class="label_rwbenx">
								<s:textfield name="plan.project" title="主题"
									cssClass="label_hang_linput validate[required,maxSize[255]]" />
							</div>
						</div>
					</div>
					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit">工作内容:</div>
							<div class="label_rwbenx">
								<s:textarea name="plan.content" title="活动备注"
									cssClass="label_hang_linput inputNote validate[maxSize[65535]]" />
							</div>
						</div>
					</div>
					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit">活动类型:</div>
							<div class="label_rwben label_rwb">
								<s:select name="plan.type"
									cssClass="selectKick validate[required]"
									list="#{1:'常规',2:'临时'}" headerKey="" />
							</div>
						</div>
						<div class="label_hang">
							<div class="label_ltit">计划开始时间:</div>
							<div class="label_rwbenx">
								<input
									class="datepicker validate[required,custom[date]]" type="text"
									name="plan.start_time"
									value="${it:formatDate(plan.start_time,'yyyy-MM-dd')}" />
								
							</div>
						</div>
						<div class="label_hang">
							<div class="label_ltit">计划结束时间:</div>
							<div class="label_rwbenx">
								 <input
									class="datepicker validate[required,custom[date]]" type="text"
									name="plan.end_time"
									value="${it:formatDate(plan.end_time,'yyyy-MM-dd')}" />
								
							</div>
						</div>

						<div class="label_hang">
							<div class="label_ltit">计划完成时间:</div>
							<div class="label_rwben2">
								<span class="label_rwb nw"> <input
									class="datepicker validate[required,custom[date]]" type="text"
									name="plan.f_time"
									value="${it:formatDate(plan.f_time,'yyyy-MM-dd')}" />
								</span>
							</div>
						</div>
					</div>



					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit">备注:</div>
							<div class="label_rwbenx">
								<s:textarea name="plan.them" title="活动备注"
									cssClass="label_hang_linput inputNote validate[maxSize[65535]]" />
							</div>
						</div>
					</div>



					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit">相关操作:</div>
							<div class="label_rwbenx">
								<font color="red"><span id="messages"></span></font>
								<s:if test="'add' == viewFlag">
									<c:if
										test="${it:checkPermit('SYS_QKJMANAGER_BASIS_PLAN_ADD',plan.add_dept)==true}">
										<s:submit id="add" name="add" value="填加" action="plan_add"
											cssClass="input-blue" />
									</c:if>
								</s:if>
								<s:elseif test="'mdy' == viewFlag">
									<c:if
										test="${it:checkPermit('SYS_QKJMANAGER_BASIS_PLAN_MDY',null)==true}">
										<s:submit id="save" name="save" value="保存" action="plan_save"
											cssClass="input-blue" />
									</c:if>

									<c:if
										test="${it:checkPermit('SYS_QKJMANAGER_BASIS_PLAN_DEL',null)==true}">
										<s:submit id="delete" name="delete" value="删除"
											action="plan_del" onclick="return isDel();"
											cssClass="input-red" />
									</c:if>
								</s:elseif>
							</div>
						</div>
					</div>
				</div>
			</s:form>

		</div>
	</div>
	<s:action name="ref_foot" namespace="/manager" executeResult="true" />
	<script type="text/javascript"
		src="<s:url value="/include/jQuery/jquery.ui.datepicker-zh.js" />"></script>
</body>
<script type="text/javascript">
	$(function() {
		$("#editForm :input").change(function() {
			//if()cellarOrder_check0 10 15 20
			//$("#rebates_mdyRebatesStatus0").attr("disabled","disabled");
			if ($("#mdyStatus0").length > 0) {
				$("#mdyStatus0").attr("disabled", "disabled");
			}

			$("#messages").text("请先保存后才能做其他操作!");
		});
	});
</script>
<script type="text/javascript"
	src="<s:url value="/include/jQuery/jquery.ui.datepicker-zh.js" />"></script>
<script type="text/javascript" src="<s:url value="/js/DatePicker.js" />"></script>

</html>