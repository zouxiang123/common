/**   
 * @Title: CmDiagnosisEntityPO.java 
 * @Package com.xtt.common.dao.po
 * Copyright: Copyright (c) 2016
 * @author: jackie   
 * @date: 2016年12月1日 下午7:09:53 
 *
 */
package com.xtt.common.dao.po;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.FactoryUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.list.LazyList;

import com.xtt.common.dao.model.CmDiagnosisEntity;
import com.xtt.platform.util.time.DateFormatUtil;

public class CmDiagnosisEntityPO extends CmDiagnosisEntity {
    private String operatorName;
    private String itemName;
    private String createTimeShow;
    private String multiTenant;
    private String hospitalName; // 就诊医院
    /**
     * Entity对应的Value集合
     */
    private List<CmDiagnosisEntityValuePO> valueList;

    private Map<String, List<CmDiagnosisEntityValuePO>> valueMap;

    public CmDiagnosisEntityPO() {
        this.valueMap = MapUtils.lazyMap(new HashMap<String, List<Object>>(), (Factory) () -> LazyList
                        .decorate(new ArrayList<CmDiagnosisEntityValuePO>(), FactoryUtils.instantiateFactory(CmDiagnosisEntityValuePO.class)));
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCreateTimeShow() {
        if (super.getCreateTime() != null)
            createTimeShow = DateFormatUtil.convertDateToStr(super.getCreateTime(), DateFormatUtil.FORMAT_DATE2);
        return createTimeShow;
    }

    public void setCreateTimeShow(String createTimeShow) {
        this.createTimeShow = createTimeShow;
    }

    public List<CmDiagnosisEntityValuePO> getValueList() {
        return valueList;
    }

    public void setValueList(List<CmDiagnosisEntityValuePO> valueList) {
        this.valueList = valueList;
    }

    public Map<String, List<CmDiagnosisEntityValuePO>> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<String, List<CmDiagnosisEntityValuePO>> valueMap) {
        this.valueMap = valueMap;
    }

    public String getMultiTenant() {
        return multiTenant;
    }

    public void setMultiTenant(String multiTenant) {
        this.multiTenant = multiTenant;
    }

}
