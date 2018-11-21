package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AppOwner;

@Repository
public interface AppOwnerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppOwner record);

    int insertSelective(AppOwner record);

    AppOwner selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppOwner record);

    int updateByPrimaryKey(AppOwner record);

    /**
     * 根据条件查询
     * 
     * @Title: listByCond
     * @param appOwner
     * @return
     *
     */
    List<AppOwner> listByCond(AppOwner appOwner);
}