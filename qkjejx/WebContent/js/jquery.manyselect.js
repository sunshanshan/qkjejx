(function(){ 

$.fn.extend({ 
checks_select: function(options){ 
jq_checks_select = null; 
$(this).val("---请选择---"); 
$(this).next().empty(); //先清空 
$(this).unbind("click"); 
$(this).click(function(e){ 
jq_check = $(this); 
if (jq_checks_select == null) { 
jq_checks_select = jq_check.next(); 
jq_checks_select.addClass("checks_div_select"); 
$.each(options, function(i, n){ 
check_div=$("<div id="+i+"><input type='checkbox' value='" + i + "'>" + n + "</div>").appendTo(jq_checks_select); 
check_box=check_div.children(); 
check_box.click(function(e){ 
temp=""; 
uuid="";
$(this).parents().find("input:checked").each(function(i){ 
uid=$(this).val();
if(i==0){ 
uuid=$(this).val();
temp=$('#'+uid).text(); 
}else{ 
uuid+=","+$(this).val();
temp+=","+$("#"+uid).text(); 
 
} 

}); 
jq_check.val(temp); 
$('#id_div').val(uuid);
e.stopPropagation(); 
}); 
}); 

jq_checks_select.show(); 
}else{ 
jq_checks_select.toggle(); 

} 
e.stopPropagation(); 
}); 
$(document).click(function () { 
flag=$("#test_div"); 
if(flag.val()==""){ 
flag.val("---请选择---"); 
} 
jq_checks_select.hide(); 
}); 
} 
}) 

})(jQuery); 