<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:text name="APP_NAME" /></title>
<s:action name="ref_head" namespace="/manager" executeResult="true" />
<style type="text/css">
.print_prepare {width: 0;height: 0;position: absolute;left: -9999px;top: -9999px;}
hr{border:7px solid #CF2E2A;width: 100%}
.imgb{float: right;width: 40%}
.imgt{width:100%;}
.height{height: 30%;}
.f{margin-right: 20%;}

@media screen and (max-width:960px) {
.imgt{width:100%;height: 20%}
.f{ margin-left: auto; margin-right: auto;}
}
</style>
</head>
<body>
<div>
<img  class="imgt" src="<s:url value="/images/title.jpg" />" />
</div>
<div class="height" style="background:url('<s:url value="/images/titleback.jpg" />') center no-repeat;" >
	<div class="main lg_main f">
		<div class="lg_title">绩效系统登录</div>
		<div class="lg_input">
			<s:form id="mainForm" cssClass="validForm" name="mainForm" action="check_login" namespace="/manager" method="post" theme="simple">
	        <div class="label_singlecol">
	        
	            <div class="label_singletitle">用户名:</div>
	            <div class="label_singlecon"><s:textfield title="用户名" name="user.title" cssClass="validate[required]" nullable="false" /></div>
	        </div>
	        <div class="label_singlecol">
	            <div class="label_singletitle">密&nbsp;&nbsp;&nbsp;&nbsp;码:</div>
	            <div class="label_singlecon"><s:password title="密码" name="user.passwords" cssClass="validate[required]" nullable="false" /></div>
	        </div>
	        <div class="label_singlecol label_s2">
	        	<s:submit value="登 录" />
	        </div>
	        </s:form>
		</div>
	</div>
</div>
<div>
<img class="imgb" src="<s:url value="/images/title.png" />" />
<hr/>
</div>
<!-- 提前加载图片 -->
<div>
<div class="print_prepare"><img src="<s:url value="/images/print/pageheader02.png" />" alt="" /></div>
<div class="print_prepare"><img src="<s:url value="/images/print/pageheader02.png" />" alt="" /></div>
</div>
<s:action name="ref_foot" namespace="/manager" executeResult="true" />
<%-- <script type="text/javascript">
$(function () {
    //判断是否宽屏
    var winWide = window.screen.width;
    alert(winWide);
    var wideScreen = false;
    if (winWide <= 1024) {//1024及以下分辨率
        $("#css").attr("href", "Styles/Style1.css");
        alert('小屏');
    } else {
        $("#css").attr("href", "Styles/Style.css");
        alert('大屏');
        wideScreen = true; //是宽屏
    }
})
</script> --%>
</body>
</html>