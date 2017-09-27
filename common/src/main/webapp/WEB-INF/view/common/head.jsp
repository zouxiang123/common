<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.xtt.common.util.ContextAuthUtil"%>
<%@ page import="com.xtt.common.constants.CommonConstants"%>
<%@ page import="com.xtt.common.constants.SysParamConstants"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<%	
	Map<String, Object> authMap = ContextAuthUtil.getAuth();
	if(authMap != null){
		request.setAttribute(CommonConstants.LOGIN_USER, authMap.get(CommonConstants.LOGIN_USER));
		request.setAttribute(CommonConstants.USER_PERMISSION, authMap.get(CommonConstants.USER_PERMISSION));
		request.setAttribute(CommonConstants.USER_NON_PERMISSION, authMap.get(CommonConstants.USER_NON_PERMISSION));
	}
%>
<meta charset="utf-8"/>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<meta name="author" content=""/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta content="telephone=no,email=no" name="format-detection" />

<link rel="bookmark" href="${COMMON_SERVER_ADDR}/assets/img/logo_ico.png">
<link rel="icon" href="${COMMON_SERVER_ADDR}/assets/img/logo_ico.png">
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="${COMMON_SERVER_ADDR}/framework/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- styles -->
<link href="${COMMON_SERVER_ADDR}/assets/css/styles.css?v=${version }" rel="stylesheet">
<link href="${COMMON_SERVER_ADDR}/assets/css/ui/global.css?v=${version }" rel="stylesheet">
<link href="${COMMON_SERVER_ADDR}/assets/css/ui/index.css?v=${version }" rel="stylesheet">
<link href="${COMMON_SERVER_ADDR}/assets/css/ui/skin.css?v=${version }" rel="stylesheet">
<link href="${COMMON_SERVER_ADDR}/assets/css/custom.css?v=${version }" rel="stylesheet">

<!-- framework JavaScript -->
<script src="${COMMON_SERVER_ADDR}/framework/jquery/1.11.3/jquery.min.js"></script>
<script src="${COMMON_SERVER_ADDR}/framework/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${COMMON_SERVER_ADDR}/framework/jquery/1.11.3/jquery.json.min.js"></script>
<script src="${COMMON_SERVER_ADDR}/framework/jquery/1.11.3/jquery.validate.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="${COMMON_SERVER_ADDR}/assets/js/common/ie10-viewport-bug-workaround.js"></script>
<!--[if lte IE 9 ]>
	<script src="${COMMON_SERVER_ADDR}/framework/json2/json2.min.js"></script>
<![endif]-->

<!-- Custom JavaScript -->
<script src="${COMMON_SERVER_ADDR}/assets/js/common/common-util.js"></script>

<!-- loading div -->
<div id="loading_center" class="loading center hide">
	<img src="${COMMON_SERVER_ADDR}/assets/img/loading.png"><p>正在加载中...</p>
</div>
<div id="loading_other" class="loading center" style="display: none;position: fixed;">
    <img src="${COMMON_SERVER_ADDR}/assets/img/loading.png"><p id="loadingMsg"></p>
</div>
<script>
  if (typeof(loadingShow) == "undefined") {
	  $("#loading_center").removeClass("hide");
  }
</script>

<script type="text/javascript">
	var ctx = "${ctx}";
	if(getCookie("cacheFlag")=="0"){
		$.ajax({
			  url: "${COMMON_SERVER_ADDR}/system/getUserPermissionData.shtml",
			  type: "post",
			  dataType: "json",
			  async:false,
			  success:function(data){
				 if(data.status==1){
					 var storage = window.localStorage;
					 storage.setItem("user_non_permission_list", convertEmpty(data.user_non_permission));
					 storage.setItem("user_permission_list",convertEmpty(data.user_permission));
					 setCookie("cacheFlag","1");
				 }else{
					 alert("用户登录信息失效，或登录信息不存在");
				 }
			  }
			});
	}
	 if (typeof(loadingShow) == "undefined"){
	  	var loading_start_time = new Date().getTime();
		$(window).load(function(){
	  		var loading_time = new Date().getTime() - loading_start_time;
	  		if (loading_time < 400) {
	  	       setTimeout(function() {
	  	    	   $("#loading_center").animate({ opacity: 0}, {duration: 200, done: function() { $("#loading_center").addClass("hide"); }});
	  	       }, 400 - loading_time);		
	  		} else {
	  			$("#loading_center").animate({ opacity: 0}, {duration: 200, done: function() { $("#loading_center").addClass("hide"); }});
	  		}
	  	});
	 }
</script>
<script src="${COMMON_SERVER_ADDR}/assets/js/common/common.js?version=${version}"></script>
<script src="${COMMON_SERVER_ADDR}/assets/js/common/common-validate.js"></script>
<script src="${COMMON_SERVER_ADDR}/assets/js/common/common-validate-message-cn.js"></script>
<script src="${COMMON_SERVER_ADDR}/assets/js/common/system-dialog.js?version=${version}"></script>

<script src="${COMMON_SERVER_ADDR}/assets/js/common/go-back.js?version=${version}"></script>

<!-- 系统提示dialog -->
    <div class="modal" id="SystemDialog" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
           <div class="modal-message-layout">
            <div id="modal-icon" class="modal-icon"></div>
            <span class="modal-message modal-messages"></span>
           </div>
          </div>
          <div class="modal-footer">
            <span class="dialog-btn-close dialog-btn-size" data-dismiss="modal">取消</span>
            <span class="dialog-btn-ok dialog-btn-size">确定</span>
          </div>
        </div>
      </div>
    </div>
<!-- 系统提示dialog -->