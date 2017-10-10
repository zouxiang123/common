/**   
 * @Title: ReportPatientAssayRecordPO.java 
 * @Package com.xtt.hd.dao.po
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年5月24日 下午8:16:24 
 *
 */
package com.xtt.common.dao.po;

import java.util.Collection;
import java.util.List;

import com.xtt.common.dao.model.ReportPatientAssayRecord;

public class ReportPatientAssayRecordPO extends ReportPatientAssayRecord {
    private String sqlCondition;// 组装的sql条件
    private String dateType;// r日期类型。月和季度
    private Double minValue;// 最小值
    private Double maxValue;// 最大值

    private Integer allCount;// 总数量
    private Integer okCount;// 达标数量
    private Integer noOkCount;// 不达标数量
    private Integer excessOkCount;// 超标数量
    private Double okRates;// 达标率
    private Double allSum;// 求和
    private Double avg;// 平均值
    private Double pop; // 标准差

    private String dateValue;// 日期值
    private String dateValueShow;// 日期值显示
    private String patientName;// 患者名字
    private String resultTipsShow;// 是否达标

    private List<Double> ruleList;// 分组规则

    private Double addRuleValue;// 添加的规则
    private String operate;// 操作

    private Integer okIndex;// 0：达标，1：不达标，2：超标，-1:全部
    private String okShow;// 显示达标状态
    private Integer groupIndex;// -1：全部，其余和分组规则匹配
    private String groupShow;// 分组值
    private String batchNo;// 批次号
    private Long patientLabelId; // 标签编号
    private Long fkUserId; // 用户编号
    private Collection<String> itemCodes;// 多个itemCode编号

    public String getSqlCondition() {
        return sqlCondition;
    }

    public void setSqlCondition(String sqlCondition) {
        this.sqlCondition = sqlCondition;
    }

    @Override
    public String getDateType() {
        return dateType;
    }

    @Override
    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }

    public Integer getOkCount() {
        return okCount;
    }

    public void setOkCount(Integer okCount) {
        this.okCount = okCount;
    }

    public Double getAllSum() {
        return allSum;
    }

    public void setAllSum(Double allSum) {
        this.allSum = allSum;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Double getPop() {
        return pop;
    }

    public void setPop(Double pop) {
        this.pop = pop;
    }

    public List<Double> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<Double> ruleList) {
        this.ruleList = ruleList;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Double getOkRates() {
        return okRates;
    }

    public void setOkRates(Double okRates) {
        this.okRates = okRates;
    }

    public Integer getNoOkCount() {
        return noOkCount;
    }

    public void setNoOkCount(Integer noOkCount) {
        this.noOkCount = noOkCount;
    }

    public Integer getExcessOkCount() {
        return excessOkCount;
    }

    public void setExcessOkCount(Integer excessOkCount) {
        this.excessOkCount = excessOkCount;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getResultTipsShow() {
        return resultTipsShow;
    }

    public void setResultTipsShow(String resultTipsShow) {
        this.resultTipsShow = resultTipsShow;
    }

    public Double getAddRuleValue() {
        return addRuleValue;
    }

    public void setAddRuleValue(Double addRuleValue) {
        this.addRuleValue = addRuleValue;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public String getDateValueShow() {
        return dateValueShow;
    }

    public void setDateValueShow(String dateValueShow) {
        this.dateValueShow = dateValueShow;
    }

    public Integer getOkIndex() {
        return okIndex;
    }

    public void setOkIndex(Integer okIndex) {
        this.okIndex = okIndex;
    }

    public Integer getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(Integer groupIndex) {
        this.groupIndex = groupIndex;
    }

    public String getOkShow() {
        return okShow;
    }

    public void setOkShow(String okShow) {
        this.okShow = okShow;
    }

    public String getGroupShow() {
        return groupShow;
    }

    public void setGroupShow(String groupShow) {
        this.groupShow = groupShow;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Long getPatientLabelId() {
        return patientLabelId;
    }

    public void setPatientLabelId(Long patientLabelId) {
        this.patientLabelId = patientLabelId;
    }

    public Long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
    }

    public Collection<String> getItemCodes() {
        return itemCodes;
    }

    public void setItemCodes(Collection<String> itemCodes) {
        this.itemCodes = itemCodes;
    }

}
