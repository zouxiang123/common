<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<jsp:include page="../common/daterangepicker_include.jsp"></jsp:include>
<style type="text/css">
	.personal-input1 {
		  font-size: 15px !important;
		  border: 1px solid #dfdfdf;
		  -moz-border-radius: 4px;
		  -webkit-border-radius: 4px;
		  border-radius: 4px;
		  width: 220px;
		  height: 30px;
		  background: #fff;
		  padding: 5px;
		  padding-left: 10px;
		  padding-right: 10px;
		  color: #484848;
		  outline: none;
	}
	
	.personal-input2 {
		  font-size: 15px !important;
		  border: 1px solid #dfdfdf;
		  -moz-border-radius: 4px;
		  -webkit-border-radius: 4px;
		  border-radius: 4px;
		  width: 120px;
		  height: 30px;
		  background: #fff;
		  padding: 5px;
		  padding-left: 10px;
		  padding-right: 10px;
		  color: #484848;
		  outline: none;
	}

	.dialog-btn-style1 {
		  margin-top: 0px;
		  margin-left: 10px;
		  border: 1px solid #31AAFF !important;
		  width: 40px !important;
		  height: 30px !important;
		  color: #31AAFF !important;
		  background: #fff !important;
		  padding: 5px !important;
	}
	
	#main .personal-value {
	  height: 36px;
	}
	
	#main .personal-input2 {
	  padding: 3px;
	}
