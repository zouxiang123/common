package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AppHospitalMenu;

@Repository
public interface AppHospitalMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppHospitalMenu record);

    int insertSelective(AppHospitalMenu record);

    AppHospitalMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppHospitalMenu record);

    int updateByPrimaryKey(AppHospitalMenu record);

    /**
     * 通过角色id和类型删除对象
     * 
     * @Title: deleteByHospitalIdAndType
     * @param fkHospitalId
     * @param types
     *
     */
    void deleteByHospitalIdAndType(@Param("fkHospitalId") Integer fkHospitalId, @Param("types") String[] types);

    /**
     * 批量插入数据
     * 
     * @Title: batchInsert
     * @param menus
     *
     */
    void insertBatch(@Param("menus") List<AppHospitalMenu> menus);
}