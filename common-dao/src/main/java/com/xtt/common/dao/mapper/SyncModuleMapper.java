package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SyncModule;

@Repository
public interface SyncModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SyncModule record);

    int insertSelective(SyncModule record);

    SyncModule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SyncModule record);

    int updateByPrimaryKey(SyncModule record);

    /*user define */
    /**
     * 根据同步组id删除数据
     * 
     * @Title: deleteBySyncGroupId
     * @param fkSyncGroupId
     *
     */
    void deleteBySyncGroupId(Long fkSyncGroupId);

    /**
     * 根据同步组id查询数据
     * 
     * @Title: listBySyncGroupId
     * @param fkSyncGroupId
     * @return
     *
     */
    List<SyncModule> listBySyncGroupId(Long fkSyncGroupId);

}