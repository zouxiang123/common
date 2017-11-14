package com.xtt.common.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SyncGroup;
import com.xtt.common.dao.po.SyncGroupPO;

@Repository
public interface SyncGroupMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SyncGroup record);

    int insertSelective(SyncGroup record);

    SyncGroup selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SyncGroup record);

    int updateByPrimaryKey(SyncGroup record);

    /*user define*/
    /**
     * 根据集团id查询显示用同步组数据
     * 
     * @Title: listByGroupIdForShow
     * @param fkGroupId
     * @return
     *
     */
    List<SyncGroupPO> listByGroupIdForShow(Integer fkGroupId);

    /**
     * 根据id查询
     * 
     * @Title: getById
     * @param id
     * @return
     *
     */
    SyncGroupPO getById(Long id);

    /**
     * 查询可以关联的租户id
     * 
     * @Title: listCanAssociateTenants
     * @param syncGroupId
     * @param groupId
     * @return
     *
     */
    List<Map<String, String>> listCanAssociateTenants(@Param("syncGroupId") Long syncGroupId, @Param("groupId") Integer groupId);
}