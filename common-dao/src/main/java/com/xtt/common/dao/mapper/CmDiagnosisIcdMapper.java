package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDiagnosisIcd;
import com.xtt.common.dao.po.CmDiagnosisIcdPO;

@Repository
public interface CmDiagnosisIcdMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDiagnosisIcd record);

    int insertSelective(CmDiagnosisIcd record);

    CmDiagnosisIcd selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDiagnosisIcd record);

    int updateByPrimaryKey(CmDiagnosisIcd record);

    List<CmDiagnosisIcdPO> listByfkPatientId(Long fkPatientId);
}