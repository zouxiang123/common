package com.xtt.common.dao.mapper;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * 根据自定义条件查询数据 1、电子病历-->化验信息-->日期查询列表返回化验数据[多参数请求]
     * 
     * @Title: selectByCondition
     * @param patientAssayRecord
     * @return
     * 
     */
    List<PatientAssayRecordPO> selectByCondition(PatientAssayRecord patientAssayRecord);

    /**
     * 电子病历-->化验信息-->日期查询列表返回化验数据
     * 
     * @Title: selectByCondition
     * @param patientAssayRecord
     * @return
     *
     */
    List<PatientAssayRecordPO> selectByCondition(PatientAssayRecordPO patientAssayRecord);

    /**
     * 查询不同化验时间的化验记录
     * 
     * @Title: selectAssayRecord
     * @param record
     * @return
     * 
     */
    List<PatientAssayRecordPO> selectDateAssayRecord(PatientAssayRecordPO record);

    /**
     * 根据条件查找报告数据
     * 
     * @Title: selectReportData
     * @param patientId
     * @param startTime
     * @param endTime
     * @param pId
     * @return
     * 
     */
    List<Map<String, Object>> selectReportData(@Param("patientId") Long patientId, @Param("startTime") Date startTime, @Param("endTime") Date endTime,
                    @Param("itemCode") String itemCode);

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
     * 根据月份查询月份的化验明细
     * 
     * @Title: selectByMonth
     * @param patientAssayRecord
     * @return
     * 
     */
    List<PatientAssayRecordPO> selectByMonth(PatientAssayRecordPO patientAssayRecord);

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
     * 差询患者所有的类别列表
     * 
     * @Title: selectCategoryListByPatientId
     * @param patientId
     * @param tenantId
     * @return
     * 
     */
    @Deprecated
    List<Map<String, Object>> selectCategoryListByPatientId(@Param("patientId") Long patientId, @Param("tenantId") Integer tenantId);

    /**
     * 查询患者某天的类别列表
     * 
     * @Title: selectCategoryList
     * @param record
     * @return
     * 
     */
    List<PatientAssayRecordPO> selectCategoryList(PatientAssayRecordPO record);

    /**
     * 获取患者最新一次的化验数据
     * 
     * @Title: selectLatestAssayDateByTenantId
     * @param tenantId
     * @param endTIme
     * @param startTime
     * @return
     * 
     */
    List<Map<String, Object>> selectLatestAssayDateByTenantId(@Param("tenantId") Integer tenantId, @Param("startTime") Date startTime,
                    @Param("endTime") Date endTime, @Param("fkPatientId") Long fkPatientId);

    /**
     * 根据自定义code查询数据
     * 
     * @Title: selectByFkDictCode
     * @param record
     * @return
     * 
     */
    List<PatientAssayRecordPO> selectByFkDictCode(PatientAssayRecord record);

    /**
     * 查询患者化验详情
     */
    List<PatientAssayRecordPO> selectPatientAssayDetail(@Param("startTime") String startTime, @Param("endTime") String endTime,
                    @Param("patientId") Long patientId, @Param("assayClass") String assayClass, @Param("fkTenantId") Integer tenantId);

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
    List<Map<String, Object>> selectAssayList(@Param("patientId") Long patientId, @Param("assayDateFrom") String assayDateFrom,
                    @Param("assayDateTo") String assayDateTo);

    /**
     * 根据患者、日期查询化验项
     * 
     * @Title: selectAssayItems
     * @param patientId
     * @param assayDate
     * @return
     *
     */
    List<PatientAssayRecord> selectAssayItems(@Param("patientId") Long patientId, @Param("assayDate") String assayDate);

    /**
     * 根据itemCode和月份查询数据
     * 
     * @Title: selectByItemCodes
     * @param itemCodes
     * @param month
     * @return
     *
     */
    List<PatientAssayRecordPO> selectByItemCodes(@Param("itemCodes") Collection<String> itemCodes, @Param("startDate") Date startDate,
                    @Param("endDate") Date endDate, @Param("patientTempValue") String patientTempValue, @Param("fkTenantId") Integer tenantId);

    /**
     * @param list
     *            手动录入化验信息
     */
    void insertPatientAssay(List<PatientAssayRecordPO> list);

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
     * @param par
     * @return
     */
    List<PatientAssayRecordPO> selectByGroupId(PatientAssayRecordPO par);

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
     * 根据条件查询患者某项目最新的数据
     * 
     * @Title: selectItemLatestDataByCondition
     * @param record
     * @return
     *
     */
    List<PatientAssayRecordPO> selectItemLatestDataByCondition(PatientAssayRecord record);

    /**
     * 根据当前参数日期获取患者原始化验结果数据
     * 
     * @Title: selectPatientAssayRecordByAssayDate
     * @param month
     * @param tenantId
     * @return
     *
     */
    List<PatientAssayRecord> selectPatientAssayRecordByAssayDate(@Param("month") String month, @Param("tenantId") Integer tenantId);

    /**
     * @Title: listPatientAssayRecord @Description:根据指定条件获取病患检验结果数据 @param po @return List<PatientAssayRecordPO> @throws
     */
    List<PatientAssayRecordPO> listPatientAssayRecord(PatientAssayRecordPO po);

    /**
     * 根据创建时间查询出当天的数据
     * 
     * @Title: listByCreateTime
     * @param createTime
     * @return
     *
     */
    List<PatientAssayRecordPO> listByCreateTime(Date createTime);

}