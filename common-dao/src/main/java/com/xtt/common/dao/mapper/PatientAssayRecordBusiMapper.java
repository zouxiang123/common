package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayRecord;
import com.xtt.common.dao.model.PatientAssayRecordBusi;

@Repository
public interface PatientAssayRecordBusiMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PatientAssayRecordBusi record);

    int insertSelective(PatientAssayRecordBusi record);

    PatientAssayRecordBusi selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PatientAssayRecordBusi record);

    int updateByPrimaryKey(PatientAssayRecordBusi record);

    /*user define*/

    int countByInspectionId(String inspectionId);

    void insertList(@Param("list") List<PatientAssayRecordBusi> list);

    /**
     * 根据项目唯一ID更新透析前后标示
     * 
     * @Title: updateByInspectionId
     * @param patientAssayRecord
     *
     */
    void updateByInspectionId(PatientAssayRecord patientAssayRecord);

    /**
     * 根据reqId，patientId，SampleTime 更新投后透前逻辑
     * 
     * @Title: updateDiaAbFlagByReqId
     * @param patientAssayRecordBusi
     *
     */
    void updateDiaAbFlagByReqId(PatientAssayRecordBusi patientAssayRecordBusi);

}