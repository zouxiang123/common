var contentEditingItemArr = [];
var contentEditingItemNavLastStep;

/**
 * 导航栏切换事件注册
 */
$(document).ready(function() {
	// 添加公共事件
	addCommonEvents();
	// 隐藏无权限按钮
	$("[data-permission-key]").each(function() {
		if (hasPermission($(this).attr("data-permission-key"))) {
			$(this).removeClass("hide");
		} else {
			$(this).addClass("hide");
		}
	});
	// 隐藏或显示iframe的内容
	showOrHideIframe();
});

function addCommonEvents() {

	// 导航栏切换事件
	$(".content-editing-bar .content-editing-item").click(function() {
		tabSwitch($(this).parent().attr("id"), $(this).attr("data-link"), null, null, true);
	});

	// textarea 自动伸缩事件
	$("textarea.textarea-auto-height").each(function() {
		addTextareaAutoHeightEvent(this);
	});

	document.onkeydown = function(e) {
		e = e || event;
		if (e.keyCode == 116) {
			e.preventDefault(); // 组织默认刷新
			if (window.top == window.self) {
				if ($("#frameBody").size() == 0) {
					location.reload(true);
				} else {
					var iframeWindow = $("#frameBody")[0].contentWindow || $("#frameBody")[0];
					$("#frameBody").attr("src", iframeWindow.location.href);
				}
			} else {
				location.reload(true);
			}
		}
	};
}
/**
 * 导航栏切换
 * 
 * @param barId
 * @param tabId
 * @param arr
 * @param last
 * @param isClick
 * @returns {Boolean}
 */
function tabSwitch(barId, tabId, arr, last, isClick) {
	if (isEmpty(arr) || arr.length == 0) {
		arr = contentEditingItemArr;
	} else {
		contentEditingItemArr = arr;
	}
	var maxLength = $("#" + barId + " .content-editing-item").length;
	if (isEmpty(barId)) {
		showWarn("导航ID不能为空");
		return false;
	} else if (isEmpty(tabId)) {
		showWarn("需要激活的tab不能为空");
		return false;
	} else if (isEmpty(arr) || arr.length == 0) {
		showWarn("传入的数组不能为空");
		return false;
	}
	if (!(tabId.substring(0, 1) == "#")) {
		tabId = "#" + tabId;
	}
	var i = 0;
	var maxDisabled = 0;
	var isDisabled = false;
	var lastStep;
	if (isEmpty(isClick) || !isClick) {
		lastStep = 0;
		$("#" + barId + " .content-editing-item").each(function() {
			if ($(this).attr("data-link") == tabId) {
				return false;
			}
			lastStep++;
		});
		if (!isEmpty(last) && lastStep < last) {
			lastStep = last;
		}
		if (isEmpty(contentEditingItemNavLastStep)) {
			contentEditingItemNavLastStep = lastStep;
		} else {
			if (lastStep < contentEditingItemNavLastStep) {
				lastStep = contentEditingItemNavLastStep;
			} else {
				contentEditingItemNavLastStep = lastStep;
			}
		}
	}
	var isInit = isEmpty(lastStep) ? false : true;
	$("#" + barId + " .content-editing-item").each(function() {
		if (isInit) {
			// 设置宽度
			var width = (100 / maxLength).toFixed(4) + "";
			$(this).width(width.substring(0, width.length - 1) + "%");
			// 隐藏所有
			$($(this).attr("data-link")).hide();
		}
		if ($(this).attr("data-link") == tabId) {
			if (!isDisabled && $(this).children(".disabled").length > 0) {
				if (isInit) {
					if (i < lastStep) {
						isDisabled = true;
					}
				} else {
					isDisabled = true;
					return false;
				}
			}
		}
		i++;
	});
	if (!isDisabled) {
		i = 0;
		if (!isInit) {
			$("#" + barId + " .content-editing-item").each(function() {
				if ($(this).children(".disabled").length > 0) {
					return false;
				}
				maxDisabled++;
			});
		} else {
			maxDisabled = lastStep + 1;
		}
		$("#" + barId + " .content-editing-item").each(
						function() {
							var $data = $($(this).attr("data-link"));
							if (i < maxDisabled) {
								if ($(this).attr("data-link") == tabId) {
									$data.show();
									if (i == 0) {
										$(this).html("<div class='content-editing'>" + arr[i] + "</div><div class='content-editing-ae'></div>");
									} else if (i == maxLength - 1) {
										$(this).html("<div class='content-editing-as'></div> <div class='content-editing'>" + arr[i] + "</div>");
									} else {
										$(this).html(
														"<div class='content-editing-as'></div><div class='content-editing'>" + arr[i]
																		+ "</div><div class='content-editing-ae'></div>");
									}
								} else {
									$data.hide();
									if (i == maxDisabled - 1) {
										$(this).html("<span style='margin-top: 0px;'>" + arr[i] + "</span>");
									} else {
										$(this).html("<img src='" + ctx + "/assets/img/edit-finish.png'><span>" + arr[i] + "</span>");
									}
								}
							} else {
								$(this).html("<span class='disabled'>" + arr[i] + "</span>");
							}
							i++;
						});
	}
}

