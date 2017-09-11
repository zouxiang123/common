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
    /**
     * 根据记录单唯一标识和租户id查询数据
     * 
     * @Title: countByInspectionId
     * @param inspectionId
     * @param fkTenantId
     * @return
     *
     */
    int countByInspectionId(@Param("inspectionId") String inspectionId, @Param("fkTenantId") Integer fkTenantId);

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
     * 根据患者id和fkDictCodes查询离日期最近的一条数据
     * 
     * @Title: listLatestOneByFkDictCodes
     * @param fkPatientId
     * @param fkDictCodes
     * @param fkTenantId
     * @param date
     * @param diaAbFlag
     * @return
     *
     */

    List<PatientAssayRecordBusiPO> listLatestOneByFkDictCodes(@Param("fkPatientId") Long fkPatientId,
                    @Param("fkDictCodes") Collection<String> fkDictCodes, @Param("fkTenantId") Integer fkTenantId, @Param("date") Date date,
                    @Param("diaAbFlag") String diaAbFlag);

    /**
     * 更新数值
     * 
     * @Title: updatePatientAssay
     * @param patientAssayRecordBusi
     *
     */
    void updatePatientAssay(PatientAssayRecordBusi patientAssayRecordBusi);

    /**
     * 根据患者id删除
     * 
     * @Title: deleteByPatient
     * @param fkPatientId
     * @param fkTenantId
     *
     */
    void deleteByPatientId(@Param("fkPatientId") Long fkPatientId, @Param("fkTenantId") Integer fkTenantId);

    /**
     * HEAD 查询常规化验项
     * 
     * @Title: selectCommonByItemCode
     * @param patientAssayRecordBusi
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
    void deleteByTenant(@Param("fkTenantId") Integer fkTenantId);

    /**
     * 查询距离date最近的count*2（前后）条数据
     * 
     * @Title: listLatestByFkDictCode
     * @return
     *
     */
    List<PatientAssayRecordBusiPO> listLatestByFkDictCode(@Param("paramList") List<Map<String, Object>> paramList);

    /**
     * 根据备份的标识更新标识数据
     * 
     * @Title: updateDiaAbFlagByInspectionIdBack
     * @param fkPatientId
     * @param fkTenantId
     *
     */
    void updateDiaAbFlagByInspectionIdBack(@Param("fkPatientId") Long fkPatientId, @Param("fkTenantId") Integer fkTenantId);

    /**
     * 根据化验单条数查询透后化验数据
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
    List<Map<String, Object>> listByAfterCount(@Param("afterCount") Integer afterCount, @Param("startCreateDate") Date startCreateDate,
                    @Param("endCreateDate") Date endCreateDate, @Param("groupName") String groupName, @Param("patientId") Long patientId,
                    @Param("fkTenantId") Integer tenantId, @Param("strItemCode") String strItemCode);

    /**
     * 根据化验单条数查询化验数据
     * 
     * @Title: listByBeforeCount
     * @param beforeCount
     * @param startCreateTime
     * @param endCreateTime
     * @param tenantId
     * @param strItemCode
     * @param groupName
     * @return
     *
     */
    Map<String, Object> getByBeforeCount(@Param("beforeCount") Integer beforeCount, @Param("startCreateDate") Date startCreateDate,
                    @Param("endCreateDate") Date endCreateDate, @Param("strItemCode") String strItemCode, @Param("patientId") Long patientId,
                    @Param("fkTenantId") Integer fkTenantId, @Param("groupName") String groupName);
}