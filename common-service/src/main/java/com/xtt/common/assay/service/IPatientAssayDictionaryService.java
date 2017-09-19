/**   
 * @Title: PatientAssayDictionaryService.java 
 * @Package com.xtt.common.patient.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月29日 上午9:36:49 
 *
 */
package com.xtt.common.assay.service;

import java.util.List;

import com.xtt.common.dao.po.PatientAssayDictionaryPO;

public interface IPatientAssayDictionaryService {
    /**
     * 根据条件模糊查询检查项
     * 
     * @Title: getByFuzzyCondition
     * @return
     *
     */
    List<PatientAssayDictionaryPO> getByFuzzyCondition(PatientAssayDictionaryPO record);

    /**
     * 根据条件获取检查项
     * 
     * @Title: getByCondition
     * @return
     *
     */
    List<PatientAssayDictionaryPO> getByCondition(PatientAssayDictionaryPO record);

    /**
     * 通过唯一编码码获取对象
     * 
     * @Title: getByUniqueId
     * @param itemCode
     * @return
     *
     */
    PatientAssayDictionaryPO getByItemCode(String itemCode);

}
