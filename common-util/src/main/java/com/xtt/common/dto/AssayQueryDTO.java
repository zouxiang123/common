/**   
 * @Title: AssayQueryApi.java 
 * @Package com.xtt.common.api
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年10月31日 下午5:38:51 
 *
 */
package com.xtt.common.dto;

import java.util.Collection;

/**
 * 化验查询的api接口
 * 
 * @ClassName: AssayQueryApi
 * @date: 2017年10月31日 下午5:38:54
 * @version: V1.0
 *
 */
public class AssayQueryDTO {
    /**
     * 获取患者dictCodes对应的最新的一条记录
     */
    public static final String GET_LATEST_BY_DICTCODES = "assay/patientAssayRecord/getLatestByDictCodes.shtml";

    private Collection<String> dictCodes;
    private Long patientId;
    private String dateStr;

    public Collection<String> getDictCodes() {
        return dictCodes;
    }

    public void setDictCodes(Collection<String> dictCodes) {
        this.dictCodes = dictCodes;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

}
