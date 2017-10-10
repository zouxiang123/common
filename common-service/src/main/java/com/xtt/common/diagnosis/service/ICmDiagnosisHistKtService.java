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

import com.xtt.common.dao.po.CmDiagnosisHistKtPO;

public interface ICmDiagnosisHistKtService {

    /**
     * 根据Id获取肾移植史数据
     * 
     * @param id
     * @return
     */
    CmDiagnosisHistKtPO selectById(Long id);

    /**
     * 根据患者Id获取患者诊断之肾移植史集合数据
     * 
     * @Title: selectKtsByPatient
     * @param patientId
     * @return
     *
     */
    List<CmDiagnosisHistKtPO> selectKtsByPatient(Long patientId);

    /**
     * 保存item数据
     * 
     * @Title: saveItem
     * @param record
     * @return status
     *
     */
    String saveItem(CmDiagnosisHistKtPO record);

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
