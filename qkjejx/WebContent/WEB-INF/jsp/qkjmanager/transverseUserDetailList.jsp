<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考核管理--<s:text name="APP_NAME" /></title>
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
 	<div class="tab_warp main" >
		<div class="dq_step">
			${path}
				<span class="opb lb op-area"><a href="JavaScript:history.go(-1)">返回</a></span>
		</div>
	<s:form id="editForm" name="editForm" cssClass="validForm" action="transverseDeail_addUser" namespace="/qkjmanager" method="post" theme="simple">
		<div class="label_main">
			<s:if test="'mdy' == viewFlag">	<s:hidden name="vardic.uuid" value="%{vardic.uuid}"></s:hidden>
			<div class="label_hang">
				<div class="label_ltit">考核年月:</div>
					<div class="label_rwben">
					${it:formatDate(vardic.cym,'yyyy-MM')}
					</div>
			</div>
			<div class="label_hang">
				<div class="label_ltit">姓名:</div>
					<div class="label_rwben" id="aut"><input id="au" type="hidden" value="${vardic.acheck_user}">
							
							<script type="text/javascript">
															$(function(){
																var au=$('#au').val();
																aus(au);
																
															});
							</script>
							</div>
			</div>	
			
			<div class="label_hang">
				<div class="label_ltit">部门:</div>
					<div class="label_rwben" id="adt">${vardic.acheck_deptname }
							
							<input id="ad" type="hidden" value="${vardic.acheck_usercode}">
							<script type="text/javascript">
															$(function(){
																var au=$('#ad').val();
																ads(au);
																
															});
							</script>
							</div>
			</div>	
			</s:if>
			<s:else>
			<div class="label_hang">
				<div class="label_ltit">考核年月:</div>
					<div class="label_rwben">
					${it:formatDate(check.ym,'yyyy-MM')}
						<input type="hidden" name="vardic.check_ym" value="${check.uuid}">
					</div>
			</div>
			<div class="label_hang">
				<div class="label_ltit">姓名:</div>
					<div class="label_rwben">
						${user.user_name }
						<input type="hidden" name="vardic.acheck_user" value="${user.uuid}">
					</div>
			</div>	
			
			<div class="label_hang">
				<div class="label_ltit">部门:</div>
					<div class="label_rwben">
						${user.dept_cname }
						<input type="hidden" name="vardic.acheck_usercode" value="${user.dept_code}">
					</div>
			</div>	
			</s:else>
						
		</div>
		<s:if test="'add' == viewFlag">			
		<div class="label_main">
			<fieldset class="clear">
				<legend>指标</legend>
				
					<table id="t" width="100%" cellpadding="0" cellspacing="0" border="0" class="lb_jpin">
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
						<s:if test="type==2&&goal==null"></s:if>
						<s:else>
						<tr id="showtr${uuid}">
								<td class="nw">${uuid }</td>
								<td class="nw">${kpi }</td>
								<td class="nw">${weight }<input id="w${uuid }" type="hidden"  value="${weight }"></td>
								<s:if test="type==1">
								<td class="nw" width="100px;"><input id="s${uuid }" type="text" onblur="kpi('${uuid}');" class="validate[required]" /></td>
								<td class="nw" width="100px;"><input id="g${uuid }" type="text" readonly="readonly" class="validate[required]"/></td>
								</s:if>
								<s:else>
								<td class="nw" width="100px;">取部门分数：${score }<input id="s${uuid }" type="hidden" value="${score }"></td>
								<td class="nw" width="100px;">${goal }<input id="g${uuid }" type="hidden" value="${goal }"></td>
								</s:else>
								<td class="nw">${cyc }</td>
								<td class="longnote" title="${definition}">${it:subString(definition,18)}</td>
								<td class="longnote" title="${correctly}">${it:subString(correctly,18)}</td>			
								<%-- <td class="longnote"  id="c${uuid }" >${check_deptcode}</td>	 --%>		
							</tr>
						</s:else>
						</s:iterator>
					</table>
				</fieldset>
			</div>
			<div class="label_main">
			<div class="label_hang">
				<div class="label_ltit">备注:</div>
				<div class="label_rwbenx">
						<s:textarea name="vardic.hremark" title="备注" cssClass="label_hang_linput inputNote validate[maxSize[65535]]" />
				</div>
			</div>
		</div>
			<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit">相关操作:</div>
							<div class="label_rwbenx">
								<font color="red"><span id="messages"></span></font>
								
									<c:if test="${it:checkPermit('SYS_QKJMANAGER_HORILIST_ADD',null)==true}">
										<button  id="btnzhuce" class="input-blue" onclick="add();">提交</button>
									</c:if>
							</div>
						</div>
			</div>
			</s:if>
		
		<s:if test="'mdy' == viewFlag">	
		<div class="label_main">
			<fieldset class="clear">
				<legend>指标</legend>
					<table id="t" width="100%" cellpadding="0" cellspacing="0" border="0" class="lb_jpin">
						<tr>
							<th>编号</th>
							<th>kpi</th>
							<th>权重</th>
							<th>评分</th>
							<th>得分</th>
							<th>周期</th>
							<th>定义</th>
							<th>标准</th>		
							<c:if test="${it:checkPermit('SYS_QKJMANAGER_VERTICLIST_MDY',null)==true}">		
							<th>操作</th>
							</c:if>
						</tr>
										<!-- lading.promotions -->
						<s:iterator value="vds" status="sta">
							<tr id="showtr${uuid}">
								<td class="nw">${uuid }</td>
								<td class="nw">${kpi }</td>
								<td class="nw">${weight }<input id="w${uuid }" type="hidden"  value="${weight }"></td>
								<td class="nw" width="100px;"><input id="s${uuid }"  type="text" onblur="kpi('${uuid}');" class="validate[required]" value="${check_score }"/></td>
								<td class="nw" width="100px;"><input id="g${uuid }" type="text" readonly="readonly" class="validate[required]" value="${check_goal }"/></td>
								<td class="nw">${cyc }</td>
								<td class="longnote" title="${definition}">${it:subString(definition,18)}</td>
								<td class="longnote" title="${correctly}">${it:subString(correctly,18)}</td>
								<c:if test="${it:checkPermit('SYS_QKJMANAGER_VERTICLIST_MDY',null)==true}">
								<td class="longnote" title="${correctly}">
								<a class="input-blue" onclick="mdy(${uuid},${score_id })">保存</a>
								</td>
								</c:if>			
							</tr>
						</s:iterator>
					</table>
				</fieldset>
			</div>
			<div class="label_main">
			<div class="label_hang">
				<div class="label_ltit">备注:</div>
				<div class="label_rwbenx">
						<s:textarea name="vardic.hremark" title="备注" cssClass="label_hang_linput inputNote validate[maxSize[65535]]" />
				</div>
			</div>
		</div>
		</s:if>
		
		</s:form>
	</div>
