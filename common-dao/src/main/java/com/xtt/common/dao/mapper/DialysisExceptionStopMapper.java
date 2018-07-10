package com.xtt.common.dao.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.DialysisExceptionStop;
import com.xtt.common.dao.po.DialysisExceptionStopPo;

@Repository
public interface DialysisExceptionStopMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DialysisExceptionStop record);

    int insertSelective(DialysisExceptionStop record);

    DialysisExceptionStop selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DialysisExceptionStop record);

    int updateByPrimaryKey(DialysisExceptionStop record);

    /**
     * 每月患者异常下机透析的数量统计 (不包含系统自动终止)
     * 
     * @return
     */
    public List<Map<String, Object>> selectPatientStopDialysisReport(List<Map<String, Object>> list);

    /**
     * 每月患者异常下机详情
     * 
     * @Title: selectPatientStopDialysisDetail
     * @param record
     * @return
     *
     */
    public List<DialysisExceptionStopPo> selectPatientStopDialysisDetail(DialysisExceptionStop record);

    /**
     * 根据患者id查询终止透析活动数据
     * 
     * @Title: selectForStopByPatientId
     * @param patientId
     * @param startDate
     * @param endDate
     * @param multiTenant
     *            租户id，多个以,分割
     * @return
     * 
     */
    List<DialysisExceptionStopPo> listForStopByPatientId(@Param("patientId") Long patientId, @Param("startDate") Date startDate,
                    @Param("endDate") Date endDate, @Param("stepNo") Integer stepNo, @Param("groupTenant") String groupTenant);
}