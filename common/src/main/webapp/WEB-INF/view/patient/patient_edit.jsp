<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head_standard.jsp"%>
<title>患者信息</title>
</head>
<body>
<form action="${ctx }/system/uploadImage.shtml" onsubmit="return patient_edit.uploadImg(this);" enctype="multipart/form-data" method="post" id ="imageUploadForm">
    <input type="file" id="up_img" name="image" style="display:none;" accept="image/*"/>
    <input type="hidden" name="id" value="${patient.id }">
</form>
<form id="patientForm" onsubmit="return false;">
<div style="width: 650px;margin: 0 auto">
    <input type="hidden" id="tempImagePath" name="tempImagePath" />
    <input type="hidden" id="patientForm_id" name="id" value="${patient.id }">
    <input type="hidden" id="sysOwner" name="sysOwner" value="${patient.sysOwner }"/>
    <input type ="hidden" id="checkedStatus" value=""/>
    <div class="mt-16 text-center">
        <span class="u-image" id="patientNameDisplay">${patient.name }</span>
    </div>
    <div id="patientCardListDiv">
        <div class="u-list-text mt-12" id="patientCardDefaultDiv" data-cardrecord>
            <div class="left">
                <label class="u-radio hide" data-newFlagLabel>
                    <input type="radio" name="patientCardList[0].newFlag" value='1' data-newFlag data-clonereplace>
                    <span class="icon-radio"></span>
                </label>
            </div>
            <div class="right">
                <label class="u-select mr-14">
                    <select id="cardTypeId" name="patientCardList[0].cardType" data-cardType data-clonereplace>
                        <c:forEach var="obj" items="${medicare_card_type}">
                            <option value="${obj.value }">${obj.name}</option>
                        </c:forEach>
                    </select>
                </label>
                <input type="text"  placeholder="请输入卡号 " name="patientCardList[0].cardNo" data-cardNo data-clonereplace/>
                <i class="icon-close ml-10 fs-12 hide" data-remove></i>
                <button id="queryPatientInfo" type="button" circle class="ml-10" data-query><i class="icon-search"></i></button>
                <button id="addPatientCard" type="button" circle class="ml-10" data-add><i class="icon-add"></i></button>
                <input type="hidden" value="0" name="patientCardList[0].delFlag" data-delFlag data-clonereplace/>
            </div>
        </div>
        <div class="u-list-text" style="min-height: 0px;">
            <div class="right" style="min-height: 0px;padding-left: 100px;" id="validCardNoDiv"></div>
        </div>
        <c:forEach var="ptCard" items="${ptCardList}" varStatus="st">
             <div class="u-list-text mt-12" data-cardrecord="${ptCard.id}">
                <input type="hidden" value="${ptCard.delFlag}" name="patientCardList[${ st.index + 1}].delFlag" data-delFlag/>
                <input type="hidden"  name="patientCardList[${ st.index + 1}].id" value="${ptCard.id}" />
                <div class="left">
                    <label class="u-radio <c:if test="${ptCard.cardType ne '03'}">hide</c:if>" data-newFlagLabel>
                        <input type="radio" name="patientCardList[${ st.index + 1}].newFlag" value='1' <c:if test="${ptCard.newFlag }">checked</c:if> data-newFlag/>
                        <span class="icon-radio"></span>
                    </label>
                </div>
                <div class="right">
                    <label class="u-select mr-14">
                        <select name="patientCardList[${ st.index + 1}].cardType" data-cardType>
                           <c:forEach var="obj" items="${medicare_card_type}" varStatus="status">
                                <option value="${obj.value }" <c:if test="${obj.value == ptCard.cardType }">selected="selected"</c:if> >${obj.name}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <input type="text" placeholder="请输入卡号 " name="patientCardList[${ st.index + 1}].cardNo" value="${ptCard.cardNo}" data-cardNo /> 
                    <i class="icon-close ml-10 fs-12" data-remove="${ptCard.id}"></i>
                </div>
        </c:forEach>
    </div>
