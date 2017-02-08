<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:url value="/css/v0.1/style.css" />" />
<script type="text/javascript"
	src="<c:url value="/js/v0.1/jquery-1.9.0.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/v0.1/jquery-migrate-1.2.1.min.js" />"></script>
<style type="text/css">
.alert {
  background: #f2f2f2;
  border: none;
  border: 1px solid gray;
  margin: 0 0 20px 0;
  padding: 10px 35px 10px 20px;
  position: relative; }

.alert strong {
  font-weight: 600; }

.alert.successed {
  background: #B8F2BC;
  border-color: #90CD90;
  color: #3F963E; }

  .checked{width: 100%}
  .inp{width:15%;}
  
  .label_hang_linput {
		width: 60% !important;
	}
.lb_jpin td{
text-align: left !important;
border-bottom-style:none
}

fieldset{
    border: 0;
}

</style>
<title>用户列表</title>
</head>
<body>
	<div class="tab_right">
		<div class="tab_warp main">
			<div class="dq_step"></div>
			<div class="checkScore">
				<div class="label_main">
						<table id="oneTable"  width="100%" cellpadding="0" cellspacing="0" border="0"
							class="lb_jpin">
							<s:iterator value="sonScores" status="sta">
								<tr>
									<td>${(sta.index+1)}、${title}</td>
								</tr>
								<s:if test="detail!=null">
								<tr>
									<td>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${detail}
									</td>
								</tr>
								</s:if>
								<tr id="showtr${uuid}">
									<td>
									<span class="ship_hidden_info" style="display:none;">
									<span id="index_id${sta.index+1}">${index_id}</span>
									<span id="ititle${sta.index+1}">${ititle}</span>
									<span id="uuid${sta.index+1}">${uuid}</span>
									</span>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;目前熟练度：
									<input type="radio" name="score${sta.index }" value="1" 
									<s:if test="%{check_score==1}"> checked="checked" </s:if>
									>1
									<input type="radio" name="score${sta.index }" value="2"
									<s:if test="%{check_score==2}"> checked="checked" </s:if>
									>2
									<input type="radio" name="score${sta.index }" value="3"
									<s:if test="%{check_score==3}"> checked="checked" </s:if>
									>3
									<input type="radio" name="score${sta.index }" value="4"
									<s:if test="%{check_score==4}"> checked="checked" </s:if>
									>4
									<input type="radio" name="score${sta.index }" value="5"
									<s:if test="%{check_score==5}"> checked="checked" </s:if>
									>5
									<input type="radio" name="score${sta.index }" value="0"
									<s:if test="%{check_score==0}"> checked="checked" </s:if>
									>N
									<input type="hidden" name="raname" value="${sta.index }">
									<input type="hidden" name="kpiid" value="${uuid }">
									<hr style=" height:2px;border:none;border-top:2px dotted #185598;" />
									</td>
									
								</tr>
							</s:iterator>
						</table>
					</div>
				
				
				<fieldset class="clear">
					<legend>自由回答</legend>
					<!--<span class="shaddress">--------------------收货地址--------------------</span>-->
					<div class="label_main">
						<table width="100%" cellpadding="0" cellspacing="0" border="0"
							class="lb_jpin">
							<s:iterator value="sonremarks" status="sta">
								<tr>
									<td>${title}
									<s:hidden name="remark_uuid" value="%{uuid}"></s:hidden>
									</td>
									<td title="${detail}">${it:subString(detail,18)}</td>
									
								</tr>
								<tr>
								<td>
									<s:textarea name="ic_remark" cssClass="label_hang_linput inputNote validate[maxSize[65535]]" value="%{remark }"/>
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</fieldset>
				
				
			</div>
			<div id="contact" class="checked"></div>
		</div>
	</div>


	<script type="text/javascript" src="<s:url value="/js/div.js" />"></script>
	<script type="text/javascript"
		src="<s:url value="/js/optiontransferselect.js" />"></script>
</body>
</html>