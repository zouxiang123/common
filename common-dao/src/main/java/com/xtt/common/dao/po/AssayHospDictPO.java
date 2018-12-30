/**   
 * @Title: AssayHospDictPO.java 
 * @Package com.xtt.hd.dao.po
 * Copyright: Copyright (c) 2015
 * @author: Administrator   
 * @date: 2017年8月22日 上午8:47:48 
 *
 */
package com.xtt.common.dao.po;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.xtt.common.dao.model.AssayHospDict;

public class AssayHospDictPO extends AssayHospDict {
    private Collection<String> dictCodes;

    private String fkItemName;
    private String assayClass;// 化验类
    private Boolean selectFlag;// 是否选中
    private Boolean categoryFlag;// 是否是化验单
    private String result;// 化验值
    private Date assayDate;// 录入日期
    private String assayDateStr;
    private String resultTips;// 化验结果
    private String patientAssayId;// 患者化验记录单
    private Long fkPatientId;// 患者id
    private String groupId; // 组id
    private String groupName; // 组名称
    private String reqId; // 申请单号
    private String oldItemCode; // 历史itemcode

    // 显示用itemCode,如果是血透唯一标识的item_code，去除其后缀
    private String itemCodeShow;

    private Collection<String> itemCodes;

    private List<Long> ids;

    public String getOldItemCode() {
        return oldItemCode;
    }

    public void setOldItemCode(String oldItemCode) {
        this.oldItemCode = oldItemCode;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFkItemName() {
        return fkItemName;
    }

    public void setFkItemName(String fkItemName) {
        this.fkItemName = fkItemName;
    }

    public Collection<String> getDictCodes() {
        return dictCodes;
    }

    public void setDictCodes(Collection<String> dictCodes) {
        this.dictCodes = dictCodes;
    }

    public String getAssayClass() {
        return assayClass;
    }

    public void setAssayClass(String assayClass) {
        this.assayClass = assayClass;
    }

    public Boolean getSelectFlag() {
        return selectFlag;
    }

    public void setSelectFlag(Boolean selectFlag) {
        this.selectFlag = selectFlag;
    }

    public Boolean getCategoryFlag() {
        return categoryFlag;
    }

    public void setCategoryFlag(Boolean categoryFlag) {
        this.categoryFlag = categoryFlag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getAssayDate() {
        return assayDate;
    }

    public void setAssayDate(Date assayDate) {
        this.assayDate = assayDate;
    }

    public String getAssayDateStr() {
        return assayDateStr;
    }

    public void setAssayDateStr(String assayDateStr) {
        this.assayDateStr = assayDateStr;
    }

    public String getResultTips() {
        return resultTips;
    }

    public void setResultTips(String resultTips) {
        this.resultTips = resultTips;
    }

    public String getPatientAssayId() {
        return patientAssayId;
    }

    public void setPatientAssayId(String patientAssayId) {
        this.patientAssayId = patientAssayId;
    }

    public Long getFkPatientId() {
        return fkPatientId;
    }

    public void setFkPatientId(Long fkPatientId) {
        this.fkPatientId = fkPatientId;
    }

    public String getItemCodeShow() {
        return itemCodeShow;
    }

    public void setItemCodeShow(String itemCodeShow) {
        this.itemCodeShow = itemCodeShow;
    }

    public Collection<String> getItemCodes() {
        return itemCodes;
    }

    public void setItemCodes(Collection<String> itemCodes) {
        this.itemCodes = itemCodes;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

}
