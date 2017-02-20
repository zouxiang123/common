/**   
 * @Title: DiagnosisStepEnum.java 
 * @Package com.xtt.common.diagnosis
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年11月18日 下午1:53:12 
 *
 */
package com.xtt.common.diagnosis;

public enum DiagnosisStepEnum {
    /** 录入患者信息 */
    PATIENT_INFO(10),
    /** 病史询问 */
    MEDICAL_HISTORY(20),
    /** 临床诊断 */
    CLINICAL_DIAGNOSIS(30),
    /** 病理诊断 */
    PATHOLOGIC_DIAGNOSIS(40),
    /** firstDone */
    CKD_AKI(50),
    /** 治疗前合并症 */
    CURE_COMPLICATION(60),
    /** 其他诊断 */
    OTHER_DIAGNOSIS(70),
    /** 完成 */
    FIRST_TDONE(80);
    private Integer value;

    private DiagnosisStepEnum(Integer value) {
        this.setValue(value);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
