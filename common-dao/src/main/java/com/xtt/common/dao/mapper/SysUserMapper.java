package com.xtt.common.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SysUser;
import com.xtt.common.dao.po.SysUserPO;

@Repository
public interface SysUserMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
     * 
     * @mbggenerated Tue Sep 15 17:05:43 CST 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
     * 
     * @mbggenerated Tue Sep 15 17:05:43 CST 2015
     */
    int insert(SysUser record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
     * 
     * @mbggenerated Tue Sep 15 17:05:43 CST 2015
     */
    int insertSelective(SysUser record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
     * 
     * @mbggenerated Tue Sep 15 17:05:43 CST 2015
     */
    SysUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
     * 
     * @mbggenerated Tue Sep 15 17:05:43 CST 2015
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
     * 
     * @mbggenerated Tue Sep 15 17:05:43 CST 2015
     */
    int updateByPrimaryKey(SysUser record);

    /**
     * 根据姓名查询医生、护士列表
     * 
     * @Title: searchPeopleByName
     * @param name
     * @return
     * 
     */
    List<SysUserPO> searchPersonByName(@Param("name") String name, @Param("tenantId") Integer tenantId, @Param("sysOwner") String sysOwner);

    /**
     * 根据租户id和角色id获取该角色下所有的对象
     * 
     * @Title: selectByParentRoleIds
     * @param tenantId
     * @param roleIds
     * @param sysOwner所属系统
     * @return
     * 
     */
    List<SysUserPO> selectByParentRoleIds(@Param("tenantId") Integer tenantId, @Param("roleIds") String[] roleIds,
                    @Param("sysOwner") String sysOwner);

    /**
     * 根据id查询对象
     * 
     * @Title: selectPOById
     * @param loginUserId
     * @return
     * 
     */
    SysUserPO selectPOById(Long id);

    /**
     * 根据租户id，所属系统，是否删除查询数据
     * 
     * @Title: listByTenantId
     * @param tenantId
     * @param sysOwner
     * @param delFlag
     * @return
     *
     */
    List<SysUserPO> listByTenantId(@Param("tenantId") Integer tenantId, @Param("sysOwner") String sysOwner, @Param("delFlag") Boolean delFlag);

    /**
     * 根据查询条件查询所有用户
     * 
     * @Title: selectUserWithFilter
     * @param user
     * @return
     * 
     */
    List<SysUserPO> selectUserWithFilter(SysUser user);

    /**
     * 根据租户id和账户名称查询数据
     * 
     * @Title: selectUserByAccount
     * @param account
     * @param tenantId
     * @return
     * 
     */
    SysUser selectUserByAccount(@Param("account") String account, @Param("tenantId") Integer tenantId, @Param("sysOwner") String sysOwner);

    SysUserPO login(@Param("account") String account, @Param("password") String password, @Param("tenantId") Integer tenantId,
                    @Param("sysOwner") String sysOwner);

    /**
     * 获取所有角色对应数目
     * 
     * @Title: selectRolesCount
     * @param tenantId
     * @param string
     * @return
     * 
     */
    List<Map<String, Object>> selectRolesCount(@Param("tenantId") Integer tenantId, @Param("sysOwner") String sysOwner);

    /**
     * 重置用户密码
     * 
     * @Title: resetUserPassword
     * @param id
     * @param defaultPassword
     *
     */
    void updatePassword(@Param("id") Long id, @Param("password") String password);

}