</div>

<s:action name="ref_foot" namespace="/manager" executeResult="true" />
<script type="text/javascript" src="<s:url value="/js/DatePicker.js" />"></script>
<script type="text/javascript">
var checkSubmitFlg = false;

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
	    	if(j==1||j==2||j==5||j==6||j==7){
	    		continue;
	    	}else{
	    		if(j==3){
	    			var sid="s"+tableObj.rows[i].cells[0].innerText;//分数
	    			var sp="";
	    			if(document.getElementById(sid)!=undefined && document.getElementById(sid).value!=''){
	    				sp=document.getElementById(sid).value;
	    				}else{
	    					continue;
	    				}
	    			if(sp==null||sp==""){
	    				flag=false;
	    				break;
	    			}
	    			tableInfo += sp+",";
	    		}else if(j==4){
	    			var gid="g"+tableObj.rows[i].cells[0].innerText;
	    			var gp="";
	    			if(document.getElementById(gid)!=undefined && document.getElementById(gid).value!=''){
	    				 gp=document.getElementById(gid).value;
	    			}else{
    					continue;
    				}
	    			tableInfo += gp+",";
	    			if(gp==null||gp==""){
	    				flag=false;
	    				break;
	    			}
	    		}
	    		else if(j==8){
	    			var cid="c"+tableObj.rows[i].cells[0].innerText;
	    			var gp="";
	    			if(document.getElementById(gid)!=undefined && document.getElementById(gid).value!=''){
	    				 gp=document.getElementById(gid).value;
	    			}else{
    					continue;
    				}
	    			tableInfo += cp+",";
	    			if(cp==null||cp==""){
	    				flag=false;
	    				break;
	    			}
	    		}else{
	    			tableInfo = tableObj.rows[i].cells[j].innerText;  //uuid
	    			tableInfo = tableInfo+",";
	    		}
	    	}
	    	 
	    }
	    tableInfo+=";";
	   obj[i]=tableInfo;
	   
	  }
	   if (!checkSubmitFlg) {
		// 第一次提交
		  checkSubmitFlg = true;
		  if(flag==true){
			  $('#btnzhuce').hide();
			  document.getElementById("editForm").action="/qkjmanager/transverseDeail_addUser?aArray="+obj;
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
	window.location.href="/qkjmanager/transverseDetail_saveUser?vd.uuid="+uuid+"&vd.check_score="+sp+"&vd.check_goal="+gp+"&vd.score_id="+so;
};

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


var ads= function(ad){
	var ajax = new Common_Ajax('ajax_member_message');
	ajax.config.action_url = ajax_url;
	ajax.config._success = function(data, textStatus){
		var l = $(data).length;
				if(l==1){
					$('#adt').html($(data)[0].dept_cname);
				}
				
	};
	ajax.addParameter("work", "AutoComplete");
	ajax.addParameter("privilege_id", "SYS_SELECT_DEPT_LISTBYDEPT");
	ajax.addParameter("parameters", "dept_code=" + encodeURI(ad));
	ajax.sendAjax2();
};

</script>
</body>
</html>