<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	var loadingShow = true;
</script>    
<%@ include file="../common/head.jsp"%>
<title>学透通®血透智能系统</title>
</head>
<body onresize="resizeFrame();">
<jsp:include page="../common/home_top_nav.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
		<img class="logo-pc" src='${COMMON_SERVER_ADDR}/common/showImage.shtml?fileName=/logo.jpg'></img>
        
		<div class="sidebar" style="width: 172px;"> 
			<ul class="nav nav-sidebar new-sidebar">
				<li class="logo"></li>
				<li id="closediv">
                    <div id="main-close" class="new-nav-item" style="height: 30px;background-color: rgba(255, 255, 255, .05);border-left: 0px;">
                        <img src="assets/img/main-s.png" class="main-close">
                    </div>
                </li>
				<li id="homeNav">
                    <div class="new-nav-item" onclick="openBody('/home.shtml');" index="1">
                        <img src="${COMMON_SERVER_ADDR}/assets/img/nav-main.png">
                        <span>首页</span>
                    </div>
                </li>
                <li>
                    <div class="new-nav-item">
                        <img src="${COMMON_SERVER_ADDR}/assets/img/nav-myworkstation.png">
                        <span>我的工作站</span>
                        <span class="navbar-more-side" id="workstation" style="display:none;"></span>
                        <div class="pull-right nav-open" style="background: none;"></div>
                    </div>
                    <div>
                        <div class="new-nav-sub-item active" data-permission-key="today_patient"  onclick="getOpenUrl(this,'我的工作站');">
                            <img class="main-icon" src="${COMMON_SERVER_ADDR}/assets/img/nav-jrjz.png">
                            <span class="active">今日就诊</span>
                            <div class="navbar-more-subside" id="nowMedical" style="display:none;"></div>
                           <!--  <div class="pull-right nav-sub-arr"></div>
                            <div class="side-tip-show active">
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/今日就诊');" data-permission-key="list_dialysis_evaluate">透前评估<span class="badge new-nav-badge pull-right" id="tqpgCnt"></span></p>
                                <p class="side-tip-show-span" onclick="openBody('/process/waitingDialysisPrescription.shtml','我的工作站/今日就诊');" data-permission-key="waiting_dialysis_prescription">制定处方<span class="badge new-nav-badge pull-right" id="zdcfCnt"></span></p>
                                <p class="side-tip-show-span" onclick="openBody('/process/listDialysisPrescriptionComfirm.shtml','我的工作站/今日就诊');" data-permission-key="list_dialysis_comfirm">确认处方<span class="badge new-nav-badge pull-right" id="qdcfCnt"></span></p>
                                <p class="side-tip-show-span" onclick="openBody('/process/listDialysisCheck.shtml','我的工作站/今日就诊');" data-permission-key="list_dialysis_check">透前准备<span class="badge new-nav-badge pull-right" id="tqzbCnt"></span></p>
                                <p class="side-tip-show-span" onclick="openBody('/process/listDialysisProcessRecord.shtml','我的工作站/今日就诊');" data-permission-key="list_dialysis_process_record">透中护理<span class="badge new-nav-badge pull-right" id="tzhlCnt"></span></p>
                                <p class="side-tip-show-span" onclick="openBody('/process/listDialysisResult.shtml','我的工作站/今日就诊');" data-permission-key="list_dialysis_result">透后护理<span class="badge new-nav-badge pull-right" id="thhlCnt"></span></p>
                                <p class="side-tip-show-span" onclick="openBody('/process/directorCheck.shtml','我的工作站/今日就诊');" data-permission-key="director_check">主任查房<span class="badge new-nav-badge pull-right"></span></p>
                            </div> -->
                        </div>

                        <div class="new-nav-sub-item" data-permission-key="patient_manage" onclick="openBody('/patient/patientEHR.shtml','我的工作站');">
                            <img class="main-icon" src="${COMMON_SERVER_ADDR}/assets/img/nav-dzbl.png">
                            <span>电子病历</span>
                            <!-- <div class="pull-right nav-sub-arr"></div>
                            <div class="side-tip-show">
                                <p class="side-tip-show-span" onclick="openBody('/patient/patientEHR.shtml','我的工作站/电子病历(患者管理)');" data-permission-key="manage_patient_info">患者信息</p>
                                <p class="side-tip-show-span" onclick="openBody('/patient/manage/diagnosisList.shtml','我的工作站/电子病历(患者管理)');" data-permission-key="manage_patient_diagnose">患者诊断</p>
                                <p class="side-tip-show-span" onclick="openBody('/patient/manage/pathwayList.shtml','我的工作站/电子病历(患者管理)');" data-permission-key="manage_patient_pathway">通路管理</p>
                                <p class="side-tip-show-span" onclick="openBody('/patient/manage/complicationList.shtml','我的工作站/电子病历(患者管理)');">并发症管理</p>
                                <p class="side-tip-show-span" onclick="openBody('/patient/manage/dialysisList.shtml','我的工作站/电子病历(患者管理)');" data-permission-key="manage_patient_dialysis">透析记录</p>
                                <p class="side-tip-show-span" onclick="openBody('/patient/manage/checkList.shtml','我的工作站/电子病历(患者管理)');" data-permission-key="manage_patient_check">例行检查</p>
                                <p class="side-tip-show-span" onclick="openBody('/patient/manage/wardRoundList.shtml','我的工作站/电子病历(患者管理)');" data-permission-key="ward_round_list">主任查房</p>
                                <p class="side-tip-show-span" onclick="openBody('/patient/manage/medicalOrdersList.shtml','我的工作站/电子病历(患者管理)');" data-permission-key="manage_patient_medical_orders">制定医嘱</p>
                                <p class="side-tip-show-span" onclick="openBody('/patient/manage/outcomeList.shtml','我的工作站/电子病历(患者管理)');" data-permission-key="manage_patient_outcome">患者转归</p>
                                <p class="side-tip-show-span" onclick="openBody('/patient/manage/jciList.shtml','我的工作站/电子病历(患者管理)');" data-permission-key="manage_patient_jci">JCI管理</p>
                            </div> -->
                        </div>

                        <div class="new-nav-sub-item" data-permission-key="data_statistics">
                            <img class="main-icon" src="${COMMON_SERVER_ADDR}/assets/img/nav-tj.png">
                            <span>报表统计</span>
                            <div class="pull-right nav-sub-arr"></div>
                            <div class="side-tip-show">
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/报表统计');" data-permission-key="complication_statistics">并发症信息统计</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/报表统计');" data-permission-key="supplies_statistics">耗材用量统计</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/报表统计');" data-permission-key="drug_statistics">药品用量统计</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/报表统计');" data-permission-key="dialysis_model_statistics">透析例次统计</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/报表统计');" data-permission-key="assay_statistics">化验项统计</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/报表统计');" data-permission-key="patient_statistics">患者信息统计</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/报表统计');" data-permission-key="pathway_report">通路统计</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/报表统计');" data-permission-key="year_evaluation_report">透析充分性统计</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/报表统计');" data-permission-key="outcome_report">转归患者统计</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/报表统计');" data-permission-key="disease_report">阴转阳统计</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/报表统计');" data-permission-key="disinfection_report">消毒统计</p>
                            </div>
                        </div>

                        <div class="new-nav-sub-item" data-permission-key="cost_manage">
                            <img class="main-icon" src="${COMMON_SERVER_ADDR}/assets/img/nav-fygl.png">
                            <span>费用管理</span>
                            <div class="pull-right nav-sub-arr"></div>
                            <div class="side-tip-show">
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/费用管理');" data-permission-key="cost_budget">费用预估</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/费用管理');" data-permission-key="cost_transaction">费用核算</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/费用管理');" data-permission-key="cost_record">费用详情</p>
                            </div>
                        </div>

                        <!-- <div class="new-nav-sub-item" data-permission-key="stock_manage">
                            <span>药品及耗材库房</span>
                            <div class="pull-right nav-sub-arr"></div>
                            <div class="side-tip-show">
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/药品及耗材库房');" data-permission-key="stock_basic">基础数据维护</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/药品及耗材库房');" data-permission-key="stock_supplies">耗材管理</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/库房管理');">耗材管理</p>
                            </div>
                        </div> -->

                        <div class="new-nav-sub-item" onclick="getOpenUrl(this,'我的工作站');" data-permission-key="bed_view">
                            <img class="main-icon" src="${COMMON_SERVER_ADDR}/assets/img/nav-cwgl.png">
                            <span>床位管理</span>
	                    </div>
                        <div class="new-nav-sub-item" onclick="getOpenUrl(this,'我的工作站');" data-permission-key="dialysis_machine">
                            <img class="main-icon" src="${COMMON_SERVER_ADDR}/assets/img/nav-txjgl.png">
                            <span>透析机管理</span>
	                    </div>
                        <div class="new-nav-sub-item" data-permission-key="water_treatment">
                            <img class="main-icon" src="${COMMON_SERVER_ADDR}/assets/img/nav-sclxtgl.png">
                            <span>水处理系统管理</span>
                            <div class="pull-right nav-sub-arr"></div>
                            <div class="side-tip-show">
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/水处理系统管理');" data-permission-key="water_treatment">水处理设备管理</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/水处理系统管理');" data-permission-key="bacteria_endotoxin_detection">细菌与内毒素检测</p>
                            </div>
	                    </div>
                        <div class="new-nav-sub-item" onclick="getOpenUrl(this,'我的工作站');" data-permission-key="bacteria_detection">
                            <img class="main-icon" src="${COMMON_SERVER_ADDR}/assets/img/nav-hjxdgl.png">
                            <span>环境消毒管理</span>
	                    </div>
                        <div class="new-nav-sub-item" onclick="getOpenUrl(this,'我的工作站');" data-permission-key="print">
                            <img class="main-icon" src="${COMMON_SERVER_ADDR}/assets/img/nav-dy.png">
                            <span>打印</span>
	                    </div>
                        <div class="new-nav-sub-item" onclick="window.open('${ctx}/common/showTV.shtml');" data-permission-key="show_tv">
                            <img class="main-icon" src="${COMMON_SERVER_ADDR}/assets/img/nav-tv.png">
                        	<span>大屏投放</span>
	                    </div>
                    </div>
                </li>
                <li>
                    <div class="new-nav-item" onclick="openBody('/virtual/dialysisCenter.shtml');">
                        <img src="${COMMON_SERVER_ADDR}/assets/img/nav-txcenter.png">
                        <span>虚拟透析中心</span>
                        <span class="navbar-more-side" style="display:none;" id="virtualCenter"></span>
                        <!-- <div class="pull-right nav-close"></div> -->
                    </div>
