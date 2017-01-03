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
<style type="text/css">
#win {
	/*窗口的高度和宽度*/
	width: 300px; height: 200px;
	/*窗口的位置*/ position: absolute; top: 100px; left: 350px;
	/*开始时窗口不可见*/ display: none;
}
/*控制背景色的样式*/
#title {
	/*控制标题栏的左内边距*/
	padding-left: 3px;
}

#cotent {
	padding-left: 3px; padding-top: 5px;
}
/*控制关闭按钮的位置*/
#close {
	margin-left: 188px;
	/*当鼠标移动到X上时，出现小手的效果*/ cursor: pointer;
}
s
</style>
</head>

<body>
	<s:action name="nav" namespace="/manage" executeResult="true" />
	
	<div class="tab_right">
		<div class="tab_warp main">
			<div class="dq_step">${path}
				<span class="opb lb op-area">
						<a href="<s:url action="check_360list" namespace="/check360"><s:param name="viewFlag">relist</s:param></s:url>">返回列表</a>
				</span>
			</div>
			<s:form id="editForm" name="editForm" cssClass="validForm" action="check_load360" namespace="/check360" method="post" theme="simple">
				<div class="label_con">
					<div class="label_main">
						<s:if test="'mdy' == viewFlag">
							<div class='label_hang'>
								<div class='label_ltit'>主键:</div>
								<div class='label_rwben'>${check.uuid}<s:hidden name="check.uuid" />
								</div>
							</div>
							<%-- <div class='label_hang'>
								<div class='label_ltit'>提交人:</div>
								<div class='label_rwben'>${check.add_username}</div>
							</div>
							<div class='label_hang'>
								<div class='label_ltit'>提交时间:</div>
								<div class='label_rwbenx'>${it:formatDate(check.add_time,'yyyy-MM-dd hh:mm:ss')}</div>
							</div> --%>
						</s:if>
						
						<div class="label_hang">
								<div class="label_ltit">考核年:</div>
								<div class="label_rwben2">
									<span class="label_rwb nw"> <%-- <input id="begintime" name="check.ym" type="text" onclick="setmonth(this)" readonly="readonly" value="${check.ym}"/> --%>
									<select class="ss" name="check.ym"">
    								</select>
									</span>
									
									
								</div>
							</div>
						<div class="label_hang">
								<div class="label_ltit">状态:</div>
								<div class="label_rwben label_rwb">
									<s:select  name="check.state" cssClass="selectKick" list="#{0:'打开',1:'关闭',2:'已审核'}" headerKey=""/>
								</div>
						</div>
					</div>
					
					<div class="label_main">
						<div class="label_hang">
							<div class="label_ltit">相关操作:</div>
							<div class="label_rwbenx">
								<font color="red"><span id="messages"></span></font>
								<s:if test="'add' == viewFlag">
										<s:submit id="add" name="add" value="填加" action="check_360add" cssClass="input-blue" />
								</s:if>
								<s:elseif test="'mdy' == viewFlag">
										<s:submit id="save" name="save" value="保存" action="check_360save" cssClass="input-blue" />
										<s:submit id="delete" name="delete" value="删除" action="check_360del" onclick="return isDel();" cssClass="input-red" />
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
</script>
<script type="text/javascript" src="<s:url value="/include/jQuery/jquery.ui.datepicker-zh.js" />"></script>
<script type="text/javascript" src="<s:url value="/js/DatePicker.js" />"></script>


<script type="text/javascript">
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
</script>


</html>