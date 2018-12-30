/**   
 * @Title: ICmDiagnosisEntityValueService.java 
 * @Package com.xtt.txgl.report.service
 * Copyright: Copyright (c) 2015
 * @author: tcy   
 * @date: 2018年3月13日 下午7:53:35 
 *
 */
package com.xtt.common.diagnosis.service;

import java.util.List;
import java.util.Map;

import com.xtt.common.dao.po.CmDiagnosisEntityValuePO;

public interface ICmDiagnosisEntityValueService {

    /**
     * 根据条件统计一种原发病
     * 
     * @Title: countByCondtion
     * @param map
     * @return
     *
     */
    List<CmDiagnosisEntityValuePO> countByCondtion(Map<String, Object> map);

    /**
     * 获取本年度原发病发病率最多的病种的item_code
     * 
     * @Title: getItemCodeByYear
     * @return
     *
     */
    CmDiagnosisEntityValuePO getItemCodeByYear();

    /**
     * 统计指定年份的所有的原发病的数量
     * 
     * @Title: countAllDiagnosis
     * @param year
     * @param tenantId
     * @return
     *
     */
    Integer countAllDiagnosis(String year, Integer tenantId);

    /**
     * 年度原发病统计
     * 
     * @Title: countYearDiagnosisByCondition
     * @param map
     * @return
     *
     */
    List<CmDiagnosisEntityValuePO> countYearDiagnosisByCondition(Map<String, Object> map);

}
