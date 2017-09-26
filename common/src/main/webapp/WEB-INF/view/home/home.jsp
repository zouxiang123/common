<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<script src="${ctx}/assets/js/home/home.js?version=${version}"></script>
<script src="${COMMON_SERVER_ADDR}/framework/autocomplete/jquery.autocomplete.js"></script>
<link rel="stylesheet" type="text/css" href="${COMMON_SERVER_ADDR}/framework/autocomplete/jquery.autocomplete.css" />

<title>学透通智能医疗系统</title>
<script type="text/javascript">
	if(isFromPC()&&!isFromIframe()){
		window.location.href="${ctx}/index.shtml";
	}
	$(function() {
		$(".main").css("margin-top", Math.abs((window.screen.height - 710) / 3) + "px");//设置content高度

		var userAgent = navigator.userAgent.toLowerCase();
		jQuery.browser = {
			version : (userAgent.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [])[1],
			safari : /webkit/.test(userAgent),
			opera : /opera/.test(userAgent),
			msie : /msie/.test(userAgent) && !/opera/.test(userAgent),
			mozilla : /mozilla/.test(userAgent) && !/(compatible|webkit)/.test(userAgent)
		};

		// 先加载数据，并注册
		$.ajax({
			url : ctx + "/searchPersonByName.shtml",
			type : "post",
			dataType : "json",
			success : function(data) {// ajax返回的数据
				if (data) {

					$("#name").autocomplete(data, {
						minChars : 1,
						matchCase : false,// 不区分大小写
						autoFill : false,
						max : 10,
						formatItem : function(row, i, max, term) {
							var v = $("#name").val();
							var nextStepName = "";
							if (row.roleName == "患者") {
								nextStepName = "<span class='pull-right margin-right'>" + (row.nextStepName == null ? "" : row.nextStepName) + "</span>";
							}
							return row.name + nextStepName;
							if (row.id.indexOf(v) >= 0) {
								return row.name + " (" + row.id + ")";
							} else
								return false;
						},
						formatMatch : function(row, i, max) {
							return row.name + " (" + row.id + ")";
						},
						formatResult : function(row) {
							return row.name;
						},
						reasultSearch : function(row, v)// 本场数据自定义查询语法
						// 注意这是我自己新加的事件
						{
							// 自定义在code或spell中匹配
							if (row.data.name.indexOf(v) >= 0) {
								return row;
							} else
								return false;
						}
					});
				}
			}
		});
	});
	
	function isNotNew() {
		if ($("#name").val() == "新增") {
			window.location.href = ctx + "/patient/diagnosis/newPatient.shtml";
			return false;
		}
	}
