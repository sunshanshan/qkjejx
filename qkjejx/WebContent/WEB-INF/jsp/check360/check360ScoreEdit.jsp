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
</style>
<title>用户列表</title>
</head>
<body>
	<div class="tab_right">
		<div class="tab_warp main">
			<div class="dq_step"></div>
			<%-- <s:form id="formEdit" name="form1" cssClass="validForm"
				action="check_360ScoreAdd" namespace="/check360" method="post"
				theme="simple"> --%>
			<div class="checkScore">
				<div class="label_hang">
					<s:hidden id="icuuid" name="ic.uuid" value="%{ic.uuid }"></s:hidden>
					<s:hidden id="checkeds" value="%{checkeds }"></s:hidden>
					<s:hidden id="inuuid" name="index360.uuid" value="%{index360.uuid }"></s:hidden>
					<div class="label_ltit">考核人:</div>
					<div class="label_rwbenx">${ic.check_user_name}&nbsp;考核权重：${ic.weight } <s:hidden id="icweight" value="%{ic.weight}"></s:hidden>
					<div style="float: right" id="totle">
					<s:if test="%{checkeds==2}">
					&nbsp;总评分：${score.check_score } &nbsp; &nbsp;总得分：${score.check_gold}
					</s:if>
					<s:else>
					&nbsp;总评分：无 &nbsp; &nbsp;总得分：无
					</s:else>
					</div>
					</div>
					
				</div>


				<fieldset class="clear">
					<legend>kpi列表</legend>
					<!--<span class="shaddress">--------------------收货地址--------------------</span>-->
					<div class="label_main">
						<table width="100%" cellpadding="0" cellspacing="0" border="0"
							class="lb_jpin">
							<tr>
								<th>kpi</th>
								<th>周期</th>
								<th>权重</th>
								<th>评分</th>
								<th>得分</th>
								<th>计分方式</th>
								<th>指标定义</th>
								<th>指标标准</th>
							</tr>
							<s:if test="checkeds==0">
							<s:iterator value="kpis" status="sta">
								<tr
									class="<s:if test="#sta.odd == true">oddStyle</s:if><s:else>evenStyle</s:else>">
									<td>${kpi}
									<s:hidden name="kpiid" value="%{uuid}"></s:hidden>
									<s:hidden name="kpi" value="%{kpi}"></s:hidden>
									</td>
									<td>${cyc }</td>
									<td>${weight }<s:hidden name="sonweight" value="%{weight}"></s:hidden><input id="w${uuid }" type="hidden" value="${weight }"></td>
									<td><input id="s${uuid }" name="sonScore" type="text" class="required" onblur="kpi('${uuid}');"/></td>
									<td class="inp"><input id="g${uuid }" name="songold" type="text" class="required" value="${goal }" readonly="readonly"/></td>
									<td>${count_way }</td>
									<td class="td2 longnote" title="${definition}">
										${it:subString(definition,18)}</td>
									<td class="td2 longnote" title="${correctly}">
										${it:subString(correctly,18)}</td>
								</tr>
							</s:iterator>
							</s:if>
							<s:elseif test="checkeds==2"><!-- 修改 -->
							<s:iterator value="sonScores" status="sta">
								<tr
									class="<s:if test="#sta.odd == true">oddStyle</s:if><s:else>evenStyle</s:else>">
									<td>${kpi}
									<s:hidden name="sonid" value="%{uuid}"></s:hidden>
									</td>
									<td>${cyc }</td>
									<td>${weight }<input id="w${uuid }" name="weight" type="hidden" value="${weight }"></td>
									<td class="inp"><input id="s${uuid }" name="sonScore" type="text" class="required" value="${check_score}" onblur="kpi('${uuid}');"/></td>
									<td class="inp"><input id="g${uuid }" name="songold" type="text" class="required" value="${goal }" readonly="readonly"/></td>
									<td>${count_way }</td>
									<td class="td2 longnote" title="${definition}">
										${it:subString(definition,18)}</td>
									<td class="td2 longnote" title="${correctly}">
										${it:subString(correctly,18)}</td>
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
				
				<div class="label_hang">
					<div class="label_ltit">备注:</div>
					<div class="label_rwben"><s:textarea id="ic_remark" name="score.remark" cssClass="label_hang_linput inputNote validate[maxSize[65535]]" /></div>
				</div>
				
			</div>
			<div id="contact" class="checked"></div>
			<%-- </s:form> --%>
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
//                 $.smooth("#contact", -60);
                 $(".success").fadeIn(3000);
			 }
		        $("#submit").click(function() {
		        	var flag = true;
		        	$("input[name='sonScore']").each(function() {
						if ($(this).val() == null || $(this).val() == "") {
							flag = false;
						}else{//是否为数字
							 var reg = new RegExp("^-?[0-9]*.?[0-9]*$");
							if (reg.test($(this).val()))
							{
								 var absVal = Math.abs($(this).val());
							     if($(this).val()==absVal){
							        	
							      }else{
							       flag = false;
							      }
								
							}else{
								flag=false;
							}
						}
					});
		        	
					if (flag == true) {
						//$("#formEdit").submit();
						var score="";
						var kpiid="";
						var kpi="";
						var icuuid=$('#icuuid').val();
						var inuuid=$('#inuuid').val();
						var rem=$('#ic_remark').val();
						var sonweight="";
						$("input[name='sonScore']").each(
								function() {
									score+=$(this).val()+",";
								}
								);
						$("input[name='kpiid']").each(
								function() {
									kpiid+=$(this).val()+",";
								}
								);
						$("input[name='kpi']").each(
								function() {
									kpi+=$(this).val()+",";
								}
								);
						
						$("input[name='sonweight']").each(
								function() {
									sonweight+=$(this).val()+",";
								}
								);
						var json={ic_uuid:icuuid,in_uuid:inuuid,kpiid:kpiid,scorejson:score,checkkpis:kpi,sonweight:sonweight,remark:rem};
						$.ajax({
				              type : "post",
				              async : false,
				              url : "/check360/check_360ScoreAdd",
				              data: json,
				              jsonpCallback:"callback",
				              success : function(data){
				            	  	if(data==0){
				            	  		/* $(".checkScore").css("display", "none");
					                    $("#contact").append('<div class="alert successed">感谢您已提交！</div>');
					                    $(".success").fadeIn(3000); */
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
						alert("评分不能为空且不能为字符！");
					}
		        });
		        
		        $("#mdy").click(function() {
		        	var flag = true;
		        	$("input[name='sonScore']").each(function() {
						if ($(this).val() == null || $(this).val() == "") {
							flag = false;
						}else{//是否为数字
							 var reg = new RegExp("^-?[0-9]*.?[0-9]*$");
							if (reg.test($(this).val()))
							{
								 var absVal = Math.abs($(this).val());
							     if($(this).val()==absVal){
							        	
							      }else{
							       flag = false;
							      }
								
							}else{
								flag=false;
							}
						}
					});
					
					if (flag == true) {
						//$("#formEdit").submit();
						var score="";
						var kpiid="";
						var icuuid=$('#icuuid').val();
						var rem=$('#ic_remark').val();
						var gold=""
						$("input[name='sonScore']").each(
								function() {
									score+=$(this).val()+",";
								}
								);
						$("input[name='songold']").each(
								function() {
									gold+=$(this).val()+",";
								}
								);
						$("input[name='sonid']").each(
								function() {
									kpiid+=$(this).val()+",";
								}
								);
						var json={ic_uuid:icuuid,kpiid:kpiid,scorejson:score,goldjson:gold,remark:rem,in_uuid:$('#score_id').val()};
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
		 
		 function kpi(uuid){
				var sid="s"+uuid;
				var gid="g"+uuid;
				var wid="w"+uuid;
				var sp=document.getElementById(sid).value;
				var wp=document.getElementById(wid).value;
				document.getElementById(gid).value=sp*wp;
				totle();
			}
		 
		 function totle(){
				var totle=0;
				var b="&nbsp;总评分：";
				var gold=0;
				var w=$('#icweight').val();
				$("input[name='songold']").each(
					function() {
						if($(this).val()!=null && $(this).val()!=""){
							var t=$(this).val();
							totle=Number(Number(t)+Number(totle)).toFixed(3);
						}
					}
				);
				gold=Number(Number(totle)*Number(w)).toFixed(3);
				$("#totle").html(b+totle+"&nbsp;"+"总得分："+gold);
			}


	</script>
</body>
</html>