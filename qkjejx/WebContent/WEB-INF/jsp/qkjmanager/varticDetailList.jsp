<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考核管理--sun<s:text name="APP_NAME" /></title>
<s:action name="ref_head" namespace="/manager" executeResult="true" />
</head>
<style type="text/css">
.ship_info {
	cursor: pointer;
}
</style>
<body>
	<!-- 顶部和左侧菜单导航 -->
	<s:action name="nav" namespace="/manage" executeResult="true" />
	<div class="tab_right">
		<div class="tab_warp main">
			<div class="dq_step">
				${path} <span class="opb lb op-area"><a
					href="JavaScript:history.go(-1)">返回</a></span>
			</div>
			<s:form id="editForm" name="editForm" cssClass="validForm"
				action="varticDetail_add" namespace="/qkjmanager" method="post"
				theme="simple">
				
				<div class="label_main">
					
					<s:if test="'mdy' == viewFlag"><s:hidden name="vardic.uuid" value="%{vardic.uuid}"></s:hidden>
						<div class="label_hang">
							<div class="label_ltit">考核年月:</div>
							<div class="label_rwben">
								${it:formatDate(vardic.cym,'yyyy-MM')}
							</div>
						</div>
						
						<div class="label_hang">
							<div class="label_ltit">姓名:</div>
							<div class="label_rwben">${vardic.acheck_username }</div>
						</div>

						<div class="label_hang">
							<div class="label_ltit">部门:</div>
							<div class="label_rwben">${vardic.acheck_deptname }</div>
						</div>
					</s:if>
					<s:else>
						<div class="label_hang">
						<div class="label_ltit">考核年月:</div>
						<div class="label_rwben">
							${it:formatDate(check.ym,'yyyy-MM')}<input id="ck" type="hidden"
								name="vardic.check_ym"
								value="${check.uuid}">
							</div>
						</div>
							
						<div class="label_hang">
							<div class="label_ltit">姓名:</div>
							<div class="label_rwben">
								${user.user_name } <input type="hidden"
									name="vardic.acheck_user" value="${user.uuid}">
							</div>
						</div>

						<div class="label_hang">
							<div class="label_ltit">部门:</div>
							<div class="label_rwben">
								${user.dept_cname } <input type="hidden"
									name="vardic.acheck_usercode" value="${user.dept_code}">
							</div>
						</div>
					</s:else>

				</div>
				<s:if test="'add' == viewFlag">
					<div class="label_main">
						<fieldset class="clear">
							<legend>指标</legend>
							<table id="t" width="100%" cellpadding="0" cellspacing="0"
								border="0" class="lb_jpin">
								<tr>
									<th>编号</th>
									<th>kpi</th>
									<th>权重</th>
									<th>评分</th>
									<th>得分</th>
									<th>周期</th>
									<th>定义</th>
									<th>标准</th>
									<!-- <th>横向考核部门</th> -->
								</tr>
								<!-- lading.promotions -->

								<s:iterator value="ids" status="sta">
									<tr id="showtr${uuid}">
										<td class="nw">${uuid }</td>
										<td class="nw">${kpi }</td>
										<td class="nw">${weight }<input id="w${uuid }" name="weight"
											type="hidden" value="${weight }"></td>
										<s:if test="isdept==1&&type==1">
											<td class="nw" width="100px;"><input id="s${uuid }"
												type="text" onblur="kpi('${uuid}');"
												class="validate[required]" /></td>
											<td class="nw" width="100px;"><input id="g${uuid }"
												type="text" readonly="readonly" class="validate[required]" /></td>
										</s:if>
										<s:else>
											<s:if test="type==2">
												<td class="nw" title="取部门分数"><input id="s${uuid }"
													type="text" readonly="readonly"></td>
												<td class="nw"><input id="g${uuid }" type="text"
													readonly="readonly"></td>
												<script type="text/javascript">
													$(function(){
														var uuid=${uuid };
														var kpi=${kpi};
														var d=$('#ck').val();
														var dept=${position_dept};
														
														var ajax = new Common_Ajax('ajax_member_message');
														ajax.config.action_url = ajax_url;
														ajax.config._success = function(data, textStatus){
															var l = $(data).length;
																	$.each(data, function(i, n){
																		var sid="s"+uuid;
																		var gid="g"+uuid;
																		var w="w"+uuid;
																		
																		document.getElementById(sid).value=n.check_score;
																		document.getElementById(gid).value=n.check_score*document.getElementById(w).value;
																	});
																	
														};
														ajax.addParameter("work", "AutoComplete");
														ajax.addParameter("privilege_id", "SYS_SELECT_SCORE_KPI");
														ajax.addParameter("parameters", "dept_code=" + encodeURI(dept)+"&check_ym="+encodeURI(d)+"&kpi="+encodeURI(kpi));
														ajax.sendAjax2();
													});
													
													
													</script>
											</s:if>

											<s:elseif test="type==3">
												<td class="nw" title="取班组分数："><input id="s${uuid }"
													type="text" readonly="readonly"></td>
												<td class="nw" width="100px;"><input id="g${uuid }"
													type="text" readonly="readonly"></td>
													<s:if test="%{user.dept_code!=null}">
														<script type="text/javascript">
															$(function(){
																var uuid=${uuid };
																var kpi=${kpi};
																var d=$('#ck').val();
																var dept=${user.dept_code};
																kpibys(uuid,kpi,d,dept);
																
															});
														</script>
													</s:if>
													<s:else>
														<script type="text/javascript">
															$(function(){
																var uuid=${uuid };
																var kpi=${kpi};
																var d=$('#ck').val();
																var dept=${vardic.acheck_usercode};
																kpibys(uuid,kpi,d,dept);
																
															});
														</script>
													</s:else>
											
											</s:elseif>
											<s:else>
												<td class="nw" title="横向分数：" id="s1${uuid }"></td>
												<td class="nw" width="100px;" id="g1${uuid }"></td>
												<script type="text/javascript">
													$(function(){
														var uuid=${uuid };
														
														var ajax = new Common_Ajax('ajax_member_message');
														ajax.config.action_url = ajax_url;
														ajax.config._success = function(data, textStatus){
															var l = $(data).length;
																	$.each(data, function(i, n){
																		var sid="s1"+uuid;
																		var gid="g1"+uuid;
																		document.getElementById(sid).innerHTML=n.check_score;
																		document.getElementById(gid).innerHTML=n.check_goal;
																	});
																	
														};
														ajax.addParameter("work", "AutoComplete");
														ajax.addParameter("privilege_id", "SYS_SELECT_SCORE_KPI");
														ajax.addParameter("parameters", "kpiid=" + encodeURI(uuid));
														ajax.sendAjax2();
													});
														
											
								</script>
											</s:else>
										</s:else>
										<td class="nw">${cyc }</td>
										<td class="longnote" title="${definition}">${it:subString(definition,18)}</td>
										<td class="longnote" title="${correctly}">${it:subString(correctly,18)}</td>
										<%-- <td class="longnote"  id="c${uuid }" >${check_deptcode}</td>	 --%>
									</tr>
								</s:iterator>
							<tr>
							<td>加扣分项</td>
							<td class="nw" style="width:150px;">
							<s:textfield name="vardic.bscore" title=""
									cssClass="validate[required]"/>
							</td>
							<td colspan="8">*分值范围是-30至10分</td>
							</tr>
							
							<tr>
							<td>合计
							</td>
							<td>总分：${vardic.check_score}
							</td>
							<td id="sumC"  colspan="8">总权重：
							</td>
							</tr>
							</table>
						</fieldset>
					</div>
					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit">备注:</div>
							<div class="label_rwbenx">
									<s:textarea name="vardic.remark" title="备注" cssClass="label_hang_linput inputNote validate[maxSize[65535]]" />
							</div>
						</div>
					</div>
					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit">相关操作:</div>
							<div class="label_rwbenx">
								<font color="red"><span id="messages"></span></font>

								<c:if
									test="${it:checkPermit('SYS_QKJMANAGER_VERTICLIST_ADD',null)==true}">
									<button class="input-blue" onclick="add();">添加</button>
								</c:if>
							</div>
						</div>
					</div>
				</s:if>
			
			<s:if test="'mdy' == viewFlag">
				<div class="label_main">
					<fieldset class="clear">
						<legend>指标</legend>
						<table id="t" width="100%" cellpadding="0" cellspacing="0"
							border="0" class="lb_jpin">
							<tr>
								<th>编号</th>
								<th>kpi</th>
								<th>权重</th>
								<th>评分</th>
								<th>得分</th>
								<th>周期</th>
								<th>定义</th>
								<th>标准</th>
								<c:if
									test="${it:checkPermit('SYS_QKJMANAGER_VERTICLIST_MDY',null)==true}">
									<th>操作</th>
								</c:if>
							</tr>
							<!-- lading.promotions -->
							<s:iterator value="vds" status="sta">
								<tr id="showtr${uuid}">
									<td class="nw">${uuid }</td>
									<td class="nw">${kpi }</td>
									<td class="nw">${weight }<input id="w${uuid }" name="weight"
										type="hidden" value="${weight }"></td>
									<td class="nw" width="100px;"><input id="s${uuid }"
										type="text" onblur="kpi('${uuid}');"
										class="validate[required]" value="${check_score }" /></td>
									<td class="nw" width="100px;"><input id="g${uuid }"
										type="text" readonly="readonly" class="validate[required]"
										value="${check_goal }" /></td>
									<td class="nw">${cyc }</td>
									<td class="longnote" title="${definition}">${it:subString(definition,18)}</td>
									<td class="longnote" title="${correctly}">${it:subString(correctly,18)}</td>
									<c:if
										test="${it:checkPermit('SYS_QKJMANAGER_VERTICLIST_MDY',null)==true}">
										<c:if test="${it:checkay(1,kpi_id)==true}">
											
											<td class="longnote" title="${correctly}">
											<s:if test="%{typea==1}">
											<a class="input-blue" onclick="mdy(${uuid},${score_id })">保存</a>
											</s:if>
											<s:else>
											横向考核
											</s:else>
											</td>
										</c:if>
										<c:if test="${it:checkay(1,kpi_id)==false}">
											<td class="longnote" title="${correctly}">取部门/班组分数</td>
										</c:if>
									</c:if>
								</tr>
							</s:iterator>
							<tr>
							<td>加扣分项</td>
							<td class="nw" style="width:150px;">
							<s:textfield name="vardic.bscore" title=""
									cssClass="validate[required]"/>
							</td>
							<td colspan="8">*分值范围是-30至10分</td>
							</tr>
							
							<tr>
							<td>合计
							</td>
							<td>总分：${vardic.check_score}
							</td>
							<td id="sumC"  colspan="8">总权重：
							</td>
							</tr>
						</table>
						
					</fieldset>
				</div>
				<div class="label_main">
			<div class="label_hang">
				<div class="label_ltit">备注:</div>
				<div class="label_rwbenx">
						<s:textarea name="vardic.remark" title="备注" cssClass="label_hang_linput inputNote validate[maxSize[65535]]" />
				</div>
			</div>
		</div>
		
		<c:if test="${it:checkPermit('SYS_QKJMANAGER_VERTICLIST_MDY',null)==true}">
			<s:submit id="save" name="save" value="保存备注及加扣分项" action="varticD_save" cssClass="input-blue" />
		</c:if>
			</s:if>
			
		</s:form>
		</div>
	</div>
	
	<s:action name="ref_foot" namespace="/manager" executeResult="true" />
	<script type="text/javascript"
		src="<s:url value="/js/DatePicker.js" />"></script>
	<script type="text/javascript">

