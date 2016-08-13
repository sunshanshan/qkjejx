<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绩效反馈--<s:text name="APP_NAME" /></title>
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
			
		</div>
		<s:form id="serachForm" name="serachForm" action="report_listbg" method="get" namespace="/qkjmanager" theme="simple">
			<div class="label_con">
				<div class="label_main">
					<div class="label_hang">
						<div class="label_ltit">考核年月:</div>
						<div class="label_rwben">
							<input id="begintime" name="vardic.cym" value="${it:formatDate(vardic.cym,'yyyy-MM')}" type="text" onclick="setmonth(this)" readonly="readonly"/>
						</div>
					</div>
							
					<div class="label_hang tac">
						<s:submit value="搜索" />
						<s:reset value="重置" />
						<c:if test="${it:checkPermit('SYS_QKJMANAGER_VERTICLIST_CHECKSURE',null)==true}">
						<span class="opb lb op-area"><a href="<s:url namespace="/qkjmanager" action="check_sure"></s:url>">审核考核成绩</a></span>
						</c:if>
					</div>
				</div>
			</div>
		</s:form>
			<table>
				<tr><td bgcolor="white" style="text-align: left;" colspan="5">信息中心总监</td></tr>
				<tr id="coltr">
					<th class="td1"　w>部门编号</th>
					<th class="td1">部门</th>
					<th class="td1">本月得分</th>
					<th class="td1">本月评级</th>
					<th class="td2">本月系数</th>
					<th class="td1">本月状态</th>
					<th class="td2">备注</th>
				</tr>
				<s:iterator value="bgs" status="sta">
					<s:if test="%{parent_dept=='101'}"><tr id="showtr${dept_code}">
						<td class="td1 nw">${dept_code} 
						<script type="text/javascript">
							$(function(){
									var d=${dept_code};
									var cym=$('#begintime').val();
									ads(d,cym);
							});
						</script>
						</td>
						<td class="td1 nw"><a href="<s:url namespace="/qkjmanager" action="report_listbgu"><s:param name="vardic.acheck_usercode" value="dept_code"></s:param><s:param name="vardic.cym">${it:formatDate(vardic.cym,'yyyy-MM')}</s:param></s:url>">${dept_cname}</a></td>
						<td class="td1 nw" id="score${dept_code }">
						</td>
						<td class="td1 nw" id="p${dept_code }">
						
						</td>
						<td class="td2 nw" id="x${dept_code }">
						
						</td>
						<td class="td1 nw" id="z${dept_code }"></td>
						<td class="td2 nw" id="b${dept_code }"></td>
					</tr></s:if>
				</s:iterator>
				
			</table>
			<table>
				<tr><td bgcolor="white" style="text-align: left;"  colspan="5">副总经理兼后勤保障中心总监</td></tr>
				<tr id="coltr">
					<th class="td1">部门编号</th>
					<th class="td1">部门</th>
					<th class="td1">本月得分</th>
					<th class="td1">本月评级</th>
					<th class="td2">本月系数</th>
					<th class="td1">本月状态</th>
					<th class="td2">备注</th>
				</tr>
				<s:iterator value="bgs" status="sta">
					<s:if test="%{parent_dept=='102'}"><tr id="showtr${dept_code}">
						<td class="td1 nw">${dept_code} 
						<script type="text/javascript">
							$(function(){
									var d=${dept_code};
									var cym=$('#begintime').val();
									ads(d,cym);
							});
						</script>
						</td>
						<td class="td1 nw"><a href="<s:url namespace="/qkjmanager" action="report_listbgu"><s:param name="vardic.acheck_usercode" value="dept_code"></s:param><s:param name="vardic.cym">${it:formatDate(vardic.cym,'yyyy-MM')}</s:param></s:url>">${dept_cname}</a></td>
						<td class="td1 nw" id="score${dept_code }">
						</td>
						<td class="td1 nw" id="p${dept_code }">
						
						</td>
						<td class="td2 nw" id="x${dept_code }">
						
						</td>
						<td class="td1 nw" id="z${dept_code }"></td>
						<td class="td2 nw" id="b${dept_code }"></td>
					</tr></s:if>
				</s:iterator>
				
			</table>
			<table>
				<tr><td bgcolor="white" style="text-align: left;"  colspan="5">董秘</td></tr>
				<tr id="coltr">
					<th class="td1">部门编号</th>
					<th class="td1">部门</th>
					<th class="td1">本月得分</th>
					<th class="td1">本月评级</th>
					<th class="td2">本月系数</th>
					<th class="td1">本月状态</th>
					<th class="td2">备注</th>
				</tr>
				<s:iterator value="bgs" status="sta">
					<s:if test="%{parent_dept=='108'}"><tr id="showtr${dept_code}">
						<td class="td1 nw">${dept_code} 
						<script type="text/javascript">
							$(function(){
									var d=${dept_code};
									var cym=$('#begintime').val();
									ads(d,cym);
							});
						</script>
						</td>
						<td class="td1 nw"><a href="<s:url namespace="/qkjmanager" action="report_listbgu"><s:param name="vardic.acheck_usercode" value="dept_code"></s:param><s:param name="vardic.cym">${it:formatDate(vardic.cym,'yyyy-MM')}</s:param></s:url>">${dept_cname}</a></td>
						<td class="td1 nw" id="score${dept_code }">
						</td>
						<td class="td1 nw" id="p${dept_code }">
						
						</td>
						<td class="td2 nw" id="x${dept_code }">
						
						</td>
						<td class="td1 nw" id="z${dept_code }"></td>
						<td class="td2 nw" id="b${dept_code }"></td>
					</tr></s:if>
				</s:iterator>
				
			</table>
			<table>
				<tr><td bgcolor="white" style="text-align: left;"  colspan="5">工会主席</td></tr>
				<tr id="coltr">
					<th class="td1">部门编号</th>
					<th class="td1">部门</th>
					<th class="td1">本月得分</th>
					<th class="td1">本月评级</th>
					<th class="td2">本月系数</th>
					<th class="td1">本月状态</th>
					<th class="td2">备注</th>
				</tr>
				<s:iterator value="bgs" status="sta">
					<s:if test="%{parent_dept=='10901'}"><tr id="showtr${dept_code}">
						<td class="td1 nw">${dept_code} 
						<script type="text/javascript">
							$(function(){
									var d=${dept_code};
									var cym=$('#begintime').val();
									ads(d,cym);
							});
						</script>
						</td>
						<td class="td1 nw"><a href="<s:url namespace="/qkjmanager" action="report_listbgu"><s:param name="vardic.acheck_usercode" value="dept_code"></s:param><s:param name="vardic.cym">${it:formatDate(vardic.cym,'yyyy-MM')}</s:param></s:url>">${dept_cname}</a></td>
						<td class="td1 nw" id="score${dept_code }">
						</td>
						<td class="td1 nw" id="p${dept_code }">
						
						</td>
						<td class="td2 nw" id="x${dept_code }">
						
						</td>
						<td class="td1 nw" id="z${dept_code }"></td>
						<td class="td2 nw" id="b${dept_code }"></td>
					</tr></s:if>
				</s:iterator>
				
			</table>
			<table>
				<tr><td bgcolor="white" style="text-align: left;"  colspan="5">总经理助理</td></tr>
				<tr id="coltr">
					<th class="td1">部门编号</th>
					<th class="td1">部门</th>
					<th class="td1">本月得分</th>
					<th class="td1">本月评级</th>
					<th class="td2">本月系数</th>
					<th class="td1">本月状态</th>
					<th class="td2">备注</th>
				</tr>
				<s:iterator value="bgs" status="sta">
					<s:if test="%{parent_dept=='109'}"><tr id="showtr${dept_code}">
						<td class="td1 nw">${dept_code} 
						<script type="text/javascript">
							$(function(){
									var d=${dept_code};
									var cym=$('#begintime').val();
									ads(d,cym);
							});
						</script>
						</td>
						<td class="td1 nw"><a href="<s:url namespace="/qkjmanager" action="report_listbgu"><s:param name="vardic.acheck_usercode" value="dept_code"></s:param><s:param name="vardic.cym">${it:formatDate(vardic.cym,'yyyy-MM')}</s:param></s:url>">${dept_cname}</a></td>
						<td class="td1 nw" id="score${dept_code }">
						</td>
						<td class="td1 nw" id="p${dept_code }">
						
						</td>
						<td class="td2 nw" id="x${dept_code }">
						
						</td>
						<td class="td1 nw" id="z${dept_code }"></td>
						<td class="td2 nw" id="b${dept_code }"></td>
					</tr></s:if>
				</s:iterator>
				
			</table>
			<table>
				<tr><td bgcolor="white" style="text-align: left;"  colspan="5">技术质量中心总监</td></tr>
				<tr id="coltr">
					<th class="td1">部门编号</th>
					<th class="td1">部门</th>
					<th class="td1">本月得分</th>
					<th class="td1">本月评级</th>
					<th class="td2">本月系数</th>
					<th class="td1">本月状态</th>
					<th class="td2">备注</th>
				</tr>
				<s:iterator value="bgs" status="sta">
					<s:if test="%{parent_dept=='104'}"><tr id="showtr${dept_code}">
						<td class="td1 nw">${dept_code} 
						<script type="text/javascript">
							$(function(){
									var d=${dept_code};
									var cym=$('#begintime').val();
									ads(d,cym);
							});
						</script>
						</td>
						<td class="td1 nw"><a href="<s:url namespace="/qkjmanager" action="report_listbgu"><s:param name="vardic.acheck_usercode" value="dept_code"></s:param><s:param name="vardic.cym">${it:formatDate(vardic.cym,'yyyy-MM')}</s:param></s:url>">${dept_cname}</a></td>
						<td class="td1 nw" id="score${dept_code }">
						</td>
						<td class="td1 nw" id="p${dept_code }">
						
						</td>
						<td class="td2 nw" id="x${dept_code }">
						
						</td>
						<td class="td1 nw" id="z${dept_code }"></td>
						<td class="td2 nw" id="b${dept_code }"></td>
					</tr></s:if>
				</s:iterator>
				
			</table>
			<table>
				<tr><td bgcolor="white" style="text-align: left;"  colspan="5">审计中心总监</td></tr>
				<tr id="coltr">
					<th class="td1">部门编号</th>
					<th class="td1">部门</th>
					<th class="td1">本月得分</th>
					<th class="td1">本月评级</th>
					<th class="td2">本月系数</th>
					<th class="td1">本月状态</th>
					<th class="td2">备注</th>
				</tr>
				<s:iterator value="bgs" status="sta">
					<s:if test="%{parent_dept=='110'}"><tr id="showtr${dept_code}">
						<td class="td1 nw">${dept_code} 
						<script type="text/javascript">
							$(function(){
									var d=${dept_code};
									var cym=$('#begintime').val();
									ads(d,cym);
							});
						</script>
						</td>
						<td class="td1 nw"><a href="<s:url namespace="/qkjmanager" action="report_listbgu"><s:param name="vardic.acheck_usercode" value="dept_code"></s:param><s:param name="vardic.cym">${it:formatDate(vardic.cym,'yyyy-MM')}</s:param></s:url>">${dept_cname}</a></td>
						<td class="td1 nw" id="score${dept_code }">
						</td>
						<td class="td1 nw" id="p${dept_code }">
						
						</td>
						<td class="td2 nw" id="x${dept_code }">
						
						</td>
						<td class="td1 nw" id="z${dept_code }"></td>
						<td class="td2 nw" id="b${dept_code }"></td>
					</tr></s:if>
				</s:iterator>
				
			</table>
			<table>
				<tr><td bgcolor="white" style="text-align: left;"  colspan="5">设备动力部经理</td></tr>
				<tr id="coltr">
					<th class="td1">部门编号</th>
					<th class="td1">部门</th>
					<th class="td1">本月得分</th>
					<th class="td1">本月评级</th>
					<th class="td2">本月系数</th>
					<th class="td1">本月状态</th>
					<th class="td2">备注</th>
				</tr>
				<s:iterator value="bgs" status="sta">
					<s:if test="%{parent_dept=='10204'}"><tr id="showtr${dept_code}">
						<td class="td1 nw">${dept_code} 
						<script type="text/javascript">
							$(function(){
									var d=${dept_code};
									var cym=$('#begintime').val();
									ads(d,cym);
							});
						</script>
						</td>
						<td class="td1 nw"><a href="<s:url namespace="/qkjmanager" action="report_listbgu"><s:param name="vardic.acheck_usercode" value="dept_code"></s:param><s:param name="vardic.cym">${it:formatDate(vardic.cym,'yyyy-MM')}</s:param></s:url>">${dept_cname}</a></td>
						<td class="td1 nw" id="score${dept_code }">
						</td>
						<td class="td1 nw" id="p${dept_code }">
						
						</td>
						<td class="td2 nw" id="x${dept_code }">
						
						</td>
						<td class="td1 nw" id="z${dept_code }"></td>
						<td class="td2 nw" id="b${dept_code }"></td>
					</tr></s:if>
				</s:iterator>
				
			</table>
			<table>
				<tr><td bgcolor="white" style="text-align: left;"  colspan="5">物流中心总监</td></tr>
				<tr id="coltr">
					<th class="td1">部门编号</th>
					<th class="td1">部门</th>
					<th class="td1">本月得分</th>
					<th class="td1">本月评级</th>
					<th class="td2">本月系数</th>
					<th class="td1">本月状态</th>
					<th class="td2">备注</th>
				</tr>
				<s:iterator value="bgs" status="sta">
					<s:if test="%{parent_dept=='106'}"><tr id="showtr${dept_code}">
						<td class="td1 nw">${dept_code} 
						<script type="text/javascript">
							$(function(){
									var d=${dept_code};
									var cym=$('#begintime').val();
									ads(d,cym);
							});
						</script>
						</td>
						<td class="td1 nw"><a href="<s:url namespace="/qkjmanager" action="report_listbgu"><s:param name="vardic.acheck_usercode" value="dept_code"></s:param><s:param name="vardic.cym">${it:formatDate(vardic.cym,'yyyy-MM')}</s:param></s:url>">${dept_cname}</a></td>
						<td class="td1 nw" id="score${dept_code }">
						</td>
						<td class="td1 nw" id="p${dept_code }">
						
						</td>
						<td class="td2 nw" id="x${dept_code }">
						
						</td>
						<td class="td1 nw" id="z${dept_code }"></td>
						<td class="td2 nw" id="b${dept_code }"></td>
					</tr></s:if>
				</s:iterator>
				
			</table>
			<table>
				<tr><td bgcolor="white" style="text-align: left;"  colspan="5">财务中心总监</td></tr>
				<tr id="coltr">
					<th class="td1">部门编号</th>
					<th class="td1">部门</th>
					<th class="td1">本月得分</th>
					<th class="td1">本月评级</th>
					<th class="td2">本月系数</th>
					<th class="td1">本月状态</th>
					<th class="td2">备注</th>
				</tr>
				<s:iterator value="bgs" status="sta">
					<s:if test="%{parent_dept=='105'}"><tr id="showtr${dept_code}">
						<td class="td1 nw">${dept_code} 
						<script type="text/javascript">
							$(function(){
									var d=${dept_code};
									var cym=$('#begintime').val();
									ads(d,cym);
							});
						</script>
						</td>
						<td class="td1 nw"><a href="<s:url namespace="/qkjmanager" action="report_listbgu"><s:param name="vardic.acheck_usercode" value="dept_code"></s:param><s:param name="vardic.cym">${it:formatDate(vardic.cym,'yyyy-MM')}</s:param></s:url>">${dept_cname}</a></td>
						<td class="td1 nw" id="score${dept_code }">
						</td>
						<td class="td1 nw" id="p${dept_code }">
						
						</td>
						<td class="td2 nw" id="x${dept_code }">
						
						</td>
						<td class="td1 nw" id="z${dept_code }"></td>
						<td class="td2 nw" id="b${dept_code }"></td>
					</tr></s:if>
				</s:iterator>
				
			</table>
			<table>
				<tr><td bgcolor="white" style="text-align: left;"  colspan="5">生产中心总监</td></tr>
				<tr id="coltr">
					<th class="td1">部门编号</th>
					<th class="td1">部门</th>
					<th class="td1">本月得分</th>
					<th class="td1">本月评级</th>
					<th class="td2">本月系数</th>
					<th class="td1">本月状态</th>
					<th class="td2">备注</th>
				</tr>
				<s:iterator value="bgs" status="sta">
					<s:if test="%{parent_dept=='103'}"><tr id="showtr${dept_code}">
						<td class="td1 nw">${dept_code} 
						<script type="text/javascript">
							$(function(){
									var d=${dept_code};
									var cym=$('#begintime').val();
									ads(d,cym);
							});
						</script>
						</td>
						<td class="td1 nw"><a href="<s:url namespace="/qkjmanager" action="report_listbgu"><s:param name="vardic.acheck_usercode" value="dept_code"></s:param><s:param name="vardic.cym">${it:formatDate(vardic.cym,'yyyy-MM')}</s:param></s:url>">${dept_cname}</a></td>
						<td class="td1 nw" id="score${dept_code }">
						</td>
						<td class="td1 nw" id="p${dept_code }">
						
						</td>
						<td class="td2 nw" id="x${dept_code }">
						
						</td>
						<td class="td1 nw" id="z${dept_code }"></td>
						<td class="td2 nw" id="b${dept_code }"></td>
					</tr></s:if>
				</s:iterator>
				
			</table>
			<table>
				<tr><td bgcolor="white" style="text-align: left;"  colspan="5">人力资源中心总监</td></tr>
				<tr id="coltr">
					<th class="td1">部门编号</th>
					<th class="td1">部门</th>
					<th class="td1">本月得分</th>
					<th class="td1">本月评级</th>
					<th class="td2">本月系数</th>
					<th class="td1">本月状态</th>
					<th class="td2">备注</th>
				</tr>
				<s:iterator value="bgs" status="sta">
					<s:if test="%{parent_dept=='107'}"><tr id="showtr${dept_code}">
						<td class="td1 nw">${dept_code} 
						<script type="text/javascript">
							$(function(){
									var d=${dept_code};
									var cym=$('#begintime').val();
									ads(d,cym);
							});
						</script>
						</td>
						<td class="td1 nw"><a href="<s:url namespace="/qkjmanager" action="report_listbgu"><s:param name="vardic.acheck_usercode" value="dept_code"></s:param><s:param name="vardic.cym">${it:formatDate(vardic.cym,'yyyy-MM')}</s:param></s:url>">${dept_cname}</a></td>
						<td class="td1 nw" id="score${dept_code }">
						</td>
						<td class="td1 nw" id="p${dept_code }">
						
						</td>
						<td class="td2 nw" id="x${dept_code }">
						
						</td>
						<td class="td1 nw" id="z${dept_code }"></td>
						<td class="td2 nw" id="b${dept_code }"></td>
					</tr></s:if>
				</s:iterator>
				
			</table>
		
		
	</div>
