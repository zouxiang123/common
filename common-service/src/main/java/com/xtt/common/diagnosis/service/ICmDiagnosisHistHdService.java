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

import com.xtt.common.dao.po.CmDiagnosisHistHdPO;

public interface ICmDiagnosisHistHdService {

    /**
     * 根据Id获取血透史数据
     * 
     * @param id
     * @return
     */
    CmDiagnosisHistHdPO selectById(Long id);

    /**
     * 根据患者Id获取患者诊断之血透史集合数据
     * 
     * @Title: selectHdsByPatient
     * @param patientId
     * @return
     *
     */
    List<CmDiagnosisHistHdPO> selectHdsByPatient(Long patientId);

    /**
     * 保存item数据
     * 
     * @Title: saveItem
     * @param record
     * @return status
     *
     */
    String saveItem(CmDiagnosisHistHdPO record);

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