var checkSubmitFlg = false;

$(function(){
	var sum=0;
	var a=$("#sumC").text();
	
	$("input[name='weight']").each(
			function() {
				sum=sum+Number($(this).val())*100;
			}
			);
	sum=a+sum;
	$("#sumC").text(sum+"%");
});

function kpi(uuid){
	var sid="s"+uuid;
	var gid="g"+uuid;
	var wid="w"+uuid;
	var sp=document.getElementById(sid).value;
	var wp=document.getElementById(wid).value;
	document.getElementById(gid).value=sp*wp;
}

function add(){
	var obj=new Array();
	 var flag=true;
	  var tableObj = document.getElementById("t");
	  for (var i = 1; i < tableObj.rows.length; i++) {  //遍历Table的所有Row
		  var tableInfo = "";
	    for (var j = 0; j < tableObj.rows[i].cells.length; j++) {  //遍历Row中的每一列
	    	if(j==1||j==2||j==5||j==6||j==7||j==8){
	    		continue;
	    	}else{
	    		if(j==3){
	    			var sid="s"+tableObj.rows[i].cells[0].innerText;//分数
	    			var sp="";
	    			if(document.getElementById(sid)!=undefined && document.getElementById(sid).value!=''){
	    				sp=document.getElementById(sid).value;
	    				if((sp==null||sp=="")&&sp!=undefined){
		    				flag=false;
		    				break;
		    			}
		    			tableInfo += sp+",";
	    				}else{
	    					continue;
	    				};
	    			
	    		}else if(j==4){
	    			var gid="g"+tableObj.rows[i].cells[0].innerText;
	    			var gp="";
	    			if(document.getElementById(gid)!=undefined && document.getElementById(gid).value!=''){
	    				 gp=document.getElementById(gid).value;
	    				 tableInfo += gp+",";
	 	    			if((gp==null||gp=="")&&gp!='undefined'){
	 	    				flag=false;
	 	    				break;
	 	    			}
	    			}else{
    					continue;
    				};
	    			
	    		}
	    		else{
	    			tableInfo = tableObj.rows[i].cells[j].innerText;  //uuid
	    			tableInfo = tableInfo+",";
	    		};
	    	};
	    	 
	    }
	    tableInfo+=";";
	   obj[i]=tableInfo;
	   
	  }
	  
	   if (!checkSubmitFlg) {
		// 第一次提交
		  checkSubmitFlg = true;
		  if(flag==true){
			  document.getElementById("editForm").action="/qkjmanager/varticDeail_add?aArray="+obj;
		  }else{
			  alert("所评分数不能为空！");
		  }
		 } else {
		//重复提交
		  return false;
		 }
}


