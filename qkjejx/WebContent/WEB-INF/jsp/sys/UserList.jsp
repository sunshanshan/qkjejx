<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表--<s:text name="APP_NAME" /></title>
<s:action name="ref_head" namespace="/manager" executeResult="true" />
</head>
<body>
<s:action name="nav" namespace="/manage" executeResult="true" />
<div class="tab_right">
	<div class="tab_warp main">
		<div class="dq_step">
			<a href="/manager/default">首页</a>&nbsp;&gt;&nbsp;管理员列表
			<span class="opb lb op-area"><a href="<s:url namespace="/sys" action="user_load"><s:param name="viewFlag">add</s:param></s:url>" >提交管理员</a></span>
			<span class="opb lb op-area">
	<s:hidden id="marketimgid"></s:hidden>
	</span>
		</div>
		<s:form id="serachForm" name="serachForm" action="user_list"  method="get" namespace="/sys" theme="simple">
		<div class="label_main">
			<div class="label_hang">
		       <div class="label_ltit">账号:</div>
		       <div class="label_rwben"><s:textfield id="user.title" title="帐号" name="user.title" /></div>
			</div>
			<div class="label_hang">
		       <div class="label_ltit">姓名:</div>
		       <div class="label_rwben"><s:textfield id="user.user_name" title="姓名" name="user.user_name" /></div>
			</div>
			
			<div class="label_hang">
		       <div class="label_ltit">职务:</div>
		       <div class="label_rwben"><s:textfield id="user.post" title="职务" name="user.post" /></div>
			</div>
			<div class="label_hang">
	            <div class="label_ltit">所属部门:</div>
	            <div class="label_rwben2">
	            	<span class="label_rwb">
					<s:textfield title="部门名称" id="userdept_nameid" name="user.dept_cname" readonly="true" />
					<s:hidden title="部门代码" id="userdept_codeid" name="user.dept_codelist" readonly="true" />
					</span>
					<span class="lb nw">
					<img class="detail vatop" src='<s:url value="/images/open2.gif" />' onclick="selectDept('userdept_codeid','userdept_nameid',null);" />
					<%-- <s:checkbox id="apply_is_sub_dept" name="user.is_sub_dept"></s:checkbox>
					<label for="apply_is_sub_dept"></label>包含子部门<span id="ajax_member_message"></span> --%>
					</span>
	            </div>
	        </div>
	        <div class="label_hang label_button tac">
	        	<s:checkbox id="search_mcondition" name="search_mcondition" fieldValue="true" value="true" cssClass="regular-checkbox" />
				<label for="search_mcondition"></label>更多条件
	            <s:submit value="搜索" /> <s:reset value="重置" />
	        </div>
		</div>
		</s:form>
		<s:form id="formEdit" name="form1" cssClass="validForm" action="user_add" namespace="/sys" onsubmit="return validator(this);" method="post" theme="simple">
		<div class="tab_warp">
	 		<table id="table1">
	 			<tr id="coltr">
	              <th  class="td1">用户名</th>
	              <th  class="td1">姓名</th>
	              <th  class="td2">一级部门</th>
	              <th  class="td2">二级部门</th>
	              <th  class="td2">三级部门</th>
	              <th  class="td2">职务</th>
	              <th  class="td3">联系方式</th>
	              <th  class="td4">状态</th>
	              <th  class="td5">操作</th>
	              <th  class="td0">查看</th>
	            </tr>
	            <s:set name="_status" value="#{0:'初始',1:'正常',2:'冻结','10000':'测试'}" />
				<s:iterator value="users" status="sta">
				<tr id="showtr${uuid}">
	              <td class="td1"><input name="user.uuid" type="checkbox" value="<s:property value="uuid" />" />${title}</td>
	              <td class="td1">${user_name}</td>
	              <td class="td2">${pparname}</td>
	              <td class="td2">${pardname}</td>
	              <td class="td2">${dept_cname}</td>
	              <td class="td2">${position_name}</td>
	              <td class="td3">${mobile}</td>
	              <td class="td4">
	              		<s:set var="_status" value="%{status}" />
					    <s:if test="#_status == 0">初始</s:if>
					    <s:elseif test="#_status == 1">正常</s:elseif>
					    <s:elseif test="#_status == 2">冻结</s:elseif>
						<s:else>其他</s:else>
	              </td>
	              <td class="td5 op-area">
	              		<a class="input-blue" href="<s:url namespace="/sys" action="user_load"><s:param name="viewFlag">mdy</s:param><s:param name="user.uuid" value="uuid"></s:param></s:url>">修改</a>
	    				<a class="input-red" href="<s:url namespace="/sys" action="user_del"><s:param name="user.uuid" value="uuid"></s:param></s:url>" onclick="return isDel();">删除</a>
	              </td>
	              <td  class="td0 op-area"><a onclick="showDetail('showtr${uuid}');" href="javascript:;" class="input-nostyle">查看</a></td>
	            </tr>
				</s:iterator>
	         </table>
	      </div>
	      <div id="listpage" class="pagination"></div>
	     <div class="label_hang">
		       <div class="label_ltit">汇报人部门:</div>
		       <div class="label_rwben2">
		       		<span class="label_rwb">
					<s:textfield title="部门名称" id="p_name" name="user.p_dname" readonly="true" />
					<s:hidden title="部门代码" id="p_code" name="user.p_code" readonly="true" />
					</span>
					<span class="lb nw">
					<img class="detail vatop" src='<s:url value="/images/open2.gif" />' onclick="selectDept('p_code','p_name',true);" />
					</span>
		       </div>
			</div>
			
			<div class="label_hang">
		       <div class="label_ltit">汇报人:</div>
		       <div class="label_rwbenx">
		       <%-- <s:select name="user.position" list="positions" listKey="uuid" listValue="position_name" headerKey="" headerValue="--请选择--" cssClass="validate[required]"/> --%>
		       <s:select id="membermanagerid" cssClass="selectKick" name="user.parent_user" list="#{}" headerKey="" headerValue="--请选择--"/>
		       </div>
			</div>
			
			<div class="label_hang">
		       <div class="label_ltit">所属部门:</div>
		       <div class="label_rwben2">
		       		<span class="label_rwb">
					<s:textfield title="部门名称" id="dept_cname" name="user.dept_cname" readonly="true" />
					<s:hidden title="部门代码" id="dept_code" name="user.dept_code" readonly="true" />
					</span>
					<span class="lb nw">
					<img class="detail vatop" src='<s:url value="/images/open2.gif" />' onclick="selectDept('dept_code','dept_cname',false);" />
					</span>
		       </div>
			</div>
			
			<s:submit id="save" name="save" value="保存" action="user_saveP" cssClass="input-blue"/>
		</s:form>
	      <div class="tab_warp"><span id="message"><s:property value="message" /></span></div>
	</div>
</div>
<s:action name="ref_foot" namespace="/manager" executeResult="true" />
<script type="text/javascript">
$(function(){
	printPagination("listpage",'${currPage}','${recCount}','${pageSize}');
});

$(document).ready(function(){
	$("#filebtn").removeClass("filearea"); //提交样式marketimgid_filebutton
	$("#marketimgid_filebutton").val("选择导入文件");
});
</script>
</body>
</html>