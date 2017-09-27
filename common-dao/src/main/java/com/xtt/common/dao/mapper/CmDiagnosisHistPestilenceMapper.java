package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDiagnosisHistPestilence;
import com.xtt.common.dao.po.CmDiagnosisHistPestilencePO;

@Repository
public interface CmDiagnosisHistPestilenceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDiagnosisHistPestilence record);

    int insertSelective(CmDiagnosisHistPestilence record);

    CmDiagnosisHistPestilencePO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDiagnosisHistPestilence record);

    int updateByPrimaryKeyWithBLOBs(CmDiagnosisHistPestilence record);

    int updateByPrimaryKey(CmDiagnosisHistPestilence record);

    /**
     * 根据患者Id获取患者诊断之传染病史集合数据
     * 
     * @Title: selectByPatient
     * @param patientId
     * @return
     *
     */
    List<CmDiagnosisHistPestilencePO> selectByPatient(Long patientId);
}