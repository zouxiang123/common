/**   
 * @Title: IPatientAssayDataService.java 
 * @Package com.xtt.common.patient.service
 * Copyright: Copyright (c) 2015
 * @author: abc   
 * @date: 2016年12月5日 下午2:53:39 
 *
 */
package com.xtt.common.assay.service;

import java.util.Date;
import java.util.List;

import com.xtt.common.dao.model.PatientAssayData;

public interface IPatientAssayDataService {

    public List<PatientAssayData> findByPatientAssayData(PatientAssayData patientAssayData);

    /**
     * 保存转换后患者化验项
     * 
     * @Title: save
     * @param patientAssayData
     * @return
     *
     */
    public PatientAssayData save(PatientAssayData patientAssayData);

    /**
     * 原始患者化验项 转换 方便查询的患者化验项 表:【patient_assay_class <--transformation--> patient_assay_data】
     * 
     * @Title: patientAssayClassTransformationPatientAssayData
     * @param transformationDate
     *
     */
    public void patientAssayRecordTransformationPatientAssayData(Date transformationDate);
}