</div>

<s:action name="ref_foot" namespace="/manager" executeResult="true" />
<script type="text/javascript" src="<s:url value="/js/DatePicker.js" />"></script>
<script type="text/javascript">
$(function(){
	printPagination("listpage",'${currPage}','${recCount}','${pageSize}');
 });

var ads= function(dept,cym){
	
	var ajax = new Common_Ajax('ajax_member_message');
	ajax.config.action_url = ajax_url;
	ajax.config._success = function(data, textStatus){
		var l = $(data).length;
				if(l==1){
					$('#score'+dept).html($(data)[0].check_score);
					if($(data)[0].check_score>=90){
						$('#p'+dept).html("A"); 
						$('#x'+dept).html("1.3");
					}else if($(data)[0].check_score>=80&&$(data)[0].check_score<90){
						$('#p'+dept).html("B");
						$('#x'+dept).html("1.1");
					}else if($(data)[0].check_score>=60&&$(data)[0].check_score<80){
						$('#p'+dept).html("C");
						$('#x'+dept).html("1.0");
					}else if($(data)[0].check_score<60){
						$('#p'+dept).html("D");
						$('#x'+dept).html("0.8");
					};
					
					if($(data)[0].cstate==2){
						$('#z'+dept).html("已审核");
					}else if($(data)[0].cstate==1){
						$('#z'+dept).html("已关闭");
					}
					else if($(data)[0].cstate==0){
						$('#z'+dept).html("已考核");
					}
					$('#b'+dept).html($(data)[0].remark.substring(0,8));
					$('#b'+dept).attr("title",$(data)[0].remark);
				}else if(l<1){
					$('#score'+dept).html(0.00);
					$('#p'+dept).html("0");
					$('#x'+dept).html("0");
					$('#z'+dept).html("未考核");
				};
				
	};
	ajax.addParameter("work", "AutoComplete");
	ajax.addParameter("privilege_id", "SYS_SELECT_SCORE_UORD");
	ajax.addParameter("parameters", "acheck_usercode=" + encodeURI(dept)+"&cym="+encodeURI(cym));
	ajax.sendAjax2();
};


var adds= function(uuid,ad){
	var ajax = new Common_Ajax('ajax_member_message');
	ajax.config.action_url = ajax_url;
	ajax.config._success = function(data, textStatus){
		var l = $(data).length;
				if(l==1){
					$('#d'+uuid).html($(data)[0].dept_cname);
				};
				
	};
	ajax.addParameter("work", "AutoComplete");
	ajax.addParameter("privilege_id", "SYS_SELECT_DEPT_LISTBYDEPT");
	ajax.addParameter("parameters", "dept_code=" + encodeURI(ad));
	ajax.sendAjax2();
};

var aps= function(uuid,ad){
	var ajax = new Common_Ajax('ajax_member_message');
	ajax.config.action_url = ajax_url;
	ajax.config._success = function(data, textStatus){
		var l = $(data).length;
				if(l==1){
					$('#po'+uuid).html($(data)[0].position_name);
				};
				
	};
	ajax.addParameter("work", "AutoComplete");
	ajax.addParameter("privilege_id", "QKJCJ_SYS_AJAXLOAD_POSBYID");
	ajax.addParameter("parameters", "uuid=" + encodeURI(ad));
	ajax.sendAjax2();
};
</script>
</body>
</html>