package com.xtt.common.dao.mapper;

import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SysUser2obj;

@Repository
public interface SysUser2objMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user2obj
     * 
     * @mbggenerated Tue Sep 08 13:30:46 CST 2015
     */
    int insert(SysUser2obj record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user2obj
     * 
     * @mbggenerated Tue Sep 08 13:30:46 CST 2015
     */
    int insertSelective(SysUser2obj record);
}