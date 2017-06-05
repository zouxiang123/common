package com.xtt.common.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xtt.common.dao.model.SysUser;
import com.xtt.common.dao.po.SysUserPO;

@Repository
public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 根据姓名查询医生、护士列表
     *
     * @Title: searchPeopleByName
     * @param name
     * @return
     *
     */
    List<SysUserPO> searchPersonByName(@Param("name") String name, @Param("roleTypes") String[] roleTypes, @Param("tenantId") Integer tenantId,
                    @Param("sysOwner") String sysOwner);

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
     * 根据条件查询用户管理的用户列表
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

    /**
     * 根据id查询数据
     *
     * @Title: getById
     * @param id
     * @return
     *
     */
    SysUserPO getById(Long id);

    /**
     * 查询所有用户
     *
     * @Title: listAll
     * @return
     *
     */
    List<SysUserPO> listAll();

    /**
     * 根据用户id和租户id查询包含角色数据的用户对象
     *
     * @Title: getFullById
     * @param id
     * @param sysOwner
     * @param tenantId
     * @return
     *
     */
    SysUserPO getFullById(@Param("id") Long id, @Param("sysOwner") String sysOwner, @Param("tenantId") Integer tenantId);

    /**
     * 集团管理员登陆
     *
     * @Title: groupAdminLogin
     * @param account
     * @param password
     * @param fkTenantId
     * @param userType
     *            用户类型
     * @return 用户对象
     *
     */
    SysUserPO groupAdminLogin(@Param("account") String account, @Param("password") String password, @Param("fkTenantId") Integer fkTenantId,
                    @Param("userType") String userType);

    /**
     * 根据账户名称查询集团用户
     * 
     * @Title: getGroupUserByAccount
     * @param account
     *            账户名
     * @param userType
     *            用户类型
     * @param fkTenantId
     *            租户id
     * @return
     *
     */
    SysUser getGroupUserByAccount(@Param("account") String account, @Param("userType") String userType, @Param("fkTenantId") Integer fkTenantId);

    /**
     * 根据id和集团id查询集团用户数据
     * 
     * @Title: getGroupFullById
     * @param id
     *            账户id
     * @param tenantId
     *            集团租户id
     * @return
     *
     */
    SysUserPO getGroupFullById(@Param("id") Long id, @Param("tenantId") Integer tenantId);

}