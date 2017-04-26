/**   
 * @Title: IPatientAssayRecordService.java 
 * @Package com.xtt.common.patient.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年1月29日 上午9:38:24 
 *
 */
package com.xtt.common.assay.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xtt.common.dao.model.PatientAssayRecord;
import com.xtt.common.dao.po.DictHospitalLabPO;
import com.xtt.common.dao.po.PatientAssayRecordPO;

public interface IPatientAssayRecordService {
    /**
     * 查询不同化验时间的化验记录
     * 
     * @Title: getAssayRecord
     * @return
     * 
     */
    List<PatientAssayRecordPO> getAssayDateRecord(PatientAssayRecordPO record);

    /**
     * 获取某项化验单报表数据
     * 
     * @Title: getReportData
     * @param patientId
     * @param startTime
     * @param endTime
     * @param itemCode
     * @return
     * 
     */

    List<Map<String, Object>> getReportData(Long patientId, Date startTime, Date endTime, String itemCode);

    /**
     * 根据自定义条件获取数据
     * 
     * @Title: getByCondition
     * @param record
     * @return
     * 
     */
    List<PatientAssayRecordPO> selectByCondition(PatientAssayRecordPO record);

    /**
     * 根据化验日期获取患者化验数据
     * 
     * @Title: getByAssayDate
     * @param date
     * @return
     * 
     */
    List<PatientAssayRecordPO> getByAssayDate(String date, Long patientId);

    /**
     * 根据时间查询报表信息
     * 
     * @Title: selectByAssayTime
     * @param patientAssayRecord
     * @return
     * 
     */
    List<PatientAssayRecordPO> selectReportByAssayTime(PatientAssayRecordPO patientAssayRecord);

    /**
     * 查询相关统计。求总数量，总和，均值，标准差
     * 
     * @Title: selectStatisticsReport
     * @param patientAssayRecord
     * @return
     * 
     */
    List<PatientAssayRecordPO> selectStatisticsReport(PatientAssayRecordPO patientAssayRecord);

    /**
     * 查询患者所有的类别列表
     * 
     * @Title: categoryList
     * @param patientId
     * @return
     * 
     */
    @Deprecated
    List<Map<String, Object>> getCategoryListByPatientId(Long patientId);

    /**
     * 查询类别列表
     * 
     * @Title: getCategoryList
     * @param query
     * @return
     * 
     */
    List<PatientAssayRecordPO> getCategoryList(PatientAssayRecordPO record);

    /**
     * 获取患者最新一次的化验数据
     * 
     * @Title: selectLatestAssayDateByTenantId
     * @param tenantId
     * @return
     * 
     */
    List<Map<String, Object>> selectLatestAssayDateByTenantId(Integer tenantId, Date startTime, Date endTime);

    /**
     * 根据月份查询月份的化验明细
     * 
     * @Title: selectByMonth
     * @param patientAssayRecord
     * @return
     * 
     */
    List<PatientAssayRecordPO> selectByMonth(PatientAssayRecordPO patientAssayRecord);

    /**
     * 根据自定义code查询数据
     * 
     * @Title: selectByFkDictCode
     * @param record
     * @return
     * 
     */
    List<PatientAssayRecordPO> selectByFkDictCode(PatientAssayRecordPO record);

    /**
     * 查询患者化验详情
     */
    List<PatientAssayRecordPO> selectPatientAssayDetail(String startTime, String endTime, Long patientId, String assayClass);

    /**
     * 查询患者的化验单
     * 
     * @Title: selectAssayList
     * @param patientId
     * @param assayDateFrom
     * @param assayDateTo
     * @return
     *
     */
    List<Map<String, Object>> selectAssayList(Long patientId, String assayDateFrom, String assayDateTo);

    /**
     * 根据患者、日期查询化验项
     * 
     * @Title: selectAssayItems
     * @param patientId
     * @param assayDate
     * @return
     *
     */
    List<PatientAssayRecord> selectAssayItems(Long patientId, String assayDate);

    /**
     * 根据itemCode和月份查询数据
     * 
     * @Title: selectByItemCodes
     * @param itemCodes
     * @param month
     * @return
     *
     */
    List<PatientAssayRecordPO> selectByItemCodes(Collection<String> itemCodes, Date startDate, Date endDate, String patientTempValue);

    /**
     * 根据特定患者查询阶段小结数据
     */
    List<Map<String, Object>> selectLatestAssayDateByPatient(Integer tenantId, Date startTime, Date endTime, Long fkPatientId);

    /**
     * 手动增加化验单
     * 
     * @param dHL
     */
    void insertPatientAssay(List<DictHospitalLabPO> dHL);

    /**
     * 修改手动录入的化验项
     * 
     * @param dHL
     * @return
     */
    void updatePatientAssay(List<DictHospitalLabPO> dHL);

    /**
     * 删除手动录入的化验项
     * 
     * @param groupId
     * @return
     */
    int deleteById(String id);

    /**
     * 查询所有手动录入的化验项
     * 
     * @param groupId
     * @return
     */
    List<PatientAssayRecordPO> selectByGroupId(PatientAssayRecordPO par);

    /**
     * 根据条件查询患者某项目最新的数据
     * 
     * @Title: selectItemLatestDataByCondition
     * @param record
     * @return
     *
     */
    List<PatientAssayRecordPO> selectItemLatestDataByCondition(PatientAssayRecordPO record);
    
    /**
      * @Title: listPatientAssayRecord 
      * @Description:根据指定条件获取病患检验结果数据
      * @param po
      * @return List<PatientAssayRecordPO>
      * @throws
      */
     List<PatientAssayRecordPO> listPatientAssayRecord(PatientAssayRecordPO po);
}
