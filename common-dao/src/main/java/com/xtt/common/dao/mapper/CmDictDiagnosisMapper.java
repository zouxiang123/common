package com.xtt.common.dao.mapper;

import com.xtt.common.dao.model.CmDictDiagnosis;

public interface CmDictDiagnosisMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDictDiagnosis record);

    int insertSelective(CmDictDiagnosis record);

    CmDictDiagnosis selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDictDiagnosis record);

    int updateByPrimaryKey(CmDictDiagnosis record);
}