/**
 * @Title: ITenantService.java
 * @Package com.xtt.common.system.service
 * Copyright: Copyright (c) 2015
 * @author: bruce
 * @date: 2016年3月3日 下午1:37:42
 *
 */
package com.xtt.common.common.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.xtt.common.dao.model.SysGroup;
import com.xtt.common.dao.model.SysGroupTenant;
import com.xtt.common.dao.model.SysTenant;
import com.xtt.common.dao.po.SysTenantPO;

public interface ISysTenantService {
    /**
     * 根据租户id查询租户对象
     *
     * @Title: getById
     * @param id
     * @return
     *
     */
    public SysTenant getById(Integer id);

    /**
     * 查询所有有效的租户
     *
     * @Title: selectAll
     * @return
     *
     */
    public List<SysTenant> listAllEnable();

    /**
     * 根据账户名称查询
     *
     * @param account
     * @param sysOwner
     * @return
     */
    public List<SysTenant> listByAccount(String account, String sysOwner);

    /**
     * 根据集团id查询所有普通租户
     * 
     * @Title: listByGroupId
     * @param groupId
     * @param isEnable
     * @return
     *
     */
    public List<SysTenant> listAllNormalByGroupId(Integer groupId, Boolean isEnable);

    /**
     * 查询所有有效的普通租户，不包含集团虚拟租户
     * 
     * @Title: listAllEnableNormal
     * @return
     *
     */
    public List<SysTenant> listAllEnableNormal();

    /**
     * 获取默认租户
     * 
     * @Title: getDefault
     * @return
     *
     */
    public SysTenant getDefault();

    /**
     * 根据多个租户id查询数据
     * 
     * @Title: listByIds
     * @param ids
     * @return
     *
     */
    public List<SysTenant> listByIds(Collection<Integer> ids);

    /**
     * 保存基础表数据
     * 
     * @Title: saveSysBasicsGroup
     * @param sysBasicsGroupPo
     * @return
     *
     */
    Object saveSysBasicsGroup(SysTenantPO sysTenantPO);

    /**
     * 查询所有的集团
     * 
     * @param sysGroup
     * 
     * @Title: getSysGroupAll
     * @return
     *
     */
    List<SysGroup> listSysGroup(SysGroup sysGroup);

    /**
     * 保存新增
     * 
     * @Title: save
     * @param urlName
     * @param sysTenant
     *
     */
    Map<String, Object> save(MultipartFile logoUpload, MultipartFile logoPrintUpload, MultipartFile tvLogoUpload, SysTenantPO sysTenant)
                    throws IOException;

    /**
     * 查询全部的租户
     * 
     * @Title: getTenantList
     * @param sysTenant
     * @return
     *
     */
    List<SysTenant> listTenant(SysTenantPO sysTenant);

    /**
     * 导入License
     * 
     * @Title: licensorUpload
     * @param licensorUpload
     * @param sysTenant
     * @return
     *
     */
    Map<String, Object> uploadLicense(MultipartFile licensorUpload, SysTenantPO sysTenant) throws IOException;

    /**
     * 获取所有集团
     * 
     * @Title: getSysGroupAll
     * @return
     *
     */
    List<SysGroup> listSysGroup();

    /**
     * 根据集团id查询所属集团
     * 
     * @Title: getSysGroupByFkTenantId
     * @param sysTenant
     * @return
     *
     */
    SysGroupTenant getSysGroupTenantByFkTenantId(Integer fkTenantId);

    /**
     * 查询医院名称是否重复
     * 
     * @Title: getCheckTenanName
     * @param sysTenant
     * @return
     *
     */
    List<SysTenant> listCheckTenanName(SysTenant sysTenant);

    /**
     * 新建默认用户
     * 
     * @Title: savaUser
     * @param sysTenant
     *
     */
    public void saveUser(SysTenantPO sysTenant);

    /**
     * 根据父节点租户id查询数据（未做级联查询）**
     * 
     * @Title: listByPTenantId
     * @param pTenantId
     * @return
     *
     */
    public List<SysTenant> listByPTenantId(Integer pTenantId);
}
