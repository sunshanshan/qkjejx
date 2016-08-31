<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户权限列表--<s:text name="APP_NAME" /></title>
<s:action name="ref_head" namespace="/manager" executeResult="true" />
<link rel="StyleSheet" href="<s:url value="/include/dtree/dtree.css" />" />
<style type="text/css">
.label_main{font-size:14px;}
.label_ltit{width:110px;}
.label_hang_linput {
	width: 760px !important;
}
@media screen and (max-width:1180px) {
	.label_hang_linput {
		width: 500px !important;
	}
}
@media screen and (max-width:820px) {
	.label_hang_linput {
		width: 300px !important;
	}
}
@media screen and (max-width:620px) {
	.label_hang_linput {
		width: 150px !important;
	}
}
</style>
</head>
<body>
<s:action name="nav" namespace="/manage" executeResult="true" />
<div class="tab_right">
	<div class="main">
		<div class="dq_step"><a href="/manager/default">首页</a>&nbsp;&gt;&nbsp;部门</div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="200" style="background-color: #F0F0F0;" valign="top">
					<div id="dtree">
						<!--<p><a href="javascript: d.openAll();">open all</a> | <a href="javascript: d.closeAll();">close all</a></p>-->
					</div>
				</td>
				<td width="10"></td>
				<td valign="top">
					<s:form name="form1" cssClass="validForm" action="dept_add" namespace="/sys" onsubmit="return validator(this);" method="post" theme="simple">
						<div class="label_main">
							<input type="button" value="新增部门" onClick="setControl('add');" />
							<input type="button" value="新增kpi" onClick="setkpi();" />
						</div>	
						<div class="label_main">
							<div class="label_hang">
								<div class="label_ltit">部门ID:</div>
								<div class="label_rwbenx"><span id="dept.uuid_text"></span><s:hidden id="dept.uuid" name="dept.uuid" value="dept.uuid" /></div>
							</div>
							<div class="label_hang clear">
								<div class="label_ltit">部门代码:</div>
								<div class="label_rwbenx"><s:textfield id="dept.dept_code" name="dept.dept_code" cssClass="validate[required]" /></div>
							</div>
							<div class="label_hang">
								<div class="label_ltit">父部门:</div>
								<div class="label_rwbenx"><s:select title="父部门" 	id="dept.parent_dept" name="dept.parent_dept" list="depts" listKey="dept_code" listValue="dept_cname"	headerKey="0"	headerValue="根节点" cssClass="validate[required]" /></div>
							</div>
							<div class="label_hang clear">
								<div class="label_ltit">部门名称:</div>
								<div class="label_rwbenx"><s:textfield id="dept.dept_cname" name="dept.dept_cname" cssClass="validate[required]"  /></div>
							</div>
							<div class="label_hang">
								<div class="label_ltit">部门名称(英文):</div>
								<div class="label_rwbenx"><s:textfield id="dept.dept_ename" name="dept.dept_ename" /></div>
							</div>
							<div class="label_hang">
								<div class="label_ltit">部门类型:</div>
								<div class="label_rwbenx">
								<s:select id="dept.type" name="dept.type" cssClass="selectKick" list="#{0:'部门',1:'班组',2:'组'}" />
								</div>
							</div>
							<div class="label_hang clear">
								<div class="label_ltit">描述:</div>
								<div class="label_rwbenx"><s:textfield id="dept.descriptions" name="dept.descriptions" cssClass="label_hang_linput"/></div>
							</div>
					
							<div class="tab_warp">
				       <table  id="intop">
		<tr>
		<th class="td1" >部门名称</th  >
		<th class="td2" >kpi</th  >
		 <th class="td3" >周期</th  >
		<th class="td4" >权重</th  >
		<th class="td5" >计分方式</th  >
		<th class="td6" >指标定义</th >
		<th class="td7" >指标标准</th >
		 <th class="td8" >部门 </th  >
		 <th class="td9" >横向考核部门 </th  >
		 <th class="td10" >横向考核岗位</th  >
		 <th class="td11" >取部门得分</th  >
		  <th class="td12" >kpi类型</th  >
		  <th class="td13" >操作</th  >
		</tr>
		</table>
							<span id="messagewei"><s:property value="messagewei" /></span>
									</div>
									
							<div class="label_hang clear">
								<div class="label_ltit">修改人:</div>
								<div class="label_rwbenx"><span id="dept.lm_user"></span></div>
							</div>
							<div class="label_hang">
								<div class="label_ltit">修改时间:</div>
								<div class="label_rwbenx"><span id="dept.lm_time"></span></div>
							</div>
							<div class="label_hang clear">
								<div class="label_ltit">相关操作:</div>
								<div class="label_rwbenx">
									<span id="label_add"><s:submit id="add" name="add" value="新增" action="dept_add" cssClass="input-blue"/></span>
									<span id="label_save" style="display: none;"><s:submit id="save" name="save" value="保存" action="dept_save" cssClass="input-blue"/></span>
									<span id="label_del" style="display: none;"><s:submit id="delete" name="delete" value="删除" action="dept_del" onclick="return isDel();" cssClass="input-red"/></span>
									&nbsp;<span id="message"><s:property value="message" /></span>
									<span id="message2"></span>
								</div>
							</div>
						</div>
					</s:form>
				</td>
			</tr>
		</table>
	</div>
