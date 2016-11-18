<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp"%>
<title>患者信息</title>
<script type="text/javascript">
	$(function() {
		$(".personal-value").each(function() {
			if ($(this).text() == "") {
				$(this).text("无");
			}
		});
	});
</script>
</head>
<body>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 col-md-12" style="padding:0px;">
				<div class="fill-parent">
					<div class="list-item margin-bottom-20 bg-white">
						<div class="tab-body">
							<div class="fill-parent center">
								<div>
									<c:if test="${!patient.delFlag}">
										<div class="personal-btn" style="margin-right: 5px;" id="btnAdd"
											onclick="window.location.href='${ctx}/patient/patientInfo.shtml?patientId=${patient.id }';" data-permission-key="edit_patient">
											<img src="${ctx}/assets/img/change.png"> <br /> <span class="personal-span" style="margin-left: -6px;">编辑</span>
										</div>
									</c:if>
									<img class="personal-photo" src="${ctx}/images${patient.imagePath }"> <input type="hidden" name="imagePath"
										value="${patient.imagePath }">
									<div class="personal-btn" style="margin-left: 8px;" onclick="$('#barcodeDialog').modal('show');">
										<img src="${ctx}/assets/img/qrcode.png"> <br /> <span class="personal-span">二维码</span>
									</div>
								</div>
								<div class="table-responsive">
									<table class="personal-table personal-table-margin table">
										<tbody>
											<tr>
												<td width="360"></td>
												<td width="150" class="personal-key">姓名：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.name }</td>
												<td></td>
												<td width="150" class="personal-key">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
												<td width="18"></td>
												<td width="200" class="personal-value"><c:if test="${patient.sex=='M'}">男</c:if> <c:if test="${ patient.sex=='F'}">女</c:if></td>
												<td width="300"></td>
											</tr>
											<tr>
												<td width="360"></td>
												<td width="150" class="personal-key">证件类型：</td>
												<td width="18"></td>
												<td width="200" class="personal-value"><c:if test="${ patient.idType=='1'}">身份证号</c:if> <c:if test="${ patient.idType=='2'}">护照</c:if>
													<c:if test="${ patient.idType=='3'}">其它</c:if></td>
												<td></td>
												<td width="150" class="personal-key">证件号码：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.idNumber }</td>
												<td width="300"></td>
											</tr>
											<tr>
												<td width="360"></td>
												<td width="150" class="personal-key">出生日期：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.birthdayShow }</td>
												<td></td>
												<td width="150" class="personal-key">出生地：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.provinceName }${patient.countyName }</td>
												<td width="300"></td>
											</tr>
											<tr>
												<td width="360"></td>
												<td width="150" class="personal-key">详细地址：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.address }</td>
												<td></td>
												<td width="150" class="personal-key">联系方式：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.mobileShow }</td>
												<td width="300"></td>
											</tr>
											
											<tr>
												<td width="360"></td>
												<td width="150" class="personal-key">&nbsp;&nbsp;工作单位：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.workUnit }</td>
												<td></td>
												<td width="150" class="personal-key">长期/临时患者：</td>
												<td width="18"></td>
												<td width="200" class="personal-value"><c:if test="${patient.isTemp }">临时</c:if>
													<c:if test="${patient.isTemp ==false }">长期</c:if></td>
												<td width="300"></td>
											</tr>
											
											<tr>
												<td width="360"></td>
												<td width="150" class="personal-key">&nbsp;&nbsp;ABO血型：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.bloodAbo }</td>
												<td></td>
												<td width="150" class="personal-key">RH(D)血型：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">
													<c:if test="${patient.bloodRh =='1' }">阳性</c:if>
													<c:if test="${patient.bloodRh =='0' }">阴性</c:if>
												</td>
												<td width="300"></td>
											</tr>
											<tr>
												<td width="360"></td>
												<td width="150" class="personal-key">家属姓名：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.emergencyContacts }</td>
												<td></td>
												<td width="150" class="personal-key">家属电话一：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.emergencyMobileShow }</td>
												<td width="300"></td>
											</tr>
											<tr>
												<td width="360"></td>
												<td width="150" class="personal-key">家属电话二：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.emergencyMobileShow2 }</td>
												<td></td>
												<td width="150" class="personal-key">家属电话三：</td>
												<td width="18"></td>
												<td width="200" class="personal-value">${patient.emergencyMobileShow3 }</td>
												<td width="300"></td>
											</tr>

											<c:forEach var="ptCard" items="${patientCardList}" varStatus="status">
												<c:forEach var="obj" items="${medicare_card_type}">
													<c:if test="${obj.value==ptCard.cardType}">
														<c:if test="${status.index%2==0 }">
															<tr>
																<td width="360"></td>
																<td width="150" class="personal-key">${obj.name}：</td>
																<td width="18"></td>
																<td width="200" class="personal-value">${ptCard.cardNo}</td>
														</c:if>
														<c:if test="${status.index%2!=0 }">
															<td></td>
															<td width="150" class="personal-key">${obj.name}：</td>
															<td width="18"></td>
															<td width="200" class="personal-value">${ptCard.cardNo}</td>
															<td width="300"></td>
														</c:if>
														<c:if test="${status.index%2!=0 || status.last}">
															</tr>
														</c:if>
													</c:if>
												</c:forEach>
											</c:forEach>

										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 二维码 -->
	<div class="modal" id="barcodeDialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="modal-message-layout" style="padding-top: 40px">
						<div class="tab-body center">
							<img src="${ctx}/images/${login_user.tenantId }/images/patient/barcode/${patient.id }.png">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
