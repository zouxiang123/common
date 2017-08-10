package com.xtt.common.dao.mapper;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayRecordBusi;
import com.xtt.common.dao.po.PatientAssayRecordBusiPO;

@Repository
public interface PatientAssayRecordBusiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PatientAssayRecordBusi record);

    int insertSelective(PatientAssayRecordBusi record);

    PatientAssayRecordBusi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PatientAssayRecordBusi record);

    int updateByPrimaryKey(PatientAssayRecordBusi record);

    /*user define*/

    int countByInspectionId(String inspectionId);

    void insertList(@Param("list") List<PatientAssayRecordBusi> list);

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
     * 根据reqId，patientId，SampleTime 更新投后透前逻辑
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
     * @param record
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
     * @param itemCodes
     * @return
     *
     */
    List<Map<String, Object>> listReportData(@Param("fkPatientId") Long fkPatientId, @Param("startDate") Date startDate,
                    @Param("endDate") Date endDate, @Param("itemCode") String itemCode, @Param("itemCodes") Collection<String> itemCodes);

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
    List<Map<String, Object>> listApiAssayList(@Param("patientId") Long patientId, @Param("startDate") Date startDate,
                    @Param("endDate") Date endDate);

    /**
     * 根据患者、日期查询化验项(api用)
     * 
     * @Title: listApiAssayItems
     * @param patientId
     * @param assayDate
     * @return
     *
     */
    List<PatientAssayRecordBusi> listApiAssayItems(@Param("patientId") Long patientId, @Param("assayDate") Date assayDate);

    /**
     * 查询患者个人统计报表数据
     * 
     * @Title: listForPersonReport
     * @param record
     * @return
     *
     */
    List<Map<String, Object>> listForPersonReport(PatientAssayRecordBusiPO record);

    /**
     * 根据租户id查询所有化验月份
     * 
     * @Title: listAllAssayMonthByTenantId
     * @param fkTenantId
     * @return
     *
     */
    List<String> listAllAssayMonthByTenantId(Integer fkTenantId);

    /**
     * 查询透前透后统计报表数据
     * 
     * @Title: listForBeforeAfterReport
     * @param record
     * @return
     *
     */
    List<PatientAssayRecordBusiPO> listForBeforeAfterReport(PatientAssayRecordBusiPO record);

    /**
     * 根据患者id和ItemCodes查询最新的一条数字
     * 
     * @Title: listLatestByFkDictCodes
     * @param fkPatientId
     * @param fkDictCodes
     * @param fkTenantId
     * @param date
     * @return
     *
     */
    List<PatientAssayRecordBusiPO> listLatestByFkDictCodes(@Param("fkPatientId") Long fkPatientId,
                    @Param("fkDictCodes") Collection<String> fkDictCodes, @Param("fkTenantId") Integer fkTenantId, @Param("date") Date date);
}