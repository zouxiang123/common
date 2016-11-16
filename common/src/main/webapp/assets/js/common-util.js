/**
 * 判断是否为空
 * 
 * @param obj
 * @returns {Boolean}
 */
function isEmpty(obj) {
	if (typeof (obj) == "undefined" || obj == null || obj === "") {
		return true;
	}
	return false;
}

function isEmptyObject(obj) {
	if (isEmpty(obj)) {
		return true;
	}
	// 兼容arra自定义属性
	if (typeof (obj) === "object") {
		if ((obj instanceof Array) && obj.length == 0)
			return true;
		for ( var name in obj) {
			return false;
		}
	} else {
		return false;
	}

	return true;
}

/**
 * 将undefined "" null 转换成 ""
 * 
 * @param obj
 * @returns
 */
function convertEmpty(obj) {
	if (isEmpty(obj)) {
		return "";
	}
	return obj;
}

/** 判断是否为pc */
function isFromPC() {
	var isAndroid = navigator.userAgent.match(/android/ig), isIos = navigator.userAgent.match(/iphone|ipod/ig), isIpad = navigator.userAgent
					.match(/ipad/ig), isWeixin = (/MicroMessenger/ig).test(navigator.userAgent);

	if (isWeixin || isAndroid || isIos || isIpad) {
		return false;
	}
	return true;
}

/** 判断是否是ipad或者Ios */
function isFromIOS() {
	var isIos = navigator.userAgent.match(/iphone|ipod/ig), isIpad = navigator.userAgent.match(/ipad/ig);
	if (isIos || isIpad) {
		return true;
	}
	return false;
}
/**
 * 对Date的扩展，将 Date 转化为指定格式的String 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) eg:
 * (newDate()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
 * 
 * (newDate()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04
 * 
 * (newDate()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04
 * 
 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04 (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
 */
Date.prototype.pattern = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // 小时
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	var week = {
		"0" : "/u65e5",
		"1" : "/u4e00",
		"2" : "/u4e8c",
		"3" : "/u4e09",
		"4" : "/u56db",
		"5" : "/u4e94",
		"6" : "/u516d"
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f" : "/u5468") : "") + week[this.getDay() + ""]);
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
};

String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}

/**
 * 判断是否是来自iframe
 * 
 * @returns {Boolean}
 */
function isFromIframe() {
	if (self != top) {
		return true;
	}
	return false;
}

/**
 * 判断是否是来自多层iframe
 * 
 * @returns {Boolean}
 */
function isFromInnerIframe() {
	if (parent != top) {
		return true;
	}
	return false;
}

/**
 * 在url上拼接时间戳
 * 
 * @param url
 * @returns {String}
 */
function convertURL(url) {
	// 获取时间戳
	var timstamp = (new Date()).valueOf();
	// 将时间戳信息拼接到url上
	if (url.indexOf("?") >= 0) {
		var index = url.indexOf("&t=");
		if (index > -1) {
			var nextIndex = url.indexOf("&", index + 1);// 时间戳后面是否存在参数
			var tempUrl = "";
			if (nextIndex > -1) {
				tempUrl = url.substring(nextIndex);
			}
			url = url.substring(0, index) + "&t=" + timstamp;
			if (nextIndex > -1) {
				url += tempUrl;
			}
		} else {
			url = url + "&t=" + timstamp;
		}
	} else {
		url = url + "?t=" + timstamp;
	}
	return url;
}

function formatMoney(s, n) // s:传入的float数字 ，n:希望返回小数点几位
{
	if (isEmpty(s)) {
		return "";
	}
	if (isEmpty(n)) {
		n = 2;
	}
	n = n > 0 && n <= 20 ? n : 2;
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
	var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
	t = "";
	for (var i = 0; i < l.length; i++) {
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
	}
	return t.split("").reverse().join("") + "." + r;
}

/**
 * 转换为百分比
 */
Number.prototype.toPercent = function() {
	return (Math.round(this * 10000) / 100).toFixed(2) + '%';
};

/**
 * 判断是否为整数
 * 
 * @param str
 * @returns {Boolean}
 */
function isInt(str) {
	if (isEmpty(str))
		return false;
	var g = /^-?\d+$/;
	return g.test(str);
}

/**
 * 检查是否为正整数
 * 
 * @param str
 * @returns {Boolean}
 */
function isPInt(str) {
	if (isEmpty(str))
		return false;
	var g = /^[1-9]\d*$/;
	return g.test(str);
}
/**
 * 停止冒泡
 * 
 * @param event
 */
function stopEventBubble(event) {
	var e = event || window.event;

	if (e && e.stopPropagation) {
		e.stopPropagation();
	} else {
		e.cancelBubble = true;
	}
}

/**
 * form表单数据映射
 * 
 * @param jsonData
 *            JSON数据
 * @param formId
 *            formId
 * @param noMappingArr
 *            不需要过滤的属性名称
 * @returns {Boolean}
 */
