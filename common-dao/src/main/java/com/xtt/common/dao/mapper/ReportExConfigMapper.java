package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.ReportExConfig;

@Repository
public interface ReportExConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReportExConfig record);

    int insertSelective(ReportExConfig record);

    ReportExConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReportExConfig record);

    int updateByPrimaryKey(ReportExConfig record);

    /**
     * 根据日期获取异常提醒配置内容
     * 
     * @Title: listByReportExConfig
     * @param record
     * @return
     *
     */
    List<ReportExConfig> listByCondition(ReportExConfig record);

    /**
     * 修改配置参数状态
     * 
     * @Title: updateByStatus
     * @param ids
     *            配置参数ID集合
     * @param status
     *            修改状态值
     * @param tenantId
     *            租户ID
     *
     */
    void updateByStatus(@Param("ids") List<ReportExConfig> ids, @Param("status") Integer status, @Param("tenantId") Integer tenantId);
}