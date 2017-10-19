<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="it" uri="http://qkjchina.com/iweb/iwebTags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/jsp/manager/ref_head.jsp" />
<link rel="stylesheet" type="text/css" href="../css/demo.css">

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
font-size:0.8em;
border-bottom-style:none
}

.new_top{position:relative;width:100%;height:45px;background:#2695d0;}
.new_lTit{position:absolute;line-height: 49px;bottom:0;left:40%;margin-left:-100px;color: #fff;font-size: 18px;font-weight: normal;}


.listyle2{
border-bottom:solid 1px #E8E8E8;
font-size: 16px;
}
.div{
border-radius:  0 0 3px 3px;
}
fieldset{
    border: 0;
}

body{  
   background-color:#f7f7f7;                
}
</style>
<title>用户列表</title>
</head>
<body>

<header>
<div class="new_top">
	<div class="new_lTit">青青稞酒360°领导力评估调查问卷</div>
</div>
</header>
	<div class="">
		<div class="tab_warp main">
			<div class="dq_step"></div>
			<div class="checkScore">
				<div class="label_hang" style="padding-left: 10%;padding-right: 10%">
					<s:hidden id="icuuid" name="ic.uuid" value="%{ic.uuid }"></s:hidden>
					<s:hidden id="checkeds" value="%{checkeds }"></s:hidden>
					<s:hidden id="inuuid" name="index360.uuid" value="%{index360.uuid }"></s:hidden>
					<font style="font-weight:bold;">被评价者:</font>
					${ic.check_user_name}&nbsp;
				
				<br>
				<p>
					<span style="font-size:0.9em;font-family:Arial;">
					指导语：360度测评是一种用于评估个人领导和管理技巧的工具。此评估问卷由一系列行为描述所组成，是青青稞酒管理人员都需要展现的重要行为。我们将行为按照一定的结构组合在一起，称之为领导力素质。请根据被您/评估者的实际情况，对以下每一项的陈述进行评分。问卷的填写是保密和匿名的，您的评分和建议将会与其他评估者的反馈综合在一起呈现在报告中。您坦率、客观的评分与反馈将有助于您/被评估者清楚地了解自己的领导力现状，并为其提供有针对性的发展建议。非常感谢您的配合！
					</span> 
				</p>
				<p>
				<br>
				<span style="font-size:0.9em;font-family:Arial;">测评内容：
				<br>•${factsize}管理维度
				<br>•${Indexsize}项目
				</span>
				</p>
				<br>
				<p>
				<span style="font-size:0.9em;font-family:Arial;">其中，${factsize}管理维度分别为
				</span>
				</p>
				<ul>
				<s:iterator value="index" status="sta">
				<li>•&nbsp;${manifestation}</li>
				</s:iterator>
				</ul>
				<br>
				<p>
					<span style="font-size:1em;font-family:Arial;">
					请回顾自己/被评估者在日常工作中的表现，评价其平时的行为是否符合以下的描述：
					<br>
					1 = 非常不符合 &nbsp;&nbsp;&nbsp;&nbsp; 2 = 比较不符合  &nbsp;&nbsp;&nbsp;&nbsp;   3 = 一般  
					&nbsp;&nbsp;&nbsp;&nbsp;   4 = 比较符合  &nbsp;&nbsp;&nbsp;&nbsp;   5 = 非常符合      &nbsp;&nbsp;&nbsp;&nbsp; N = 不了解/不适用（不了解被评价者的情况/题目对被评价者不适用）
					</span> 
				</p>
				</div>

				<div class="label_main">
						<table id="oneTable"  width="100%" cellpadding="0" cellspacing="0" border="0"
							class="lb_jpin" >
							<s:if test="checkeds==0">
							<s:iterator value="asses" status="sta">
								<tr>
									<td   style="padding-left: 10%;padding-right: 10%;font-weight: bold;" >
									 <span id="ts${sta.index }" style="display:none;"><font color="red">*</font></span>
									${(sta.index+1)}、${title}</td>
								</tr>
								<s:if test="detail!=null">
								<tr>
									<td   style="padding-left: 10%;padding-right: 10%">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${detail}
									</td>
								</tr>
								</s:if>
								<tr id="showtr${uuid}">
									<td   style="padding-left: 10%;padding-right: 10%;">
									<span class="ship_hidden_info" style="display:none;">
									<span id="index_id${sta.index+1}">${index_id}</span>
									<span id="ititle${sta.index+1}">${ititle}</span>
									<span id="uuid${sta.index+1}">${uuid}</span>
									</span>
									<div style="border:2px solid #b5b5b6;border-radius:5px;background-color: white;">
									<ul>
									<li style="border-bottom:solid 1px #E8E8E8;font-size: 16px;"><input style="width:20px;height:20px;" type="radio" name="score${sta.index }" value="1">1</li>
									<li class="listyle2"><input style="width:20px;height:20px;" type="radio" name="score${sta.index }" value="2">2</li>
									<li class="listyle2"><input style="width:20px;height:20px;" type="radio" name="score${sta.index }" value="3">3</li>
									<li class="listyle2"><input style="width:20px;height:20px;" type="radio" name="score${sta.index }" value="4">4</li>
									<li class="listyle2"><input style="width:20px;height:20px;" type="radio" name="score${sta.index }" value="5">5</li>
									<li style="border-top:solid 1px #F8F7F7;font-size: 16px;"><input style="width:20px;height:20px;" type="radio" name="score${sta.index }" value="0">N</li>
									</ul>
									</div>
									<br>
									
									<input type="hidden" name="raname" value="${sta.index }">
									<input type="hidden" name="kpiid" value="${uuid }">
									<!-- <hr style=" height:2px;border:none;border-top:2px dotted #185598;" /> -->
									</td>
								</tr>
								
							</s:iterator>
							</s:if>
							<s:elseif test="checkeds==2"><!-- 修改 -->
							
							<p align="center">
							<span style="font-size:1em;">
								<font color="red;">已完成考核</font>
								</span> 
							</p>
							</s:elseif>
							<s:elseif test="checkeds==33">
							
							<p align="center">
							<span style="font-size:1em;">
								<font color="red;">您已超过考核时间,不能再进行考核</font>
								</span> 
							</p>
							</s:elseif>
						</table>
					</div>
				
				
				<fieldset class="clear">
					<legend></legend>
					<!--<span class="shaddress">--------------------收货地址--------------------</span>-->
					<div class="label_main">
						<table width="100%" cellpadding="0" cellspacing="0" border="0"
							class="lb_jpin">
							<s:if test="checkeds==0">
							<s:iterator value="remark360s" status="sta">
								<tr>
									<td   style="padding-left: 10%;padding-right: 10%">${title}
									<s:hidden name="remark_title" value="%{title}"></s:hidden>
									<s:hidden name="remark_uuid" value="%{uuid}"></s:hidden>
									</td>
									
								</tr>
								<tr>
								<td   style="padding-left: 10%;padding-right: 10%">
									<s:textarea name="ic_remark" cssClass="label_hang_linput inputNote validate[required,maxSize[65535]]" />
									</td>
								</tr>
							</s:iterator>
							</s:if>
							
							<tr>
								<td colspan="6" style="padding-left: 10%;padding-right: 10%">
								<s:if test="checkeds==0">
									<button id="submit" type="button">提交</button>
								</s:if>
								<%-- <s:elseif test="checkeds==2"><!-- 修改 -->
								<s:hidden id="score_id" value="%{score.uuid }"></s:hidden>
									<button id="mdy" type="button">保存</button>
								</s:elseif> --%>

								</td>
							</tr>
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
	<script type="text/javascript">
	
	jQuery(document).ready(function ($) {
			 var checkeds=$('#checkeds').val();
			 if(checkeds==1){
				 $(".checkScore").css("display", "none");
                 $("#contact").append('<div class="alert successed">感谢您已提交！</div>');
                 $(".success").fadeIn(3000);
			 }
			 
		     $("#submit").click(function() {
		        	var resualt=false;
		        	var score="";//每个得分
		        	//判断每给radio必须选中
		        	$("input[name='raname']").each(function() {
		        		var name="score"+$(this).val();
		        		if(name!=null && name!=""){
		        			var v=document.getElementsByName(name);
		        			var j=0
			        		for (var i=0;i<v.length;i++){
			        		 if(v.item(i).checked==true){
			        			 j=1;
			        			 if(i==5){//选中的是第五个N成绩为0
			        				 score+=0+",";
			        			 }else{
			        				 score+=(i+1)+",";
			        			 }
			        			 resualt=true;
			        			 break;
			        		 }else{
			        			 resualt=false;
			        		 }
			        		}
		        			
		        		}
		        		
		        	});
		        	
		        if(1==1){
						var kpiid="";//题目编号
						var icuuid=$('#icuuid').val();//考核人
						var inuuid=$('#inuuid').val();//考核年月
						var rem="";//自由问答
						var rem_id="";//自由问答
						
						$("input[name='remark_uuid']").each(
								function() {
									rem_id+=$(this).val()+",";
								}
								);
						
						$("textarea[name='ic_remark']").each(
								function() {
									rem+=$(this).val()+",";
								}
								);
						$("input[name='kpiid']").each(
								function() {
									kpiid+=$(this).val()+",";
								}
								);
					
						var json={ic_uuid:icuuid,in_uuid:inuuid,kpiid:kpiid,scorejson:score,remark:rem,remark_uuid:rem_id};
						$.ajax({
				              type : "post",
				              async : false,
				              url : "/check360/check_360ScoreAdd",
				              data: json,
				              jsonpCallback:"callback",
				              success : function(data){
				            	  	if(data==0){
				            	  		window.location.reload();
				            	  		alert("谢谢您的提交！");
				            	  	}else if(data==3){
				            	  		alert("请检查是否有未选择项目！(注：未选项目已用*标识)");
				            	  		var r=false;
				            	  		$("input[name='raname']").each(function() {
				    		        		var name="score"+$(this).val();
				    		        		if(name!=null && name!=""){
				    		        			var v=document.getElementsByName(name);
				    			        		for (var i=0;i<v.length;i++){
				    			        		 if(v.item(i).checked==true){
				    			        			 r=true;
				    			        			 break;
				    			        		 }else{
				    			        			 r=false;
				    			        		 }
				    			        		}
				    			        		if(r==false){//本组未选中
				    			        			$("#ts"+$(this).val()).show();
				    			        		}else{
				    			        			$("#ts"+$(this).val()).hide();
				    			        		}
				    		        			
				    		        		}
				    		        		
				    		        	});
				            	  		
				            	  	}else if(data==4){
				            	  		alert("您已考核过一次,如有问题请联系管理员。");
				            	  	}
				            	  	else{
				            	  		alert("失败请联系管理员。");
				            	  	}
				                    
				              },
				              error: function(XMLHttpRequest, textStatus, errorThrown) {
				               alert(textStatus);
				              }   
				                		 
				          });
		        	}else{
		        		alert("请检查是否有未选择项目！");
		        	} 
		        
		        });
		        
		        $("#mdy").click(function() {
		        	var flag = true;
		        	var score="";
		        	//判断每给radio必须选中
		        	$("input[name='raname']").each(function() {
		        		var name="score"+$(this).val();
		        		if(name!=null && name!=""){
		        			var v=document.getElementsByName(name);
		        			var j=0
			        		for (var i=0;i<v.length;i++){
			        		 if(v.item(i).checked){
			        			 j=1;
			        			 if(i==5){//选中的是第五个N成绩为0
			        				 score+=0+",";
			        			 }else{
			        				 score+=(i+1)+",";
			        			 }
			        			 
			        			 break;
			        		 }
			        		}
		        			if(j>0){//本组中已选中
		        				flag=true;
		        			}else{
		        				flag=false;
		        			}
		        		}
		        		
		        	});
		        	
					
					if (flag == true) {
						var kpiid="";
						var icuuid=$('#icuuid').val();
						var rem="";
						var rem_id="";
						
						$("input[name='remark_uuid']").each(
									function() {
										rem_id+=$(this).val()+",";
									}
									);
							
						$("textarea[name='ic_remark']").each(
									function() {
										rem+=$(this).val()+",";
									}
									);
						$("input[name='kpiid']").each(
								function() {
									kpiid+=$(this).val()+",";
								}
								);
						var json={ic_uuid:icuuid,kpiid:kpiid,scorejson:score,remark:rem,in_uuid:$('#score_id').val(),remark_uuid:rem_id};
						$.ajax({
				              type : "post",
				              async : false,
				              url : "/check360/check_360ScoreSave",
				              data: json,
				              jsonpCallback:"callback",
				              success : function(data){
				            	  		window.location.reload();
				            	  		alert("谢谢您的提交！");
				              },
				              error: function(XMLHttpRequest, textStatus, errorThrown) {
				               alert(textStatus);
				              }   
				                		 
				          });
						
					}else{
						alert("评分不能为空且不能为字符！");
					}
		        });
		    });
		 
		 $("input:radio").change(function() { 
			 totle(); 
			});
		 
		 function totle(){
				var totle=0;
				$("input:radio").each(
					function() {
						if ($(this).attr("checked")) {
							var t=$(this).val();
							totle=Number(Number(t)+Number(totle)).toFixed(3);
			             }
						  
					}
				);
				$("#totle").html("&nbsp;得分："+totle);
			}


	</script>
</body>
</html>