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

public interface IPatientAssayRecordBusiService {

    // 保存病人化验检查结果表
    void save(Date startCreateTime, Date endCreateTime);

}