</script>
</head>
<body>
	<jsp:include page="../common/home_top_nav.jsp"></jsp:include>
	<%-- <jsp:include page="../common/home_left_nav.jsp" flush="true"></jsp:include> --%>
	<div class="container-fluid">
		<div class="row">
			<div class="tip-show"></div>
			<div class="show-description"></div>
    <div class="col-sm-10 col-sm-offset-2 col-md-11 col-md-offset-1 main" style="margin-left:78px !important;">
      <p class="pull-right action-bar"></p>
		<form class="navbar-form search-input-layout" action="search.shtml" method="post" onsubmit="return isNotNew();">
			<input id="name" name="name" type="text" class="form-control search-input" placeholder="患者/医生/护士/新增">
			<ul id="search-result" class="search-result"></ul>
			<button type="submit" class="btn search-button">搜 索</button>
		</form>
      <div class="action-item-layout">
			<div class="action-item" data-permission-key="today_patient">
				<span class="hand" onclick="openUrl('today_patient');">
					<img src="${COMMON_SERVER_ADDR}/assets/img/today-patients.png"><span class="item-label">今日就诊</span><span class="badge badge-index"></span>
				</span>
			</div>
			<div class="action-item" data-permission-key="announcement">
				<span class="hand" onclick="window.location.href='${ ctx}/system/announcement/center.shtml'">
					<img src="${COMMON_SERVER_ADDR}/assets/img/announcement-center.png"><span class="item-label">公告中心</span><span id="announcementCount" class="badge badge-index"></span>
				</span>
			</div>
			<div class="action-item" data-permission-key="contacts">
				<span class="hand" onclick="window.location.href='${ ctx}/system/contacts.shtml'">
					<img src="${COMMON_SERVER_ADDR}/assets/img/mail-list.png"><span class="item-label">通讯录</span><span id="contactsCount" class="badge badge-index"></span>
				</span>
			</div>
			<div class="action-item" data-permission-key="calendar">
				<span class="hand" onclick="window.location.href='${ctx}/calendar/index.shtml'">
					<img src="${COMMON_SERVER_ADDR}/assets/img/schedule-management.png"><span class="item-label">日历日程</span><span id="calendarCount" class="badge badge-index"></span>
				</span>
			</div>
      </div>
      
      <div class="clearfix time-content-layout content-layout1" style="top: 348px;padding-left: 17px;margin-left:-458px;">
		<div class="time-content">
			<div class="user-img-s"></div><div class="user-img-l" style="background: url('${COMMON_SERVER_ADDR}/assets/img/fk.png') center;"></div>
        </div>
        <div class="time-content">
        	<div class="user-img-s"></div><div class="user-img-l" style="background: url('${COMMON_SERVER_ADDR}/assets/img/yytx.png') center;"></div>
        </div>
        <div class="time-content" id="pg"></div>
        <div class="time-content" id="cfzd"></div>
        <div class="time-content" id="qdcf"></div>
        <div class="time-content" id="sj"></div>
        <div class="time-content" id="jchd"></div>
        <div class="time-content" id="txbcjl"></div>
        <div class="time-content" id="xj"></div>
        <div class="time-content"></div>
      </div>
      
      <div class="clearfix time-line-layout" style="top: 400px;margin-left:-420px;">
       	<div class="step"></div>
        <div class="time-line"></div>
        <div class="step"></div>
        <div class="time-line"></div>
        <div class="step"></div>
        <div class="time-line"></div>
        <div class="step"></div>
        <div class="time-line"></div>
        <div class="step"></div>
        <div class="time-line"></div>
        <div class="step"></div>
        <div class="time-line"></div>
        <div class="step"></div>
        <div class="time-line"></div>
        <div class="step"></div>
        <div class="time-line"></div>
        <div class="finish"></div>
        <div class="time-line"></div>
        <div class="finish"></div>
      </div>
      
      <div class="clearfix time-content-layout" style="top: 420px;margin-left:-458px;">
		<div class="time-content">付款</div>
        <div class="time-content">预约透析</div>
        <div class="time-content">透前称量</div>
        <div class="time-content">制定处方</div>
        <div class="time-content">确认处方</div>
        <div class="time-content">透前准备</div>
        <div class="time-content">交叉核对</div>
        <div class="time-content">透中护理</div>
        <div class="time-content">透后护理</div>
        <div class="time-content">完成</div>
	     <%--  <div class="work-time-layout">
	        <div class="work-time-line"></div>
	        <div class="work-time">工作流程  <fmt:formatDate value="${today }" pattern="yyyy/MM/dd"/></div>
	        <div class="work-time-line"></div>
	      </div> --%>
      </div>
      <div class="clearfix time-line2-text-layout" style="top: 520px;">
           <div class="time-line2-text margin-right-34">轻松&nbsp;&nbsp;愉快</div>
           <div class="time-line2-text margin-right-34" style="margin-left: -1px;">高效</div>
           <div class="time-line2-text margin-right-34">易学&nbsp;&nbsp;易用</div>
           <div class="time-line2-text margin-right-34" style="margin-left: -2px;">质控</div>
           <div class="time-line2-text" style="margin-left: -7px;margin-right: 27px;">可追溯</div>
           <div class="time-line2-text">更好&nbsp;更安全</div>
       </div>
       <div class="clearfix time-line2-layout"  style="top: 546px;">
            <div class="step2"></div>
            <div class="time-line2"></div>
            <div class="step2"></div>
            <div class="time-line3"></div>
            <div class="step2"></div>
            <div class="time-line3"></div>
            <div class="step2"></div>
            <div class="time-line2"></div>
            <div class="step2"></div>
            <div class="time-line3"></div>
            <div class="step2"></div>
            <div class="time-line3"></div>
            <div class="step2"></div>
            <div class="time-line3"></div>
            <div class="step2"></div>
            <div class="time-line2"></div>
            <div class="step2"></div>
        </div>
       
      
     	<p class="copyright" style="margin-left: -182px;">©2015 学透通®智能医疗系统</p>
		<jsp:include page="../common/home_footer.jsp" flush="true"></jsp:include>
		
		
    </div>

	<script type="text/javascript">
		$(function(){
			$("#homeNav").addClass("active");// 设置激活tab页

			// 获取流程数据
			/* getProcess();

			setLinkCount(); */
			
	        $(".time-line2-text").mouseover(function() {
	            var left = 144;
	            if ($(this).index() == 0 || $(this).index() == 2 || $(this).index() == 5) {
	                left = 128;
	            }
	
	            if ($(this).index() == 0) {
	                $(".show-description").html("让医生、护士轻松愉快的完成工作");
	                $(".show-description").css("left", $(this).offset().left - left);
	                $(".show-description").css("top", $(this).offset().top - 60);
	                $(".show-description").css("text-align", "center");
	                $(".show-description").append("<div class=\"show_jt\"></div>");
	                $(".show_jt").css("left", 154);
	                $(".show_jt").css("top", 49);
	                $(".show-description").show();
	            } else if ($(this).index() == 1) {
	                $(".show-description").html("流程简单，功能强大，且大量繁琐的文字性输入工作都由软件后台自动生成，减轻了医护人员大量的重复性工作。同时软件还设计有快捷的检索功能，这些都极大的提升了医护人员的劳力生产效率。");
	                $(".show-description").css("left", $(this).offset().left - left);
	                $(".show-description").css("top", $(this).offset().top - 158);
	                $(".show-description").css("text-align", "left");
	                $(".show-description").append("<div class=\"show_jt\"></div>");
	                $(".show_jt").css("top", 149);
	                $(".show-description").show();
	            } else if ($(this).index() == 2) {
	                $(".show-description").html("逻辑关注清晰明了，流程简易、操作简便，每页都有明显的导航条指引。另有“学童”秘书随时随地给您提供及时贴心的秘书服务。");
	                $(".show-description").css("left", $(this).offset().left - left);
	                $(".show-description").css("top", $(this).offset().top - 110);
	                $(".show-description").css("text-align", "left");
	                $(".show-description").append("<div class=\"show_jt\"></div>");
	                $(".show_jt").css("left", 154);
	                $(".show_jt").css("top", 99);
	                $(".show-description").show();
	            } else if ($(this).index() == 3) {
	                $(".show-description").html("本产品严格遵从中国《血透质控标准》进行设计和研发的，流程、操作规范及业务实现均符合严格质控要求。同时本产品还引入了世界卫生组织（WHO）及国际血液透析学会（ISHD）认可、美国医保认可的JCI标准，极大的提升了以“患者为中心”的质控安全和持续改进（PDCA）的质控要求。");
	                $(".show-description").css("left", $(this).offset().left - left);
	                $(".show-description").css("top", $(this).offset().top - 206);
	                $(".show-description").css("text-align", "left");
	                $(".show-description").append("<div class=\"show_jt\"></div>");
	                $(".show_jt").css("top", 199);
	                $(".show-description").show();
	            } else if ($(this).index() == 4) {
	                $(".show-description").html("本产品自动生成和记录患者治疗全流程的数据和事件，并将数据自动存储与备份，数据一旦生成就不可逆转不可修改。数据和事件全程可追溯，并可通过后台“管理日志”进行调阅和查询（必要时可以打印）。");
	                $(".show-description").css("left", $(this).offset().left - left);
	                $(".show-description").css("top", $(this).offset().top - 158);
	                $(".show-description").css("text-align", "left");
	                $(".show-description").append("<div class=\"show_jt\"></div>");
	                $(".show_jt").css("left", 160);
	                $(".show_jt").css("top", 149);
	                $(".show-description").show();
	            } else if ($(this).index() == 5) {
	                $(".show-description").html("使患者得到更好更安全的治疗，提升患者生活品质。");
	                $(".show-description").css("left", $(this).offset().left - left);
	                $(".show-description").css("top", $(this).offset().top - 84);
	                $(".show-description").css("text-align", "left");
	                $(".show-description").append("<div class=\"show_jt\"></div>");
	                $(".show_jt").css("left", 154);
	                $(".show_jt").css("top",74);
	                $(".show-description").show();
	            }
	        }).mouseout( function(){
	            $(".show-description").hide();
	        });
		});
		
		function openUrl(key){
			var permissionObj = getPermissionObjByKey(key);
			if(!isEmpty(permissionObj)){
				var url = "";
				if(isEmpty(permissionObj.url)){
					url = getPermissionUrlByParentCode(permissionObj.code);
				}else{
					url = permissionObj.url;
				}
				if(!isEmpty(url))
					window.location.href = ctx +"/"+url+".shtml";
			}
		}
	</script>
</body>

</html>