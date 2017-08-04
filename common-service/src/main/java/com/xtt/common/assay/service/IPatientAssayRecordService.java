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

import org.apache.ibatis.annotations.Param;

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
     * @return
     *
     */
    List<PatientAssayRecordPO> listByCreateTime(@Param("startCreateTime") Date startCreateTime, @Param("endCreateTime") Date endCreateTime);

    /**
     * 根据itemCode 查询化验单
     * 
     * @Title: listByItemCode
     * @param startCreateTime
     * @param endCreateTime
     * @param itemCode
     * @return
     *
     */
    List<PatientAssayRecord> listByItemCode(@Param("startCreateTime") Date startCreateTime, @Param("endCreateTime") Date endCreateTime,
                    @Param("itemCodeList") List<String> itemCode);

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
    List<PatientAssayRecordPO> listByAfterCount(String afterCount, Date startCreateTime, Date endCreateTime, Integer fkTenantId, String strItemCode);

    /**
     * 根据化验项目条目查询只去最近的一条
     * 
     * @Title: getByBeforeCount
     * @param beforeCount
     * @param sampleTime
     * @param startCreateTime
     * @param strItemCode
     * @param fkPatientId
     * @param fkTenantId
     * @return
     *
     */
    PatientAssayRecordPO getByBeforeCount(String beforeCount, Date sampleTime, Date startCreateTime, String strItemCode, Long fkPatientId,
                    Integer fkTenantId);

}
