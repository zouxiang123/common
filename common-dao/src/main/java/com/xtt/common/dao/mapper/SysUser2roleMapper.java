package com.xtt.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SysUser2role;

@Repository
public interface SysUser2roleMapper {
	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user2role
	 *
	 * @mbggenerated Thu Oct 15 18:49:09 CST 2015
	 */
	int insert(SysUser2role record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user2role
	 *
	 * @mbggenerated Thu Oct 15 18:49:09 CST 2015
	 */
	int insertSelective(SysUser2role record);

	/**
	 * 通过roleId查询对象
	 * 
	 * @Title: selectByRoleId
	 * @param roleId
	 * @return
	 * 
	 */
	List<SysUser2role> selectByRoleId(Long roleId);

	/**
	 * 通过roleId和userId查询对象
	 * 
	 * @Title: selectByUserAndRoleId
	 * @param roleId
	 * @param userId
	 * @return
	 *
	 */
	SysUser2role selectByUserAndRoleId(@Param("roleId") Long roleId, @Param("userId") Long userId);

	/**
	 * 通过roleId和userId删除对象
	 * 
	 * @Title: selectByUserAndRoleId
	 * @param roleId
	 * @param userId
	 * @return
	 *
	 */
	int deleteByUserAndRoleIds(@Param("roleIds") List<Long> roleIds, @Param("userId") Long userId);

	/**
	 * 根据角色id批量删除对象
	 * 
	 * @Title: batchDeleteByRoleIds
	 * @param delRoleIds
	 *
	 */
	void batchDeleteByRoleIds(Long[] roleIds);

	/**
	 * 
	 * @Title: deleteByUserId
	 * @param userId
	 *
	 */
	void deleteByUserId(Long userId);
}