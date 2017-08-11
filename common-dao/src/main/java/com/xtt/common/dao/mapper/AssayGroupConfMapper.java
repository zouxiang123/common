package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AssayGroupConf;

@Repository
public interface AssayGroupConfMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AssayGroupConf record);

    int insertSelective(AssayGroupConf record);

    AssayGroupConf selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AssayGroupConf record);

    int updateByPrimaryKey(AssayGroupConf record);

    List<AssayGroupConf> selectByCondition(AssayGroupConf record);
}