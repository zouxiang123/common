package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.CmDict;
import com.xtt.common.dao.po.CmDictPO;

@Repository
public interface CmDictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CmDict record);

    int insertSelective(CmDict record);

    CmDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CmDict record);

    int updateByPrimaryKey(CmDict record);

    /*user define*/
    /**
     * 根据条件查询数据
     * 
     * @Title: selectByCondition
     * @param cmDictPO
     * @return
     *
     */
    List<CmDictPO> selectByCondition(CmDict record);

    /**
     * 查询字典表类别
     * 
     * @Title: selectDictionaryCategory
     * @param record
     * @return
     *
     */
    List<CmDictPO> selectDictCategory(CmDict record);
}