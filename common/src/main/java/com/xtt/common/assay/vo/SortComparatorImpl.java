/**

 */
package com.xtt.common.assay.vo;

import java.util.Comparator;

import com.xtt.common.dao.po.PatientAssayGroupRulePO;

/**
 * @author liujun
 * @2016年6月1日 排序的实现类
 */
public class SortComparatorImpl implements Comparator<PatientAssayGroupRulePO> {

    /**
     * 排序方法
     */
    @Override
    public int compare(PatientAssayGroupRulePO patientAssayGroupRulePO1, PatientAssayGroupRulePO patientAssayGroupRulePO2) {
        if (patientAssayGroupRulePO1 != null && patientAssayGroupRulePO2 != null) {
            if (patientAssayGroupRulePO1.getMinValue() > patientAssayGroupRulePO2.getMinValue()) {
                return 1;
            } else if (patientAssayGroupRulePO1.getMinValue() == patientAssayGroupRulePO2.getMinValue()) {
                return 0;
            }
        }
        return -1;
    }

}
