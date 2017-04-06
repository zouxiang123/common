/**   
 * @Title: ICmDiagnosisHistTumourService.java 
 * @Package com.xtt.common.diagnosis.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2017年4月1日 上午9:36:10 
 *
 */
package com.xtt.common.diagnosis.service;

import java.util.List;

import com.xtt.common.dao.po.CmDiagnosisHistTumourPO;

public interface ICmDiagnosisHistTumourService {
    /**
     * 根据id查询数据
     * 
     * @Title: getById
     * @param id
     * @return
     *
     */
    CmDiagnosisHistTumourPO getById(Long id);

    /**
     * 根据id物理删除数据（物理删除，不可恢复）
     * 
     * @Title: removeById
     * @param id
     * @return （2:记录不存在，1：成功）
     */
    String removeById(Long id);

    /**
     * 根据患者id查询数据
     * 
     * @Title: listByPatientId
     * @param patientId
     * @return
     *
     */
    List<CmDiagnosisHistTumourPO> listByPatientId(Long patientId);

    /**
     * 保存数据
     * 
     * @Title: save
     * @param record
     * @return save status
     */
    String save(CmDiagnosisHistTumourPO record);
}
