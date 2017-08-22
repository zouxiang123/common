package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.PatientAssayData;
import com.xtt.common.dao.model.PatientAssayRecord;
import com.xtt.common.dao.po.AssayHospDictPO;
import com.xtt.common.dao.po.PatientAssayDataPO;

@Repository
public interface PatientAssayDataMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PatientAssayData record);

    int insertSelective(PatientAssayData record);

    PatientAssayData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PatientAssayData record);

    int updateByPrimaryKey(PatientAssayData record);

    /**
     * 获取原始化验项数据
     * 
     * @Title: findByPatientAssayRecord
     * @param type
     *            获取类型[b范围查询，d日期查询]
     * @param startTime
     *            起始日期/单日期查询
     * @param endTime
     *            结束日期
     * @return
     */
    List<PatientAssayRecord> findByPatientAssayRecord(PatientAssayDataPO patientAssayData);

    /**
     * 获取原始化验项数据中最小日期
     * 
     * @Title: selectByPatientAssayRecordMinDate
     * @return
     *
     */
    String selectByPatientAssayRecordMinDate();

    /**
     * 统计是否存在记录
     * 
     * @Title: selectByPatientAssayDataCount
     * @return
     *
     */
    int selectByPatientAssayDataCount(@Param("checkDate") String checkDate);

    /**
     * 获取医院化验项常量定义
     * 
     * @Title: selectByDictHospitalLab
     * @return
     */
    List<AssayHospDictPO> selectByDictHospitalLab();

}