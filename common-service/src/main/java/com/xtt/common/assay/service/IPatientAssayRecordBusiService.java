/**   
 * @Title: IDictHospitalLabService.java 
 * @Package com.xtt.common.patient
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年4月22日 下午12:54:10 
 *
 */
package com.xtt.common.assay.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xtt.common.dao.model.PatientAssayInspectioidBack;
import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;

public interface IPatientAssayRecordBusiService {

    /**
     * 根据自定义条件查询常用项
     * 
     * @Title: listByCondition
     * @param query
     * @return
     *
     */
    List<PatientAssayRecordBusiPO> listByCondition(PatientAssayRecordBusiPO query);

    /**
     * 根据Inspection_Id 查询是否唯一
     * 
     * @Title: countByInspectionId
     * @param inspectionId
     * @param fkTenantId
     * @return
     *
     */
    int countByInspectionId(String inspectionId, Integer fkTenantId);

    /**
     * 批量插入
     * 
     * @Title: insertList
     * @param listPatientAssayRecordBusi
     *
     */
    void insertList(List<PatientAssayRecordBusi> listPatientAssayRecordBusi);

    /**
     * 根据条件更新透前透后标识
     * 
     * @Title: updateDiaAbFlagByReqId
     * @param patientAssayRecordBusi
     *
     */
    void updateDiaAbFlagByReqId(PatientAssayRecordBusi patientAssayRecordBusi);

    /**
     * 查询所有类别
     * 
     * @Title: listCategory
     * @param query
     * @return
     * 
     */
    List<PatientAssayRecordBusiPO> listCategory(PatientAssayRecordBusiPO record);

    /**
     * 根据条件查询报表数据
     * 
     * @Title: listReportData
     * @param fkPatientId
     * @param startDate
     * @param endDate
     * @param itemCode
     * @return
     *
     */
    List<Map<String, Object>> listReportData(Long fkPatientId, Date startDate, Date endDate, String itemCode);

    /**
     * 查询患者的化验单(api用)
     * 
     * @Title: listApiAssayList
     * @param patientId
     * @param startDate
     * @param endDate
     * @return
     *
     */
    List<Map<String, Object>> listApiAssayList(Long patientId, Date startDate, Date endDate);

    /**
     * 根据患者、日期查询化验项(api用)
     * 
     * @Title: listApiAssayItems
     * @param patientId
     * @param assayDate
     * @return
     *
     */
    List<PatientAssayRecordBusi> listApiAssayItems(Long patientId, Date assayDate);

    /**
     * 根据关联字典编号查询数据
     * 
     * @Title: listByFkDictCode
     * @param query
     * @return
     *
     */
    List<PatientAssayRecordBusiPO> listByFkDictCode(PatientAssayRecordBusiPO query);

    /**
     * 根据itemCodes和日期查询数据
     * 
     * @Title: listByItemCodes
     * @param itemCodes
     * @param startDate
     * @param endDate
     * @return
     *
     */
    List<PatientAssayRecordBusiPO> listByItemCodes(Set<String> itemCodes, Date startDate, Date endDate);

    /**
     * 根据id删除数据
     * 
     * @Title: removeById
     * @param id
     *
     */
    void removeById(Long id);

    /**
     * 查询个人统计报表数据
     * 
     * @Title: listForPersonReport
     * @param patientId
     * @param startDate
     * @param endDate
     * @param itemCode
     * @return
     *
     */
    List<Map<String, Object>> listForPersonReport(Long patientId, Date startDate, Date endDate, String itemCode);

    /**
     * 查询所有化验月份
     * 
     * @Title: listAllAssayMonthByTenantId
     * @param tenantId
     * @return
     *
     */
    List<String> listAllAssayMonthByTenantId(Integer tenantId);

    /**
     * 查询透前透后统计化验数据
     * 
     * @Title: listForBeforeAfterReport
     * @param record
     * @return
     *
     */
    List<PatientAssayRecordBusiPO> listForBeforeAfterReport(PatientAssayRecordBusiPO record);

    /**
     * 根据患者id和fkDictCodes查询离日期最近的一条数据
     * 
     * @Title: listLatestByFkDictCode
     * @param fkPatientId
     * @param itemCodes
     * @param tenantId
     * @param date
     * @param isBefore
     *            是否透前（null时不区分透前透后）
     * @return
     *
     */