</div>
<s:action name="ref_foot" namespace="/manager" executeResult="true" />
<script type="text/javascript" src="<s:url value="/include/dtree/dtree.js" />"></script>
<script type="text/javascript">
	var divID =document.getElementById("dtree");  
	d = new dTree('d');
	d.config.isAddNoRootNode = true;
	d.icon = {
			root		: '<s:url value="/include/dtree/" />'+'img/globe.gif',
			folder		: '<s:url value="/include/dtree/" />'+'img/folder.gif',
			folderOpen	: '<s:url value="/include/dtree/" />'+'img/folderopen.gif',
			node		: '<s:url value="/include/dtree/" />'+'img/page.gif',
			empty		: '<s:url value="/include/dtree/" />'+'img/empty.gif',
			line		: '<s:url value="/include/dtree/" />'+'img/line.gif',
			join		: '<s:url value="/include/dtree/" />'+'img/join.gif',
			joinBottom	: '<s:url value="/include/dtree/" />'+'img/joinbottom.gif',
			plus		: '<s:url value="/include/dtree/" />'+'img/plus.gif',
			plusBottom	: '<s:url value="/include/dtree/" />'+'img/plusbottom.gif',
			minus		: '<s:url value="/include/dtree/" />'+'img/minus.gif',
			minusBottom	: '<s:url value="/include/dtree/" />'+'img/minusbottom.gif',
			nlPlus		: '<s:url value="/include/dtree/" />'+'img/nolines_plus.gif',
			nlMinus		: '<s:url value="/include/dtree/" />'+'img/nolines_minus.gif'
	};		
	d.add('0','-1','部门列表');
	<s:iterator value="depts">
	d.add('<s:property value="dept_code" />','<s:property value="parent_dept" />','<s:property value="dept_cname" />',"javascript:getInfo('<s:property value="uuid" />')");
	</s:iterator>
	d.add();
	divID.innerHTML = d;
</script>
<script type="text/javascript">
var puuid = '<s:property value="dept.uuid" />';
var cflag = 0;//状态符,0代表新增状态 1代表修改状态

$(function(){
	if(puuid=='') {
		cflag = 1;
		setControl('add');
		cflag = 0;
	} else {
		getInfo(puuid);
	}	
});
function getInfo(obj) {

	var ajax = new Common_Ajax('message');
	ajax.config.action_url = '<s:url value="/common_ajax/json_ajax" />';
	ajax.config._success = function(data, textStatus) {
		var json = data[0];
		$("#dept\\.uuid_text").text(json.uuid);
		$("#dept\\.uuid").val(json.uuid);
		$("#dept\\.dept_code").val(json.dept_code);		
		$("#dept\\.parent_dept").val(json.parent_dept);
		$("#dept\\.dept_cname").val(json.dept_cname);
		$("#dept\\.dept_ename").val(json.dept_ename);		
		$("#dept\\.descriptions").val(json.descriptions);
		$("#dept\\.lm_user").text(json.lm_user);
		$("#dept\\.lm_time").text(formatDate(json.lm_time));
		
		$("#dept\\.type").val(json.type);
		setControl("save");
		$("#message").text("获取数据成功!");
		var dept_code=$("#dept\\.dept_code").val();
		getIndexDetail(dept_code);
	};
	ajax.addParameter("privilege_id","SYS_MANAGER_DEPT_AJAX_LOAD");
	ajax.addParameter("parameters","uuid="+obj);
	ajax.sendAjax2();

}

