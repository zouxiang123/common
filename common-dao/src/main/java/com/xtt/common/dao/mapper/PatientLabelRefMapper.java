package com.xtt.common.dao.mapper;

import java.util.Collection;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientLabelRef;

@Repository
public interface PatientLabelRefMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PatientLabelRef record);

    int insertSelective(PatientLabelRef record);

    PatientLabelRef selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PatientLabelRef record);

    int updateByPrimaryKey(PatientLabelRef record);

    /*user define*/
    /**
     * 根据自定义条件删除数据
     * 
     * @Title: deleteByCondition
     * @param patientIds
     * @param fkTenantId
     * @param sysOwner
     *
     */
    void deleteByCondition(@Param("patientIds") Collection<Long> patientIds, @Param("fkTenantId") Integer fkTenantId,
                    @Param("fkLabelId") Long fkLabelId, @Param("sysOwner") String sysOwner);
}