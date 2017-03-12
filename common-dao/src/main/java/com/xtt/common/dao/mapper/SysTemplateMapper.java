package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SysTemplate;
import com.xtt.common.dao.po.SysTemplatePO;

@Repository
public interface SysTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysTemplate record);

    int insertSelective(SysTemplate record);

    SysTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysTemplate record);

    int updateByPrimaryKey(SysTemplate record);

    /*use define*/
    /**
     * 根据类型查询模板
     * 
     * @Title: selectByType
     * @param type
     * @param fkTenantId
     * @return
     * 
     */
    List<SysTemplatePO> selectByType(@Param("type") String type, @Param("fkTenantId") Integer fkTenantId, @Param("sysOwner") String sysOwner);

    /**
     * 查询模板类型
     * 
     * @Title: selectTemplateType
     * @return
     * 
     */
    List<SysTemplate> selectTemplateType(@Param("fkTenantId") Integer fkTenantId, @Param("sysOwner") String sysOwner);

    /**
     * 根据类型查询is_default 为1的数据
     * 
     * @param record
     * @return
     */
    SysTemplate getTemplate(SysTemplate record);
}