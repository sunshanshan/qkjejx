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
				<div class="label_hang">
					<s:hidden id="icuuid" name="ic.uuid" value="%{ic.uuid }"></s:hidden>
					<s:hidden id="checkeds" value="%{checkeds }"></s:hidden>
					<s:hidden id="inuuid" name="index360.uuid" value="%{index360.uuid }"></s:hidden>
					<div class="label_ltit">考核人:</div>
					<div class="label_rwbenx">${ic.check_user_name}&nbsp;
					<div style="float: right" id="totle">
					<s:if test="%{checkeds==2}">
					&nbsp;得分：${score.check_score }
					</s:if>
					<s:else>
					&nbsp;得分：无
					</s:else>
					</div>
					</div>
					
				</div>

				<div class="label_main">
						<table id="oneTable"  width="100%" cellpadding="0" cellspacing="0" border="0"
							class="lb_jpin">
							<s:if test="checkeds==0">
							<s:iterator value="asses" status="sta">
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
									<input type="radio" name="score${sta.index }" value="1">1
									<input type="radio" name="score${sta.index }" value="2">2
									<input type="radio" name="score${sta.index }" value="3">3
									<input type="radio" name="score${sta.index }" value="4">4
									<input type="radio" name="score${sta.index }" value="5">5
									<input type="radio" name="score${sta.index }" value="0">N
									<input type="hidden" name="raname" value="${sta.index }">
									<input type="hidden" name="kpiid" value="${uuid }">
									<hr style=" height:2px;border:none;border-top:2px dotted #185598;" />
									</td>
								</tr>
								
							</s:iterator>
							</s:if>
							<s:elseif test="checkeds==2"><!-- 修改 -->
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
							</s:elseif>
						</table>
					</div>
				
				
				<fieldset class="clear">
					<legend>自由回答</legend>
					<!--<span class="shaddress">--------------------收货地址--------------------</span>-->
					<div class="label_main">
						<table width="100%" cellpadding="0" cellspacing="0" border="0"
							class="lb_jpin">
							<s:if test="checkeds==0">
							<s:iterator value="remark360s" status="sta">
								<tr>
									<td>${title}
									<s:hidden name="remark_title" value="%{title}"></s:hidden>
									<s:hidden name="remark_uuid" value="%{uuid}"></s:hidden>
									</td>
									
								</tr>
								<tr>
								<td>
									<s:textarea name="ic_remark" cssClass="label_hang_linput inputNote validate[maxSize[65535]]" />
									</td>
								</tr>
							</s:iterator>
							</s:if>
							<s:elseif test="checkeds==2"><!-- 修改 -->
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
							</s:elseif>
							
							<tr>
								<td colspan="6">
								<s:if test="checkeds==0">
									<button id="submit" type="button">保存</button>
								</s:if>
								<s:elseif test="checkeds==2"><!-- 修改 -->
								<s:hidden id="score_id" value="%{score.uuid }"></s:hidden>
									<button id="mdy" type="button">保存</button>
								</s:elseif>

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
	/* $(function(){
	    getRelust();
	});
	
	function getRelust(){
		$("#oneTable").find("tr").each(function(i){
			 if(i>0){
				var index_id=$("#index_id"+i).text();
				var cname=$("#ititle"+i).text();
				var uuid=$("#uuid"+i).text();
				
				var lastindex_id=$("#index_id"+(i-1)).text();
				var nindex_id=$("#index_id"+(i+1)).text();
				if(lastindex_id==index_id && nindex_id!=index_id){
					$("#showtr"+uuid).after('<tr class="fo"><td  colspan="8">'+cname+'</td></tr>');
				}
			 }
			
	    });
	} */
	
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
		        				resualt=true;
		        			}else{
		        				resualt=false;
		        			}
		        		}
		        		
		        	});
		        	
		        if(resualt==true){
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
				            	  	}else{
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