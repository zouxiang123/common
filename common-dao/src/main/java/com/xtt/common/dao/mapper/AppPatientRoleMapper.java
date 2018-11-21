package com.xtt.common.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.AppPatientRole;

@Repository
public interface AppPatientRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppPatientRole record);

    int insertSelective(AppPatientRole record);

    AppPatientRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppPatientRole record);

    int updateByPrimaryKey(AppPatientRole record);

    /**
     * 通过roleId查询对象
     * 
     * @Title: selectByRoleId
     * @param roleId
     * @return
     * 
     */
    List<AppPatientRole> selectByRoleId(Long roleId);

    /**
     * 根据角色id批量删除对象
     * 
     * @Title: batchDeleteByRoleIds
     * @param delRoleIds
     *
     */
    void batchDeleteByRoleIds(Long[] roleIds);

    /**
     * 根据患者id删除角色对应关系
     * 
     * @Title: deleteByPatientId
     * @param fkPatientId
     *
     */
    void deleteByPatientId(Long fkPatientId);

}