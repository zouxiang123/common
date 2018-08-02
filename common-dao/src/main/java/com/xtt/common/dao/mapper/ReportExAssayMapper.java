package com.xtt.common.dao.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.ReportExAssay;
import com.xtt.common.dao.po.ReportExAssayPo;

@Repository
public interface ReportExAssayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReportExAssay record);

    int insertSelective(ReportExAssay record);

    ReportExAssay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReportExAssay record);

    int updateByPrimaryKey(ReportExAssay record);

    /**
     * 获取化验结果开始化验时间的最大值和最小值日期数据
     * 
     * @Title: getByDialysisCampaignStartTime
     * @param fkTenantId
     * @return
     *
     */
    Map<String, Date> getByAssayStartTime(@Param("fkTenantId") Integer fkTenantId);

    /**
     * 查询化验结果异常数据
     * 
     * @Title: listExAssayByPatient
     * @param record
     * @return
     *
     */
    List<Map> listExAssayByPatient(ReportExAssayPo record);

    /**
     * 查询患者化验结果数据详细
     * 
     * @Title: listExAssayByPatientDeital
     * @param record
     * @return
     *
     */
    List<ReportExAssayPo> listExAssayByPatientDeital(ReportExAssayPo record);

    /**
     * 删除指定条件内容
     * 
     * @Title: deleteByCondition
     * @param record
     *
     */
    void deleteByCondition(@Param("startTime") String startTime, @Param("fkTenantId") Integer fkTenantId);

}