</style>
	<input type="hidden" id="id" name="id" value="${patient.id }">
	<input type="hidden" id="sysOwner" name="sysOwner" value="${patient.sysOwner }"/>
	<div class="fill-parent">
		<div class="list-item margin-bottom-20 bg-white">
			<div class="tab-body">
				<div class="fill-parent center">
					<div id="imgdiv" style="margin-bottom: -12px;">
						<c:if test="${empty patient.imagePath }">
							<img id="imgShow" class="personal-photo hand" onclick="up_img.click();" src="${COMMON_SERVER_ADDR}/assets/img/default-user.png">
						</c:if>
						<c:if test="${!empty patient.imagePath }">
							<img id="imgShow" class="personal-photo hand" onclick="up_img.click();" src="${ctx}/images${patient.imagePath }">
						</c:if>
						<img class="personal-photo camera hand" onclick="up_img.click();" src="${COMMON_SERVER_ADDR}/assets/img/camera.png">
					</div>
					<input type="hidden" value="${patient.imagePath}">
					<div class="table-responsive">
						<table class="personal-table personal-table-margin table">
							<tbody>
								  <tr>
								  		<td width="300"></td>
								  		<td width="150" class="personal-title">&nbsp;&nbsp;卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
										<td width="10"></td>
								  		<td width="500" colspan="2"  class="personal-value">
								  			<table id = "main">
								  					<tr>
														<td class="personal-value">
												        	<select class="personal-input2" id="patientForm_CardType" name="patientCardList[0].cardType">
																	<c:forEach var="obj" items="${medicare_card_type}" varStatus="status">
																		<option value="${obj.itemCode }" <c:if test="${obj.isChecked }">selected="selected"</c:if> >${obj.itemName}</option>
																	</c:forEach>
															</select>
												        	<input type="text"  placeholder="请输入卡号 " class="personal-input1" id="cardNo0" name="patientCardList[0].cardNo" value=""  />
														</td>
														<td  align="left">
																&nbsp;&nbsp;&nbsp;
															<img  id="queryPatientInfo" class="dialog-delete" style="width: 22px;height: 22px;" name ="imgDel_1" src="${COMMON_SERVER_ADDR}/assets/img/query.png"  />
																&nbsp;
															<img  id="addText" class="dialog-delete" style="width: 22px;height: 22px;" name ="imgDel_0" src="${COMMON_SERVER_ADDR}/assets/img/patient_add.png"  />
														</td>
											        </tr>
											        
											   		 <c:forEach var="ptCard" items="${patientCardList}" varStatus="st">
											   		 		<tr>
																<td  class="personal-value">
														   		 		<span class="personal-title" style="min-width:60px"></span>
														   		 		<select class="personal-input2" id="patientCardList[${ st.index + 10}].cardType" name="patientCardList[${ st.index + 10}].cardType">
												   		 					<c:forEach var="obj" items="${medicare_card_type}" varStatus="status">
																				<option value="${obj.itemCode }" <c:if test="${obj.itemCode== ptCard.cardType }">selected="selected"</c:if> >${obj.itemName}</option>
																			</c:forEach>
																		</select> 
																		<input type="hidden" value="${ptCard.delFlag}" name="patientCardList[${ st.index + 10}].delFlag" >
																		<input type="hidden"  name="patientCardList[${ st.index + 10}].id" value="${ptCard.id}">
																		<input type="text"  placeholder="请输入卡号 " class="personal-input1" id="cardNo${ st.index + 10}" name="patientCardList[${ st.index + 10}].cardNo" value="${ptCard.cardNo}"  /> 
																</td>
																<td align="left">&nbsp;&nbsp;&nbsp;&nbsp;
																		<img class="dialog-delete" name ="imgDel_${st.index + 10}" src="${COMMON_SERVER_ADDR}/assets/img/dialog-delete.png" onclick="removeSaveCardNos(this,${ st.index + 10})" />
																</td>
													        </tr>										
													</c:forEach>
								  			</table>
								  		</td>
								  </tr>
								<tr>
									<td width="300"></td>
									<td width="150" class="personal-title">*&nbsp;&nbsp;姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
									<td width="10"></td>
									<td width="200" class="personal-value"><input type="text" class="personal-input" id="patientForm_name" name="name"
										value="${patient.name }" placeholder="请输入姓名" maxlength="18"/></td>
									<td width="300"></td>
								</tr>
								<tr>
									<td width="300"></td>
									<td width="150" class="personal-title">*&nbsp;&nbsp;性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
									<td width="10"></td>
									<td width="200" class="personal-value">
										<div class="item-box">
											<input id="patientForm_male" type="radio" name="sex" value="M" <c:if test="${ patient.sex=='M'}">checked</c:if> /> <label
												for="patientForm_male" class="form-span form-item-label">男</label>
										</div>
										<div class="item-box">
											<input id="patientForm_female" type="radio" name="sex" value="F" <c:if test="${ patient.sex=='F'}">checked</c:if> /> <label
												for="patientForm_female" class="form-span form-item-label">女</label>
										</div>
									</td>
									<td width="300"></td>
								</tr>
								
								<tr>
									<td width="300"></td>
									<td width="150" class="personal-title">*&nbsp;&nbsp;证件类型：</td>
									<td width="10"></td>
									<td width="200" class="personal-value">
										<select class="personal-input" id="patientForm_idType" name="idType">
											<option value="1" <c:if test="${ patient.idType=='1'}">selected</c:if> >身份证</option>
											<option value="2" <c:if test="${ patient.idType=='2'}">selected</c:if> >护照</option>
											<option value="3" <c:if test="${ patient.idType=='3'}">selected</c:if> >其它</option>
										</select>
									</td>
									<td width="300"></td>
								</tr>
								<tr>
									<td width="300"></td>
									<td width="150" class="personal-title">*&nbsp;&nbsp;证件号码：</td>
									<td width="10"></td>
									<td width="200" class="personal-value"><input type="text" class="personal-input" id="patientForm_id_number" name="idNumber"
										value="${patient.idNumber }" placeholder="请输入证件号码" maxlength="18" /></td>
									<td width="300"></td>
								</tr>
								<tr>
									<td width="300"></td>
									<td width="150" class="personal-title">*&nbsp;&nbsp;出生日期：</td>
									<td width="10"></td>
									<td width="200" class="personal-value">
									 <div id= "birthdayShow_div_id">
									 <select id="YYYY" class="selectpicker">
										</select><span class="personal-form-span form-span">年</span> <select id="MM" class="selectpicker">
										</select><span class="personal-form-span form-span">月</span> <select id="DD" class="selectpicker">
										</select><span class="personal-form-span form-span">日</span>
									</div>
									<input type="text" id="patientForm_birthday" name="birthdayShow" value="${patient.birthdayShow}" /></td>
									<td width="300"></td>
								</tr>
								<tr>
									<td width="300"></td>
									<td width="150" class="personal-title">&nbsp;&nbsp;出生地：</td>
									<td width="10"></td>
									<td width="200" class="personal-value"><select name="province" id="province" class="selectpicker">
											<option value="0">--</option>
											<c:forEach var="data" items="${provinceList}">
												<option value="${data.id }" <c:if test="${data.id == patient.province}">selected</c:if>>${data.name }</option>
											</c:forEach>
									</select><span class="personal-form-span form-span">省份</span> <select name="county" id="county" class="selectpicker">
											<option value="0">--</option>
											<c:forEach var="data" items="${countyList}">
												<option value="${data.id }" <c:if test="${data.id == patient.county}">selected</c:if>>${data.name }</option>
											</c:forEach>
									</select><span class="personal-form-span form-span">县区</span></td>
									<td width="300"></td>
								</tr>
								<tr>
									<td width="300"></td>
									<td width="150" class="personal-title">*&nbsp;&nbsp;详细地址：</td>
									<td width="10"></td>
									<td width="200" class="personal-value"><input type="text" class="personal-input" name="address"
										value="${patient.address }" placeholder="请输入详细地址" maxlength="64"/></td>
									<td width="300"></td>
								</tr>
								<tr>
									<td width="300"></td>
									<td width="150" class="personal-title">*&nbsp;&nbsp;联系方式：</td>
									<td width="10"></td>
									<td width="200" class="personal-value"><input type="text" class="personal-input" id="patientForm_mobile" name="mobile"
										value="${patient.mobile }" placeholder="请输入联系方式 " /></td>
									<td width="300"></td>
								</tr>
								<tr>
									<td width="300"></td>
									<td width="150" class="personal-title">&nbsp;&nbsp;工作单位：</td>
									<td width="10"></td>
									<td width="200" class="personal-value"><input type="text" class="personal-input" id="patientForm_workUnit" name="workUnit"
										value="${patient.workUnit }" placeholder="请输入工作单位" maxlength="64"/></td>
									<td width="300"></td>
								</tr>
								<tr>
									<td width="300"></td>
									<td width="150" class="personal-title">长期/临时患者：</td>
									<td width="10"></td>
									<td width="200" class="personal-value" style="padding-top:0px;">
										<div class="group-radio" style="padding-top:15px; margin-bottom:-15px;margin-left:-35px;">
											<input type="radio" id="patientForm_isTemp0" name="isTemp" value="0" <c:if test="${ patient.isTemp == false ||empty patient.isTemp}">checked</c:if> />
											<label for="patientForm_isTemp0" class="form-span form-item-label">长期</label>
											<input type="radio" id="patientForm_isTemp1" name="isTemp" value="1" <c:if test="${ patient.isTemp}">checked</c:if> /> 
											<label for="patientForm_isTemp1" class="form-span form-item-label">临时</label>
										</div>
									</td>
									<td width="300"></td>
								</tr>
								<tr>
									<td width="300"></td>
									<td width="150" class="personal-title">血型：</td>
									<td width="10"></td>
									<td width="200" class="personal-value" style="padding-top:0px;">
										<div class="group-radio" style="padding-top:15px; margin-bottom:-15px;margin-left:-35px;">
											<input type="radio" id="patientForm_bloodAbo1" name="bloodAbo" value="A" 
													<c:if test="${ patient.bloodAbo == 'A'}">checked</c:if> />
											<label for="patientForm_bloodAbo1" class="form-span form-item-label">A</label>
											
											<input type="radio" id="patientForm_bloodAbo2" name="bloodAbo" value="B"
											 		<c:if test="${ patient.bloodAbo== 'B'}">checked</c:if> /> 
											<label for="patientForm_bloodAbo2" class="form-span form-item-label">B</label>
											
											<input type="radio" id="patientForm_bloodAbo3" name="bloodAbo" value="AB"
											 		<c:if test="${ patient.bloodAbo== 'AB'}">checked</c:if> /> 
											<label for="patientForm_bloodAbo3" class="form-span form-item-label">AB</label>
											
											<input type="radio" id="patientForm_bloodAbo4" name="bloodAbo" value="O"
											 		<c:if test="${ patient.bloodAbo== 'O'}">checked</c:if> /> 
											<label for="patientForm_bloodAbo4" class="form-span form-item-label">O</label>
											
											<input type="radio" id="patientForm_bloodRh1" name="bloodRh" value="1"
											 		<c:if test="${ patient.bloodRh== '1'}">checked</c:if> /> 
											<label for="patientForm_bloodRh1" class="form-span form-item-label">RH+</label>
											
											<input type="radio" id="patientForm_bloodRh2" name="bloodRh" value="0"
											 		<c:if test="${ patient.bloodRh== '0'}">checked</c:if> /> 
											<label for="patientForm_bloodRh2" class="form-span form-item-label">RH-</label>
										</div>
									</td>
									<td width="300"></td>
								</tr>
								
								<tr>
									<td width="300"></td>
									<td width="150" class="personal-title">家属姓名：</td>
									<td width="10"></td>
									<td width="200" class="personal-value"><input type="text" class="personal-input" id="patientForm_emergencyContacts" name="emergencyContacts"
										value="${patient.emergencyContacts }" placeholder="请输入家属姓名 " /></td>
									<td width="300"></td>
								</tr>
								<tr>
									<td width="300"></td>
									<td width="150" class="personal-title">家属电话一：</td>
									<td width="10"></td>
									<td width="200" class="personal-value"><input type="text" class="personal-input" id="patientForm_emergencyMobile" name="emergencyMobile"
										value="${patient.emergencyMobile }" placeholder="请输入家属电话 " /></td>
									<td width="300"></td>
								</tr>
								<tr>
									<td width="300"></td>
									<td width="150" class="personal-title">家属电话二：</td>
									<td width="10"></td>
									<td width="200" class="personal-value"><input type="text" class="personal-input" id="patientForm_emergencyMobile2" name="emergencyMobile2"
										value="${patient.emergencyMobile2 }" placeholder="请输入家属电话 " /></td>
									<td width="300"></td>
								</tr>
								<tr>
									<td width="300"></td>
									<td width="150" class="personal-title">家属电话三：</td>
									<td width="10"></td>
									<td width="200" class="personal-value"><input type="text" class="personal-input" id="patientForm_emergencyMobile3" name="emergencyMobile3"
										value="${patient.emergencyMobile3 }" placeholder="请输入家属电话 " /></td>
									<td width="300"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="${COMMON_SERVER_ADDR}/framework/jquery/1.11.3/jquery.form.js"></script>
	<script src="${COMMON_SERVER_ADDR}/framework/uploadPreview.min.js"></script>
	<script src="${COMMON_SERVER_ADDR}/assets/js/common/birthday.js?version=${version}"></script>
	<script src="${ctx }/assets/js/patient/edit_patient_basic.js?version=${version}"></script>
	<script type="text/javascript">
	var cards_i = 1;
	$(function() {
		$("#birthdayShow_div_id").hide();//隐藏div  
		
		$("#patientForm_birthday").daterangepicker({
			"singleDatePicker": true,
		    "showDropdowns": true,
		    "locale": {
		    	format:"YYYY-MM-DD"
		    }
		});
		
        $('#addText').click(function() {
            if (cards_i < 9) {
				var html ="";
		        	html+='<tr>';
		        	html+='<td class="personal-value">';
		        	html+='<select class="personal-input2" id="patientForm_CardType" + cards_i + "" name="patientCardList['+cards_i+'].cardType"> ';
		        	html+='<c:forEach var="obj" items="${medicare_card_type }" varStatus="status">';
		        	html+='<option value="${obj.itemCode }" <c:if test="${obj.isChecked }">selected="selected"</c:if> >${obj.itemName }</option></c:forEach></select>';
		            html+='&nbsp;<input type="text" placeholder="请输入卡号 " class="personal-input1"  name="patientCardList['+cards_i+'].cardNo" + cards_i + ""/> ';
		            html+='</td>';
		            html+='<td align="left">';
		            html+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img class="dialog-delete" name ="imgDel_'+ cards_i +'" src="' + ctx + '/assets/img/dialog-delete.png" onclick="removeAddCardNos(this)"> ';
		            html+='</td>';
		            html+='</tr>';	
		            $("#main").append(html);
                cards_i++;
            } else {
            	showAlert("最多只能添加8个");
            }
        });  
        
     	$('#queryPatientInfo').click(function() {
        	$.ajax({
				url : ctx + "/patient/wsQueryPatientInfo.shtml",
				data : {
					cardNo: $("#cardNo0").val()
		        },
				type : "post",
				dataType : "json",
				loading : true,
				success : function(data) {// ajax返回的数据
					if (data) {
						var pt = data.patient;
						//向页面填充数据
						mappingFormData(pt, "patientForm");
					}
					return false;
				}
			});
		});
     	
	});
	
	function removeAddCardNos(element) {
		var parentElement = $(element).parent().parent();
		parentElement.remove();
		cards_i--;
	}
	
	function removeSaveCardNos(element,num) {
		var parentElement = $(element).parent().parent();
		parentElement.find("input[name$='delFlag']").val(0);
		parentElement.hide();
	}

</script>
	
