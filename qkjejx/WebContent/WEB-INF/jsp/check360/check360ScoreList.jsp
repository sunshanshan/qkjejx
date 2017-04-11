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
		</div>
		<s:form id="serachForm" name="serachForm" action="check_360ScoreList" method="get" namespace="/check360" theme="simple">
			<div class="label_con">
				<div class="label_main">
					<div class='label_hang'>
						<div class='label_ltit'>被考核人:</div>
						<div class='label_rwben'>
							<s:textfield name='score.user_name'  />
						</div>
					</div>
					<div class="label_hang">
						<div class="label_ltit">考核活动主题:</div>
						<div class="label_rwben">
						<s:textfield name='score.title'/>
						</div>
					</div>
							
					<div class="label_hang tac">
						<s:checkbox id="search_mcondition" name="search_mcondition" fieldValue="true" value="true" cssClass="regular-checkbox" />
						<label for="search_mcondition"></label>更多条件
						<s:submit value="搜索" />
						<s:reset value="重置" />
					</div>
				</div>
			</div>
		</s:form>
		<div class="tab_warp">
			<table>
				<tr id="coltr">
					<th class="td1">被考核人</th>
					<th class="td1">活动主题</th>
					<th class="td1">总分</th>
					<th class="td4">操作</th>
					<th class="td0">查看</th>
				</tr>
				<s:iterator value="scores" status="sta">
					<tr id="showtr${user_id}">
						<td class="td1 nw">${user_name}</td>
						<td class="td1 nw">${title}</td>
						<td class="td1 nw">${sumscore}</td>
						<td class="td4 op-area">
								<a class="input-blue" href="<s:url namespace="/check360" action="check_360ScoreView"><s:param name="score.user_id" value="user_id"></s:param><s:param name="score.check_ym" value="check_ym"></s:param></s:url>">详情</a>
								<a class="input-blue" href="<s:url namespace="/check360" action="check_loadDetail"><s:param name="score.user_id" value="user_id"></s:param><s:param name="score.check_ym" value="check_ym"></s:param></s:url>">详细报告</a>		
								<%-- <a class="input-blue" href="/collect/detailView.jsp?score.user_id=${user_id }&score.check_ym=${check_ym}">详细报告2</a> --%>
								<a class="input-blue" href="<s:url namespace="/check360" action="check_360loadAbst"><s:param name="score.user_id" value="user_id"></s:param><s:param name="score.check_ym" value="check_ym"></s:param></s:url>">摘要报告</a>
						</td>
						<td class="td0 op-area"><a href="javascript:;" onClick="showDetail('showtr${uuid}');" class="input-nostyle">查看</a></td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<div id="listpage" class="pagination"></div>
	</div>
</div>

<s:action name="ref_foot" namespace="/manager" executeResult="true" />
<script type="text/javascript" src="<s:url value="/js/DatePicker.js" />"></script>
<script type="text/javascript">
$(function(){
	printPagination("listpage",'${currPage}','${recCount}','${pageSize}');
 });
 
$(function(){
    function sYear(){
        var d = new Date();
        var vYear = d.getFullYear();
        for(var i=0;i<=5;i++){
            var html='<option value="'+(vYear+i)+'">'+(vYear+i)+'</option>';
            $(".ss").append(html);
        }
    }
    sYear();
})
 
function add_user(uuid){
	 $.ajax({
	     type:'POST',
	     url: '/check360/send_email',
	     data: "params="+uuid,
	     success: function(data){
	    	 if(data=="0"){
	    		 alert("成功.");
	 		}else if(data=="2"){
	 			 alert("没有考核人.");
	 		}   	 
	    	 else {
	 			alert("失败");
	 		}
	    }			    
	  });
}
</script>
</body>
</html>