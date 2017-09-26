<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>学透通管理中心</title>
<script type="text/javascript" src="${ctx }/assets/js/assay/patient_assay_record_maintain.js"></script>

</head>
<body>
 	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12 main bg-white" style="padding: 0px;">
                <jsp:include page="assay_config_head.jsp"/>
				<div class="tab-header">
						<span class="tip-line" style="background: #31AAFF;"></span> <span class="tab-title">化验单模板</span>
						<div class="tab-action hand margin-right-25" onclick="showAddDialog('addGroupDictHospital');" >
								<div class="dividing-line"></div>
								<div class="new-btn"></div>
								<span class="pull-right action-name">添加化验单名称</span>
						</div>
                        <div class="tab-action hand margin-right-25" style="display: none;" id="dictHospital" onclick="showAddDialog('addDictHospital');" >
                                <div class="dividing-line"></div>
                                <div class="new-btn"></div>
                                <span class="pull-right action-name">添加化验项</span>
                        </div>
				</div>
				<div class="tab-body">
				<form id="updateDictionaryForm">
					<div class="table-responsive bg-white">
						<table class="table" style="padding-left: 250px;" >
							<thead>
								<tr>
									<th class="text-left">化验单名称</th>
									<th class="text-left">项目名称</th>
									<th class="text-left">项目代号</th>
									<th class="text-left">最小值/最大值</th>
									<th class="text-left">单位</th>
									<th class="text-left">参考值</th>
									<th class="text-left">结果值类型</th>
									<th></th>
								</tr>
							</thead>
							<tbody id="loadInit">
								<c:forEach var="item" items="${allDictHospitalLabPO}" varStatus="it">
									<tr class="table-default">
										<td>
                                            <input type="hidden" id="oldItemCode${it.index}" name="oldItemCode" value="${item.itemCode }">
                                            <input type="hidden" id="groupId${it.index}" name="groupId" value="${item.groupId }">
											<input type="text" id="groupName${it.index}" class="tl-input pull-left"  readonly="readonly" style="width: 150px;"
												value="${item.groupName }" />
										</td>
										<td>
											<input type="text" id="itemName${it.index}" class="tl-input pull-left"
												value="${item.itemName }" />
										</td>
										<td>
											<input type="text" id="itemCode${it.index}" class="tl-input pull-left" 
												value="${item.itemCode }" />
										</td>
										<td>
											<input type="text" id="minValue${it.index}" class="tl-input pull-left" 
												value="${item.minValue}" />
											<input type="text" id="maxValue${it.index}" class="tl-input pull-left" 
											value="${item.maxValue }" />
										</td>
										<td>
											<input type="text" id="unit${it.index}" class="tl-input pull-left" 
												value="${item.unit }" />
										</td>
										<td>
											<input type="text" id="reference${it.index}" class="tl-input pull-left" 
												value="${item.reference }" />
										</td>
										<td>
											<select style="width:120px;height:24px;border-radius:0px;-webkit-appearance:block;" id="valueType${it.index}">
											 	<option value="1" <c:if test="${item.valueType==1 }">selected="selected"</c:if>>数字</option>
												<option value="2" <c:if test="${item.valueType==2 }">selected="selected"</c:if>>文本</option>		
							         		</select>
										</td>
										<td class="text-right">
											<input type="button" value="更新" data-id="${item.id}" data-index='${it.index}' onclick="updateAssay(this);" class="btn btn-def" />
											<input type="button" value="删除" data-id="${item.id}" data-index='${it.index}' onclick="removeAssayMaintain(this);" class="btn btn-def" />
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 新建项目数据 dialog -->
				    <div class="modal" id="addDictHospital" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
				      <div class="modal-dialog">
				        <div class="modal-content" style="min-width:600px;">
				       	<form id="addDictionaryForm" method="post" onsubmit="return false;">
				          <div class="modal-header">
				            <h4 class="modal-title">化验单模板</h4>
				          </div>
				          <div class="modal-body">
				         	 <div class="dialog-wrap" style="width: inherit;">
                    			<div class="list-group bg-white">
                    				<div class="list-group-item margin-top-10">
					         	 	  <span class="list-group-item-title">化验单名称:</span>
                                        <select style="width:120px;height:24px;border-radius:0px;-webkit-appearance:block;" name="groupId" id="groupId">
                                        </select>
					         	 	  <div data-error></div>
						            </div>
                    				<div class="list-group-item">
					         	 	  <span class="list-group-item-title">项目名称&nbsp;&nbsp;:</span>
					         	 	  <input class="input-style width-300"  type="text"  name="itemName"/>
					         	 	  <div data-error></div>
						            </div>
						            <div class="list-group-item">
					         	 	  <span class="list-group-item-title">项目代号&nbsp;&nbsp;:</span>
					         	 	  <input class="input-style width-300"  type="text"  name="itemCode" />
					         	 	  <div data-error></div>
						            </div>
						            <div class="list-group-item margin-top-10">
					         	 	  <span class="list-group-item-title">最小值&nbsp;&nbsp;&nbsp;:</span>
					         	 	  <input class="input-style width-300" id="addMinValue"  type="text"  name="minValue" />
					         	 	  <div data-error></div>
						            </div>
						            <div class="list-group-item margin-top-10">
					         	 	  <span class="list-group-item-title">最大值&nbsp;&nbsp;&nbsp;:</span>
					         	 	  <input class="input-style width-300" id="addMaxValue" type="text"  name="maxValue" />
					         	 	  <div data-error></div>
						            </div>
						            <div class="list-group-item margin-top-10">
					         	 	  <span class="list-group-item-title">单位&nbsp;&nbsp;&nbsp;&nbsp;:</span>
					         	 	  <input class="input-style width-300"  type="text" id="addUnit"  name="unit" />
						            </div>
						            <div class="list-group-item margin-top-10">
					         	 	  <span class="list-group-item-title">参考值&nbsp;&nbsp;&nbsp;:</span>
					         	 	  <input class="input-style width-300"  type="text"  id="addReference" name="reference" />
						            </div>
						            <div class="list-group-item margin-top-10">
					         	 	  <span class="list-group-item-title">结果值类型:</span>
					         	 	  <select name="valueType" style="width:120px;height:24px;border-radius:0px;-webkit-appearance:block;">
					         	 	  	<option value="1">数字</option>
					         	 	  	<option value="2">文本</option>
					         	 	  </select>
						            </div>
				              </div>
				          </div>
				          <input type="hidden" name="isAuto" value="false">
				          <div class="modal-footer">
				            <span class="dialog-btn-close dialog-btn-size" data-dismiss="modal">取消</span>
				            <span id="addCheck" class="dialog-btn-ok dialog-btn-size"  onclick="addAssayMaintain();">确定</span>
				          </div>
					    </form>
				        </div>
				      </div>
				    </div> 
                    </div>
                    
                    <div class="modal" id="addGroupDictHospital" role="dialog" aria-hidden="true" data-backdrop="static" data-keyboard="false">
                      <div class="modal-dialog">
				        <div class="modal-content" style="min-width:600px;">
				       	<form id="addGroupDictionaryForm" method="post" onsubmit="return false;">
				          <div class="modal-header">
				            <h4 class="modal-title">化验单模板</h4>
				          </div>
				          <div class="modal-body">
				         	 <div class="dialog-wrap" style="width: inherit;">
                    			<div class="list-group bg-white">
                    				<div class="list-group-item">
					         	 	  <span class="list-group-item-title">化验单名称&nbsp;&nbsp;:</span>
					         	 	  <input class="input-style width-300"  type="text"  name="groupName" maxlength="20"/>
					         	 	  <div data-error></div>
						            </div>
						            
				              </div>
				          </div>
				          <input type="hidden" name="isAuto" value="false">
				          <div class="modal-footer">
				            <span class="dialog-btn-close dialog-btn-size" data-dismiss="modal">取消</span>
				            <span id="addCheck" class="dialog-btn-ok dialog-btn-size"  onclick="addGroupAssayMaintain();">确定</span>
				          </div>
					    </form>
				        </div>
				      </div>
				    </div> 
</body>
</html>