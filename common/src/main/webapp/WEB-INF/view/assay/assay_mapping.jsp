<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>字典参数维护</title>
<style type="text/css">
.select {
    -moz-border-radius:4px;
    -webkit-border-radius:4px;
    -border-radius:4px;
    border:1px solid #d9e0e6;
    appearance:none;
    -moz-appearance:none;
    -webkit-appearance:none;
    margin-left: 14px;
    height:32px;
    min-width: 180px;
    text-align: center;
    background: url("${ctx}/assets/img/arrow.png") no-repeat scroll right center transparent;
}

tbody tr.active{
	color: #fff !important;
}
tbody tr.active td{
	background: #31AAFF !important;
}

.select::-ms-expand { display: none; }
.input-style{
 	margin-bottom:0px;
}
.dialog-wrap .input-style{
	width:180px !important; 
}
.input-style.width-70{
	width:71px !important; 
	min-width:70px !important; 
}
.input-style.width-300{
	width:300px !important; 
	min-width:0px !important; 
}
</style>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
			<div class="col-sm-12 col-md-12 main bg-white" style="padding: 0px;">
                <!-- 头部标签的  -->
                <jsp:include page="assay_config_head.jsp"/>
                <!-- 化验单 -->
                <div class="col-sm-12 col-md-12" style="padding: 0px;" id="assayMappingDiv">
                    <div class="list-card-item">
                        <div class="tab-header">
                            <span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">未匹配列表</span>
                        </div>
                        <div class="tab-body">
                            <div class="col-sm-6 col-md-6 p-r-0 p-l-0">
                                <input class="nav-search-input fill-parent" style="width:88%;" type="search" placeholder="请根据名称或者编码进行搜索" id="hospAssaySerch">
                                <div class="table-responsive bg-white" style="overflow-x: hidden; overflow-y: auto; max-height: 400px;"
                                    id="hospitalAssayTable">
                                    <table class="table">
                                        <thead>
                                            <th><span>项目代号</span></th>
                                            <th><span>项目名称</span></th>
                                        </thead>
                                        <tbody id="hospitalAssayTableBody">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col-sm-2 col-md-1 p-r-0" style="padding-left: 5px; text-align: center;">
                                <button class="btn btn-def" style="width: 80px;" onclick="assay_mapping.mappingAssayDict();">关联</button>
                            </div>
                            <div class="col-sm-4 col-md-5 p-r-0 p-l-0">
                                <input class="nav-search-input fill-parent" style="width:88%;" type="search" placeholder="请根据名称或者编码进行搜索" id="sysAssaySerch">
                                <div class="table-responsive bg-white" style="overflow-x: hidden; overflow-y: auto; max-height: 400px;" id="sysAssayTable">
                                    <table class="table">
                                        <thead>
                                            <th><span>项目代号</span></th>
                                            <th><span>项目名称</span></th>
                                        </thead>
                                        <tbody id="sysAssayTableBody">
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12 col-md-12" style="padding: 0px;">
                    <div class="list-card-item">
                        <div class="tab-header">
                            <span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">已匹配列表</span>
                        </div>
                        <div class="tab-body">
                            <div class="table-responsive bg-white" style="overflow-x: hidden; overflow-y: auto; max-height: 300px;" id="assayTable">
                                <table class="table">
                                    <thead>
                                        <!-- <th><span>分组编号</span></th> -->
                                        <th><span>项目代号</span></th>
                                        <th><span>项目名称</span></th>
                                        <!-- <th><span>结果类型</span></th> -->
                                        <th><span>参考值</span></th>
                                        <th><span>单位</span></th>
                                        <th><span>关联编码</span></th>
                                        <th><span>关联名称</span></th>
                                        <th></th>
                                    </thead>
                                    <tbody id="assayTableBody">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 化验单 end-->
            </div>
        </div>
    </div>
    <script type="text/javascript" src="${ctx }/framework/jquery/jquery.fastLiveFilter.js"></script>
    <script src="${ctx }/assets/js/assay/assay_mapping.js?version=${version}"></script>
	 <script type="text/javascript">
	 $(function(){
	    assay_mapping.init();
	    setAssayTopActive("mapping");
	 });
	 </script>
</body>
</html>