/**
 * 隐藏或者显示iframe的内容
 */
function showOrHideIframe() {
	if (isFromIframe()) {
		$("[data-iframe]").each(function() {
			if ($(this).attr("data-iframe") == "true" && parent == top) {
				$(this).show();
			}
		});
		if (!$("body").hasClass("bg-grey"))
			$("body").css("padding-top", "0px");
		$("[data-iframe-css]").each(function() {
			if ($(this).attr("data-iframe-css") == "main") {
				$(this).attr("style", "padding:10px 15px 0px 15px !important");
			}
			if (parent != top) {
				$(this).attr("style", "padding:10px 0px 0px 15px !important");
			}
		});
	} else {
		$("[data-iframe]").each(function() {
			if ($(this).attr("data-iframe") == "false") {
				$(this).show();
			}
		});
	}

	$("[data-inner-iframe]").each(function() {
		if (parent != top) {
			$(this).attr("class", "");
			$("#patient-navbar").hide();
		}
	});
	if (parent != top) {
		$("#patientHeadNav").attr("style", "padding:0px 0px 0px 15px");
	}
}

/** textarea 添加自动伸缩 */
function addTextareaAutoHeightEvent(element) {
	$(element).unbind("focus").bind("focus", function() {
		window.activeobj = this;
		this.clock = setInterval(function() {
			activeobj.style.height = activeobj.scrollHeight + 'px';
		}, 50);
	});
	$(element).unbind("blur").bind("blur", function() {
		clearInterval(this.clock);
	});
}

/**
 * 显示系统dialog
 * 
 * @param errorMap
 */
function showSystemDialog(errorMap, type) {
	if (isEmpty(type)) {// confirm warn info
		type = 'warn';
	}
	if (!isEmptyObject(errorMap)) {
		var json = {
			messages : errorMap,
			type : type
		};
		SystemDialog.set(json);
		SystemDialog.modal('show');
		SystemDialog.callback(function() {
			SystemDialog.modal("hide");
		});
	}
}

/**
 * 显示系统dialog alert
 * 
 * @param message
 *            显示信息
 */
function showWarn(message, callback) {
	var needCallback = isEmpty(callback) ? false : true;
	var map = {
		"" : message
	};
	var json = {
		messages : map,
		type : "info",
		cancelable : needCallback
	};
	SystemDialog.set(json);
	SystemDialog.modal('show');
	if (needCallback) {
		SystemDialog.callback(callback);
	} else {
		SystemDialog.callback(function() {
			SystemDialog.modal("hide");
		});
	}
}

/**
 * 显示系统dialog error
 * 
 * @param message
 *            显示信息
 */
function showError(message, callback) {
	var needCallback = isEmpty(callback) ? false : true;
	var map = {
		"" : message
	};
	var json = {
		messages : map,
		type : "warn",
		cancelable : needCallback
	};
	SystemDialog.set(json);
	SystemDialog.modal('show');
	if (needCallback) {
		SystemDialog.callback(callback);
	} else {
		SystemDialog.callback(function() {
			SystemDialog.modal("hide");
		});
	}
}

/**
 * 显示系统dialog alert
 * 
 * @param message
 *            显示信息
 */
function showAlert(message) {
	var map = {
		"" : message
	};
	var json = {
		messages : map,
		type : "confirm"
	};
	SystemDialog.set(json);
	SystemDialog.modal('show');
	SystemDialog.callback(function() {
		SystemDialog.modal("hide");
	});
}

