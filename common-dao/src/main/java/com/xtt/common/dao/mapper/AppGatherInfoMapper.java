package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AppGatherInfo;

@Repository
public interface AppGatherInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppGatherInfo record);

    int insertSelective(AppGatherInfo record);

    AppGatherInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppGatherInfo record);

    int updateByPrimaryKey(AppGatherInfo record);

    /**
     * 根据className获取数据
     * 
     * @Title: getByClassName
     * @param record
     * @return
     *
     */
    AppGatherInfo getByClassName(AppGatherInfo record);
}