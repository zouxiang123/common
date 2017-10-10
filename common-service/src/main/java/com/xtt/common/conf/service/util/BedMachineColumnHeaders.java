package com.xtt.common.conf.service.util;

/**
 * The column headers used by the standard importer. Has Chinese and English versions.
 * 
 */

public enum BedMachineColumnHeaders {
    /** 患者 */
    bedNumber("床位编号"), areaCode("所属病区"), backupFlag("是否备用"), patientBirthday("出生日期"), patientWorkUnit("工作单位"), patientProfession("职业"), patientMobile("手机号"), patientEmergencyMobile("紧急联系方式"), patientAddress("住址"),
    /** 医生 */
    doctorAccount("账户"), doctorName("姓名"), doctorSex("性别"), doctorRole("角色"), doctorBirthday("出生日期"), doctorPosition("职称"), doctorMobile("手机号"), doctorOtherContacts("其它联系方式"),
    /** 护士 */
    nurseAccount("账户"), nurseName("姓名"), nurseSex("性别"), nurseRole("角色"), nurseBirthday("出生日期"), nursePosition("职称"), nurseMobile("手机号"), nurseOtherContacts("其它联系方式");

    private final String value;

    private BedMachineColumnHeaders(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    static BedMachineColumnHeaders[] patientColumnHeaders = { bedNumber, areaCode, backupFlag, patientBirthday, patientWorkUnit, patientProfession,
            patientMobile, patientEmergencyMobile, patientAddress };

    static BedMachineColumnHeaders[] doctorColumnHeaders = { doctorAccount, doctorName, doctorSex, doctorRole, doctorBirthday, doctorPosition,
            doctorMobile, doctorOtherContacts };

    static BedMachineColumnHeaders[] nurseColumnHeaders = { nurseAccount, nurseName, nurseSex, nurseRole, nurseBirthday, nursePosition, nurseMobile,
            nurseOtherContacts };
}
