/**   
 * @Title: DiagnosisApiDto.java 
 * @Package com.xtt.common.dto
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年9月27日 上午10:49:15 
 *
 */
package com.xtt.common.dto;

import java.util.Collection;

public class DiagnosisApi {
    private static final String REQUEST_BASE = "patient/diagnosis/";
    /**
     * 获取患者最新一次的诊断字符串地址
     */
    public static final String API_ADDR_LATEST_STR = REQUEST_BASE.concat("getLatestStrByPatientIds.shtml");

    private Collection<Long> patientIds;
    // 查询的数据类别 如：临床诊断，病理诊断
    private Collection<String> types;

    public Collection<Long> getPatientIds() {
        return patientIds;
    }

    public void setPatientIds(Collection<Long> patientIds) {
        this.patientIds = patientIds;
    }

    public Collection<String> getTypes() {
        return types;
    }

    public void setTypes(Collection<String> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        String patientIdsStr = "";
        if (patientIds != null && patientIds.size() > 0) {
            for (Long patientId : patientIds) {
                patientIdsStr += patientId + ",";
            }
            patientIdsStr = patientIdsStr.substring(0, patientIdsStr.length() - 1);
        }
        String typesStr = "";
        if (types != null && types.size() > 0) {
            for (String type : types) {
                typesStr += type + ",";
            }
            typesStr = typesStr.substring(0, typesStr.length() - 1);
        }
        return "DiagnosisApiDto [patientIds={" + patientIdsStr + "}, types={" + typesStr + "}]";
    }

}