    List<PatientAssayRecordBusiPO> listLatestOneByFkDictCodes(Long fkPatientId, Collection<String> fkDictCodes, Integer tenantId, Date date,
                    Boolean isBefore);

    /**
     * 据患者id和fkDictCode查询离日期最近的一条数据
     * 
     * @Title: getLatestOneByFkDictCode
     * @param fkPatientId
     * @param fkDictCode
     * @param tenantId
     * @param date
     * @param isBefore
     * @return
     *
     */
    PatientAssayRecordBusiPO getLatestOneByFkDictCode(Long fkPatientId, String fkDictCode, Integer tenantId, Date date, Boolean isBefore);

    void updatePatientAssay(List<AssayHospDictPO> getdHL);

    void insertPatientAssay(List<AssayHospDictPO> getdHL);

    /**
     * 根据患者id删除
     * 
     * @Title: deleteByPatient
     * @param fkPatientId
     * @param fkTenantId
     *
     */
    void deleteByPatientId(Long fkPatientId, Integer fkTenantId);

    /**
     * 从数据源获取数据并插入
     * 
     * @Title: insertFromSource
     * @param startCreateTime
     * @param endCreateTime
     * @param mapPatientId
     * @param patientId
     * @param isDelete
     * @param fktenantId
     *
     */
    Set<String> selectInsertFromSource(Date startCreateTime, Date endCreateTime, Map<Long, List<Date>> mapPatientId, Long patientId, boolean isDelete,
                    Integer fktenantId);

    /**
     * HEAD 查询常规化验项目
     * 
     * @Title: selectCommonByItemCode
     * @param patientAssayRecordBusi
     * @return
     *
     */
    List<PatientAssayRecordBusi> selectCommonByItemCode(PatientAssayRecordBusiPO patientAssayRecordBusi);

    /**
     * 根据租户id删除数据
     * 
     * @Title: deleteByTenant
     * @param fkTenantId
     *
     */
    void deleteByTenant(Integer fkTenantId);

    /**
     * 根据申请单号查询
     * 
     * @Title: listByReqId
     * @param par
     * @return
     *
     */
    List<PatientAssayRecordBusiPO> listByReqId(PatientAssayRecordBusiPO par);

    /**
     * 查询距离date最近的count*2（前后）条数据
     * 
     * @Title: listLatestByFkDictCode
     * @param fkPatientId
     * @param dictCode
     * @param tenantId
     * @param date
     * @param count
     * @param diaAbFlag
     * @return
     *
     */
    List<PatientAssayRecordBusiPO> listLatestByFkDictCode(Long fkPatientId, String dictCode, Integer tenantId, Date date, int count,
                    String diaAbFlag);

    /**
     * 根据备份的标识更新标识数据
     * 
     * @Title: updateDiaAbFlagByInspectionIdBack
     * @param fkPatientId
     * @param tenantId
     *
     */
    void updateDiaAbFlagByInspectionIdBack(Long fkPatientId, Integer tenantId);

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
    List<Map<String, Object>> listByAfterCount(Integer afterCount, Date startCreateDate, Date endCreateDate, String groupName, Long patientId,
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
    Map<String, Object> getByBeforeCount(Integer beforeCount, Date sampleTime, Date startCreateTime, String strItemCode, Long patientId,
                    Integer fkTenantId, String groupName);

    /**
     * 手动更新透前透后标识
     * 
     * @Title: updateDiaAbFlag
     * @param assayRecord
     *
     */
    void updateHandDiaAbFlag(PatientAssayRecordBusi assayRecord);

    /**
     * 根据申请单号更新透前透后字段
     * 
     * @Title: updateDiaAbFlagByReqId
     * @param reqList
     *
     */
    void updateDiaAbFlagByReqId(List<PatientAssayRecordBusi> reqList);

    /**
     * 获取透后标识对象
     * 
     * @Title: getInspectioidBack
     * @param inspectionId
     * @param patientId
     * @param diaAbFlag
     * @param tenantId
     * @return
     *
     */
    public PatientAssayInspectioidBack getInspectioidBack(String inspectionId, Long patientId, String diaAbFlag, Integer tenantId);

}
