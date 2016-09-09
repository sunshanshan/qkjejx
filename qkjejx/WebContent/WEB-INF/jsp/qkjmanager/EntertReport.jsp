<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品列表--<s:text name="APP_NAME" /></title>
<s:action name="ref_head" namespace="/manager" executeResult="true" />
</head>
<body onload="type();">
<!-- 顶部和左侧菜单导航 -->
<s:action name="nav" namespace="/manage" executeResult="true" />
<div class="tab_right">
 	<div class="tab_warp main" >
	<div class="dq_step">
		${path}
		<c:if test="${it:checkPermit('QKJ_QKJMANAGE_ENTERTAI',null)==true}">
			<span class="opb lb op-area">
				<a class="input-gray" href="<s:url namespace="/qkjmanage" action="entert_list"><s:param name="viewFlag">relist</s:param></s:url>">返回列表</a>
				<a href="<s:url namespace="/qkjmanage" action="entert_print"></s:url>">导出详情</a>
				<a href="<s:url namespace="/qkjmanage" action="entertProduct_list"></s:url>">详情</a>
			</span>
		</c:if>
	</div>
	<s:form id="serachForm" name="serachForm" action="entert_report"  method="get" namespace="/qkjmanage" theme="simple">
 	<div class="label_con">
 	<div class="label_main">
	 	<div class="label_hang">
	 		 <div class="label_ltit">查询维度:</div>
	 		 <div class="label_rwben label_rwb">
            	<s:select id="report_type" name="entert.report_type" cssClass="selectKick" list="#{0:'原始维度',1:'部门维度',2:'产品维度'}"/>
            </div>
	 	</div>
 	</div>
 	<div class="label_main">
        <div class="label_hang" id="dept">
            <div class="label_ltit">申请部门:</div>
            <div class="label_rwben2">
            	<span class="label_rwb">
				<s:textfield title="部门名称" id="userdept_nameid" name="entert.apply_dept_name" readonly="true" />
				<s:hidden title="部门代码" id="userdept_codeid" name="entert.apply_dept" readonly="true" />
				</span>
				<span class="lb nw">
				<img class="detail vatop" src='<s:url value="/images/open2.gif" />' onclick="selectDept('userdept_codeid','userdept_nameid',true);" />
				<s:checkbox id="apply_is_sub_dept" name="entert.is_sub_dept" />
				<label for="apply_is_sub_dept"></label>包含子部门<span id="ajax_member_message"></span>
				</span>
            </div>
        </div>
        
        <div class="label_hang" id="product">
            <div class="label_ltit">产品名称:</div>
            <div class="label_rwben2">
            	<span class="label_rwb">
				<s:textfield name="entert.ptitle"/>
				</span>
            </div>
        </div>
        
        <div class="label_hang">
            <div class="label_ltit">申请日期:</div>
            <div class="label_rwben2">
            	<span class="label_rwb nw">
				<input  class="datepicker iI iI-f" type="text" name="entert.plan_start_begin" title="从" value="${it:formatDate(entert.plan_start_begin,'yyyy-MM-dd')}" />
				</span>
				<span class="label_rwb nw">
				<input  class="datepicker iI iI-t" type="text" name="entert.plan_start_end" title="到" value="${it:formatDate(entert.plan_start_end,'yyyy-MM-dd')}" />
            	</span>
            </div>
        </div>
        
        <div class="label_hang label_button tac">
        	<s:checkbox id="search_mcondition" name="search_mcondition" fieldValue="true" value="true" cssClass="regular-checkbox" />
			<label for="search_mcondition"></label>更多条件
            <s:submit value="搜索" /> <s:reset value="重置" />
        </div>
    </div>
 	</div>
 	</s:form>
		<div class="tab_warp">
 		<table>
 		<tr  id="a1">
 		<th class="td1">部门</th>
		<th class="td2">产品</th>
		<th class="td1">规格</th>
		<th class="td1">瓶数</th>
		<th class="td1">件数</th>
		<th class="td0">查看</th>
	  	</tr>
	  	<tr  id="a2">
 		<th class="td1">部门</th>
		<th class="td1">瓶数</th>
		<th class="td0">查看</th>
	  	</tr>
	  	<tr id="a3">
		<th class="td2">产品</th>
		<th class="td1">规格</th>
		<th class="td1">瓶数</th>
		<th class="td1">件数</th>
		<th class="td0">查看</th>
	  	</tr>
	  	<s:iterator value="entertProducts" status="sta">
	  	<tr id="showtr${uuid}">
		   <s:if test="%{apply_dept_name!=null}"> <td class="td1 nw"><a href="javascript:;" onclick="openCustomerView(${apply_dept});">${apply_dept_name}</a></td></s:if>
			<s:if test="%{product_name!=null}"><td class="td2 nw">${product_name}</td></s:if>
			<s:if test="%{spec!=null}"><td class="td2 nw">${spec}</td></s:if>
			<td class="td1 nw">${proNum}</td>
			<s:if test="%{case_spec!=null}"><td class="td1 nw">${proNum/case_spec }件</td></s:if>
		    <td class="td0 op-area"><a onclick="showDetail('showtr${uuid}');" href="javascript:;" class="input-nostyle">查看</a></td>
	  	</tr>
	  	</s:iterator>
 		</table>
 	</div>
 	<div id="listpage" class="pagination"></div>
</div>
</div>
<s:action name="ref_foot" namespace="/manager" executeResult="true" />
<script type="text/javascript" src="<s:url value="/js/jqueryPlugins/select3/jquery.cityselect.js" />"></script>
<script type="text/javascript">
$(function(){
	$("#mmtype").citySelect({
		url:'<s:url value="/js/jqueryPlugins/select3/mm.js" />',
		prov:"${assets.typea}",
		city:"${assets.typeb}",
		dist:" ${assets.typec}",
		nodata:"none",
		required:false
	});
	printPagination("listpage",'${currPage}','${recCount}','${pageSize}');
	typea();
	createCustomerView();
 });
 
 function typea(){
	var p1=$("#report_type").val();
	 if(p1==0){
		 $("#product").hide();
		 $("#dept").show();
		 $("#a1").show();
		 $("#a2").hide();
		 $("#a3").hide();
	 }else if(p1==1){
		 $("#product").hide();
		 $("#dept").show();
		 $("#a2").show();
		 $("#a1").hide();
		 $("#a3").hide();
	 }else if (p1==2){
		 $("#product").show();
		 $("#dept").hide();
		 $("#a3").show();
		 $("#a1").hide();
		 $("#a2").hide();
	 }
 }
 
function type(){
	$('#report_type').change(function(){ 
	 var p1=$(this).children('option:selected').val();//这就是selected的值 
	 document.getElementById("serachForm").action="/qkjmanage/entert_report";
	 document.getElementById("serachForm").submit();
	});
}

var sobj02;
var createCustomerView = function() {
	//http://localhost:8888/qkjmanage/customer_load?viewFlag=mdy&customer.uuid=3
	var w_width = $(window).width();
	var w_height = $(window).height();
	sobj02 = new DialogIFrame({
		src:'',
		title:"查看",
		width:w_width*0.65,
		height:w_height*0.65
	});
	sobj02.selfAction = function(val1,val2) {};
	sobj02.create();
	//sobj02.open();
};

var openCustomerView = function(customer_uuid) {
	var iframeId = sobj02.getConid() + "iframe";
	$("#"+iframeId).attr("src","/qkjmanage/entert_reportbyd?entert.apply_dept=" + customer_uuid);
	sobj02.open();
};
</script>
</body>
</html>