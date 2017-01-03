/**
 * 所需 jquery1.8 / jqueryui1.10(修改版) / common_ajax2.0.js
 */
var SimpleLoadUser = function(ajax_url_action, _successCallback){
	var loadmember = new LoadMemberAutoComplete();
	loadmember.config = { ajax_url_action : ajax_url_action,
	_successCallback : _successCallback };
	loadmember.addEvent();
};

var LoadMemberAutoComplete = function(){
	var selfobj = this;

	this.config = { ajax_url_action : "/erp/common_ajax/json_ajax",
	_successCallback : function(event, ui){} };

	var cache = {};// 缓存
	this.addEvent = function(){
		
		// 会员姓名搜索
		$("#order_user_name").autocomplete({ source : function(request, response){
			$("#order_user_id").val("0")
			var term = request.term;
			if (term in cache) {
				response($.map(cache[term], function(item){
					return { order_user_id : item.uuid,
						
						order_user_email : item.email,
					order_user_name : item.user_name,
					value : item.user_name,
					label : selfobj.boldColorTerm(item.user_name, request.term)
					};
					
				}));
				return;
			}

			var ajax = new Common_Ajax();
			ajax.config.action_url = selfobj.config.ajax_url_action;
			ajax.config._success = function(data, textStatus){
				cache[term] = data;
				response($.map(data, function(item){
					return { order_user_id : item.uuid,
					order_user_email : item.email,
					order_user_name : item.user_name,
					value : item.user_name,
					label : selfobj.boldColorTerm(item.user_name, request.term) };
				}));
			};
			ajax.addParameter("work", "AutoComplete");
			ajax.addParameter("parameters", "privilege_id=QKJCJ_SYSEBIZ_AJAXLOAD_USER&user_name=" + encodeURI(request.term));
			ajax.sendAjax();
		},
		minLength : 1,
		select : function(event, ui){
			selfobj.config._successCallback(event, ui);
			// loadAddress(ui.item.order_user_id, true);
		},
		focus : function(event, ui){
			$("#order_user_id").val(ui.item.order_user_id);
			$("#order_user_email").val(ui.item.order_user_email);
			$("#order_user_name").val(ui.item.order_user_name);
		} });
		
		
			
	};

	this.boldColorTerm = function(str, term){
		// return str.replace(new RegExp(term, "g"), "<b style='color: red;'>" + term + "</b>");
		return str.replace(eval("/"+term+"/ig"), "<b style='color: red;'>$&</b>");
	};
};