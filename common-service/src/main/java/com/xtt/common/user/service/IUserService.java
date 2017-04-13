/**
 * @Title: IUserService.java
 * @Package com.xtt.common.system.service
 * Copyright: Copyright (c) 2015
 * @author: bruce
 * @date: 2015年10月10日 下午12:33:15
 *
 */
package com.xtt.common.user.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.xtt.common.dao.model.SysUser;
import com.xtt.common.dao.po.SysUserPO;

public interface IUserService {
    /**
     * 根据租户id获取所有的医生对象
     *
     * @param tenantId
     *
     * @Title: getDoctors
     * @return
     *
     */
    List<SysUserPO> getDoctors(Integer tenantId, String sysOwner);

    /**
     * 根据租户id获取所有的护士对象
     *
     * @param tenantId
     *
     * @Title: getNurses
     * @return
     *
     */
    List<SysUserPO> getNurses(Integer tenantId, String sysOwner);

    /**
     * 根据租户id获取所有的医生和护士对象
     *
     * @param tenantId
     *
     * @Title: getNurse
     * @return
     *
     */
    List<SysUserPO> getNurseAndDoctor(Integer tenantId, String sysOwner);

    /**
     * 根据租户id获取arr对应的role type角色对应的用户对象
     * 
     * @param tenantId
     * @param arr
     * @param sysOwner
     * @return
     */
    List<SysUserPO> listByRoleTypes(Integer tenantId, String[] arr, String sysOwner);

    /**
     * 通过用户Id获取用户
     *
     * @Title: getUserById
     * @param userId
     * @return
     *
     */
    SysUserPO selectById(Long userId);

    /**
     * 更新用户数据<br>
     *
     * @Title: updateUser
     * @param user
     *
     */
    String saveUser(SysUserPO user);

    /**
     * 上传图片
     *
     * @Title: uploadImage
     * @param image
     * @throws IOException
     * @throws IllegalStateException
     *
     */
    String uploadImage(MultipartFile image) throws IllegalStateException, IOException;

    /**
     * 根据租户Id获取该租户下所有用户
     *
     * @Title: selectByTenantId
     * @param tenantId
     * @return
     *
     */
    List<SysUserPO> selectByTenantId(Integer tenantId, String sysOwner);

    /**
     * 查询所有的用户
     *
     * @Title: listAll
     * @return
     *
     */
    List<SysUserPO> listAll();

    /**
     * 通过id删除对象<br>
     * 设置del_flag = 1,删除用户和角色的关联数据
     *
     * @Title: deleteUserById
     * @param id
     *
     */
    void deleteUserById(Long id);

    /**
     * 根据条件查询用户管理的用户列表
     *
     * @Title: selectUserWithFilter
     * @param user
     * @return
     *
     */
    List<SysUserPO> selectUserWithFilter(SysUserPO user);

    /**
     * 根据账户名称查询用户
     *
     * @Title: getUserByAccount
     * @param account
     * @param tenantId
     *            租户id
     * @param sysOwner
     *            所属系统
     * @param groupFlag
     *            是否是集团用户
     * @return
     *
     */
    SysUser getUserByAccount(String account, Integer tenantId, String sysOwner, boolean groupFlag);

    /**
     * 获取医生数目
     *
     * @Title: getDoctorsCount
     * @param tenantId
     * @return
     *
     */
    Integer getDoctorsCount(Integer tenantId, String sysOwner);

    /**
     * 获取护士数目
     *
     * @Title: getDoctorsCount
     * @param tenantId
     * @return
     *
     */
    Integer getNursesCount(Integer tenantId, String sysOwner);

    /**
     * 根据用户id更新用户基础信息
     *
     * @Title: updateUserBasicInfo
     * @param user
     *
     */
    int updateUserBasicInfo(SysUser user);

    /**
     * 重置用户密码
     *
     * @Title: resetUserPassword
     * @param id
     *
     */
    void resetUserPassword(Long id);

    /**
     * 获取所有角色为其他的用户
     *
     * @Title: getOthers
     * @param tenantId
     * @return
     *
     */
    List<SysUserPO> getOthers(Integer tenantId, String sysOwner);

    /**
     * 获取不同角色下的用户数
     *
     * @Title: getRolesCount
     * @param tenantId
     * @return
     *
     */
    List<Map<String, Object>> getRolesCount(Integer tenantId, String sysOwner);

    /**
     * 更新用户密码
     *
     * @Title: updatePassword
     * @param user
     *
     */
    int updatePassword(SysUserPO user);

    /**
     * 获取用户详细信息<br>
     * 集团方式：根据创建该用户的租户id查询数据（包含拥有的租户，不包含角色数据，不含所属系统）<br>
     * 普通方式：根据用户和租户的关联表获取数据（包含角色数据，所属系统，拥有的租户）
     * 
     *
     * @Title: getFullById
     * @param id
     *            用户id
     * @param sysOwner
     *            所属系统
     * @param tenantId
     *            租户id
     * @param groupFlag
     *            以集团方式获取用户数据
     * @return
     *
     */
    SysUserPO getFullById(Long id, String sysOwner, Integer tenantId, boolean groupFlag);

    /**
     * 登陆动作接口
     *
     * @Title: loginSubmit
     * @param account
     * @param password
     * @param tenantId
     * @param groupAdmin
     *            是否是集团管理员登陆
     * @param sysOwner
     *            所属系统
     * @param isSwitch
     *            是否是切换系统
     * @return
     * @throws Exception
     *
     */
    Map<String, Object> loginSubmit(String account, String password, Integer tenantId, Boolean groupAdmin, String sysOwner, Boolean isSwitch)
                    throws Exception;

    /**
     * 根据集团管理员账户查询查询集团管理员
     * 
     * @Title: getGroupAdminByAccount
     * @param account
     * @return
     *
     */
    SysUser getGroupAdminByAccount(String account);

    /**
     * 获取用户所属系统和租户数据列表
     * 
     * @param id
     *            用户id
     * 
     * @Title: getTenantsAndSysOwnersById
     * @return {tenantId:{name:租户名称,owners:{key,name}} }
     *
     */
    Map<Integer, Map<String, Object>> getTenantsAndSysOwnersById(Long id);

}
