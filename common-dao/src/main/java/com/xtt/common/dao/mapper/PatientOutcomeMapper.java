package com.xtt.common.dao.mapper;

import java.util.Collection;
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
                    @Param("multiTenant") String multiTenant, @Param("sysOwner") String sysOwner);
}