/**
 * 显示系统dialog confirm
 * 
 * @param message
 *            显示信息
 * @param callback
 *            确认回调函数
 */
function showConfirm(message, callback) {
	var map = {
		"" : message
	};
	var json = {
		messages : map,
		cancelable : true,
		type : "confirm"
	};
	SystemDialog.set(json);
	SystemDialog.modal('show');
	SystemDialog.callback(callback);
}
/**
 * 获取权限集合
 * 
 * @param name
 * @returns
 */
function getPermissionList(key) {
	var permissionStr = window.localStorage.getItem(key);
	if (isEmpty(permissionStr)) {
		return new Array();
	}
	return eval('(' + permissionStr + ')');
}
/**
 * 判断是否有权限
 */
function hasPermission(val) {
	var user_non_permission_list = getPermissionList("user_non_permission_list");
	for (var i = 0; i < user_non_permission_list.length; i++) {
		if (user_non_permission_list[i].key == val) {
			return false;
		}
	}
	return true;
}

/**
 * 通过权限key获取权限对象
 * 
 * @param val
 * @returns权限对象
 */
function getPermissionObjByKey(val) {
	var user_permission_list = getPermissionList("user_permission_list");
	for (var i = 0; i < user_permission_list.length; i++) {
		if (user_permission_list[i].key == val) {
			return user_permission_list[i];
		}
	}
}

/**
 * 通过权限key获取权限url,如果当前权限不存在url，获取其子节点的第一个url
 * 
 * @param val
 * @returns 权限中的url地址
 */
function getPermissionUrlByKey(val) {
	var user_permission_list = getPermissionList("user_permission_list");
	var obj = null;
	for (var i = 0; i < user_permission_list.length; i++) {
		if (user_permission_list[i].key == val) {
			obj = user_permission_list[i];
			break;
		}
	}
	if (!isEmptyObject(obj)) {
		if (!isEmpty(obj.url)) {
			if (obj.url.indexOf(",") > -1) {// 如果一个权限对应多个url，默认获取第一个
				var arr = obj.url.split(",");
				return arr[0];
			} else {
				return obj.url;
			}
		} else {
			return getPermissionUrlByParentCode(obj.code);
		}
	}
	return null;
}

/**
 * 通过url获取权限对象
 * 
 * @param val
 * @returns权限对象
 */
function getPermissionObjByUrl(url) {
	var realUrl = getRelativeUrl(url);
	var user_permission_list = getPermissionList("user_permission_list");
	for (var i = 0; i < user_permission_list.length; i++) {
		if (!isEmpty(user_permission_list[i].url)) {
			var tempUrls = user_permission_list[i].url.split(",");
			for (var t = 0; t < tempUrls.length; t++) {
				if (tempUrls[t] == realUrl) {
					return user_permission_list[i];
				}
			}
		}
	}
}

/**
 * 通过pCode获取该对象下第一个有权限的url
 * 
 * @param val
 * @returns 第一个有权限的url,如果对应多个url，则返回第一个
 */
function getPermissionUrlByParentCode(code) {
	var obj = getPermissionByParentCode(code);
	if (!isEmptyObject(obj)) {
		if (obj.url.indexOf(",") > -1) {// 如果一个权限对应多个url，默认获取第一个
			var arr = obj.url.split(",");
			return arr[0];
		} else {
			return obj.url;
		}
	}
}

/**
 * 通过pcode获取该对象下所有同级的对象
 * 
 * @param code
 * @returns permission Object[]
 */
function getPermissionSiblingsByPcode(code) {
	var siblings = [];
	var user_permission_list = getPermissionList("user_permission_list");
	for (var i = 0; i < user_permission_list.length; i++) {
		if (user_permission_list[i].pCode == code) {
			siblings.push(user_permission_list[i]);
		}
	}
	return siblings;
}

/**
 * 通过pCode获取该对象下第一个有权限而且有url的对象
 * 
 * @param val
 * @returns permission Object
 */
function getPermissionByParentCode(code) {
	var user_permission_list = getPermissionList("user_permission_list");
	for (var i = 0; i < user_permission_list.length; i++) {
		if (user_permission_list[i].pCode == code) {
			if (isEmpty(user_permission_list[i].url)) {
				return getPermissionByParentCode(user_permission_list[i].code);
			} else {
				return user_permission_list[i];
			}
		}
	}
}