function mappingFormData(jsonData, formId, noMappingArr) {
	/* var obj = eval("("+jsonStr+")"); */
	if (isEmpty(formId)) {
		return false;
	}
	var key, value, tagName, type, arr;
	for (x in jsonData) {
		key = x;
		value = jsonData[x];

		if (!isEmptyObject(noMappingArr)) {
			var continueFlag = true;
			for (y in noMappingArr) {
				if (key == noMappingArr[y]) {
					continueFlag = false;
					break;
				}
			}
			if (!continueFlag) {
				continue;
			}
		}

		$("#" + formId + " [name='" + key + "'],[name='" + key + "[]']").each(function() {
			tagName = $(this)[0].tagName;
			type = $(this).attr('type');
			if (tagName == 'INPUT') {
				if (type == 'radio' || type == 'checkbox') {
					$(this).prop('checked', false);
					if (!isEmpty(value)) {
						if ((typeof value == 'string') && (type == 'checkbox')) {
							arr = value.split(',');
							for (var i = 0; i < arr.length; i++) {
								if ($(this).val() == arr[i]) {
									$(this).prop('checked', true);
									break;
								}
							}
						} else if (typeof value == 'boolean') {
							if (!isEmpty($(this).val())) {
								if (value) {
									if ($(this).val() == "true" || $(this).val() == "1") {
										$(this).prop('checked', true);
									}
								} else {
									if ($(this).val() == "false" || $(this).val() == "0") {
										$(this).prop('checked', true);
									}
								}
							}
						} else {
							if ($(this).val() == value) {
								$(this).prop('checked', true);
							}
						}
					}
				} else {
					$(this).val(value);
				}
			} else if (tagName == 'SELECT' || tagName == 'TEXTAREA') {
				$(this).val(value);
			}

		});
	}
}

/**
 * 将JSON内为NULL的数据转为空串
 * 
 * @param json
 * @returns
 */
function trimJsonOjbect(json) {
	if (isEmpty(json))
		return json;
	for ( var key in json) {
		json[key] = isEmpty(json[key]) ? "" : json[key];
	}
	return json;
}

/**
 * 将JSON数组中为NULL的数据转为空串
 * 
 * @param jsons
 * @returns
 */
function trimJosnArray(jsons) {
	if (isEmpty(jsons))
		return jsons;
	for (var i = 0; i < jsons.length; i++) {
		jsons[i] = trimJsonOjbect(jsons[i]);
	}
	return jsons;
}

/** 获取包含error的组件 */
function getValidateErrorObj(obj, index) {
	if (isEmpty(obj) || obj.length == 0)
		return obj;
	index = isEmpty(index) ? 0 : (++index);
	if (index == 20)// 最多遍历十层
		return obj;
	if (obj.find("[data-error]").length > 0) {
		return obj;
	} else {
		return getValidateErrorObj(obj.parent(), index);
	}
}
/**
 * 重置form表单，清空hidden域的值
 * 
 * @param formId
 */
function resetFormAndClearHidden(formId) {
	var formObj = document.getElementById(formId);
	if (isEmpty(formObj)) {
		return;
	}
	for (var i = 0; i < formObj.elements.length; i++) {
		if (formObj.elements[i].type == "hidden") {
			formObj.elements[i].value = "";
		}
	}
	formObj.reset();
}
/**
 * 清空form所有元素
 * 
 * @param formId
 */
function clearForm(formId) {
	var formObj = document.getElementById(id);
	if (isEmpty(formObj)) {
		return;
	}
	for (var i = 0; i < formObj.elements.length; i++) {
		if (formObj.elements[i].type == "hidden") {
			formObj.elements[i].value = "";
		} else if (formObj.elements[i].type == "text") {
			formObj.elements[i].value = "";
		} else if (formObj.elements[i].type == "password") {
			formObj.elements[i].value = "";
		} else if (formObj.elements[i].type == "radio") {
			formObj.elements[i].checked = false;
		} else if (formObj.elements[i].type == "checkbox") {
			formObj.elements[i].checked = false;
		} else if (formObj.elements[i].type == "select-one") {
			formObj.elements[i].options[0].selected = true;
		} else if (formObj.elements[i].type == "select-multiple") {
			for (var j = 0; j < formObj.elements[i].options.length; j++) {
				formObj.elements[i].options[j].selected = false;
			}
		} else if (formObj.elements[i].type == "textarea") {
			formObj.elements[i].value = "";
		} else if (formObj.elements[i].type == "file") {
			formObj.elements[i].select();
			document.selection.clear();
			// for IE, Opera, Safari, Chrome
			var file = formObj.elements[i];
			if (file.outerHTML) {
				file.outerHTML = file.outerHTML;
			} else {
				file.value = ""; // FF(包括3.5)
			}
		}
	}
}

/** 左补0 */
function lPad(str, bit) {
	if (isEmpty(bit)) {
		bit = 3;
	}
	var countLength = (str + "").length;
	for (var i = bit; i > countLength; i--) {
		str = "0" + str;
	}
	return str;
}

$.fn.serializeJson = function() {
	var serializeObj = {};
	var array = this.serializeArray();
	// var str=this.serialize();
	$(array).each(function() {
		if (serializeObj[this.name]) {
			if ($.isArray(serializeObj[this.name])) {
				serializeObj[this.name].push(this.value);
			} else {
				serializeObj[this.name] = [ serializeObj[this.name], this.value ];
			}
		} else {
			serializeObj[this.name] = this.value;
		}
	});
	return serializeObj;
};

/** 对象和数组的深拷贝 */
function cloneObject(sObj) {
	if (typeof sObj !== "object") {
		return sObj;
	}
	var s = {};
	if (sObj.constructor == Array) {
		s = [];
	}
	for ( var i in sObj) {
		s[i] = cloneObject(sObj[i]);
	}
	return s;
}
