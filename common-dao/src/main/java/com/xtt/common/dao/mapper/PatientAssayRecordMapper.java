package com.xtt.common.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayRecord;
import com.xtt.common.dao.po.PatientAssayRecordPO;

@Repository
public interface PatientAssayRecordMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_assay_record
     * 
     * @mbggenerated Wed Jan 27 20:16:53 CST 2016
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_assay_record
     * 
     * @mbggenerated Wed Jan 27 20:16:53 CST 2016
     */
    int insert(PatientAssayRecord record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_assay_record
     * 
     * @mbggenerated Wed Jan 27 20:16:53 CST 2016
     */
    int insertSelective(PatientAssayRecord record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_assay_record
     * 
     * @mbggenerated Wed Jan 27 20:16:53 CST 2016
     */
    PatientAssayRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_assay_record
     * 
     * @mbggenerated Wed Jan 27 20:16:53 CST 2016
     */
    int updateByPrimaryKeySelective(PatientAssayRecord record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table patient_assay_record
     * 
     * @mbggenerated Wed Jan 27 20:16:53 CST 2016
     */
    int updateByPrimaryKey(PatientAssayRecord record);

    /* 自定义 */
    /**
     * 通过结果生成字典数据
     * 
     * @Title: getLisDict
     * @param record
     * @return
     * 
     */
    List<PatientAssayRecordPO> getLisDict(@Param("startDateStr") java.lang.String startDateStr);

    /**
     * 根据指定条件获取病患检验结果数据
     * 
     * @Title: listPatientAssayRecord
     * @param po
     * @return
     *
     */
    List<PatientAssayRecordPO> listPatientAssayRecord(PatientAssayRecordPO po);

    /**
     * 根据创建时间查询出当天的数据
     * 
     * @Title: listByCreateTime
     * @param createTime
     * @param endCreateTime
     * @param fkPatientId
     * @param integer
     * @return
     *
     */
    List<PatientAssayRecordPO> listByCreateTime(@Param("startCreateTime") Date startCreateTime, @Param("endCreateTime") Date endCreateTime,
                    @Param("fkTenantId") Integer fkTenantId, @Param("fkPatientId") Long fkPatientId);

    /**
     * 根据itemCode 查询化验单
     * 
     * @Title: listByItemCode
     * @param startCreateTime
     * @param endCreateTime
     * @param itemCode
     * @param groupName
     * @param integer
     * @return
     *
     */
    List<PatientAssayRecord> listByItemCode(@Param("startCreateTime") Date startCreateTime, @Param("endCreateTime") Date endCreateTime,
                    @Param("itemCodeList") List<String> itemCode, @Param("groupName") String groupName, @Param("fkTenantId") Integer fkTenantId);

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
    PatientAssayRecordPO getByBeforeCount(@Param("beforeCount") String beforeCount, @Param("startCreateDate") Date startCreateDate,
                    @Param("endCreateDate") Date endCreateDate, @Param("strItemCode") String strItemCode, @Param("patientId") Long patientId,
                    @Param("fkTenantId") Integer fkTenantId, @Param("groupName") String groupName);

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
    List<PatientAssayRecordPO> listByAfterCount(@Param("afterCount") String afterCount, @Param("startCreateDate") Date startCreateDate,
                    @Param("endCreateDate") Date endCreateDate, @Param("groupName") String groupName, @Param("patientId") Long patientId,
                    @Param("fkTenantId") Integer tenantId, @Param("strItemCode") String strItemCode);

}