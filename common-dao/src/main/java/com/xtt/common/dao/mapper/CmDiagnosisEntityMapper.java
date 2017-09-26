package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDiagnosisEntity;
import com.xtt.common.dao.po.CmDiagnosisEntityPO;

@Repository
public interface CmDiagnosisEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDiagnosisEntity record);

    int insertSelective(CmDiagnosisEntity record);

    CmDiagnosisEntityPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDiagnosisEntity record);

    int updateByPrimaryKey(CmDiagnosisEntity record);

    List<CmDiagnosisEntityPO> selectEntitiesByPatient(CmDiagnosisEntityPO entity);
}