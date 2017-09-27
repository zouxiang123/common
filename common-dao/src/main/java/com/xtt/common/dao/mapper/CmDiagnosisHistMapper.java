package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDiagnosisHist;
import com.xtt.common.dao.po.CmDiagnosisHistPO;

@Repository
public interface CmDiagnosisHistMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDiagnosisHist record);

    int insertSelective(CmDiagnosisHist record);

    CmDiagnosisHistPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDiagnosisHist record);

    int updateByPrimaryKey(CmDiagnosisHist record);

    /**
     * 根据患者Id获取询问病史主表数据
     * 
     * @Title: selectByPatient
     * @param patientId
     * @return
     *
     */
    List<CmDiagnosisHistPO> selectByPatient(Long patientId);
}