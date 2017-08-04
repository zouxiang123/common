/**   
 * @Title: IDictHospitalLabService.java 
 * @Package com.xtt.common.patient
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年4月22日 下午12:54:10 
 *
 */
package com.xtt.common.assay.service;

import java.util.Date;
import java.util.List;

import com.xtt.common.dao.po.PatientAssayRecordBusiPO;

public interface IPatientAssayRecordBusiService {

    // 保存病人化验检查结果表
    void save(Date startCreateTime, Date endCreateTime);

    /**
     * 根据自定义条件查询常用项
     * 
     * @Title: listByCondition
     * @param query
     * @return
     *
     */
    List<PatientAssayRecordBusiPO> listByCondition(PatientAssayRecordBusiPO query);

}
