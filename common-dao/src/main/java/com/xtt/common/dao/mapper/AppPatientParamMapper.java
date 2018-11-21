package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AppPatientParam;

@Repository
public interface AppPatientParamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppPatientParam record);

    int insertSelective(AppPatientParam record);

    AppPatientParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppPatientParam record);

    int updateByPrimaryKey(AppPatientParam record);

    /**
     * 根据条件查询
     * 
     * @Title: listByCond
     * @param record
     * @return
     *
     */
    List<AppPatientParam> listByCond(AppPatientParam record);

    /**
     * 根据唯一索引删除
     * 
     * @Title: deleteByUniquekey
     * @param userId
     * @param itemCode
     *
     */
    void deleteByUniquekey(@Param("fkUserId") Long fkUserId, @Param("itemCode") String itemCode);
}