/** 获取请求不包含后缀的相对路径 */
function getRelativeUrl(url) {
	if (isEmpty(url) || url.indexOf(".shtml") == -1) {
		return url;
	}
	var realUrl = "";
	var tempUrls = url.substring(url.indexOf(ctx) + ctx.length + 1, url.indexOf(".shtml")).split("/");
	for (var i = 0; i < tempUrls.length; i++) {
		if (!isEmpty(tempUrls[i]))
			realUrl += tempUrls[i] + "/";
	}
	if (realUrl.length > 0) {
		realUrl = realUrl.substring(0, realUrl.length - 1);
	}
	return realUrl;
}
/* ---------------- ajax filter start----------------------------*/
var _storedPendingRequests = new Object();
/**
 * 所有ajax请求的通用前置filter
 * 
 * 1.统一处理错误异常 2.防止重复提交。两种方案，一根据状态位控制；二通过显示遮罩层控制。使用是，添加数据loading: true
 */
$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
	// 不重复发送相同请求
	var exists = false;
	for ( var i in _storedPendingRequests) {
		if (_storedPendingRequests[i].url == options.url && _storedPendingRequests[i].data == options.data) {
			exists = true;
			break;
		}
	}
	if (!exists) {
		var pendingKey = "ajax_" + new Date().getTime();
		_storedPendingRequests[pendingKey] = {
			url : options.url,
			data : options.data
		};
		jqXHR.pendingKey = pendingKey;
	} else {
		// or do other
		jqXHR.abort();// 终止请求
	}

	options.loading = options.loading || false;
	options.loadingMsg = options.loadingMsg || "正在加载中...";

	var beforeSend = options.beforeSend;
	options.beforeSend = function(jqXHR, settings) {
		if (options.loading) {
			// 提交前回调方法,动态创建loadDiv
			var loadingDiv = $("#loading_other").clone();
			$(loadingDiv).find("#loadingMsg").text(options.loadingMsg);
			$(loadingDiv).attr("id", jqXHR.pendingKey);
			$(loadingDiv).removeClass("hide");
			$("body").append(loadingDiv);
		}
		if ($.isFunction(beforeSend)) {
			beforeSend.apply(this, arguments);
		}
	};

	var success = options.success;
	options.success = function(data, textStatus, jqXHR) {
		if ($.isFunction(success)) {
			success.apply(this, arguments);
		}
	};

	var errorMsgArr = {
		401 : "登陆信息已过期,请重新登陆",
		404 : "404:很抱歉,您访问地址出错了...",
		403 : "403:很抱歉,您没有访问权限...",
		500 : "500:很抱歉,服务器出错了..."
	};
	var error = options.error;
	options.error = function(jqXHR, textStatus, errorThrown) {
		var status = jqXHR.status;
		var msg = errorMsgArr[status];
		if (!isEmpty(msg))
			showTips(msg, 1000);
		if (status == 401) {
			top.location.reload(true);
		}
		if ($.isFunction(error)) {
			error.apply(this, arguments);
		}
	};

	var complete = options.complete;
	options.complete = function(jqXHR, textStatus) {
		if (options.loading) {
			// 请求完成后回调函数 (请求成功或失败之后均调用)。
			$("#" + jqXHR.pendingKey).remove();
		}
		// clear from pending requests
		delete _storedPendingRequests[jqXHR.pendingKey];
		delete jqXHR.pendingKey;
		if ($.isFunction(complete)) {
			complete.apply(this, arguments);
		}
	};
});
/* ---------------- ajax filter end----------------------------*/

/** 提示信息 */
function showTips(msg, delay) {
	if ($("#save-msg-tips").length == 0) {
		$("body").append('<div class="toast" id="save-msg-tips" style="z-index:99999;"><p></p></div>');
	}

	if (isEmpty(msg))
		msg = "保存成功";

	$("#save-msg-tips p").text(msg);
	$("#save-msg-tips").removeClass("hide");

	if (isEmpty(delay)) {
		delay = 2000;
	}
	$("#save-msg-tips").fadeIn("slow", function() {
		setTimeout(function() {
			$("#save-msg-tips").addClass("hide");
		}, delay);
	});
}
