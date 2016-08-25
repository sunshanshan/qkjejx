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
</head>
<body>
	<s:action name="nav" namespace="/manage" executeResult="true" />
	<div class="tab_right">
		<div class="tab_warp main">
			<div class="dq_step">${path}
				<span class="opb lb op-area">
						<a href="<s:url action="vartic_list" namespace="/qkjmanager"></s:url>">返回成绩列表</a>
				</span>
			</div>
			<p>一、上月绩效情况回顾</p>
			<div class="tab_warp">
				<table>
					<tr id="coltr">
						<th class="td1">姓名</th>
						<th class="td1">得分</th>
						<th class="td1">评级</th>
						<th class="td1">系数</th>
						<th class="td1">加减分项</th>
						<th class="td1">备注</th>
					</tr>
						<tr>
							<td class="td1 nw" id="aut">
							<input id="au" type="hidden" value="${vardic.acheck_user}">
							<script type="text/javascript">
															$(function(){
																var au=$('#au').val();
																aus(au);
																
															});
							</script>
							</td>
							<td class="td1 nw">${vardic.check_score }</td>
							<td class="td1 nw">
							<s:if test="vardic.check_score>=90">
							A
							</s:if>
							<s:if test="vardic.check_score>=80&&vardic.check_score<90">
							B
							</s:if>
							<s:if test="vardic.check_score>=60&&vardic.check_score<80">
							C
							</s:if>
							<s:if test="vardic.check_score<60">
							D
							</s:if>
							</td>
							<td class="td1 nw">
							<s:if test="vardic.check_score>=90">
							1.3
							</s:if>
							<s:if test="vardic.check_score>=80&&vardic.check_score<90">
							1.1
							</s:if>
							<s:if test="vardic.check_score>=60&&vardic.check_score<80">
							1.0
							</s:if>
							<s:if test="vardic.check_score<60">
							0.8
							</s:if>
							</td>
							<td class="td1 nw">${vardic.bscore }</td>
							<td class="td1 longnote" title="${vardic.remark}">${it:subString(vardic.remark,18)}</td>
						</tr>
				</table>
			</div>
			<s:form id="editForm" name="editForm" action="talk_add" method="post" namespace="/qkjmanager" theme="simple">	
			<s:if test="'add' == viewFlag"><s:hidden name="talk.vartic_id" value="%{vardic.uuid }"/></s:if>
			<s:if test="'mdy' == viewFlag"><s:hidden name="talk.uuid" value="%{talk.uuid }"/></s:if>
				<div class="label_con">
				<p> 二、上月内突出的业绩。（对应加分项，由上级讲述并填写）</p>
					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit"></div>
							<div class="label_rwbenx">
									<s:textarea name="talk.gjob" title="" cssClass="label_hang_linput inputNote validate[maxSize[65535]]" />
							</div>
						</div>
					</div>
					
					<p> 三、上月内工作中存在的不足及需要提升的技能或能力。（对应扣分项，由上级提出指导意见并由上级填写）</p>
					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit"></div>
							<div class="label_rwbenx">
									<s:textarea name="talk.bjob" title="" cssClass="label_hang_linput inputNote validate[maxSize[65535]]" />
							</div>
						</div>
					</div>
					<p>   四、双方沟通下月工作计划及目标是否达成一致？（由上级填写是或否）</p>
					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit"></div>
							<div class="label_rwbenx">
									<s:radio name="talk.isnext" list="#{0:'是',1:'否'}"></s:radio>
							</div>
						</div>
					</div>
					<p>   五、对于上月内工作中相对薄弱的环节，你计划采取什么方式或行动弥补？（本项与第三项当中存在不足相对应，此项可由员工填写）</p>
					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit"></div>
							<div class="label_rwbenx">
									<s:textarea name="talk.eujob" title="" cssClass="label_hang_linput inputNote validate[maxSize[65535]]" />
							</div>
						</div>
					</div>
					<p> 六、在下月度内完成既定的工作目标有哪些困难或需要协调的事项？（此项可由员工填写）</p>
					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit"></div>
							<div class="label_rwbenx">
									<s:textarea name="talk.enjob" title="" cssClass="label_hang_linput inputNote validate[maxSize[65535]]" />
							</div>
						</div>
					</div>
					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit">相关操作:</div>
							<div class="label_rwbenx">
								<font color="red"><span id="messages"></span></font>
								<s:if test="'add' == viewFlag">
									<c:if test="${it:checkPermit('SYS_QKJMANAGER_VERTICLIST_TALKADD',plan.add_dept)==true}">
										<s:submit id="add" name="add" value="提交" action="talk_add"
											cssClass="input-blue" />
									</c:if>
								</s:if>
								<s:elseif test="'mdy' == viewFlag">
								<c:if test="${it:checkPermit('SYS_QKJMANAGER_VERTICLIST_TALKADD',null)==true}">
								<c:if test="${it:checkTalkbyU(vardic.acheck_user)==false}">
									<s:submit id="save" name="save" value="提交" action="talk_saveLeader" cssClass="input-blue" />
								</c:if>
								</c:if>
								<c:if test="${it:checkTalkbyU(vardic.acheck_user)==true}">
								<c:if test="${it:checkPermit('SYS_QKJMANAGER_VERTICLIST_TALKMDY',null)==true}">
									<s:submit id="save" name="save" value="提交回复" action="saveEmp" cssClass="input-blue" />
								</c:if>
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
	<script type="text/javascript" src="<s:url value="/include/jQuery/jquery.ui.datepicker-zh.js" />"></script>
</body>
<script type="text/javascript">
	$(function(){
		$("#editForm :input").change(function(){
			//if()cellarOrder_check0 10 15 20
			//$("#rebates_mdyRebatesStatus0").attr("disabled","disabled");
			if ($("#mdyStatus0").length > 0) {
				$("#mdyStatus0").attr("disabled", "disabled");
			}

			$("#messages").text("请先保存后才能做其他操作!");
		});
	});
	
	var aus= function(au){
		var ajax = new Common_Ajax('ajax_member_message');
		ajax.config.action_url = ajax_url;
		ajax.config._success = function(data, textStatus){
			var l = $(data).length;
					if(l==1){
						$('#aut').html($(data)[0].user_name);
					}
					
		};
		ajax.addParameter("work", "AutoComplete");
		ajax.addParameter("privilege_id", "QKJCJ_SYS_AJAXLOAD_USERBYID");
		ajax.addParameter("parameters", "uuid=" + encodeURI(au));
		ajax.sendAjax2();
	};
</script>
<script type="text/javascript" src="<s:url value="/include/jQuery/jquery.ui.datepicker-zh.js" />"></script>
<script type="text/javascript" src="<s:url value="/js/DatePicker.js" />"></script>

</html>