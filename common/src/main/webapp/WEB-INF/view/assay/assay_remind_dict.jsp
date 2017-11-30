<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>患者化验提醒配置</title>
<%@ include file="../common/head.jsp"%>
<link rel="stylesheet" href="${COMMON_SERVER_ADDR}/framework/ztree/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="${COMMON_SERVER_ADDR}/framework/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${COMMON_SERVER_ADDR}/framework/ztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${COMMON_SERVER_ADDR}/framework/ztree/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${COMMON_SERVER_ADDR}/framework/jquery/jquery.fastLiveFilter.js"></script>

<style type="text/css">
.ztree {
	margin: 5px 10px 5px 20px;
	border: 1px solid #EDEFF0;
	-webkit-box-shadow:2px 2px 2px 2px #EDEFF0 inset;
	box-shadow:2px 2px 2px 2px #EDEFF0 inset;
	border-radius: 10px;
	background: #fff;
	width:auto;
	height:auto;
	overflow-y: auto;
	overflow-x: auto;
}
.ztree li a input.rename {height:16px;line-height:16px; width:80px; padding:0; margin:0;
  font-size:12px; border:1px #585956 solid; *border:0px}
</style>
 <link rel="stylesheet" href="${COMMON_SERVER_ADDR}/framework/bootstrap/daterangepicker/daterangepicker.css">
<script type="text/javascript" src="${COMMON_SERVER_ADDR}/framework/moment/moment.min.js"></script>
<script type="text/javascript" src="${COMMON_SERVER_ADDR}/framework/bootstrap/daterangepicker/daterangepicker.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12 main bg-white" style="padding: 0px;">
                <!-- 头部标签的  -->
                <jsp:include page="assay_config_head.jsp"/>
				<div class="col-sm-6 bg-white tree-block">
					<div style="margin-top:10px;">
						<ul id="dictHospitalLabTree"  style="height:!important;height:400px;overflow:auto;"  class="ztree"></ul>
					</div>
					<div class="tab-header">
						<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">同类分组维护</span>
						<div class="tab-action" onclick="showAssayGroupDialog();">
							<div class="dividing-line"></div>
							<span>新增同类分组</span><img src="${ctx }/assets/img/add.png" class="refresh">
						</div>
					</div>
					<div class="list-group bg-white border" style="height:200px;width:100%;overflow-x:hidden;overflow-y:auto;">
						<table class="table table-border">
							<thead>
								<th class="text-center" width="70%">分组名</th>
								<th class="text-center" width="30%">操作</th>
							</thead>
							<tbody id="assayGroupList">
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-sm-6 bg-white tree-block" style="height:660px;padding-left:20px;">
					<div style="margin-top:10px;">
						<%-- <div>
							<c:forEach items="${listDictionaryPO}"  var ="obj" >
		           				<label><input type="radio" name="assayClass" value="${obj.value }">${obj.name }</label>
		           			</c:forEach>
						</div> --%>
						<div style="margin-left:-8px;margin-top:10px;" >
							<!-- <ul id="menuTree" class="ztree" style="height:256px; overflow:auto;"></ul> -->
							<div class="tab-header">
                                <span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">化验逾期项维护</span>
                            </div>
							<div class="list-group bg-white border" style="height:550px;max-width:680px;overflow-x:hidden;overflow-y:auto;" id="assayClassDiv">
								<table class="table table-border">
									<thead>
										<th class="text-center" width="30%">化验项</th>
										<th class="text-center" width="30%">所属分类</th>
										<th class="text-center" width="20%">化验默认天数</th>
										<th class="text-center" width="20%">操作</th>
									</thead>
									<tbody id="resultList">
									</tbody>
								</table>
							</div>
							<div style="padding:10px 0px 25px 20px;text-align:center" >
								<input type="button" id="roleSaveBtn" onclick="savePatientAssayClass();" class="btn btn-def" value="保存">
							</div>
						</div>
						
					</div>
				</div>
                <div class="col-sm-12" style="padding:0px;margin-top: 5px;">
                    <div class="tab-content bg-grew" style=" height:300px;">
                        <p style="margin-left:3%"><label>检查提醒规则:</label></p>
                        <p  style="margin-left:3%">
                            <label>统计截止日期：
                            <select name ="endDate" id="endDate">
                                <c:forEach begin="1" end="28" var="item">
                                    <option value="${item}">
                                    <c:if test="${item >= 28 }">月底</c:if>
                                    <c:if test="${item < 28 }">${item }</c:if>
                                    </option>
                                </c:forEach>
                            </select>号</label>
                            <input type="hidden" name="confId" id="confId">
                         </p>
                        <div style="margin:30px 0px 0px 20px" >
                            <input type="button" id="cancleMonBtn"  class="btn btn-def"  onclick="loadPatientAssayMonth();"value="取消">
                            <input type="button" id="saveMonthBtn"  onclick="saveMonthFun();" class="btn btn-def" value="保存">
                        </div>
                    </div>
                </div>
			</div>
		</div>
	</div>
	
	<!-- 同类化验分组dialog -->
	<div class="modal" id="assayGroupDialog" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content" style="min-width: 800px;">
				<form id="assayGroupForm" method="post">
					<input type="hidden" id="assayGroupForm_id" name="id">
					<div class="modal-header">
						<h4 class="modal-title">同类化验分组(OR)</h4>
					</div>
					<div class="modal-body">
						<div class="dialog-wrap" style="width: inherit;">
							<div class="list-group bg-white">
								<div class="form-item-box margin-top-20">
									<span class="min-width-100 form-span text-right">分组名称：</span>
									<input class="input-style" type="text" id="assayGroupForm_name" name="name" />
								</div>
							</div>
						    <div class="rel" style="height: 36px;border-bottom:1px solid rgba(0,0,0,.05);">
						        <img src="${ctx }/assets/img/search-icon.png" class="side-panel-search-bing">
						        <input type="search" class="side-panel-search" placeholder="搜 索" id="assaySearch"/>
						    </div>
							<div class="list-group bg-white" style="min-height:200px;max-height:300px;max-width:680px;">
								<table class="table table-border">
									<thead>
										<th class="text-center" width="5%">选择</th>
										<th width="10%">项目编码</th>
										<th width="20%">项目名称</th>
										<th width="25%">所属组名</th>
										<th width="10%">最小值</th>
										<th width="10%">最大值</th>
										<th width="10%">引用</th>
									</thead>
									<tbody id="assayList">
									</tbody>
								</table>
							</div>
						</div>
					</div>
		            <div class="modal-footer">
		                <div class="center">
		                    <button type="button" class="btn btn-can dialog-button" data-dismiss="modal">关闭</button>
                  				<button type="button" class="btn btn-def dialog-button" onclick="saveAssayGroupConf();">确定</button>
		                </div>
		            </div>
				</form>
			</div>
		</div>
	</div>
<script type="text/javascript" src="${ctx }/assets/js/assay/assay_remind_dict.js"></script>
</body>
</html>
