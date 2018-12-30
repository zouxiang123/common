package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDictIcd;

@Repository
public interface CmDictIcdMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDictIcd record);

    int insertSelective(CmDictIcd record);

    CmDictIcd selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDictIcd record);

    int updateByPrimaryKey(CmDictIcd record);

    /**
     * 根据icd名称查询
     * 
     * @Title: listByItemName
     * @param itemName
     * @return
     *
     */
    List<CmDictIcd> listByItemName(CmDictIcd cmDictIcd);

}