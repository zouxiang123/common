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
     * 更新用户数据
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
     * 根据租户id、所属系统，是否删除标记查询数据
     * 
     * @Title: listByTenantId
     * @param tenantId
     * @param sysOwner
     * @param delFlag
     * @return
     *
     */
    List<SysUserPO> listByTenantId(Integer tenantId, String sysOwner, Boolean delFlag);

    /**
     * 通过id删除对象
     * 
     * @Title: deleteUserById
     * @param id
     * 
     */
    void deleteUserById(Long id);

    /**
     * 根据查询条件查询所有用户
     * 
     * @Title: selectUserWithFilter
     * @param user
     * @return
     * 
     */
    List<SysUserPO> selectUserWithFilter(SysUserPO user);

    /**
     * 判断账户名是否存在
     * 
     * @Title: selectUserWithFilter
     * @param user
     * @return boolean
     * 
     */
    SysUser getUserByAccount(String account, Integer tenantId, String sysOwner);

    /**
     * 验证登陆
     * 
     * @Title: checkLogin
     * @param account
     * @param password
     * @param tenantId
     * @return
     * 
     */
    SysUserPO login(String account, String password, Integer tenantId, String sysOwner);

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
     * 更新用户信息
     * 
     * @Title: updateUser
     * @param user
     * 
     */
    int updateUser(SysUserPO user);

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
     * 上传签名并裁剪保存
     * 
     * @param imgFile
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    String uploadAutograph(MultipartFile imgFile, int x, int y, int width, int height) throws IllegalStateException, IOException;

    /**
     * 保存皮肤
     *
     * @param skin
     * @return
     */
    int saveSkin(String skin);

    /**
     * 获取随机护士
     * 
     * @param constantType
     * @return
     */
    SysUser getRoundUser(Integer constantType);

}
