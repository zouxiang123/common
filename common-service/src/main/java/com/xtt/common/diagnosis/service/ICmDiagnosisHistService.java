/**   
 * @Title: ICmDiagnosisHistService.java 
 * @Package com.xtt.common.diagnosis.service
 * Copyright: Copyright (c) 2015
 * @author: admin   
 * @date: 2016年12月1日 下午7:18:34 
 *
 */
package com.xtt.common.diagnosis.service;

import java.util.List;

import com.xtt.common.dao.model.CmDiagnosisHist;
import com.xtt.common.dao.po.CmDiagnosisHistPO;

public interface ICmDiagnosisHistService {

    CmDiagnosisHistPO selectById(Long id);

    String updateCmDiagnosisHist(CmDiagnosisHist record);

    /**
     * 根据患者Id获取患者诊断之询问病史数据
     * 
     * @Title: selectByPatient
     * @param patientId
     * @return
     *
     */
    List<CmDiagnosisHistPO> selectByPatient(Long patientId);

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