function getIndexDetail(obj) {
	var ajax = new Common_Ajax('message');
	ajax.config.action_url = '<s:url value="/common_ajax/json_ajax" />';
	ajax.config._success = function(data, textStatus) {
		var arr=data;
		var show = new Array(); 
		var dept_name=$("#dept\\.dept_cname").val();
		var wei=0;
		$("[name='deletetd']").remove();
		for (var i in arr) {  
			 show.push('<tr name="deletetd" id="'+arr[i].uuid+'tr">');
			 show.push('<td class="td1"  >'+dept_name+'</td  >' ) ;
			 show.push('<td class="td2"  id="'+arr[i].uuid+'kpi">'+ arr[i].kpi+'</td  >' ) ;
			 show.push('<td class="td3"  id="'+arr[i].uuid+'cyc">'+ arr[i].cyc+'</td  >' ) ;
			 show.push('<td class="td4"  id="'+arr[i].uuid+'weight">'+ arr[i].weight+'</td  >' ) ;
			 show.push('<td class="td5" id="'+arr[i].uuid+'count_way">'+ arr[i].count_way+'</td  >' ) ;
			 show.push('<td class="td6"  id="'+arr[i].uuid+'definition">'+ arr[i].definition+'</td  >' ) ;
			 show.push('<td class="td7" id="'+arr[i].uuid+'correctly">'+ arr[i].correctly+'</td  >' ) ;
			 show.push('<td class="td8" id="'+arr[i].uuid+'position_dept">'+ arr[i].position_dept+'</td  >' ) ;
			 show.push('<td class="td5" id="'+arr[i].uuid+'check_deptcode">'+ arr[i].check_deptcode+'</td  >' ) ;
			 show.push('<td class="td6" id="'+arr[i].uuid+'check_post">'+ arr[i].check_post+'</td  >' ) ;
			 show.push('<td class="td7" id="'+arr[i].uuid+'isdept">'+ arr[i].isdept+'</td  >' ) ;
			 var types=null;
			 
			 if(arr[i].type==1){
				 types="职务权重"
			 }else if(arr[i].type==2){
				 types="部门权重"
			 }else if(arr[i].type==3){
				 types="班组权重"
			 }
			 show.push('<td class="td9" id="'+arr[i].uuid+'type">'+types+' <input type="hidden" value="'+arr[i].type+'"   id="'+arr[i].uuid+'types"></input></td  >' ) ;
			 show.push(' <td class="td1 op-area"><a  id="'+arr[i].uuid+'buttb" onclick="javascript:updatetab('+arr[i].uuid+')" href="javascript:void(0)" class="input-red">修改</a><a style="display: none" id="'+arr[i].uuid+'buttd" onclick="javascript:updatedetermine('+arr[i].uuid+')" href="javascript:void(0)" class="input-greed">保存</a> <input id="'+arr[i].uuid+'delete" type="button" value="删除"  onclick="delkpi('+arr[i].uuid+');" class="input-red"/></td>') ;
			 show.push('</tr>');
			 wei= Number((arr[i].weight+wei).toFixed(3))

			}  
		$("#messagewei").text('权重总和:'+wei);
		$("#messagewei").css("color","red");
		$("#messagewei").css("color","red");
		 $("#intop").append(show.join(""));
		 
	
	};
	ajax.addParameter("privilege_id", "SYS_MANAGER_DEPT_LOADKPI");
	ajax.addParameter("parameters", "dept_code=" + obj);
	ajax.sendAjax2();
}

