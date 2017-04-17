package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDiagnosisHistAllergy;
import com.xtt.common.dao.po.CmDiagnosisHistAllergyPO;

@Repository
public interface CmDiagnosisHistAllergyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDiagnosisHistAllergy record);

    int insertSelective(CmDiagnosisHistAllergy record);

    CmDiagnosisHistAllergyPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDiagnosisHistAllergy record);

    int updateByPrimaryKeyWithBLOBs(CmDiagnosisHistAllergy record);

    int updateByPrimaryKey(CmDiagnosisHistAllergy record);

    /**
     * 根据患者Id获取患者诊断之过敏史集合数据
     * 
     * @Title: selectByPatient
     * @param patientId
     * @param multiTenant
     * @return
     *
     */
    List<CmDiagnosisHistAllergyPO> selectByPatient(@Param("patientId") Long patientId, @Param("multiTenant") String multiTenant);
}