<!--                     <div>
                        <div class="new-nav-sub-item" onclick="getOpenUrl(this,'虚拟透析中心');">
                            <span>候诊室<span class="badge new-nav-badge pull-right" id="waitingRoomCnt"></span></span>
                        </div>
                        <div class="new-nav-sub-item" onclick="getOpenUrl(this,'虚拟透析中心');">
                            <span>透析区<span class="badge new-nav-badge pull-right" id="dialysisRoomCnt"></span></span>
                        </div>
                        <div class="new-nav-sub-item">
                            <span>水处理区</span>
                        </div>
                        <div class="new-nav-sub-item">
                            <span>治疗室</span>
                        </div>
                        <div class="new-nav-sub-item" data-permission-key="stock_manage">
                            <span>药品及耗材库房</span> 
                            <div class="pull-right nav-sub-arr"></div>
                            <div class="side-tip-show">
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'虚拟透析中心/药品及耗材库房');" data-permission-key="stock_basic">基础数据维护</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'虚拟透析中心/药品及耗材库房');" data-permission-key="stock_supplies">耗材管理</p>
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'我的工作站/库房管理');">耗材管理</p>
                            </div>
                        </div>
                        <div class="new-nav-sub-item" id="doctorWorkstation1">
                            <span>医生办公室</span>
                        </div>
                        <div class="new-nav-sub-item" id="nurseWorkstation1">
                            <span>护士办公室</span>
                            <div class="pull-right nav-sub-arr"></div>
                            <div class="side-tip-show">
                                <p class="side-tip-show-span" onclick="getOpenUrl(this,'虚拟透析中心/护士办公室');">透析区</p>
                                <p class="side-tip-show-span" onclick="javascript:showAlert('页面正在建设中');">实时监测</p>
                            </div>
                        </div>
                        <div class="new-nav-sub-item" id="engineerWorkstation1">
                            <span>工程师办公室</span>
                        </div>
                    </div> -->
                </li>

                <li>
                    <div class="new-nav-item" onclick="openBody('/qc.shtml');">
                        <img src="${COMMON_SERVER_ADDR}/assets/img/nav-qc.png">
                        <span>质控监管</span>
                    </div>
                </li>
			</ul>
		</div>
        <div class="col-sm-12 col-md-12 main" style="padding: 15px;">
        	<iframe id="frameBody" src="" style="position:fixed;" frameborder="no" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>
        </div>
    </div>
