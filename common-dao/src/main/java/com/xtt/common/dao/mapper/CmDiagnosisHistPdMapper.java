package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDiagnosisHistPd;
import com.xtt.common.dao.po.CmDiagnosisHistPdPO;

@Repository
public interface CmDiagnosisHistPdMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDiagnosisHistPd record);

    int insertSelective(CmDiagnosisHistPd record);

    CmDiagnosisHistPdPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDiagnosisHistPd record);

    int updateByPrimaryKeyWithBLOBs(CmDiagnosisHistPd record);

    int updateByPrimaryKey(CmDiagnosisHistPd record);

    /**
     * 根据患者Id获取患者诊断之传腹透史集合数据
     * 
     * @Title: selectByPatient
     * @param patientId
     * @return
     *
     */
    List<CmDiagnosisHistPdPO> selectByPatient(Long patientId);
}