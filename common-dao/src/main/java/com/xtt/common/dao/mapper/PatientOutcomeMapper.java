package com.xtt.common.dao.mapper;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientOutcome;
import com.xtt.common.dao.po.PatientOutcomePO;

@Repository
public interface PatientOutcomeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PatientOutcome record);

    int insertSelective(PatientOutcome record);

    PatientOutcome selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PatientOutcome record);

    int updateByPrimaryKey(PatientOutcome record);

    /*use define*/
    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param record
     * @return
     *
     */
    List<PatientOutcomePO> selectByCondition(PatientOutcomePO record);

    /**
     * 查询患者最新的一条转归记录
     * 
     * @Title: listLatest
     * @param patientIds
     * @param month
     * @param multiTenant
     * @param sysOwner
     * @return
     *
     */
    List<PatientOutcomePO> listLatest(@Param("patientIds") Collection<Long> patientIds, @Param("month") String month,
                    @Param("multiTenant") String multiTenant, @Param("sysOwner") String sysOwner, @Param("startTime") Date startTime,
                    @Param("endTime") Date endTime);

    /**
     * 根据患者id查询转归次数
     * 
     * @Title: selectCountByPatientId
     * @param outcomeRecord
     * @return
     *
     */
    Integer selectCountByPatientId(PatientOutcomePO outcomeRecord);

    /**
     * 根据时间月份查询转归患者
     * 
     * @Title: selectPatientByMonth
     * @param startDate
     * @param endDate
     * @param types
     * @return
     *
     */
    List<Long> selectPatientByMonth(@Param("startTime") Date startTime, @Param("endTime") Date endTime,
                    @Param("excludeTypes") List<String> excludeTypes);
}