</div>

<div id="sidebar-tip" class="side-tip-show"></div>
<script type="text/javascript">
	var isExtend = true;
	$(function() {
		if (hasPermission("doctor_workstation")) {
			$("#doctorWorkstation1").click(function() {
				openBody('/process/waitingEvaluate.shtml','医生办公室/我的工作站/今日就诊');
			});
		}
		if (!hasPermission("nurse_workstation")) {
			$("#nurseWorkstation1").html("<span>护士办公室</span>");
		}
		if (hasPermission("engineer_workstation")) {
			$("#engineerWorkstation1").click(function() {
				openBody('/dialysisMachine/dialysisMachineList.shtml','工程师办公室/我的工作站');
			});
		}
		if (window.top != window.self) {
			top.location = self.location;
		}
		resizeFrame();
		addEvents();
		
		$(".new-nav-item").children("div").removeClass("nav-open").addClass("nav-close");
		$(".new-nav-item").next().hide();
		
		$(".new-nav-item").eq(1).children("div").removeClass("nav-close").addClass("nav-open");
		$(".new-nav-item").eq(1).next().show();
		$(".new-nav-item").eq(1).click();
		//getProcessCount();//加载流程的数目
		//setInterval(getProcessCount, 5 * 60 * 1000);
	});
	
	function addEvents(){
		   $(".new-nav-sub-item").mouseover(function() {
                var elements = $(this).find(".side-tip-show");
                if(elements.length>0){
                	$("#sidebar-tip").data("parentElement",this);
	                $("#sidebar-tip").html(elements.clone().html());
	                if (isExtend) {
	                    $("#sidebar-tip").css("top",$(this).offset().top+"px");
	                    $("#sidebar-tip").css("left",$(this).offset().left+187+"px");
	                    $("#sidebar-tip").show();
	                } else {
	                    $("#sidebar-tip").css("top",$(this).offset().top+"px");
	                    $("#sidebar-tip").css("left",$(this).offset().left+47+"px");
	                    $("#sidebar-tip").show();
	                }
                } else {
                	 $("#sidebar-tip").hide();
                     $("#sidebar-tip").empty();
                }
		    }); 
		   $("#sidebar-tip").mouseleave(function() {
				$(this).hide();
                $(this).empty();
	    	});
		   
		   
			$(".new-nav-item:not(#main-close)").click(function(){
				if ($(this).children("div").hasClass("nav-open")) {
					$(this).children("div").removeClass("nav-open").addClass("nav-close");
					$(this).next().hide();
				} else if ($(this).children("div").hasClass("nav-close")){
					$(this).children("div").removeClass("nav-close").addClass("nav-open");
					$(this).next().show();
				} else {
					$(".new-nav-sub-item").removeClass("active");
					//$(".side-tip-show").removeClass("active");
					$(".new-nav-sub-item").children("span").removeClass("active");
					$(".new-nav-item").removeClass("active");
					$(this).addClass("active");
				}
			});
			
			$(".new-nav-sub-item").click(function(){
				if (!$(this).children("div").hasClass("side-tip-show")) {
					$(".new-nav-item,.new-nav-sub-item").removeClass("active");
					//$(".side-tip-show").removeClass("active");
					$(".new-nav-sub-item").children("span").removeClass("active");

					//$(this).parent().parent().children("div").addClass("active");
					$(this).addClass("active");
					$(this).children("span").addClass("active");
				} else {
					return;
				}
			});
			
			$("#sidebar-tip").on("click",".side-tip-show-span",function(){
				$(".new-nav-sub-item,.new-nav-item").removeClass("active");
				//$(".side-tip-show").removeClass("active");
				$(".new-nav-sub-item").children("span").removeClass("active");
				$(this).addClass("active").siblings().removeClass("active");
				var parentObj = $($(this).parent().data("parentElement"));
				parentObj.addClass("active");
				//$(this).parent().parent().parent().parent().children("div").addClass("active");
				parentObj.children("span").addClass("active");
				
			});
			
			$("#main-close").click(function() {
	            isExtend = !isExtend;
	            extendNav();
	        });
	}
	
	function resizeFrame() {
		$("#frameBody").css("top",50);
		if(isExtend){
			$("#frameBody").css("left",172);
			$("#frameBody").css("width",document.documentElement.clientWidth - 172);
		}else{
			$("#frameBody").css("left",46);
			$("#frameBody").css("width",document.documentElement.clientWidth - 46);
		}
		$("#frameBody").css("height",document.documentElement.clientHeight-50);
	}
	
  function extendNav() {
        if (isExtend) {
            $("#main-close img").attr("src", "${COMMON_SERVER_ADDR}/assets/img/main-s.png");
            $(".sidebar").css("width", "172px");
            $("#closediv").css("width", "187px");
        } else {
            $("#main-close img").attr("src", "${COMMON_SERVER_ADDR}/assets/img/main-h.png");
            $("#closediv").css("width", "63px");
            $(".sidebar").css("width", "46px");
        }
        resizeFrame();
    }

	/**设置子节点菜单的数字 */
	function setChildNodeCount(id,count){
		if (isEmpty(count) || count == 0) {
			$("#"+id).html("");
		} else if (count > 99) {
			$("#"+id).html("99+");
		} else {
			$("#"+id).html(count);
		}
	}
	
	/** 获取跳转url链接并打开url */
	function getOpenUrl(element,path){
		var permissionObj = getPermissionObjByKey($(element).attr("data-permission-key"));
		if(!isEmptyObject(permissionObj)){
			var url = "";
			if(!isEmpty(permissionObj.url)){
				url = permissionObj.url;
			}else{
				url = getPermissionUrlByParentCode(permissionObj.code);
			}
			if(!isEmpty(url)){
				url = url + ".shtml";
				openBody(url,path);
			}
		}
	}
	
	function openBody(url,path) {
		//清空已保存的url
		var storage = window.sessionStorage;
		storage.removeItem('urlStack');
		var stackArr = [];
		if(!isEmpty(path)){
			var pathArr = path.split("/");
			for (var i = 0; i < pathArr.length; i++) {
				stackArr.push({
					name : pathArr[i],
					url:null
				});
			}
		}
		storage.setItem("urlStack", JSON.stringify(stackArr));
		$("#frameBody").attr("src",ctx + url);
	}
</script>
</body>
</html>
