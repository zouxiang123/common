package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SyncGroupTenant;

@Repository
public interface SyncGroupTenantMapper {
    int insert(SyncGroupTenant record);

    int insertSelective(SyncGroupTenant record);

    /*user define*/
    /**
     * 根据同步组id查询数据
     * 
     * @Title: listTenantId
     * @param fkSyncGroupId
     * @return
     *
     */
    List<SyncGroupTenant> listBySyncGroupId(Long fkSyncGroupId);

    /**
     * 根据同步组id删除数据
     * 
     * @Title: deleteBySyncGroupId
     * @param fkSyncGroupId
     *
     */
    void deleteBySyncGroupId(Long fkSyncGroupId);

    /**
     * 根据租户id查询对应的数据
     * 
     * @Title: getByTenantId
     * @param fkTenantId
     * @return
     *
     */
    SyncGroupTenant getByTenantId(Integer fkTenantId);
}