package com.xtt.common.dao.mapper;

import java.util.List;

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
    List<PatientOutcomePO> selectByCondition(PatientOutcome record);
}