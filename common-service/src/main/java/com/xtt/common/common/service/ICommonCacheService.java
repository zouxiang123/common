/**   
 * @Title: ICommonCacheService.java 
 * @Package com.xtt.common.common.service
 * Copyright: Copyright (c) 2015
 * @author: bruce   
 * @date: 2016年11月10日 下午6:33:10 
 *
 */
package com.xtt.common.common.service;

public interface ICommonCacheService {

    /**
     * 缓存字典表数据
     * 
     * @Title: cacheDict
     *
     */
    public void cacheDict(Integer tenantId);

    /**
     * 缓存字典表数据
     * 
     * @Title: cacheDict
     *
     */
    public void cacheAppDict();

    /**
     * 缓存用户配置
     * 
     * @Title: cacheDict
     *
     */
    public void cacheAppUserParam();

    /**
     * 缓存系统参数数据
     * 
     * @Title: cacheSysParam
     *
     */
    public void cacheSysParam(Integer tenantId);

    /**
     * 患者权限数据
     * 
     * @Title: cachePermission
     * @param tenantId
     * @throws Exception
     *
     */
    public void cachePermission(String appSysOwner);

    /**
     * 缓存患者数据
     * 
     * @Title: cachePatient
     * @param tenantId
     *
     */
    public void cachePatient();

    /**
     * 缓存动态表单节点
     * 
     * @Title: cacheDynamicFormNode
     * @param tenantId
     * @param sysOwner
     *
     */
    public void cacheDynamicFormNode(Integer tenantId, String sysOwner);

    /**
     * 缓存用户数据
     * 
     * @Title: cacheUser
     *
     */
    public void cacheUser();

    /**
     * 缓存公式配置
     */
    public void cacheFormula(Integer tenantId);

    /**
     * 初始化缓存所有数据
     * 
     * @Title: cacheAll
     *
     */
    public void cacheAll();

    /**
     * 缓存首字母信息
     * 
     * @Title: cacheFamilyInitial
     *
     */
    public void cacheFamilyInitial();

    public void cacheAuthority(String lic);

    public void cacheAuthority();
}
