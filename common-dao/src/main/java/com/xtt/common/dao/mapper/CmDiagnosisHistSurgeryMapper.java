package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDiagnosisHistSurgery;
import com.xtt.common.dao.po.CmDiagnosisHistSurgeryPO;

@Repository
public interface CmDiagnosisHistSurgeryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDiagnosisHistSurgery record);

    int insertSelective(CmDiagnosisHistSurgery record);

    CmDiagnosisHistSurgeryPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDiagnosisHistSurgery record);

    int updateByPrimaryKey(CmDiagnosisHistSurgery record);

    /**
     * 根据患者Id获取患者诊断之手术史集合数据
     * 
     * @Title: selectByPatient
     * @param patientId
     * @return
     *
     */
    List<CmDiagnosisHistSurgeryPO> selectByPatient(Long patientId);
}