function delkpi(obj){
	
	var dept_uuid=$("#dept\\.uuid").val();
	 if(confirm("确认删除？")){
	  
	  
	var ajax = new Common_Ajax('message');
	ajax.config.action_url = '<s:url value="/common_ajax/json_ajax" />';
	ajax.config._success = function(data, textStatus) {

		getInfo(dept_uuid);
		
	};
	ajax.addParameter("privilege_id", "SYS_MANAGER_DEPT_DELETEKPI");
	ajax.addParameter("work","update");
	ajax.addParameter("parameters", "uuid=" + obj);
	ajax.sendAjax2();
	
	 }
	
	
}
function updatedetermine(obj){
	
	var ajax = new Common_Ajax('message');
	var dept_uuid=$("#dept\\.uuid").val();
	var kpi=$("#"+obj+"kpiip").val();
	var cyc=$("#"+obj+"cycip").val();
	 var weight=$("#"+obj+"weightip").val();
	 var count_way=$("#"+obj+"count_wayip").val();
	 var definition=$("#"+obj+"definitionip").val();
	 var correctly=$("#"+obj+"correctlyip").val();
	 var check_deptcode=$("#"+obj+"check_deptcodeip").val();
	 var check_post=$("#"+obj+"check_postip").val();
	 var isdept=$("#"+obj+"isdeptip").val();
	 var type=$("#"+obj+"typeip").val();
	 var position_dept=$("#"+obj+"position_deptip").val();
	ajax.config.action_url = '<s:url value="/common_ajax/json_ajax" />';
	ajax.config._success = function(data, textStatus) {
		getInfo(dept_uuid);
	};
	ajax.addParameter("privilege_id", "SYS_MANAGER_DEPT_UPDATEKPI");
	ajax.addParameter("work","update");
	ajax.addParameter("parameters", "dept_code=" + obj+"&kpi="+encodeURI(kpi)+"&weight="+weight+"&count_way="+encodeURI(count_way)
			+"&definition="+encodeURI(definition)+"&correctly="+encodeURI(correctly)+"&check_deptcode="+encodeURI(check_deptcode)+"&check_post="+encodeURI(check_post)
			+"&isdept="+encodeURI(isdept)+"&type="+encodeURI(type)+"&cyc="+encodeURI(cyc)+"&position_dept="+encodeURI(position_dept));
	ajax.sendAjax2();
}
function updatetab(obj) {

	var show = new Array(); 
	var kpi=$("#"+obj+"kpi").text();
	 show.push('<input type="text" value="'+kpi+'" id="'+obj+'kpiip" />');
	 $("#"+obj+"kpi").text("");
	 $("#"+obj+"kpi").append(show.join(""));
	 show=[];
	 var weight=$("#"+obj+"weight").text();
	 show.push('<input type="text" value="'+weight+'" id="'+obj+'weightip" />');
	 $("#"+obj+"weight").text("");
	 $("#"+obj+"weight").append(show.join(""));
	 show=[];
	 var count_way=$("#"+obj+"count_way").text();
	 show.push('<input type="text" style="width:80px" value="'+count_way+'" id="'+obj+'count_wayip" />');
	 $("#"+obj+"count_way").text("");
	 $("#"+obj+"count_way").append(show.join(""));
	 show=[];
	 var definition=$("#"+obj+"definition").text();
	 show.push('<input type="text" style="width:80px" value="'+definition+'" id="'+obj+'definitionip" />');
	 $("#"+obj+"definition").text("");
	 $("#"+obj+"definition").append(show.join(""));
	 show=[];
	 var correctly=$("#"+obj+"correctly").text();
	 show.push('<input type="text" style="width:80px" value="'+correctly+'" id="'+obj+'correctlyip" />');
	 $("#"+obj+"correctly").text("");
	 $("#"+obj+"correctly").append(show.join(""));
	 show=[];
	 var check_deptcode=$("#"+obj+"check_deptcode").text();
	 show.push('<input type="text" style="width:80px" value="'+check_deptcode+'" id="'+obj+'check_deptcodeip" />');
	 $("#"+obj+"check_deptcode").text("");
	 $("#"+obj+"check_deptcode").append(show.join(""));
	 show=[];
	 var check_post=$("#"+obj+"check_post").text();
	 show.push('<input type="text" style="width:80px" value="'+check_post+'" id="'+obj+'check_postip" />');
	 $("#"+obj+"check_post").text("");
	 $("#"+obj+"check_post").append(show.join(""));
	 show=[];
	 var isdept=$("#"+obj+"isdept").text();
	 show.push('<input type="text" style="width:80px" value="'+isdept+'" id="'+obj+'isdeptip" />');
	 $("#"+obj+"isdept").text("");
	 $("#"+obj+"isdept").append(show.join(""));
	 show=[];
	 var type=$("#"+obj+"types").val();
	 show.push('<select id="'+obj+'typeip" value="'+type+'"><option value ="1" >职务权重</option> <option value ="2">部门权重</option><option value ="3">班组权重</option>     </select>  ');
	 $("#"+obj+"type").text("");
	 $("#"+obj+"type").append(show.join(""));
	 show=[];
		var cyc=$("#"+obj+"cyc").text();
	
		 show.push('<input type="text" value="'+cyc+'" id="'+obj+'cycip" />');
		 $("#"+obj+"cyc").text("");
		 $("#"+obj+"cyc").append(show.join(""));
		 show=[];
		 
		 var position_dept=$("#"+obj+"position_dept").text();

		 show.push('<input type="text" style="width:80px" value="'+position_dept+'" id="'+obj+'position_deptip" />');
		 $("#"+obj+"position_dept").text("");
		 $("#"+obj+"position_dept").append(show.join(""));
		 show=[];
	 $("#"+obj+"buttd").show()
	  $("#"+obj+"buttb").hide()
	  
	  
		 if(type==1){
			 $("#"+obj+"typeip").find("option[value='1']").attr("selected",true);
			 }
			 if(type==2){
				 $("#"+obj+"typeip").find("option[value='2']").attr("selected",true);
				 }
			 if(type==3){
				 $("#"+obj+"typeip").find("option[value='3']").attr("selected",true);
				 }
}

