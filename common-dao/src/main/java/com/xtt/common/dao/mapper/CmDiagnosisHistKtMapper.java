package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDiagnosisHistKt;
import com.xtt.common.dao.po.CmDiagnosisHistKtPO;

@Repository
public interface CmDiagnosisHistKtMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDiagnosisHistKt record);

    int insertSelective(CmDiagnosisHistKt record);

    CmDiagnosisHistKtPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDiagnosisHistKt record);

    int updateByPrimaryKeyWithBLOBs(CmDiagnosisHistKt record);

    int updateByPrimaryKey(CmDiagnosisHistKt record);

    /**
     * 根据患者Id获取患者诊断之肾移植史集合数据
     * 
     * @Title: selectByPatient
     * @param patientId
     * @param multiTenant
     * @return
     *
     */
    List<CmDiagnosisHistKtPO> selectByPatient(@Param("patientId") Long patientId, @Param("groupTenant") String groupTenant);
}