</div>
<div style="height: 1px;" class="bb-dashed mt-16"></div>
<div style="width: 650px;margin: 0 auto" class="pb-70">
    <c:if test="${sysParam.paramValue=='1'}">
       <div class="u-list-text mt-12">
       <div class="left">*透析号：</div>
       <div class="right">
            <!-- 允许复用，则使用下拉框选择 -->
            <c:if test="${isSerialNumMultiplex}">
                <label class="u-select mr-14" style="width: 438px">
                    <select name="serialNum" style="width: 436px">
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
                </label>
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
       </div>
    </c:if>
    <div class="u-list-text mt-12">
        <div class="left">*姓名：</div>
        <div class="right">
            <input style="width: 438px" type="text" id="patientForm_name" name="name" value="${patient.name }" placeholder="请输入姓名" maxlength="18"/>
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">*性别：</div>
        <div class="right">
            <label class="u-radio w-21">
                <input id="patientForm_male" type="radio" name="sex" value="M" <c:if test="${empty patient.sex or patient.sex eq 'M'}">checked</c:if> />
                <span class="icon-radio"></span>男
            </label>
            <label class="u-radio w-21">
                <input id="patientForm_female" type="radio" name="sex" value="F" <c:if test="${ patient.sex eq 'F'}">checked</c:if> />
                <span class="icon-radio"></span>女
            </label>
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">身高：</div>
        <div class="right">
            <input style="width: 438px" type="text" name="height" value="${patient.heightShow }" placeholder="请输入患者身高" maxlength="6"/>
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">体重：</div>
        <div class="right">
            <input style="width: 438px" type="text" name="weight" value="${patient.weightShow }" placeholder="请输入患者体重" maxlength="6"/>
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">费用类型：</div>
        <div class="right">
            <c:forEach items="${chargeTypes }" var="chargeType">
                <label class="u-radio w-21">
                    <input type="radio" name="chargeType" value="${chargeType.value }" <c:if test="${chargeType.value eq patient.chargeType}">checked</c:if>  />
                    <span class="icon-radio"></span>${chargeType.name}
                </label>
            </c:forEach>
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">*证件类型：</div>
        <div class="right">
            <label class="u-select mr-14" style="width: 438px">
                <select style="width: 436px" id="patientForm_idType" name="idType">
                    <option value="1" <c:if test="${ patient.idType=='1'}">selected</c:if> >身份证</option>
                    <option value="2" <c:if test="${ patient.idType=='2'}">selected</c:if> >护照</option>
                    <option value="3" <c:if test="${ patient.idType=='3'}">selected</c:if> >其它</option>
                </select>
            </label>
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">*证件号码：</div>
        <div class="right">
            <input type="text" style="width: 438px" id="patientForm_id_number" name="idNumber" value="${patient.idNumber }" placeholder="请输入证件号码" maxlength="18" />
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">*出生日期：</div>
        <div class="right">
            <input type="text" style="width: 438px" id="patientForm_birthday" name="birthdayShow" value="${patient.birthdayShow}" readonly="readonly" />
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">出生地：</div>
        <div class="right">
            <label class="u-select" style="width: 170px">
                <select style="width: 170px" id="province" name="province">
                    <option value="0">--</option>
                    <c:forEach var="data" items="${provinceList}">
                        <option value="${data.id }" <c:if test="${data.id == patient.province}">selected</c:if>>${data.name }</option>
                    </c:forEach>
                </select>
            </label>
            <span class="opacity-5 ml-6">省份</span>
            <label class="u-select ml-14" style="width: 170px">
                <select style="width: 170px" name="county" id="county">
                    <option value="0">--</option>
                    <c:forEach var="data" items="${countyList}">
                        <option value="${data.id }" <c:if test="${data.id == patient.county}">selected</c:if>>${data.name }</option>
                    </c:forEach>
                </select>
            </label>
            <span class="opacity-5 ml-6">县/区</span>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">*详细地址：</div>
        <div class="right">
            <input type="text" style="width: 438px" name="address" value="${patient.address }" placeholder="请输入详细地址" maxlength="64" />
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">*联系方式：</div>
        <div class="right">
            <input type="text" style="width: 438px" id="patientForm_mobile" name="mobile" value="${patient.mobile }" placeholder="请输入联系方式 " />
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">电子邮件：</div>
        <div class="right">
            <input type="text" style="width: 438px" id="patientForm_email" name="email" value="${patient.email }" placeholder="请输入电子邮件 " maxlength="64" />
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">工作单位：</div>
        <div class="right">
            <input type="text" style="width: 438px" id="patientForm_workUnit" name="workUnit" value="${patient.workUnit }" placeholder="请输入工作单位" maxlength="64" />
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">长期/临时：</div>
        <div class="right">
            <label class="u-radio w-21">
                <input type="radio" name="isTemp" value="0" <c:if test="${ !patient.isTemp }">checked</c:if> />
                <span class="icon-radio"></span>长期患者
            </label>
            <label class="u-radio w-21">
                <input type="radio" name="isTemp" value="1" <c:if test="${ patient.isTemp || empty patient.isTemp}">checked</c:if> />
                <span class="icon-radio"></span>临时患者
            </label>
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">患者状态：</div>
        <div class="right">
            <label class="u-radio w-21">
                <input type="radio" name="patientType" value="1" <c:if test="${ '1' eq patient.patientType}">checked</c:if> />
                <span class="icon-radio"></span>门诊
            </label>
            <label class="u-radio w-21">
                <input type="radio" name="patientType" value="2" <c:if test="${ '2' eq patient.patientType}">checked</c:if> />
                <span class="icon-radio"></span>住院
            </label>
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">血型：</div>
        <div class="right">
            <label class="u-radio w-21">
                <input type="radio" name="bloodAbo" value="A" <c:if test="${ patient.bloodAbo == 'A'}">checked</c:if>/>
                <span class="icon-radio"></span>A
            </label>
            <label class="u-radio w-21">
                <input type="radio" name="bloodAbo" value="B" <c:if test="${ patient.bloodAbo== 'B'}">checked</c:if> /> 
                <span class="icon-radio"></span>B
            </label>
            <label class="u-radio w-21">
                <input type="radio" name="bloodAbo" value="AB" <c:if test="${ patient.bloodAbo== 'AB'}">checked</c:if> />
                <span class="icon-radio"></span>AB
            </label>
            <label class="u-radio w-21">
                <input type="radio" name="bloodAbo" value="O" <c:if test="${ patient.bloodAbo== 'O'}">checked</c:if> />
                <span class="icon-radio"></span>O
            </label>
            <label class="u-radio w-21">
                <input type="radio" name="bloodRh" value="1" <c:if test="${ patient.bloodRh== '1'}">checked</c:if> /> 
                <span class="icon-radio"></span>RH+
            </label>
            <label class="u-radio w-21">
                <input type="radio" name="bloodRh" value="0" <c:if test="${ patient.bloodRh== '0'}">checked</c:if> /> 
                <span class="icon-radio"></span>RH-
            </label>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">家属姓名：</div>
        <div class="right">
            <input type="text" style="width: 438px" name="emergencyContacts" value="${patient.emergencyContacts }" placeholder="请输入家属姓名 " />
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">家属电话一：</div>
        <div class="right">
            <input type="text" style="width: 438px" name="emergencyMobile" value="${patient.emergencyMobile }" placeholder="请输入家属电话 " />
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">家属电话二：</div>
        <div class="right">
            <input type="text" style="width: 438px" name="emergencyMobile2" value="${patient.emergencyMobile2 }" placeholder="请输入家属电话 " />
            <div data-error></div>
        </div>
    </div>
    <div class="u-list-text mt-12">
        <div class="left">家属电话三：</div>
        <div class="right">
            <input type="text" style="width: 438px" name="emergencyMobile3" value="${patient.emergencyMobile3 }" placeholder="请输入家属电话 " />
            <div data-error></div>
        </div>
    </div>
</div>
</form>
<div class="bt-line pt-10 modelbottom" style="z-index: 99;">
    <c:if test="${!empty patient.id }">
        <button type="button" class="mb-10" onclick="window.history.go(-1);">取消</button>
    </c:if>
    <button type="button" class="u-btn-blue mb-10" onclick="patient_edit.save();" fill>保存</button>
</div>
</body>
<script src="${ctx }/framework/jquery/1.11.3/jquery.form.js"></script>
<script src="${ctx }/assets/js/patient/patient_edit.js?version=${version}"></script>
<script type="text/javascript">
patient_edit.init('${interfaceOpenCardText}');
</script>
</html>