function mdy(uuid,so){
	var sid="s"+uuid;
	var gid="g"+uuid;
	var sp=document.getElementById(sid).value;
	var gp=document.getElementById(gid).value;
	window.location.href="/qkjmanager/varticDetail_save?vd.uuid="+uuid+"&vd.check_score="+sp+"&vd.check_goal="+gp+"&vd.score_id="+so;
}

var kpibys = function(uuid,kpi,d,dept){
	var ajax = new Common_Ajax('ajax_member_message');
	ajax.config.action_url = ajax_url;
	ajax.config._success = function(data, textStatus){
		var l = $(data).length;
		var score=0.00;
		var sid="s"+uuid;
		var gid="g"+uuid;
		var w="w"+uuid;
				$.each(data, function(i, n){
					score=score+n.check_score;
				});
				document.getElementById(sid).value=score;
				document.getElementById(gid).value=score*document.getElementById(w).value;
	};
	ajax.addParameter("work", "AutoComplete");
	ajax.addParameter("privilege_id", "SYS_SELECT_SCORE_KPI");
	ajax.addParameter("parameters", "dept_code=" + encodeURI(dept)+"&check_ym="+encodeURI(d)+"&kpi="+encodeURI(kpi));
	ajax.sendAjax2();
};

</script>
</body>
</html>