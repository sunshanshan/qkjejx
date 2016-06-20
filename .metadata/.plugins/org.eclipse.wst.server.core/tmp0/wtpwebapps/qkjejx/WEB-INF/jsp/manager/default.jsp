<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><s:text name="APP_NAME" /></title>
<s:action name="ref_head" namespace="/manager" executeResult="true" />
<style type="text/css">
.main_info {
	margin: auto; width: 98%; padding-top: 10px;
}

.main_info a {
	font-size: 14px; 
}

.main_info a:hover {
text-decoration: underline;
}

.ht_tabcon p {
line-height: 25px;
}

.ht_tabtit2 {
padding:10px 0;
margin:10px 0;
border-bottom: #999 dashed 1px; 
}

.update_info {
	border: #999 solid 1px; margin-bottom: 10px;
}

.update_title {
	margin: 1px; background-color: #999; line-height: 35px; height: 35px; font-size: 14px; font-weight: bold; cursor: pointer;
}

.update_info dl {
	margin: 10px 5px; border-bottom: #999 dashed 1px; border-top: #999 dashed 1px; padding: 0;
}

.update_info dt,.update_info dd {
	font-size: 14px;
}

.update_info dd {
	margin-left: 14px;
}

.last_update {
	color: red;
}

.sysnote ul {
	margin-left: 30px;
}

.sysnote {
	border: #999 solid 1px; margin-bottom: 10px; padding: 10px;
}

.sysnote .note_title {
	font-size: 18px; text-align: center; font-weight: bold; padding: 10px; border-bottom: #999 dashed 1px; margin-bottom: 10px;
}

.sysnote .note_content {
	font-size: 14px;
}

.active_info .update_content {
	display: block;
}

.update_content {
	display: none;
}
.ht_right{
font-size:14px;
float: right;
}
</style>
</head>
<body>
<s:action name="nav" namespace="/manage" executeResult="true" />
<div class="tab_right">
<div class="main_info">
	<div class="sysnote">
		<div class="note_title">系统公告</div>
		<p style="color: red;"><span id="pointx"></span><span id="pointy"></span></p>
		<div class="note_content">
			<p>新版系统上线，<font style="color: #FF0000;">基本管理及考核管理</font>四大模块已经实现新版改版工作。</p>
			<p>&nbsp;</p>
			<p>新版特点如下:</p>
			<ul>
				<li>界面改造，对于不同的屏幕自动进行适应。</li>
				<li><font style="color: #FF0000;">适用于移动端</font>，如手机、PAD等设备，也能正常使用</li>
			</ul>
			<p>&nbsp;</p>
			<p>特别注意:</p>
			<ul>
				<li>如用户使用的是IE，本系统将<font style="color: #FF0000;">不支持IE8及以下版本</font>，请升级浏览器版本，或者直接使用其他内核的浏览器(推荐使用360极速浏览器)</li>
				<li>手机端左侧菜单触摸滑动，仅支持Android和iOS，不支持WP</li>
			</ul>
			<p>&nbsp;</p>
			<p>常见问题:</p>
			<ul>
				<li><font style="color: #FF0000;">推荐使用360极速浏览器 浏览器</font>(<a href="http://chrome.360.cn/" target="_blank">点此下载</a>),或者拥有Chrome内核(极速模式)的浏览器</li>
				<li>用户登录后,请第一时间<font style="color: #FF0000;">修改密码</font>,以免账号不安全</li>
			</ul>
		</div>
	</div>
	<%-- <div class="ht_tablb">
	    <h3 class="ht_tabtit2">公司内部公告</h3>
	    <div class="ht_tabcon">
            <s:iterator value="sches" status="sch">
             
            <p>
            <a href="<s:url namespace="/sche" action="schedule_load"><s:param name="viewFlag">view</s:param><s:param name="sche.uuid" value="uuid"></s:param></s:url>">
            [<s:if test="type==0"> 部门手册</s:if>
			<s:if test="type==1">公司制度</s:if>
			<s:if test="type==2">公司文件</s:if>
			<s:if test="type==3"> 更新公告 </s:if>]&nbsp;
			${title}&nbsp;(${sdate})</a></p>
			
            </s:iterator>
	    </div>
	    <!-- <div class="ht_right">
	    <a href='/sche/schedule_leftList?sche.type=0'>>>更多公告信息</a>
	    </div> -->
	</div> --%>
	<div>
	</div>
</div>
</div>
<s:action name="ref_foot" namespace="/manager" executeResult="true" />
<script type="text/javascript">
var toggleCon = function(o){
	$(o).find(".update_content").toggle();
};

$(function(){
	$(".update_info").click(function(){
		toggleCon($(this));
	});
});
</script>
</body>
</html>