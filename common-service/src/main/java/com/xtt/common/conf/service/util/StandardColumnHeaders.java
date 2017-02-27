package com.xtt.common.conf.service.util;

import java.util.ArrayList;
import java.util.List;

/**
 * The column headers used by the standard importer. Has Chinese and English versions.
 * 
 */

public enum StandardColumnHeaders {
    /** 患者 */
    patientName("姓名"), patientSex("性别"), patientIdType("证件类型"), patientIdNumber("证件号码"), patientBirthday("出生日期"), patientWorkUnit("工作单位"),
    // ------------------------------------------------------------------------------------------------------------------------------------------------------
    patientMobile("手机号"), patientEmergencyContacts("家属姓名"), patientEmergencyMobile("家属电话一"), patientEmergencyMobile2(
                    "家属电话二"), patientEmergencyMobile3("家属电话三"), patientAddress("住址"), patientAdmissionNumber("住院号"), patientOutpatientNumber(
                                    "门诊号"), patientDryWeight("干体重"), patientDialysisTimes("透析次数"), patientSerialNum("序号"),
    /** 医生 */
    doctorAccount("账户"), doctorName("姓名"), doctorSex("性别"), doctorRole("角色"), doctorBirthday("出生日期"), doctorPosition("职称"), doctorMobile("手机号"), doctorOtherContacts("其它联系方式"),
    /** 护士 */
    nurseAccount("账户"), nurseName("姓名"), nurseSex("性别"), nurseRole("角色"), nurseBirthday("出生日期"), nursePosition("职称"), nurseMobile("手机号"), nurseOtherContacts("其它联系方式");

    private final String value;

    private StandardColumnHeaders(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    static StandardColumnHeaders[] patientColumnHeaders = getHead("patient");

    static StandardColumnHeaders[] doctorColumnHeaders = getHead("doctor");

    static StandardColumnHeaders[] nurseColumnHeaders = getHead("nurse");

    boolean isPatientHeader() {
        return name().startsWith("patient");
    }

    boolean isDoctorHeader() {
        return name().startsWith("patient");
    }

    boolean isNurseHeader() {
        return name().startsWith("patient");
    }

    private static StandardColumnHeaders[] getHead(String startWith) {
        List<StandardColumnHeaders> headers = new ArrayList<StandardColumnHeaders>();
        for (StandardColumnHeaders head : StandardColumnHeaders.values()) {
            if (head.name().startsWith(startWith)) {
                headers.add(head);
            }
        }
        return convertToArray(headers);
    }

    /**
     * 将枚举list转换成数组
     * 
     * @Title: convertToArray
     * @param list
     * @return
     * 
     */
    public static StandardColumnHeaders[] convertToArray(List<StandardColumnHeaders> list) {
        StandardColumnHeaders[] temp = {};
        if (list == null || list.isEmpty()) {
            return temp;
        }
        return list.toArray(temp);
    }
}
