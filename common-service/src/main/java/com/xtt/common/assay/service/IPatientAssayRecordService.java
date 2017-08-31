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

import com.xtt.common.dao.model.PatientAssayRecord;
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

    /**
     * 根据itemCode 查询化验单
     * 
     * @Title: listByItemCode
     * @param startCreateTime
     * @param endCreateTime
     * @param itemCode
     * @param groupName
     * @return
     *
     */
    List<PatientAssayRecord> listByItemCode(Date startCreateTime, Date endCreateTime, List<String> itemCode, String groupName);

    /**
     * 根据患者id itemCode查询
     * 
     * @Title: listByPatientId
     * @param startTime
     * @param endTime
     * @param itemCode
     * @param fkPatientId
     * @param resultActual
     * @return
     *
     */
    Integer countByPatientId(Date startTime, Date endTime, String itemCode, Long fkPatientId, Double resultActual);

    /**
     * 根据化验项目条目查询得到所有的数据
     * 
     * @Title: listByBeforeCount
     * @param beforeCount
     * @param startCreateTime
     * @param endCreateTime
     * @param tenantId
     * @param strItemCode
     * @return
     *
     */
    List<PatientAssayRecordPO> listByAfterCount(Integer afterCount, Date startCreateDate, Date endCreateDate, String groupName, Long patientId,
                    Integer tenantId, String strItemCode);

    /**
     * 根据化验项目条目查询只去最近的一条
     * 
     * @Title: getByBeforeCount
     * @param beforeCount
     * @param sampleTime
     * @param startCreateTime
     * @param strItemCode
     * @param patientId
     * @param fkTenantId
     * @param groupName
     * @return
     *
     */
    PatientAssayRecordPO getByBeforeCount(Integer beforeCount, Date sampleTime, Date startCreateTime, String strItemCode, Long patientId,
                    Integer fkTenantId, String groupName);

}