function setkpi() {
	var dept_name=$("#dept\\.dept_cname").val();
	var show = new Array(); 
	 show.push('<tr name="deletetd" ·>');
	 show.push('<td class="td1"  >'+dept_name+'</td  >' ) ;
	 show.push('<td class="td2" ><input type="text" value="" id="newkpiip" /></td  >' ) ;
	 show.push('<td class="td5"><input type="text" value="" id="newcycip" /></td  >' ) ;
	 show.push('<td class="td3" ><input type="text" value="" id="newweightip" /></td  >' ) ;
	 show.push('<td class="td4" ><input type="text" style="width:80px" value="" id="newcount_wayip" /></td  >' ) ;
	 show.push('<td class="td6"  ><input type="text" style="width:80px" value="" id="newdefinitionip" /></td  >' ) ;
	 show.push('<td class="td7"><input type="text" style="width:80px" value="" id="newcorrectlyip" /></td  >' ) ;
	 show.push('<td class="td7"><input type="text" style="width:80px" value="" id="newposition_deptip" /></td  >' ) ;
	 show.push('<td class="td5"><input type="text" style="width:80px" value="" id="newcheck_deptcodeip" /></td  >' ) ;
	 show.push('<td class="td6" ><input type="text" style="width:80px" value="" id="newcheck_postip" /></td  >' ) ;
	 show.push('<td class="td7" ><input type="text" style="width:80px" value="" id="newisdeptip" /></td  >' ) ;
	 show.push('<td class="td6"  ><select  id="newtypeip"><option value ="1">职务权重</option> <option value ="2">部门权重</option> <option value ="3">班组权重</option>   </select>  ' ) ;
	 show.push(' <td class="td1 op-area"><a   onclick="javascript:addtab()" href="javascript:void(0)" class="input-greed">提交</a></td>') ;
	 show.push('</tr>');
	 $("#intop").append(show.join(""));
	
}
function addtab(ct) {
	var ajax = new Common_Ajax('message');
	var kpi=$("#newkpiip").val();
	var weight=$("#newweightip").val();
	var count_way=$("#newcount_wayip").val();
	var definition=$("#newdefinitionip").val();
	var correctly=$("#newcorrectlyip").val();
	var check_deptcode=$("#newcheck_deptcodeip").val();
	var check_post=$("#newcheck_postip").val();
	var cyc=$("#newcycip").val();
	var position_dept=$("#newposition_deptip").val();
	var isdept=$("#newisdeptip").val();
	var dept_code=$("#dept\\.dept_code").val();
	var dept_uuid=$("#dept\\.uuid").val();
	var type=$("#newtypeip").val();
	ajax.config.action_url = '<s:url value="/common_ajax/json_ajax" />';
	ajax.config._success = function(data, textStatus) {
	
		getInfo(dept_uuid)
	};
	ajax.addParameter("privilege_id", "SYS_MANAGER_DEPT_ADDKPI");
	ajax.addParameter("work","update");
	ajax.addParameter("parameters", "dept_code=" + dept_code+"&kpi="+encodeURI(kpi)+"&weight="+weight+"&count_way="+encodeURI(count_way)
			+"&definition="+encodeURI(definition)+"&correctly="+encodeURI(correctly)+"&check_deptcode="+encodeURI(check_deptcode)+"&check_post="+encodeURI(check_post)
			+"&isdept="+encodeURI(isdept)+"&type="+encodeURI(type)+"&cyc="+encodeURI(cyc)+"&position_dept="+encodeURI(position_dept));
	ajax.sendAjax2();
	 
	
}
function setControl(ct) {
	if("save"==ct) {
		if(cflag == 0) {
			$('#label_add').hide();
			$('#label_save').show();
			$('#label_del').show();
			$('#dept\\.dept_code').attr("readonly","true");
			cflag = 1;
		}		
	} else if("add"==ct) {
		if(cflag == 1) {
			$('#label_add').show();
			$('#label_save').hide();
			$('#label_del').hide();
			
			$('#message').empty();

			var new_parent_id = $('#dept\\.dept_code').val();
			form1.reset();
			
			$("#dept\\.lm_user").empty();
			$("#dept\\.lm_time").empty();
			$('#dept\\.parent_dept').val(new_parent_id);
			$('#dept\\.dept_code').removeAttr("readonly");
			cflag = 0;
		}
	}
}
function view(obj) {
	var str = "";
	for(var i in obj) {
		str += i+"\t";
	}
	$('message').innerHTML = str;
}
function viewx(obj) {
	alert(obj);
	$('message').innerHTML = obj;
}
</script>
</body>
</html>
