package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.FamilyInitial;

/**
 * @ClassName: FamilyInitialMapper
 * @date: 2017年4月18日 上午10:31:29
 * @version: V1.0
 *
 */
@Repository
public interface FamilyInitialMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FamilyInitial record);

    int insertSelective(FamilyInitial record);

    FamilyInitial selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FamilyInitial record);

    int updateByPrimaryKey(FamilyInitial record);

    /**
     * @Title: 根据条件查询
     * @param record
     * @return
     *
     */
    List<FamilyInitial> listByCondition(FamilyInitial record);

}