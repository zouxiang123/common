
package com.xtt.common.api;

/**
 * @ClassName com.xtt.hd.dao.po.ApiThirdPatientAssayRecordPO.java
 * @Description:接口入参条件（获取全员检验指定指标数据）
 * @author zz
 * @version V1.0
 * @date 2017年4月13日 上午11:56:40
 */
public class CommQueryApi {
    private String parmType; // 入参类型(调用指定的接口)

    private String dateStr;// 日期(2017-05-01)

    private Long fkPatientId; // 血透病患ID

    private Integer tenantId; // 租户ID

    private String subType;// 子操作类别

    public String getParmType() {
        return parmType;
    }

    public void setParmType(String parmType) {
        this.parmType = parmType;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Long getFkPatientId() {
        return fkPatientId;
    }

    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    @Override
    public String toString() {
        return "ApiCommQueryDto [parmType=" + parmType + ", dateStr=" + dateStr + ", fkPatientId=" + fkPatientId + ", tenantId=" + tenantId
                        + ", subType=" + subType + "]";
    }

}
