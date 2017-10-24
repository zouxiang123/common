<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head_standard.jsp"%>
<title>患者信息</title>
</head>
<body>
    <div class="ml-50 mt-18">
        <div>
            <img src="${ctx}/images${patient.imagePath }?time=${currentTime}" class="newPatientimgsize mr-10"> 
            <span class="mr-34 fs-18">${patient.name }</span> 
            <i class="icon-star mr-24" data-permission-key="edit_patient" onclick="window.location.href='${ctx}/patient/editPatient.shtml?patientId=${patient.id }';"></i> 
            <i class="icon-star" data-popup="#barcodeDialog"></i>
        </div>
        <div class="u-xt-12">
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">性别：</div>
                    <div class="right" displayfield>${patient.sexShow}</div>
                </div>
            </div>
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">年龄：</div>
                    <div class="right" displayfield><c:if test="${!empty patient.age }">${patient.age }岁</c:if></div>
                </div>
            </div>
        </div>
        <div class="u-xt-12">
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">身高：</div>
                    <div class="right" displayfield><c:if test="${!empty patient.height }">${patient.heightShow }cm</c:if></div>
                </div>
            </div>
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">体重：</div>
                    <div class="right" displayfield><c:if test="${!empty patient.weight }">${patient.weightShow }cm</c:if></div>
                </div>
            </div>
        </div>
        <div class="u-xt-12">
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">证件类型：</div>
                    <div class="right" displayfield>
                        <c:if test="${ patient.idType=='1'}">身份证号</c:if> 
                        <c:if test="${ patient.idType=='2'}">护照</c:if> 
                        <c:if test="${ patient.idType=='3'}">其它</c:if>
                    </div>
                </div>
            </div>
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">证件号码：</div>
                    <div class="right" displayfield>${patient.idNumber }</div>
                </div>
            </div>
        </div>
        <div class="u-xt-12">
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">出生日期：</div>
                    <div class="right" displayfield>${patient.birthdayShow }</div>
                </div>
            </div>
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">出生地：</div>
                    <div class="right" displayfield>${patient.provinceName }${patient.countyName }</div>
                </div>
            </div>
        </div>
        <div class="u-xt-12">
            <div class="u-list-text">
                <div class="left text-left-imp">详细地址：</div>
                <div class="right" displayfield>${patient.address }</div>
            </div>
        </div>
        <div class="u-xt-12">
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">联系方式：</div>
                    <div class="right" displayfield>${patient.mobileShow }</div>
                </div>
            </div>
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">工作单位：</div>
                    <div class="right" displayfield>${patient.workUnit }</div>
                </div>
            </div>
        </div>
        <div class="u-xt-12">
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">长期/临时患者：</div>
                    <div class="right" displayfield>
                        <c:if test="${patient.isTemp }">临时</c:if> 
                        <c:if test="${!patient.isTemp }">长期</c:if>
                    </div>
                </div>
            </div>
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">患者状态：</div>
                    <div class="right" displayfield>
                        <c:if test="${ '1' eq patient.patientType}">门诊</c:if>
                        <c:if test="${ '2' eq patient.patientType}">住院</c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="u-xt-12">
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">ABO血型：</div>
                    <div class="right" displayfield>${patient.bloodAbo }</div>
                </div>
            </div>
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">RH(D)血型：</div>
                    <div class="right" displayfield>
                        <c:if test="${patient.bloodRh =='1' }">阳性</c:if> 
                        <c:if test="${patient.bloodRh =='0' }">阴性</c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="u-xt-12">
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">家属姓名：</div>
                    <div class="right" displayfield>${patient.emergencyContacts }</div>
                </div>
            </div>
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">家属电话一：</div>
                    <div class="right" displayfield>${patient.emergencyMobileShow }</div>
                </div>
            </div>
        </div>
        <div class="u-xt-12">
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">家属电话二：</div>
                    <div class="right" displayfield>${patient.emergencyMobileShow2 }</div>
                </div>
            </div>
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">家属电话三：</div>
                    <div class="right" displayfield>${patient.emergencyMobileShow3 }</div>
                </div>
            </div>
        </div>
        <div class="u-xt-12">
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">费用类型：</div>
                    <div class="right" displayfield>
                        <c:if test="${patient.chargeType=='1'}">医保</c:if>
                        <c:if test="${patient.chargeType=='2'}">自费</c:if>
                        <c:if test="${patient.chargeType=='3'}">军人</c:if>
                    </div>
                </div>
            </div>
            <div class="u-xt-6">
                <div class="u-list-text">
                    <div class="left text-left-imp">电子邮件：</div>
                    <div class="right" displayfield>${patient.email }</div>
                </div>
            </div>
        </div>
        <c:if test="${sysParam.paramValue=='1' }">
            <div class="u-xt-12">
                <div class="u-list-text">
                    <div class="left text-left-imp">透析号：</div>
                    <div class="right" displayfield>${patient.serialNum }</div>
                </div>
            </div>
        </c:if>
        <c:if test="${patientCardList != null and patientCardList.size()>0 }">
            <div class="u-xt-12">
                <c:forEach var="ptCard" items="${patientCardList}" varStatus="status">
                    <c:forEach var="obj" items="${medicare_card_type}">
                        <c:if test="${obj.value==ptCard.cardType}">
                            <div class="u-xt-6">
                                <div class="u-list-text">
                                    <div class="left text-left-imp">${obj.name}：</div>
                                    <div class="right" displayfield>${ptCard.cardNo} 
                                        <c:if test="${ptCard.newFlag && ptCard.cardType=='03'}">
                                            <label class="u-radio" style="cursor: default;">
                                                <input type="radio" checked /><span class="icon-radio"></span>
                                            </label>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </div>
        </c:if>
    </div>
</body>
<!-- 二维码 -->
<div class="u-mask" id="barcodeDialog" data-hide="#barcodeDialog">
    <div class="u-dialog" style="width:auto;">
      <div class="u-dialog-content">
        <img src="${ctx}/images/${login_user.tenantId }/images/patient/barcode/${patient.id }.png">
      </div>
    </div>
</div>
<script type="text/javascript">
$(function() {
    $("[displayfield]").each(function() {
        if (isEmpty($(this).text())) {
            $(this).text("无");
        }
    });
});
</script>
</html>
