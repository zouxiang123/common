<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<style type="text/css">
    .personal-input1 {
          font-size: 15px !important;
          border: 1px solid #b4bdc8;
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
          border: 1px solid #b4bdc8;
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
    .u-btn{
        background:none;
        outline: none;
        border: 1px solid #d9e0e6;
        height: 38px;
        min-width: 80px;
        padding:0px 15px;
        font-size: 17px;
        border-radius: 4px;
        margin-left: 0px !important;
        margin-right: 10px !important;
        margin-top: 5px;
    }
    .u-btn.blue{
        background: #31aaff;
        color: #ffffff;
    }
    .list-group-item .form-item-box{
       margin-left: -8px;
       margin-right: 30px;
    }
    .input-style{
        margin-left: 0px;
    }
    .u-bar-dashed{
        border-bottom: 1px dashed #d9e0e6;
    }
    .dialog-wrap .col-img.vertical-align-middle .user-photo{
        margin-top:-26px !important;
    }
    
</style>
<jsp:include page="../common/daterangepicker_include.jsp"></jsp:include>
    <input type="hidden" id="id" name="id" value="${patient.id }">
   	<input type="hidden" id="sysOwner" name="sysOwner" value="${patient.sysOwner }"/>
    <input type ="hidden" id="checkedStatus" value=""/>
    <input type ="hidden" id="patient_name_edit" value="${patient.name }"/>
    <div class="fill-parent" style="height: 50%;">
        <div class="list-item margin-bottom-20 bg-white">
            <div class="tab-body">
                <div class="fill-parent center">
                    <div id="imgdiv" style="margin-bottom: -12px;">
                        <c:if test="${empty patient.imagePath }">
                            <img id="imgShow" class="personal-photo hand" onclick="$('#up_img').click();" src="${ctx}/assets/img/default-user.png">
                        </c:if>
                        <c:if test="${!empty patient.imagePath }">
                            <img id="imgShow" class="personal-photo hand" onclick="$('#up_img').click();" src="${ctx}/images${patient.imagePath }">
                        </c:if>
                        <img class="personal-photo camera hand" onclick="$('#up_img').click();" src="${ctx }/assets/img/camera.png">
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
                                            <table id="patientCardTable">
                                                <tr id="patientCardDefaultTr">
                                                    <td class="personal-value">
                                                        <select class="personal-input2" id="cardTypeId" name="patientCardList[0].cardType" data-cardType data-clonereplace>
                                                            <c:forEach var="obj" items="${medicare_card_type}" varStatus="status">
                                                                <option value="${obj.value }">${obj.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <input type="text"  placeholder="请输入卡号 " class="personal-input1" name="patientCardList[0].cardNo"  onchange="cardNoChange(this.value);" data-cardNo data-clonereplace/>
                                                        <label class="form-span form-checkbox-label hide" style="min-width:70px !important;" data-newFlagLabel>
                                                            <input name="patientCardList[0].newFlag" value='1' type="radio" data-newFlag data-clonereplace/>
                                                        </label>
                                                          <div  style="height:30px;line-height:30px;display: none;" id="validCardNoDiv"><font style='color:#F8C730;'>卡号未验证</font></div>
                                                       
                                                    </td>
                                                    <td  align="left">
                                                        <img id="queryPatientInfo" class="dialog-delete m-l-10" style="width: 22px;height: 22px;margin-top:-26px;" src="${ctx }/assets/img/query.png" data-query/>
                                                        <img id="addPatientCard" class="dialog-delete m-l-10" style="width: 22px; height: 22px;margin-top:-26px;" src="${ctx }/assets/img/patient_add.png" data-add/>
                                                        <img class="dialog-delete m-l-10 hide" src="${ctx }/assets/img/dialog-delete.png" data-remove/>
                                                    </td>
                                                    <input type="hidden" value="0" name="patientCardList[0].delFlag" data-delFlag data-clonereplace/>
                                                </tr>
                                                
                                                <c:forEach var="ptCard" items="${ptCardList}" varStatus="st">
                                                <tr data-trid="${ptCard.id}">
                                                    <td  class="personal-value">
                                                        <span class="personal-title" style="min-width:60px"></span>
                                                        <select class="personal-input2" id="patientCardList[${ st.index + 1}].cardType" name="patientCardList[${ st.index + 1}].cardType" data-cardType>
                                                            <c:forEach var="obj" items="${medicare_card_type}" varStatus="status">
                                                                <option value="${obj.value }" <c:if test="${obj.value == ptCard.cardType }">selected="selected"</c:if> >${obj.name}</option>
                                                            </c:forEach>
                                                        </select> 
                                                        <input type="hidden" value="${ptCard.delFlag}" name="patientCardList[${ st.index + 1}].delFlag" data-delFlag/>
                                                        <input type="hidden"  name="patientCardList[${ st.index + 1}].id" value="${ptCard.id}" />
                                                        <input type="text"  placeholder="请输入卡号 " class="personal-input1" id="cardNo${ st.index + 1}" name="patientCardList[${ st.index + 1}].cardNo" value="${ptCard.cardNo}" data-cardNo /> 
                                                        <label class="form-span form-checkbox-label <c:if test="${ptCard.cardType ne '03'}">hide</c:if>" style="min-width:70px !important;" data-newFlagLabel>
                                                            <input name="patientCardList[${ st.index + 1}].newFlag" value='1' <c:if test="${ptCard.newFlag }">checked</c:if> data-newFlag type="radio"/>
                                                        </label>
                                                    </td>
                                                    <td align="left">
                                                        <img class="dialog-delete m-l-10" src="${ctx }/assets/img/dialog-delete.png" data-remove="${ptCard.id}" />
                                                    </td>
                                                </tr>
                                                
                                                </c:forEach>
                                            </table>
                                        </td>
                                  </tr>
                                 
                                 <c:choose>
                                    <c:when test="${sysParam.paramValue=='1'}">
                                       <tr>
                                        <td width="300"></td>
                                        <td width="150" class="personal-title">*透析号：</td>
                                        <td width="10"></td>
                                        <td width="200" class="personal-value">
                                            <!-- 允许复用，则使用下拉框选择 -->
                                            <c:if test="${isSerialNumMultiplex}">
                                            <select name="serialNum" class="personal-input2 valid">
                                                <!-- 当前患者使用的序列号 -->
                                                <c:if test="${patient.serialNum != null }">
                                                    <option value="${patient.serialNum}">${patient.serialNum}</option>
                                                </c:if>
                                                <c:if test="${patient.serialNum == null }">
                                                    <option value="${patient.serialNum}">${patient.serialNum}</option>
                                                </c:if>
                                                <!-- 所有未使用的序列号 -->
                                                <c:forEach items="${psnList }" var="item">
                                                    <option value="${item.serialNum }" <c:if test="${patient.serialNum==item.serialNum }"> selected</c:if>  >${item.serialNum }</option>
                                                </c:forEach>
                                            </select>
                                            </c:if>
                                            <!-- 不允许复用，则使用text显示，添加type="hidden"提交 -->
                                            <c:if test="${!isSerialNumMultiplex}">
                                                <c:if test="${patient.serialNum != null}">
                                                    ${patient.serialNum}
                                                    <input type="hidden" name="serialNum" value="${patient.serialNum}"/>
                                                </c:if>
                                                <c:if test="${patient.serialNum == null}">
                                                    ${newestSerialNum}
                                                    <input type="hidden" name="serialNum" value="${newestSerialNum}"/>
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <td width="300"></td>
                                    </tr>
                                    </c:when>
                                </c:choose>
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
                                    <td width="150" class="personal-title">&nbsp;&nbsp;费用类型：</td>
                                    <td width="10"></td>
                                    <td width="200" class="personal-value">
                                        <c:forEach items="${chargeTypes }" var="chargeType">
                                            <div class="item-box">
                                                <label class="form-span form-item-label">
                                                <input type="radio" name="chargeType" value="${chargeType.value }" ${chargeType.value == patient.chargeType?"checked":""}  />${chargeType.name}</label>
                                            </div>
                                        </c:forEach>
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
                                    <input type="text" class="personal-input" id="patientForm_birthday" name="birthdayShow" value="${patient.birthdayShow}" /></td>
                                    <td width="300"></td>
                                </tr>
                                <tr>
                                    <td width="300"></td>
                                    <td width="150" class="personal-title">&nbsp;&nbsp;出生地：</td>
                                    <td width="10"></td>
                                    <td width="200" class="personal-value">
                                        <select name="province" id="province" class="personal-input" style="width: 130px;">
                                            <option value="0">--</option>
                                            <c:forEach var="data" items="${provinceList}">
                                                <option value="${data.id }" <c:if test="${data.id == patient.province}">selected</c:if>>${data.name }</option>
                                            </c:forEach>
                                        </select>
                                        <span class="personal-form-span form-span">省份</span> 
                                        <select name="county" id="county" class="personal-input" style="width: 130px;">
                                            <option value="0">--</option>
                                            <c:forEach var="data" items="${countyList}">
                                                <option value="${data.id }" <c:if test="${data.id == patient.county}">selected</c:if>>${data.name }</option>
                                            </c:forEach>
                                        </select>
                                        <span class="personal-form-span form-span">县区</span>
                                    </td>
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
                                    <td width="150" class="personal-title">电子邮件：</td>
                                    <td width="10"></td>
                                    <td width="200" class="personal-value"><input type="text" class="personal-input" id="patientForm_email" name="email"
                                        value="${patient.email }" placeholder="请输入电子邮件 " maxlength="64" /></td>
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
                                            <input type="radio" id="patientForm_isTemp0" name="isTemp" value="0" <c:if test="${ !patient.isTemp }">checked</c:if> />
                                            <label for="patientForm_isTemp0" class="form-span form-item-label">长期</label>
                                            <input type="radio" id="patientForm_isTemp1" name="isTemp" value="1" <c:if test="${ patient.isTemp || empty patient.isTemp}">checked</c:if> /> 
                                            <label for="patientForm_isTemp1" class="form-span form-item-label">临时</label>
                                        </div>
                                    </td>
                                    <td width="300"></td>
                                </tr>
                                <tr>
									<td width="300"></td>
									<td width="150" class="personal-title">患者状态：</td>
									<td width="10"></td>
									<td width="200" class="personal-value" style="padding-top:0px;">
										<div class="group-radio" style="padding-top:15px; margin-bottom:-15px;margin-left:-35px;">
											<label class="form-span form-item-label">
												<input type="radio" name="patientType" value="1" <c:if test="${ '1' eq patient.patientType}">checked</c:if> />
												门诊
											</label>
											<label class="form-span form-item-label">
												<input type="radio" name="patientType" value="2" <c:if test="${ '2' eq patient.patientType}">checked</c:if> />
												住院
											</label>
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
    
    <script src="${ctx }/framework/jquery/1.11.3/jquery.form.js"></script>
    <script src="${ctx }/framework/uploadPreview.min.js?version=${version}"></script>
	<script src="${ctx }/assets/js/patient/edit_patient_basic.js?version=${version}"></script>
    <script type="text/javascript">
    paramVal = '${paramVal}';
    addOrEdit = '${addOrEdit}';
    $(function() {
        $("#birthdayShow_div_id").hide();//隐藏div  
        $("#patientForm_birthday").daterangepicker({
            "singleDatePicker": true,
            "showDropdowns": true,
            "locale": {
                format:"YYYY-MM-DD"
            }
        });
        
    });
</script>
