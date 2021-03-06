/**   
 * @Title: IPatientAssayRecordService.java 
 * @Package com.xtt.common.patient.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月29日 上午9:38:24 
 *
 */
package com.xtt.common.assay.service;

import java.util.Date;
import java.util.List;

import com.xtt.common.dao.po.PatientAssayRecordPO;

public interface IPatientAssayRecordService {

    /**
     * @Title: listPatientAssayRecord @Description:根据指定条件获取病患检验结果数据 @param po @return List<PatientAssayRecordPO> @throws
     */
    List<PatientAssayRecordPO> listPatientAssayRecord(PatientAssayRecordPO po);

    /**
     * 根据创建时间查询出当天的数据
     * 
     * @Title: listByCreateTime
     * @param createTime
     * @param endCreateTime
     * @param fkPatientId
     * @return
     *
     */
    List<PatientAssayRecordPO> listByCreateTime(Date startCreateTime, Date endCreateTime, Long fkPatientId);

}
