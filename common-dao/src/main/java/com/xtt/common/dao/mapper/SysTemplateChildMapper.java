package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SysTemplateChild;
import com.xtt.common.dao.po.SysTemplateChildPO;

@Repository
public interface SysTemplateChildMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysTemplateChild record);

    int insertSelective(SysTemplateChild record);

    SysTemplateChild selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysTemplateChild record);

    int updateByPrimaryKey(SysTemplateChild record);

    /*use define*/
    List<SysTemplateChildPO> selectByCondition(SysTemplateChild record);

    /**
     * 根据系统模板id删除数据
     * 
     * @Title: deleteBySysTemplateId
     * @param id
     *
     */
    void deleteBySysTemplateId(Long sysTemplateId);
}