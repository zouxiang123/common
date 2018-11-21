package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AppOwnerMenu;

@Repository
public interface AppOwnerMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppOwnerMenu record);

    int insertSelective(AppOwnerMenu record);

    AppOwnerMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppOwnerMenu record);

    int updateByPrimaryKey(AppOwnerMenu record);

    /**
     * 根据owner和type删除
     * 
     * @Title: deleteByOwnerAndType
     * @param sysOwner
     * @param types
     *
     */
    void deleteByOwnerAndType(@Param("sysOwner") String sysOwner, @Param("types") String[] types);

    /**
     * 批量插入数据
     * 
     * @Title: batchInsert
     * @param menus
     *
     */
    void insertBatch(@Param("menus") List<AppOwnerMenu> menus);
}