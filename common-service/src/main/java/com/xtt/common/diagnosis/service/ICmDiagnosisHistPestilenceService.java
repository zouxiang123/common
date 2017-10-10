/**   
 * @Title: ICmDiagnosisHistSurgeryService.java 
 * @Package com.xtt.common.diagnosis.service
 * Copyright: Copyright (c) 2015
 * @author: admin   
 * @date: 2016年12月2日 下午5:37:23 
 *
 */
package com.xtt.common.diagnosis.service;

import java.util.List;

import com.xtt.common.dao.po.CmDiagnosisHistPestilencePO;

public interface ICmDiagnosisHistPestilenceService {

    /**
     * 根据Id获取传染病史数据
     * 
     * @param id
     * @return
     */
    CmDiagnosisHistPestilencePO selectById(Long id);

    /**
     * 根据患者Id获取患者诊断之传染病史集合数据
     * 
     * @Title: selectPestilencesByPatient
     * @param patientId
     * @return
     *
     */
    List<CmDiagnosisHistPestilencePO> selectPestilencesByPatient(Long patientId);

    /**
     * 保存item数据
     * 
     * @Title: saveItem
     * @param record
     * @return status
     *
     */
    String saveItem(CmDiagnosisHistPestilencePO record);

    /**
     * 根据id删除数据
     * 
     * @Title: deleteById
     * @param id
     * @return status
     *
     */
    String deleteById(Long id);
}
