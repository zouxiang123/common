package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDiagnosisHistHd;
import com.xtt.common.dao.po.CmDiagnosisHistHdPO;

@Repository
public interface CmDiagnosisHistHdMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDiagnosisHistHd record);

    int insertSelective(CmDiagnosisHistHd record);

    CmDiagnosisHistHdPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDiagnosisHistHd record);

    int updateByPrimaryKeyWithBLOBs(CmDiagnosisHistHd record);

    int updateByPrimaryKey(CmDiagnosisHistHd record);

    /**
     * 根据患者Id获取患者诊断之血透史集合数据
     * 
     * @Title: selectByPatient
     * @param patientId
     * @param multiTenant
     * @return
     *
     */
    List<CmDiagnosisHistHdPO> selectByPatient(@Param("patientId") Long patientId, @Param("groupTenant") String groupTenant);
}